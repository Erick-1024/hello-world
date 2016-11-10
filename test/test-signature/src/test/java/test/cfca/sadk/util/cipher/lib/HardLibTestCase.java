package test.cfca.sadk.util.cipher.lib;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;

import junit.framework.TestCase;

import org.junit.Assert;

import test.cfca.sadk.perfermance.Constants;
import test.cfca.sadk.testdata.RSATestData;
import test.cfca.sadk.testdata.SM2TestData;
import test.cfca.sadk.testdata.SYMTestData;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.lib.crypto.bcsoft.BCSoftLib;
import cfca.sadk.lib.crypto.hard.HardLib;
import cfca.sadk.org.bouncycastle.asn1.sm2.ASN1SM2Cipher;
import cfca.sadk.org.bouncycastle.asn1.sm2.ASN1SM2Cipher.SM2EncryptedType;
import cfca.sadk.system.FileHelper;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class HardLibTestCase extends TestCase {

    Session session = null;

    protected void setUp() throws Exception {
        session = new HardLib(null);
        System.err.println(session.getClass().getName());
        System.err.println(session.getProvider());
    }

    protected void tearDown() throws Exception {
        session = null;
    }

    public final void xxtestGenerateKeyPair() throws Exception {

        session.generateKeyPair(new Mechanism(Mechanism.RSA), 1024);
        session.generateKeyPair(new Mechanism(Mechanism.RSA), 2048);

        KeyPair keyPair = session.generateKeyPair(new Mechanism(Mechanism.SM2), 256);

        java.security.spec.ECPoint Q = ((java.security.interfaces.ECPublicKey) keyPair.getPublic()).getW();

        Assert.assertTrue("testGenerateKeyPairD", ((java.security.interfaces.ECPrivateKey) keyPair.getPrivate()).getS().bitLength() <= 256);
        Assert.assertTrue("testGenerateKeyPairX", Q.getAffineX().bitLength() > 248);
        Assert.assertTrue("testGenerateKeyPairY", Q.getAffineY().bitLength() > 248);

        Mechanism m = new Mechanism(Mechanism.SM2);

        byte[] data = "anqing".getBytes();
        byte[] encryptedData = session.encrypt(m, keyPair.getPublic(), data);

        byte[] decryptedData = session.decrypt(m, keyPair.getPrivate(), encryptedData);

        Assert.assertTrue("testGenerateKeyPairY", Arrays.equals(decryptedData, data));

        System.err.println("testGenerateKeyPair");

    }

    public final void testRSASignMechanismPrivateKeyByteArray() throws PKIException {
        // RSA-SHA256
        byte[] signx = session.sign(new Mechanism(Mechanism.SHA256_RSA), RSATestData.userPriKey, RSATestData.data);
        Assert.assertTrue("testRSASignMechanismPrivateKeyByteArray", Arrays.equals(signx, RSATestData.sign));

        System.err.println("testRSASignMechanismPrivateKeyByteArray");

    }

    public final void testRSASignMechanismPrivateKeyString() throws Exception {
        // RSA-SHA256
        String sourceFilePath = "TestData/rsa/test256.dat";
        byte[] signx = session.sign(new Mechanism(Mechanism.SHA256_RSA), RSATestData.userPriKey, new FileInputStream(sourceFilePath));
        Assert.assertTrue("testRSASignMechanismPrivateKeyString", Arrays.equals(signx, RSATestData.sign));
        System.err.println("testRSASignMechanismPrivateKeyString");

    }

    public final void testRSAverifyMechanismPublicKeyByteArrayByteArray() throws PKIException {
        // RSA-SHA256
        boolean verify = session.verify(new Mechanism(Mechanism.SHA256_RSA), RSATestData.userPubKey, RSATestData.data, RSATestData.sign);
        Assert.assertTrue("testverifyMechanismPublicKeyByteArrayByteArray", verify);
        System.err.println("testRSAverifyMechanismPublicKeyByteArrayByteArray");

    }

    public final void testRSAverifyMechanismPublicKeyStringByteArray() throws Exception {

        String sourceFilePath = "TestData/rsa/test256.dat";

        boolean verify = session.verify(new Mechanism(Mechanism.SHA256_RSA), RSATestData.userPubKey, new FileInputStream(sourceFilePath), RSATestData.sign);
        Assert.assertTrue("testRSAverifyMechanismPublicKeyStringByteArray", verify);
        System.err.println("testRSAverifyMechanismPublicKeyStringByteArray");

    }

    public final void testRSASignByHash() throws PKIException {
        final RSAPrivateKey rsaPrivateKey = RSATestData.userPriKey;
        final byte[] hash = RSATestData.hash;

        byte[] sign = session.signByHash(new Mechanism(Mechanism.SHA256_RSA), rsaPrivateKey, hash);

        Assert.assertTrue("testRSASignByHash", Arrays.equals(RSATestData.sign, sign));
        System.err.println("testRSASignByHash");

    }

    public final void testRSAVerifyByHash() throws PKIException {
        final RSAPublicKey rsaPublicKey = RSATestData.userPubKey;
        final byte[] hash = RSATestData.hash;
        final byte[] signy = RSATestData.sign;

        boolean verify = session.verifyByHash(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey, hash, signy);
        Assert.assertTrue("testRSAVerifyByHash", verify);
        System.err.println("testRSAVerifyByHash");

    }

    public final void testRSAEncryptMechanismKeyByteArray() throws Exception {

        final byte[] data = RSATestData.data;
        final RSAPrivateKey rsaPrivateKey = RSATestData.userPriKey;
        final RSAPublicKey rsaPublicKey = RSATestData.userPubKey;

        byte[] encryptedData = session.encrypt(new Mechanism(Mechanism.RSA_PKCS), rsaPublicKey, data);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        Assert.assertTrue("testRSAEncryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testRSAEncryptMechanismKeyByteArray");

    }

    public final void testRSADecryptMechanismKeyByteArray() throws Exception {
        final byte[] data = RSATestData.data;
        final RSAPrivateKey rsaPrivateKey = RSATestData.userPriKey;
        final RSAPublicKey rsaPublicKey = RSATestData.userPubKey;

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        final byte[] encryptedData = cipher.doFinal(data);

        final byte[] decryptedData = session.decrypt(new Mechanism(Mechanism.RSA_PKCS), rsaPrivateKey, encryptedData);
        Assert.assertTrue("testRSADecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testRSADecryptMechanismKeyByteArray");

    }

    public final void testSM2SignMechanismPrivateKeyByteArray() throws PKIException {
        byte[] sign = session.sign(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPriKey, SM2TestData.sm2Data);

        boolean result = session.verify(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, SM2TestData.sm2Data, sign);

        Assert.assertTrue("testSM2SignMechanismPrivateKeyByteArray", result);
        System.err.println("testSM2SignMechanismPrivateKeyByteArray");

    }

    public final void testSM2SignMechanismPrivateKeyString() throws Exception {

        String sourceFilePath = "TestData/sm2/test.dat";

        byte[] signData = session.sign(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPriKey, new FileInputStream(sourceFilePath));

        boolean result = session.verify(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, new FileInputStream(sourceFilePath), signData);

        Assert.assertTrue("testSM2SignMechanismPrivateKeyString", result);
        System.err.println("testSM2SignMechanismPrivateKeyString");

    }

    public final void testSM2verifyMechanismPublicKeyByteArrayByteArray() throws PKIException {

        boolean result = session.verify(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, SM2TestData.sm2Data, SM2TestData.signBytes);

        Assert.assertTrue("testSM2verifyMechanismPublicKeyByteArrayByteArray", result);
        System.err.println("testSM2verifyMechanismPublicKeyByteArrayByteArray");

    }

    public final void testSM2verifyMechanismPublicKeyStringByteArray() throws Exception {

        ByteArrayInputStream in = new ByteArrayInputStream(SM2TestData.sm2Data);

        boolean verify = session.verify(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, in, SM2TestData.signBytes);
        Assert.assertTrue("testSM2verifyMechanismPublicKeyStringByteArray", verify);
        System.err.println("testSM2verifyMechanismPublicKeyStringByteArray");

    }

    public final void testSM2SignByHash() throws Exception {

        byte[] sign = session.signByHash(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPriKey, SM2TestData.sm2HashValue);
        boolean result = session.verifyByHash(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, SM2TestData.sm2HashValue, sign);
        Assert.assertTrue("testSM2SignByHash", result);
        System.err.println("testSM2SignByHash");
    }

    public final void testSM2VerifyByHash() throws PKIException {

        boolean verify = session.verifyByHash(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, SM2TestData.sm2HashValue, SM2TestData.signBytes);
        Assert.assertTrue("testSM2VerifyByHash", verify);
        System.err.println("testSM2VerifyByHash");

    }

    public final void testSM2EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = new byte[136];
        new Random().nextBytes(data);
        byte[] encryptedData = session.encrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPubKey, data);

        byte[] decryptedData = session.decrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPriKey, encryptedData);

        Assert.assertTrue("testSM2EncryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));

        BCSoftLib softLib = new BCSoftLib();
        decryptedData = softLib.decrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPriKey, encryptedData);

        Assert.assertTrue("testSM2EncryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testSM2EncryptMechanismKeyByteArray");
    }

    public final void testSM2DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = HexBin
                .decode("8FE7A0301C0B68B2CB076EA29399507D741C745281679ADBEDE9109218E6F29F57C7473FA4A2BCDB842AEAAE093F676925938C147F2BE5DA59BE6648A257C29F0054E89B1454C926836175C23B735AB9FA81DC0AE6AE9B83C5991A8BDE4122531F1C13BAAA4F6E0B34FB573B1872FC79B83768BA3EE3AB441D9330");

        BCSoftLib softLib = new BCSoftLib();
        byte[] encryptedData = softLib.encrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPubKey, data);

        byte[] decryptedData = session.decrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPriKey, encryptedData);
        Assert.assertTrue("testSM2DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));

        encryptedData = HexBin
                .decode("3081E4022100F566F4AB9B2EF1D809E656784CF4DF34ED7D98875F8A6368342727DAB1A38A27022035F2BF80B2393984CBE9AA79507F6EE2B0ED59810588496A071918CB5705B499042010E494E0A1C89B28B41AB90BE11B68DECCE71A0007A18430887A6BAE583D3791047B94F5B7951F7E40A61C7393F106B8215248C6B05EEF1ECAFF95D53B7A8EC4BD07CC0AC3504FE07F75F7E71E291F72349A0EC8A590133651F90A220B641D8653575601CE020C55C1E37F3173E80847EF595C8E2B09725DA2C35D1531F1BB535F3E6934FFFDAC88BF7D15B61E43B667F9038BECE24F2AE26FFFF790EB");

        decryptedData = session.decrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPriKey, encryptedData);
        Assert.assertTrue("testSM2DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));

        ASN1SM2Cipher dest = new ASN1SM2Cipher(encryptedData, SM2EncryptedType.C1_C3_C2_WITH_ASN1);

        //
        encryptedData = dest.getEncryptedBytes(SM2EncryptedType.C1_C3_C2_WITHOUT_0x04);

        decryptedData = session.decrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPriKey, encryptedData);
        Assert.assertTrue("testSM2DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));

        //
        encryptedData = dest.getEncryptedBytes(SM2EncryptedType.C1_C2_C3_WITHOUT_0x04);

        decryptedData = session.decrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPriKey, encryptedData);
        Assert.assertTrue("testSM2DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));

        System.err.println("testSM2DecryptMechanismKeyByteArray");
        
       data = SM2TestData.dataBytes;

      encryptedData = SM2TestData.encryptedBytes;//OLD-VERSION: C1+C2+C3


    }

    public final void testDES3EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] encryptedData = session.encrypt(mechanism, SYMTestData.desKey, data);

        Assert.assertTrue("testDES3DecryptMechanismKeyByteArray", Arrays.equals(encryptedData, SYMTestData.desEncryptedBytes));
        System.err.println("testDES3EncryptMechanismKeyByteArray");
    }

    public final void testDES3DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] encryptedData = SYMTestData.desEncryptedBytes;

        byte[] decryptedData = session.decrypt(mechanism, SYMTestData.desKey, encryptedData);
        Assert.assertTrue("testDES3DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testDES3DecryptMechanismKeyByteArray");
    }

    public final void testDES3EncryptMechanismKeyStringString() throws Exception {

        Mechanism mechanism = SYMTestData.desMechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/sym/test.enc";

        session.encrypt(mechanism, SYMTestData.desKey, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath));

        byte[] encryptedData = FileHelper.read(encryptFilePath);
        
        System.err.println(HexBin.encode(SYMTestData.desEncryptedBytes));
        System.err.println(HexBin.encode(FileHelper.read(encryptFilePath)));

        Assert.assertTrue("testDES3EncryptMechanismKeyStringString", Constants.equals(encryptedData, SYMTestData.desEncryptedBytes));
        System.err.println("testDES3EncryptMechanismKeyStringString");

    }

    public final void tesDES3DecryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/desEncryptedBytes.dat";
        String plainTextFilePath = "TestData/sym/test.dec";

        session.decrypt(mechanism, SYMTestData.desKey, new FileInputStream(encryptFilePath), new FileOutputStream(plainTextFilePath));

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("tesDES3DecryptMechanismKeyStringString", Arrays.equals(decryptedData, data));
        System.err.println("tesDES3DecryptMechanismKeyStringString");
    }

    public final void testRC4EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] encryptedData = session.encrypt(mechanism, SYMTestData.rc4Key, data);

        Assert.assertTrue("testRC4EncryptMechanismKeyByteArray", Arrays.equals(encryptedData, SYMTestData.rc4EncryptedBytes));
        System.err.println("testRC4EncryptMechanismKeyByteArray");
    }

    public final void testRC4DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] encryptedData = SYMTestData.rc4EncryptedBytes;

        byte[] decryptedData = session.decrypt(mechanism, SYMTestData.rc4Key, encryptedData);
        Assert.assertTrue("testRC4DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testRC4DecryptMechanismKeyByteArray");
    }

    public final void testRC4EncryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/sym/test.enc";

        session.encrypt(mechanism, SYMTestData.rc4Key, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath));

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testRC4EncryptMechanismKeyStringString", Arrays.equals(encryptedData, SYMTestData.rc4EncryptedBytes));
        System.err.println("testRC4EncryptMechanismKeyStringString");
    }

    public final void tesRC4DecryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/rc4EncryptedBytes.dat";
        String plainTextFilePath = "TestData/sym/test.dec";

        session.decrypt(mechanism, SYMTestData.rc4Key, new FileInputStream(encryptFilePath), new FileOutputStream(plainTextFilePath));

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("tesRC4DecryptMechanismKeyStringString", Arrays.equals(decryptedData, data));
        System.err.println("tesRC4DecryptMechanismKeyStringString");
    }

    public final void testSM4EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] encryptedData = session.encrypt(mechanism, SYMTestData.sm4Key, data);

        Assert.assertTrue("testSM4EncryptMechanismKeyByteArray", Arrays.equals(encryptedData, SYMTestData.sm4EncryptedBytes));

        byte[] decryptedData = session.decrypt(mechanism, SYMTestData.sm4Key, encryptedData);
        Assert.assertTrue("testSM4EncryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testSM4EncryptMechanismKeyByteArray");

    }

    public final void testSM4DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] encryptedData = SYMTestData.sm4EncryptedBytes;

        byte[] decryptedData = session.decrypt(mechanism, SYMTestData.sm4Key, encryptedData);
        Assert.assertTrue("testSM4DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        System.err.println("testSM4DecryptMechanismKeyByteArray");
    }

    public final void testSM4EncryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/sym/test.enc";

        session.encrypt(mechanism, SYMTestData.sm4Key, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath));

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testSM4EncryptMechanismKeyStringString", Constants.equals(encryptedData, SYMTestData.sm4EncryptedBytes));
        System.err.println("testSM4EncryptMechanismKeyStringString");
    }

    public final void testSM4DecryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/sm4EncryptedBytes.dat";
        String plainTextFilePath = "TestData/sym/test.dec";

        session.decrypt(mechanism, SYMTestData.sm4Key, new FileInputStream(encryptFilePath), new FileOutputStream(plainTextFilePath));

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("testSM4DecryptMechanismKeyStringString", Constants.equals(decryptedData, data));
        System.err.println("testSM4DecryptMechanismKeyStringString");
    }

    public final void testGenerateKey() throws PKIException {
        session.generateKey(new Mechanism(Mechanism.DES3_KEY));
        session.generateKey(new Mechanism(Mechanism.RC4_KEY));
        session.generateKey(new Mechanism(Mechanism.SM4_KEY));

        Assert.assertTrue("testGenerateKey", true);
        System.err.println("testGenerateKey");
    }

}
