package org.mybatis.generator.plugins;

import java.util.List;

import org.joda.time.DateTime;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 生成Model数据库字段注释的Plugin.
 * 
 * @author cheng.zeng@travelzen.com
 */
public class GenerateDBCommentPlugin extends PluginAdapter {

	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addFileCommentLine("/**");
		topLevelClass.addFileCommentLine(" * Copyright (c) "+getYear()+", Cana and/or its affiliates. All rights reserved.");
		topLevelClass.addFileCommentLine(" */");
		return true;
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		generateFieldExplain(field, introspectedColumn);
		return true;
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		generateMethodExplain(method, introspectedColumn);
		return true;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		generateMethodExplain(method, introspectedColumn);
		return true;
	}

	private void generateFieldExplain(Field field, IntrospectedColumn introspectedColumn) {
		field.addJavaDocLine("/**");
		field.addJavaDocLine(" *" + introspectedColumn.getRemarks());
		field.addJavaDocLine(" */");
	}

	private void generateMethodExplain(Method method, IntrospectedColumn introspectedColumn) {
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" *" + introspectedColumn.getRemarks());
		method.addJavaDocLine(" */");
	}
	
	private String getYear(){
		return new DateTime().toString("yyyy");
	}

}
