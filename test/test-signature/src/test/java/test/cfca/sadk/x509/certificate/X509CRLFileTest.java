package test.cfca.sadk.x509.certificate;

import java.math.BigInteger;
import java.security.PublicKey;

import cfca.sadk.util.CertUtil;
import cfca.sadk.x509.certificate.X509CRLFile;
import cfca.sadk.x509.certificate.X509Cert;

public final class X509CRLFileTest {

    public static void main(String[] args) throws Exception {
        System.err.println("Finished");

        final String sm2FileText = "MIICMAIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFoBDB0vnnhR0gsAqT4Uieo84OJ0EV76ea/FvatojLQKofjlQkaRn8SKWAgMLI3IOLnXsMwggHgBgoqgRzPVQYBBAIBBIIB0DCCAcwwggFxoAMCAQICBSAAAACSMAwGCCqBHM9VAYN1BQAwITELMAkGA1UEBhMCQ04xEjAQBgNVBAoMCUNGQ0EgT0NBNjAeFw0xMjExMTkwMjE5NDlaFw0xMzAyMjcwMjE5NDlaMGYxCzAJBgNVBAYTAmNuMRswGQYDVQQKDBJDRkNBIE9wZXJhdGlvbiBDQTIxEDAOBgNVBAsMB0JPQy1UUEMxFDASBgNVBAsMC0luZGl2aWR1YWwyMRIwEAYDVQQDDAlyc2FmZjAwMTEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAT7sUFLTK4wBDrSnr1q8dwMhr+ws+Lfda9VFNid0J4YYQL0pSikQRPYw+U+/ckrZTe0loRLORr9FAMD9XztA2KGo08wTTAfBgNVHSMEGDAWgBQAkArr76OKEQ0Wwlgl7Q4nkO0YJzALBgNVHQ8EBAMCBsAwHQYDVR0OBBYEFIzMC06btck4k+a3q5jmwdQiohxWMAwGCCqBHM9VAYN1BQADRwAwRAIgF9pesommXwnUg0RCBNQDE9nqdkrVqWd2bBh8wpo+E/kCID5xx7sN61oqaol2T0EvG08U/kPV6QdAws3zWFQmiTw2";

        final X509Cert cer = CertUtil.getCertFromSM2(sm2FileText.getBytes("UTF8"));
        final PublicKey publicKey = cer.getPublicKey();

        final BigInteger certsn = new BigInteger("00a1000000999999", 16);

        System.err.println("Test:" + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) >> 20) + "m");

        long yTime = 0;
        long xTime = 0;

        long w = 1000;

        yTime = System.currentTimeMillis();
        xTime = System.currentTimeMillis();
        X509CRLFile file = new X509CRLFile("c:/TestData/newCRL_SM2_" + w + "w.crl", true);
        yTime = System.currentTimeMillis();
        System.err.println("LoadTime: " + (yTime - xTime));
        xTime = System.currentTimeMillis();
        System.err.println(file.verify(publicKey));
        yTime = System.currentTimeMillis();
        System.err.println("VerifyTime: " + (yTime - xTime));
        xTime = System.currentTimeMillis();
        System.err.println(file.verify(publicKey));
        yTime = System.currentTimeMillis();
        System.err.println("VerifyTime: " + (yTime - xTime));
        System.err.println(file.isRevoke(certsn));
        yTime = System.currentTimeMillis();
        System.err.println("RunTime: " + (yTime - xTime));
        xTime = System.currentTimeMillis();
        System.err.println(file.isRevoke(certsn));
        yTime = System.currentTimeMillis();
        System.err.println("RunTime: " + (yTime - xTime));

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
    }

}
