package test.cfca.sadk.x509.certificate;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import junit.framework.TestCase;
import sun.security.util.BitArray;
import test.cfca.sadk.testdata.RSATestData;
import test.cfca.sadk.testdata.SM2TestData;
import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.org.bouncycastle.asn1.x500.style.BCStrictStyle;
import cfca.sadk.org.bouncycastle.asn1.x500.style.BCStyle;
import cfca.sadk.org.bouncycastle.asn1.x500.style.RFC4519Style;
import cfca.sadk.org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import cfca.sadk.org.bouncycastle.asn1.x509.BasicConstraints;
import cfca.sadk.org.bouncycastle.asn1.x509.Extension;
import cfca.sadk.org.bouncycastle.asn1.x509.KeyUsage;
import cfca.sadk.org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import cfca.sadk.util.HashUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.x509.certificate.CFCAStyle;
import cfca.sadk.x509.certificate.X509Cert;
import cfca.sadk.x509.certificate.X509CertGenerator;

public class X509CertGeneratorTestCase extends TestCase {

    final String DN1 = "CN = CFCA CS SM2 CA,O = China Financial Certification Authority, C = CN";
    final String DN2 = "CN = 051@民生测试@11232311213132@1,OU = Individual-1,  OU = Local RA,O = CFCA TEST CA,C = CN";

    final String RDN1 = "C=CN, O=China Financial Certification Authority, CN=CFCA CS SM2 CA";
    final String RDN2 = "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@民生测试@11232311213132@1";

    final byte[] issuerUniqueID = "issuerUniqueID".getBytes();

    final byte[] subjectUniqueID = "subjectUniqueID".getBytes();

    final BigInteger serialNumber = new BigInteger("10086");

    final boolean generatedRSA2048Flag = false;

    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    Date notBefore;

    Date notAfter;

    final PrivateKey sm2PrivateKey = SM2TestData.userPriKey;
    final PublicKey sm2PublicKey = SM2TestData.userPubKey;
    final PrivateKey rsaPrivateKey = RSATestData.userPriKey;
    final PublicKey rsaPublicKey = RSATestData.userPubKey;

    Session session = null;

    protected void setUp() throws Exception {
        super.setUp();
        session = TestReady.openSession();

        notBefore = formatter.parse("2008/12/18 12:34:56");
        notAfter = formatter.parse("2018/12/31 12:34:56");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        session = null;
    }

    public void testX509CertGenerator() throws PKIException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);
        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testX509CertGenerator", cert != null);
        assertTrue("testX509CertGenerator", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testX509CertGenerator", cert.verify(rsaPublicKey));
    }

    public void testSetSerialNumberString() throws PKIException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final String serialNumber = "9001";
        gen.setSerialNumber(serialNumber);

        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testSetSerialNumberString", cert != null);
        assertTrue("testSetSerialNumberString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetSerialNumberString", cert.verify(rsaPublicKey));
        assertTrue("testSetSerialNumberString", serialNumber.equals(cert.getSerialNumber().toString(16)));

    }

    public void testSetSerialNumberBigInteger() throws PKIException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final BigInteger serialNumber = new BigInteger("9001");
        gen.setSerialNumber(serialNumber);

        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testSetSerialNumberBigInteger", cert != null);
        assertTrue("testSetSerialNumberBigInteger", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetSerialNumberBigInteger", cert.verify(rsaPublicKey));
        assertTrue("testSetSerialNumberBigInteger", serialNumber.equals(cert.getSerialNumber()));
    }

    public void testSetSubjectString() throws PKIException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final String RDN = "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@SADK测试@11232311213132@1";

        final String DN = "CN = 051@SADK测试@11232311213132@1,OU = Individual-1,  OU = Local RA,O = CFCA TEST CA,C = CN";
        gen.setSubject(DN);

        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testSetSubjectString", cert != null);
        assertTrue("testSetSubjectString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetSubjectString", cert.verify(rsaPublicKey));
        assertTrue("testSetSubjectString", RDN.equals(cert.getSubject()));
    }

    public void testSetSubjectX500NameStyleString() throws PKIException {
        byte[] certBytes = null;
        X509Cert cert = null;
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final String DN = "CN = 051@SADK测试@11232311213132@1,OU = Individual-1,  OU = Local RA,O = CFCA TEST CA,C = CN";
        //
        gen.setSubject(CFCAStyle.INSTANCE, DN);
        certBytes = gen.generateX509Cert(rsaPrivateKey, session);
        cert = new X509Cert(certBytes);
        assertTrue("testSetSubjectX500NameStyleString", cert != null);
        assertTrue("testSetSubjectX500NameStyleString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetSubjectX500NameStyleString", cert.verify(rsaPublicKey));
        assertTrue("testSetSubjectX500NameStyleString",
                "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@SADK测试@11232311213132@1".equals(cert.getSubject()));
        //
        gen.setSubject(RFC4519Style.INSTANCE, DN);
        certBytes = gen.generateX509Cert(rsaPrivateKey, session);
        cert = new X509Cert(certBytes);
        assertTrue("testSetSubjectX500NameStyleString", cert != null);
        assertTrue("testSetSubjectX500NameStyleString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetSubjectX500NameStyleString", cert.verify(rsaPublicKey));
        assertTrue("testSetSubjectX500NameStyleString",
                "CN=051@SADK测试@11232311213132@1, OU=Individual-1, OU=Local RA, O=CFCA TEST CA, C=CN".equals(cert.getSubject()));
        //
        gen.setSubject(BCStyle.INSTANCE, DN);
        certBytes = gen.generateX509Cert(rsaPrivateKey, session);
        cert = new X509Cert(certBytes);
        assertTrue("testSetSubjectX500NameStyleString", cert != null);
        assertTrue("testSetSubjectX500NameStyleString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetSubjectX500NameStyleString", cert.verify(rsaPublicKey));
        assertTrue("testSetSubjectX500NameStyleString",
                "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@SADK测试@11232311213132@1".equals(cert.getSubject()));
        //
        gen.setSubject(BCStrictStyle.INSTANCE, DN);
        certBytes = gen.generateX509Cert(rsaPrivateKey, session);
        cert = new X509Cert(certBytes);
        assertTrue("testSetSubjectX500NameStyleString", cert != null);
        assertTrue("testSetSubjectX500NameStyleString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetSubjectX500NameStyleString", cert.verify(rsaPublicKey));
        assertTrue("testSetSubjectX500NameStyleString",
                "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@SADK测试@11232311213132@1".equals(cert.getSubject()));

    }

    public void testSetIssuerString() throws PKIException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final String RDN = "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@SADK测试@11232311213132@1";

        final String DN = "CN = 051@SADK测试@11232311213132@1,OU = Individual-1,  OU = Local RA,O = CFCA TEST CA,C = CN";
        gen.setIssuer(DN);

        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testSetIssuerString", cert != null);
        assertTrue("testSetIssuerString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetIssuerString", cert.verify(rsaPublicKey));
        assertTrue("testSetIssuerString", RDN.equals(cert.getIssuer()));
    }

    public void testSetIssuerX500NameStyleString() throws PKIException {
        byte[] certBytes = null;
        X509Cert cert = null;
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final String DN = "CN = 051@SADK测试@11232311213132@1,OU = Individual-1,  OU = Local RA,O = CFCA TEST CA,C = CN";
        //
        gen.setIssuer(CFCAStyle.INSTANCE, DN);
        certBytes = gen.generateX509Cert(rsaPrivateKey, session);
        cert = new X509Cert(certBytes);
        assertTrue("testSetIssuerX500NameStyleString", cert != null);
        assertTrue("testSetIssuerX500NameStyleString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetIssuerX500NameStyleString", cert.verify(rsaPublicKey));
        assertTrue("testSetIssuerX500NameStyleString",
                "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@SADK测试@11232311213132@1".equals(cert.getIssuer()));
        //
        gen.setIssuer(RFC4519Style.INSTANCE, DN);
        certBytes = gen.generateX509Cert(rsaPrivateKey, session);
        cert = new X509Cert(certBytes);
        assertTrue("testSetIssuerX500NameStyleString", cert != null);
        assertTrue("testSetIssuerX500NameStyleString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetIssuerX500NameStyleString", cert.verify(rsaPublicKey));
        assertTrue("testSetIssuerX500NameStyleString",
                "CN=051@SADK测试@11232311213132@1, OU=Individual-1, OU=Local RA, O=CFCA TEST CA, C=CN".equals(cert.getIssuer()));
        //
        gen.setIssuer(BCStyle.INSTANCE, DN);
        certBytes = gen.generateX509Cert(rsaPrivateKey, session);
        cert = new X509Cert(certBytes);
        assertTrue("testSetIssuerX500NameStyleString", cert != null);
        assertTrue("testSetIssuerX500NameStyleString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetIssuerX500NameStyleString", cert.verify(rsaPublicKey));
        assertTrue("testSetIssuerX500NameStyleString",
                "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@SADK测试@11232311213132@1".equals(cert.getIssuer()));
        //
        gen.setIssuer(BCStrictStyle.INSTANCE, DN);
        certBytes = gen.generateX509Cert(rsaPrivateKey, session);
        cert = new X509Cert(certBytes);
        assertTrue("testSetIssuerX500NameStyleString", cert != null);
        assertTrue("testSetIssuerX500NameStyleString", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetIssuerX500NameStyleString", cert.verify(rsaPublicKey));
        assertTrue("testSetIssuerX500NameStyleString",
                "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@SADK测试@11232311213132@1".equals(cert.getIssuer()));
    }

    public void testSetNotBefore() throws PKIException, ParseException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final Date time = formatter.parse("2016/12/18 12:34:56");
        gen.setNotBefore(time);

        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testSetNotBefore", cert != null);
        assertTrue("testSetNotBefore", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetNotBefore", cert.verify(rsaPublicKey));
        assertTrue("testSetNotBefore", time.equals(cert.getNotBefore()));
    }

    public void testSetNotAfter() throws PKIException, ParseException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final Date time = formatter.parse("2016/12/18 12:34:56");
        gen.setNotAfter(time);

        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testSetNotAfter", cert != null);
        assertTrue("testSetNotAfter", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetNotAfter", cert.verify(rsaPublicKey));
        assertTrue("testSetNotAfter", time.equals(cert.getNotAfter()));
    }

    public void testSetPublicKey() throws PKIException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SM3_SM2), rsaPublicKey);

        gen.setPublicKey(sm2PublicKey);

        byte[] certBytes = gen.generateX509Cert(sm2PrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testSetPublicKey", cert != null);
        assertTrue("testSetPublicKey", cert.getPublicKey().equals(sm2PublicKey));
        assertTrue("testSetPublicKey", cert.verify(sm2PublicKey));
    }

    public void testSetSignatureAlg() throws PKIException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final String signatureAlgorithm = Mechanism.SHA1_RSA;
        gen.setSignatureAlg(signatureAlgorithm);

        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testSetNotAfter", cert != null);
        assertTrue("testSetNotAfter", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetNotAfter", cert.verify(rsaPublicKey));
        assertTrue("testSetNotAfter", signatureAlgorithm.equals(cert.getSignatureAlgName()));
    }

    public void testSetIssuerUniqueID() throws PKIException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final byte[] uniqueID = "anqing".getBytes();
        gen.setIssuerUniqueID(uniqueID);

        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testSetIssuerUniqueID", cert != null);
        assertTrue("testSetIssuerUniqueID", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetIssuerUniqueID", cert.verify(rsaPublicKey));

        X509Certificate certx = certFrom(certBytes);

        assertTrue("testSetIssuerUniqueID", Arrays.equals(uniqueID, new BitArray(certx.getIssuerUniqueID()).toByteArray()));
    }

    public void testSetSubjectUniqueID() throws PKIException {
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final byte[] uniqueID = "anqing".getBytes();
        gen.setSubjectUniqueID(uniqueID);

        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testSetSubjectUniqueID", cert != null);
        assertTrue("testSetSubjectUniqueID", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testSetSubjectUniqueID", cert.verify(rsaPublicKey));

        X509Certificate certx = certFrom(certBytes);

        assertTrue("testSetSubjectUniqueID", Arrays.equals(uniqueID, new BitArray(certx.getSubjectUniqueID()).toByteArray()));
    }

    public void testAddExtension() throws Exception {
        byte[] keyIdentifier = HashUtil.RSAHashMessageByBC(rsaPublicKey.getEncoded(), new Mechanism(Mechanism.SHA1), false);
        byte[] keyid = HashUtil.RSAHashMessageByBC(rsaPublicKey.getEncoded(), new Mechanism(Mechanism.SHA1), false);
        X509CertGenerator gen = buildX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);
        Extension basicConstraints = new Extension(Extension.basicConstraints, true, new BasicConstraints(true).getEncoded());
        Extension keyUsage = new Extension(Extension.keyUsage, true, new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyCertSign).getEncoded());
        Extension authorityKeyIdentifier = new Extension(Extension.authorityKeyIdentifier, true, new AuthorityKeyIdentifier(keyIdentifier).getEncoded());
        Extension subjectKeyIdentifier = new Extension(Extension.subjectKeyIdentifier, true, new SubjectKeyIdentifier(keyid).getEncoded());

        gen.addExtension(basicConstraints);
        gen.addExtension(keyUsage);
        gen.addExtension(authorityKeyIdentifier);
        gen.addExtension(subjectKeyIdentifier);

        byte[] certBytes = gen.generateX509Cert(rsaPrivateKey, session);

        X509Cert cert = new X509Cert(certBytes);
        assertTrue("testAddExtension", cert != null);
        assertTrue("testAddExtension", cert.getPublicKey().equals(rsaPublicKey));
        assertTrue("testAddExtension", cert.verify(rsaPublicKey));

        assertTrue("testAddExtension", Arrays.equals(basicConstraints.getExtnValue().getOctets(), cert.getExtensionByteData(Extension.basicConstraints)));
        assertTrue("testAddExtension", Arrays.equals(keyUsage.getExtnValue().getOctets(), cert.getExtensionByteData(Extension.keyUsage)));
        assertTrue("testAddExtension",
                Arrays.equals(authorityKeyIdentifier.getExtnValue().getOctets(), cert.getExtensionByteData(Extension.authorityKeyIdentifier)));
        assertTrue("testAddExtension",
                Arrays.equals(subjectKeyIdentifier.getExtnValue().getOctets(), cert.getExtensionByteData(Extension.subjectKeyIdentifier)));

        X509Certificate certx = certFrom(certBytes);

        assertTrue("testAddExtension", Arrays.equals(basicConstraints.getExtnValue().getEncoded(), certx.getExtensionValue(Extension.basicConstraints.getId())));
        assertTrue("testAddExtension", Arrays.equals(keyUsage.getExtnValue().getEncoded(), certx.getExtensionValue(Extension.keyUsage.getId())));
        assertTrue("testAddExtension",
                Arrays.equals(authorityKeyIdentifier.getExtnValue().getEncoded(), certx.getExtensionValue(Extension.authorityKeyIdentifier.getId())));
        assertTrue("testAddExtension",
                Arrays.equals(subjectKeyIdentifier.getExtnValue().getEncoded(), certx.getExtensionValue(Extension.subjectKeyIdentifier.getId())));
    }

    public void testGenerateX509Cert() throws PKIException {
        if (generatedRSA2048Flag) {
            callX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), 2048);
        }
        callX509CertGenerator(new Mechanism(Mechanism.SHA256_RSA), 1024);
        callX509CertGenerator(new Mechanism(Mechanism.SHA512_RSA), 1024);
        callX509CertGenerator(new Mechanism(Mechanism.SHA1_RSA), 1024);
        callX509CertGenerator(new Mechanism(Mechanism.SM3_SM2), 256);
    }

    final void callX509CertGenerator(final Mechanism mechanism, int kLength) throws PKIException {

        KeyPair keyPair = null;
        if (mechanism.getMechanismType().contains("SM2")) {
            keyPair = KeyUtil.generateKeyPair(new Mechanism(Mechanism.SM2), kLength, session);
        } else {
            keyPair = KeyUtil.generateKeyPair(new Mechanism(Mechanism.RSA), kLength, session);
        }

        X509CertGenerator gen = buildX509CertGenerator(mechanism, keyPair.getPublic());

        byte[] certBytes = gen.generateX509Cert(keyPair.getPrivate(), session);

        X509Cert cert = new X509Cert(certBytes);

        assertTrue("callX509CertGenerator", cert != null);

        assertTrue("callX509CertGenerator", cert.getSubject().equals(RDN2));
        assertTrue("callX509CertGenerator", cert.getIssuer().equals(RDN1));
        assertTrue("callX509CertGenerator", cert.getSerialNumber().equals(serialNumber));
        assertTrue("callX509CertGenerator", cert.getNotBefore().equals(notBefore));
        assertTrue("callX509CertGenerator", cert.getNotAfter().equals(notAfter));
        assertTrue("callX509CertGenerator", cert.getVersion().getValue().equals(new BigInteger("2")));
        //在hard模式下报错，equals 
        //assertTrue("callX509CertGenerator", cert.getPublicKey().equals(keyPair.getPublic()));
        //是sansec的国密公钥类型，sadk有强校验
        //assertTrue("callX509CertGenerator", cert.verify(keyPair.getPublic()));
 
        X509Certificate certx = certFrom(certBytes);

        assertTrue("callX509CertGenerator", Arrays.equals(issuerUniqueID, new BitArray(certx.getIssuerUniqueID()).toByteArray()));
        assertTrue("callX509CertGenerator", Arrays.equals(subjectUniqueID, new BitArray(certx.getSubjectUniqueID()).toByteArray()));
    }

    final X509CertGenerator buildX509CertGenerator(final Mechanism mechanism, final PublicKey publicKey) throws PKIException {
        final String DN1 = "CN = CFCA CS SM2 CA,O = China Financial Certification Authority, C = CN";
        final String DN2 = "CN = 051@民生测试@11232311213132@1,OU = Individual-1,  OU = Local RA,O = CFCA TEST CA,C = CN";

        X509CertGenerator gen = new X509CertGenerator();
        gen.setIssuer(DN1);
        gen.setIssuerUniqueID(issuerUniqueID);
        gen.setNotBefore(notBefore);
        gen.setNotAfter(notAfter);
        gen.setPublicKey(publicKey);
        gen.setSerialNumber(serialNumber);
        gen.setSignatureAlg(mechanism.getMechanismType());
        gen.setSubject(DN2);
        gen.setSubjectUniqueID(subjectUniqueID);
        return gen;
    }

    final X509Certificate certFrom(byte[] certBytes) throws PKIException {
        return SM2TestData.certFrom(certBytes);
    }

}
