package com.cana.asset.search.canal;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.CanalEntry.TransactionBegin;
import com.alibaba.otter.canal.protocol.CanalEntry.TransactionEnd;
import com.alibaba.otter.canal.protocol.Message;
import com.cana.asset.search.handler.IBatchIndexUpdateHandler;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.google.protobuf.InvalidProtocolBufferException;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;

@Service
public class CanalClientLaucher implements ApplicationListener<ContextRefreshedEvent>, DisposableBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private volatile boolean running = false;
	
	private final String             SEP                = SystemUtils.LINE_SEPARATOR;
	private final String             DATE_FORMAT        = "yyyy-MM-dd HH:mm:ss";
	private String                   context_format     = null;
	private String                   row_format         = null;
	private String                   transaction_format = null;
	
	@Resource
	private IBatchIndexUpdateHandler handler;
	
	private void init() {
		context_format = SEP + "****************************************************" + SEP;
		context_format += "* Batch Id: [{}] ,count : [{}] , memsize : [{}] , Time : {}" + SEP;
		context_format += "* Start : [{}] " + SEP;
		context_format += "* End : [{}] " + SEP;
		context_format += "****************************************************" + SEP;

		row_format = SEP
				+ "----------------> binlog[{}:{}] , name[{},{}] , eventType : {} , executeTime : {} , delay : {}ms"
				+ SEP;

		transaction_format = SEP + "================> binlog[{}:{}] , executeTime : {} , delay : {}ms" + SEP;

	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		init();
		if (isRootApplicationContext(event.getApplicationContext())) {
			Thread thread = new Thread(new Runnable() {

	            public void run() {
	                process();
	            }
	        });

			Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {

                public void uncaughtException(Thread t, Throwable e) {
                    logger.error("parse events has an error", e);
                }
            };
	        thread.setUncaughtExceptionHandler(handler);
	        thread.start();
	        running = true;
		}

	}

	protected void process() {
		int batchSize = 512;
		String canalServerIp = TopsConfReader.getConfContent("properties/canal.properties", "canal_server_ip", ConfScope.G);
		int canalServerPort = TopsConfReader.getConfContentForInt("properties/canal.properties", "canal_server_port", ConfScope.G);
		String destination = TopsConfReader.getConfContent("properties/canal.properties", "destination", ConfScope.G);
		CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(canalServerIp, canalServerPort), destination, "", "");
		while (running) {
            try {
                connector.connect();
                connector.subscribe("vbam\\.asset_underlying_asset,vbam\\.asset_customer,vbam\\.asset_credit,vbam\\.asset_loan_info,vbam\\.asset_loan_plan");
                while (running) {
                	MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
                	handler.prepare();
                    Message message = connector.getWithoutAck(batchSize, 3L, TimeUnit.SECONDS); // 获取指定数量的数据
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId == -1 || size == 0) {
                        // try {
                        // Thread.sleep(1000);
                        // } catch (InterruptedException e) {
                        // }
                    } else {
                       // printSummary(message, batchId, size);
                        processEntry(message.getEntries());
                    }

                    connector.ack(batchId); // 提交确认
                    handler.commit();
                    // connector.rollback(batchId); // 处理失败, 回滚数据
                }
            } catch (Exception e) {
                logger.error("process error!", e);
                Cat.logMetricForCount("canal_client_error");
            } finally {
                connector.disconnect();
            }
        }
		
	}
	
	private void printSummary(Message message, long batchId, int size) {
        long memsize = 0;
        for (Entry entry : message.getEntries()) {
            memsize += entry.getHeader().getEventLength();
        }

        String startPosition = null;
        String endPosition = null;
        if (!CollectionUtils.isEmpty(message.getEntries())) {
            startPosition = buildPositionForDump(message.getEntries().get(0));
            endPosition = buildPositionForDump(message.getEntries().get(message.getEntries().size() - 1));
        }

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        logger.info(context_format, new Object[] { batchId, size, memsize, format.format(new Date()), startPosition,
                endPosition });
    }

    protected String buildPositionForDump(Entry entry) {
        long time = entry.getHeader().getExecuteTime();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return entry.getHeader().getLogfileName() + ":" + entry.getHeader().getLogfileOffset() + ":"
               + entry.getHeader().getExecuteTime() + "(" + format.format(date) + ")";
    }

    protected void processEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            long executeTime = entry.getHeader().getExecuteTime();
            long delayTime = new Date().getTime() - executeTime;
            
            if(delayTime > 10 * 1000)
            	Cat.logMetricForCount("canal_sync_lag");

            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN) {
                    TransactionBegin begin = null;
                    try {
                        begin = TransactionBegin.parseFrom(entry.getStoreValue());
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                    }
                    // 打印事务头信息，执行的线程id，事务耗时
                    logger.info(transaction_format,
                        new Object[] { entry.getHeader().getLogfileName(),
                                String.valueOf(entry.getHeader().getLogfileOffset()),
                                String.valueOf(entry.getHeader().getExecuteTime()), String.valueOf(delayTime) });
                    logger.info(" BEGIN ----> Thread id: {}", begin.getThreadId());
                } else if (entry.getEntryType() == EntryType.TRANSACTIONEND) {
                    TransactionEnd end = null;
                    try {
                        end = TransactionEnd.parseFrom(entry.getStoreValue());
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                    }
                    // 打印事务提交信息，事务id
                    logger.info("----------------\n");
                    logger.info(" END ----> transaction id: {}", end.getTransactionId());
                    logger.info(transaction_format,
                        new Object[] { entry.getHeader().getLogfileName(),
                                String.valueOf(entry.getHeader().getLogfileOffset()),
                                String.valueOf(entry.getHeader().getExecuteTime()), String.valueOf(delayTime) });
                }

                continue;
            }

            if (entry.getEntryType() == EntryType.ROWDATA) {
                RowChange rowChage = null;
                try {
                    rowChage = RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }

                EventType eventType = rowChage.getEventType();

                logger.info(row_format,
                    new Object[] { entry.getHeader().getLogfileName(),
                            String.valueOf(entry.getHeader().getLogfileOffset()), entry.getHeader().getSchemaName(),
                            entry.getHeader().getTableName(), eventType,
                            String.valueOf(entry.getHeader().getExecuteTime()), String.valueOf(delayTime) });

                if ((eventType == EventType.QUERY || rowChage.getIsDdl()) && StringUtils.isNotBlank(entry.getHeader().getSchemaName()) && StringUtils.isNotBlank(entry.getHeader().getTableName())) {
                    logger.info(" sql ----> " + rowChage.getSql() + SEP);
                    continue;
                }

                for (RowData rowData : rowChage.getRowDatasList()) {
                    if (eventType == EventType.DELETE) {
                        printColumn(rowData.getBeforeColumnsList());
                    } else if (eventType == EventType.INSERT) {
                        printColumn(rowData.getAfterColumnsList());
                    } else {
                        printColumn(rowData.getAfterColumnsList());
                    }
                    processRowDataChange(eventType, entry.getHeader().getTableName(), rowData);
                }
            }
        }
    }

    /**
     * 处理数据的变动
     * @param eventType
     * @param tableName
     * @param rowData
     */
    private void processRowDataChange(EventType eventType, String tableName, RowData rowData) {
    	try{
    		if("asset_customer".equals(tableName) && eventType == EventType.UPDATE && anyColumnValueChanged(rowData.getAfterColumnsList(), "customer_name", "economic_category", "industry", "city")){ // 客户表变更
    			handler.customerChanged(getColumnValue(rowData.getBeforeColumnsList(), "id"));
    		}else if("asset_underlying_asset".equals(tableName)){ // 基础资产表变更
    			if(eventType == EventType.INSERT && StringUtils.isNotBlank(getColumnValue(rowData.getAfterColumnsList(), "special_program_id"))){
    				handler.underlyingAssetChanged(getColumnValue(rowData.getAfterColumnsList(), "id"));
    			}
    			if(eventType == EventType.DELETE && StringUtils.isNotBlank(getColumnValue(rowData.getBeforeColumnsList(), "special_program_id"))){
    				handler.underlyingAssetChanged(getColumnValue(rowData.getBeforeColumnsList(), "id"));
    			}
    			if(eventType == EventType.UPDATE && isBeforeOrAfterColumnValueNotBlank(rowData, "special_program_id")){
    				handler.underlyingAssetChanged(getColumnValue(rowData.getBeforeColumnsList(), "id"));
    			}
    		}else if("asset_loan_plan".equals(tableName) && eventType == EventType.UPDATE){
    			handler.loanPlanChanged(getColumnValue(rowData.getBeforeColumnsList(), "id"), getColumnValue(rowData.getBeforeColumnsList(), "loan_info_id"));
    		}else if("asset_loan_info".equals(tableName) && eventType == EventType.UPDATE){
    			handler.loanInfoChanged(getColumnValue(rowData.getBeforeColumnsList(), "id"));
    		}else if("asset_credit".equals(tableName) && eventType == EventType.UPDATE){
    			handler.creditChanged(getColumnValue(rowData.getBeforeColumnsList(), "business_contract_no"));
    		}
    		
    	}catch(Exception e){
    		logger.error("处理数据变更出错", e);
    		Cat.logMetricForCount("processRowDataChange_error");
    	}
	}
    
    /**
     * 变更前或者变更后指定列值不为空则返回true
     * @param rowData
     * @param name
     * @return
     */
    private boolean isBeforeOrAfterColumnValueNotBlank(RowData rowData, String name){
    	return StringUtils.isNotBlank(getColumnValue(rowData.getBeforeColumnsList(), name)) || StringUtils.isNotBlank(getColumnValue(rowData.getAfterColumnsList(), name));
    }
    
    private String getColumnValue(List<Column> columnsList, String name){
    	return getColumnByName(columnsList, name).getValue();
    }
    
    /**
     * 是否指定的任意一列有更新
     * @param columnsList
     * @param names
     * @return
     */
	private boolean anyColumnValueChanged(List<Column> columnsList, String... names) {
		for(String name : names){
		   if(getColumnByName(columnsList, name).getUpdated())
			   return true;
		}
		return false;
	}

	/**
     * 根据name获取Column
     * @param beforeColumnsList
     * @param name
     * @return
     */
	private Column getColumnByName(List<Column> columnsList, String name) {
		for(Column column : columnsList){
			if(name.equals(column.getName()))
				return column;
		}
		return null;
	}

	protected void printColumn(List<Column> columns) {
        for (Column column : columns) {
            StringBuilder builder = new StringBuilder();
            builder.append(column.getName() + " : " + column.getValue());
            builder.append("    type=" + column.getMysqlType());
            if (column.getUpdated()) {
                builder.append("    update=" + column.getUpdated());
            }
            builder.append(SEP);
            logger.info(builder.toString());
        }
    }

	private boolean isRootApplicationContext(ApplicationContext context) {
		return context.getParent() == null;
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub

	}

}
