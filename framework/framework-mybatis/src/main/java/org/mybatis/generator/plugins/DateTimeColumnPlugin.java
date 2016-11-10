package org.mybatis.generator.plugins;

import org.joda.time.DateTime;
import org.joda.time.mybatis.handlers.DateTimeTypeHandler;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

import java.util.Date;
import java.util.List;

/**
 * 将日期类型column设置其JavaType为 DateTime, typeHandler设置为DateTimeTypeHandler
 * User: dingguangxian
 * Date: 14-5-26
 * Time: 下午7:04
 */
public class DateTimeColumnPlugin extends PluginAdapter{
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
		for(IntrospectedColumn column : columns){

			if(Date.class.getName().equals(column.getFullyQualifiedJavaType().getFullyQualifiedName())){
//				System.out.println("change type handler for column : " + column.getActualColumnName());
				column.setTypeHandler(DateTimeTypeHandler.class.getName());
				column.setFullyQualifiedJavaType(new FullyQualifiedJavaType(DateTime.class.getName()));
			}
		}
		super.initialized(introspectedTable);
	}
}
