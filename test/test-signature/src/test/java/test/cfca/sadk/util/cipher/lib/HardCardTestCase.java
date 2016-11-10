package test.cfca.sadk.util.cipher.lib;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Assert;

import test.cfca.sadk.perfermance.Constants;
import test.cfca.sadk.testdata.RSATestData;
import test.cfca.sadk.testdata.SM2TestData;
import test.cfca.sadk.testdata.SYMTestData;
import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.algorithm.common.GenKeyAttribute;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.lib.crypto.bcsoft.BCSoftLib;
import cfca.sadk.lib.crypto.hard.HardLib;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.HashUtil;

public class HardCardTestCase extends TestCase {

    static Session hardLib;
    static Session softLib;
    static {
        try {
            TestReady.openSession();
            softLib = new BCSoftLib();
            hardLib = new HardLib(null);
            System.err.println(hardLib.getClass().getName());
            System.err.println(hardLib.getProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setUp() throws Exception {

    }

    protected void tearDown() throws Exception {

    }

    final KeyPair RSAKeyPair() throws PKIException {

        Mechanism mechanism = new Mechanism("RSA");
        GenKeyAttribute attr = new GenKeyAttribute();
        attr.isExport = false;
        attr.keyNum = 04;// 04=1024;12=2048
        mechanism.setParam(attr);

        return hardLib.generateKeyPair(mechanism, 0);

    }

    final KeyPair SM2KeyPair() throws PKIException {

        Mechanism mechanism = new Mechanism("SM2");
        GenKeyAttribute attr = new GenKeyAttribute();
        attr.isExport = false;
        attr.keyNum = 06;//
        mechanism.setParam(attr);

        return hardLib.generateKeyPair(mechanism, 0);

    }

    public static final KeyPair GenerateKeyPair(String keyType, int keyIndex) throws PKIException {

        Mechanism mechanism = new Mechanism(keyType);
        GenKeyAttribute attr = new GenKeyAttribute();
        attr.isExport = false;
        attr.keyNum = keyIndex;
        mechanism.setParam(attr);

        return hardLib.generateKeyPair(mechanism, 0);

    }

    public final void testSM2Operations() throws Exception {
        boolean softLibFlat = true;
        if (!softLibFlat) {
            return;
        }
        TestReady.openSession();
        Session softLib = new BCSoftLib();
        Session hardLib = new HardLib(null);

        KeyPair keyPair = hardLib.generateKeyPair(new Mechanism(Mechanism.SM2), 256);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        byte[] sign = softLib.sign(new Mechanism(Mechanism.SM3_SM2), privateKey, SM2TestData.sm2Data);

        boolean result = hardLib.verify(new Mechanism(Mechanism.SM3_SM2), publicKey, SM2TestData.sm2Data, sign);

        Assert.assertTrue("sm2Operations#11", result);
        System.err.println("sm2Operations#11");

        sign = hardLib.sign(new Mechanism(Mechanism.SM3_SM2), privateKey, SM2TestData.sm2Data);

        result = softLib.verify(new Mechanism(Mechanism.SM3_SM2), publicKey, SM2TestData.sm2Data, sign);

        Assert.assertTrue("sm2Operations#12", result);
        System.err.println("sm2Operations#12");

        InputStream in = new ByteArrayInputStream(SM2TestData.sm2Data);

        sign = softLib.sign(new Mechanism(Mechanism.SM3_SM2), privateKey, in);

        in = new ByteArrayInputStream(SM2TestData.sm2Data);

        result = hardLib.verify(new Mechanism(Mechanism.SM3_SM2), publicKey, in, sign);

        Assert.assertTrue("sm2Operations#21", result);
        System.err.println("sm2Operations#21");
        in = new ByteArrayInputStream(SM2TestData.sm2Data);
        sign = hardLib.sign(new Mechanism(Mechanism.SM3_SM2), privateKey, in);
        in = new ByteArrayInputStream(SM2TestData.sm2Data);
        result = softLib.verify(new Mechanism(Mechanism.SM3_SM2), publicKey, in, sign);

        Assert.assertTrue("sm2Operations#22", result);
        System.err.println("sm2Operations#22");

        byte[] hash = SM2TestData.sm2HashValue;
        sign = softLib.signByHash(new Mechanism(Mechanism.SM3_SM2), privateKey, hash);

        result = hardLib.verifyByHash(new Mechanism(Mechanism.SM3_SM2), publicKey, hash, sign);

        Assert.assertTrue("sm2Operations#31", result);
        System.err.println("sm2Operations#31");

        sign = hardLib.signByHash(new Mechanism(Mechanism.SM3_SM2), privateKey, hash);

        result = softLib.verifyByHash(new Mechanism(Mechanism.SM3_SM2), publicKey, hash, sign);

        Assert.assertTrue("sm2Operations#32", result);
        System.err.println("sm2Operations#32");

        byte[] sourceData = SM2TestData.sm2Data;
        byte[] encryptData = softLib.encrypt(new Mechanism(Mechanism.SM2), publicKey, sourceData);

        byte[] decryptData = hardLib.decrypt(new Mechanism(Mechanism.SM2), privateKey, encryptData);

        Assert.assertTrue("sm2Operations#31", Arrays.equals(sourceData, decryptData));
        System.err.println("sm2Operations#31");

        encryptData = hardLib.encrypt(new Mechanism(Mechanism.SM2), publicKey, sourceData);

        decryptData = softLib.decrypt(new Mechanism(Mechanism.SM2), privateKey, encryptData);

        Assert.assertTrue("sm2Operations#32", Arrays.equals(sourceData, decryptData));
        System.err.println("sm2Operations#32");

    }

    public final void testRSASignMechanismPrivateKeyByteArray() throws PKIException {

        KeyPair keypair = RSAKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        Mechanism mechanism = new Mechanism(Mechanism.SHA256_RSA);
        byte[] sourceData = RSATestData.data;
        byte[] signData = hardLib.sign(mechanism, privateKey, sourceData);

        boolean verifiedResult = softLib.verify(mechanism, publicKey, sourceData, signData);

        Assert.assertTrue("testRSASignMechanismPrivateKeyByteArray", verifiedResult);

        System.err.println("testRSASignMechanismPrivateKeyByteArray");

    }

    public final void testRSASignMechanismPrivateKeyString() throws Exception {
        KeyPair keypair = RSAKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        Mechanism mechanism = new Mechanism(Mechanism.SHA256_RSA);

        InputStream in = new ByteArrayInputStream(RSATestData.data);

        byte[] signData = hardLib.sign(mechanism, privateKey, in);

        in = new ByteArrayInputStream(RSATestData.data);
        boolean verifiedResult = softLib.verify(mechanism, publicKey, in, signData);

        Assert.assertTrue("testRSASignMechanismPrivateKeyString", verifiedResult);

        System.err.println("testRSASignMechanismPrivateKeyString");

    }

    public final void testRSAverifyMechanismPublicKeyByteArrayByteArray() throws PKIException {
        Mechanism mechanism = new Mechanism(Mechanism.SHA256_RSA);
        PrivateKey privateKey = RSATestData.userPriKey;
        PublicKey publicKey = RSATestData.userPubKey;

        byte[] sourceData = RSATestData.data;
        byte[] signData = softLib.sign(mechanism, privateKey, sourceData);
        boolean verify = hardLib.verify(mechanism, publicKey, sourceData, signData);

        Assert.assertTrue("testRSAverifyMechanismPublicKeyByteArrayByteArray", verify);
        System.err.println("testRSAverifyMechanismPublicKeyByteArrayByteArray");

    }

    public final void testRSAverifyMechanismPublicKeyStringByteArray() throws Exception {

        PrivateKey privateKey = RSATestData.userPriKey;
        PublicKey publicKey = RSATestData.userPubKey;

        Mechanism mechanism = new Mechanism(Mechanism.SHA256_RSA);

        InputStream in = new ByteArrayInputStream(RSATestData.data);

        byte[] signData = softLib.sign(mechanism, privateKey, in);

        in = new ByteArrayInputStream(RSATestData.data);

        boolean verifiedResult = hardLib.verify(mechanism, publicKey, in, signData);

        Assert.assertTrue("testRSASignMechanismPrivateKeyString", verifiedResult);

        System.err.println("testRSASignMechanismPrivateKeyString");

    }

    public final void testRSASignByHash() throws PKIException {

        KeyPair keypair = RSAKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        Mechanism mechanism = new Mechanism(Mechanism.SHA256_RSA);
        byte[] sourceData = RSATestData.data;
        byte[] hash = HashUtil.RSAHashMessageByBC(sourceData, mechanism, false);
        byte[] signData = hardLib.signByHash(mechanism, privateKey, hash);

        boolean verifiedResult = softLib.verifyByHash(mechanism, publicKey, hash, signData);

        Assert.assertTrue("testRSASignByHash", verifiedResult);

        System.err.println("testRSASignByHash");

    }

    public final void testRSAVerifyByHash() throws PKIException {
        Mechanism mechanism = new Mechanism(Mechanism.SHA256_RSA);
        PrivateKey privateKey = RSATestData.userPriKey;
        PublicKey publicKey = RSATestData.userPubKey;

        byte[] sourceData = RSATestData.data;
        byte[] hash = HashUtil.RSAHashMessageByBC(sourceData, mechanism, false);

        byte[] signData = softLib.signByHash(mechanism, privateKey, hash);
        boolean verify = hardLib.verifyByHash(mechanism, publicKey, hash, signData);

        Assert.assertTrue("testRSAVerifyByHash", verify);
        System.err.println("testRSAVerifyByHash");

    }

    public final void testRSAEncryptMechanismKeyByteArray() throws Exception {

        Mechanism mechanism = new Mechanism(Mechanism.RSA_PKCS);
        PrivateKey privateKey = RSATestData.userPriKey;
        PublicKey publicKey = RSATestData.userPubKey;

        byte[] sourceData = RSATestData.data;

        byte[] encryptData = hardLib.encrypt(mechanism, publicKey, sourceData);

        byte[] decryptData = softLib.decrypt(mechanism, privateKey, encryptData);

        Assert.assertTrue("testRSAEncryptMechanismKeyByteArray", Arrays.equals(sourceData, decryptData));
        System.err.println("testRSAEncryptMechanismKeyByteArray");

    }

    public final void testRSADecryptMechanismKeyByteArray() throws Exception {
        Mechanism mechanism = new Mechanism(Mechanism.RSA_PKCS);
        KeyPair keypair = RSAKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        byte[] sourceData = RSATestData.data;

        byte[] encryptData = softLib.encrypt(mechanism, publicKey, sourceData);

        byte[] decryptData = hardLib.decrypt(mechanism, privateKey, encryptData);

        Assert.assertTrue("testRSADecryptMechanismKeyByteArray", Arrays.equals(sourceData, decryptData));
        System.err.println("testRSADecryptMechanismKeyByteArray");

    }

    public final void testSM2SignMechanismPrivateKeyByteArray() throws PKIException {

        KeyPair keypair = SM2KeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        Mechanism mechanism = new Mechanism(Mechanism.SM3_SM2);
        byte[] sourceData = RSATestData.data;
        byte[] signData = hardLib.sign(mechanism, privateKey, sourceData);

        boolean verifiedResult = softLib.verify(mechanism, publicKey, sourceData, signData);

        Assert.assertTrue("testSM2SignMechanismPrivateKeyByteArray", verifiedResult);

        System.err.println("testSM2SignMechanismPrivateKeyByteArray");

    }

    public final void testSM2SignMechanismPrivateKeyString() throws Exception {
        KeyPair keypair = SM2KeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        Mechanism mechanism = new Mechanism(Mechanism.SM3_SM2);

        InputStream in = new ByteArrayInputStream(RSATestData.data);

        byte[] signData = hardLib.sign(mechanism, privateKey, in);

        in = new ByteArrayInputStream(RSATestData.data);
        boolean verifiedResult = softLib.verify(mechanism, publicKey, in, signData);

        Assert.assertTrue("testSM2SignMechanismPrivateKeyString", verifiedResult);

        System.err.println("testSM2SignMechanismPrivateKeyString");
        in = new ByteArrayInputStream(RSATestData.data);

    }

    public final void testSM2verifyMechanismPublicKeyByteArrayByteArray() throws PKIException {
        Mechanism mechanism = new Mechanism(Mechanism.SM3_SM2);
        PrivateKey privateKey = SM2TestData.userPriKey;
        PublicKey publicKey = SM2TestData.userPubKey;

        byte[] sourceData = RSATestData.data;
        byte[] signData = softLib.sign(mechanism, privateKey, sourceData);
        boolean verify = hardLib.verify(mechanism, publicKey, sourceData, signData);

        Assert.assertTrue("testSM2verifyMechanismPublicKeyByteArrayByteArray", verify);
        System.err.println("testSM2verifyMechanismPublicKeyByteArrayByteArray");

    }

    public final void testSM2verifyMechanismPublicKeyStringByteArray() throws Exception {

        PrivateKey privateKey = SM2TestData.userPriKey;
        PublicKey publicKey = SM2TestData.userPubKey;

        Mechanism mechanism = new Mechanism(Mechanism.SM3_SM2);

        InputStream in = new ByteArrayInputStream(RSATestData.data);

        byte[] signData = softLib.sign(mechanism, privateKey, in);

        in = new ByteArrayInputStream(RSATestData.data);

        boolean verifiedResult = hardLib.verify(mechanism, publicKey, in, signData);

        Assert.assertTrue("testSM2SignMechanismPrivateKeyString", verifiedResult);

        System.err.println("testSM2SignMechanismPrivateKeyString");

    }

    public final void testSM2SignByHash() throws Exception {

        KeyPair keypair = SM2KeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        Mechanism mechanism = new Mechanism(Mechanism.SM3_SM2);
        byte[] sourceData = RSATestData.data;
        byte[] hash = HashUtil.SM2HashMessageByBCWithoutZValue(sourceData);
        byte[] signData = hardLib.signByHash(mechanism, privateKey, hash);

        boolean verifiedResult = softLib.verifyByHash(mechanism, publicKey, hash, signData);

        Assert.assertTrue("testSM2SignByHash", verifiedResult);

        System.err.println("testSM2SignByHash");

    }

    public final void testSM2VerifyByHash() throws Exception {
        Mechanism mechanism = new Mechanism(Mechanism.SM3_SM2);
        PrivateKey privateKey = SM2TestData.userPriKey;
        PublicKey publicKey = SM2TestData.userPubKey;

        byte[] sourceData = RSATestData.data;
        byte[] hash = HashUtil.SM2HashMessageByBCWithoutZValue(sourceData);

        byte[] signData = softLib.signByHash(mechanism, privateKey, hash);
        boolean verify = hardLib.verifyByHash(mechanism, publicKey, hash, signData);

        Assert.assertTrue("testSM2VerifyByHash", verify);
        System.err.println("testSM2VerifyByHash");

    }

    public final void testSM2EncryptMechanismKeyByteArray() throws Exception {

        Mechanism mechanism = new Mechanism(Mechanism.SM2);
        PrivateKey privateKey = SM2TestData.userPriKey;
        PublicKey publicKey = SM2TestData.userPubKey;

        byte[] sourceData = RSATestData.data;

        byte[] encryptData = hardLib.encrypt(mechanism, publicKey, sourceData);

        byte[] decryptData = softLib.decrypt(mechanism, privateKey, encryptData);

        Assert.assertTrue("testSM2EncryptMechanismKeyByteArray", Arrays.equals(sourceData, decryptData));
        System.err.println("testSM2EncryptMechanismKeyByteArray");

    }

    public final void testSM2DecryptMechanismKeyByteArray() throws Exception {
        Mechanism mechanism = new Mechanism(Mechanism.SM2);
        KeyPair keypair = SM2KeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        byte[] sourceData = RSATestData.data;

        byte[] encryptData = softLib.encrypt(mechanism, publicKey, sourceData);

        byte[] decryptData = hardLib.decrypt(mechanism, privateKey, encryptData);

        Assert.assertTrue("testSM2DecryptMechanismKeyByteArray", Arrays.equals(sourceData, decryptData));
        System.err.println("testSM2DecryptMechanismKeyByteArray");

        hardLib.decrypt(mechanism, privateKey, encryptData);

    }

    public final void testDES3EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] encryptedData = hardLib.encrypt(mechanism, SYMTestData.desKey, data);

        Assert.assertTrue("testDES3DecryptMechanismKeyByteArray", Arrays.equals(encryptedData, SYMTestData.desEncryptedBytes));
        System.err.println("testDES3EncryptMechanismKeyByteArray");
    }

    public final void testDES3DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] encryptedData = SYMTestData.desEncryptedBytes;

        byte[] decryptedData = hardLib.decrypt(mechanism, SYMTestData.desKey, encryptedData);
        Assert.assertTrue("testDES3DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testDES3DecryptMechanismKeyByteArray");
    }

    public final void testDES3EncryptMechanismKeyStringString() throws Exception {

        Mechanism mechanism = SYMTestData.desMechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/sym/test.enc";

        hardLib.encrypt(mechanism, SYMTestData.desKey, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath));

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testDES3EncryptMechanismKeyStringString", Constants.equals(encryptedData, SYMTestData.desEncryptedBytes));
        System.err.println("testDES3EncryptMechanismKeyStringString");

    }

    public final void tesDES3DecryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/desEncryptedBytes.dat";
        String plainTextFilePath = "TestData/sym/test.dec";

        hardLib.decrypt(mechanism, SYMTestData.desKey, new FileInputStream(encryptFilePath), new FileOutputStream(plainTextFilePath));

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("tesDES3DecryptMechanismKeyStringString", Arrays.equals(decryptedData, data));
        System.err.println("tesDES3DecryptMechanismKeyStringString");
    }

    public final void testRC4EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] encryptedData = hardLib.encrypt(mechanism, SYMTestData.rc4Key, data);

        Assert.assertTrue("testRC4EncryptMechanismKeyByteArray", Arrays.equals(encryptedData, SYMTestData.rc4EncryptedBytes));
        System.err.println("testRC4EncryptMechanismKeyByteArray");
    }

    public final void testRC4DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] encryptedData = SYMTestData.rc4EncryptedBytes;

        byte[] decryptedData = hardLib.decrypt(mechanism, SYMTestData.rc4Key, encryptedData);
        Assert.assertTrue("testRC4DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testRC4DecryptMechanismKeyByteArray");
    }

    public final void testRC4EncryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/sym/test.enc";

        hardLib.encrypt(mechanism, SYMTestData.rc4Key, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath));

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testRC4EncryptMechanismKeyStringString", Arrays.equals(encryptedData, SYMTestData.rc4EncryptedBytes));
        System.err.println("testRC4EncryptMechanismKeyStringString");
    }

    public final void tesRC4DecryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/rc4EncryptedBytes.dat";
        String plainTextFilePath = "TestData/sym/test.dec";

        hardLib.decrypt(mechanism, SYMTestData.rc4Key, new FileInputStream(encryptFilePath), new FileOutputStream(plainTextFilePath));

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("tesRC4DecryptMechanismKeyStringString", Arrays.equals(decryptedData, data));
        System.err.println("tesRC4DecryptMechanismKeyStringString");
    }

    public final void testSM4EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] encryptedData = hardLib.encrypt(mechanism, SYMTestData.sm4Key, data);

        Assert.assertTrue("testSM4EncryptMechanismKeyByteArray", Arrays.equals(encryptedData, SYMTestData.sm4EncryptedBytes));

        byte[] decryptedData = hardLib.decrypt(mechanism, SYMTestData.sm4Key, encryptedData);
        Assert.assertTrue("testSM4EncryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testSM4EncryptMechanismKeyByteArray");

    }

    public final void testSM4DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] encryptedData = SYMTestData.sm4EncryptedBytes;

        byte[] decryptedData = hardLib.decrypt(mechanism, SYMTestData.sm4Key, encryptedData);
        Assert.assertTrue("testSM4DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testSM4DecryptMechanismKeyByteArray");
    }

    public final void testSM4EncryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/sym/test.enc";

        hardLib.encrypt(mechanism, SYMTestData.sm4Key, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath));

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testSM4EncryptMechanismKeyStringString", Constants.equals(encryptedData, SYMTestData.sm4EncryptedBytes));
        System.err.println("testSM4EncryptMechanismKeyStringString");
    }

    public final void testSM4DecryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/sm4EncryptedBytes.dat";
        String plainTextFilePath = "TestData/sym/test.dec";

        hardLib.decrypt(mechanism, SYMTestData.sm4Key, new FileInputStream(encryptFilePath), new FileOutputStream(plainTextFilePath));

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("testSM4DecryptMechanismKeyStringString", Constants.equals(decryptedData, data));
        System.err.println("testSM4DecryptMechanismKeyStringString");
    }

    public final void testGenerateKey() throws PKIException {
        hardLib.generateKey(new Mechanism(Mechanism.DES3_KEY));
        hardLib.generateKey(new Mechanism(Mechanism.RC4_KEY));
        hardLib.generateKey(new Mechanism(Mechanism.SM4_KEY));

        Assert.assertTrue("testGenerateKey", true);
        System.err.println("testGenerateKey");
    }

}
