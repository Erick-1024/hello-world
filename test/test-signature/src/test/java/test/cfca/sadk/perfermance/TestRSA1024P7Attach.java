package test.cfca.sadk.perfermance;

import java.io.FileInputStream;

import cfca.sadk.util.CertUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.x509.certificate.X509Cert;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public final class TestRSA1024P7Attach extends TestRSA2048P7Attach {

    @Override
    void init() throws Exception {

        final String cmbcCertPath = "TestData/rsa/test1024.cer";
        final String sm2Path = "TestData/rsa/test1024.pfx";
        final String sm2Pass = "123123";
        priKey = KeyUtil.getPrivateKeyFromPFX(sm2Path, sm2Pass);
        cert = new X509Cert(new FileInputStream(cmbcCertPath));
        cert = CertUtil.getCertFromPFX(sm2Path, sm2Pass);
        certs = new X509Cert[] { cert };

    }

    @Override
    String getTestName() {
        return "P7Attach: RSA1024";
    }

    public static void main(String[] args) throws Exception {
        TestFrame.runTest(new TestRSA1024P7Attach(), true, args);
    }

}
