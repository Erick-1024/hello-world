package test.cfca.sadk.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.sm2.SM2PrivateKey;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.sadk.util.EncryptUtil;
import cfca.sadk.x509.certificate.X509Cert;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 *
 */
public final class EncryptUtilTestCase extends TestCase {

    Session session = null;

    String pwd = null;

    byte[] sm4iv;
    byte[] sm4key;

    PrivateKey rsapriKey;
    PublicKey rsapubKey;
    X509Cert rsacert;
    String pfxcertFilePath = "TestData/rsa/test.cer";
    String pfxFilePath = "TestData/rsa/test.p12";
    String pfxFilePwd = "123123";

    PrivateKey sm2priKey;
    PublicKey sm2pubKey;
    X509Cert sm2cert;

    String sm2certFilePath = "TestData/sm2/test.cer";
    String sm2FilePath = "TestData/sm2/test.sm2";
    String sm2FilePwd = "123123";

    protected void setUp() throws Exception {
        session = TestReady.openSession();

        byte[] password = new byte[32];
        Arrays.fill(password, (byte) 0x11);
        pwd = HexBin.encode(password);

        sm4iv = new byte[16];
        Arrays.fill(sm4iv, (byte) 0x88);
        sm4key = new byte[16];
        Arrays.fill(sm4key, (byte) 0x88);

        rsapriKey = RSATestData.userPriKey;
        rsapubKey = RSATestData.userPubKey;
        rsacert = RSATestData.UserCert;

        sm2priKey = SM2TestData.userPriKey;
        sm2pubKey = SM2TestData.userPubKey;
        sm2cert = SM2TestData.UserCert;
    }

    protected void tearDown() throws Exception {
        session = null;
    }

    public void testEncryptMessageByDES3() throws Exception {

        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptedFilePath = "./TestData/sym/test-des3-pwd-encrypt.enc";

        byte[] encryptedBase64Bytes = FileHelper.read(encryptedFilePath);

        String encryptedBase64Text = new String(encryptedBase64Bytes);

        byte[] dataBytes = FileHelper.read(sourceFilePath);
        String dataText = Base64.toBase64String(dataBytes);

        String base64EncryptedText = EncryptUtil.encryptMessageByDES3(dataText, pwd);

        assertTrue("testDecryptMessageByDES3", base64EncryptedText.equals(encryptedBase64Text));

        String base64DecryptedText = EncryptUtil.decryptMessageByDES3(encryptedBase64Text, pwd);

        assertTrue("testDecryptMessageByDES3", base64DecryptedText.equals(dataText));

    }

    public void testDecryptMessageByDES3() throws Exception {

        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptedFilePath = "./TestData/sym/test-des3-pwd-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);
        String dataText = Base64.toBase64String(dataBytes);

        byte[] encryptedBase64Bytes = FileHelper.read(encryptedFilePath);

        String encryptedBase64Text = new String(encryptedBase64Bytes);

        String base64DecryptedText = EncryptUtil.decryptMessageByDES3(encryptedBase64Text, pwd);

        assertTrue("testDecryptMessageByDES3", base64DecryptedText.equals(dataText));
    }

    public void testEncryptFileByDES3() throws Exception {

        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptFilePath = "./TestData/out/test.des.tmp";
        EncryptUtil.encryptFileByDES3(sourceFilePath, encryptFilePath, pwd);

        byte[] encx = FileHelper.read("./TestData/sym/test_des3.enc");
        byte[] ency = FileHelper.read(encryptFilePath);

        assertTrue("testEncryptFileByDES3", Arrays.equals(encx, ency));
    }

    public void testDecryptFileByDES3StringStringString() throws Exception {
        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptFilePath = "./TestData/sym/test_des3.enc";
        String plainTextFilePath = "./TestData/out/test.dec.tmp";
        EncryptUtil.decryptFileByDES3(encryptFilePath, plainTextFilePath, pwd);

        byte[] dat = FileHelper.read(sourceFilePath);
        byte[] dec = FileHelper.read(plainTextFilePath);

        assertTrue("testDecryptFileByDES3StringStringString", Arrays.equals(dat, dec));
    }

    public void testDecryptFileByDES3StringByteArrayOutputStreamString() throws Exception {
        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptFilePath = "./TestData/sym/test_des3.enc";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        EncryptUtil.decryptFileByDES3(encryptFilePath, out, pwd);

        byte[] dat = FileHelper.read(sourceFilePath);
        byte[] dec = out.toByteArray();

        assertTrue("testDecryptFileByDES3StringByteArrayOutputStreamString", Arrays.equals(dat, dec));
    }

    public void testEncryptMessageBySM4ByteArrayString() throws Exception {
        String sourceFilePath = "./TestData/sym/test.bin";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] base64 = EncryptUtil.encryptMessageBySM4(dataBytes, pwd);

        byte[] decryptedBytes = EncryptUtil.decryptMessageBySM4(base64, pwd);

        assertTrue("testEncryptMessageBySM4ByteArrayString", Arrays.equals(dataBytes, decryptedBytes));
    }

    public void testDecryptMessageBySM4ByteArrayString() throws Exception {
        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptedFilePath = "./TestData/sym/test-sm4-pwd-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] base64 = FileHelper.read(encryptedFilePath);

        byte[] decryptedBytes = EncryptUtil.decryptMessageBySM4(base64, pwd);

        assertTrue("testEncryptMessageBySM4ByteArrayString", Arrays.equals(dataBytes, decryptedBytes));
    }

    public void testEncryptMessageBySM4ByteArrayByteArrayByteArray() throws Exception {
        String sourceFilePath = "./TestData/sym/test.bin";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] base64 = EncryptUtil.encryptMessageBySM4(dataBytes, sm4iv, sm4key);

        byte[] decryptedBytes = EncryptUtil.decryptMessageBySM4(base64, sm4iv, sm4key);

        assertTrue("testEncryptMessageBySM4ByteArrayString", Arrays.equals(dataBytes, decryptedBytes));
    }

    public void testDecryptMessageBySM4ByteArrayByteArrayByteArray() throws Exception {
        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptedFilePath = "./TestData/sym/test-sm4-key-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] base64 = FileHelper.read(encryptedFilePath);

        byte[] decryptedBytes = EncryptUtil.decryptMessageBySM4(base64, sm4iv, sm4key);

        assertTrue("testDecryptMessageBySM4ByteArrayByteArrayByteArray", Arrays.equals(dataBytes, decryptedBytes));
    }

    public void testEncryptFileBySM4() throws Exception {
        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptFilePath = "./TestData/out/test.sm4.tmp";
        EncryptUtil.encryptFileBySM4(sourceFilePath, encryptFilePath, pwd);
        byte[] encx = FileHelper.read("./TestData/sym/test_sm4.enc");
        byte[] ency = FileHelper.read(encryptFilePath);

        assertTrue("testEncryptFileBySM4", Arrays.equals(encx, ency));
    }

    public void testDecryptFileBySM4StringStringString() throws Exception {
        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptFilePath = "./TestData/sym/test_sm4.enc";
        String plainTextFilePath = "./TestData/out/test.sm4.tmp";
        EncryptUtil.decryptFileBySM4(encryptFilePath, plainTextFilePath, pwd);

        byte[] dat = FileHelper.read(sourceFilePath);
        byte[] dec = FileHelper.read(plainTextFilePath);

        assertTrue("testDecryptFileBySM4StringStringString", Arrays.equals(dat, dec));
    }

    public void testDecryptFileBySM4StringByteArrayOutputStreamString() throws Exception {
        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptFilePath = "./TestData/sym/test_sm4.enc";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        EncryptUtil.decryptFileBySM4(encryptFilePath, out, pwd);

        byte[] dat = FileHelper.read(sourceFilePath);
        byte[] dec = out.toByteArray();

        assertTrue("testDecryptFileBySM4StringByteArrayOutputStreamString", Arrays.equals(dat, dec));
    }

    public void testDecryptFileBySM4StringByteArrayString() throws Exception {
        String sourceFilePath = "./TestData/sym/test.bin";
        String encryptFilePath = "./TestData/sym/test_sm4.enc";

        File file = new File(encryptFilePath);

        byte[] plainTextBytes = new byte[(int) file.length()];

        int length = EncryptUtil.decryptFileBySM4(encryptFilePath, plainTextBytes, pwd);

        byte[] dat = FileHelper.read(sourceFilePath);
        byte[] dec = new byte[length];

        System.arraycopy(plainTextBytes, 0, dec, 0, dec.length);

        assertTrue("testDecryptFileBySM4StringByteArrayString", Arrays.equals(dat, dec));
    }

    public void testEncryptMessageByRSAByteArrayStringSession() throws Exception {

        byte[] sourceData = RSATestData.data;

        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageByRSA(sourceData, pfxcertFilePath, session);

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageByRSA(base64EncryptedBytes, rsapriKey, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayStringSession", Arrays.equals(sourceData, base64DecryptedBytes));

    }

    public void testEncryptMessageByRSAByteArrayX509CertSession() throws Exception {
        byte[] sourceData = RSATestData.data;
        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageByRSA(sourceData, rsacert, session);

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageByRSA(base64EncryptedBytes, rsapriKey, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayX509CertSession", Arrays.equals(sourceData, base64DecryptedBytes));
    }

    public void testEncryptMessageByRSAByteArrayKeySession() throws Exception {

        byte[] sourceData = RSATestData.data;
        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageByRSA(sourceData, rsapubKey, session);

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageByRSA(base64EncryptedBytes, rsapriKey, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayStringSession", Arrays.equals(sourceData, base64DecryptedBytes));

        byte[] base64DecryptedBytes2 = EncryptUtil.decrypt(new Mechanism(Mechanism.RSA_PKCS), rsapriKey, base64EncryptedBytes, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayStringSession", Arrays.equals(sourceData, base64DecryptedBytes2));

    }

    public void testDecryptMessageByRSAByteArrayStringStringSession() throws Exception {

        final String base64EncryptedText = "hYM+4gZj5hCXlqQOxTpTI1qUNkAcCaKu3Sm4s1HGPdPNAM8wLIBzs8aHRrkDlp9U3o0Wm6UKmm9aKFzIWLE74Q+CH7fhQsMaQRM48zKOgsgKYXNMkbcIra8D85mtxO0XG5tWsjTvzLU2f8YLV0yAxICPIaHzqyl+giuf+XqkiCFeC+QD5i3bwMdew03Hd6A7C752oWYno5lRdGn5vxK+iT9U2X/YvMADjFLa9WeJQsWnnSWPhq1pv/Hp1PGAG/xTC22MbibaBuXEtOgjQBEVUYDIeZbGlDVeFPqwGaCHvHpjTT3m0QSnf6hpC+Gz7v0iFwlt2RuylW1LQhHrLvfgWw==";

        byte[] sourceData = RSATestData.data;
        byte[] base64EncryptedBytes = base64EncryptedText.getBytes();

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageByRSA(base64EncryptedBytes, pfxFilePath, pfxFilePwd, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayKeySession", Arrays.equals(sourceData, base64DecryptedBytes));

        byte[] base64EncryptedByte2 = EncryptUtil.encrypt(new Mechanism(Mechanism.RSA_PKCS), rsapubKey, sourceData, session);

        byte[] base64DecryptedBytes2 = EncryptUtil.decryptMessageByRSA(base64EncryptedByte2, pfxFilePath, pfxFilePwd, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayKeySession", Arrays.equals(sourceData, base64DecryptedBytes2));

    }

    public void testDecryptMessageByRSAByteArrayKeySession() throws Exception {
        final String base64EncryptedText = "hYM+4gZj5hCXlqQOxTpTI1qUNkAcCaKu3Sm4s1HGPdPNAM8wLIBzs8aHRrkDlp9U3o0Wm6UKmm9aKFzIWLE74Q+CH7fhQsMaQRM48zKOgsgKYXNMkbcIra8D85mtxO0XG5tWsjTvzLU2f8YLV0yAxICPIaHzqyl+giuf+XqkiCFeC+QD5i3bwMdew03Hd6A7C752oWYno5lRdGn5vxK+iT9U2X/YvMADjFLa9WeJQsWnnSWPhq1pv/Hp1PGAG/xTC22MbibaBuXEtOgjQBEVUYDIeZbGlDVeFPqwGaCHvHpjTT3m0QSnf6hpC+Gz7v0iFwlt2RuylW1LQhHrLvfgWw==";

        byte[] sourceData = RSATestData.data;
        byte[] base64EncryptedBytes = base64EncryptedText.getBytes();

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageByRSA(base64EncryptedBytes, rsapriKey, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayKeySession", Arrays.equals(sourceData, base64DecryptedBytes));
    }

    public void testEncryptMessageBySM2ByteArrayStringSession() throws Exception {

        byte[] sourceData = SM2TestData.dataBytes;
        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageBySM2(sourceData, sm2certFilePath, session);

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageBySM2(base64EncryptedBytes, sm2priKey, session);

        Assert.assertTrue("testEncryptMessageBySM2ByteArrayStringSession", Arrays.equals(sourceData, base64DecryptedBytes));

        byte[] base64DecryptedBytes2 = EncryptUtil.decrypt(new Mechanism(Mechanism.SM2), sm2priKey, base64EncryptedBytes, session);

        Assert.assertTrue("testEncryptMessageBySM2ByteArrayStringSession", Arrays.equals(sourceData, base64DecryptedBytes2));

    }

    public void testEncryptMessageBySM2ByteArrayX509CertSession() throws Exception {

        byte[] sourceData = SM2TestData.dataBytes;
        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageBySM2(sourceData, sm2cert, session);

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageBySM2(base64EncryptedBytes, sm2priKey, session);

        Assert.assertTrue("testEncryptMessageBySM2ByteArrayX509CertSession", Arrays.equals(sourceData, base64DecryptedBytes));

        byte[] base64EncryptedByte2 = EncryptUtil.encrypt(new Mechanism(Mechanism.SM2), sm2pubKey, sourceData, session);

        byte[] base64DecryptedBytes2 = EncryptUtil.decryptMessageBySM2(base64EncryptedByte2, sm2priKey, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayKeySession", Arrays.equals(sourceData, base64DecryptedBytes2));

    }

    public void testEncryptMessageBySM2ByteArrayKeySession() throws Exception {

        byte[] sourceData = SM2TestData.dataBytes;
        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageBySM2(sourceData, sm2pubKey, session);

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageBySM2(base64EncryptedBytes, sm2priKey, session);

        Assert.assertTrue("testEncryptMessageBySM2ByteArrayKeySession", Arrays.equals(sourceData, base64DecryptedBytes));
    }

    public void testDecryptMessageBySM2ByteArrayStringStringSession() throws Exception {
        final String base64EncryptedText = "MIICNwIhAO9pJUmms1vYjeBXenNaz77W3P1dpaE5jC3uJ7lpgwUBAiA9BNl2ebOfJi/zpH3QEo6XwYoWrfUJdz6euJyb4QV9KgQgqyoxLKMKOqQjesx2OPNz4lpCh3+Nx3XiI06XewBmy2YEggHMp/Fj1qhiH/zzmi8WfVAZQuSeVkJdMcuP6Bj0Lf+sEV4/eCU6HlBHylqe3GRHBS1e5HtQhy/P/VQrBHJfdabmD/XGxiRriNcnxJ5rw5PSykw/MNj/7hRat6bMEOIZna0qmBi/AjmaeMnWN1e4fe/F3tj2MPTH1qe4k7UcBXQYgYiPvpv8hQBX2hVYu4YTFrMq3Y0JZcpf0VSpdmMJ2RjmgCPeI3vBfm2FXTkbpZ+odoZqFj/jtxhvT/SD3/yEt28UeTNSG0ZE41dYWv22O/dS7aNEsG+KpWmqbTnpc0rarFHqAhCxwPybOM3ZLVDlmGqi2eaE3HxzxiFIaeMh4qQ0y705ot8T+5pmukP7JZE/9ILda4cy+RqD72GzEMQEsLLfUBSiga/NjEeADEhuzvMny48Hp3Ld/WubsrQZWMdcEuhfHHi7Na4Ao69t3K2sNB9PtLAlm4z3+tlM4cXARz3ju2vTaHo5A/gar4gTWax+m6R7xegaECf2VNzjfhEuCIO4noeVAiAP5aTzqxQTZEMDN9OMM6EsmNYv3bwpmDT3wUlcfQeqQJWqh3JP2iBVm9UbowpP+r6n5kMyqMQ+EzwpoE9YyNz7edoj/FBo+w==";
        byte[] sourceData = SM2TestData.dataBytes;
        byte[] base64EncryptedBytes = base64EncryptedText.getBytes();

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageBySM2(base64EncryptedBytes, sm2FilePath, sm2FilePwd, session);

        Assert.assertTrue("testDecryptMessageBySM2ByteArrayStringStringSession", Arrays.equals(sourceData, base64DecryptedBytes));
    }

    public void testDecryptMessageBySM2ByteArrayKeySession() throws Exception {
        byte[] sourceData = SM2TestData.dataBytes;
        {
            // ASN1(C1+C3+C2)
            final String base64EncryptedText = "MIICNwIhAO9pJUmms1vYjeBXenNaz77W3P1dpaE5jC3uJ7lpgwUBAiA9BNl2ebOfJi/zpH3QEo6XwYoWrfUJdz6euJyb4QV9KgQgqyoxLKMKOqQjesx2OPNz4lpCh3+Nx3XiI06XewBmy2YEggHMp/Fj1qhiH/zzmi8WfVAZQuSeVkJdMcuP6Bj0Lf+sEV4/eCU6HlBHylqe3GRHBS1e5HtQhy/P/VQrBHJfdabmD/XGxiRriNcnxJ5rw5PSykw/MNj/7hRat6bMEOIZna0qmBi/AjmaeMnWN1e4fe/F3tj2MPTH1qe4k7UcBXQYgYiPvpv8hQBX2hVYu4YTFrMq3Y0JZcpf0VSpdmMJ2RjmgCPeI3vBfm2FXTkbpZ+odoZqFj/jtxhvT/SD3/yEt28UeTNSG0ZE41dYWv22O/dS7aNEsG+KpWmqbTnpc0rarFHqAhCxwPybOM3ZLVDlmGqi2eaE3HxzxiFIaeMh4qQ0y705ot8T+5pmukP7JZE/9ILda4cy+RqD72GzEMQEsLLfUBSiga/NjEeADEhuzvMny48Hp3Ld/WubsrQZWMdcEuhfHHi7Na4Ao69t3K2sNB9PtLAlm4z3+tlM4cXARz3ju2vTaHo5A/gar4gTWax+m6R7xegaECf2VNzjfhEuCIO4noeVAiAP5aTzqxQTZEMDN9OMM6EsmNYv3bwpmDT3wUlcfQeqQJWqh3JP2iBVm9UbowpP+r6n5kMyqMQ+EzwpoE9YyNz7edoj/FBo+w==";

            byte[] base64EncryptedBytes = base64EncryptedText.getBytes();

            byte[] base64DecryptedBytes = EncryptUtil.decryptMessageBySM2(base64EncryptedBytes, sm2priKey, session);

            Assert.assertTrue("testDecryptMessageBySM2ByteArrayKeySession", Arrays.equals(sourceData, base64DecryptedBytes));

        }

        {// RAW(C1+C3+C2)
            final String base64EncryptedText = "72klSaazW9iN4Fd6c1rPvtbc/V2loTmMLe4nuWmDBQE9BNl2ebOfJi/zpH3QEo6XwYoWrfUJdz6euJyb4QV9KqsqMSyjCjqkI3rMdjjzc+JaQod/jcd14iNOl3sAZstmp/Fj1qhiH/zzmi8WfVAZQuSeVkJdMcuP6Bj0Lf+sEV4/eCU6HlBHylqe3GRHBS1e5HtQhy/P/VQrBHJfdabmD/XGxiRriNcnxJ5rw5PSykw/MNj/7hRat6bMEOIZna0qmBi/AjmaeMnWN1e4fe/F3tj2MPTH1qe4k7UcBXQYgYiPvpv8hQBX2hVYu4YTFrMq3Y0JZcpf0VSpdmMJ2RjmgCPeI3vBfm2FXTkbpZ+odoZqFj/jtxhvT/SD3/yEt28UeTNSG0ZE41dYWv22O/dS7aNEsG+KpWmqbTnpc0rarFHqAhCxwPybOM3ZLVDlmGqi2eaE3HxzxiFIaeMh4qQ0y705ot8T+5pmukP7JZE/9ILda4cy+RqD72GzEMQEsLLfUBSiga/NjEeADEhuzvMny48Hp3Ld/WubsrQZWMdcEuhfHHi7Na4Ao69t3K2sNB9PtLAlm4z3+tlM4cXARz3ju2vTaHo5A/gar4gTWax+m6R7xegaECf2VNzjfhEuCIO4noeVAiAP5aTzqxQTZEMDN9OMM6EsmNYv3bwpmDT3wUlcfQeqQJWqh3JP2iBVm9UbowpP+r6n5kMyqMQ+EzwpoE9YyNz7edoj/FBo+w==";
            byte[] base64EncryptedBytes = base64EncryptedText.getBytes();

            byte[] base64DecryptedBytes = EncryptUtil.decryptMessageBySM2(base64EncryptedBytes, sm2priKey, session);
            Assert.assertTrue("testDecryptMessageBySM2ByteArrayKeySession", Arrays.equals(sourceData, base64DecryptedBytes));
        }
        {// RAW(C1+C2+C3)
            final String base64EncryptedText = "72klSaazW9iN4Fd6c1rPvtbc/V2loTmMLe4nuWmDBQE9BNl2ebOfJi/zpH3QEo6XwYoWrfUJdz6euJyb4QV9KqfxY9aoYh/885ovFn1QGULknlZCXTHLj+gY9C3/rBFeP3glOh5QR8pantxkRwUtXuR7UIcvz/1UKwRyX3Wm5g/1xsYka4jXJ8Sea8OT0spMPzDY/+4UWremzBDiGZ2tKpgYvwI5mnjJ1jdXuH3vxd7Y9jD0x9anuJO1HAV0GIGIj76b/IUAV9oVWLuGExazKt2NCWXKX9FUqXZjCdkY5oAj3iN7wX5thV05G6WfqHaGahY/47cYb0/0g9/8hLdvFHkzUhtGRONXWFr9tjv3Uu2jRLBviqVpqm056XNK2qxR6gIQscD8mzjN2S1Q5ZhqotnmhNx8c8YhSGnjIeKkNMu9OaLfE/uaZrpD+yWRP/SC3WuHMvkag+9hsxDEBLCy31AUooGvzYxHgAxIbs7zJ8uPB6dy3f1rm7K0GVjHXBLoXxx4uzWuAKOvbdytrDQfT7SwJZuM9/rZTOHFwEc947tr02h6OQP4Gq+IE1msfpuke8XoGhAn9lTc434RLgiDuJ6HlQIgD+Wk86sUE2RDAzfTjDOhLJjWL928KZg098FJXH0HqkCVqodyT9ogVZvVG6MKT/q+p+ZDMqjEPhM8KaBPWMjc+3naI/xQaPurKjEsowo6pCN6zHY483PiWkKHf43HdeIjTpd7AGbLZg==";
            byte[] base64EncryptedBytes = base64EncryptedText.getBytes();

            byte[] base64DecryptedBytes = EncryptUtil.decryptMessageBySM2(base64EncryptedBytes, sm2priKey, session);
            Assert.assertTrue("testDecryptMessageBySM2ByteArrayKeySession", Arrays.equals(sourceData, base64DecryptedBytes));
        }

    }

    public void testEncryptFileBySM2() throws Exception {

        String sourceFilePath = "TestData/sm2/test.dat";
        String encryptFilePath = "TestData/out/test.enc";
        String decryptFilePath = "TestData/out/test.dec";
        EncryptUtil.encryptFileBySM2(sourceFilePath, encryptFilePath, sm2cert, session);

        EncryptUtil.decryptFileBySM2(encryptFilePath, decryptFilePath, (SM2PrivateKey) sm2priKey, session);

        byte[] sourceBytes = FileHelper.read(sourceFilePath);
        byte[] decryptBytes = FileHelper.read(decryptFilePath);
        Assert.assertTrue("testEncryptFileBySM2", Arrays.equals(sourceBytes, decryptBytes));

    }

    public void testDecryptFileBySM2() throws Exception {
        String sourceFilePath = "TestData/sm2/test.dat";
        String encryptFilePath = "TestData/sm2/test.enc";
        String decryptFilePath = "TestData/out/test.dec";

        EncryptUtil.decryptFileBySM2(encryptFilePath, decryptFilePath, (SM2PrivateKey) sm2priKey, session);

        byte[] sourceBytes = FileHelper.read(sourceFilePath);
        byte[] decryptBytes = FileHelper.read(decryptFilePath);
        Assert.assertTrue("testDecryptFileBySM2", Arrays.equals(sourceBytes, decryptBytes));
    }

    public void testDES3EncryptMechanismKeyByteArraySession() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] encryptedData = EncryptUtil.encrypt(mechanism, SYMTestData.desKey, data, session);

        byte[] enc = Base64.decode(encryptedData);

        Assert.assertTrue("testDES3EncryptMechanismKeyByteArraySession", Arrays.equals(enc, SYMTestData.desEncryptedBytes));

    }

    public void testDES3DecryptMechanismKeyByteArraySession() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] encryptedData = SYMTestData.desEncryptedBytes;

        byte[] decryptedData = EncryptUtil.decrypt(mechanism, SYMTestData.desKey, Base64.encode(encryptedData), session);
        Assert.assertTrue("testDES3DecryptMechanismKeyByteArraySession", Arrays.equals(decryptedData, data));
    }

    public void testDES3EncryptMechanismKeyStringStringSession() throws Exception {

        Mechanism mechanism = SYMTestData.desMechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/out/test.tmp";

        EncryptUtil.encrypt(mechanism, SYMTestData.desKey, sourceFilePath, encryptFilePath, session);

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testDES3EncryptMechanismKeyStringStringSession", Constants.equals(encryptedData, SYMTestData.desEncryptedBytes));
    }

    public void testDES3DecryptMechanismKeyStringStringSession() throws Exception {
        Mechanism mechanism = SYMTestData.desMechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/desEncryptedBytes.dat";
        String plainTextFilePath = "TestData/out/test.dec.tmp";

        EncryptUtil.decrypt(mechanism, SYMTestData.desKey, encryptFilePath, plainTextFilePath, session);

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("testDES3DecryptMechanismKeyStringStringSession", Constants.equals(decryptedData, data));
    }

    public void testRC4EncryptMechanismKeyByteArraySession() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] encryptedData = EncryptUtil.encrypt(mechanism, SYMTestData.rc4Key, data, session);

        byte[] enc = Base64.decode(encryptedData);

        Assert.assertTrue("testRC4EncryptMechanismKeyByteArraySession", Arrays.equals(enc, SYMTestData.rc4EncryptedBytes));

    }

    public void testRC4DecryptMechanismKeyByteArraySession() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] encryptedData = SYMTestData.rc4EncryptedBytes;

        byte[] decryptedData = EncryptUtil.decrypt(mechanism, SYMTestData.rc4Key, Base64.encode(encryptedData), session);
        Assert.assertTrue("testRC4DecryptMechanismKeyByteArraySession", Arrays.equals(decryptedData, data));
    }

    public void testRC4EncryptMechanismKeyStringStringSession() throws Exception {

        Mechanism mechanism = SYMTestData.rc4Mechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/out/test.tmp";

        EncryptUtil.encrypt(mechanism, SYMTestData.rc4Key, sourceFilePath, encryptFilePath, session);

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testRC4EncryptMechanismKeyStringStringSession", Arrays.equals(encryptedData, SYMTestData.rc4EncryptedBytes));
    }

    public void testRC4DecryptMechanismKeyStringStringSession() throws Exception {
        Mechanism mechanism = SYMTestData.rc4Mechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/rc4EncryptedBytes.dat";
        String plainTextFilePath = "TestData/out/test.dec.tmp";

        EncryptUtil.decrypt(mechanism, SYMTestData.rc4Key, encryptFilePath, plainTextFilePath, session);

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("testRC4DecryptMechanismKeyStringStringSession", Arrays.equals(decryptedData, data));
    }

    public void testSM4EncryptMechanismKeyByteArraySession() throws Exception {

        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] encryptedData = EncryptUtil.encrypt(mechanism, SYMTestData.sm4Key, data, session);

        byte[] enc = Base64.decode(encryptedData);

        Assert.assertTrue("testRC4EncryptMechanismKeyByteArraySession", Arrays.equals(enc, SYMTestData.sm4EncryptedBytes));

    }

    public void testSM4DecryptMechanismKeyByteArraySession() throws Exception {
        byte[] data = SYMTestData.dataBytes;
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] encryptedData = SYMTestData.sm4EncryptedBytes;

        byte[] decryptedData = EncryptUtil.decrypt(mechanism, SYMTestData.sm4Key, Base64.encode(encryptedData), session);
        Assert.assertTrue("testRC4DecryptMechanismKeyByteArraySession", Arrays.equals(decryptedData, data));
    }

    public void testSM4EncryptMechanismKeyStringStringSession() throws Exception {

        Mechanism mechanism = SYMTestData.sm4Mechanism;

        String sourceFilePath = "TestData/sym/test.dat";
        String encryptFilePath = "TestData/out/test.tmp";

        EncryptUtil.encrypt(mechanism, SYMTestData.sm4Key, sourceFilePath, encryptFilePath, session);

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testSM4EncryptMechanismKeyStringStringSession", Constants.equals(encryptedData, SYMTestData.sm4EncryptedBytes));
    }

    public void testSM4DecryptMechanismKeyStringStringSession() throws Exception {
        Mechanism mechanism = SYMTestData.sm4Mechanism;

        byte[] data = SYMTestData.dataBytes;

        String encryptFilePath = "TestData/sym/sm4EncryptedBytes.dat";
        String plainTextFilePath = "TestData/out/test.dec.tmp";

        EncryptUtil.decrypt(mechanism, SYMTestData.sm4Key, encryptFilePath, plainTextFilePath, session);

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("testSM4DecryptMechanismKeyStringStringSession", Constants.equals(decryptedData, data));
    }

}
