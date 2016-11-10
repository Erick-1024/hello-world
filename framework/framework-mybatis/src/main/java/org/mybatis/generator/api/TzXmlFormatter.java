package org.mybatis.generator.api;

import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.config.TzContext;

public interface TzXmlFormatter {
    void setContext(TzContext context);
    String getFormattedContent(Document document);
}
