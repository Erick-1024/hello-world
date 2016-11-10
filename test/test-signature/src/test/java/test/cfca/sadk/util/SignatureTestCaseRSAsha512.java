package test.cfca.sadk.util;

import test.cfca.sadk.testdata.RSATestData;
import cfca.sadk.algorithm.common.Mechanism;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 *
 */
public class SignatureTestCaseRSAsha512 extends SignatureTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        hashAlg = "SHA-512";
        signAlg = Mechanism.SHA512_RSA;
        priKey = RSATestData.userPriKey;
        pubKey = RSATestData.userPubKey;
        cert = RSATestData.UserCert;

        sourceFilePath = "TestData/rsa/test.dat";
        signedFilePath = "TestData/rsa/test-sha512.sig";
        signedDataPathAttach = "TestData/rsa/test-sha512-msg-attach.p7b";
        signedDataPathDetach = "TestData/rsa/test-sha512-msg-detach.p7b";
        signedFilePathAttach = "TestData/rsa/test-sha512-file-attach.p7b";
        signedFilePathDetach = "TestData/rsa/test-sha512-file-detach.p7b";

        sourceHashPath = "TestData/rsa/test-sha512.hash";
        signedHashPathP7 = "TestData/rsa/test-sha512-hash-signed.p7b";
        signedHashPathP1 = "TestData/rsa/test-sha512-hash-signed.p1";

        signedByteLength = 256;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
