package test.cfca.sadk.x509.certificate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import test.cfca.sadk.testdata.SM2TestData;
import junit.framework.TestCase;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.org.bouncycastle.asn1.ASN1Object;
import cfca.sadk.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import cfca.sadk.org.bouncycastle.asn1.x500.style.BCStrictStyle;
import cfca.sadk.org.bouncycastle.asn1.x500.style.BCStyle;
import cfca.sadk.org.bouncycastle.asn1.x500.style.RFC4519Style;
import cfca.sadk.org.bouncycastle.asn1.x509.Extensions;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.sadk.x509.certificate.CFCAStyle;
import cfca.sadk.x509.certificate.X509Cert;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class X509CertTestCase extends TestCase {

    final String head = "-----BEGIN CERTIFICATE-----"; // 27
    final String tail = "-----END CERTIFICATE-----"; // 25

    static final String[] base64Text = new String[2];
    static final X509Certificate[] cerxs = new X509Certificate[2];
    static final X509Cert[] certs = new X509Cert[2];

    static final String[] pemPaths = new String[] { "TestData/rsa/test.pem", "TestData/sm2/test.pem" };
    static final String[] certPaths = new String[] { "TestData/rsa/test.cer", "TestData/sm2/test.cer" };

    static final X509Cert[] cacerts = new X509Cert[2];
    static {
        try {
            byte[] certBytes = null;
            for (int i = 0; i < certPaths.length; i++) {
                certBytes = FileHelper.read(certPaths[i]);
                base64Text[i] = Base64.toBase64String(certBytes);
                cerxs[i] = SM2TestData.certFrom(certBytes);
                certs[i] = new X509Cert(pemPaths[i]);
            }
            cacerts[0] = new X509Cert("TestData/rsa/boca.cer");
            cacerts[1] = new X509Cert("TestData/sm2/oca1sm2.cer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testX509CertByteArray() throws PKIException {
        X509Cert cert = null;
        byte[] certBytes = null;
        for (int i = 0; i < certs.length; i++) {
            certBytes = base64Text[i].getBytes();
            cert = new X509Cert(certBytes);
            assertTrue("testX509CertByteArray", certs[i].equals(cert));
            assertTrue("testX509CertByteArray", cert.getCertStructure() != null);
        }
        for (int i = 0; i < certs.length; i++) {
            certBytes = Base64.decode(base64Text[i].getBytes());
            cert = new X509Cert(certBytes);
            assertTrue("testX509CertByteArray", certs[i].equals(cert));
            assertTrue("testX509CertByteArray", cert.getCertStructure() != null);
        }
        for (int i = 0; i < certs.length; i++) {
            certBytes = (head + base64Text[i] + tail).getBytes();
            cert = new X509Cert(certBytes);
            assertTrue("testX509CertByteArray", certs[i].equals(cert));
            assertTrue("testX509CertByteArray", cert.getCertStructure() != null);
        }

    }

    public void testX509CertInputStream() throws PKIException {
        X509Cert cert = null;
        byte[] certBytes = null;
        for (int i = 0; i < certs.length; i++) {
            certBytes = base64Text[i].getBytes();
            cert = new X509Cert(new ByteArrayInputStream(certBytes, 0, certBytes.length));
            assertTrue("testX509CertInputStream", certs[i].equals(cert));
            assertTrue("testX509CertInputStream", cert.getCertStructure() != null);
        }
        for (int i = 0; i < certs.length; i++) {
            certBytes = Base64.decode(base64Text[i].getBytes());
            cert = new X509Cert(new ByteArrayInputStream(certBytes, 0, certBytes.length));
            assertTrue("testX509CertInputStream", certs[i].equals(cert));
            assertTrue("testX509CertInputStream", cert.getCertStructure() != null);
        }
        for (int i = 0; i < certs.length; i++) {
            certBytes = (head + base64Text[i] + tail).getBytes();
            cert = new X509Cert(new ByteArrayInputStream(certBytes, 0, certBytes.length));
            assertTrue("testX509CertInputStream", certs[i].equals(cert));
            assertTrue("testX509CertInputStream", cert.getCertStructure() != null);
        }
    }

    public void testX509CertString() throws PKIException {
        X509Cert cert = null;
        for (int i = 0; i < pemPaths.length; i++) {
            cert = new X509Cert(pemPaths[i]);
            assertTrue("testX509CertString", certs[i].equals(cert));
            assertTrue("testX509CertString", cert.getCertStructure() != null);
        }
        for (int i = 0; i < certPaths.length; i++) {
            cert = new X509Cert(certPaths[i]);
            assertTrue("testX509CertString", certs[i].equals(cert));
            assertTrue("testX509CertString", cert.getCertStructure() != null);
        }

        final String[] pemFiles = new String[] { "TestData/rsa/test-noHead.pem", "TestData/rsa/test-noEnd.pem", "TestData/rsa/test-noHead-noEnd.pem", };
        for (int i = 0; i < pemFiles.length; i++) {
            cert = new X509Cert(pemFiles[i]);
            assertTrue("testX509CertString", certs[0].equals(cert));
            assertTrue("testX509CertString", cert.getCertStructure() != null);
        }
    }

    public void testX509CertCertificate() throws CertificateEncodingException {
        X509Cert cert = null;
        for (int i = 0; i < certs.length; i++) {
            cert = new X509Cert(cfca.sadk.org.bouncycastle.asn1.x509.Certificate.getInstance(cerxs[i].getEncoded()));
            assertTrue("testX509CertCertificate", certs[i].equals(cert));
        }

    }

    public void testGetCertStructure() throws CertificateEncodingException, PKIException {

        cfca.sadk.org.bouncycastle.asn1.x509.Certificate cer = null;
        for (int i = 0; i < certs.length; i++) {
            cer = cfca.sadk.org.bouncycastle.asn1.x509.Certificate.getInstance(cerxs[i].getEncoded());
            assertTrue("testGetCertStructure", certs[i].getCertStructure().equals(cer));
        }
    }

    public void testGetEncoded() throws CertificateEncodingException, PKIException {
        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetEncoded", Arrays.equals(certs[i].getEncoded(), cerxs[i].getEncoded()));
            assertTrue("testGetEncoded", Arrays.equals(certs[i].getEncoded(), Base64.decode(base64Text[i])));
        }
    }

    public void testGetVersion() throws PKIException {

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetVersion", certs[i].getVersion().getValue().intValue() == 2);
        }
    }

    public void testGetIssuer() throws PKIException {
        final String[] DNs = new String[] { "O=BOC, C=CN", "O=BOC SM2, C=CN" };
        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetIssuer", DNs[i].equals(certs[i].getIssuer()));
        }
    }

    public void testGetIssuerX500NameStyle() throws PKIException {
        String[] DNs = new String[] { "O=BOC, C=CN", "O=BOC SM2, C=CN" };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetIssuerX500NameStyle", DNs[i].equals(certs[i].getIssuer(CFCAStyle.INSTANCE)));
        }
        DNs = new String[] { "C=CN,O=BOC", "C=CN,O=BOC SM2" };
        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetIssuerX500NameStyle", DNs[i].equals(certs[i].getIssuer(BCStyle.INSTANCE)));
            assertTrue("testGetIssuerX500NameStyle", DNs[i].equals(certs[i].getIssuer(BCStrictStyle.INSTANCE)));
        }

        DNs = new String[] { "o=BOC,c=CN", "o=BOC SM2,c=CN" };
        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetIssuerX500NameStyle", DNs[i].equals(certs[i].getIssuer(RFC4519Style.INSTANCE)));
        }
    }

    public void testGetIssuerX500Name() throws PKIException {
        final String[] RDNs = new String[] { "C=CN,O=BOC", "C=CN,O=BOC SM2" };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetIssuerX500Name", RDNs[i].equals(certs[i].getIssuerX500Name().toString()));
        }
    }

    public void testGetSubject() throws PKIException {
        final String[] DNs = new String[] { //
        "CN=051@民生RSA@12323232323232@1, OU=Individual-1, OU=Local RA, O=CFCA TEST CA, C=CN",//
                "CN=051@民生测试@11232311213132@1, OU=Individual-1, OU=Local RA, O=CFCA TEST CA, C=CN"//
        };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetSubject", DNs[i].equals(certs[i].getSubject()));
        }
    }

    public void testGetSubjectX500NameStyle() throws PKIException {
        String[] DNs = new String[] { "CN=051@民生RSA@12323232323232@1, OU=Individual-1, OU=Local RA, O=CFCA TEST CA, C=CN",//
                "CN=051@民生测试@11232311213132@1, OU=Individual-1, OU=Local RA, O=CFCA TEST CA, C=CN" };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetSubjectX500NameStyle", DNs[i].equals(certs[i].getSubject(CFCAStyle.INSTANCE)));
        }
        DNs = new String[] { "C=CN,O=CFCA TEST CA,OU=Local RA,OU=Individual-1,CN=051@民生RSA@12323232323232@1", //
                "C=CN,O=CFCA TEST CA,OU=Local RA,OU=Individual-1,CN=051@民生测试@11232311213132@1" };
        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetSubjectX500NameStyle", DNs[i].equals(certs[i].getSubject(BCStyle.INSTANCE)));
            assertTrue("testGetSubjectX500NameStyle", DNs[i].equals(certs[i].getSubject(BCStrictStyle.INSTANCE)));
        }

        DNs = new String[] { "cn=051@民生RSA@12323232323232@1,ou=Individual-1,ou=Local RA,o=CFCA TEST CA,c=CN", //
                "cn=051@民生测试@11232311213132@1,ou=Individual-1,ou=Local RA,o=CFCA TEST CA,c=CN" };
        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetSubjectX500NameStyle", DNs[i].equals(certs[i].getSubject(RFC4519Style.INSTANCE)));
        }
    }

    public void testGetSubjectX500Name() throws PKIException {
        String[] RDNs = new String[] { "C=CN,O=CFCA TEST CA,OU=Local RA,OU=Individual-1,CN=051@民生RSA@12323232323232@1",//
                "C=CN,O=CFCA TEST CA,OU=Local RA,OU=Individual-1,CN=051@民生测试@11232311213132@1" };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("getSubjectX500Name", RDNs[i].equals(certs[i].getSubjectX500Name().toString()));
        }
    }

    public void testGetNotBefore() throws PKIException {
        final long[] times = new long[] { 1427877033000L, 1427876826000L };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetNotBefore", times[i] == certs[i].getNotBefore().getTime());
        }
    }

    public void testGetNotAfter() throws PKIException {
        final long[] times = new long[] { 1491035433000L, 1491035226000L };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetNotAfter", times[i] == certs[i].getNotAfter().getTime());
        }
    }

    public void testGetSerialNumber() throws PKIException {
        final BigInteger[] serialNumbers = new BigInteger[] { new BigInteger("68738947414"), new BigInteger("68738947411") };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetSerialNumber", serialNumbers[i].equals(certs[i].getSerialNumber()));
        }
    }

    public void testGetStringSerialNumber() throws PKIException {
        final String[] serialNumbers = new String[] { "1001291956", "1001291953" };
        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetStringSerialNumber", serialNumbers[i].equals(certs[i].getStringSerialNumber()));
        }
    }

    public void testGetSignatureAlgName() throws PKIException {
        final String[] signatureAlgNames = new String[] { "sha1WithRSAEncryption", "sm3WithSM2Encryption" };
        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetSignatureAlgName", signatureAlgNames[i].equals(certs[i].getSignatureAlgName()));
        }
    }

    public void testGetPublicKey() throws PKIException {
        final byte[][] encodeds = new byte[][] {//
                HexBin.decode("30820122300d06092a864886f70d01010105000382010f003082010a0282010100eff9c88984fee2904e11a67a9bf626f7671e272fe7115d333c2e456299402e18f7fd73c0f93d9a1b6ea3122e116de31d94885bc3b3a10d7c14b444f09bf11f48ecaeae9b4a64253df8345edf08afe7d1dd6a116c9ba468c17218b56fc143a9098ff0caa9ae3d62b988204b0b5dacc8b04622630cafc35845124afea36fffb27ed0a14f125878a007e3b49203b5d78e15356c33fbf2216401d9aedad33b007e157ab0fe84b307e35d7375d96c8bb88df7522f3dced8570a36067a6928fd47cf3084aa53f1527e9b90d0db9af4a8ac58f1bc28d7bb22e57460a04ecd8033e7adb5b68e834eb8078adf4f1e8517304df8d7c9d487d8c9102db95dffd0820e5bfb410203010001"),
                HexBin.decode("3059301306072a8648ce3d020106082a811ccf5501822d034200043baf4a962ac73736c91130f6b1fe78e16834d6a22b2e9506a606981377a1c8f3da0986a80b194e0be9fc6d400ceb8c93d81da5c82527519382a460503bcbe610") };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetPublicKey", Arrays.equals(encodeds[i], certs[i].getPublicKey().getEncoded()));
        }
    }

    public void testVerify() throws PKIException {

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testVerify", certs[i].verify(cacerts[i].getPublicKey()));
        }
    }

    public void testGetPublicKeyData() throws PKIException {
        final byte[][] encodeds = new byte[][] {//
                HexBin.decode("3082010a0282010100eff9c88984fee2904e11a67a9bf626f7671e272fe7115d333c2e456299402e18f7fd73c0f93d9a1b6ea3122e116de31d94885bc3b3a10d7c14b444f09bf11f48ecaeae9b4a64253df8345edf08afe7d1dd6a116c9ba468c17218b56fc143a9098ff0caa9ae3d62b988204b0b5dacc8b04622630cafc35845124afea36fffb27ed0a14f125878a007e3b49203b5d78e15356c33fbf2216401d9aedad33b007e157ab0fe84b307e35d7375d96c8bb88df7522f3dced8570a36067a6928fd47cf3084aa53f1527e9b90d0db9af4a8ac58f1bc28d7bb22e57460a04ecd8033e7adb5b68e834eb8078adf4f1e8517304df8d7c9d487d8c9102db95dffd0820e5bfb410203010001"),
                HexBin.decode("043baf4a962ac73736c91130f6b1fe78e16834d6a22b2e9506a606981377a1c8f3da0986a80b194e0be9fc6d400ceb8c93d81da5c82527519382a460503bcbe610") };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetPublicKeyData", Arrays.equals(encodeds[i], certs[i].getPublicKeyData()));
        }
    }

    public void testGetTBSCertificate() throws PKIException {

        final byte[][] encodeds = new byte[][] {
                HexBin.decode("308202e3a00302010202051001291956300d06092a864886f70d0101050500301b310b300906035504061302434e310c300a060355040a1303424f43301e170d3135303430313038333033335a170d3137303430313038333033335a3077310b300906035504061302434e31153013060355040a130c4346434120544553542043413111300f060355040b13084c6f63616c20524131153013060355040b130c496e646976696475616c2d313127302506035504030c1e30353140e6b091e7949f525341403132333233323332333233323332403130820122300d06092a864886f70d01010105000382010f003082010a0282010100eff9c88984fee2904e11a67a9bf626f7671e272fe7115d333c2e456299402e18f7fd73c0f93d9a1b6ea3122e116de31d94885bc3b3a10d7c14b444f09bf11f48ecaeae9b4a64253df8345edf08afe7d1dd6a116c9ba468c17218b56fc143a9098ff0caa9ae3d62b988204b0b5dacc8b04622630cafc35845124afea36fffb27ed0a14f125878a007e3b49203b5d78e15356c33fbf2216401d9aedad33b007e157ab0fe84b307e35d7375d96c8bb88df7522f3dced8570a36067a6928fd47cf3084aa53f1527e9b90d0db9af4a8ac58f1bc28d7bb22e57460a04ecd8033e7adb5b68e834eb8078adf4f1e8517304df8d7c9d487d8c9102db95dffd0820e5bfb410203010001a381e93081e6301f0603551d23041830168014cf709d61eb9d7c2eb8f7cb0240f7099dfe33748030480603551d200441303f303d060860811c86ef2a01013031302f06082b060105050702011623687474703a2f2f7777772e636663612e636f6d2e636e2f75732f75732d31342e68746d30380603551d1f0431302f302da02ba0298627687474703a2f2f7563726c2e636663612e636f6d2e636e2f5253412f63726c313633392e63726c300b0603551d0f0404030203e8301d0603551d0e0416041497e44a5785478cbff3112a0fd0d095c5f317857f30130603551d25040c300a06082b06010505070302"),
                HexBin.decode("3082021da00302010202051001291953300c06082a811ccf550183750500301f310b300906035504061302434e3110300e060355040a0c07424f4320534d32301e170d3135303430313038323730365a170d3137303430313038323730365a307a310b300906035504061302434e31153013060355040a0c0c4346434120544553542043413111300f060355040b0c084c6f63616c20524131153013060355040b0c0c496e646976696475616c2d31312a302806035504030c2130353140e6b091e7949fe6b58be8af9540313132333233313132313331333240313059301306072a8648ce3d020106082a811ccf5501822d034200043baf4a962ac73736c91130f6b1fe78e16834d6a22b2e9506a606981377a1c8f3da0986a80b194e0be9fc6d400ceb8c93d81da5c82527519382a460503bcbe610a381e83081e5301f0603551d230418301680146bfe18da8f423aa6b86db32e88833a34a2c130e130480603551d200441303f303d060860811c86ef2a01013031302f06082b060105050702011623687474703a2f2f7777772e636663612e636f6d2e636e2f75732f75732d31342e68746d30370603551d1f0430302e302ca02aa0288626687474703a2f2f7563726c2e636663612e636f6d2e636e2f534d322f63726c3834362e63726c300b0603551d0f0404030203e8301d0603551d0e0416041409cf5276236c386f0b4693d0049b0a74ee1154ef30130603551d25040c300a06082b06010505070302") };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetTBSCertificate", Arrays.equals(encodeds[i], certs[i].getTBSCertificate()));
        }
    }

    public void testGetSignature() throws PKIException {

        final byte[][] encodeds = new byte[][] {
                HexBin.decode("4a6b361c4bed48cd75a77d4b916eb4361af82ca386183a5d7dad8b4b0881921f977d2bc72c2f39c856e3230634330e5de5ed261bfedc480239aeefa01e19b8da883d19dedf69fde2f6b09d7c783d672dfe6c59ee24d1ad909ffd4326f1b4c615cb0ade913b4efb13f33033c2fa71dcf58a9ef5f28a14eb0efd1bea899be6ce212585288abd6739053143e3ad51f79ec242f4e02042c7b3ca8a15e919268d627a14f7797542163a5ba12000928621d63a3f27615abf2c30e47fd57f33a3931cea6faef764f787f52c52a8a5637b691ae79e999eb3b7f8be7039581205b00db94d90d50053e83b2094f18ae75d89f6301eebf0d6c76011065090efbd870bf0bbfc"),
                HexBin.decode("304402201f19cf091485cbb441c43b7402a1740974b535a8246f1e16e85bf128c20dccf302203a019b86ec3a72bde4c00f57a22b7418deb86d2077e77ad0ab587bf8799b0e00") };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetSignature", Arrays.equals(encodeds[i], certs[i].getSignature()));
        }
    }

    public void testGetSubjectKeyIdentifier() throws PKIException, IOException {
        final byte[][] encodeds = new byte[][] {//
        HexBin.decode("041497e44a5785478cbff3112a0fd0d095c5f317857f"), HexBin.decode("041409cf5276236c386f0b4693d0049b0a74ee1154ef") };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetSubjectKeyIdentifier", Arrays.equals(encodeds[i], certs[i].getSubjectKeyIdentifier().getEncoded()));
        }
    }

    public void testGetAuthorityKeyIdentifier() throws PKIException, IOException {
        final byte[][] encodeds = new byte[][] {//
        HexBin.decode("30168014cf709d61eb9d7c2eb8f7cb0240f7099dfe337480"), HexBin.decode("301680146bfe18da8f423aa6b86db32e88833a34a2c130e1") };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetAuthorityKeyIdentifier", Arrays.equals(encodeds[i], certs[i].getAuthorityKeyIdentifier().getEncoded()));
        }
    }

    public void testGetExtensionsData() throws PKIException, IOException {
        final byte[][] encodeds = new byte[][] {//
                HexBin.decode("3081e6301f0603551d23041830168014cf709d61eb9d7c2eb8f7cb0240f7099dfe33748030480603551d200441303f303d060860811c86ef2a01013031302f06082b060105050702011623687474703a2f2f7777772e636663612e636f6d2e636e2f75732f75732d31342e68746d30380603551d1f0431302f302da02ba0298627687474703a2f2f7563726c2e636663612e636f6d2e636e2f5253412f63726c313633392e63726c300b0603551d0f0404030203e8301d0603551d0e0416041497e44a5785478cbff3112a0fd0d095c5f317857f30130603551d25040c300a06082b06010505070302"),
                HexBin.decode("3081e5301f0603551d230418301680146bfe18da8f423aa6b86db32e88833a34a2c130e130480603551d200441303f303d060860811c86ef2a01013031302f06082b060105050702011623687474703a2f2f7777772e636663612e636f6d2e636e2f75732f75732d31342e68746d30370603551d1f0430302e302ca02aa0288626687474703a2f2f7563726c2e636663612e636f6d2e636e2f534d322f63726c3834362e63726c300b0603551d0f0404030203e8301d0603551d0e0416041409cf5276236c386f0b4693d0049b0a74ee1154ef30130603551d25040c300a06082b06010505070302") };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetExtensionsData", Arrays.equals(encodeds[i], certs[i].getExtensionsData().getEncoded()));
        }
    }

    public void testGetExtensionData() throws Exception {

        Map<ASN1ObjectIdentifier, byte[]> extItemRSAs = new HashMap<ASN1ObjectIdentifier, byte[]>();
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.35"), HexBin.decode("30168014cf709d61eb9d7c2eb8f7cb0240f7099dfe337480"));
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.32"), HexBin
                .decode("303f303d060860811c86ef2a01013031302f06082b060105050702011623687474703a2f2f7777772e636663612e636f6d2e636e2f75732f75732d31342e68746d"));
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.31"),
                HexBin.decode("302f302da02ba0298627687474703a2f2f7563726c2e636663612e636f6d2e636e2f5253412f63726c313633392e63726c"));
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.15"), HexBin.decode("030203e8"));
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.14"), HexBin.decode("041497e44a5785478cbff3112a0fd0d095c5f317857f"));
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.37"), HexBin.decode("300a06082b06010505070302"));

        Map<ASN1ObjectIdentifier, byte[]> extItemSM2s = new HashMap<ASN1ObjectIdentifier, byte[]>();
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.35"), HexBin.decode("301680146bfe18da8f423aa6b86db32e88833a34a2c130e1"));
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.32"), HexBin
                .decode("303f303d060860811c86ef2a01013031302f06082b060105050702011623687474703a2f2f7777772e636663612e636f6d2e636e2f75732f75732d31342e68746d"));
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.31"),
                HexBin.decode("302e302ca02aa0288626687474703a2f2f7563726c2e636663612e636f6d2e636e2f534d322f63726c3834362e63726c"));
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.15"), HexBin.decode("030203e8"));
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.14"), HexBin.decode("041409cf5276236c386f0b4693d0049b0a74ee1154ef"));
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.37"), HexBin.decode("300a06082b06010505070302"));

        @SuppressWarnings("rawtypes")
        Map[] extensionItems = new Map[2];
        extensionItems[0] = extItemRSAs;
        extensionItems[1] = extItemSM2s;

        Extensions extensions = null;
        ASN1ObjectIdentifier[] extensionOIDs = null;
        ASN1ObjectIdentifier extensionOID = null;

        ASN1Object extension = null;
        byte[] encoded = null;
        for (int i = 0; i < certs.length; i++) {
            extensions = certs[i].getExtensionsData();
            extensionOIDs = extensions.getExtensionOIDs();

            assertTrue("testGetExtensionData", extensionOIDs.length == extensionItems[i].size());

            for (int j = 0; j < extensionOIDs.length; j++) {
                extensionOID = extensionOIDs[j];
                extension = certs[i].getExtensionData(extensionOID);
                encoded = (byte[]) extensionItems[i].get(extensionOID);
                assertTrue("testGetExtensionData", extension != null);
                assertTrue("testGetExtensionData", encoded != null);
                assertTrue("testGetExtensionData", Arrays.equals(extension.getEncoded(), encoded));
                assertTrue("testGetExtensionByteData", Arrays.equals(extension.getEncoded(), certs[i].getExtensionByteData(extensionOID)));

            }
        }

        Map<ASN1ObjectIdentifier, byte[]> extItemCARSAs = new HashMap<ASN1ObjectIdentifier, byte[]>();
        extItemCARSAs.put(new ASN1ObjectIdentifier("2.5.29.35"), HexBin.decode("301680140dc87726e91a3110ea5c30d05b8bcfa34ec172ce"));
        extItemCARSAs.put(new ASN1ObjectIdentifier("2.5.29.19"), HexBin.decode("30030101ff"));
        extItemCARSAs
                .put(new ASN1ObjectIdentifier("2.5.29.31"),
                        HexBin.decode("3081f8304fa04da04ba4493047310b300906035504061302434e310d300b060355040a130443464341310c300a060355040b130343524c310c300a060355040b1303525341310d300b0603550403130463726c31307ca07aa07886766c6461703a2f2f31302e372e322e31323a3338392f636e3d63726c312c4f553d5253412c4f553d43524c2c4f3d434643412c433d434e3f63657274696669636174655265766f636174696f6e4c6973743f626173653f6f626a656374636c6173733d63524c446973747269627574696f6e506f696e743027a025a0238621687474703a2f2f31302e372e322e31362f63726c2f5253412f63726c312e63726c"));
        extItemCARSAs.put(new ASN1ObjectIdentifier("2.5.29.15"), HexBin.decode("030201fe"));
        extItemCARSAs.put(new ASN1ObjectIdentifier("2.5.29.14"), HexBin.decode("0414cf709d61eb9d7c2eb8f7cb0240f7099dfe337480"));

        Map<ASN1ObjectIdentifier, byte[]> extItemCASM2s = new HashMap<ASN1ObjectIdentifier, byte[]>();
        extItemCASM2s.put(new ASN1ObjectIdentifier("2.5.29.35"), HexBin.decode("30168014b5d8906f5cf0d833d263bd7eb2c38dc64f127a61"));
        extItemCASM2s.put(new ASN1ObjectIdentifier("2.5.29.19"), HexBin.decode("30030101ff"));
        extItemCASM2s.put(new ASN1ObjectIdentifier("2.5.29.31"),
                HexBin.decode("302f302da02ba0298627687474703a2f2f3231302e37342e34312e38372f726361736d322f534d322f63726c312e63726c"));
        extItemCASM2s.put(new ASN1ObjectIdentifier("2.5.29.15"), HexBin.decode("030201fe"));
        extItemCASM2s.put(new ASN1ObjectIdentifier("2.5.29.14"), HexBin.decode("04146bfe18da8f423aa6b86db32e88833a34a2c130e1"));

        extensionItems = new Map[2];
        extensionItems[0] = extItemCARSAs;
        extensionItems[1] = extItemCASM2s;

        for (int i = 0; i < cacerts.length; i++) {
            extensions = cacerts[i].getExtensionsData();
            extensionOIDs = extensions.getExtensionOIDs();

            assertTrue("testGetExtensionData", extensionOIDs.length == extensionItems[i].size());

            for (int j = 0; j < extensionOIDs.length; j++) {
                extensionOID = extensionOIDs[j];
                extension = cacerts[i].getExtensionData(extensionOID);
                encoded = (byte[]) extensionItems[i].get(extensionOID);

                assertTrue("testGetExtensionData", extension != null);
                assertTrue("testGetExtensionData", encoded != null);
                assertTrue("testGetExtensionData", Arrays.equals(extension.getEncoded(), encoded));
                assertTrue("testGetExtensionByteData", Arrays.equals(extension.getEncoded(), cacerts[i].getExtensionByteData(extensionOID)));

            }
        }

    }

    public void testGetExtensionByteData() throws Exception {
        Map<ASN1ObjectIdentifier, byte[]> extItemRSAs = new HashMap<ASN1ObjectIdentifier, byte[]>();
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.35"), HexBin.decode("30168014cf709d61eb9d7c2eb8f7cb0240f7099dfe337480"));
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.32"), HexBin
                .decode("303f303d060860811c86ef2a01013031302f06082b060105050702011623687474703a2f2f7777772e636663612e636f6d2e636e2f75732f75732d31342e68746d"));
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.31"),
                HexBin.decode("302f302da02ba0298627687474703a2f2f7563726c2e636663612e636f6d2e636e2f5253412f63726c313633392e63726c"));
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.15"), HexBin.decode("030203e8"));
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.14"), HexBin.decode("041497e44a5785478cbff3112a0fd0d095c5f317857f"));
        extItemRSAs.put(new ASN1ObjectIdentifier("2.5.29.37"), HexBin.decode("300a06082b06010505070302"));

        Map<ASN1ObjectIdentifier, byte[]> extItemSM2s = new HashMap<ASN1ObjectIdentifier, byte[]>();
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.35"), HexBin.decode("301680146bfe18da8f423aa6b86db32e88833a34a2c130e1"));
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.32"), HexBin
                .decode("303f303d060860811c86ef2a01013031302f06082b060105050702011623687474703a2f2f7777772e636663612e636f6d2e636e2f75732f75732d31342e68746d"));
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.31"),
                HexBin.decode("302e302ca02aa0288626687474703a2f2f7563726c2e636663612e636f6d2e636e2f534d322f63726c3834362e63726c"));
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.15"), HexBin.decode("030203e8"));
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.14"), HexBin.decode("041409cf5276236c386f0b4693d0049b0a74ee1154ef"));
        extItemSM2s.put(new ASN1ObjectIdentifier("2.5.29.37"), HexBin.decode("300a06082b06010505070302"));

        @SuppressWarnings("rawtypes")
        Map[] extensionItems = new Map[2];
        extensionItems[0] = extItemRSAs;
        extensionItems[1] = extItemSM2s;

        Extensions extensions = null;
        ASN1ObjectIdentifier[] extensionOIDs = null;
        ASN1ObjectIdentifier extensionOID = null;

        byte[] extension = null;
        byte[] encoded = null;
        for (int i = 0; i < certs.length; i++) {
            extensions = certs[i].getExtensionsData();
            extensionOIDs = extensions.getExtensionOIDs();

            assertTrue("testGetExtensionData", extensionOIDs.length == extensionItems[i].size());

            for (int j = 0; j < extensionOIDs.length; j++) {
                extensionOID = extensionOIDs[j];
                extension = certs[i].getExtensionByteData(extensionOID);
                encoded = (byte[]) extensionItems[i].get(extensionOID);
                assertTrue("testGetExtensionByteData", extension != null);
                assertTrue("testGetExtensionByteData", encoded != null);
                assertTrue("testGetExtensionByteData", Arrays.equals(extension, encoded));
                assertTrue("testGetExtensionByteData", Arrays.equals(extension, certs[i].getExtensionData(extensionOID).getEncoded()));

            }
        }

        Map<ASN1ObjectIdentifier, byte[]> extItemCARSAs = new HashMap<ASN1ObjectIdentifier, byte[]>();
        extItemCARSAs.put(new ASN1ObjectIdentifier("2.5.29.35"), HexBin.decode("301680140dc87726e91a3110ea5c30d05b8bcfa34ec172ce"));
        extItemCARSAs.put(new ASN1ObjectIdentifier("2.5.29.19"), HexBin.decode("30030101ff"));
        extItemCARSAs
                .put(new ASN1ObjectIdentifier("2.5.29.31"),
                        HexBin.decode("3081f8304fa04da04ba4493047310b300906035504061302434e310d300b060355040a130443464341310c300a060355040b130343524c310c300a060355040b1303525341310d300b0603550403130463726c31307ca07aa07886766c6461703a2f2f31302e372e322e31323a3338392f636e3d63726c312c4f553d5253412c4f553d43524c2c4f3d434643412c433d434e3f63657274696669636174655265766f636174696f6e4c6973743f626173653f6f626a656374636c6173733d63524c446973747269627574696f6e506f696e743027a025a0238621687474703a2f2f31302e372e322e31362f63726c2f5253412f63726c312e63726c"));
        extItemCARSAs.put(new ASN1ObjectIdentifier("2.5.29.15"), HexBin.decode("030201fe"));
        extItemCARSAs.put(new ASN1ObjectIdentifier("2.5.29.14"), HexBin.decode("0414cf709d61eb9d7c2eb8f7cb0240f7099dfe337480"));

        Map<ASN1ObjectIdentifier, byte[]> extItemCASM2s = new HashMap<ASN1ObjectIdentifier, byte[]>();
        extItemCASM2s.put(new ASN1ObjectIdentifier("2.5.29.35"), HexBin.decode("30168014b5d8906f5cf0d833d263bd7eb2c38dc64f127a61"));
        extItemCASM2s.put(new ASN1ObjectIdentifier("2.5.29.19"), HexBin.decode("30030101ff"));
        extItemCASM2s.put(new ASN1ObjectIdentifier("2.5.29.31"),
                HexBin.decode("302f302da02ba0298627687474703a2f2f3231302e37342e34312e38372f726361736d322f534d322f63726c312e63726c"));
        extItemCASM2s.put(new ASN1ObjectIdentifier("2.5.29.15"), HexBin.decode("030201fe"));
        extItemCASM2s.put(new ASN1ObjectIdentifier("2.5.29.14"), HexBin.decode("04146bfe18da8f423aa6b86db32e88833a34a2c130e1"));

        extensionItems = new Map[2];
        extensionItems[0] = extItemCARSAs;
        extensionItems[1] = extItemCASM2s;

        for (int i = 0; i < cacerts.length; i++) {
            extensions = cacerts[i].getExtensionsData();
            extensionOIDs = extensions.getExtensionOIDs();

            assertTrue("testGetExtensionData", extensionOIDs.length == extensionItems[i].size());

            for (int j = 0; j < extensionOIDs.length; j++) {
                extensionOID = extensionOIDs[j];
                extension = cacerts[i].getExtensionByteData(extensionOID);
                encoded = (byte[]) extensionItems[i].get(extensionOID);

                assertTrue("testGetExtensionByteData", extension != null);
                assertTrue("testGetExtensionByteData", encoded != null);
                assertTrue("testGetExtensionByteData", Arrays.equals(extension, encoded));
                assertTrue("testGetExtensionByteData", Arrays.equals(extension, cacerts[i].getExtensionData(extensionOID).getEncoded()));

            }
        }

    }

    public void testGetCRLDistributionPoints() throws PKIException, IOException {
        byte[][] encodeds = new byte[][] {//
        HexBin.decode("302f302da02ba0298627687474703a2f2f7563726c2e636663612e636f6d2e636e2f5253412f63726c313633392e63726c"), //
                HexBin.decode("302e302ca02aa0288626687474703a2f2f7563726c2e636663612e636f6d2e636e2f534d322f63726c3834362e63726c") };

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetCRLDistributionPoints", certs[i].getCRLDistributionPoints() != null);
            assertTrue("testGetCRLDistributionPoints", Arrays.equals(encodeds[i], certs[i].getCRLDistributionPoints().getEncoded()));
        }

        encodeds = new byte[][] {//
                HexBin.decode("3081f8304fa04da04ba4493047310b300906035504061302434e310d300b060355040a130443464341310c300a060355040b130343524c310c300a060355040b1303525341310d300b0603550403130463726c31307ca07aa07886766c6461703a2f2f31302e372e322e31323a3338392f636e3d63726c312c4f553d5253412c4f553d43524c2c4f3d434643412c433d434e3f63657274696669636174655265766f636174696f6e4c6973743f626173653f6f626a656374636c6173733d63524c446973747269627574696f6e506f696e743027a025a0238621687474703a2f2f31302e372e322e31362f63726c2f5253412f63726c312e63726c"), //
                HexBin.decode("302f302da02ba0298627687474703a2f2f3231302e37342e34312e38372f726361736d322f534d322f63726c312e63726c") };
        for (int i = 0; i < cacerts.length; i++) {
            assertTrue("testGetCRLDistributionPoints", cacerts[i].getCRLDistributionPoints() != null);
            assertTrue("testGetCRLDistributionPoints", Arrays.equals(encodeds[i], cacerts[i].getCRLDistributionPoints().getEncoded()));
        }
    }

    public void testGetBasicConstraints() throws IOException, PKIException {

        for (int i = 0; i < certs.length; i++) {
            assertTrue("testGetBasicConstraints", certs[i].getBasicConstraints() == null);
        }

        final byte[][] encodeds = new byte[][] {//
        HexBin.decode("30030101ff"), //
                HexBin.decode("30030101ff") };

        for (int i = 0; i < cacerts.length; i++) {
            assertTrue("testGetBasicConstraints", cacerts[i].getBasicConstraints() != null);
            assertTrue("testGetBasicConstraints", Arrays.equals(encodeds[i], cacerts[i].getBasicConstraints().getEncoded()));
        }

    }

}
