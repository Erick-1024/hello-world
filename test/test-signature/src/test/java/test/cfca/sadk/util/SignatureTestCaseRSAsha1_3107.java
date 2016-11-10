package test.cfca.sadk.util;

import test.cfca.sadk.testdata.RSATestData;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public class SignatureTestCaseRSAsha1_3107 extends SignatureTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        hashAlg = "SHA1";
        signAlg = "SHA1withRSAEncryption";
        priKey = RSATestData.userPriKey;
        pubKey = RSATestData.userPubKey;
        cert = RSATestData.UserCert;

        sourceFilePath = "TestData/rsa/test.dat";
        signedFilePath = "TestData/rsa/test-sha1.sig";
        signedDataPathAttach = "TestData/rsa/test-sha1-msg-attach.p7b";
        signedDataPathDetach = "TestData/rsa/test-sha1-msg-detach.p7b";
        signedFilePathAttach = "TestData/rsa/test-sha1-file-attach.p7b";
        signedFilePathDetach = "TestData/rsa/test-sha1-file-detach.p7b";

        sourceHashPath = "TestData/rsa/test-sha1.hash";
        signedHashPathP7 = "TestData/rsa/test-sha1-hash-signed.p7b";
        signedHashPathP1 = "TestData/rsa/test-sha1-hash-signed.p1";

        signedByteLength = 256;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    boolean R32Version() {
        return false;
    }
}
