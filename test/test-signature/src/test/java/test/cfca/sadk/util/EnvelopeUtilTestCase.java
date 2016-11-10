package test.cfca.sadk.util;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Arrays;

import junit.framework.TestCase;
import test.cfca.sadk.testdata.SM2TestData;
import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.EnvelopeUtil;
import cfca.sadk.x509.certificate.X509Cert;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public abstract class EnvelopeUtilTestCase extends TestCase {

    Session session = null;

    X509Cert recvCert = null;
    X509Cert[] recvCerts = null;
    PrivateKey privateKey = null;

    String sourceFilePath = null;
    String encryptFilePath = null;
    byte[] sourceData = null;
    byte[] cmsEnvelopedDataBase64 = null;

    String mechanism = null;

    protected void setUp() throws Exception {
        session = TestReady.openSession();

        sourceFilePath = "./TestData/envelopes/test.bin";
        encryptFilePath = "./TestData/envelopes/test.sm4.enc";

        sourceData = FileHelper.read(sourceFilePath);
        cmsEnvelopedDataBase64 = FileHelper.read("./TestData/envelopes/test.sm4.msg");

        mechanism = Mechanism.SM4_CBC;

        recvCert = SM2TestData.UserCert;
        privateKey = SM2TestData.userPriKey;
        recvCerts = new X509Cert[] { recvCert };

    }

    protected void tearDown() throws Exception {
        session = null;
    }

    public void testCalls() {

    }

    public void callIsRecipient() {

        try {
            boolean isRecipient = EnvelopeUtil.isRecipient(recvCert, cmsEnvelopedDataBase64);

            assertTrue("testIsRecipient", isRecipient);
        } catch (PKIException e) {
            e.printStackTrace();
            assertTrue("testIsRecipient", false);
        }
    }

    public void callEnvelopeMessageByteArrayStringX509CertArray() {
        try {
            byte[] cmsEnvelopedDataBase64 = EnvelopeUtil.envelopeMessage(sourceData, mechanism, recvCerts);

            byte[] dec = EnvelopeUtil.openEvelopedMessage(cmsEnvelopedDataBase64, privateKey, recvCert, session);

            assertTrue("testEnvelopeMessageByteArrayStringX509CertArraySession", Arrays.equals(sourceData, dec));
        } catch (PKIException e) {
            e.printStackTrace();
            assertTrue("testEnvelopeMessageByteArrayStringX509CertArraySession", false);
        }
    }

    public void callEnvelopeMessageByteArrayStringX509CertArraySession() {

        try {
            byte[] cmsEnvelopedDataBase64 = EnvelopeUtil.envelopeMessage(sourceData, mechanism, recvCerts, session);

            byte[] dec = EnvelopeUtil.openEvelopedMessage(cmsEnvelopedDataBase64, privateKey, recvCert, session);

            assertTrue("testEnvelopeMessageByteArrayStringX509CertArraySession", Arrays.equals(sourceData, dec));
        } catch (PKIException e) {
            e.printStackTrace();
            assertTrue("testEnvelopeMessageByteArrayStringX509CertArraySession", false);
        }

    }

    public void callEnvelopeFileStringStringStringX509CertArray() throws IOException {

        String encryptFilePath = "./TestData/out/test.enc.tmp";
        String plainTextFilePath = "./TestData/out/test.dec.tmp";

        try {

            EnvelopeUtil.envelopeFile(sourceFilePath, encryptFilePath, mechanism, recvCerts);

            EnvelopeUtil.openEnvelopedFile(encryptFilePath, plainTextFilePath, privateKey, recvCert, session);
        } catch (PKIException e) {
            e.printStackTrace();
            assertTrue("testEnvelopeFileStringStringStringX509CertArray", false);
        }

        byte[] dec = FileHelper.read(plainTextFilePath);

        assertTrue("testEnvelopeFileStringStringStringX509CertArray", Arrays.equals(sourceData, dec));
    }

    public void callEnvelopeFileStringStringStringX509CertArraySession() throws IOException {

        String encryptFilePath = "./TestData/out/test.enc.tmp";
        String plainTextFilePath = "./TestData/out/test.dec.tmp";

        try {
            EnvelopeUtil.envelopeFile(sourceFilePath, encryptFilePath, mechanism, recvCerts, session);

            EnvelopeUtil.openEnvelopedFile(encryptFilePath, plainTextFilePath, privateKey, recvCert, session);
        } catch (PKIException e) {
            e.printStackTrace();
            assertTrue("testEnvelopeFileStringStringStringX509CertArraySession", false);
        }

        byte[] dec = FileHelper.read(plainTextFilePath);

        assertTrue("testEnvelopeFileStringStringStringX509CertArraySession", Arrays.equals(sourceData, dec));

    }

    public void callOpenEnvelopedFile() throws IOException {

        String plainTextFilePath = "./TestData/out/test.dec.tmp";

        try {
            EnvelopeUtil.openEnvelopedFile(encryptFilePath, plainTextFilePath, privateKey, recvCert, session);
        } catch (PKIException e) {
            e.printStackTrace();
            assertTrue("testOpenEnvelopedFile", false);
        }

        byte[] dec = FileHelper.read(plainTextFilePath);

        assertTrue("testOpenEnvelopedFile", Arrays.equals(sourceData, dec));

    }

    public void callOpenEvelopedMessage() {

        try {
            byte[] dec = EnvelopeUtil.openEvelopedMessage(cmsEnvelopedDataBase64, privateKey, recvCert, session);

            assertTrue("testOpenEvelopedMessage", Arrays.equals(sourceData, dec));
        } catch (PKIException e) {
            e.printStackTrace();
            assertTrue("testOpenEvelopedMessage", false);
        }

    }

}
