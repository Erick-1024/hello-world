package test.cgb.cfca.sadk;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cfca.sadk.cgb.toolkit.X509CertValidator;
import cfca.sadk.system.FileHelper;

public class CGBX509CertValidatorTestCase {

    @Before
    public void setUp() throws Exception {
        X509CertValidator.clearTrustCertsMap();
    }

    @After
    public void tearDown() throws Exception {

    }

    final int successResult = 1;

    final String topca = CGBTestData.TESTDATA_DIR +"rsa/test-topca.der";
    final String subca = CGBTestData.TESTDATA_DIR +"rsa/test-subca.der";
    final String test2048 = CGBTestData.TESTDATA_DIR +"rsa/test2048.der";
    final String test1024 = CGBTestData.TESTDATA_DIR +"rsa/test1024.der";

    final String sm2subca = CGBTestData.TESTDATA_DIR +"sm2/subca.cer";
    final String sm2cmbc = CGBTestData.TESTDATA_DIR +"sm2/cmbc.cer";

    @Test
    public void testUpdateTrustCertsMapString() throws Exception {

        final byte[] certBytes = FileHelper.read(test2048);

        X509CertValidator.updateTrustCertsMap(topca);
        int certResult = X509CertValidator.verifyCertificate(FileHelper.read(topca));
        Assert.assertTrue("testUpdateTrustCertsMapString", certResult == successResult);

        try {
            certResult = X509CertValidator.verifyCertificate(certBytes);

            Assert.assertTrue("testUpdateTrustCertsMapString", certResult != successResult);
        } catch (Exception e) {
            Assert.assertTrue("testUpdateTrustCertsMapString", true);
        }
        certResult = X509CertValidator.verifyCertificate(FileHelper.read(subca));
        Assert.assertTrue("testUpdateTrustCertsMapString", certResult == successResult);

        X509CertValidator.updateTrustCertsMap(subca);
        certResult = X509CertValidator.verifyCertificate(certBytes);
        Assert.assertTrue("testUpdateTrustCertsMapString", certResult == successResult);

        try {
            certResult = X509CertValidator.verifyCertificate(FileHelper.read(test1024));
            Assert.assertTrue("testUpdateTrustCertsMapString", certResult != successResult);
        } catch (Exception e) {
            Assert.assertTrue("testUpdateTrustCertsMapString", true);
        }

        //TEST SM2

        try {
            certResult = X509CertValidator.verifyCertificate(FileHelper.read(sm2cmbc));
            Assert.assertTrue("testUpdateTrustCertsMapString", certResult != successResult);
        } catch (Exception e) {
            Assert.assertTrue("testUpdateTrustCertsMapString", true);
        }
        X509CertValidator.updateTrustCertsMap(sm2subca);
        certResult = X509CertValidator.verifyCertificate(FileHelper.read(sm2cmbc));
        Assert.assertTrue("testUpdateTrustCertsMapString", certResult == successResult);

    }

    @Test
    public void testUpdateTrustCertsMapArrayList() throws Exception {

        final byte[] certBytes = FileHelper.read(test2048);

        int certResult = 0;
        try {
            certResult = X509CertValidator.verifyCertificate(certBytes);
            Assert.assertTrue("testUpdateTrustCertsMapArrayList", certResult != successResult);
        } catch (Exception e) {
            Assert.assertTrue("testUpdateTrustCertsMapArrayList", true);
        }

        ArrayList<String> trustCertPath = new ArrayList<String>();
        trustCertPath.add(topca);
        trustCertPath.add(subca);
        X509CertValidator.updateTrustCertsMap(trustCertPath);

        X509CertValidator.updateTrustCertsMap(topca);
        certResult = X509CertValidator.verifyCertificate(FileHelper.read(topca));
        Assert.assertTrue("testUpdateTrustCertsMapArrayList", certResult == successResult);

        certResult = X509CertValidator.verifyCertificate(FileHelper.read(subca));
        Assert.assertTrue("testUpdateTrustCertsMapArrayList", certResult == successResult);

        certResult = X509CertValidator.verifyCertificate(certBytes);
        Assert.assertTrue("testUpdateTrustCertsMapArrayList", certResult == successResult);

    }

    @Test
    public void testValidateCertSignature() throws Exception {
        final byte[] certBytes = FileHelper.read(test2048);

        boolean certResult = false;
        try {
            certResult = X509CertValidator.validateCertSignature(certBytes);
            Assert.assertTrue("testValidateCertSignature", !certResult);
        } catch (Exception e) {
            Assert.assertTrue("testValidateCertSignature", true);
        }

        X509CertValidator.updateTrustCertsMap(subca);
        certResult = X509CertValidator.validateCertSignature(certBytes);
        Assert.assertTrue("testValidateCertSignature", certResult);
    }

    @Test
    public void testVerifyCertificate() throws Exception {
        final byte[] certBytes = FileHelper.read(test2048);

        int certResult = 0;
        try {
            certResult = X509CertValidator.verifyCertificate(certBytes);
            Assert.assertTrue("testUpdateTrustCertsMapString", certResult != successResult);
        } catch (Exception e) {
            Assert.assertTrue("testVerifyCertificate", true);
        }

        X509CertValidator.updateTrustCertsMap(subca);
        certResult = X509CertValidator.verifyCertificate(certBytes);
        Assert.assertTrue("testUpdateTrustCertsMapString", certResult == successResult);
    }

    @Test
    public void testVerifyCertByCRL() throws Exception {

        final String crl1Path = CGBTestData.TESTDATA_DIR +"crl/crl1.crl";
        final String crl2Path = CGBTestData.TESTDATA_DIR +"crl/crl2.crl";

        final byte[] certBytes = FileHelper.read(CGBTestData.TESTDATA_DIR +"crl/test.cer");

        boolean revokedFlag = X509CertValidator.verifyCertByCRL(certBytes, crl1Path);

        Assert.assertTrue("testVerifyCertByCRL", revokedFlag == true);

        revokedFlag = X509CertValidator.verifyCertByCRL(certBytes, crl2Path);

        Assert.assertTrue("testVerifyCertByCRL", revokedFlag == false);
    }

    @Test
    public void testClearTrustCertsMap() throws Exception {
        final byte[] certBytes = FileHelper.read(test2048);

        boolean certResult = false;
        X509CertValidator.updateTrustCertsMap(subca);
        certResult = X509CertValidator.validateCertSignature(certBytes);
        Assert.assertTrue("testClearTrustCertsMap", certResult);

        X509CertValidator.clearTrustCertsMap();
        try {
            certResult = X509CertValidator.validateCertSignature(certBytes);
            Assert.assertTrue("testClearTrustCertsMap", !certResult);
        } catch (Exception e) {
            Assert.assertTrue("testClearTrustCertsMap", true);
        }

    }
}
