package test.cfca.sadk.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PrivateKey;

import junit.framework.TestCase;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.lib.crypto.bcsoft.BCSoftLib;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;

public class BERTestCase extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetPrivateKeyFromBER() throws FileNotFoundException, PKIException {

        PrivateKey key = KeyUtil.getPrivateKeyFromPFX(new FileInputStream("TestData/login.ber_1.p12"), "1");

        assertTrue("testGetPrivateKeyFromBER", key != null);

    }

    public void testGetX509CertFromBER() throws FileNotFoundException, PKIException {

        X509Cert cer = CertUtil.getCertFromPFX(new FileInputStream("TestData/login.ber_1.p12"), "1");

        assertTrue("testGetX509CertFromBER", cer != null);

    }

    public void testBER() throws FileNotFoundException, PKIException {

        PrivateKey key = KeyUtil.getPrivateKeyFromPFX(new FileInputStream("TestData/login.ber_1.p12"), "1");

        assertTrue("testBER", key != null);

        X509Cert cer = CertUtil.getCertFromPFX(new FileInputStream("TestData/login.ber_1.p12"), "1");

        assertTrue("testBER", key != null);

        Session session = new BCSoftLib();

        final String signAlg = Mechanism.SHA256_RSA;
        final byte[] sourceData = "TESTING".getBytes();
        final Signature so = new Signature();

        byte[] base64P1SignedData = so.p1SignMessage(signAlg, sourceData, key, session);
        boolean testResult = so.p1VerifyMessage(signAlg, sourceData, base64P1SignedData, cer.getPublicKey(), session);

        assertTrue("testBER", testResult);
    }

}
