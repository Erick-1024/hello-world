/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.utils;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author ducer
 *
 */
public class XStreamUtil {

  // new XStream(new DomDriver("utf-8")); //指定编码解析器,直接用jaxp dom来解释
  // new Xstream(); fromXml报错：java.lang.NoClassDefFoundError: org/xmlpull/v1/XmlPullParserFactory
  private static final XStream xs = new XStream(new DomDriver());

  static {
    xs.autodetectAnnotations(true);
  }

  public static String toXml(Serializable xmlRequest) {
    return xs.toXML(xmlRequest);
  }

  @SuppressWarnings("unchecked")
  public static <T> T fromXml(String xml, Class<T> cls) {
    XStream xst = new XStream(new DomDriver());
    xst.autodetectAnnotations(true);
    xst.alias("stream", cls);
    T obj = (T) xst.fromXML(xml);
    return obj;
  }
}
