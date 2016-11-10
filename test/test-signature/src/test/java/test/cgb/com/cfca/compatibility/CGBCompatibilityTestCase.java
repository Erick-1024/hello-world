package test.cgb.com.cfca.compatibility;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.cgb.cfca.sadk.CGBTestData;
import cfca.sm2rsa.common.Mechanism;
import cfca.sm2rsa.common.PKIException;
import cfca.x509.certificate.X509Cert;

import com.cfca.toolkit.Castle;
import com.cfca.toolkit.X509CertValidator;

public class CGBCompatibilityTestCase {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRSA() throws PKIException {
        X509CertValidator.clearTrustCertsMap();
        ArrayList subcas = new ArrayList();
        subcas.add( CGBTestData.TESTDATA_DIR +"rsa/TestcaRSA.cer");

        X509CertValidator.updateTrustCertsMap(subcas);

        final Castle castle = new Castle();
        String signedMsg = "MIIEjQYJKoZIhvcNAQcCoIIEfjCCBHoCAQExCzAJBgUrDgMCGgUAMBUGCSqGSIb3DQEHAaAIBAZhAGIAYwCgggN7MIIDdzCCAl+gAwIBAgIFEAUxV5AwDQYJKoZIhvcNAQEFBQAwIzELMAkGA1UEBhMCQ04xFDASBgNVBAoMC0NBMjAyOC0yMDMxMB4XDTE1MDMyOTAzMzE0MloXDTE2MDMyODAzMzE0MlowNTELMAkGA1UEBhMCQ04xFDASBgNVBAoMC0NBMjAyOC0yMDMxMRAwDgYDVQQDDAd0ZXN0MzI5MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFm+6NCVS/g+D7y6XrAizq+G2rQl84nkiy/SKrnDZzhnuphiUuijW/6mbVuKaDUkFxbaba3z97rl66Q2fTYTEbC2ImUClWtnEiCM4IW/rXDVkp6sKD0utBP4mXvECqVi5XnyVrTLNWpqlGloUyyj0MtKcblgIYGvRUbHNWj+JXLQIDAQABo4IBIjCCAR4wHwYDVR0jBBgwFoAUQh91vsC1py4NpqRVNNbJMtnVCkYwgc4GA1UdHwSBxjCBwzCBjqCBi6CBiIaBhWxkYXA6Ly8xOTIuMTY4LjkzLjExNDozODkvY249Y3JsMzUyOSxvdT1SU0EsT1U9Q1JMLE89Q0EyMDI4LTIwMzEsQz1DTj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0P2Jhc2U/b2JqZWN0Y2xhc3M9Y1JMRGlzdHJpYnV0aW9uUG9pbnQwMKAuoCyGKmh0dHA6Ly8xOTIuMTY4LjEyMC4xMjcvY3JsL1JTQS9jcmwzNTI5LmNybDALBgNVHQ8EBAMCA/gwHQYDVR0OBBYEFFwnP005w7UYf172RmulL14U05htMA0GCSqGSIb3DQEBBQUAA4IBAQB38XeCXUwoWvUcI68tkxNIWTmw9qATzgQobnnwxaXFPunHT9aiEIdvHPZkL1jzsoBmFEK3sWOKRtqAd7qq3GHqxIP2zfg01O5+DPDWTY+L2Tfsg5bekfKNjd5I9D+ZrxUDkAyxPVugyluZYA0UHDM1bCKFW/5TyxFQqGUM7X3GsTqN7fBNf15cH8myl/3Inr/pc0zXryvf19fEv1+1KYYkU5xOVobqJj1LBe+d7ax8gmvU4lkRWFuy3wnKzetXbiNB8QQ6dApiOyWHRNDVLPL2n4Aom++bFCh7ceZVAb0hqWiGIVBp8y7AYrq7D23V72PSl7tjl72FBLRwUt+8SoTqMYHRMIHOAgEBMCwwIzELMAkGA1UEBhMCQ04xFDASBgNVBAoMC0NBMjAyOC0yMDMxAgUQBTFXkDAJBgUrDgMCGgUAMA0GCSqGSIb3DQEBAQUABIGAwFgWZ5k3NtgLRfqleNJIiPBvAMFG5FIghvBE72nKu+S8M7B1opDiXeIeD+u6ts7Wpd5bFMwcY9Ortc3WNYYA2X79p5ZVP4k1SEWBCnGlb4mTCFDtbeOpXfy+IF0ynuOEuqT2liDJKU3ib2qW2IPekq8DtgL7TSNRgEhECIFeifY=";

        byte[] certificateByte = castle.getCertificate(signedMsg);
        X509Cert cert = new X509Cert(certificateByte);

        String certRefNo = String.valueOf(cert.getSerialNumber());

        System.err.println(certRefNo);
        System.err.println(cert.getDN());
        System.err.println(cert.getSignatureAlgName());

        int iResult = X509CertValidator.verifyCertificate(certificateByte);
        Assert.assertTrue("testRSA", iResult == 1);

        cert = new X509Cert(certificateByte);
        String certDN = cert.getDN();
        certRefNo = String.valueOf(cert.getSerialNumber());

        byte[] data = cfca.util.Base64.encode(certificateByte);

        byte[] bytes = cfca.util.Base64.decode(data);

        Assert.assertTrue("testRSA", Arrays.equals(certificateByte, bytes));

        final String pfxCertPath =  CGBTestData.TESTDATA_DIR +"rsa/test1024.der";
        final String pfxPrivatePath =  CGBTestData.TESTDATA_DIR +"rsa/test1024.pfx";
        final String pfxPrivatePassword = "123123";

        final String sm2CertPath =  CGBTestData.TESTDATA_DIR +"sm2/cmbc.cer";
        final String sm2PrivatePath =  CGBTestData.TESTDATA_DIR +"sm2/cmbc.sm2";
        final String sm2PrivatePassword = "123123";

        castle.initCertAppContext(pfxPrivatePath, pfxPrivatePassword, sm2PrivatePath, sm2PrivatePassword);

        String base64SignedMessage = castle.signDataDetached(Mechanism.SHA256_RSA,signedMsg);

        boolean result = castle.verifyDetachedSignedData(base64SignedMessage, signedMsg);
        Assert.assertTrue("testRSA", result);

        String signedText = "MIIEgwYJKoZIhvcNAQcCoIIEdDCCBHACAQExCzAJBgUrDgMCGgUAMAsGCSqGSIb3DQEHAaCCA3swggN3MIICX6ADAgECAgUQBTFXkDANBgkqhkiG9w0BAQUFADAjMQswCQYDVQQGEwJDTjEUMBIGA1UECgwLQ0EyMDI4LTIwMzEwHhcNMTUwMzI5MDMzMTQyWhcNMTYwMzI4MDMzMTQyWjA1MQswCQYDVQQGEwJDTjEUMBIGA1UECgwLQ0EyMDI4LTIwMzExEDAOBgNVBAMMB3Rlc3QzMjkwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAMWb7o0JVL+D4PvLpesCLOr4batCXzieSLL9IqucNnOGe6mGJS6KNb/qZtW4poNSQXFtptrfP3uuXrpDZ9NhMRsLYiZQKVa2cSIIzghb+tcNWSnqwoPS60E/iZe8QKpWLlefJWtMs1amqUaWhTLKPQy0pxuWAhga9FRsc1aP4lctAgMBAAGjggEiMIIBHjAfBgNVHSMEGDAWgBRCH3W+wLWnLg2mpFU01sky2dUKRjCBzgYDVR0fBIHGMIHDMIGOoIGLoIGIhoGFbGRhcDovLzE5Mi4xNjguOTMuMTE0OjM4OS9jbj1jcmwzNTI5LG91PVJTQSxPVT1DUkwsTz1DQTIwMjgtMjAzMSxDPUNOP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Q/YmFzZT9vYmplY3RjbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludDAwoC6gLIYqaHR0cDovLzE5Mi4xNjguMTIwLjEyNy9jcmwvUlNBL2NybDM1MjkuY3JsMAsGA1UdDwQEAwID+DAdBgNVHQ4EFgQUXCc/TTnDtRh/XvZGa6UvXhTTmG0wDQYJKoZIhvcNAQEFBQADggEBAHfxd4JdTCha9Rwjry2TE0hZObD2oBPOBChuefDFpcU+6cdP1qIQh28c9mQvWPOygGYUQrexY4pG2oB3uqrcYerEg/bN+DTU7n4M8NZNj4vZN+yDlt6R8o2N3kj0P5mvFQOQDLE9W6DKW5lgDRQcMzVsIoVb/lPLEVCoZQztfcaxOo3t8E1/XlwfybKX/ciev+lzTNevK9/X18S/X7UphiRTnE5WhuomPUsF753trHyCa9TiWRFYW7LfCcrN61duI0HxBDp0CmI7JYdE0NUs8vafgCib75sUKHtx5lUBvSGpaIYhUGnzLsBiursPbdXvY9KXu2OXvYUEtHBS37xKhOoxgdEwgc4CAQEwLDAjMQswCQYDVQQGEwJDTjEUMBIGA1UECgwLQ0EyMDI4LTIwMzECBRAFMVeQMAkGBSsOAwIaBQAwDQYJKoZIhvcNAQEBBQAEgYCjlaGiyrhMipZmZ4cTExaZqmZaNYYNrUbSeZktayduodt7xV7GxagWmwxJDRlc3eMxO6CtSDirWKUW2TaW7h/aedSiHS4WkdRvuz7Amaih/oeSbSJtq5FYbvD3x996HBNSjHsYC+n8MhJENJoM70MVjSrIH98QvFdtrUb2lWMDhQ==";

        final String srcMessage = "测试+ABC";
        boolean verifyResult = castle.verifyDetachedSignedDataAU(signedText, srcMessage);
        Assert.assertTrue("testRSA", verifyResult);
    }

    @Test
    public void testSM2() throws PKIException {
        X509CertValidator.clearTrustCertsMap();
        ArrayList subcas = new ArrayList();
        subcas.add( CGBTestData.TESTDATA_DIR +"sm2/subca.cer");

        X509CertValidator.updateTrustCertsMap(subcas);

        final Castle castle = new Castle();
        String signedMsg = "MIIDVwYKKoEcz1UGAQQCAqCCA0cwggNDAgEBMQ4wDAYIKoEcz1UBgxEFADAWBgoqgRzPVQYBBAIBoAgEBmEAYgBjAKCCAnwwggJ4MIICHaADAgECAgUQASkZUzAMBggqgRzPVQGDdQUAMB8xCzAJBgNVBAYTAkNOMRAwDgYDVQQKDAdCT0MgU00yMB4XDTE1MDQwMTA4MjcwNloXDTE3MDQwMTA4MjcwNlowejELMAkGA1UEBhMCQ04xFTATBgNVBAoMDENGQ0EgVEVTVCBDQTERMA8GA1UECwwITG9jYWwgUkExFTATBgNVBAsMDEluZGl2aWR1YWwtMTEqMCgGA1UEAwwhMDUxQOawkeeUn+a1i+ivlUAxMTIzMjMxMTIxMzEzMkAxMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEO69KlirHNzbJETD2sf544Wg01qIrLpUGpgaYE3ehyPPaCYaoCxlOC+n8bUAM64yT2B2lyCUnUZOCpGBQO8vmEKOB6DCB5TAfBgNVHSMEGDAWgBRr/hjaj0I6prhtsy6Igzo0osEw4TBIBgNVHSAEQTA/MD0GCGCBHIbvKgEBMDEwLwYIKwYBBQUHAgEWI2h0dHA6Ly93d3cuY2ZjYS5jb20uY24vdXMvdXMtMTQuaHRtMDcGA1UdHwQwMC4wLKAqoCiGJmh0dHA6Ly91Y3JsLmNmY2EuY29tLmNuL1NNMi9jcmw4NDYuY3JsMAsGA1UdDwQEAwID6DAdBgNVHQ4EFgQUCc9SdiNsOG8LRpPQBJsKdO4RVO8wEwYDVR0lBAwwCgYIKwYBBQUHAwIwDAYIKoEcz1UBg3UFAANHADBEAiAfGc8JFIXLtEHEO3QCoXQJdLU1qCRvHhboW/Eowg3M8wIgOgGbhuw6cr3kwA9Xoit0GN64bSB353rQq1h7+HmbDgAxgZUwgZICAQEwKDAfMQswCQYDVQQGEwJDTjEQMA4GA1UECgwHQk9DIFNNMgIFEAEpGVMwDAYIKoEcz1UBgxEFADANBgkqgRzPVQGCLQEFAARGMEQCIA55tQCLl/fZLkp6pF1sjnYzC8BaWRFpEIcTsmuXKkbNAiAJbW7X5oc/AnTDj0agLX89C+AF14XI1ZoYAuFxNWF4/w==";

        byte[] certificateByte = castle.getCertificate(signedMsg);
        X509Cert cert = new X509Cert(certificateByte);

        String certRefNo = String.valueOf(cert.getSerialNumber());

        System.err.println(certRefNo);
        System.err.println(cert.getDN());
        System.err.println(cert.getSignatureAlgName());

        int iResult = X509CertValidator.verifyCertificate(certificateByte);
        Assert.assertTrue("testSM2", iResult == 1);

        cert = new X509Cert(certificateByte);
        String certDN = cert.getDN();
        certRefNo = String.valueOf(cert.getSerialNumber());

        byte[] data = cfca.util.Base64.encode(certificateByte);

        byte[] bytes = cfca.util.Base64.decode(data);

        Assert.assertTrue("testSM2", Arrays.equals(certificateByte, bytes));

        final String pfxCertPath =  CGBTestData.TESTDATA_DIR +"rsa/test1024.der";
        final String pfxPrivatePath =  CGBTestData.TESTDATA_DIR +"rsa/test1024.pfx";
        final String pfxPrivatePassword = "123123";

        final String sm2CertPath =  CGBTestData.TESTDATA_DIR +"sm2/cmbc.cer";
        final String sm2PrivatePath =  CGBTestData.TESTDATA_DIR +"sm2/cmbc.sm2";
        final String sm2PrivatePassword = "123123";

        castle.initCertAppContext(pfxPrivatePath, pfxPrivatePassword, sm2PrivatePath, sm2PrivatePassword);

        String base64SignedMessage = castle.signDataDetached(Mechanism.SM3_SM2, signedMsg);

        boolean result = castle.verifyDetachedSignedData(base64SignedMessage, signedMsg);
        Assert.assertTrue("testSM2", result);

        String signedText = "MIIDTgYKKoEcz1UGAQQCAqCCAz4wggM6AgEBMQ4wDAYIKoEcz1UBgxEFADAMBgoqgRzPVQYBBAIBoIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOADGBljCBkwIBATAoMB8xCzAJBgNVBAYTAkNOMRAwDgYDVQQKDAdCT0MgU00yAgUQASkZUzAMBggqgRzPVQGDEQUAMA0GCSqBHM9VAYItAQUABEcwRQIhAIHe2s+zV2DvKdI4goPLT1sdM3DOOYl9ebFh1hFMI+BiAiBTkMgDRgwUeMgv3R0RYNYPCUei8PHJwpzcyUYgLSJ0mQ==";

        final String srcMessage = "测试+ABC";
        boolean verifyResult = castle.verifyDetachedSignedDataAU(signedText, srcMessage);
        Assert.assertTrue("testSM2", verifyResult);
    }

}
