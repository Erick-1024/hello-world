package test.cgb.cfca.x509.certificate;

import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;
import test.cgb.cfca.sadk.CGBTestData;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.x509.certificate.X509Cert;
import cfca.sadk.x509.certificate.X509CertVerifier;

public class CGBX509CertVerifierChainTestCase extends TestCase {

    final String ca1Path =  CGBTestData.TESTDATA_DIR +"chain/boc.cer";
    final String ca2Path =  CGBTestData.TESTDATA_DIR +"chain/oca1.cer";
    final String cert1Path =  CGBTestData.TESTDATA_DIR +"chain/boc_11015974610.cer";
    final String cert2Path =  CGBTestData.TESTDATA_DIR +"chain/oca1_00000002.cer";

    final String cert1PemPath =  CGBTestData.TESTDATA_DIR +"chain/boc_11015974610.crt";
    final String cert2PemPath =  CGBTestData.TESTDATA_DIR +"chain/oca1_00000002.crt";

    X509Cert ca1Cert = null;
    X509Cert ca2Cert = null;
    X509Cert user1Cert = null;
    X509Cert user2Cert = null;

    protected void setUp() throws Exception {
        super.setUp();
        X509CertVerifier.clearTrustCertsMap();

        ca1Cert = certFrom(ca1Path);
        ca2Cert = certFrom(ca2Path);
        user1Cert = certFrom(cert1Path);
        user2Cert = certFrom(cert2Path);

        user1Cert = certFrom(cert1PemPath);
        user2Cert = certFrom(cert2PemPath);

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUpdateTrustCertsMapString() throws PKIException {
        // updateTrustCertsMap
        X509CertVerifier.clearTrustCertsMap();
        X509CertVerifier.updateTrustCertsMap(ca1Path);

        Assert.assertTrue("testUpdateTrustCertsMapString", X509CertVerifier.validateCertSign(user1Cert));
        Assert.assertTrue("testUpdateTrustCertsMapString", X509CertVerifier.validateCertSign(user2Cert));

        // updateTrustCertsMap
        X509CertVerifier.clearTrustCertsMap();
        X509CertVerifier.updateTrustCertsMap(ca2Path);

        Assert.assertTrue("testUpdateTrustCertsMapString", X509CertVerifier.validateCertSign(user1Cert));
        Assert.assertTrue("testUpdateTrustCertsMapString", X509CertVerifier.validateCertSign(user2Cert));

        // updateTrustCertsMap
        X509CertVerifier.clearTrustCertsMap();
        X509CertVerifier.updateTrustCertsMap(ca1Path);
        X509CertVerifier.updateTrustCertsMap(ca2Path);

        Assert.assertTrue("testUpdateTrustCertsMapString", X509CertVerifier.validateCertSign(user1Cert));
        Assert.assertTrue("testUpdateTrustCertsMapString", X509CertVerifier.validateCertSign(user2Cert));
    }

    public void testUpdateTrustCertsMapX509CertArray() throws PKIException {
        // updateTrustCertsMap
        X509CertVerifier.clearTrustCertsMap();
        X509CertVerifier.updateTrustCertsMap(new X509Cert[] { ca1Cert });

        Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", X509CertVerifier.validateCertSign(user1Cert));
        Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", X509CertVerifier.validateCertSign(user2Cert));

        // updateTrustCertsMap
        X509CertVerifier.clearTrustCertsMap();
        X509CertVerifier.updateTrustCertsMap(new X509Cert[] { ca2Cert });

        Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", X509CertVerifier.validateCertSign(user1Cert));
        Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", X509CertVerifier.validateCertSign(user2Cert));

        // updateTrustCertsMap
        X509CertVerifier.clearTrustCertsMap();
        X509CertVerifier.updateTrustCertsMap(new X509Cert[] { ca1Cert, ca2Cert });

        Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", X509CertVerifier.validateCertSign(user1Cert));
        Assert.assertTrue("testUpdateTrustCertsMapX509CertArray", X509CertVerifier.validateCertSign(user2Cert));
    }

    public void testUpdateTrustCertsMapX509Cert() throws PKIException {
        // updateTrustCertsMap
        X509CertVerifier.clearTrustCertsMap();
        X509CertVerifier.updateTrustCertsMap(ca1Cert);

        Assert.assertTrue("testUpdateTrustCertsMapX509Cert", X509CertVerifier.validateCertSign(user1Cert));
        Assert.assertTrue("testUpdateTrustCertsMapX509Cert", X509CertVerifier.validateCertSign(user2Cert));

        // updateTrustCertsMap
        X509CertVerifier.clearTrustCertsMap();
        X509CertVerifier.updateTrustCertsMap(ca2Cert);

        Assert.assertTrue("testUpdateTrustCertsMapX509Cert", X509CertVerifier.validateCertSign(user1Cert));
        Assert.assertTrue("testUpdateTrustCertsMapX509Cert", X509CertVerifier.validateCertSign(user2Cert));

        // updateTrustCertsMap
        X509CertVerifier.clearTrustCertsMap();
        X509CertVerifier.updateTrustCertsMap(ca1Cert);
        X509CertVerifier.updateTrustCertsMap(ca2Cert);

        Assert.assertTrue("testUpdateTrustCertsMapX509Cert", X509CertVerifier.validateCertSign(user1Cert));
        Assert.assertTrue("testUpdateTrustCertsMapX509Cert", X509CertVerifier.validateCertSign(user2Cert));
    }

    public void testClearTrustCertsMap() throws PKIException, IOException {

        // updateTrustCertsMap
        X509CertVerifier.clearTrustCertsMap();
        X509CertVerifier.updateTrustCertsMap(ca1Cert);
        X509CertVerifier.updateTrustCertsMap(ca2Cert);

        Assert.assertTrue("testClearTrustCertsMap", X509CertVerifier.validateCertSign(user1Cert));
        Assert.assertTrue("testClearTrustCertsMap", X509CertVerifier.validateCertSign(user2Cert));

        X509CertVerifier.clearTrustCertsMap();

        try {
            Assert.assertFalse("testClearTrustCertsMap", X509CertVerifier.validateCertSign(user1Cert));
        } catch (PKIException e) {
            Assert.assertTrue("testClearTrustCertsMap", true);
        }
        try {
            Assert.assertFalse("testClearTrustCertsMap", X509CertVerifier.validateCertSign(user2Cert));
        } catch (PKIException e) {
            Assert.assertTrue("testClearTrustCertsMap", true);
        }

    }

    private final X509Cert certFrom(String certPath) throws PKIException, IOException {
        return new X509Cert(certPath);
    }

}
