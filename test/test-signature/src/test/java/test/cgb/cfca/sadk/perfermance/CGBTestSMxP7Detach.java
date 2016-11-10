package test.cgb.cfca.sadk.perfermance;

import java.io.UnsupportedEncodingException;

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
public final class CGBTestSMxP7Detach extends CGBTestFrame {

    byte[] certBytes = null;

    @Override
    final void init() throws Exception {
        castle = new Castle(CGBTestFrame.jniFlag ? JCrypto.JNI_LIB : JCrypto.JSOFT_LIB);
        castle.initCertAppContext(null, null, sm2Path, sm2Pass);
        certBytes = CertUtil.getCertFromSM2(sm2Path).getEncoded();
    }

    @Override
    final String getTestName() {
        return "P7Detach: SM2-256";
    }

    public static void main(String[] args) throws Exception {
        CGBTestFrame.runTest(new CGBTestSMxP7Detach(), true, args);
    }

    @Override
    final String signedMessage(String message) throws UnsupportedEncodingException, PKIException {
        return castle.signDataDetached(Mechanism.SM3_SM2, message);
    }

    @Override
    final  String envelopedMessage(String signedData) throws UnsupportedEncodingException, PKIException {
        return castle.generateEnvelope(signedData, Mechanism.SM4_CBC, certBytes);
    }

    @Override
    final  String openEnvelopedMessage(String encryptedData) throws UnsupportedEncodingException, PKIException {
        return castle.decodeEnvelope(encryptedData);
    }

    @Override
    final boolean verifiedMessage(String message, String signature) throws UnsupportedEncodingException, PKIException {
        
        return castle.verifyDetachedSignedData( signature,message);
    }

}
