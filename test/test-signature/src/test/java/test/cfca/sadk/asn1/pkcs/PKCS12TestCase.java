package test.cfca.sadk.asn1.pkcs;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.asn1.pkcs.PKCS12;
import cfca.sadk.org.bouncycastle.asn1.pkcs.Pfx;
import cfca.sadk.system.FileHelper;
import cfca.sadk.x509.certificate.X509Cert;

public class PKCS12TestCase {
    final char[] PFXPassword = "123123".toCharArray();
    final String PFXFile = "TestData/rsa/userTest.pfx";
    byte[] PFXData = null;

    PrivateKey PFXPrivateKey = null;
    X509Cert[] PFXPublicCerts = null;

    @Before
    public void setUp() throws Exception {
        PFXData = FileHelper.read(PFXFile);

        PKCS12 p12 = new PKCS12();

        p12.load(PFXData);
        p12.decrypt(PFXPassword);

        PFXPrivateKey = p12.getPrivateKey();
        PFXPublicCerts = p12.getCerts();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPKCS12() {

        PKCS12 p12 = new PKCS12();

        assertTrue("testPKCS12", p12 != null);
    }

    @Test
    public void testLoadPfx() {
        PKCS12 p12 = new PKCS12();

        Pfx _pfx = Pfx.getInstance(PFXData);
        p12.load(_pfx);

        assertTrue("testLoadPfx", p12 != null);
    }

    @Test
    public void testLoadString() throws PKIException {
        PKCS12 p12 = new PKCS12();

        p12.load(PFXFile);

        assertTrue("testLoadString", p12 != null);
    }

    @Test
    public void testLoadInputStream() throws Exception {
        PKCS12 p12 = new PKCS12();

        p12.load(new FileInputStream(PFXFile));

        assertTrue("testLoadInputStream", p12 != null);
    }

    @Test
    public void testLoadByteArray() throws PKIException, IOException {
        PKCS12 p12 = new PKCS12();

        p12.load(FileHelper.read(PFXFile));

        assertTrue("testLoadByteArray", p12 != null);
    }

    @Test
    public void testDecrypt() throws PKIException {
        PKCS12 p12 = new PKCS12();

        p12.load(PFXFile);
        p12.decrypt(PFXPassword);

        assertTrue("testDecrypt", p12 != null);

        try {
            p12.decrypt("TESTING".toCharArray());
        } catch (Exception e) {
            assertTrue("testDecrypt", true);
        }
    }

    @Test
    public void testGetCerts() throws PKIException {
        PKCS12 p12 = new PKCS12();

        p12.load(PFXFile);

        try {
            p12.getCerts();
            assertTrue("testGetCerts", false);
        } catch (PKIException e) {
            assertTrue("testGetCerts", true);
        }
        p12.decrypt(PFXPassword);

        assertTrue("testGetCerts", p12.getCerts() != null);
        assertTrue("testGetCerts", p12.getCerts().length == 1);
        assertTrue("testGetCerts", p12.getCerts()[0] != null);
    }

    @Test
    public void testGetPrivateKey() throws PKIException {
        PKCS12 p12 = new PKCS12();

        p12.load(PFXFile);

        try {
            p12.getPrivateKey();
            assertTrue("testGetPrivateKey", false);
        } catch (PKIException e) {
            assertTrue("testGetPrivateKey", true);
        }
        p12.decrypt(PFXPassword);

        assertTrue("testGetPrivateKey", p12.getPrivateKey() != null);
    }

    @Test
    public void testGeneratePfxFile() throws PKIException {

        String password = "TESTING";

        String fileName = "TestData/out/TEST.P12";

        String file = PKCS12.generatePfxFile(PFXPublicCerts[0], PFXPrivateKey, password, fileName);

        PKCS12 newP12 = new PKCS12();

        newP12.load(file);
        newP12.decrypt(password.toCharArray());

        assertTrue("testGeneratePfxFile", newP12.getPrivateKey() != null);

        assertTrue("testGeneratePfxFile", newP12.getPrivateKey().equals(PFXPrivateKey));
        assertTrue("testGeneratePfxFile", Arrays.equals(newP12.getCerts(), PFXPublicCerts));

    }

    @Test
    public void testGeneratePfxData() throws PKIException {

        String password = "TESTING";

        byte[] data = PKCS12.generatePfxData(PFXPublicCerts[0], PFXPrivateKey, password);

        PKCS12 newP12 = new PKCS12();

        newP12.load(data);
        newP12.decrypt(password.toCharArray());
        assertTrue("testGeneratePfxData", newP12.getPrivateKey() != null);

        assertTrue("testGeneratePfxData", newP12.getPrivateKey().equals(PFXPrivateKey));
        assertTrue("testGeneratePfxData", Arrays.equals(newP12.getCerts(), PFXPublicCerts));
    }

    @Test
    public void testGeneratePfx() throws PKIException {
        String password = "cfca1234";

        Pfx data = PKCS12.generatePfx(PFXPublicCerts[0], PFXPrivateKey, password);

        PKCS12 newP12 = new PKCS12();

        newP12.load(data);
        newP12.decrypt(password.toCharArray());
        assertTrue("testGeneratePfx", newP12.getPrivateKey() != null);

        assertTrue("testGeneratePfx", newP12.getPrivateKey().equals(PFXPrivateKey));
        assertTrue("testGeneratePfx", Arrays.equals(newP12.getCerts(), PFXPublicCerts));
    }

}
