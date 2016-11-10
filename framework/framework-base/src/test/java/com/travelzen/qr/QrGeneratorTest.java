package com.travelzen.qr;

import com.travelzen.framework.util.TwoDimensionCode;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * Powered by IntelliJ IDEA.
 * Author: luchen.
 * Date: 14-11-13.
 */
public class QrGeneratorTest {

    @Test
    public void qrTest(){
        TwoDimensionCode handler = new TwoDimensionCode();
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        handler.encoderQRCode("测试生成Pnr", byteArrayOS, "png");
        byte[] imageBytes = byteArrayOS.toByteArray();
        System.out.println(imageBytes.toString());
    }
}
