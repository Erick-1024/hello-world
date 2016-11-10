package com.cana.vbam.front.biz.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.poi.hwpf.usermodel.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * word相关工具
 * @author tangyh
 *
 */
public class WordUtil {

	private static Logger logger = LoggerFactory.getLogger(WordUtil.class);
    /**
     * 將数据填充到ftl文件(由于生成的doc文件样式变异，暂废弃)
     * @param dataMap word中需要展示的动态数据，用map集合来保存
     * @param templatePath word模板绝对路径，例如：/home/tangyh/cana/front/vbam-front-biz/src/main/resources/template/contractTemplate.ftl
     * @throws IOException
     */
    public static String generateWordContent(Map<String, String> dataMap, String templatePath) throws IOException {
            // 创建配置实例
            Configuration configuration = new Configuration();
            // 设置编码
            configuration.setDefaultEncoding("UTF-8");
            configuration.setDirectoryForTemplateLoading(new File(templatePath).getParentFile());
            Template template = null;
            try {
                // 获取模板
                template = configuration.getTemplate(new File(templatePath).getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringWriter out =  new StringWriter();
            try {
            // 生成文件
            template.process(dataMap, out);
            // 关闭流
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(out!=null){
                    out.flush();
                    out.close();
                }
            }
        return out.getBuffer().toString();
    }
    
    /**
     * 將数据填充到doc文件
     * @param dataMap word中需要展示的动态数据，用map集合来保存
     * @param templatePath word模板绝对路径，例如：/home/tangyh/cana/front/vbam-front-biz/src/main/resources/template/contractTemplate.doc
     * @author hu 修改让替换内容包括页眉页脚
     */
    public static byte[] getFilledDocTemplateAsBytes(String templatePath, Map<String, String> paramMap) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			HWPFDocument doc = new HWPFDocument(new FileInputStream(new File(templatePath)));
			Range range = doc.getOverallRange();
			for (Map.Entry<String, String> entry : paramMap.entrySet()) {
				range.replaceText(entry.getKey(), entry.getValue());
			}
			doc.write(outputStream);
			return outputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("將数据填充到doc文件失败!{}",e);
		}
		return null;

	}
}
