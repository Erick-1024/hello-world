package test.cgb.cfca.sadk;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.cgb.cfca.sadk.testdata.CGBSM2TestData;
import cfca.sadk.algorithm.sm2.SM2PublicKey;
import cfca.sadk.cgb.toolkit.SM3Toolkit;
import cfca.sadk.system.FileHelper;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class CGBSM3ToolkitTestCase {

    static final byte[] defaultUserId = { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38 }; // "1234567812345678"

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSM3HashDataByteArray() throws Exception {
        String sourceFilePath = CGBTestData.TESTDATA_DIR +"sm2/test.dat";
        byte[] sourceData = FileHelper.read(sourceFilePath);

        byte[] hash = SM3Toolkit.SM3HashData(sourceData);

        byte[] digest = HexBin.decode("0C105D5A46A65FDF0A0938283DB2517EA87F176DE84786F443CB78802AAA03DE");

        assertTrue("testSM3HashDataByteArray", Arrays.equals(digest, hash));
    }

    @Test
    public void testSM3HashFileString() throws Exception {
        String sourceFilePath = CGBTestData.TESTDATA_DIR +"sm2/test.dat";

        byte[] hash = HexBin.decode("0C105D5A46A65FDF0A0938283DB2517EA87F176DE84786F443CB78802AAA03DE");

        byte[] digest = SM3Toolkit.SM3HashFile(sourceFilePath);

        assertTrue("testSM3HashFileString", Arrays.equals(digest, hash));

        sourceFilePath = CGBTestData.TESTDATA_DIR +"sm2/test.bin";
        hash = SM3Toolkit.SM3HashFile(sourceFilePath);

        byte[] sm3 = SM3Toolkit.SM3HashData(FileHelper.read(sourceFilePath));

        assertTrue("testSM3HashFileString", Arrays.equals(sm3, hash));
    }

    @Test
    public void testSM3HashDataPublicKeyByteArray() throws Exception {

        byte[] sourceData = CGBSM2TestData.sm2Data;
        SM2PublicKey sm2PublicKey = CGBSM2TestData.userPubKey;

        byte[] hash = SM3Toolkit.SM3HashData(sm2PublicKey, sourceData);

        assertTrue("testSM3HashDataPublicKeyByteArray", Arrays.equals(CGBSM2TestData.sm2HashValue, hash));

    }

    @Test
    public void testSM3HashFilePublicKeyString() throws Exception {
        String sourceFilePath = CGBTestData.TESTDATA_DIR +"sm2/test.dat";
        SM2PublicKey sm2PublicKey = CGBSM2TestData.userPubKey;

        byte[] hash = SM3Toolkit.SM3HashFile(sm2PublicKey, sourceFilePath);

        assertTrue("testSM3HashFilePublicKeyString", Arrays.equals(CGBSM2TestData.sm2HashValue, hash));

        sourceFilePath = CGBTestData.TESTDATA_DIR +"sm2/test.bin";
        hash = SM3Toolkit.SM3HashFile(sm2PublicKey, sourceFilePath);

        byte[] sm3 = SM3Toolkit.SM3HashData(sm2PublicKey, FileHelper.read(sourceFilePath));

        assertTrue("testSM3HashFilePublicKeyString", Arrays.equals(sm3, hash));
    }

}
