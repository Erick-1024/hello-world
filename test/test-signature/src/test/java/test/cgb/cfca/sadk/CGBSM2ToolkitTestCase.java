package test.cgb.cfca.sadk;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import test.cgb.cfca.sadk.testdata.CGBSM2TestData;
import cfca.sadk.algorithm.sm2.SM2PrivateKey;
import cfca.sadk.cgb.toolkit.SM2Toolkit;
import cfca.sadk.org.bouncycastle.asn1.sm2.ASN1SM2Signature;

public class CGBSM2ToolkitTestCase {

    final SM2Toolkit toolkit = new SM2Toolkit();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSM2GenerateKeyPair() throws Exception {
        KeyPair keypair = toolkit.SM2GenerateKeyPair();

        Assert.assertTrue("testSM2GenerateKeyPair", keypair != null);

        Assert.assertTrue("testSM2GenerateKeyPair", keypair.getPrivate() != null);
        Assert.assertTrue("testSM2GenerateKeyPair", keypair.getPublic() != null);
    }

    @Test
    public void testSM2BuildKeyPairByText() throws Exception {
        String sm2UserPfxText = CGBSM2TestData.sm2UserPfxText;
        String sm2FilePassword = "123123";
        KeyPair keypair = toolkit.SM2BuildKeyPairByText(sm2UserPfxText, sm2FilePassword);
        Assert.assertTrue("testSM2BuildKeyPairByText", keypair != null);

        Assert.assertTrue("testSM2BuildKeyPairByText", keypair.getPrivate() != null);
        Assert.assertTrue("testSM2BuildKeyPairByText", keypair.getPublic() != null);
    }

    @Test
    public void testSM2BuildKeyPairByFile() throws Exception {
        String sm2FilePath = CGBTestData.TESTDATA_DIR +"sm2/cmbc.sm2";
        String sm2FilePassword = "123123";
        KeyPair keypair = toolkit.SM2BuildKeyPairByFile(sm2FilePath, sm2FilePassword);
        Assert.assertTrue("testSM2BuildKeyPairByFile", keypair != null);

        Assert.assertTrue("testSM2BuildKeyPairByFile", keypair.getPrivate() != null);
        Assert.assertTrue("testSM2BuildKeyPairByFile", keypair.getPublic() != null);
    }

    @Test
    public void testSM2BuildPublicKey() throws Exception {
        PublicKey publicKey = toolkit.SM2BuildPublicKey(CGBSM2TestData.base64PubKey);
        Assert.assertTrue("testSM2BuildPublicKey", publicKey != null);
        Assert.assertTrue("testSM2BuildPublicKey", publicKey.equals(CGBSM2TestData.userPubKey));
    }

    @Test
    public void testSM2BuildPrivateKey() throws Exception {
        PrivateKey privateKey = toolkit.SM2BuildPrivateKey(CGBSM2TestData.base64PriKey);
        Assert.assertTrue("testSM2BuildPrivateKey", privateKey != null);
        Assert.assertTrue("testSM2BuildPrivateKey", privateKey.equals(CGBSM2TestData.userPriKey));

    }

    @Test
    public void testSM2EncryptData() throws Exception {
        byte[] data = CGBSM2TestData.dataBytes;
        PublicKey sm2PublicKey = CGBSM2TestData.userPubKey;
        PrivateKey sm2PrivateKey = CGBSM2TestData.userPriKey;
        byte[] encryptedBytes = toolkit.SM2EncryptData(sm2PublicKey, data);

        byte[] decryptedBytes = toolkit.SM2DecryptData(sm2PrivateKey, encryptedBytes);

        Assert.assertTrue("testSM2EncryptData", Arrays.equals(data, decryptedBytes));
    }

    @Test
    public void testSM2DecryptData() throws Exception {
        byte[] data = CGBSM2TestData.dataBytes;
        PrivateKey sm2PrivateKey = CGBSM2TestData.userPriKey;

        byte[] decryptedBytes = toolkit.SM2DecryptData(sm2PrivateKey, CGBSM2TestData.encryptedBytes);

        Assert.assertTrue("testSM2DecryptData", Arrays.equals(data, decryptedBytes));
    }

    @Test
    public void testSM2Sign() throws Exception {
        byte[] sourceData = CGBSM2TestData.sm2Data;

        SM2PrivateKey sm2PrivateKey = CGBSM2TestData.userPriKey;
        PublicKey sm2PublicKey = CGBSM2TestData.userPubKey;

        byte[] signature = toolkit.SM2Sign(sm2PrivateKey, sourceData);

        boolean verifyResult = toolkit.SM2Verify(sm2PublicKey, sourceData, signature);

        Assert.assertTrue("testSM2Sign", verifyResult);
        Assert.assertTrue("testSM2Sign", signature.length > 64);

        ASN1SM2Signature asn1Sm2Signature = ASN1SM2Signature.getInstance(signature);
        Assert.assertTrue("testSM2Sign", asn1Sm2Signature != null);
    }

    @Test
    public void testSM2Verify() throws Exception {
        byte[] sourceData = CGBSM2TestData.sm2Data;
        PublicKey sm2PublicKey = CGBSM2TestData.userPubKey;
        byte[] signData = CGBSM2TestData.sm2SignValue;

        boolean verifyResult = toolkit.SM2Verify(sm2PublicKey, sourceData, signData);

        Assert.assertTrue("testSM2Verify", verifyResult);
    }

    @Test
    public void testSM2SignHash() throws Exception {
        byte[] hash = CGBSM2TestData.sm2HashValue;
        SM2PrivateKey sm2PrivateKey = CGBSM2TestData.userPriKey;
        PublicKey sm2PublicKey = CGBSM2TestData.userPubKey;

        byte[] signature = toolkit.SM2SignHash(sm2PrivateKey, hash);

        boolean verifyResult = toolkit.SM2VerifyHash(sm2PublicKey, hash, signature);

        Assert.assertTrue("testSM2SignHash", verifyResult);

        Assert.assertTrue("testSM2SignHash", signature.length > 64);

        ASN1SM2Signature asn1Sm2Signature = ASN1SM2Signature.getInstance(signature);
        Assert.assertTrue("testSM2SignHash", asn1Sm2Signature != null);
    }

    @Test
    public void testSM2VerifyHash() throws Exception {
        byte[] hash = CGBSM2TestData.sm2HashValue;
        PublicKey sm2PublicKey = CGBSM2TestData.userPubKey;
        byte[] signData = CGBSM2TestData.sm2SignValue;

        boolean verifyResult = toolkit.SM2VerifyHash(sm2PublicKey, hash, signData);

        Assert.assertTrue("testSM2VerifyHash", verifyResult);
    }

}
