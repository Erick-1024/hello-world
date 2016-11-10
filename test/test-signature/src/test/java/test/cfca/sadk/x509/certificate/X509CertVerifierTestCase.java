package test.cfca.sadk.x509.certificate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;

import junit.framework.TestCase;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.org.bouncycastle.asn1.x509.DistributionPoint;
import cfca.sadk.system.FileHelper;
import cfca.sadk.x509.certificate.X509Cert;
import cfca.sadk.x509.certificate.X509CertVerifier;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class X509CertVerifierTestCase extends TestCase {

    final String topca = "TestData/verifiers/rsa/test-topca.der";
    final String subca = "TestData/verifiers/rsa/test-subca.der";
    final String test2048 = "TestData/verifiers/rsa/test2048.der";
    final String test1024 = "TestData/verifiers/rsa/test1024.der";

    final String sm2subca = "TestData/verifiers/sm2/subca.cer";
    final String sm2cmbc = "TestData/verifiers/sm2/cmbc.cer";

    protected void setUp() throws Exception {
        super.setUp();
        X509CertVerifier.clearTrustCertsMap();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUpdateTrustCertsMapString() throws PKIException, IOException {
        final byte[] certBytes = FileHelper.read(test2048);

        X509CertVerifier.updateTrustCertsMap(topca);

        boolean certResult = X509CertVerifier.validateCertSign(new X509Cert(topca));
        Assert.assertTrue("testUpdateTrustCertsMapString", certResult);

        try {
            certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));

            Assert.assertTrue("testUpdateTrustCertsMapString", !certResult);
        } catch (Exception e) {
            Assert.assertTrue("testUpdateTrustCertsMapString", true);
        }
        certResult = X509CertVerifier.validateCertSign(new X509Cert(subca));
        Assert.assertTrue("testUpdateTrustCertsMapString", certResult);

        X509CertVerifier.updateTrustCertsMap(subca);
        certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));
        Assert.assertTrue("testUpdateTrustCertsMapString", certResult);

        try {
            certResult = X509CertVerifier.validateCertSign(new X509Cert(test1024));
            Assert.assertTrue("testUpdateTrustCertsMapString", !certResult);
        } catch (Exception e) {
            Assert.assertTrue("testUpdateTrustCertsMapString", true);
        }

        //TEST SM2

        try {
            certResult = X509CertVerifier.validateCertSign(new X509Cert(sm2cmbc));
            Assert.assertTrue("testUpdateTrustCertsMapString", !certResult);
        } catch (Exception e) {
            Assert.assertTrue("testUpdateTrustCertsMapString", true);
        }
        X509CertVerifier.updateTrustCertsMap(sm2subca);
        certResult = X509CertVerifier.validateCertSign(new X509Cert(sm2cmbc));
        Assert.assertTrue("testUpdateTrustCertsMapString", certResult);
    }

    public void testUpdateTrustCertsMapX509Cert() throws IOException, PKIException {
        final byte[] certBytes = FileHelper.read(test2048);

        boolean certResult = false;
        try {
            certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));
            Assert.assertTrue("testUpdateTrustCertsMapX509Cert", !certResult);
        } catch (Exception e) {
            Assert.assertTrue("testUpdateTrustCertsMapX509Cert", true);
        }

        X509CertVerifier.updateTrustCertsMap(new X509Cert(topca));
        X509CertVerifier.updateTrustCertsMap(new X509Cert(subca));

        certResult = X509CertVerifier.validateCertSign(new X509Cert(topca));
        Assert.assertTrue("testUpdateTrustCertsMapX509Cert", certResult);

        certResult = X509CertVerifier.validateCertSign(new X509Cert(subca));
        Assert.assertTrue("testUpdateTrustCertsMapX509Cert", certResult);

        certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));
        Assert.assertTrue("testUpdateTrustCertsMapX509Cert", certResult);
    }

    public void testUpdateTrustCertsMapX509CertArray() throws IOException, PKIException {

        final byte[] certBytes = FileHelper.read(test2048);

        boolean certResult = false;
        try {
            certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));
            Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", !certResult);
        } catch (Exception e) {
            Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", true);
        }

        final X509Cert[] certs = new X509Cert[] { new X509Cert(topca), new X509Cert(subca) };

        X509CertVerifier.updateTrustCertsMap(certs);
        certResult = X509CertVerifier.validateCertSign(new X509Cert(topca));
        Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", certResult);

        certResult = X509CertVerifier.validateCertSign(new X509Cert(subca));
        Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", certResult);

        certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));
        Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", certResult);
    }

    public void testClearTrustCertsMap() throws IOException, PKIException {
        final byte[] certBytes = FileHelper.read(test2048);

        boolean certResult = false;
        try {
            certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));
            Assert.assertTrue("testClearTrustCertsMap", !certResult);
        } catch (Exception e) {
            Assert.assertTrue("testClearTrustCertsMap", true);
        }

        final X509Cert[] certs = new X509Cert[] { new X509Cert(topca), new X509Cert(subca) };

        X509CertVerifier.updateTrustCertsMap(certs);
        certResult = X509CertVerifier.validateCertSign(new X509Cert(topca));
        Assert.assertTrue("testClearTrustCertsMap", certResult);

        certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));
        Assert.assertTrue("testClearTrustCertsMap", certResult);

        X509CertVerifier.clearTrustCertsMap();

        try {
            certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));
            Assert.assertTrue("testClearTrustCertsMap", certResult == false);
        } catch (Exception e) {
            Assert.assertTrue("testClearTrustCertsMap", true);
        }
    }

    public void testValidateCertSign() throws IOException, PKIException {
        final byte[] certBytes = FileHelper.read(test2048);

        boolean certResult = false;
        try {
            certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));
            Assert.assertTrue("testValidateCertSign", !certResult);
        } catch (Exception e) {
            Assert.assertTrue("testValidateCertSign", true);
        }

        final X509Cert[] certs = new X509Cert[] { new X509Cert(topca), new X509Cert(subca) };

        X509CertVerifier.updateTrustCertsMap(certs);
        certResult = X509CertVerifier.validateCertSign(new X509Cert(topca));
        Assert.assertTrue("testValidateCertSign", certResult);

        certResult = X509CertVerifier.validateCertSign(new X509Cert(certBytes));
        Assert.assertTrue("testValidateCertSign", certResult);
    }

    public void testVerifyCertDate() throws PKIException {
        final String testExpired = "TestData/verifiers/test.cer";

        boolean certResult = X509CertVerifier.verifyCertDate(new X509Cert(test1024));
        Assert.assertTrue("testVerifyCertDate", certResult);

        certResult = X509CertVerifier.verifyCertDate(new X509Cert(testExpired));
        Assert.assertTrue("testVerifyCertDate", !certResult);
    }

    public void testVerifyCertByCRLOutLine() throws PKIException {
        String crl1Path = "TestData/verifiers/crl1.crl";
        String crl2Path = "TestData/verifiers/crl2.crl";
        String cerPath = "TestData/verifiers/test.cer";
        Assert.assertTrue("testVerifyCertByCRLOutLine", X509CertVerifier.verifyCertByCRLOutLine(new X509Cert(cerPath), crl1Path));
        Assert.assertFalse("testVerifyCertByCRLOutLine", X509CertVerifier.verifyCertByCRLOutLine(new X509Cert(cerPath), crl2Path));
    }

    /**
     * 本测试用例在linux下跑不过，因为换行符windows是\n,linux是\r\n
     * @throws PKIException
     * @throws UnsupportedEncodingException
     */
    public void testGetCRLPointName() throws PKIException, UnsupportedEncodingException {
        final String codeCRLPint = new String(
                HexBin.decode("446973747269627574696f6e506f696e744e616d653a205b0d0a2020202066756c6c4e616d653a0d0a202020202020202047656e6572616c4e616d65733a0d0a20202020363a206c6461703a2f2f746573746c6461702e636663612e636f6d2e636e3a3338392f434e3d63726c3132365f3138342c4f553d43524c2c4f3d4346434120544553542043412c433d434e3f63657274696669636174655265766f636174696f6e4c6973743f626173653f6f626a656374636c6173733d63524c446973747269627574696f6e506f696e740d0a0d0a5d0d0a"),
                "UTF8");
        final String testCRLPoint = "TestData/verifiers/test.cer";

        X509Cert cert = new X509Cert(testCRLPoint);
        String crlPointName = X509CertVerifier.getCRLPointName(cert);

        DistributionPoint[] crlDistributionPoints = cert.getCRLDistributionPoints().getDistributionPoints();
        Assert.assertTrue("testGetCRLPointName", crlDistributionPoints != null);
        Assert.assertTrue("testGetCRLPointName", crlDistributionPoints.length == 2);

        Assert.assertTrue("testGetCRLPointName", crlPointName != null);
        //在做equals的时候，由于换行符的关系在linux下不相等
        Assert.assertTrue("testGetCRLPointName", crlPointName.equals(codeCRLPint));

        cert = new X509Cert(topca);
        Assert.assertTrue("testGetCRLPointName", cert.getCRLDistributionPoints() == null);
        try {
            crlPointName = X509CertVerifier.getCRLPointName(cert);
            Assert.assertTrue("testGetCRLPointName", crlPointName == null);
        } catch (PKIException e) {
            Assert.assertTrue("testGetCRLPointName", true);
        }
        cert = new X509Cert("TestData/rsa/test.cer");
        try {
            crlPointName = X509CertVerifier.getCRLPointName(cert);
            Assert.assertTrue("testGetCRLPointName", crlPointName == null);
        } catch (PKIException e) {
            Assert.assertTrue("testGetCRLPointName", true);
        }

    }

    public void testVerifyCertByLDAP() throws PKIException {
        X509Cert cert = new X509Cert("TestData/verifiers/test.cer");
        String crlPointName = X509CertVerifier.getCRLPointName(cert);
        System.err.println(crlPointName);
        
        boolean certResult = X509CertVerifier.verifyCertByLDAP(cert);
        Assert.assertTrue("testVerifyCertByLDAP", certResult);
//        System.err.println(cert.getCRLDistributionPoints().getDistributionPoints()[1]);

        cert = new X509Cert("TestData/rsa/test.cer");

        Assert.assertTrue("testVerifyCertByLDAP", cert.getCRLDistributionPoints().getDistributionPoints()[0] != null);

        try {
            certResult = X509CertVerifier.verifyCertByLDAP(cert);
            Assert.assertTrue("testVerifyCertByLDAP", certResult);
        } catch (Exception e) {
            Assert.assertTrue("testVerifyCertByLDAP", true);
        }
    }

}
