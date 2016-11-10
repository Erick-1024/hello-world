package test.cfca.sadk.util;

import junit.framework.TestCase;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.system.CompatibleAlgorithm;
import cfca.sadk.util.Signature;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public final class SM2WithoutZVerifyTestCase extends TestCase {

    protected void setUp() throws Exception {

    }

    protected void tearDown() throws Exception {

    }

    public void testSM2WithoutZVerify() throws Exception {

        String signData = "MIICrQYKKoEcz1UGAQQCAqCCAp0wggKZAgEBMQ4wDAYIKoEcz1UBgxEFADAUBgoqgRzPVQYBBAIBoAYEBGFiY2SgggHOMIIByjCCAW6gAwIBAgIFEAAAAEIwDAYIKoEcz1UBg3UFADAjMQswCQYDVQQGEwJDTjEUMBIGA1UECgwLQ0ZDQSBPQ0E4ODgwHhcNMTMwNTE2MDM0NjU2WhcNMTQwMzEyMDM0NjU2WjAlMQswCQYDVQQGEwJDTjEWMBQGA1UEAwwNc20yZG91YmxlY2VydDBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABAO7+FfG6YBxo6/nWexjUIANxq/6eEMmCtdwTLiP2nMehUOdnDEvyPPDVzEnjHxSJrJK5FJfFFbkTEDM4iF+WjajgYowgYcwHwYDVR0jBBgwFoAUAJAK6++jihENFsJYJe0OJ5DtGCcwOAYDVR0fBDEwLzAtoCugKYYnaHR0cDovLzE5Mi4xNjguMTIwLjEyNy9jcmwvU00yL2NybDEuY3JsMAsGA1UdDwQEAwIGwDAdBgNVHQ4EFgQUAuC2hGlWI+NJr2Tqywhe6KBqt/swDAYIKoEcz1UBg3UFAANIADBFAiBEEsm+Uh/TybALNz2yn9Ls9grSkZX426L8qQKlx5t4dgIhALf/GDypJYilQyLjowXoLned6qPFzSNcsrlioJ81j3oYMYGbMIGYAgEBMCwwIzELMAkGA1UEBhMCQ04xFDASBgNVBAoMC0NGQ0EgT0NBODg4AgUQAAAAQjAMBggqgRzPVQGDEQUAMA0GCSqBHM9VAYItAQUABEgwRgIhAKjgxtQR0peHkc2nmcu4oY/NYlLGz+Hxm3hRJltqpox8AiEAssn14dlFPH26HwJSHPnQKBcvFGtQs478stCvlxJ0E34=";

        JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
        Session session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);

        Signature engine = new Signature();

        CompatibleAlgorithm.setCompatibleSM2WithoutZ(true);
        boolean verifiedResult = engine.p7VerifyMessageAttach(signData.getBytes(), session);

        assertTrue("testSM2WithoutZVerify", verifiedResult);

        CompatibleAlgorithm.setCompatibleSM2WithoutZ(false);

        verifiedResult = engine.p7VerifyMessageAttach(signData.getBytes(), session);

        assertFalse("testSM2WithoutZVerify", verifiedResult);

    }
}
