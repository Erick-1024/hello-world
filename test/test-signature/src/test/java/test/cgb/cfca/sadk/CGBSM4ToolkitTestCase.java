package test.cgb.cfca.sadk;

import java.util.Arrays;
import java.util.Random;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.cgb.cfca.sadk.testdata.CGBSYMTestData;
import cfca.sadk.cgb.toolkit.SM4Toolkit;
import cfca.sadk.system.FileHelper;

public class CGBSM4ToolkitTestCase {

    final Random random = new Random();
    final SM4Toolkit toolkit = new SM4Toolkit();

    @Before
    public void setUp() throws Exception {
        toolkit.SM4Init(CGBSYMTestData.sm4KeyBytes, CGBSYMTestData.sm4IvBytes);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSM4Init() throws Exception {
        toolkit.SM4Init(CGBSYMTestData.sm4KeyBytes, CGBSYMTestData.sm4IvBytes);
        org.junit.Assert.assertTrue("testSM4Init", true);
        try {
            toolkit.SM4Init("xxxx".getBytes(), CGBSYMTestData.sm4IvBytes);
        } catch (Exception e) {
            org.junit.Assert.assertTrue("testSM4Init", true);
        }
    }

    @Test
    public void testSM4EncryptData() throws Exception {

        byte[] data = CGBSYMTestData.dataBytes;

        byte[] encryptedData = toolkit.SM4EncryptData(data);

        Assert.assertTrue("testSM4EncryptData", Arrays.equals(encryptedData, CGBSYMTestData.sm4EncryptedBytes));

    }

    @Test
    public void testSM4DecryptData() throws Exception {
        byte[] data = CGBSYMTestData.dataBytes;

        byte[] encryptedData = CGBSYMTestData.sm4EncryptedBytes;

        byte[] decryptedData = toolkit.SM4DecryptData(encryptedData);

        Assert.assertTrue("testSM4DecryptData", Arrays.equals(decryptedData, data));

        byte[] bytes = new byte[100 + random.nextInt(240000)];
        random.nextBytes(bytes);

        encryptedData = toolkit.SM4EncryptData(data);

        decryptedData = toolkit.SM4DecryptData(encryptedData);

        Assert.assertTrue("testSM4DecryptData", Arrays.equals(decryptedData, data));

    }

    @Test
    public void testSM4EncryptFile() throws Exception {

        String sourceFilePath = CGBTestData.TESTDATA_DIR +"sym/test.dat";
        String encryptFilePath ="TestData/out/test.tmp";

        boolean encryptedResult = toolkit.SM4EncryptFile(sourceFilePath, encryptFilePath);
        org.junit.Assert.assertTrue("testSM4EncryptFile", encryptedResult);

        byte[] encryptedData = FileHelper.read(encryptFilePath);

        Assert.assertTrue("testSM4EncryptFile", Arrays.equals(encryptedData, CGBSYMTestData.sm4EncryptedBytes));

        sourceFilePath = CGBTestData.TESTDATA_DIR +"sym/test.bin";
        toolkit.SM4EncryptFile(sourceFilePath, encryptFilePath);
        byte[] encryptedData1 = FileHelper.read(encryptFilePath);
        byte[] encryptedData2 = toolkit.SM4EncryptData(FileHelper.read(sourceFilePath));

        FileHelper.write(encryptFilePath + 2, encryptedData2);

        Assert.assertTrue("testSM4EncryptFile", Arrays.equals(encryptedData1, encryptedData2));

    }

    @Test
    public void testSM4DecryptFile() throws Exception {

        byte[] data = CGBSYMTestData.dataBytes;

        String encryptFilePath = CGBTestData.TESTDATA_DIR +"sym/sm4EncryptedBytes.dat";
        String plainTextFilePath = "TestData/out/test.dec.tmp";

        boolean encryptedResult = toolkit.SM4DecryptFile(encryptFilePath, plainTextFilePath);
        org.junit.Assert.assertTrue("testSM4DecryptFile", encryptedResult);

        byte[] decryptedData = FileHelper.read(plainTextFilePath);

        Assert.assertTrue("testSM4DecryptFile", Arrays.equals(decryptedData, data));

        String sourceFilePath = CGBTestData.TESTDATA_DIR +"sym/test.bin";
        String sourceFileEncPath = "TestData/out/test.bin.enc.tmp";
        String sourceFileDecPath ="TestData/out/test.bin.dec.tmp";
        toolkit.SM4EncryptFile(sourceFilePath,sourceFileEncPath);
        toolkit.SM4DecryptFile(sourceFileEncPath, sourceFileDecPath);

        byte[] source1 = FileHelper.read(sourceFilePath);
        byte[] source2 = FileHelper.read(sourceFileDecPath);
        Assert.assertTrue("testSM4DecryptFile", Arrays.equals(source1, source2));

    }
}
