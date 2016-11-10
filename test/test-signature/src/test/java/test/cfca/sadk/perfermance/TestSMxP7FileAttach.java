package test.cfca.sadk.perfermance;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.EnvelopeUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public final class TestSMxP7FileAttach extends TestFileFrame {

    @Override
    void init() throws Exception {
        final String cmbcCertPath = Constants.BASE_CERT_PATH + "cmbcTest.cer";
        final String sm2Path = Constants.BASE_CERT_PATH + "0001.sm2";
        final String sm2Pass = "123123";
        priKey = KeyUtil.getPrivateKeyFromSM2(sm2Path, sm2Pass);
        cert = new X509Cert(new FileInputStream(cmbcCertPath));
        cert = CertUtil.getCertFromSM2(sm2Path);
        certs = new X509Cert[] { cert };

    }

    @Override
    String getTestName() {
        return "P7Attach: SM2-256";
    }

    public static void main(String[] args) throws Exception {
        TestFileFrame.runTest(new TestSMxP7FileAttach(), true, args);
    }

 

    @Override
    String signedFile(String sourceFile,String id) throws UnsupportedEncodingException, PKIException {
        String signedFile = sourceFile + "." + id + ".sig";
        new Signature().p7SignFileAttach(Mechanism.SM3_SM2, sourceFile, signedFile, priKey, cert, session);
        return signedFile;
    }

    @Override
    String envelopedFile(String sourceFile,String id) throws UnsupportedEncodingException, PKIException {
        String outFilePath = null;
        if (sourceFile.indexOf(id) < 0) {
            outFilePath = sourceFile + "." + id + ".enc";
        } else {
            outFilePath = sourceFile + ".enc";
        }

        EnvelopeUtil.envelopeFile(sourceFile, outFilePath, Mechanism.SM4_CBC, certs, session);
        return outFilePath;
    }

    @Override
    String openEnvelopedFile(String envelopedFile,String id) throws UnsupportedEncodingException, PKIException {
        String outFilePath = null;
        if (envelopedFile.indexOf(id) < 0) {
            outFilePath = envelopedFile + "." + id + ".dec";
        } else {
            outFilePath = envelopedFile + ".dec";
        }
        EnvelopeUtil.openEnvelopedFile(envelopedFile, outFilePath, priKey, cert, session);
        return outFilePath;
    }

    @Override
    boolean verifiedFile(String sourceFile, String signedFile,String id) throws UnsupportedEncodingException, PKIException {
        String outFilePath = null;
        if (signedFile.indexOf(id) < 0) {
            outFilePath = signedFile + "." + id + ".src";
        } else {
            outFilePath = signedFile + ".src";
        }

        return new Signature().p7VerifyFileAttach(signedFile, outFilePath, session);
    }

}
