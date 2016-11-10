package test.cfca.sadk.asn1.parser;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.asn1.parser.ASN1Parser;
import cfca.sadk.org.bouncycastle.asn1.DEROctetString;
import cfca.sadk.system.FileHelper;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class ASN1ParserTestCase {

    static final String encoded = "MIIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFoBDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";
    static final byte[] testData = Base64.decode(encoded);

    static final String testText1 = " M IIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFoBDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";
    static final String testText2 = "\nMIIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFo\nBDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";
    static final String testText3 = "\rMIIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFoB\rDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";
    static final String testText4 = " \n\rM IIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQF\n\roBDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsBase64Compatability() throws PKIException, IOException {
        Assert.assertFalse("testIsBase64Compatability", ASN1Parser.isBase64Compatability(testData));
        Assert.assertTrue("testIsBase64Compatability", ASN1Parser.isBase64Compatability(testText1.getBytes()));
        Assert.assertTrue("testIsBase64Compatability", ASN1Parser.isBase64Compatability(testText2.getBytes()));
        Assert.assertTrue("testIsBase64Compatability", ASN1Parser.isBase64Compatability(testText3.getBytes()));
        Assert.assertTrue("testIsBase64Compatability", ASN1Parser.isBase64Compatability(testText4.getBytes()));

        String text1 = "MIIC3AIBATBHBgoqg";
        Assert.assertTrue("testIsBase64Compatability", ASN1Parser.isBase64Compatability(text1.getBytes()));

        String text2 = "MIIC3AIBATBHBgoqg+/= \r\n\t";
        Assert.assertTrue("testIsBase64Compatability", ASN1Parser.isBase64Compatability(text2.getBytes()));

    }

    @Test
    public void testGetDERSequenceFrom() throws PKIException, IOException {
        byte[] encoding = FileHelper.read("TestData/sm2/test.base64");

        Assert.assertTrue("testGetDERSequenceFrom", ASN1Parser.getDERSequenceFrom(encoding) != null);

        encoding = FileHelper.read("TestData/sm2/test.cer");

        Assert.assertTrue("testGetDERSequenceFrom", ASN1Parser.getDERSequenceFrom(encoding) != null);

        encoding = FileHelper.read("TestData/sm2/SignedData.base64");

        Assert.assertTrue("testGetDERSequenceFrom", ASN1Parser.getDERSequenceFrom(encoding) != null);

        encoding = FileHelper.read("TestData/sm2/SignedData.der");

        Assert.assertTrue("testGetDERSequenceFrom", ASN1Parser.getDERSequenceFrom(encoding) != null);

    }

    // @Test
    public void testIsDERSequence() throws PKIException, IOException {

        byte[] encoding = HexBin.decode("3000");

        Assert.assertTrue("testIsDERSequence", ASN1Parser.isDERSequence(encoding));

        for (int i = 0; i < 70000; i++) {
            encoding = new DEROctetString(new byte[i]).getEncoded();
            encoding[0] = 0x30;

            Assert.assertTrue("testIsDERSequence", ASN1Parser.isDERSequence(encoding));

        }

        for (int i = 100000; i < 100000 + 300; i++) {
            encoding = new DEROctetString(new byte[i]).getEncoded();
            encoding[0] = 0x30;

            Assert.assertTrue("testIsDERSequence", ASN1Parser.isDERSequence(encoding));

        }

        for (int i = 1000000; i < 1000000 + 300; i++) {
            encoding = new DEROctetString(new byte[i]).getEncoded();
            encoding[0] = 0x30;

            Assert.assertTrue("testIsDERSequence", ASN1Parser.isDERSequence(encoding));

        }
        for (int i = 10000000; i < 10000000 + 300; i++) {
            encoding = new DEROctetString(new byte[i]).getEncoded();
            encoding[0] = 0x30;

            Assert.assertTrue("testIsDERSequence", ASN1Parser.isDERSequence(encoding));

        }
        for (int i = 5000000; i < 5000000 + 300; i++) {
            encoding = new DEROctetString(new byte[i]).getEncoded();
            encoding[0] = 0x30;

            Assert.assertTrue("testIsDERSequence", ASN1Parser.isDERSequence(encoding));

        }

    }

}
