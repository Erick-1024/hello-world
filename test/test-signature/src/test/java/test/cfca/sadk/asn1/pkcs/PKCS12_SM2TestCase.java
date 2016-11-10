package test.cfca.sadk.asn1.pkcs;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.asn1.pkcs.PKCS12_SM2;
import cfca.sadk.org.bouncycastle.asn1.ASN1Primitive;
import cfca.sadk.org.bouncycastle.asn1.ASN1Sequence;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.sadk.x509.certificate.X509Cert;

public class PKCS12_SM2TestCase {

    final String SM2Password = "123123";
    final String SM2File = "TestData/sm2/userTest.sm2";
    byte[] SM2Data = null;

    PrivateKey SM2PrivateKey = null;
    X509Cert[] SM2PublicCerts = null;

    @Before
    public void setUp() throws Exception {
        SM2Data = Base64.decode(FileHelper.read(SM2File));

        PKCS12_SM2 p12 = new PKCS12_SM2();

        p12.load(SM2Data);
        p12.decrypt(SM2Password);

        SM2PrivateKey = p12.getPrivateKey();
        SM2PublicCerts = p12.getPublicCert();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetInstance() throws PKIException {

        PKCS12_SM2 P12 = PKCS12_SM2.getInstance(SM2Data);

        assertTrue("testGetInstance", P12 != null);
    }

    @Test
    public void testPKCS12_SM2() {

        PKCS12_SM2 P12 = new PKCS12_SM2();
        assertTrue("testPKCS12_SM2", P12 != null);

        try {
            P12.toASN1Primitive();
            assertTrue("testPKCS12_SM2ASN1Sequence", false);
        } catch (Exception e) {
            assertTrue("testPKCS12_SM2ASN1Sequence", true);
        }
    }

    @Test
    public void testPKCS12_SM2ASN1Sequence() throws PKIException {
        ASN1Sequence seq = ASN1Sequence.getInstance(SM2Data);

        PKCS12_SM2 P12 = new PKCS12_SM2(seq);
        assertTrue("testPKCS12_SM2ASN1Sequence", P12 != null);

        assertTrue("testPKCS12_SM2ASN1Sequence", P12.toASN1Primitive() != null);

    }

    @Test
    public void testPKCS12_SM2ASN1SequenceASN1Sequence() throws PKIException {

        ASN1Sequence privateInfo = ASN1Sequence.getInstance(Base64
                .decode("MEcGCiqBHM9VBgEEAgEGByqBHM9VAWgEMMcqjbO6BwtHUp3LbAFZzNf5li15/BHNPxAsGM1/DspiaT0v6G4eiGBv3TnFob/zmw=="));
        ASN1Sequence publicInfo = ASN1Sequence
                .getInstance(Base64
                        .decode("MIICjAYKKoEcz1UGAQQCAQSCAnwwggJ4MIICHaADAgECAgUQASkZUzAMBggqgRzPVQGDdQUAMB8xCzAJBgNVBAYTAkNOMRAwDgYDVQQKDAdCT0MgU00yMB4XDTE1MDQwMTA4MjcwNloXDTE3MDQwMTA4MjcwNlowejELMAkGA1UEBhMCQ04xFTATBgNVBAoMDENGQ0EgVEVTVCBDQTERMA8GA1UECwwITG9jYWwgUkExFTATBgNVBAsMDEluZGl2aWR1YWwtMTEqMCgGA1UEAwwhMDUxQOawkeeUn+a1i+ivlUAxMTIzMjMxMTIxMzEzMkAxMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEO69KlirHNzbJETD2sf544Wg01qIrLpUGpgaYE3ehyPPaCYaoCxlOC+n8bUAM64yT2B2lyCUnUZOCpGBQO8vmEKOB6DCB5TAfBgNVHSMEGDAWgBRr/hjaj0I6prhtsy6Igzo0osEw4TBIBgNVHSAEQTA/MD0GCGCBHIbvKgEBMDEwLwYIKwYBBQUHAgEWI2h0dHA6Ly93d3cuY2ZjYS5jb20uY24vdXMvdXMtMTQuaHRtMDcGA1UdHwQwMC4wLKAqoCiGJmh0dHA6Ly91Y3JsLmNmY2EuY29tLmNuL1NNMi9jcmw4NDYuY3JsMAsGA1UdDwQEAwID6DAdBgNVHQ4EFgQUCc9SdiNsOG8LRpPQBJsKdO4RVO8wEwYDVR0lBAwwCgYIKwYBBQUHAwIwDAYIKoEcz1UBg3UFAANHADBEAiAfGc8JFIXLtEHEO3QCoXQJdLU1qCRvHhboW/Eowg3M8wIgOgGbhuw6cr3kwA9Xoit0GN64bSB353rQq1h7+HmbDgA="));
        PKCS12_SM2 P12 = new PKCS12_SM2(publicInfo, privateInfo);
        assertTrue("testPKCS12_SM2ASN1SequenceASN1Sequence", P12 != null);

        assertTrue("testPKCS12_SM2ASN1SequenceASN1Sequence", P12.toASN1Primitive() != null);

    }

    @Test
    public void testLoad() throws PKIException {

        PKCS12_SM2 P12 = new PKCS12_SM2();
        P12.load(SM2Data);

        assertTrue("testLoad", P12 != null);

        P12.load(Base64.encode(SM2Data));

        assertTrue("testLoad", P12 != null);

        assertTrue("testLoad", P12.toASN1Primitive() != null);
    }

    @Test
    public void testParseSM2() throws PKIException {
        ASN1Sequence seq = ASN1Sequence.getInstance(SM2Data);
        PKCS12_SM2 P12 = new PKCS12_SM2();
        P12.parseSM2(seq);

        assertTrue("testParseSM2", P12 != null);

        assertTrue("testParseSM2", P12 != null);

        assertTrue("testParseSM2", P12.toASN1Primitive() != null);
    }

    @Test
    public void testGetPrivateKey() throws PKIException {
        PKCS12_SM2 P12 = new PKCS12_SM2();
        P12.load(SM2Data);

        try {
            P12.getPrivateKey();
            assertTrue("testGetPrivateKey", false);
        } catch (PKIException e) {
            assertTrue("testGetPrivateKey", true);
        }

        P12.decrypt(SM2Password);

        assertTrue("testGetPrivateKey", P12.getPrivateKey() != null);

        assertTrue("testGetPrivateKey", P12.getPrivateKey().equals(SM2PrivateKey));
    }

    @Test
    public void testGetPrivateKeyString() throws PKIException {
        PKCS12_SM2 P12 = new PKCS12_SM2();
        P12.load(SM2Data);

        PrivateKey privateKey = P12.getPrivateKey(SM2Password);
        assertTrue("testGetPrivateKeyString", privateKey != null);

        assertTrue("testGetPrivateKeyString", privateKey.equals(this.SM2PrivateKey));
        assertTrue("testGetPrivateKeyString", P12.getPrivateKey().equals(privateKey));
    }

    @Test
    public void testDecrypt() throws PKIException {
        PKCS12_SM2 P12 = new PKCS12_SM2();
        P12.load(SM2Data);

        try {
            P12.getPrivateKey();
            assertTrue("testGetPrivateKey", false);
        } catch (PKIException e) {
            assertTrue("testGetPrivateKey", true);
        }

        try {
            P12.decrypt("TESTING");
            assertTrue("testDecrypt", false);
        } catch (PKIException e) {
            assertTrue("testDecrypt", true);
        }

        P12.decrypt(SM2Password);

        assertTrue("testDecrypt", P12.getPrivateKey() != null);

        assertTrue("testDecrypt", P12.getPrivateKey().equals(SM2PrivateKey));

    }

    @Test
    public void testGetPublicCert() throws PKIException {
        PKCS12_SM2 P12 = new PKCS12_SM2();
        P12.load(SM2Data);

        assertTrue("testGetPublicCert", P12.getPublicCert() != null);

        assertTrue("testGetPublicCert", Arrays.equals(P12.getPublicCert(), SM2PublicCerts));
    }

    @Test
    public void testToASN1Primitive() throws PKIException, IOException {
        PKCS12_SM2 P12 = new PKCS12_SM2();
        P12.load(SM2Data);

        ASN1Primitive obj = P12.toASN1Primitive();

        assertTrue("testGetPublicCert", obj != null);

        PKCS12_SM2 newP12 = new PKCS12_SM2();
        newP12.parseSM2(ASN1Sequence.getInstance(obj.getEncoded()));

    }

    @Test
    public void testGenerateSM2File() throws PKIException, IOException {

        String password = "TESTING";
        String fileName = "TestData/out/P12.SM2";
        String file = PKCS12_SM2.generateSM2File(SM2PublicCerts[0], SM2PrivateKey, password, fileName);

        byte[] data = FileHelper.read(file);

        PKCS12_SM2 newP12 = new PKCS12_SM2();

        newP12.load(data);
        newP12.decrypt(password);
        assertTrue("testGenerateSM2File", newP12.getPrivateKey() != null);

        assertTrue("testGenerateSM2File", newP12.getPrivateKey().equals(SM2PrivateKey));
        assertTrue("testGenerateSM2File", Arrays.equals(newP12.getPublicCert(), SM2PublicCerts));
    }

    @Test
    public void testGenerateSM2Data() throws PKIException {
        String password = "TESTING";
        byte[] data = PKCS12_SM2.generateSM2Data(SM2PublicCerts[0], SM2PrivateKey, password);

        PKCS12_SM2 newP12 = new PKCS12_SM2();

        newP12.load(data);
        newP12.decrypt(password);
        assertTrue("testGenerateSM2File", newP12.getPrivateKey() != null);

        assertTrue("testGenerateSM2File", newP12.getPrivateKey().equals(SM2PrivateKey));
        assertTrue("testGenerateSM2File", Arrays.equals(newP12.getPublicCert(), SM2PublicCerts));
    }

}
