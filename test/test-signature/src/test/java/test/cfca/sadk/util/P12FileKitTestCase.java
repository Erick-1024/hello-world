package test.cfca.sadk.util;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.asn1.pkcs.PKCS12;
import cfca.sadk.asn1.pkcs.PKCS12_SM2;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.util.P10Request;
import cfca.sadk.util.P12FileKit;
import cfca.sadk.x509.certificate.X509Cert;
import cfca.sadk.x509.certificate.X509CertGenerator;

public class P12FileKitTestCase {

    final String SM2Password = "123123";
    final String SM2File = "TestData/sm2/userTest.sm2";
    byte[] SM2Data = null;

    PrivateKey SM2PrivateKey = null;
    X509Cert[] SM2PublicCerts = null;

    final char[] PFXPassword = "123123".toCharArray();
    final String PFXFile = "TestData/rsa/userTest.pfx";
    byte[] PFXData = null;

    PrivateKey PFXPrivateKey = null;
    X509Cert[] PFXPublicCerts = null;

    @Before
    public void setUp() throws Exception {
        SM2Data = Base64.decode(FileHelper.read(SM2File));

        PKCS12_SM2 SM2 = new PKCS12_SM2();

        SM2.load(SM2Data);
        SM2.decrypt(SM2Password);

        SM2PrivateKey = SM2.getPrivateKey();
        SM2PublicCerts = SM2.getPublicCert();

        PFXData = FileHelper.read(PFXFile);

        PKCS12 PFX = new PKCS12();

        PFX.load(PFXData);
        PFX.decrypt(PFXPassword);

        PFXPrivateKey = PFX.getPrivateKey();
        PFXPublicCerts = PFX.getCerts();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSM2GenerateP12File() throws PKIException, IOException {
        String password = "TESTING";
        String fileName = "TestData/out/P12.SM2";
        String file = P12FileKit.SM2CombineP12File(SM2PublicCerts[0], SM2PrivateKey, password, fileName);

        byte[] data = FileHelper.read(file);

        PKCS12_SM2 newP12 = new PKCS12_SM2();

        newP12.load(data);
        newP12.decrypt(password);
        assertTrue("testSM2GenerateP12File", newP12.getPrivateKey() != null);

        assertTrue("testSM2GenerateP12File", newP12.getPrivateKey().equals(SM2PrivateKey));
        assertTrue("testSM2GenerateP12File", Arrays.equals(newP12.getPublicCert(), SM2PublicCerts));
    }

    @Test
    public void testSM2GenerateP12Data() throws PKIException {
        String password = "TESTING";
        byte[] data = P12FileKit.SM2CombineP12Data(SM2PublicCerts[0], SM2PrivateKey, password);

        PKCS12_SM2 newP12 = new PKCS12_SM2();

        newP12.load(data);
        newP12.decrypt(password);
        assertTrue("testSM2GenerateP12Data", newP12.getPrivateKey() != null);

        assertTrue("testSM2GenerateP12Data", newP12.getPrivateKey().equals(SM2PrivateKey));
        assertTrue("testSM2GenerateP12Data", Arrays.equals(newP12.getPublicCert(), SM2PublicCerts));
    }

    @Test
    public void testSM2GenerateKeyPair() throws PKIException {
        String password = "TESTING";
        int bitLength = 256;
        String base64EncryptedKeyData = P12FileKit.SM2GenerateKeyPair(bitLength, password);

        assertTrue("testPFXGenerateKeyPair", base64EncryptedKeyData != null);

        assertTrue("testPFXGenerateKeyPair", KeyUtil.getPrivateKeyFromSM2(base64EncryptedKeyData.getBytes(), password) != null);

        assertTrue("testPFXGenerateKeyPair", CertUtil.getCertFromSM2(base64EncryptedKeyData.getBytes()) != null);
    }

    @Test
    public void testSM2GenerateP10() throws PKIException {
        String password = "TESTING";
        int bitLength = 256;
        String base64EncryptedKeyData = P12FileKit.SM2GenerateKeyPair(bitLength, password);

        assertTrue("testSM2GenerateP10", base64EncryptedKeyData != null);

        String base64CertData = P12FileKit.SM2GenerateP10(base64EncryptedKeyData, password);

        assertTrue("testSM2GenerateP10", base64CertData != null);
    }

    @Test
    public void testSM2CombineP12() throws Exception {
        String password = "TESTING";
        int bitLength = 2048;
        String base64EncryptedKeyData = P12FileKit.SM2GenerateKeyPair(bitLength, password);

        assertTrue("testSM2CombineP12", base64EncryptedKeyData != null);

        String base64P10Data = P12FileKit.SM2GenerateP10(base64EncryptedKeyData, password);

        assertTrue("testSM2CombineP12", base64P10Data != null);

        Session session = session();

        P10Request P10 = new P10Request(session);
        P10.load(base64P10Data.getBytes());

        String base64CertData = signTestCert(session, SM2PrivateKey, P10.getPublicKey(), "SM2");
        assertTrue("testSM2CombineP12", base64CertData != null);
        String base64P12Data = P12FileKit.SM2CombineP12(base64EncryptedKeyData, base64CertData, password);
        assertTrue("testSM2CombineP12", base64P12Data != null);

        assertTrue("testSM2CombineP12", KeyUtil.getPrivateKeyFromSM2(base64P12Data.getBytes(), password) != null);

        assertTrue("testSM2CombineP12", CertUtil.getCertFromSM2(base64P12Data.getBytes()) != null);
    }

    @Test
    public void testPFXGenerateP12File() throws PKIException, IOException {

        String password = "TESTING";

        String fileName = "TestData/out/TEST.P12";

        String file = P12FileKit.RSACombineP12File(PFXPublicCerts[0], PFXPrivateKey, password, fileName);

        PKCS12 newP12 = new PKCS12();

        newP12.load(file);
        newP12.decrypt(password.toCharArray());

        assertTrue("testPFXGenerateP12File", newP12.getPrivateKey() != null);

        assertTrue("testPFXGenerateP12File", newP12.getPrivateKey().equals(PFXPrivateKey));
        assertTrue("testPFXGenerateP12File", Arrays.equals(newP12.getCerts(), PFXPublicCerts));
    }

    @Test
    public void testPFXGenerateP12Data() throws PKIException {

        String password = "TESTING";

        byte[] data = P12FileKit.RSACombineP12Data(PFXPublicCerts[0], PFXPrivateKey, password);

        PKCS12 newP12 = new PKCS12();

        newP12.load(data);
        newP12.decrypt(password.toCharArray());
        assertTrue("testGeneratePfxData", newP12.getPrivateKey() != null);

        assertTrue("testGeneratePfxData", newP12.getPrivateKey().equals(PFXPrivateKey));
        assertTrue("testGeneratePfxData", Arrays.equals(newP12.getCerts(), PFXPublicCerts));
    }

    @Test
    public void testPFXGenerateKeyPair() throws PKIException {
        String password = "TESTING";
        int bitLength = 2048;
        String base64EncryptedKeyData = P12FileKit.RSAGenerateKeyPair(bitLength, password);

        assertTrue("testPFXGenerateKeyPair", base64EncryptedKeyData != null);

        assertTrue("testPFXGenerateKeyPair", KeyUtil.getPrivateKeyFromPFX(base64EncryptedKeyData.getBytes(), password) != null);

        assertTrue("testPFXGenerateKeyPair", CertUtil.getCertFromPFX(base64EncryptedKeyData.getBytes(), password) != null);

    }

    @Test
    public void testPFXGenerateP10() throws PKIException {
        String password = "TESTING";
        int bitLength = 2048;
        String base64EncryptedKeyData = P12FileKit.RSAGenerateKeyPair(bitLength, password);

        assertTrue("testPFXGenerateP10", base64EncryptedKeyData != null);

        String base64P10Data = P12FileKit.RSAGenerateP10(base64EncryptedKeyData, password);

        assertTrue("testPFXGenerateP10", base64P10Data != null);

    }

    @Test
    public void testPFXCombineP12() throws Exception {
        String password = "TESTING";
        int bitLength = 2048;
        String base64EncryptedKeyData = P12FileKit.RSAGenerateKeyPair(bitLength, password);

        assertTrue("testPFXCombineP12", base64EncryptedKeyData != null);

        String base64P10Data = P12FileKit.RSAGenerateP10(base64EncryptedKeyData, password);

        assertTrue("testPFXCombineP12", base64P10Data != null);

        Session session = session();

        P10Request P10 = new P10Request(session);
        P10.load(base64P10Data.getBytes());

        String base64CertData = signTestCert(session, PFXPrivateKey, P10.getPublicKey(), "RSA");
        assertTrue("testPFXCombineP12", base64CertData != null);
        String base64P12Data = P12FileKit.RSACombineP12(base64EncryptedKeyData, base64CertData, password);
        assertTrue("testPFXCombineP12", base64P12Data != null);

        assertTrue("testPFXCombineP12", KeyUtil.getPrivateKeyFromPFX(base64P12Data.getBytes(), password) != null);

        assertTrue("testPFXCombineP12", CertUtil.getCertFromPFX(base64P12Data.getBytes(), password) != null);

    }

    static volatile Session session = null;

    static private final Session session() throws PKIException {
        if (session == null) {
            synchronized (Session.class) {
                if (session == null) {
                    try {
                        final String deviceName = JCrypto.JSOFT_LIB;
                        JCrypto.getInstance().initialize(deviceName, null);
                        session = JCrypto.getInstance().openSession(deviceName);
                    } catch (PKIException e) {
                        throw new PKIException("Open session failure: " + e.getMessage());
                    }
                }
            }
        }
        return session;

    }

    static private final String signTestCert(Session session, PrivateKey privateKey, PublicKey publicKey, String keyType) throws Exception {

        X509CertGenerator gen = new X509CertGenerator();

        String algorithm = Mechanism.RSA.equals(keyType) ? Mechanism.SHA256_RSA : Mechanism.SM3_SM2;

        String TESTDN = String.format("CN=%s P10 AGENT,OU=CFCA SADK P10 RSA ,O=CFCA TEST,C=CN", keyType);

        gen.setIssuer(TESTDN);
        gen.setSubject(TESTDN);
        gen.setSerialNumber(BigInteger.valueOf(1));
        gen.setNotAfter(new Date());
        gen.setNotBefore(new Date());
        gen.setPublicKey(publicKey);
        gen.setSignatureAlg(algorithm);

        byte[] encoding = gen.generateX509Cert(privateKey, session);

        return Base64.toBase64String(encoding);
    }

}
