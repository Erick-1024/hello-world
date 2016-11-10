package test.cgb.cfca.sadk.perfermance;

import test.cgb.cfca.sadk.CGBTestData;
import cfca.sadk.cgb.toolkit.Castle;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.util.CertUtil;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public final class CGBTestRSA1024P7Detach extends CGBTestRSA2048P7Detach {
    @Override
    void init() throws Exception {

        final String pfxPath = CGBTestData.TESTDATA_DIR + "rsa/test1024.pfx";
        final String pfxPass = "123123";

        castle = new Castle(CGBTestFrame.jniFlag ? JCrypto.JNI_LIB : JCrypto.JSOFT_LIB);
        castle.initCertAppContext(pfxPath, pfxPass, sm2Path, sm2Pass);
        certBytes = CertUtil.getCertFromPFX(pfxPath, pfxPass).getEncoded();

    }

    @Override
    String getTestName() {
        return "P7Detach: RSA1024";
    }

    public static void main(String[] args) throws Exception {
        CGBTestFrame.runTest(new CGBTestRSA1024P7Detach(), true, args);
    }

}
