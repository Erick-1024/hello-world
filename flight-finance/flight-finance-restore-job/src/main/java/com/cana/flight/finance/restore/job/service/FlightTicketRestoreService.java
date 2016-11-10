package com.cana.flight.finance.restore.job.service;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.flight.finance.dao.mapper.FlightTicketCustomMapper;
import com.cana.flight.finance.dao.mapper.gen.FlightTicketMapper;
import com.cana.flight.finance.dao.po.FlightTicket;

@Service
public class FlightTicketRestoreService {
    @Resource
    private PropertiesMapper propertiesMapper;
    @Resource
    private FlightTicketMapper flightTicketMapper;
    @Resource
    private FlightTicketCustomMapper flightTicketCustomMapper;
    private int lineNum=0;

    public void restore(String filePath, int fileSubscript, long filePointer) {
        long startTime = System.currentTimeMillis();
        long pointerTemp = filePointer;
        boolean allFinished = true;
        List<File> listFiles = obtainFiles(filePath);
        if (listFiles == null)
            return;
        for (int i = fileSubscript; i < listFiles.size(); i++) {
            System.out.printf("正在读取第%d个文件,共有%s个文件\n", i + 1, listFiles.size());
            System.out.printf("[%s]:%s已完成%s\n", getCurrentTime(), listFiles.get(i).getName(), "0%");
            allFinished = readFileAndSave(pointerTemp, listFiles.get(i));
            if (!allFinished)
                break;
            pointerTemp = 0;
        }
        if (allFinished) {
            long endTime = System.currentTimeMillis();
            System.out.println("总行数："+lineNum);
            System.out.println("操作全部完成所用时间为：" + (endTime - startTime));
        } else {
            System.out.println("------执行过程出现异常-------");
        }
    }

    private List<File> obtainFiles(String filePath) {
        File file = new File(filePath);
        File[] files = file.listFiles();
        if (files == null) {
            System.out.printf("%s路径不存在\n", filePath);
            return null;
        }
        List<File> listFiles = sortFileByName(file.listFiles());
        return listFiles;
    }

    public List<File> sortFileByName(File[] files) {
        List<File> listfiles = new ArrayList<>();
        for (File f : files) {
            listfiles.add(f);
        }
        Collections.sort(listfiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return listfiles;
    }

    public String getCurrentTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat simple = new SimpleDateFormat("MM-dd HH:mm:ss");
        return simple.format(time);
    }

    @SuppressWarnings("resource")
    private boolean readFileAndSave(long pos, File file) {
        long pointL = pos;
        try {
            RandomAccessFile accessFile = new RandomAccessFile(file, "r");
            accessFile.seek(pos);
            String temp = null;
            long length = accessFile.length();
            pointL = accessFile.getFilePointer();
            List<FlightTicket> flightTicketList = new ArrayList<>();
            accessFile.readLine();
            int i=1;
            long pointt=0;
            while ((temp = accessFile.readLine()) != null) {
                
                i++;
                if(StringUtils.isBlank(temp)){
                    System.out.printf("第%d行记录为空",i);
                    continue;
                }else{
                    lineNum++;
                }
                temp = new String(temp.getBytes("ISO-8859-1"), "utf-8");
                
                FlightTicket flightTicket = convertFlightTicket(temp, file.getName(),pointt);
                pointt=accessFile.getFilePointer();
                if (flightTicket == null)
                    continue;
                flightTicketList.add(flightTicket);
                if (flightTicketList.size() == 5000 || accessFile.getFilePointer() == length) {
                    insertBatch(flightTicketList,file.getName());
                    pointL = accessFile.getFilePointer();
                    printPercentage(pointL, length, file.getName());
                    flightTicketList.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("restoreError[file:%s, pointer:%d]\n", file.getName(), pointL);
            return false;
        }
        return true;
    }

    private void printPercentage(long low, long high, String fileName) {
        float d = (float) low / high * 100;
        String percentage = String.format("%.2f", d);
        System.out.printf("[%s]:%s文件已完成%s\n", getCurrentTime(), fileName, percentage + "%");
    }

    // private void insertStockData(List<FlightTicket> flightTicket) {
    // try {
    // // flightTicketMapper.insertSelective(flightTicket);
    // } catch (DuplicateKeyException e) {
    // System.out.println("重复数据插入异常");
    // }
    // }

    private void insertBatch(List<FlightTicket> flightTickets,String fileName) {
        try {
            flightTicketCustomMapper.insertFlightTicketByBatch(flightTickets);
        } catch (DuplicateKeyException e) {
            System.out.println("重复数据插入异常"+e);
            System.out.println(fileName);
        }
    }

    private FlightTicket convertFlightTicket(String temp, String fileName,long pointer) {
        FlightTicket flightTicket = new FlightTicket();
        if(StringUtils.isBlank(temp)){
            System.out.println("该行记录为空");
            return null;
        }   
        if (temp.startsWith("\"")) {
            temp = temp.substring(1);
        }
        if (temp.endsWith("\"")) {
            temp = temp.substring(0, temp.length() - 1);
        }
        String flightInfo[] = temp.split("\\|\\|");
        try {
            
            int i = 0;
            flightTicket.setRecordId(flightInfo[i++].toString());
            flightTicket.setCustomerId(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setCustomerName(StringUtils.trimToNull(flightInfo[i++]));
            String isDometic = flightInfo[i++];
            flightTicket.setIsDomestic(StringUtils.isBlank(isDometic) ? null : BooleanUtils.toBooleanObject(isDometic));
            flightTicket.setOrderNo(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setOrderType(StringUtils.trimToNull(flightInfo[i++]));
            String isAdjust = flightInfo[i++];
            flightTicket.setIsAdjust(StringUtils.isBlank(isAdjust) ? null : BooleanUtils.toBooleanObject(isAdjust));
            flightTicket.setCompleteIssueTime(StringUtils.trimToNull(flightInfo[i++]));
            if(flightTicket.getCompleteIssueTime()==null)
                System.out.println("第"+pointer+"行CompleteIssudTime为空");
            flightTicket.setTicketNo(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setConjunctionTicketSeqNo(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setSupplierName(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setPayType(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setTotalAmount(Long.valueOf(flightInfo[i++]));
            flightTicket.setTicketPrice(Long.valueOf(flightInfo[i++]));
            flightTicket.setFuelTax(Long.valueOf(flightInfo[i++]));
            flightTicket.setConstructionFee(Long.valueOf(flightInfo[i++]));
            flightTicket.setCrsPnr(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setTicketOfficeNo(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setAirline(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setItinerary(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setDepartureDateTime(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setCabinCode(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setPassengerName(StringUtils.trimToNull(flightInfo[i++]));
            flightTicket.setPassengerType(StringUtils.trimToNull(flightInfo[i++]));
//            if(i==flightInfo.length){
//                
//            }else if (i == flightInfo.length-1) {
//                flightTicket.setDoucmentType(StringUtils.trimToNull(flightInfo[i++]));
//            } else if(i==flightInfo.length-2){
//                flightTicket.setDoucmentType(StringUtils.trimToNull(flightInfo[i++]));
//                flightTicket.setDocumentNo(StringUtils.trimToNull(flightInfo[i++]));
//            }else{
//                flightTicket.setDoucmentType(StringUtils.trimToNull(flightInfo[i++]));
//                flightTicket.setDocumentNo(StringUtils.trimToNull(flightInfo[i++]));
//                flightTicket.setMobileNo(StringUtils.trimToNull(flightInfo[i++]));
//                if (i == flightInfo.length - 1)
//                    flightTicket.setOrigRecordId(StringUtils.trimToNull(flightInfo[i++]));
//            }
            if(i < flightInfo.length)
                flightTicket.setDoucmentType(StringUtils.trimToNull(flightInfo[i++]));
            if(i < flightInfo.length)
                flightTicket.setDocumentNo(StringUtils.trimToNull(flightInfo[i++]));
            if(i < flightInfo.length)
                flightTicket.setMobileNo(StringUtils.trimToNull(flightInfo[i++]));
            if(i < flightInfo.length)
                flightTicket.setOrigRecordId(StringUtils.trimToNull(flightInfo[i++]));
            
            flightTicket.setCreateTime(new Date());
            flightTicket.setUpdateTime(new Date());
        } catch (Exception e) {
            System.out.printf("%s数据格式异常\n", fileName);
            e.printStackTrace();
            System.out.printf("内容:%s\n", temp);
            System.out.println("长度"+flightInfo.length+"指针:"+pointer);
            return null;
        }
        return flightTicket;
    }

}
