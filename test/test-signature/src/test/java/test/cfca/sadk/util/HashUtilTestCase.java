package test.cfca.sadk.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;

import junit.framework.TestCase;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.AlgorithmId;
import test.cfca.sadk.testdata.SM2TestData;
import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKCSObjectIdentifiers;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.algorithm.sm2.SM2PublicKey;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import cfca.sadk.org.bouncycastle.jcajce.provider.asymmetric.sm.SM2Params;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.HashUtil;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 *
 */
public class HashUtilTestCase extends TestCase {

    Session session = null;

    protected void setUp() throws Exception {
        session = TestReady.openSession();

    }

    protected void tearDown() throws Exception {
        session = null;
    }

    public final byte[] encodeSignature(ObjectIdentifier oid, byte[] digest) throws IOException {

        DerOutputStream out = new DerOutputStream();
        new AlgorithmId(oid).encode(out);
        out.putOctetString(digest);
        DerValue result = new DerValue(DerValue.tag_Sequence, out.toByteArray());
        return result.toByteArray();
    }

    public void testRsaHashMessageMD5() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = false;
        byte[] hash = HashUtil.RSAHashMessageByBC(sourceData, new Mechanism(Mechanism.MD5), ifDEREncoding);

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashval = md.digest(sourceData);

        assertTrue("testRsaHashMessageMD5", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashMessageByJNI(sourceData, new Mechanism(Mechanism.MD5), ifDEREncoding);
        assertTrue("testRsaHashMessageMD5", Arrays.equals(hashval, hash));

    }

    public void testRsaHashMessageSHA1() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = false;
        byte[] hash = HashUtil.RSAHashMessageByBC(sourceData, new Mechanism(Mechanism.SHA1), ifDEREncoding);

        MessageDigest md = MessageDigest.getInstance("SHA1");
        byte[] hashval = md.digest(sourceData);

        assertTrue("testRsaHashMessageSHA1", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashMessageByJNI(sourceData, new Mechanism(Mechanism.SHA1), ifDEREncoding);
        assertTrue("testRsaHashMessageSHA1", Arrays.equals(hashval, hash));

    }

    public void testRsaHashMessageSHA256() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = false;
        byte[] hash = HashUtil.RSAHashMessageByBC(sourceData, new Mechanism(Mechanism.SHA256), ifDEREncoding);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashval = md.digest(sourceData);
        assertTrue("testRsaHashMessageSHA256", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashMessageByJNI(sourceData, new Mechanism(Mechanism.SHA256), ifDEREncoding);
        assertTrue("testRsaHashMessageSHA256", Arrays.equals(hashval, hash));

    }

    public void testRsaHashMessageSHA512() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = false;
        byte[] hash = HashUtil.RSAHashMessageByBC(sourceData, new Mechanism(Mechanism.SHA512), ifDEREncoding);

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashval = md.digest(sourceData);
        assertTrue("testRsaHashMessageSHA512", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashMessageByJNI(sourceData, new Mechanism(Mechanism.SHA512), ifDEREncoding);
        assertTrue("testRsaHashMessageSHA512", Arrays.equals(hashval, hash));

    }

    public void testRsaHashMessageDERMD5() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = true;
        byte[] hash = HashUtil.RSAHashMessageByBC(sourceData, new Mechanism(Mechanism.MD5), ifDEREncoding);

        ObjectIdentifier oid = new ObjectIdentifier(PKCSObjectIdentifiers.md5.getId());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashval = encodeSignature(oid, md.digest(sourceData));

        assertTrue("testRsaHashMessageDERMD5", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashMessageByJNI(sourceData, new Mechanism(Mechanism.MD5), ifDEREncoding);
        assertTrue("testRsaHashMessageDERMD5", Arrays.equals(hashval, hash));
    }

    public void testRsaHashMessageDERSHA1() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = true;
        byte[] hash = HashUtil.RSAHashMessageByBC(sourceData, new Mechanism(Mechanism.SHA1), ifDEREncoding);

        ObjectIdentifier oid = new ObjectIdentifier(PKCSObjectIdentifiers.sha1.getId());

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hashval = encodeSignature(oid, md.digest(sourceData));

        assertTrue("testRsaHashMessageDERSHA1", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashMessageByJNI(sourceData, new Mechanism(Mechanism.SHA1), ifDEREncoding);
        assertTrue("testRsaHashMessageDERSHA1", Arrays.equals(hashval, hash));
    }

    public void testRsaHashMessageDERSHA256() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = true;
        byte[] hash = HashUtil.RSAHashMessageByBC(sourceData, new Mechanism(Mechanism.SHA256), ifDEREncoding);

        ObjectIdentifier oid = new ObjectIdentifier(NISTObjectIdentifiers.id_sha256.getId());

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashval = encodeSignature(oid, md.digest(sourceData));

        assertTrue("testRsaHashMessageDERSHA256", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashMessageByJNI(sourceData, new Mechanism(Mechanism.SHA256), ifDEREncoding);
        assertTrue("testRsaHashMessageDERSHA256", Arrays.equals(hashval, hash));
    }

    public void testRsaHashMessageDERSHA512() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = true;
        byte[] hash = HashUtil.RSAHashMessageByBC(sourceData, new Mechanism(Mechanism.SHA512), ifDEREncoding);

        ObjectIdentifier oid = new ObjectIdentifier(NISTObjectIdentifiers.id_sha512.getId());

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashval = encodeSignature(oid, md.digest(sourceData));

        assertTrue("testRsaHashMessageDERSHA512", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashMessageByJNI(sourceData, new Mechanism(Mechanism.SHA512), ifDEREncoding);
        assertTrue("testRsaHashMessageDERSHA512", Arrays.equals(hashval, hash));
    }

    public void testRsaHashFileSHA1() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = false;
        byte[] hash = HashUtil.RSAHashFileByBC(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA1), ifDEREncoding);

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hashval = md.digest(sourceData);
        assertTrue("testRsaHashFileSHA1", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashFileByJNI(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA1), ifDEREncoding);
        assertTrue("testRsaHashFileSHA1", Arrays.equals(hashval, hash));

    }

    public void testRsaHashFileSHA256() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = false;
        byte[] hash = HashUtil.RSAHashFileByBC(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA256), ifDEREncoding);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashval = md.digest(sourceData);
        assertTrue("testRsaHashFileSHA256", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashFileByJNI(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA256), ifDEREncoding);
        assertTrue("testRsaHashFileSHA256", Arrays.equals(hashval, hash));

    }

    public void testRsaHashFileSHA512() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = false;
        byte[] hash = HashUtil.RSAHashFileByBC(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA512), ifDEREncoding);

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashval = md.digest(sourceData);
        assertTrue("testRsaHashFileSHA512", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashFileByJNI(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA512), ifDEREncoding);
        assertTrue("testRsaHashFileSHA512", Arrays.equals(hashval, hash));

    }

    public void testRsaHashFileDERMD5() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = true;
        byte[] hash = HashUtil.RSAHashFileByBC(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.MD5), ifDEREncoding);

        ObjectIdentifier oid = new ObjectIdentifier(PKCSObjectIdentifiers.md5.getId());

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashval = encodeSignature(oid, md.digest(sourceData));

        assertTrue("testRsaHashFileDERMD5", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashFileByJNI(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.MD5), ifDEREncoding);
        assertTrue("testRsaHashFileDERMD5", Arrays.equals(hashval, hash));
    }

    public void testRsaHashFileDERSHA1() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = true;
        byte[] hash = HashUtil.RSAHashFileByBC(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA1), ifDEREncoding);

        ObjectIdentifier oid = new ObjectIdentifier(PKCSObjectIdentifiers.sha1.getId());

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hashval = encodeSignature(oid, md.digest(sourceData));

        assertTrue("testRsaHashFileDERSHA1", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashFileByJNI(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA1), ifDEREncoding);
        assertTrue("testRsaHashFileDERSHA1", Arrays.equals(hashval, hash));
    }

    public void testRsaHashFileDERSHA256() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = true;
        byte[] hash = HashUtil.RSAHashFileByBC(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA256), ifDEREncoding);

        ObjectIdentifier oid = new ObjectIdentifier(NISTObjectIdentifiers.id_sha256.getId());

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashval = encodeSignature(oid, md.digest(sourceData));

        assertTrue("testRsaHashFileDERSHA256", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashFileByJNI(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA256), ifDEREncoding);
        assertTrue("testRsaHashFileDERSHA256", Arrays.equals(hashval, hash));
    }

    public void testRsaHashFileDERSHA512() throws Exception {
        String sourceFilePath = "TestData/rsa/test.dat";

        byte[] sourceData = FileHelper.read(sourceFilePath);

        boolean ifDEREncoding = true;
        byte[] hash = HashUtil.RSAHashFileByBC(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA512), ifDEREncoding);

        ObjectIdentifier oid = new ObjectIdentifier(NISTObjectIdentifiers.id_sha512.getId());

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashval = encodeSignature(oid, md.digest(sourceData));

        assertTrue("testRsaHashFileDERSHA512", Arrays.equals(hashval, hash));

        hash = HashUtil.RSAHashFileByJNI(new FileInputStream(sourceFilePath), new Mechanism(Mechanism.SHA512), ifDEREncoding);
        assertTrue("testRsaHashFileDERSHA512", Arrays.equals(hashval, hash));
    }

    public void testSm2HashMessageWithZValue_BC() throws PKIException {

        byte[] userId = SM2Params.getDefaultuserid();
        byte[] sourceData = SM2TestData.sm2Data;
        SM2PublicKey pubKey = SM2TestData.userPubKey;

        BigInteger pubX = pubKey.getPubX_Int();
        BigInteger pubY = pubKey.getPubY_Int();
        byte[] hash = HashUtil.SM2HashMessageByBCWithZValue(userId, sourceData, pubX, pubY);

        assertTrue("testSm2HashMessageWithZValue_BC", Arrays.equals(SM2TestData.sm2HashValue, hash));
    }

    public void testSm2HashMessageWithZValue_JNI() throws PKIException {

        byte[] userId = SM2Params.getDefaultuserid();
        byte[] sourceData = SM2TestData.sm2Data;
        SM2PublicKey pubKey = SM2TestData.userPubKey;
        byte[] pubX = pubKey.getPubX();
        byte[] pubY = pubKey.getPubY();
        byte[] hash = HashUtil.SM2HashMessageByJNIWithZValue(userId, sourceData, pubX, pubY);

        assertTrue("testSm2HashMessageWithZValue_JNI", Arrays.equals(SM2TestData.sm2HashValue, hash));
    }

    public void testSm2HashMessageWithoutZValue_BC() throws Exception {
        String sourceFilePath = "TestData/sm2/test.dat";
        byte[] sourceData = FileHelper.read(sourceFilePath);

        byte[] hash = HashUtil.SM2HashMessageByBCWithoutZValue(sourceData);

        byte[] digest = HexBin.decode("E136F149DF6825EA173020B58AC661E0C35DBE7B8D50BEDEE10E61636E75819F");

        assertTrue("testSm2HashMessageWithoutZValue_BC", Arrays.equals(digest, hash));
    }

    public void testSm2HashMessageWithoutZValue_JNI() throws Exception {
        String sourceFilePath = "TestData/sm2/test.dat";
        byte[] sourceData = FileHelper.read(sourceFilePath);

        byte[] hash = HashUtil.SM2HashMessageByJNIWithoutZValue(sourceData);

        byte[] digest = HexBin.decode("E136F149DF6825EA173020B58AC661E0C35DBE7B8D50BEDEE10E61636E75819F");

        assertTrue("testSm2HashMessageWithoutZValue_JNI", Arrays.equals(digest, hash));
    }

    public void testSm2HashFileWithZValue_BC() throws Exception {
        String sourceFilePath = "TestData/sm2/test.dat";

        byte[] userId = SM2Params.getDefaultuserid();

        SM2PublicKey pubKey = SM2TestData.userPubKey;
        BigInteger pubX = pubKey.getPubX_Int();
        BigInteger pubY = pubKey.getPubY_Int();
        byte[] hash = HashUtil.SM2HashFileByBCWithZValue(userId, new FileInputStream(sourceFilePath), pubX, pubY);

        byte[] digest = HexBin.decode("85F9563C0DD0D714DC8E5C132FE600518D0BB0F9D9827D1C7AE3F58680DCB11A");

        assertTrue("testSm2HashMessageWithZValue_BC", Arrays.equals(digest, hash));
    }

    public void testSm2HashFileWithZValue_JNI() throws Exception {
        String sourceFilePath = "TestData/sm2/test.dat";

        byte[] userId = SM2Params.getDefaultuserid();

        SM2PublicKey pubKey = SM2TestData.userPubKey;
        byte[] pubX = pubKey.getPubX();
        byte[] pubY = pubKey.getPubY();
        byte[] hash =

        HashUtil.SM2HashFileByJNIWithZValue(userId, new FileInputStream(sourceFilePath), pubX, pubY);

        byte[] digest = HexBin.decode("85F9563C0DD0D714DC8E5C132FE600518D0BB0F9D9827D1C7AE3F58680DCB11A");

        assertTrue("testSm2HashFileWithZValue_JNI", Arrays.equals(digest, hash));
    }

    public void testSm2HashFileWithoutZValue_BC() throws Exception {
        String sourceFilePath = "TestData/sm2/test.dat";

        byte[] hash = HexBin.decode("E136F149DF6825EA173020B58AC661E0C35DBE7B8D50BEDEE10E61636E75819F");

        byte[] digest = HashUtil.SM2HashFileByBCWithoutZValue(new FileInputStream(sourceFilePath));

        assertTrue("testSm2HashFileWithoutZValue_BC", Arrays.equals(digest, hash));
    }

    public void testSm2HashFileWithoutZValue_JNI() throws Exception {
        String sourceFilePath = "TestData/sm2/test.dat";

        byte[] hash = HexBin.decode("E136F149DF6825EA173020B58AC661E0C35DBE7B8D50BEDEE10E61636E75819F");

        byte[] digest =

        HashUtil.SM2HashFileByJNIWithoutZValue(new FileInputStream(sourceFilePath));

        assertTrue("testSm2HashFileWithoutZValue_BC", Arrays.equals(digest, hash));
    }

}
