package test.cfca.sadk.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;
import cfca.sadk.system.FileHelper;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 *
 */
public class Base64TestCase extends TestCase {

    static final String encoded = "MIIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFoBDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";
    static final byte[] testData = HexBin
            .decode("308202DC0201013047060A2A811CCF55060104020106072A811CCF5501680430C72A8DB3BA070B47529DCB6C0159CCD7F9962D79FC11CD3F102C18CD7F0ECA62693D2FE86E1E88606FDD39C5A1BFF39B3082028C060A2A811CCF5506010402010482027C308202783082021DA00302010202051001291953300C06082A811CCF550183750500301F310B300906035504061302434E3110300E060355040A0C07424F4320534D32301E170D3135303430313038323730365A170D3137303430313038323730365A307A310B300906035504061302434E31153013060355040A0C0C4346434120544553542043413111300F060355040B0C084C6F63616C20524131153013060355040B0C0C496E646976696475616C2D31312A302806035504030C2130353140E6B091E7949FE6B58BE8AF9540313132333233313132313331333240313059301306072A8648CE3D020106082A811CCF5501822D034200043BAF4A962AC73736C91130F6B1FE78E16834D6A22B2E9506A606981377A1C8F3DA0986A80B194E0BE9FC6D400CEB8C93D81DA5C82527519382A460503BCBE610A381E83081E5301F0603551D230418301680146BFE18DA8F423AA6B86DB32E88833A34A2C130E130480603551D200441303F303D060860811C86EF2A01013031302F06082B060105050702011623687474703A2F2F7777772E636663612E636F6D2E636E2F75732F75732D31342E68746D30370603551D1F0430302E302CA02AA0288626687474703A2F2F7563726C2E636663612E636F6D2E636E2F534D322F63726C3834362E63726C300B0603551D0F0404030203E8301D0603551D0E0416041409CF5276236C386F0B4693D0049B0A74EE1154EF30130603551D25040C300A06082B06010505070302300C06082A811CCF550183750500034700304402201F19CF091485CBB441C43B7402A1740974B535A8246F1E16E85BF128C20DCCF302203A019B86EC3A72BDE4C00F57A22B7418DEB86D2077E77AD0AB587BF8799B0E00");

    static final String testText1 = " M IIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFoBDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";
    static final String testText2 = "\nMIIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFo\nBDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";
    static final String testText3 = "\rMIIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFoB\rDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";
    static final String testText4 = " \n\rM IIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQF\n\roBDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testEncodeByteArray() {
        byte[] result = cfca.sadk.util.Base64.encode(testData);
        assertTrue("testEncodeByteArray", encoded.equals(new String(result)));
    }

    public void testEncodeByteArrayOutputStream() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        cfca.sadk.util.Base64.encode(testData, out);

        byte[] result = out.toByteArray();

        FileHelper.write("/tmp/test.bin", testData);
        System.err.println(HexBin.encode(testData));

        assertTrue("testEncodeByteArrayOutputStream", encoded.equals(new String(result)));

    }

    public void testEncodeByteArrayIntIntOutputStream() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        cfca.sadk.util.Base64.encode(testData, 0, testData.length, out);

        byte[] result = out.toByteArray();

        assertTrue("testEncodeByteArrayOutputStream", encoded.equals(new String(result)));
    }

    public void testDecodeByteArray() {
        byte[] coding = cfca.sadk.util.Base64.decode(encoded);
        assertTrue("testDecodeByteArray", Arrays.equals(coding, testData));

        coding = cfca.sadk.util.Base64.decode(testText1);
        assertTrue("testDecodeByteArray", Arrays.equals(coding, testData));

        coding = cfca.sadk.util.Base64.decode(testText2);
        assertTrue("testDecodeByteArray", Arrays.equals(coding, testData));

        coding = cfca.sadk.util.Base64.decode(testText3);
        assertTrue("testDecodeByteArray", Arrays.equals(coding, testData));

        coding = cfca.sadk.util.Base64.decode(testText4);
        assertTrue("testDecodeByteArray", Arrays.equals(coding, testData));
    }

    public void testDecodeString() {
        byte[] coding = cfca.sadk.util.Base64.decode(encoded);
        assertTrue("testDecodeString", Arrays.equals(coding, testData));
    }

    public void testDecodeStringOutputStream() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        cfca.sadk.util.Base64.decode(encoded, out);
        byte[] coding = out.toByteArray();
        assertTrue("testDecodeString", Arrays.equals(coding, testData));
    }

    public void testToBase64StringByteArray() {
        String coding = cfca.sadk.util.Base64.toBase64String(testData);

        assertTrue("testToBase64StringByteArray", coding.equals(encoded));
    }

    public void testToBase64StringByteArrayIntInt() {
        String coding = cfca.sadk.util.Base64.toBase64String(testData, 0, testData.length);

        assertTrue("testToBase64StringByteArrayIntInt", coding.equals(encoded));
    }

    public void testEncodeByteArrayIntInt() {
        byte[] result = cfca.sadk.util.Base64.encode(testData, 0, testData.length);
        assertTrue("testEncodeByteArrayIntInt", encoded.equals(new String(result)));
    }
}
