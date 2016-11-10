package test.cfca.sadk.x509.certificate;

import java.io.IOException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509CRLEntry;
import java.util.Arrays;
import java.util.Set;

import junit.framework.TestCase;
import test.cfca.sadk.testdata.RSATestData;
import test.cfca.sadk.testdata.SM2TestData;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.system.FileHelper;
import cfca.sadk.x509.certificate.X509CRL;
import cfca.sadk.x509.certificate.X509CRLFile;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public final class X509CRLFileTestCase extends TestCase {

    static final java.security.cert.X509CRL[] crlxs = new java.security.cert.X509CRL[2];
    static final X509CRL[] crlts = new X509CRL[2];
    static final String[] crlPaths = new String[] { "TestData/rsa/test.crl", "TestData/sm2/test.crl" };
    static final String[] certPaths = new String[] { "TestData/rsa/test.cer", "TestData/sm2/test.cer" };
    static final PublicKey[] publicKeys = new PublicKey[] { RSATestData.userPubKey, SM2TestData.userPubKey };
    static {
        try {
            byte[] certBytes = null;
            for (int i = 0; i < crlPaths.length; i++) {
                certBytes = FileHelper.read(crlPaths[i]);
                crlts[i] = new X509CRL(certBytes);
                crlxs[i] = SM2TestData.crlFrom(certBytes);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final byte[][] encodedCRLs = new byte[][] {
            HexBin.decode("30820512308203fc020101300b06092a864886f70d01010b30583117301506035504030c0e43464341204353205253412043413130302e060355040a0c274368696e612046696e616e6369616c2043657274696669636174696f6e20417574686f72697479310b300906035504061302434e170d3038313231383034333435365a170d3138313233313034333435365a3082032730140203010001170d3135303531383034333435365a301302024e21170d3135303531393034333435365a30140203030000170d3135303532313034333435365a30140203009c40170d3135303532313034333435365a30220203030001170d3135303532313034333435365a300c300a0603551d1504030a010130220203009c41170d3135303532313034333435365a300c300a0603551d1504030a010130220203030002170d3135303532313034333435365a300c300a0603551d1504030a010230220203009c42170d3135303532313034333435365a300c300a0603551d1504030a010230220203030003170d3135303532313034333435365a300c300a0603551d1504030a010330220203009c43170d3135303532313034333435365a300c300a0603551d1504030a010330220203030004170d3135303532313034333435365a300c300a0603551d1504030a010430220203009c44170d3135303532313034333435365a300c300a0603551d1504030a010430220203030005170d3135303532313034333435365a300c300a0603551d1504030a010530220203009c45170d3135303532313034333435365a300c300a0603551d1504030a010530220203030006170d3135303532313034333435365a300c300a0603551d1504030a010630220203009c46170d3135303532313034333435365a300c300a0603551d1504030a010630220203030007170d3135303532313034333435365a300c300a0603551d1504030a010730220203009c47170d3135303532313034333435365a300c300a0603551d1504030a010730220203030008170d3135303532313034333435365a300c300a0603551d1504030a010830220203009c48170d3135303532313034333435365a300c300a0603551d1504030a010830220203030009170d3135303532313034333435365a300c300a0603551d1504030a010930220203009c49170d3135303532313034333435365a300c300a0603551d1504030a010930220203300010170d3135303532313034333435365a300c300a0603551d1504030a010a30220203061a8a170d3135303532313034333435365a300c300a0603551d1504030a010aa047304530220603551d230101ff041830168014a4c0f4cec52ab141dd42bc4b1020df9d78aab94e300f0603551d130101ff040530030101ff300e0603551d0f0101ff040403020284300b06092a864886f70d01010b038201010080ed4a144f2e776c9a864f6d80407511991352c163f60280dfbd89e31577090547d67813b8452e0f9cd1c15b3357eb5b6357cc86b1cfbbd09895f75ce3dfa2eaf8d9ebbf3ebb0dc262afa370a6f3ab7fbf6c35d29a9eef58dd0a5a6750a113993b67c4fd8c96eb84e1db9cb32db6335e21be0a17fade81147a6c231fc3f0033a048ebace6a79f1b23dad76f25964ef77ce3c93572569ee3cd9786aebbe6392b4e23e0c1f825b125f12274c733f1c2bc64e8c9a1b99de7ec641799a0289b4b75479dfaffe66d075642d39d6b456ba68c9eeb0aa6128f23bf672513fc72c786f768ae10bb2350dbb5bb89538699cfb4fa2af5b423f0c8c6f3bdbf34a420ea759af"),
            HexBin.decode("3082044e308203fb020101300a06082a811ccf5501837530583117301506035504030c0e4346434120435320534d322043413130302e060355040a0c274368696e612046696e616e6369616c2043657274696669636174696f6e20417574686f72697479310b300906035504061302434e170d3038313231383034333435365a170d3138313233313034333435365a3082032730140203010001170d3135303531383034333435365a301302024e21170d3135303531393034333435365a30140203030000170d3135303532313034333435365a30140203009c40170d3135303532313034333435365a30220203030001170d3135303532313034333435365a300c300a0603551d1504030a010130220203009c41170d3135303532313034333435365a300c300a0603551d1504030a010130220203030002170d3135303532313034333435365a300c300a0603551d1504030a010230220203009c42170d3135303532313034333435365a300c300a0603551d1504030a010230220203030003170d3135303532313034333435365a300c300a0603551d1504030a010330220203009c43170d3135303532313034333435365a300c300a0603551d1504030a010330220203030004170d3135303532313034333435365a300c300a0603551d1504030a010430220203009c44170d3135303532313034333435365a300c300a0603551d1504030a010430220203030005170d3135303532313034333435365a300c300a0603551d1504030a010530220203009c45170d3135303532313034333435365a300c300a0603551d1504030a010530220203030006170d3135303532313034333435365a300c300a0603551d1504030a010630220203009c46170d3135303532313034333435365a300c300a0603551d1504030a010630220203030007170d3135303532313034333435365a300c300a0603551d1504030a010730220203009c47170d3135303532313034333435365a300c300a0603551d1504030a010730220203030008170d3135303532313034333435365a300c300a0603551d1504030a010830220203009c48170d3135303532313034333435365a300c300a0603551d1504030a010830220203030009170d3135303532313034333435365a300c300a0603551d1504030a010930220203009c49170d3135303532313034333435365a300c300a0603551d1504030a010930220203300010170d3135303532313034333435365a300c300a0603551d1504030a010a30220203061a8a170d3135303532313034333435365a300c300a0603551d1504030a010aa047304530220603551d230101ff0418301680149fa8976be52d91c24ce2bf37bf0bc8161d4d2c96300f0603551d130101ff040530030101ff300e0603551d0f0101ff040403020284300a06082a811ccf550183750341003c1ea69a97f4eb6cb96eabe7286df3219e61d82841d6041393afcb6575f05a28d62992cc574a97deee9e449a67217c8b6b3cb9cab3fd6b9fd3ee79fbcfb90bd4") };

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testX509CRLByteArray() throws PKIException, IOException {
        X509CRL cert = null;
        byte[] certBytes = null;
        X509CRLFile file = null;
        for (int i = 0; i < crlts.length; i++) {
            certBytes = FileHelper.read(crlPaths[i]);
            cert = new X509CRL(certBytes);
            assertTrue("testX509CRLByteArray", crlts[i].equals(cert));

            file = new X509CRLFile(crlPaths[i], false);
            assertTrue("X509CRLFile.getIssuer", file.getIssuer().equals(cert.getIssuer()));
            assertTrue("X509CRLFile.getNextUpdate", file.getNextUpdate().equals(cert.getNextUpdate()));
            assertTrue("X509CRLFile.getThisUpdate", file.getThisUpdate().equals(cert.getThisUpdate()));
            assertTrue("X509CRLFile.getSignatureAlgName", file.getSignatureAlgName().equals(cert.getSignatureAlgName()));
            assertTrue("X509CRLFile.getSignatureAlgOID", file.getSignatureAlgOID().equals(cert.getSignatureAlgOID()));
            assertTrue("X509CRLFile.getSignature", Arrays.equals(file.getSignature(), cert.getSignature()));
            System.err.println("Finished");
        }

        for (int i = 0; i < crlts.length; i++) {
            certBytes = FileHelper.read(crlPaths[i]);
            cert = new X509CRL(certBytes);
            assertTrue("testX509CRLByteArray", crlts[i].equals(cert));

            file = new X509CRLFile(crlPaths[i], true);
            assertTrue("X509CRLFile.getIssuer", file.getIssuer().equals(cert.getIssuer()));
            assertTrue("X509CRLFile.getNextUpdate", file.getNextUpdate().equals(cert.getNextUpdate()));
            assertTrue("X509CRLFile.getThisUpdate", file.getThisUpdate().equals(cert.getThisUpdate()));
            assertTrue("X509CRLFile.getSignatureAlgName", file.getSignatureAlgName().equals(cert.getSignatureAlgName()));
            assertTrue("X509CRLFile.getSignatureAlgOID", file.getSignatureAlgOID().equals(cert.getSignatureAlgOID()));
            assertTrue("X509CRLFile.getSignature", Arrays.equals(file.getSignature(), cert.getSignature()));
            System.err.println("Finished");
        }

    }

    public void testVerifyPublicKey() throws PKIException {
        X509CRLFile file = null;
        for (int i = 0; i < crlts.length; i++) {

            assertTrue("testVerifyPublicKey", crlts[i].verify(publicKeys[i]));
            file = new X509CRLFile(crlPaths[i], true);
            assertTrue("testVerifyPublicKey", file.verify(publicKeys[i]));
        }
    }

    public void testIsRevokeBigInteger() throws PKIException {
        Set<? extends X509CRLEntry> crlEntrys = null;

        X509CRLFile file = null;
        for (int i = 0; i < crlts.length; i++) {
            crlEntrys = crlxs[i].getRevokedCertificates();
            for (X509CRLEntry crlEntry : crlEntrys) {
                assertTrue("testIsRevokeBigInteger", crlEntry != null);
                assertTrue("testIsRevokeBigInteger", crlts[i].isRevoke(crlEntry.getSerialNumber()));
                file = new X509CRLFile(crlPaths[i], true);
                assertTrue("testIsRevokeBigInteger", file.isRevoke(crlEntry.getSerialNumber()));
            }
            assertTrue("testIsRevokeBigInteger", !crlts[i].isRevoke(new BigInteger("1000")));
            assertTrue("testIsRevokeBigInteger", !crlts[i].isRevoke(new BigInteger("1000")));
            assertTrue("testIsRevokeBigInteger", !file.isRevoke(new BigInteger("1000")));
        }

        for (int i = 0; i < crlts.length; i++) {
            crlEntrys = crlxs[i].getRevokedCertificates();
            for (X509CRLEntry crlEntry : crlEntrys) {
                assertTrue("testIsRevokeBigInteger", crlEntry != null);
                assertTrue("testIsRevokeBigInteger", crlts[i].isRevoke(crlEntry.getSerialNumber()));
                file = new X509CRLFile(crlPaths[i], false);
                assertTrue("testIsRevokeBigInteger", file.isRevoke(crlEntry.getSerialNumber()));
            }
            assertTrue("testIsRevokeBigInteger", !crlts[i].isRevoke(new BigInteger("1000")));
            assertTrue("testIsRevokeBigInteger", !crlts[i].isRevoke(new BigInteger("1000")));
            assertTrue("testIsRevokeBigInteger", !file.isRevoke(new BigInteger("1000")));
        }
    }

}
