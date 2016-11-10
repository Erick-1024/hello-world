/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package org.mybatis.generator.plugins;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.TableConfiguration;

/**
 * 不带分页条件，需要带分页条件请使用子类{@link GeneratePostgresqlPessimisticLockPlugin} 或
 * {@link GenerateMySQLPessimisticLockPlugin}
 * @author ducer
 *
 */
public class GeneratePessimisticLockPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		interfaze.addMethod(generateLockByPrimaryKey(introspectedTable,importedTypes));
		interfaze.addMethod(generateLockByExample(introspectedTable,importedTypes));
		interfaze.addImportedTypes(importedTypes);
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		generateLockByPrimaryKeyXml(document.getRootElement(), introspectedTable);
		generateLockByExample(document.getRootElement(), introspectedTable);
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	private void generateLockByPrimaryKeyXml(XmlElement parentElement, IntrospectedTable introspectedTable) {
		if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
			List<IntrospectedColumn> primaryKeys = introspectedTable.getPrimaryKeyColumns();

			XmlElement lockByPrimaryKey = new XmlElement("select");
			lockByPrimaryKey.addAttribute(new Attribute("id", "lockByPrimaryKey"));
			lockByPrimaryKey.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));

			String parameterType = primaryKeys.get(0).getFullyQualifiedJavaType().getFullyQualifiedName();
			if (introspectedTable.getPrimaryKeyColumns().size() > 1) {
				parameterType = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType()).getFullyQualifiedName();
			}
			lockByPrimaryKey.addAttribute(new Attribute("parameterType", parameterType));

			lockByPrimaryKey.addElement(new TextElement("select "));

			XmlElement columnList = new XmlElement("include");
			columnList.addAttribute(new Attribute("refid", introspectedTable.getBaseColumnListId()));
			lockByPrimaryKey.addElement(columnList);

			TableConfiguration conf = introspectedTable.getTableConfiguration();
			String schema = conf.getSchema() == null ? "" : conf.getSchema() + ".";
			lockByPrimaryKey.addElement(new TextElement("from " + schema + conf.getTableName()));

			StringBuilder bu = new StringBuilder("");
			boolean and = false;
			for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
				if (and) {
					bu.append("  and ");
				} else {
					bu.append("where ");
					and = true;
				}
				bu.append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn));
				bu.append(" = ").append("#{").append(introspectedColumn.getJavaProperty()).append(",jdbcType=").append(introspectedColumn.getJdbcTypeName()).append("} ");
			}
			bu.append(" for update ");
			TextElement text = new TextElement(bu.toString());
			lockByPrimaryKey.addElement(text);

			if (context.getPlugins().sqlMapSelectByPrimaryKeyElementGenerated(lockByPrimaryKey, introspectedTable)) {
				parentElement.addElement(lockByPrimaryKey);
			}
		}
	}
	
	private void generateLockByExample(XmlElement parentElement, IntrospectedTable introspectedTable) {
		if (introspectedTable.getRules().generateSQLExampleWhereClause()) {
			XmlElement lockByPrimaryKey = new XmlElement("select");
			lockByPrimaryKey.addAttribute(new Attribute("id", "lockByExample"));
			lockByPrimaryKey.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
			lockByPrimaryKey.addAttribute(new Attribute("parameterType", introspectedTable.getExampleType()));

			lockByPrimaryKey.addElement(new TextElement("select "));
			XmlElement ifElement = new XmlElement("if");
			ifElement.addAttribute(new Attribute("test", "distinct"));
			ifElement.addElement(new TextElement("distinct"));
			lockByPrimaryKey.addElement(ifElement);

			XmlElement includeElement = new XmlElement("include");
			includeElement.addAttribute(new Attribute("refid", introspectedTable.getBaseColumnListId()));
			lockByPrimaryKey.addElement(includeElement);

			TableConfiguration conf = introspectedTable.getTableConfiguration();
			String schema = conf.getSchema() == null ? "" : conf.getSchema() + ".";
			lockByPrimaryKey.addElement(new TextElement("from " + schema + conf.getTableName()));

			XmlElement parameterElement = new XmlElement("if");
			parameterElement.addAttribute(new Attribute("test", "_parameter != null"));
			XmlElement paramIncludeElement = new XmlElement("include");
			paramIncludeElement.addAttribute(new Attribute("refid", introspectedTable.getExampleWhereClauseId()));
			parameterElement.addElement(paramIncludeElement);
			lockByPrimaryKey.addElement(parameterElement);

			XmlElement orderByElement = new XmlElement("if");
			orderByElement.addAttribute(new Attribute("test", "orderByClause != null"));
			orderByElement.addElement(new TextElement(" order by #{orderByClause} "));
			lockByPrimaryKey.addElement(orderByElement);
			generateLimitElement(lockByPrimaryKey);

			lockByPrimaryKey.addElement(new TextElement(" for update "));

			if (context.getPlugins().sqlMapExampleWhereClauseElementGenerated(lockByPrimaryKey, introspectedTable)) {
				parentElement.addElement(lockByPrimaryKey);
			}
		}
	}
	
	private Method generateLockByPrimaryKey(IntrospectedTable introspectedTable,Set<FullyQualifiedJavaType> importedTypes) {
		boolean isMultiPk = introspectedTable.getPrimaryKeyColumns().size() > 1;
		FullyQualifiedJavaType recordType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		FullyQualifiedJavaType pkType = isMultiPk ? new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType()) : introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType();
		importedTypes.add(recordType);
		importedTypes.add(pkType);
		Method method = new Method("lockByPrimaryKey");
		method.setVisibility(JavaVisibility.DEFAULT);
		method.setReturnType(recordType);
		method.addParameter(new Parameter(pkType, isMultiPk ? "key" : introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
		return method;
	}
	

	private Method generateLockByExample(IntrospectedTable introspectedTable,Set<FullyQualifiedJavaType> importedTypes) {
		FullyQualifiedJavaType recordType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		FullyQualifiedJavaType listType = FullyQualifiedJavaType.getNewListInstance();
		FullyQualifiedJavaType exType = new FullyQualifiedJavaType(introspectedTable.getExampleType());
		importedTypes.add(listType);
		importedTypes.add(recordType);
		importedTypes.add(exType);
		Method method = new Method("lockByExample");
		method.setVisibility(JavaVisibility.DEFAULT);
		listType.addTypeArgument(recordType);
		method.setReturnType(listType);
		method.addParameter(new Parameter(exType, "example"));
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
		return method;
	}

	/**
	 * 分页节点，生成然后add 进 parentElement
	 * 
	 * <pre>
	 * &lt;if test="limitStart >= 0" &gt;
	 *    limit ${limitStart} , ${limitEnd}
	 * &lt;/if&gt;
	 * </pre>
	 * 
	 * @return 返回null将不会生成分页节点
	 */
	protected void generateLimitElement(XmlElement parentElement){};
}
