/**
 *  Copyright © 2016 Cana. All rights reserved.
 */
package com.cana.bankgate.test.generate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;


/**
 * @author ducer
 *
 */
public class LogAnalyse {
	private final String vbam = "vbam-front-biz";//2015-12-22.log
	private final String account = "bankgate-server";
	private final String bankgate = "account-server";
	private final String logTag = "(localhost.localdomain)-debug-";
	private DateTime start;
	private DateTime end;
	private String rootPath;
	private final String keyWord = "主动开户异常";
	private ExecutorService threadpool = Executors.newFixedThreadPool(30);
	
	public static void main(String[] args) throws Exception{
		LogAnalyse analyse = new LogAnalyse();
		analyse.setStart(new DateTime("2015-11-17"));
		analyse.setEnd(new DateTime("2016-01-07"));
		analyse.setRootPath("/home/ducer/Public/history/");
		Map<String, List<String>> data = analyse.execute();
		File file = new File("/home/ducer/Public/result");
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		try{
			Set<Entry<String,List<String>>> entries = data.entrySet();
			for(Entry<String,List<String>> entry : entries){
				for(String str : entry.getValue()){
					writer.write(str);
				}
			}
		}catch(Exception e){
			throw e;
		}finally{
			writer.close();
		}
	}
	
	public Map<String, List<String>> execute() throws Exception{
		List<Future<Pair<String,List<String>>>> futures = new ArrayList<Future<Pair<String,List<String>>>>();
		int fileCount = end.getDayOfYear() - start.getDayOfYear() + 1;
		for(int i = 0 ; i<fileCount ;i++){
			AnalyseTraceIdThread idAnalyse = new AnalyseTraceIdThread(vbam,start.plusDays(0).toString("yyyy-MM-dd"),keyWord);
			Future<Pair<String,List<String>>> future = threadpool.submit(idAnalyse);
			futures.add(future);
		}
		List<Future<Map<String, List<String>>>> datas = new ArrayList<Future<Map<String, List<String>>>>();
		boolean finish = false;
		while(!finish){
			System.out.println("正在分析!");
			finish = true;
			for(Future<Pair<String,List<String>>> future :futures){
				if(future.isDone()){
					System.out.println(vbam+"-["+future.get().getKey()+"]已经分析完毕!");
					datas.add(threadpool.submit(new AnalyseMsgThread(vbam, future.get().getKey(),future.get().getValue())));
					datas.add(threadpool.submit(new AnalyseMsgThread(account, future.get().getKey(),future.get().getValue())));
					datas.add(threadpool.submit(new AnalyseMsgThread(bankgate, future.get().getKey(),future.get().getValue())));
				}
				else{
					finish = false;
				}
			}
			Thread.sleep(1000);
		}
		
		Map<String, List<String>> result = new HashMap<String,List<String>>();
		finish = false;
		while(!finish){
			System.out.println("正在分析真正需要的数据!");
			finish = true;
			for(Future<Map<String, List<String>>> data :datas){
				if(data.isDone()){
					Set<Entry<String,List<String>>> entries = data.get().entrySet();
					for(Entry<String,List<String>> entry :entries){
						if(result.get(entry.getKey()) == null){
							result.put(entry.getKey(), entry.getValue());
						}
						else{
							result.get(entry.getKey()).addAll(entry.getValue());
						}
					}
				}
				else{
					finish = false;
				}
			}
			Thread.sleep(1000);
		}
		return result;
	}
	
	public class AnalyseTraceIdThread implements Callable<Pair<String,List<String>>> {
		private String serverName;
		private String date;
		private String keyWord;
		public AnalyseTraceIdThread(String serverName, String date, String keyWord) {
			this.serverName = serverName;
			this.date = date;
			this.keyWord = keyWord;
		}
		@Override
		public Pair<String,List<String>> call() throws Exception {
			File file = new File(rootPath + serverName + logTag + date + ".log");
			if (!file.exists()) {
				System.out.println("该日志文件不存在:"+serverName + logTag + date + ".log");
				return null;
			}
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			String temp = null;
			Pair<String,List<String>> traceIds = new MutablePair<String,List<String>>(date,new ArrayList<String>());
			try {
				while ((temp = bReader.readLine()) != null) {
					if (temp.contains(keyWord) && temp.contains("rpid=")) {
						String pid = temp.substring(temp.indexOf("rpid=") + 1);
						traceIds.getValue().add(pid.substring(0, pid.indexOf("]")));
					}
				}
				return traceIds;
			} catch (Exception e) {
				throw e;
			} finally {
				bReader.close();
			}
		}
	}
	
	public class AnalyseMsgThread implements Callable<Map<String, List<String>>> {
		private String serverName;
		private String date;
		private List<String> traceIds;

		public AnalyseMsgThread(String serverName, String date, List<String> traceIds) {
			this.serverName = serverName;
			this.date = date;
			this.traceIds = traceIds;
		}
		@Override
		public Map<String, List<String>> call() throws Exception {
			File file = new File(rootPath + serverName + logTag + date + ".log");
			if (!file.exists()) {
				System.out.println("该日志文件不存在:"+serverName + logTag + date + ".log");
				return null;
			}
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			String temp = null;
			Map<String, List<String>> data = new HashMap<String, List<String>>();
			try {
				while ((temp = bReader.readLine()) != null) {
					if (!temp.contains("rpid=")) continue;
					String pid = temp.substring(temp.indexOf("rpid=") + 1);
					String traceId = pid.substring(0, pid.indexOf("]"));
					if (traceIds.contains(traceId)) {
						if (data.get(traceId) == null) {
							data.put(traceId, new ArrayList<String>());
						}
						data.get(traceId).add(temp);
						/*
						if (data.get(traceId).get(serverName) == null) {
							data.get(traceId).put(serverName, new ArrayList<String>());
						}
						data.get(traceId).get(serverName).add(temp.substring(temp.indexOf(traceId) + 2));*/
					}
				}
				return data;
			} catch (Exception e) {
				throw e;
			} finally {
				bReader.close();
			}
		}
	}

	public void setStart(DateTime start) {
		this.start = start;
	}

	public void setEnd(DateTime end) {
		this.end = end;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
}
