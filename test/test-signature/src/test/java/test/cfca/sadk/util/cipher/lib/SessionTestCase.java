package test.cfca.sadk.util.cipher.lib;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import javax.crypto.Cipher;

import org.junit.Assert;

import junit.framework.TestCase;
import test.cfca.sadk.testdata.RSATestData;
import test.cfca.sadk.testdata.SM2TestData;
import test.cfca.sadk.testdata.SYMTestData;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.algorithm.sm2.SM2PrivateKey;
import cfca.sadk.algorithm.sm2.SM2PublicKey;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.lib.crypto.bcsoft.BCSoftLib;
import cfca.sadk.org.bouncycastle.math.ec.ECPoint;
import cfca.sadk.system.FileHelper;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class SessionTestCase extends TestCase {

    Session session = null;

    protected void setUp() throws Exception {
        session = new BCSoftLib();
    }

    protected void tearDown() throws Exception {
        session = null;
    }

    public final void testGenerateKeyPair() throws Exception {

        session.generateKeyPair(new Mechanism(Mechanism.RSA), 1024);
        session.generateKeyPair(new Mechanism(Mechanism.RSA), 2048);

        KeyPair keyPair = session.generateKeyPair(new Mechanism(Mechanism.SM2), 256);

        ECPoint Q = ((SM2PublicKey) keyPair.getPublic()).getQ().normalize();
        Assert.assertTrue("testGenerateKeyPairD", ((SM2PrivateKey) keyPair.getPrivate()).getDByInt().bitLength() <= 256);
        Assert.assertTrue("testGenerateKeyPairX", Q.getXCoord().toBigInteger().bitLength() > 248);
        Assert.assertTrue("testGenerateKeyPairY", Q.getYCoord().toBigInteger().bitLength() > 248);

        FileHelper.write("TestData/demo/SM2PublicKey.der", keyPair.getPublic().getEncoded());
        FileHelper.write("TestData/demo/SM2PrivateKey.der", keyPair.getPrivate().getEncoded());

        System.err.println(HexBin.encode(((SM2PrivateKey) keyPair.getPrivate()).getEncoding()));

    }

    public final void testRSASignMechanismPrivateKeyByteArray() throws PKIException {
        // RSA-SHA256
        byte[] signx = session.sign(new Mechanism(Mechanism.SHA256_RSA), RSATestData.userPriKey, RSATestData.data);
        Assert.assertTrue("testRSASignMechanismPrivateKeyByteArray", Arrays.equals(signx, RSATestData.sign));

    }

    public final void testRSASignMechanismPrivateKeyString() throws Exception {
        // RSA-SHA256
        String sourceFilePath = "TestData/rsa/test256.dat";
        byte[] signx = session.sign(new Mechanism(Mechanism.SHA256_RSA), RSATestData.userPriKey, new FileInputStream(sourceFilePath));
        Assert.assertTrue("testRSASignMechanismPrivateKeyString", Arrays.equals(signx, RSATestData.sign));

    }

    public final void testRSAverifyMechanismPublicKeyByteArrayByteArray() throws PKIException {
        // RSA-SHA256
        boolean verify = session.verify(new Mechanism(Mechanism.SHA256_RSA), RSATestData.userPubKey, RSATestData.data, RSATestData.sign);
        Assert.assertTrue("testverifyMechanismPublicKeyByteArrayByteArray", verify);

    }

    public final void testRSAverifyMechanismPublicKeyStringByteArray() throws Exception {

        String sourceFilePath = "TestData/rsa/test256.dat";

        boolean verify = session.verify(new Mechanism(Mechanism.SHA256_RSA), RSATestData.userPubKey, new FileInputStream(sourceFilePath), RSATestData.sign);
        Assert.assertTrue("testRSAverifyMechanismPublicKeyStringByteArray", verify);

    }

    public final void testRSASignByHash() throws PKIException {
        final RSAPrivateKey rsaPrivateKey = RSATestData.userPriKey;
        final byte[] hash = RSATestData.hash;

        byte[] sign = session.signByHash(new Mechanism(Mechanism.SHA256_RSA), rsaPrivateKey, hash);

        Assert.assertTrue("testRSASignByHash", Arrays.equals(RSATestData.sign, sign));

    }

    public final void testRSAVerifyByHash() throws PKIException {
        final RSAPublicKey rsaPublicKey = RSATestData.userPubKey;
        final byte[] hash = RSATestData.hash;
        final byte[] signy = RSATestData.sign;

        boolean verify = session.verifyByHash(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey, hash, signy);
        Assert.assertTrue("testRSAVerifyByHash", verify);

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

    }

    public final void testSM2SignMechanismPrivateKeyByteArray() throws PKIException {
        byte[] sign = session.sign(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPriKey, SM2TestData.sm2Data);

        boolean result = session.verify(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, SM2TestData.sm2Data, sign);

        Assert.assertTrue("testSM2SignMechanismPrivateKeyByteArray", result);

    }

    public final void testSM2SignMechanismPrivateKeyString() throws Exception {

        String sourceFilePath = "TestData/sm2/test.dat";

        byte[] signData = session.sign(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPriKey, new FileInputStream(sourceFilePath));

        boolean result = session.verify(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, new FileInputStream(sourceFilePath), signData);

        Assert.assertTrue("testSM2SignMechanismPrivateKeyString", result);

    }

    public final void testSM2verifyMechanismPublicKeyByteArrayByteArray() throws PKIException {

        boolean result = session.verify(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, SM2TestData.sm2Data, SM2TestData.signBytes);

        Assert.assertTrue("testSM2verifyMechanismPublicKeyByteArrayByteArray", result);

    }

    public final void testSM2verifyMechanismPublicKeyStringByteArray() throws Exception {

        ByteArrayInputStream in = new ByteArrayInputStream(SM2TestData.sm2Data);

        boolean verify = session.verify(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, in, SM2TestData.signBytes);
        Assert.assertTrue("testSM2verifyMechanismPublicKeyStringByteArray", verify);

    }

    public final void testSM2SignByHash() throws PKIException {

        byte[] sign = session.signByHash(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPriKey, SM2TestData.sm2HashValue);
        boolean result = session.verifyByHash(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, SM2TestData.sm2HashValue, sign);
        Assert.assertTrue("testSM2SignByHash", result);
    }

    public final void testSM2VerifyByHash() throws PKIException {

        boolean verify = session.verifyByHash(new Mechanism(Mechanism.SM3_SM2), SM2TestData.userPubKey, SM2TestData.sm2HashValue, SM2TestData.signBytes);
        Assert.assertTrue("testSM2VerifyByHash", verify);

    }

    public final void testSM2EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = SM2TestData.dataBytes;
        byte[] encryptedData = session.encrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPubKey, data);

        byte[] decryptedData = session.decrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPriKey, encryptedData);

        Assert.assertTrue("testSM2EncryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
    }

    public final void testSM2DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = SM2TestData.dataBytes;

        byte[] encryptedData = SM2TestData.encryptedBytes;//OLD-VERSION: C1+C2+C3

        byte[] decryptedData = session.decrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPriKey, encryptedData);
        Assert.assertTrue("testSM2DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
    }

    public final void testDES3EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] encryptedData = session.encrypt(mechanism, SYMTestData.desKey, data);

        Assert.assertTrue("testDES3DecryptMechanismKeyByteArray", Arrays.equals(encryptedData, SYMTestData.desEncryptedBytes));
    }

    public final void testDES3DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] encryptedData = SYMTestData.desEncryptedBytes;

        byte[] decryptedData = session.decrypt(mechanism, SYMTestData.desKey, encryptedData);
        Assert.assertTrue("testDES3DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
    }

    public final void testDES3EncryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.desMechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/sym/test.enc";

        session.encrypt(mechanism, SYMTestData.desKey, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath));

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testDES3EncryptMechanismKeyStringString", Arrays.equals(encryptedData, SYMTestData.desEncryptedBytes));
    }

    public final void tesDES3DecryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/desEncryptedBytes.dat";
        String plainTextFilePath = "TestData/sym/test.dec";

        session.decrypt(mechanism, SYMTestData.desKey, new FileInputStream(encryptFilePath), new FileOutputStream(plainTextFilePath));

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("tesDES3DecryptMechanismKeyStringString", Arrays.equals(decryptedData, data));
    }

    public final void testRC4EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] encryptedData = session.encrypt(mechanism, SYMTestData.rc4Key, data);

        Assert.assertTrue("testRC4EncryptMechanismKeyByteArray", Arrays.equals(encryptedData, SYMTestData.rc4EncryptedBytes));
    }

    public final void testRC4DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] encryptedData = SYMTestData.rc4EncryptedBytes;

        byte[] decryptedData = session.decrypt(mechanism, SYMTestData.rc4Key, encryptedData);
        Assert.assertTrue("testRC4DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
    }

    public final void testRC4EncryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/sym/test.enc";

        session.encrypt(mechanism, SYMTestData.rc4Key, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath));

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testRC4EncryptMechanismKeyStringString", Arrays.equals(encryptedData, SYMTestData.rc4EncryptedBytes));
    }

    public final void tesRC4DecryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/rc4EncryptedBytes.dat";
        String plainTextFilePath = "TestData/sym/test.dec";

        session.decrypt(mechanism, SYMTestData.rc4Key, new FileInputStream(encryptFilePath), new FileOutputStream(plainTextFilePath));

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("tesRC4DecryptMechanismKeyStringString", Arrays.equals(decryptedData, data));
    }

    public final void testSM4EncryptMechanismKeyByteArray() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] encryptedData = session.encrypt(mechanism, SYMTestData.sm4Key, data);

        Assert.assertTrue("testSM4EncryptMechanismKeyByteArray", Arrays.equals(encryptedData, SYMTestData.sm4EncryptedBytes));
    }

    public final void testSM4DecryptMechanismKeyByteArray() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] encryptedData = SYMTestData.sm4EncryptedBytes;

        byte[] decryptedData = session.decrypt(mechanism, SYMTestData.sm4Key, encryptedData);
        Assert.assertTrue("testSM4DecryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
    }

    public final void testSM4EncryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/sym/test.enc";

        session.encrypt(mechanism, SYMTestData.sm4Key, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath));

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testSM4EncryptMechanismKeyStringString", Arrays.equals(encryptedData, SYMTestData.sm4EncryptedBytes));
    }

    public final void testSM4DecryptMechanismKeyStringString() throws Exception {
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/sm4EncryptedBytes.dat";
        String plainTextFilePath = "TestData/sym/test.dec";

        session.decrypt(mechanism, SYMTestData.sm4Key, new FileInputStream(encryptFilePath), new FileOutputStream(plainTextFilePath));

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("testSM4DecryptMechanismKeyStringString", Arrays.equals(decryptedData, data));
    }

    public final void testGenerateKey() throws PKIException {
        session.generateKey(new Mechanism(Mechanism.DES3_KEY));
        session.generateKey(new Mechanism(Mechanism.RC4_KEY));
        session.generateKey(new Mechanism(Mechanism.SM4_KEY));

        Assert.assertTrue("testGenerateKey", true);
    }

    public static void main(String[] args) throws Exception {
        final SessionTestCase test = new JNISoftLibTestCase();
        test.setUp();

        final byte[] data = RSATestData.data;
        final RSAPublicKey rsaPublicKey = RSATestData.userPubKey;
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        final byte[] encryptedData = cipher.doFinal(data);

        long xTime = System.currentTimeMillis();
        final AtomicInteger num = new AtomicInteger(0);

        final byte[] keyBytes = HexBin
                .decode("308204A40201000282010100EFF9C88984FEE2904E11A67A9BF626F7671E272FE7115D333C2E456299402E18F7FD73C0F93D9A1B6EA3122E116DE31D94885BC3B3A10D7C14B444F09BF11F48ECAEAE9B4A64253DF8345EDF08AFE7D1DD6A116C9BA468C17218B56FC143A9098FF0CAA9AE3D62B988204B0B5DACC8B04622630CAFC35845124AFEA36FFFB27ED0A14F125878A007E3B49203B5D78E15356C33FBF2216401D9AEDAD33B007E157AB0FE84B307E35D7375D96C8BB88DF7522F3DCED8570A36067A6928FD47CF3084AA53F1527E9B90D0DB9AF4A8AC58F1BC28D7BB22E57460A04ECD8033E7ADB5B68E834EB8078ADF4F1E8517304DF8D7C9D487D8C9102DB95DFFD0820E5BFB410203010001028201002CCFCA00D412FC9FD1D43DF03B5B75DF5124325121A6FE6E68D883206C59ADC1FFDD16567CB46228A93A4E54C7F06B2EC4E3C9AEFBC6214C8059415E64E05BE5343D5261B13C8E2FCA3F8D4FBCC18C5A862C1E60D79795F039AC1F8564E38B973B87CD706E170C6E74DB02E8766FA372374688947FE5CC7FE97093A8556BBC1AD1AAA28992155463461DF46C975E32B0A0850334BC18BD74D730BEF6B5A4AA80F55A1990BFBB7A857970D910A0A7F1C6AC0F2E386DF6F0EA64E3BB458AB382A80C6B6A94F09737AE7E930A372657F3C79B5631CE31BD604F3C87348D769177AA9BFE2580BEB003347B575A5144ED62DF7239E24CB67DC0FA8DCF63892CE5ADA102818100FB741F018C3FA34DF6242BD386CBFC917E08E52C37F11AA64B68C976294A75F3D105CF4DA128A12DE35FF6B5668EB6588E5ACC95BD836C2F04F322966BFD858B82CA8B48A92250183783A57E8D526F0D0364DF54871B03EA045E5FEBD7418B85955224AA91AF970875B0A37A57C239FD24E08D516ED259883A7A1E29F92432F502818100F4508921CFF8169C56B7613D734E76E394CBDD300CE941C08958790BFA00AA6CB89FC91D85AC00C658046DD5864BF8DE1C066E785104C1DA8994534E5FBBB8903BD457661DCA11B34A72ED574D9DBA8B15703B14A8DA5617DF74669EE1A10647FBE6C82D5439098AD1FC57D4AC0F3E3AE3588B257ABD310587388570F77EEF9D0281810084A485B2A053F5862BA07B09F32269D229E4F157390F535A838EFC2EFBAB0EBB95F8759962B8F70528DB375E0B829EC7EA1815B1ABADC704AA9CC3130C5648FB05FC682AE5257790BEC0102DC8C8C251E0077713B4CD586C3AEFB9B3C3F20C72640957D42E53ACDD468FEE3C9C4C95A066B299796A7745C969E77F3FEEEFDC7D0281810093EB3ACBB4FDB0BAE129CFFF03940B19491A9105A99B844ACD1D3AD48C7DE0B148AABD7A59574574D10F856C550E24545DC56B16FF7E90DBFE964645DA626EA12D08280E5494E6589607B539396DF8E682895BCBEB5B9DEA80491A64B198EB93A3A91BA38E2DA6605D3899F8D25FBC3EAD2D0BBA1A70F780305981395F2F8465028180623B9D66C4B3D55A735E588C8CB5CE70E062497649681011D0AAAB743F710C0B7C39FBD3D5903830B81BE0CB6805FDC416E3445B427589FAB17716733D88B49DD22AF8F46E54631D4DD8ACA9E5923E7F6233B8FE43357679BA7DE9BB284036A16F82A9822AB91FBC73EFE3FABF46B54CCB75655B118FB658B45049FC6EC00043");

        for (int i = 0; i < 16; i++) {
            Thread r = new Thread(new Runnable() {
                public void run() {
                    final byte[] output = new byte[256];
                    while (true)
                        try {
                            // test.session.decrypt(new
                            // Mechanism(Mechanism.RSA_PKCS), rsaPrivateKey,
                            // encryptedData);

                            cryptokit.jni.JNIRSA.dowithPrivateKey(encryptedData, keyBytes, output);

                            num.getAndIncrement();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }

            });
            r.setDaemon(true);
            r.start();
        }
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long yTime = System.currentTimeMillis();
        System.err.println(num.get() * 1000 / (yTime - xTime));
    }

}
