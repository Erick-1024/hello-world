package test.cfca.sadk.interoperability;

import java.security.interfaces.RSAPrivateKey;

import junit.framework.TestCase;
import test.cfca.sadk.testdata.RSATestData;
import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;

public class CMBCInteroperabilityTestCase extends TestCase {
    Session session = null;

    Signature engine = new Signature();

    protected void setUp() throws Exception {

        super.setUp();
        session = TestReady.openSession();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        session = null;
    }

    public void testrsaP7VerifyMessageDetach() throws Exception {

        byte[] sourceData = "test".getBytes("UTF8");

        byte[] signData = "MIID8AYJKoZIhvcNAQcCoIID4TCCA90CAQExCzAJBgUrDgMCGgUAMAsGCSqGSIb3DQEHAaCCAuQwggLgMIIByKADAgECAgUQAAAAEDANBgkqhkiG9w0BAQUFADAnMQswCQYDVQQGEwJDTjEYMBYGA1UECgwPS0lOTUlFIEdJR0kgUlNBMB4XDTEzMDgyMDA2MjY1OVoXDTE4MDgxOTA2MjY1OVowNDELMAkGA1UEBhMCQ04xFDASBgNVBAoMC0tJTk1JRSBHSUdJMQ8wDQYDVQQDDAZjbGllbnQwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAKekI00nCFUxjsWa6ZAvTte77RChJYzIId1gne/z3YzoXkS/+yZoX+il6ffP3CfGevbmo76Rvrml/buKTLix1fH6jdmbHf+NuWNQKBtuPJwWZMOc/nTU7o5UQwoSKdVrPHFDUZIxPD8j+6IkDoIwhNg9FJApzR3nG82igd+QcU9BAgMBAAGjgYkwgYYwHwYDVR0jBBgwFoAUCQ4rUrRq+cnd+6M2B4M4wTjz7p0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovLzE5Mi4xNjguMTEzLjY0L2NybC9SU0EvY3JsMS5jcmwwCwYDVR0PBAQDAgbAMB0GA1UdDgQWBBTxhd27zTvabHm/lOuwlvrf17khizANBgkqhkiG9w0BAQUFAAOCAQEAVec0Im1LkhE01MnXyP6lQhVQsm7QpupmLTqYx8yo87I96L0+9Pvo381FDraZhL9c1HFRRwFYN1rhOZmXCpPYc7pYNQWOGfwVDsTJ1EsIGWekLgU/SDVzbhY56f9RVteajEjrx4U2mt2jd83jx07JfqsjEyMUrhMB93uwEJWHXXiJnQSueR+5Jjy/Pl7boJdJup4MwAqn3temPZjQL7Cn2bUH08RgYYqjg9b4FJ/9hsTrLQRNwZ6oKoVEOKGkmBfExTPBndEgaMYganyoAGY42yYsbcQkQW1bTctbfAlrZuE7oG6G3z/7m636+Rey2pSBBZhMIh4PImPg6qLo77errDGB1TCB0gIBATAwMCcxCzAJBgNVBAYTAkNOMRgwFgYDVQQKDA9LSU5NSUUgR0lHSSBSU0ECBRAAAAAQMAkGBSsOAwIaBQAwDQYJKoZIhvcNAQEBBQAEgYBvo2QY/DLHFF9j+Xx8T0wIyzkTfF+YrHw3PY/kuHOLWNLZIyUqVqi+wonxpwEel6dOb9Ta4mJ6C/sAqMmlxPGhS/NLDEvNXRGb0kP8xgd4kwLkNetFW8fYRrdKCdIjfv3jrIKxhUoV3wrHu/mUPV8oq7Euizr+WT97frpTUtMpzw=="
                .getBytes("UTF8");

        boolean verifyResult = engine.p7VerifyMessageDetach(sourceData, signData, session);

        assertTrue("testrsaP7VerifyMessageDetach", verifyResult);
    }

    public void testSM2P7VerifyFileDetach() throws Exception {
        String sourceFilePath = "TestData/interoperability/sm2-plainText.bin";
        String signedFilePath = "TestData/interoperability/sm2-plainText.detach-signed";

        byte[] signData = FileHelper.read(signedFilePath);

        boolean verifyResult = engine.p7VerifyFileDetach(sourceFilePath, signData, session);
        assertTrue("testrsaP7VerifyFileAttach", verifyResult);

    }

    public void testrsaP7VerifyFileDetach() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";
        String signFilePath = "TestData/rsa/test-sha256-msg-detach.p7b";

        byte[] signData = FileHelper.read(signFilePath);

        boolean verifyResult = engine.p7VerifyFileDetach(sourceFilePath, signData, session);
        assertTrue("testrsaP7VerifyFileAttach", verifyResult);
    }

    public void testrsaP7SignFileDetach() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        String signAlg = Mechanism.SHA256_RSA;
        RSAPrivateKey priKey = RSATestData.userPriKey;
        X509Cert cert = RSATestData.UserCert;
        byte[] signData = engine.p7SignFileDetach(signAlg, sourceFilePath, priKey, cert, session);

        boolean verifyResult = engine.p7VerifyFileDetach(sourceFilePath, signData, session);

        assertTrue("testrsaP7SignFileDetach", verifyResult);

        System.err.println(new String(signData));

        //        FileHelper.write("xxxxx", signData);
    }

}
