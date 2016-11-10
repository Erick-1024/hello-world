package test.cfca.sadk.util;

import test.cfca.sadk.testdata.SM2TestData;
import cfca.sadk.algorithm.common.Mechanism;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 *
 */
public class SignatureTestCaseSM2 extends SignatureTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        hashAlg = "SM3";
        signAlg = Mechanism.SM3_SM2;
        priKey = SM2TestData.userPriKey;
        pubKey = SM2TestData.userPubKey;
        cert = SM2TestData.UserCert;

        sourceFilePath = "TestData/sm2/test.dat";
        signedFilePath = "TestData/sm2/test-sm3.sig";
        signedDataPathAttach = "TestData/sm2/test-sm3-msg-attach.p7b";
        signedDataPathDetach = "TestData/sm2/test-sm3-msg-detach.p7b";
        signedFilePathAttach = "TestData/sm2/test-sm3-file-attach.p7b";
        signedFilePathDetach = "TestData/sm2/test-sm3-file-detach.p7b";

        sourceHashPath = "TestData/sm2/test-sm3.hash";
        signedHashPathP7 = "TestData/sm2/test-sm3-hash-signed.p7b";
        signedHashPathP1 = "TestData/sm2/test-sm3-hash-signed.p1";

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    

    public final void testP7SignFileAttach2() throws Exception {

        String signFilePath = "TestData/out/test-sign-file-attach-64.p7b";
        String outFilePath = "TestData/out/test-sign-file-attach.bin";

        engine.p7SignFileAttach(signAlg, sourceFilePath, signFilePath, priKey, cert, session);

        boolean verifyResult = engine.p7VerifyFileAttach(signFilePath, outFilePath, session);

        assertTrue("testP7SignFileAttach", verifyResult);

    }

}
