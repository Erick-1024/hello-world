package test.cfca.sadk.util;

import java.io.IOException;

import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.x509.certificate.X509Cert;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 *
 */
public class EnvelopeUtilRC4TestCase extends EnvelopeUtilTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        sourceFilePath = "./TestData/envelopes/test.bin";
        encryptFilePath = "./TestData/envelopes/test.rc4.enc";

        sourceData = FileHelper.read(sourceFilePath);
        cmsEnvelopedDataBase64 = FileHelper.read( "./TestData/envelopes/test.rc4.msg");

        mechanism = Mechanism.RC4;

        final String pfxPath = "./TestData/cert/CFCACSP1024qm.pfx";
        final String pfxPWD = "123123";

        recvCert = CertUtil.getCertFromPFX(pfxPath, pfxPWD);
        privateKey = KeyUtil.getPrivateKeyFromPFX(pfxPath, pfxPWD);
        recvCerts = new X509Cert[] { recvCert };

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsRecipient() {
        
        super.callIsRecipient();
    }

    public void testEnvelopeMessageByteArrayStringX509CertArray() {
       
        super.callEnvelopeMessageByteArrayStringX509CertArray();
    }

    public void testEnvelopeMessageByteArrayStringX509CertArraySession() {
       
        super.callEnvelopeMessageByteArrayStringX509CertArraySession();
    }

    public void testEnvelopeFileStringStringStringX509CertArray() throws IOException {
       
        super.callEnvelopeFileStringStringStringX509CertArray();
    }

    public void testEnvelopeFileStringStringStringX509CertArraySession() throws IOException {
       
        super.callEnvelopeFileStringStringStringX509CertArraySession();
    }

    public void testOpenEnvelopedFile() throws IOException {
       
        super.callOpenEnvelopedFile();
    }

    public void testOpenEvelopedMessage() {
       
        super.callOpenEvelopedMessage();
    }

}
