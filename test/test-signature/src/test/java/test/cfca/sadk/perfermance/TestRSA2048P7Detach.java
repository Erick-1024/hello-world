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
public class TestRSA2048P7Detach extends TestFrame {

    @Override
    void init() throws Exception {
        final String cmbcCertPath = "TestData/rsa/boca.cer";
        final String sm2Path = "TestData/rsa/0001.pfx";
        final String sm2Pass = "123123";
        priKey = KeyUtil.getPrivateKeyFromPFX(sm2Path, sm2Pass);
        cert = new X509Cert(new FileInputStream(cmbcCertPath));
        cert = CertUtil.getCertFromPFX(sm2Path, sm2Pass);
        certs = new X509Cert[] { cert };

    }

    @Override
    final String signedMessage(byte[] message) throws UnsupportedEncodingException, PKIException {
        return new String(new Signature().p7SignMessageDetach(Mechanism.SHA256_RSA, message, priKey, cert, session));
    }

    @Override
    final String envelopedMessage(byte[] signedData) throws UnsupportedEncodingException, PKIException {
        return new String(EnvelopeUtil.envelopeMessage(signedData, Mechanism.DES3_CBC, certs, session), charsetName);
    }

    @Override
    final String openEnvelopedMessage(byte[] encryptedData) throws UnsupportedEncodingException, PKIException {
        return new String(EnvelopeUtil.openEvelopedMessage(encryptedData, priKey, cert, session), charsetName);
    }

    @Override
    final boolean verifiedMessage(byte[] message, byte[] signature) throws UnsupportedEncodingException, PKIException {
        return new Signature().p7VerifyMessageDetach(message, signature, session);
    }

    @Override
    String getTestName() {
        return "P7Detach: RSA2048";
    }

    public static void main(String[] args) throws Exception {
        TestFrame.runTest(new TestRSA2048P7Detach(), true, args);
    }

}
