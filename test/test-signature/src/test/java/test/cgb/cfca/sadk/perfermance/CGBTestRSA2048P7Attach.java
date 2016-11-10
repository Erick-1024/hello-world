package test.cgb.cfca.sadk.perfermance;

import java.io.UnsupportedEncodingException;

import test.cgb.cfca.sadk.CGBTestData;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.cgb.toolkit.Castle;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.util.CertUtil;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public class CGBTestRSA2048P7Attach extends CGBTestFrame {

    byte[] certBytes = null;

    @Override
    void init() throws Exception {
        final String pfxPath = CGBTestData.TESTDATA_DIR + "rsa/test2048.pfx";
        final String pfxPass = "123123";

        castle = new Castle(CGBTestFrame.jniFlag ? JCrypto.JNI_LIB : JCrypto.JSOFT_LIB);
        castle.initCertAppContext(pfxPath, pfxPass, null, null);
        certBytes = CertUtil.getCertFromPFX(pfxPath, pfxPass).getEncoded();
    }

    @Override
    String getTestName() {
        return "P7Attach: RSA2048";
    }

    public static void main(String[] args) throws Exception {
        CGBTestFrame.runTest(new CGBTestRSA2048P7Attach(), true, args);
    }

    @Override
    final String signedMessage(String message) throws UnsupportedEncodingException, PKIException {
        return castle.signData(Mechanism.SHA256_RSA, message);
    }

    @Override
    final String envelopedMessage(String signedData) throws UnsupportedEncodingException, PKIException {
        return castle.generateEnvelope(signedData, Mechanism.DES3_CBC, certBytes);
    }

    @Override
    final String openEnvelopedMessage(String encryptedData) throws UnsupportedEncodingException, PKIException {
        return castle.decodeEnvelope(encryptedData);
    }

    @Override
    final boolean verifiedMessage(String message, String signature) throws UnsupportedEncodingException, PKIException {
        return message.equals(castle.verifySignedData(signature));
    }
}
