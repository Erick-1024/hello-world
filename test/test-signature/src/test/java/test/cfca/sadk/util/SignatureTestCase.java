package test.cfca.sadk.util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Assert;

import test.cfca.sadk.testdata.SM2TestData;
import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.org.bouncycastle.asn1.sm2.ASN1SM2Signature;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public class SignatureTestCase extends TestCase {

    Session session;

    Signature engine;

    String hashAlg;
    String signAlg;
    PrivateKey priKey;
    PublicKey pubKey;
    X509Cert cert;

    String sourceFilePath;
    String signedFilePath;
    String signedDataPathAttach;
    String signedDataPathDetach;
    String signedFilePathAttach;
    String signedFilePathDetach;

    String sourceHashPath;
    String signedHashPathP7;
    String signedHashPathP1;
    int signedByteLength = 64;

    protected void setUp() throws Exception {
        session = TestReady.openSession();
        engine = new Signature();

        hashAlg = "SM3";
        signAlg = Mechanism.SM3_SM2;
        priKey = SM2TestData.userPriKey;
        pubKey = SM2TestData.userPubKey;
        cert = SM2TestData.UserCert;

        sourceFilePath = "TestData/sm2/test.dat";
        signedFilePath = "TestData/sm2/test-sm3.sig";
        signedDataPathAttach = "TestData/sm2/test-sm3-msg-attach.p7b";
        signedDataPathDetach = "TestData/sm2/test-sm3-msg-detach.p7b";
        signedFilePathAttach = "TestData/sm2/test-sm3-file-attach.p7b";
        signedFilePathDetach = "TestData/sm2/test-sm3-file-detach.p7b";

        sourceHashPath = "TestData/sm2/test-sm3.hash";
        signedHashPathP7 = "TestData/sm2/test-sm3-hash-signed.p7b";
        signedHashPathP1 = "TestData/sm2/test-sm3-hash-signed.p1";

    }

    protected void tearDown() throws Exception {
        session = null;
        engine = null;
    }

    public final void testGetSignerX509CertFromP7SignData() throws Exception {

        byte[] signedData = FileHelper.read(signedDataPathDetach);

        X509Cert signerCert = engine.getSignerX509CertFromP7SignData(signedData);

        assertTrue("testGetSignerX509CertFromP7SignData", cert.equals(signerCert));

        signedData = FileHelper.read(signedDataPathDetach);

        signerCert = engine.getSignerX509CertFromP7SignData(signedData);

        assertTrue("testGetSignerX509CertFromP7SignData", cert.equals(signerCert));

    }

    public final void testGetContentFromP7SignData() throws Exception {

        byte[] sourceData = FileHelper.read(sourceFilePath);
        byte[] signedData = FileHelper.read(signedDataPathAttach);

        byte[] content = engine.getContentFromP7SignData(signedData);
        assertTrue("testGetContentFromP7SignData", Arrays.equals(sourceData, content));
    }

    public final void testGetDigestAlgorithmFromP7SignData() throws Exception {

        byte[] signedData = FileHelper.read(signedDataPathAttach);

        String digestAlgorithm = engine.getDigestAlgorithmFromP7SignData(signedData);
        if (R32Version()) {
            assertTrue("testGetDigestAlgorithm", hashAlg.equals(digestAlgorithm));
        }
        signedData = FileHelper.read(signedDataPathDetach);

        digestAlgorithm = engine.getDigestAlgorithmFromP7SignData(signedData);

        if (R32Version()) {
            assertTrue("testGetDigestAlgorithm", hashAlg.equals(digestAlgorithm));
        }
    }

    public final void testGetSourceData() throws Exception {

        byte[] sourceData = FileHelper.read(sourceFilePath);

        byte[] signedData = FileHelper.read(signedDataPathAttach);
        boolean verifyResult = engine.p7VerifyMessageAttach(signedData, session);

        assertTrue("testGetSourceData", verifyResult);
        assertTrue("testGetSourceData", Arrays.equals(sourceData, engine.getSourceData()));

    }

    public final void testGetSignature() throws Exception {

        byte[] signedData = FileHelper.read(signedDataPathAttach);
        boolean verifyResult = engine.p7VerifyMessageAttach(signedData, session);

        byte[] signature = engine.getSignature();

        assertTrue("testGetSignature", verifyResult);
        assertTrue("testGetSignature", signature != null);
        assertTrue("testGetSignature", signature.length == signedByteLength);
    }

    public final void testGetDigestAlgorithm() throws Exception {

        byte[] signedData = FileHelper.read(signedDataPathAttach);
        boolean verifyResult = engine.p7VerifyMessageAttach(signedData, session);

        String digestAlgorithm = engine.getDigestAlgorithm();

        assertTrue("testGetDigestAlgorithm", verifyResult);

        if (R32Version()) {
            assertTrue("testGetDigestAlgorithm", hashAlg.equals(digestAlgorithm));
        }

    }

    /**
     * @return 32系列新版本,无兼容31系列签名机制大小写和32版本不同的特性
     */
    boolean R32Version() {
        return true;
    }

    public final void testGetSignerCert() throws Exception {

        byte[] signedData = FileHelper.read(signedDataPathAttach);
        boolean verifyResult = engine.p7VerifyMessageAttach(signedData, session);

        assertTrue("testGetSignerCert", verifyResult);
        assertTrue("testGetSignerCert", cert.equals(engine.getSignerCert()));
    }

    public final void testP1SignByHash() throws Exception {

        byte[] hash = FileHelper.read(sourceHashPath);

        byte[] signedData = engine.p1SignByHash(signAlg, hash, priKey, session);

        sm2CheckedSignedBytesASN1Format(signedData);

        boolean verifyResult = engine.p1VerifyByHash(signAlg, hash, signedData, pubKey, session);

        assertTrue("testP1SignByHash", verifyResult);

    }

    final void sm2CheckedSignedBytesASN1Format(byte[] signedData) {
        if (Mechanism.SM3_SM2.equals(signAlg)) {

            byte[] signature = Base64.decode(signedData);

            ASN1SM2Signature.getInstance(signature);

            assertTrue("sm2CheckSignedBytesASN1Format", true);
        }

    }

    public final void testP7SignByHash() throws Exception {
        byte[] hash = FileHelper.read(sourceHashPath);
        byte[] signedData = engine.p7SignByHash(signAlg, hash, priKey, cert, session);

        boolean verifyResult = engine.p7VerifyByHash(hash, signedData, session);

        assertTrue("testP7SignByHash", verifyResult);

    }

    public final void testP1VerifyByHash() throws Exception {
        byte[] hash = FileHelper.read(sourceHashPath);
        byte[] signedData = FileHelper.read(signedHashPathP1);

        boolean verifyResult = engine.p1VerifyByHash(signAlg, hash, signedData, pubKey, session);

        assertTrue("testP1VerifyByHash", verifyResult);

        verifyResult = engine.p1VerifyByHash(signAlg, hash, Base64.decode(signedData), pubKey, session);

        assertTrue("testP1VerifyByHash", verifyResult);
    }

    public final void testP7VerifyByHash() throws Exception {
        byte[] hash = FileHelper.read(sourceHashPath);
        byte[] signedData = FileHelper.read(signedHashPathP7);

        boolean verifyResult = engine.p7VerifyByHash(hash, signedData, session);

        assertTrue("testrsaP7VerifyByHash", verifyResult);

        verifyResult = engine.p7VerifyByHash(hash, Base64.decode(signedData), session);

        assertTrue("testrsaP7VerifyByHash", verifyResult);
    }

    public final void testP1SignMessage() throws Exception {

        byte[] sourceData = FileHelper.read(sourceFilePath);
        byte[] signedData = engine.p1SignMessage(signAlg, sourceData, priKey, session);

        sm2CheckedSignedBytesASN1Format(signedData);

        boolean verifyResult = engine.p1VerifyMessage(signAlg, sourceData, signedData, pubKey, session);

        assertTrue("testP1SignMessage", verifyResult);

    }

    public final void testP1SignFile() throws Exception {

        byte[] signedData = engine.p1SignFile(signAlg, sourceFilePath, priKey, session);

        sm2CheckedSignedBytesASN1Format(signedData);

        boolean result = engine.p1VerifyFile(signAlg, sourceFilePath, signedData, pubKey, session);

        Assert.assertTrue("testP1SignFile", result);

    }

    public final void testP7SignMessageAttach() throws Exception {

        byte[] sourceData = FileHelper.read(sourceFilePath);
        byte[] signedData = engine.p7SignMessageAttach(signAlg, sourceData, priKey, cert, session);

        boolean verifyResult = engine.p7VerifyMessageAttach(signedData, session);

        assertTrue("testP7SignMessageAttach", verifyResult);
        verifyResult = engine.p7VerifyMessageAttach(Base64.decode(signedData), session);

        assertTrue("testP7SignMessageAttach", verifyResult);

    }

    public final void testP7SignMessageDetach() throws Exception {

        byte[] sourceData = FileHelper.read(sourceFilePath);
        byte[] signedData = engine.p7SignMessageDetach(signAlg, sourceData, priKey, cert, session);

        boolean verifyResult = engine.p7VerifyMessageDetach(sourceData, signedData, session);

        assertTrue("testP7SignMessageDetach", verifyResult);

        verifyResult = engine.p7VerifyMessageDetach(sourceData, Base64.decode(signedData), session);

        assertTrue("testP7SignMessageDetach", verifyResult);

    }

    public final void testP7SignFileAttach() throws Exception {

        String signFilePath = "TestData/out/test-sign-file-attach.p7b";
        String outFilePath = "TestData/out/test-sign-file-attach.bin";

        engine.p7SignFileAttach(signAlg, sourceFilePath, signFilePath, priKey, cert, session);

        boolean verifyResult = engine.p7VerifyFileAttach(signFilePath, outFilePath, session);

        assertTrue("testP7SignFileAttach", verifyResult);

    }

    public final void testP7SignFileDetach() throws Exception {

        byte[] signedData = engine.p7SignFileDetach(signAlg, sourceFilePath, priKey, cert, session);

        boolean verifyResult = engine.p7VerifyFileDetach(sourceFilePath, signedData, session);

        assertTrue("testP7SignFileDetach", verifyResult);

    }

    public final void testP1VerifyMessage() throws Exception {
        byte[] sourceData = FileHelper.read(sourceFilePath);
        byte[] signedData = FileHelper.read(signedFilePath);

        boolean verifyResult = engine.p1VerifyMessage(signAlg, sourceData, signedData, pubKey, session);

        assertTrue("testP1VerifyMessage", verifyResult);

        verifyResult = engine.p1VerifyMessage(signAlg, sourceData, Base64.decode(signedData), pubKey, session);

        assertTrue("testP1VerifyMessage", verifyResult);
    }

    public final void testP1VerifyFile() throws Exception {
        byte[] signedData = FileHelper.read(signedFilePath);
        boolean result = engine.p1VerifyFile(signAlg, sourceFilePath, signedData, pubKey, session);

        Assert.assertTrue("testP1VerifyFile", result);

        result = engine.p1VerifyFile(signAlg, sourceFilePath, Base64.decode(signedData), pubKey, session);

        Assert.assertTrue("testP1VerifyFile", result);
    }

    public final void testP7VerifyMessageAttach() throws Exception {

        byte[] signedData = FileHelper.read(signedDataPathAttach);
        boolean verifyResult = engine.p7VerifyMessageAttach(signedData, session);

        assertTrue("testP7VerifyMessageAttach", verifyResult);

        verifyResult = engine.p7VerifyMessageAttach(Base64.decode(signedData), session);

        assertTrue("testP7VerifyMessageAttach", verifyResult);
    }

    public final void testP7VerifyMessageDetach() throws Exception {

        byte[] sourceData = FileHelper.read(sourceFilePath);
        byte[] signedData = FileHelper.read(signedDataPathDetach);

        boolean verifyResult = engine.p7VerifyMessageDetach(sourceData, signedData, session);

        assertTrue("testP7VerifyMessageDetach", verifyResult);

        verifyResult = engine.p7VerifyMessageDetach(sourceData, Base64.decode(signedData), session);

        assertTrue("testP7VerifyMessageDetach", verifyResult);

    }

    public final void testP7VerifyFileAttach() throws Exception {
        String outFilePath = "TestData/out/test-sign-file-attach.p7b";

        boolean verifyResult = engine.p7VerifyFileAttach(signedFilePathAttach, outFilePath, session);
        assertTrue("testrsaP7VerifyFileAttach", verifyResult);
    }

    public final void testP7VerifyFileDetach() throws Exception {

        byte[] signedData = FileHelper.read(signedFilePathDetach);

        boolean verifyResult = engine.p7VerifyFileDetach(sourceFilePath, signedData, session);

        assertTrue("testP7VerifyFileDetach", verifyResult);

        verifyResult = engine.p7VerifyFileDetach(sourceFilePath, Base64.decode(signedData), session);

        assertTrue("testP7VerifyFileDetach", verifyResult);
    }

    public final void testGetTimeFromTimeStamp() throws Exception {
        // TODO

    }

}
