package test.ecitic.cfca.sadk.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import test.cfca.sadk.testdata.RSATestData;
import test.cfca.sadk.testdata.SM2;
import test.cfca.sadk.testdata.SM2TestData;
import test.cfca.sadk.testdata.SYMTestData;
import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.algorithm.sm2.SM2PrivateKey;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.sadk.util.EncryptUtil;
import cfca.sadk.x509.certificate.X509Cert;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * 中信专版V3.0.0.2兼容性测试代码
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public class ECITICEncryptUtilTestCase extends TestCase {
    Session session = null;

    String pwd = null;

    byte[] des3iv;
    byte[] des3key;

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

        des3iv = new byte[8];
        Arrays.fill(des3iv, (byte) 0x88);
        des3key = new byte[24];
        Arrays.fill(des3key, (byte) 0x88);

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

    @Test
    public void testEncryptUtil() {
        EncryptUtil xxx = new EncryptUtil();

        Assert.assertTrue("testEncryptUtil", xxx != null);
    }

    @Test
    public void testEncryptMessageByDES3ByteArrayString() throws PKIException, IOException {

        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-des3-pwd-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageByDES3(dataBytes, pwd);

        byte[] encryptedBase64Bytes = FileHelper.read(encryptedFilePath);

        assertTrue("testEncryptMessageByDES3ByteArrayString", Arrays.equals(encryptedBase64Bytes, base64EncryptedBytes));

        byte[] decryptedBytes = EncryptUtil.decryptMessageByDES3(encryptedBase64Bytes, pwd);

        assertTrue("testEncryptMessageByDES3ByteArrayString", Arrays.equals(decryptedBytes, dataBytes));

    }

    @Test
    public void testEncryptMessageByDES3ByteArrayByteArrayByteArray() throws PKIException, IOException {

        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-des3-iv-key-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageByDES3(dataBytes, des3iv, des3key);

        byte[] encryptedBase64Bytes = FileHelper.read(encryptedFilePath);

        assertTrue("testEncryptMessageByDES3ByteArrayByteArrayByteArray", Arrays.equals(encryptedBase64Bytes, base64EncryptedBytes));

        byte[] decryptedBytes = EncryptUtil.decryptMessageByDES3(encryptedBase64Bytes, des3iv, des3key);

        assertTrue("testEncryptMessageByDES3ByteArrayByteArrayByteArray", Arrays.equals(decryptedBytes, dataBytes));

    }

    @Test
    public void testDecryptMessageByDES3ByteArrayString() throws PKIException, IOException {

        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-des3-pwd-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] encryptedBase64Bytes = FileHelper.read(encryptedFilePath);

        byte[] decryptedBytes = EncryptUtil.decryptMessageByDES3(encryptedBase64Bytes, pwd);

        assertTrue("testDecryptMessageByDES3ByteArrayString", Arrays.equals(decryptedBytes, dataBytes));

    }

    @Test
    public void testDecryptMessageByDES3ByteArrayByteArrayByteArray() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-des3-iv-key-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] encryptedBase64Bytes = FileHelper.read(encryptedFilePath);

        byte[] decryptedBytes = EncryptUtil.decryptMessageByDES3(encryptedBase64Bytes, des3iv, des3key);

        assertTrue("testDecryptMessageByDES3ByteArrayByteArrayByteArray", Arrays.equals(decryptedBytes, dataBytes));
    }

    @Test
    public void testEncryptMessageBySM2ByteArrayX509CertSession() throws PKIException {
        byte[] sourceData = SM2TestData.dataBytes;
        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageBySM2(sourceData, sm2cert, session);
        Assert.assertTrue("testEncryptMessage", SM2.isECITICEncryptedFormatRAWBytes(Base64.decode(base64EncryptedBytes)));

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageBySM2(base64EncryptedBytes, sm2priKey, session);

        Assert.assertTrue("testEncryptMessageBySM2ByteArrayX509CertSession", Arrays.equals(sourceData, base64DecryptedBytes));
    }

    @Test
    public void testEncryptMessageBySM2ByteArrayPublicKeySession() throws PKIException {
        byte[] sourceData = SM2TestData.dataBytes;
        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageBySM2(sourceData, sm2pubKey, session);

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageBySM2(base64EncryptedBytes, sm2priKey, session);

        Assert.assertTrue("testEncryptMessageBySM2ByteArrayPublicKeySession", Arrays.equals(sourceData, base64DecryptedBytes));
    }

    @Test
    public void testDecryptMessageBySM2() throws PKIException {

        byte[] sourceData = SM2TestData.dataBytes;

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

    @Test
    public void testEncryptMessageByRSAByteArrayX509CertSession() throws PKIException {
        byte[] sourceData = RSATestData.data;

        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageByRSA(sourceData, rsacert, session);

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageByRSA(base64EncryptedBytes, rsapriKey, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayX509CertSession", Arrays.equals(sourceData, base64DecryptedBytes));
    }

    @Test
    public void testEncryptMessageByRSAByteArrayPublicKeySession() throws PKIException {
        byte[] sourceData = RSATestData.data;
        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageByRSA(sourceData, rsapubKey, session);

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageByRSA(base64EncryptedBytes, rsapriKey, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayPublicKeySession", Arrays.equals(sourceData, base64DecryptedBytes));
    }

    @Test
    public void testDecryptMessageByRSA() throws PKIException {
        final String base64EncryptedText = "hYM+4gZj5hCXlqQOxTpTI1qUNkAcCaKu3Sm4s1HGPdPNAM8wLIBzs8aHRrkDlp9U3o0Wm6UKmm9aKFzIWLE74Q+CH7fhQsMaQRM48zKOgsgKYXNMkbcIra8D85mtxO0XG5tWsjTvzLU2f8YLV0yAxICPIaHzqyl+giuf+XqkiCFeC+QD5i3bwMdew03Hd6A7C752oWYno5lRdGn5vxK+iT9U2X/YvMADjFLa9WeJQsWnnSWPhq1pv/Hp1PGAG/xTC22MbibaBuXEtOgjQBEVUYDIeZbGlDVeFPqwGaCHvHpjTT3m0QSnf6hpC+Gz7v0iFwlt2RuylW1LQhHrLvfgWw==";

        byte[] sourceData = RSATestData.data;
        byte[] base64EncryptedBytes = base64EncryptedText.getBytes();

        byte[] base64DecryptedBytes = EncryptUtil.decryptMessageByRSA(base64EncryptedBytes, rsapriKey, session);

        Assert.assertTrue("testEncryptMessageByRSAByteArrayKeySession", Arrays.equals(sourceData, base64DecryptedBytes));
    }

    @Test
    public void testEncryptFileBySM2InputStreamOutputStreamX509CertSession() throws PKIException, IOException {
        String sourceFilePath = "TestData/ecitic/sm2/test.dat";
        String encryptFilePath = "TestData/out/test.enc";
        String decryptFilePath = "TestData/out/test.dec";
        EncryptUtil.encryptFileBySM2(new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath), sm2cert, session);

        byte[] encryptedBytes = Base64.decode(FileHelper.read(encryptFilePath));
        Assert.assertTrue("testEncryptMessage", SM2.isECITICEncryptedFormatRAWBytes(encryptedBytes));

        EncryptUtil.decryptFileBySM2(new FileInputStream(encryptFilePath), new FileOutputStream(decryptFilePath), (SM2PrivateKey) sm2priKey, session);

        byte[] sourceBytes = FileHelper.read(sourceFilePath);
        byte[] decryptBytes = FileHelper.read(decryptFilePath);
        Assert.assertTrue("testEncryptFileBySM2InputStreamOutputStreamX509CertSession", Arrays.equals(sourceBytes, decryptBytes));
    }

    @Test
    public void testEncryptFileBySM2InputStreamOutputStreamPublicKeySession() throws PKIException, IOException {
        String sourceFilePath = "TestData/ecitic/sm2/test.dat";
        String encryptFilePath = "TestData/out/test.enc";
        String decryptFilePath = "TestData/out/test.dec";
        EncryptUtil.encryptFileBySM2(new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath), sm2pubKey, session);

        byte[] encryptedBytes = Base64.decode(FileHelper.read(encryptFilePath));
        Assert.assertTrue("testEncryptMessage", SM2.isECITICEncryptedFormatRAWBytes(encryptedBytes));

        EncryptUtil.decryptFileBySM2(new FileInputStream(encryptFilePath), new FileOutputStream(decryptFilePath), (SM2PrivateKey) sm2priKey, session);

        byte[] sourceBytes = FileHelper.read(sourceFilePath);
        byte[] decryptBytes = FileHelper.read(decryptFilePath);
        Assert.assertTrue("testEncryptFileBySM2InputStreamOutputStreamPublicKeySession", Arrays.equals(sourceBytes, decryptBytes));
    }

    @Test
    public void testDecryptFileBySM2() throws PKIException, IOException {
        String sourceFilePath = "TestData/ecitic/sm2/test.dat";
        String encryptFilePath = "TestData/ecitic/sm2/test.enc";
        String decryptFilePath = "TestData/out/test.dec";

        EncryptUtil.decryptFileBySM2(new FileInputStream(encryptFilePath), new FileOutputStream(decryptFilePath), (SM2PrivateKey) sm2priKey, session);

        byte[] sourceBytes = FileHelper.read(sourceFilePath);
        byte[] decryptBytes = FileHelper.read(decryptFilePath);
        Assert.assertTrue("testDecryptFileBySM2", Arrays.equals(sourceBytes, decryptBytes));
    }

    @Test
    public void testEncryptFileByDES3InputStreamOutputStreamString() throws PKIException, IOException {

        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-des3-pwd-file-encrypt.enc";
        final String decryptFilePath = "TestData/out/test.dec";

        EncryptUtil.encryptFileByDES3(new FileInputStream(sourceFilePath), new FileOutputStream(encryptedFilePath), pwd);
        EncryptUtil.decryptFileByDES3(new FileInputStream(encryptedFilePath), new FileOutputStream(decryptFilePath), pwd);

        final byte[] sourceFileBytes = FileHelper.read(sourceFilePath);
        final byte[] decryptFileBytes = FileHelper.read(decryptFilePath);

        Assert.assertTrue("testEncryptFileByDES3InputStreamOutputStreamString", Arrays.equals(sourceFileBytes, decryptFileBytes));

    }

    @Test
    public void testEncryptFileByDES3InputStreamOutputStreamByteArrayByteArray() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-des3-iv-key-file-encrypt.enc";
        final String decryptFilePath = "TestData/out/test.dec";

        EncryptUtil.encryptFileByDES3(new FileInputStream(sourceFilePath), new FileOutputStream(encryptedFilePath), des3iv, des3key);
        EncryptUtil.decryptFileByDES3(new FileInputStream(encryptedFilePath), new FileOutputStream(decryptFilePath), des3iv, des3key);

        final byte[] sourceFileBytes = FileHelper.read(sourceFilePath);
        final byte[] decryptFileBytes = FileHelper.read(decryptFilePath);

        Assert.assertTrue("testEncryptFileByDES3InputStreamOutputStreamByteArrayByteArray", Arrays.equals(sourceFileBytes, decryptFileBytes));
    }

    @Test
    public void testDecryptFileByDES3InputStreamOutputStreamString() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-des3-pwd-file-encrypt.enc";
        final String decryptFilePath = "TestData/out/test.dec";

        EncryptUtil.decryptFileByDES3(new FileInputStream(encryptedFilePath), new FileOutputStream(decryptFilePath), pwd);

        final byte[] sourceFileBytes = FileHelper.read(sourceFilePath);
        final byte[] decryptFileBytes = FileHelper.read(decryptFilePath);

        Assert.assertTrue("testEncryptFileByDES3InputStreamOutputStreamString", Arrays.equals(sourceFileBytes, decryptFileBytes));
    }

    @Test
    public void testDecryptFileByDES3InputStreamOutputStreamByteArrayByteArray() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-des3-iv-key-file-encrypt.enc";
        final String decryptFilePath = "TestData/out/test.dec";

        EncryptUtil.decryptFileByDES3(new FileInputStream(encryptedFilePath), new FileOutputStream(decryptFilePath), des3iv, des3key);

        final byte[] sourceFileBytes = FileHelper.read(sourceFilePath);
        final byte[] decryptFileBytes = FileHelper.read(decryptFilePath);

        Assert.assertTrue("testEncryptFileByDES3InputStreamOutputStreamByteArrayByteArray", Arrays.equals(sourceFileBytes, decryptFileBytes));

    }

    @Test
    public void testEncryptMessageBySM4ByteArrayString() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-sm4-pwd-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageBySM4(dataBytes, pwd);

        byte[] encryptedBase64Bytes = FileHelper.read(encryptedFilePath);

        assertTrue("testEncryptMessageBySM4ByteArrayString", Arrays.equals(encryptedBase64Bytes, base64EncryptedBytes));

        byte[] decryptedBytes = EncryptUtil.decryptMessageBySM4(encryptedBase64Bytes, pwd);

        assertTrue("testEncryptMessageBySM4ByteArrayString", Arrays.equals(decryptedBytes, dataBytes));
    }

    @Test
    public void testEncryptMessageBySM4ByteArrayByteArrayByteArray() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-sm4-iv-key-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] base64EncryptedBytes = EncryptUtil.encryptMessageBySM4(dataBytes, sm4iv, sm4key);

        byte[] encryptedBase64Bytes = FileHelper.read(encryptedFilePath);

        assertTrue("testEncryptMessageBySM4ByteArrayByteArrayByteArray", Arrays.equals(encryptedBase64Bytes, base64EncryptedBytes));

        byte[] decryptedBytes = EncryptUtil.decryptMessageBySM4(encryptedBase64Bytes, sm4iv, sm4key);

        assertTrue("testEncryptMessageBySM4ByteArrayByteArrayByteArray", Arrays.equals(decryptedBytes, dataBytes));
    }

    @Test
    public void testDecryptMessageBySM4ByteArrayString() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-sm4-pwd-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] encryptedBase64Bytes = FileHelper.read(encryptedFilePath);

        byte[] decryptedBytes = EncryptUtil.decryptMessageBySM4(encryptedBase64Bytes, pwd);

        assertTrue("testDecryptMessageBySM4ByteArrayString", Arrays.equals(decryptedBytes, dataBytes));
    }

    @Test
    public void testDecryptMessageBySM4ByteArrayByteArrayByteArray() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-sm4-iv-key-encrypt.enc";

        byte[] dataBytes = FileHelper.read(sourceFilePath);

        byte[] encryptedBase64Bytes = FileHelper.read(encryptedFilePath);

        byte[] decryptedBytes = EncryptUtil.decryptMessageBySM4(encryptedBase64Bytes, sm4iv, sm4key);

        assertTrue("testDecryptMessageBySM4ByteArrayByteArrayByteArray", Arrays.equals(decryptedBytes, dataBytes));
    }

    @Test
    public void testEncryptFileBySM4InputStreamOutputStreamString() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-sm4-pwd-file-encrypt.enc";
        final String decryptFilePath = "TestData/out/test.dec";

        EncryptUtil.encryptFileBySM4(new FileInputStream(sourceFilePath), new FileOutputStream(encryptedFilePath), pwd);
        EncryptUtil.decryptFileBySM4(new FileInputStream(encryptedFilePath), new FileOutputStream(decryptFilePath), pwd);

        final byte[] sourceFileBytes = FileHelper.read(sourceFilePath);
        final byte[] decryptFileBytes = FileHelper.read(decryptFilePath);

        Assert.assertTrue("testEncryptFileBySM4InputStreamOutputStreamString", Arrays.equals(sourceFileBytes, decryptFileBytes));
    }

    @Test
    public void testEncryptFileBySM4InputStreamOutputStreamByteArrayByteArray() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-sm4-iv-key-file-encrypt.enc";
        final String decryptFilePath = "TestData/out/test.dec";

        EncryptUtil.encryptFileBySM4(new FileInputStream(sourceFilePath), new FileOutputStream(encryptedFilePath), sm4iv, sm4key);
        EncryptUtil.decryptFileBySM4(new FileInputStream(encryptedFilePath), new FileOutputStream(decryptFilePath), sm4iv, sm4key);

        final byte[] sourceFileBytes = FileHelper.read(sourceFilePath);
        final byte[] decryptFileBytes = FileHelper.read(decryptFilePath);

        Assert.assertTrue("testEncryptFileBySM4InputStreamOutputStreamByteArrayByteArray", Arrays.equals(sourceFileBytes, decryptFileBytes));
    }

    @Test
    public void testDecryptFileBySM4InputStreamOutputStreamString() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-sm4-pwd-file-encrypt.enc";
        final String decryptFilePath = "TestData/out/test.dec";

        EncryptUtil.decryptFileBySM4(new FileInputStream(encryptedFilePath), new FileOutputStream(decryptFilePath), pwd);

        final byte[] sourceFileBytes = FileHelper.read(sourceFilePath);
        final byte[] decryptFileBytes = FileHelper.read(decryptFilePath);

        Assert.assertTrue("testDecryptFileBySM4InputStreamOutputStreamString", Arrays.equals(sourceFileBytes, decryptFileBytes));
    }

    @Test
    public void testDecryptFileBySM4InputStreamOutputStreamByteArrayByteArray() throws PKIException, IOException {
        final String sourceFilePath = "TestData/ecitic/sym/test.bin";
        final String encryptedFilePath = "TestData/ecitic/sym/test-sm4-iv-key-file-encrypt.enc";
        final String decryptFilePath = "TestData/out/test.dec";

        EncryptUtil.decryptFileBySM4(new FileInputStream(encryptedFilePath), new FileOutputStream(decryptFilePath), sm4iv, sm4key);

        final byte[] sourceFileBytes = FileHelper.read(sourceFilePath);
        final byte[] decryptFileBytes = FileHelper.read(decryptFilePath);

        Assert.assertTrue("testDecryptFileBySM4InputStreamOutputStreamByteArrayByteArray", Arrays.equals(sourceFileBytes, decryptFileBytes));
    }

    @Test
    public void testEncryptMechanismKeyByteArraySession() throws PKIException {
        final byte[] data = SYMTestData.dataBytes;
        byte[] encryptedData = null;
        //
        encryptedData = EncryptUtil.encrypt(SYMTestData.desMechanism, SYMTestData.desKey, data, session);
        Assert.assertTrue("testEncryptMechanismKeyByteArraySession", Arrays.equals(Base64.decode(encryptedData), SYMTestData.desEncryptedBytes));
        //
        encryptedData = EncryptUtil.encrypt(SYMTestData.rc4Mechanism, SYMTestData.rc4Key, data, session);
        Assert.assertTrue("testEncryptMechanismKeyByteArraySession", Arrays.equals(Base64.decode(encryptedData), SYMTestData.rc4EncryptedBytes));
        //
        encryptedData = EncryptUtil.encrypt(SYMTestData.sm4Mechanism, SYMTestData.sm4Key, data, session);
        Assert.assertTrue("testEncryptMechanismKeyByteArraySession", Arrays.equals(Base64.decode(encryptedData), SYMTestData.sm4EncryptedBytes));

        //
        //
        byte[] sourceData = SM2TestData.dataBytes;
        // SM2: ASN1(C1+C3+C2)
        byte[] base64EncryptedBytes = EncryptUtil.encrypt(new Mechanism(Mechanism.SM2), sm2pubKey, sourceData, session);
        Assert.assertTrue("testEncryptMechanismKeyByteArraySession", SM2.isECITICEncryptedFormatASNBytes(Base64.decode(base64EncryptedBytes)));
        byte[] base64DecryptedBytes = EncryptUtil.decrypt(new Mechanism(Mechanism.SM2), sm2priKey, base64EncryptedBytes, session);
        Assert.assertTrue("testEncryptMechanismKeyByteArraySession", Arrays.equals(sourceData, base64DecryptedBytes));

        // RSA_PKCS
        sourceData = RSATestData.data;
        base64EncryptedBytes = EncryptUtil.encrypt(new Mechanism(Mechanism.RSA_PKCS), rsapubKey, sourceData, session);
        base64DecryptedBytes = EncryptUtil.decrypt(new Mechanism(Mechanism.RSA_PKCS), rsapriKey, base64EncryptedBytes, session);
        Assert.assertTrue("testEncryptMechanismKeyByteArraySession", Arrays.equals(sourceData, base64DecryptedBytes));

    }

    @Test
    public void testDecryptMechanismKeyByteArraySession() throws PKIException {
        final byte[] data = SYMTestData.dataBytes;
        byte[] decryptedData = null;
        //
        decryptedData = EncryptUtil.decrypt(SYMTestData.desMechanism, SYMTestData.desKey, Base64.encode(SYMTestData.desEncryptedBytes), session);
        Assert.assertTrue("testDecryptMechanismKeyByteArraySession", Arrays.equals(decryptedData, data));
        //
        decryptedData = EncryptUtil.decrypt(SYMTestData.rc4Mechanism, SYMTestData.rc4Key, Base64.encode(SYMTestData.rc4EncryptedBytes), session);
        Assert.assertTrue("testDecryptMechanismKeyByteArraySession", Arrays.equals(decryptedData, data));
        //
        decryptedData = EncryptUtil.decrypt(SYMTestData.sm4Mechanism, SYMTestData.sm4Key, Base64.encode(SYMTestData.sm4EncryptedBytes), session);
        Assert.assertTrue("testDecryptMechanismKeyByteArraySession", Arrays.equals(decryptedData, data));
    }

    @Test
    public void testEncryptMechanismKeyInputStreamOutputStreamSession() throws PKIException, IOException {
        final String sourceFilePath = "TestData/sym/test.dat";
        final String encryptFilePath = "TestData/out/test.tmp";
        byte[] encryptedData = null;
        //

        EncryptUtil.encrypt(SYMTestData.desMechanism, SYMTestData.desKey, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath), session);
        encryptedData = FileHelper.read(encryptFilePath);
        Assert.assertTrue("testEncryptMechanismKeyInputStreamOutputStreamSession", Arrays.equals(encryptedData, SYMTestData.desEncryptedBytes));
        //
        EncryptUtil.encrypt(SYMTestData.rc4Mechanism, SYMTestData.rc4Key, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath), session);
        encryptedData = FileHelper.read(encryptFilePath);
        Assert.assertTrue("testEncryptMechanismKeyInputStreamOutputStreamSession", Arrays.equals(encryptedData, SYMTestData.rc4EncryptedBytes));
        //
        EncryptUtil.encrypt(SYMTestData.sm4Mechanism, SYMTestData.sm4Key, new FileInputStream(sourceFilePath), new FileOutputStream(encryptFilePath), session);
        encryptedData = FileHelper.read(encryptFilePath);
        Assert.assertTrue("testEncryptMechanismKeyInputStreamOutputStreamSession", Arrays.equals(encryptedData, SYMTestData.sm4EncryptedBytes));

    }

    @Test
    public void testDecryptMechanismKeyInputStreamOutputStreamSession() throws PKIException, IOException {
        final byte[] data = SYMTestData.dataBytes;
        final String plainTextFilePath = "TestData/out/test.dec.tmp";
        FileInputStream encryptFileInputStream = null;
        byte[] decryptedData = null;
        //
        encryptFileInputStream = new FileInputStream("TestData/sym/desEncryptedBytes.dat");
        EncryptUtil.decrypt(SYMTestData.desMechanism, SYMTestData.desKey, encryptFileInputStream, new FileOutputStream(plainTextFilePath), session);
        decryptedData = FileHelper.read(plainTextFilePath);
        Assert.assertTrue("testDecryptMechanismKeyInputStreamOutputStreamSession", Arrays.equals(decryptedData, data));
        //
        encryptFileInputStream = new FileInputStream("TestData/sym/desEncryptedBytes.dat");
        EncryptUtil.decrypt(SYMTestData.desMechanism, SYMTestData.desKey, encryptFileInputStream, new FileOutputStream(plainTextFilePath), session);
        decryptedData = FileHelper.read(plainTextFilePath);
        Assert.assertTrue("testDecryptMechanismKeyInputStreamOutputStreamSession", Arrays.equals(decryptedData, data));
        //
        encryptFileInputStream = new FileInputStream("TestData/sym/rc4EncryptedBytes.dat");
        EncryptUtil.decrypt(SYMTestData.rc4Mechanism, SYMTestData.rc4Key, encryptFileInputStream, new FileOutputStream(plainTextFilePath), session);
        decryptedData = FileHelper.read(plainTextFilePath);
        Assert.assertTrue("testDecryptMechanismKeyInputStreamOutputStreamSession", Arrays.equals(decryptedData, data));
    }

    @Test
    public void testEncryptMessage() throws PKIException {
        byte[] sourceData = SM2TestData.dataBytes;
        // SM2: RAW(C1+C3+C2)
        byte[] base64EncryptedBytes = EncryptUtil.encryptMessage(new Mechanism(Mechanism.SM2), sm2pubKey, sourceData, session);
        Assert.assertTrue("testEncryptMessage", SM2.isECITICEncryptedFormatRAWBytes(Base64.decode(base64EncryptedBytes)));
        byte[] base64DecryptedBytes = EncryptUtil.decryptMessage(new Mechanism(Mechanism.SM2), sm2priKey, base64EncryptedBytes, session);
        Assert.assertTrue("testEncryptMessage", Arrays.equals(sourceData, base64DecryptedBytes));

        // RSA_PKCS
        sourceData = RSATestData.data;
        base64EncryptedBytes = EncryptUtil.encryptMessage(new Mechanism(Mechanism.RSA_PKCS), rsapubKey, sourceData, session);
        base64DecryptedBytes = EncryptUtil.decryptMessage(new Mechanism(Mechanism.RSA_PKCS), rsapriKey, base64EncryptedBytes, session);
        Assert.assertTrue("testEncryptMessage", Arrays.equals(sourceData, base64DecryptedBytes));

    }

    @Test
    public void testDecryptMessage() throws PKIException {
        byte[] sourceData = SM2TestData.dataBytes;
        // SM2: RAW(C1+C3+C2)
        String base64EncryptedText = "fWEg/C5rlN6lOaKV6F/dn4iQJa0T9sR2uJV9sP+qQaKWH3MGbucL/eDpfOqrGrMui97VW8SPDdvx/Fqkxam2LK5+gN062FKL2+VjYhb1jLliEJwMIt+8MwRgSl9107O1+rb1kR9Mr4AiKyQeL2IGHHs43v43xOFSut5Ms1Cb/QvpbXaSHNDz3Q2QtgG29+QyTefZ+Jt4tfrEtXzMZ446AmJpFialRXpxHoLlcX0UiOpcnYBKK1AAFtNKcRHZGN6N/zs1TiUYKJ9w80ViO/OnMY3mEhOAsXd2M5BFN5paulgyu7T79oSjc9XL0EVfEL4SDAvaZWzIFo/CU2QQDkDEnhxIEIuDzAFpFQm7Fu3YM2k6Ghs9efFM040kewTd/970ZjuXlE1t0PLCeCcPXRMnU4lA+YttY007s9QGQ6aMQd9j1XyDwLv8SyrktN2SylzptP/HkuixeOpjyrwBgOIk/UazqMqjapA8qqVxvz33q/Hv+8zRgpAkQcdR5clKmtZ62KP4ZTylYaXkYIRfEv/Tb+r2NZ+IxKWnpgm6eUObODRwRc2n7zOwJR648qx6qXS/sGOTZ1fxtWhDAWUZXbv4acNWeH0ajAb22nwvCPwbjK4U1fFCQW9aw1Ty9sIRQSnd8To2cO6N2ET62eEvnIR14ZErqOQLMdSApEWyJaqk3ObWDbRIMjx4FUmD1tfJd9whR/PXL58el0R7pKnEP8hgwKu3txvSK7FXeAYhbA==";
        byte[] base64EncryptedBytes = base64EncryptedText.getBytes();
        byte[] base64DecryptedBytes = EncryptUtil.decryptMessage(new Mechanism(Mechanism.SM2), sm2priKey, base64EncryptedBytes, session);
        Assert.assertTrue("testEncryptMessage", Arrays.equals(sourceData, base64DecryptedBytes));
        // RSA_PKCS
        sourceData = RSATestData.data;
        base64EncryptedText = "hYM+4gZj5hCXlqQOxTpTI1qUNkAcCaKu3Sm4s1HGPdPNAM8wLIBzs8aHRrkDlp9U3o0Wm6UKmm9aKFzIWLE74Q+CH7fhQsMaQRM48zKOgsgKYXNMkbcIra8D85mtxO0XG5tWsjTvzLU2f8YLV0yAxICPIaHzqyl+giuf+XqkiCFeC+QD5i3bwMdew03Hd6A7C752oWYno5lRdGn5vxK+iT9U2X/YvMADjFLa9WeJQsWnnSWPhq1pv/Hp1PGAG/xTC22MbibaBuXEtOgjQBEVUYDIeZbGlDVeFPqwGaCHvHpjTT3m0QSnf6hpC+Gz7v0iFwlt2RuylW1LQhHrLvfgWw==";
        base64EncryptedBytes = base64EncryptedText.getBytes();
        base64DecryptedBytes = EncryptUtil.decryptMessage(new Mechanism(Mechanism.RSA_PKCS), rsapriKey, base64EncryptedBytes, session);
        Assert.assertTrue("testEncryptMessage", Arrays.equals(sourceData, base64DecryptedBytes));

        final byte[] data = SYMTestData.dataBytes;
        byte[] decryptedData = null;
        //
        decryptedData = EncryptUtil.decryptMessage(SYMTestData.desMechanism, SYMTestData.desKey, Base64.encode(SYMTestData.desEncryptedBytes), session);
        Assert.assertTrue("testEncryptMessage", Arrays.equals(decryptedData, data));
        //
        decryptedData = EncryptUtil.decryptMessage(SYMTestData.rc4Mechanism, SYMTestData.rc4Key, Base64.encode(SYMTestData.rc4EncryptedBytes), session);
        Assert.assertTrue("testEncryptMessage", Arrays.equals(decryptedData, data));
        //
        decryptedData = EncryptUtil.decryptMessage(SYMTestData.sm4Mechanism, SYMTestData.sm4Key, Base64.encode(SYMTestData.sm4EncryptedBytes), session);
        Assert.assertTrue("testEncryptMessage", Arrays.equals(decryptedData, data));
    }

}
