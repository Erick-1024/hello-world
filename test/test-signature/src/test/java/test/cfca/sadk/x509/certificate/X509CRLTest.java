package test.cfca.sadk.x509.certificate;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509CRLEntry;
import java.util.Set;

import org.junit.Assert;

import test.cfca.sadk.testdata.SM2TestData;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.CertUtil;
import cfca.sadk.x509.certificate.X509CRL;
import cfca.sadk.x509.certificate.X509Cert;

public final class X509CRLTest {

    public static void main(String[] args) throws Exception {

        final String sm2FileText = "MIICMAIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFoBDB0vnnhR0gsAqT4Uieo84OJ0EV76ea/FvatojLQKofjlQkaRn8SKWAgMLI3IOLnXsMwggHgBgoqgRzPVQYBBAIBBIIB0DCCAcwwggFxoAMCAQICBSAAAACSMAwGCCqBHM9VAYN1BQAwITELMAkGA1UEBhMCQ04xEjAQBgNVBAoMCUNGQ0EgT0NBNjAeFw0xMjExMTkwMjE5NDlaFw0xMzAyMjcwMjE5NDlaMGYxCzAJBgNVBAYTAmNuMRswGQYDVQQKDBJDRkNBIE9wZXJhdGlvbiBDQTIxEDAOBgNVBAsMB0JPQy1UUEMxFDASBgNVBAsMC0luZGl2aWR1YWwyMRIwEAYDVQQDDAlyc2FmZjAwMTEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAT7sUFLTK4wBDrSnr1q8dwMhr+ws+Lfda9VFNid0J4YYQL0pSikQRPYw+U+/ckrZTe0loRLORr9FAMD9XztA2KGo08wTTAfBgNVHSMEGDAWgBQAkArr76OKEQ0Wwlgl7Q4nkO0YJzALBgNVHQ8EBAMCBsAwHQYDVR0OBBYEFIzMC06btck4k+a3q5jmwdQiohxWMAwGCCqBHM9VAYN1BQADRwAwRAIgF9pesommXwnUg0RCBNQDE9nqdkrVqWd2bBh8wpo+E/kCID5xx7sN61oqaol2T0EvG08U/kPV6QdAws3zWFQmiTw2";

        final X509Cert cer = CertUtil.getCertFromSM2(sm2FileText.getBytes("UTF8"));
        final PublicKey publicKey = cer.getPublicKey();

        long yTime = 0;
        long xTime = 0;
        long w = 100;

        yTime = System.currentTimeMillis();
        xTime = System.currentTimeMillis();
        X509CRL file = new X509CRL("c:/TestData/newCRL_SM2_" + w + "w.crl");
        file.loadAllRecordsToMemory();
        file.getCertificateList();
        System.err.println("############");
     

        yTime = System.currentTimeMillis();
        System.err.println("LoadTime: " + (yTime - xTime));
        System.err.println(file.getIssuer());

        long beginIndex = 1000000000000L + w * 10000 - 1;
        final long endIndex = 1000000000000L-1;
        xTime = System.currentTimeMillis();
        String text = null;

        BigInteger find = null;
        while (true) {
            if (beginIndex == endIndex ) {
                find = new BigInteger("a" + beginIndex, 16);
                text = "Passed-->" + (false == file.isRevoke(find));

                break;
            }
            find = new BigInteger("a" + beginIndex, 16);
            if (!file.isRevoke(find)) {
                text = "Failed: " + find.toString(16);
                break;
            }
         
            if (beginIndex % 100000 == 0) {
               
                System.err.println("Testing: " + beginIndex);
            }
            beginIndex--;
        }

        System.err.println("Finished: " + text);
        yTime = System.currentTimeMillis();
        System.err.println("RunTime: " + (yTime - xTime));

        System.err.println("Verify: " + file.verify(publicKey));

        final String[] crlPaths = new String[] { "TestData/rsa/test.crl", "TestData/sm2/test.crl" };

        final String crlPath = crlPaths[0];

        X509CRL crlts = new X509CRL(crlPath);

        java.security.cert.X509CRL crlxs = SM2TestData.crlFrom(FileHelper.read(crlPath));

        Set<? extends X509CRLEntry> crlEntrys = crlxs.getRevokedCertificates();
        for (X509CRLEntry crlEntry : crlEntrys) {
            Assert.assertTrue("testIsRevokeBigInteger", crlts.isRevoke(crlEntry.getSerialNumber()));
            Assert.assertTrue("testIsRevokeBigInteger", crlts.isRevoke(crlEntry.getSerialNumber().toString(16)));
        }

        System.err.println("Finished");

    }

}
