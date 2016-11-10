/**
 *  Copyright © 2016 Cana. All rights reserved.
 */
package com.travelzen.tops.mediaserver.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;

/**
 * 需要在globle/properties/file-template.properties配置固定的模板ID
 * 把配置文件模板放在test/resources/templates目录下
 * 
 * @author ducer
 *
 */
public class MediaProEnvTemplateUpload {

	private MediaClient mClient = new MediaClient();
	private Properties properties;

	{
		properties = TopsConfReader.getConfProperties("properties/file-template.properties", ConfScope.R);
		//生产环境上传的时候把地址换成生产环境的 Ex: media.cana.com

		mClient.setHost("op.canacorp.net", 0, "/tops-mediaserver/uploadImageService");
//		mClient.setHost("op3.canacorp.net", 0, "/tops-mediaserver/uploadImageService");
//		mClient.setHost("ams.canacorp.net", 0, "/tops-mediaserver/uploadImageService");
	}

	@Test
	public void uploadTemplate() throws Exception {
		upload("凯拿平台授权委托书.doc", properties.getProperty("authorization-letter-template-id"));
		upload("账户开户买方名称.xls", properties.getProperty("buyer-name-template-id"));
		upload("融资客户开户.doc", properties.getProperty("finace-apply-files-id"));
		upload("放款信息模板.xls", properties.getProperty("loadinfo-id"));
		upload("还款计划及费用模板.xls", properties.getProperty("repayment-plan-id"));
	}

	@Test
	public void uploadTemplateForAsset() throws Exception {
		upload("基础资产导入模版.xlsx", properties.getProperty("asset-underlying-id"));
//		upload("应收账款模板.xlsx", properties.getProperty("asset-invoice-id"));
//		upload("放款信息导入模板.xlsx", properties.getProperty("asset-loaninfo-id"));
//		upload("还款计划导入模板.xlsx", properties.getProperty("asset-repayment-plan-id"));
	}
	private void upload(String fileName, String templateId) throws IOException {
		if (StringUtils.isBlank(templateId)) {
			System.out.println(fileName + "模板ID不存在于配置文件中");
			return;
		}
		String filePath;
		try {
			filePath = this.getClass().getClassLoader().getResource("templates/asset/" + fileName).toURI().getPath();
		} catch (URISyntaxException e) {
			System.out.println(fileName + "模板不存在于resources/templates下");
			return;
		}
		boolean v = mClient.uploadPhoto(MediaClientUtilTest.getBytes(filePath), templateId, "image", fileName);
		if (v == false) System.out.println(fileName + "上传失败");
		else System.out.println(fileName + "上传成功");
		return;
	}
}
