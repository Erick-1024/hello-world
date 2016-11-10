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
public final class TestSMxP1 extends TestFrame {

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
    String signedMessage(byte[] message) throws UnsupportedEncodingException, PKIException {
        return new String(new Signature().p1SignMessage(Mechanism.SM3_SM2, message, priKey, session));
    }

    @Override
    String envelopedMessage(byte[] signedData) throws UnsupportedEncodingException, PKIException {
        return new String(EnvelopeUtil.envelopeMessage(signedData, Mechanism.SM4_CBC, certs, session), charsetName);
    }

    @Override
    String openEnvelopedMessage(byte[] encryptedData) throws UnsupportedEncodingException, PKIException {
        return new String(EnvelopeUtil.openEvelopedMessage(encryptedData, priKey, cert, session), charsetName);
    }

    @Override
    boolean verifiedMessage(byte[] message, byte[] signature) throws UnsupportedEncodingException, PKIException {
        return new Signature().p1VerifyMessage(Mechanism.SM3_SM2, message, signature, cert.getPublicKey(), session);
    }

    @Override
    String getTestName() {
        return "------P1: SM2-256";
    }

    public static void main(String[] args) throws Exception {
        TestFrame.runTest(new TestSMxP1(), true, args);
    }

}
