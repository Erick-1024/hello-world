package test.cfca.sadk.x509.certificate;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.X509CRLEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import junit.framework.TestCase;
import test.cfca.sadk.testdata.RSATestData;
import test.cfca.sadk.testdata.SM2TestData;
import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import cfca.sadk.org.bouncycastle.asn1.x500.style.BCStyle;
import cfca.sadk.org.bouncycastle.asn1.x500.style.RFC4519Style;
import cfca.sadk.org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import cfca.sadk.org.bouncycastle.asn1.x509.BasicConstraints;
import cfca.sadk.org.bouncycastle.asn1.x509.CRLReason;
import cfca.sadk.org.bouncycastle.asn1.x509.Extension;
import cfca.sadk.org.bouncycastle.asn1.x509.KeyUsage;
import cfca.sadk.org.bouncycastle.jce.provider.BouncyCastleProvider;
import cfca.sadk.util.HashUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.x509.certificate.CFCAStyle;
import cfca.sadk.x509.certificate.X509CRL;
import cfca.sadk.x509.certificate.X509CRLGenerator;

public class X509CRLGeneratorTestCase extends TestCase {
    final String DN = "CN = CFCA CS SM2 CA,O = China Financial Certification Authority, C = CN";
    final String RDN = "C=CN, O=China Financial Certification Authority, CN=CFCA CS SM2 CA";
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date thisUpdate;
    Date nextUpdate;
    final PrivateKey sm2PrivateKey = SM2TestData.userPriKey;
    final PublicKey sm2PublicKey = SM2TestData.userPubKey;
    final PrivateKey rsaPrivateKey = RSATestData.userPriKey;
    final PublicKey rsaPublicKey = RSATestData.userPubKey;

    Session session = null;

    protected void setUp() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        super.setUp();
        session = TestReady.openSession();
        thisUpdate = formatter.parse("2008/12/18 12:34:56");
        nextUpdate = formatter.parse("2018/12/31 12:34:56");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        session = null;
    }

    public void testX509CRLGenerator() throws PKIException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        byte[] certBytes = gen.generateCRL(rsaPrivateKey, session);

        X509CRL cert = new X509CRL(certBytes);

        assertTrue("testX509CRLGenerator", cert != null);
        assertTrue("testX509CRLGenerator", cert.verify(rsaPublicKey));

        java.security.cert.X509CRL certx = crlFrom(certBytes);
        try {
            certx.verify(rsaPublicKey);
            assertTrue("testX509CRLGenerator", true);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("testX509CRLGenerator", false);
        }
    }

    public void testAddRevokeCertStringDate() throws PKIException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final String serialNumber = "1920086";
        final Date revokeTime = dateFrom("2017/12/18 12:34:56");

        gen.addRevokeCert(serialNumber, revokeTime);

        byte[] certBytes = gen.generateCRL(rsaPrivateKey, session);

        final BigInteger sn = new BigInteger(serialNumber, 16);

        X509CRL cert = new X509CRL(certBytes);
        assertTrue("testAddRevokeCertStringDate", cert != null);
        assertTrue("testAddRevokeCertStringDate", cert.verify(rsaPublicKey));
        assertTrue("testAddRevokeCertStringDate", cert.isRevoke(sn));
        assertTrue("testAddRevokeCertStringDate", cert.isRevoke(serialNumber));

        java.security.cert.X509CRL certx = crlFrom(certBytes);

        X509CRLEntry crlEntry = certx.getRevokedCertificate(sn);

        assertTrue("testAddRevokeCertStringDate", crlEntry != null);
        assertTrue("testAddRevokeCertStringDate", revokeTime.equals(crlEntry.getRevocationDate()));

    }

    public void testAddRevokeCertStringDateInt() throws PKIException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final int reason = 7;
        final String serialNumber = "1920086";
        final Date revokeTime = dateFrom("2017/12/18 12:34:56");

        gen.addRevokeCert(serialNumber, revokeTime, reason);

        byte[] certBytes = gen.generateCRL(rsaPrivateKey, session);

        BigInteger sn = new BigInteger(serialNumber, 16);

        X509CRL cert = new X509CRL(certBytes);
        assertTrue("testAddRevokeCertStringDateInt", cert != null);
        assertTrue("testAddRevokeCertStringDateInt", cert.verify(rsaPublicKey));
        assertTrue("testAddRevokeCertStringDate", cert.isRevoke(sn));
        assertTrue("testAddRevokeCertStringDate", cert.isRevoke(serialNumber));

        java.security.cert.X509CRL certx = crlFrom(certBytes);

        X509CRLEntry crlEntry = certx.getRevokedCertificate(sn);

        assertTrue("testAddRevokeCertStringDateInt", crlEntry != null);
        assertTrue("testAddRevokeCertStringDateInt", sn.equals(crlEntry.getSerialNumber()));
        assertTrue("testAddRevokeCertStringDateInt", revokeTime.equals(crlEntry.getRevocationDate()));

    }

    public void testAddRevokeCertBigIntegerDate() throws PKIException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final BigInteger serialNumber = new BigInteger("1920086", 16);
        final Date revokeTime = dateFrom("2017/12/18 12:34:56");

        gen.addRevokeCert(serialNumber, revokeTime);

        byte[] certBytes = gen.generateCRL(rsaPrivateKey, session);

        X509CRL cert = new X509CRL(certBytes);
        assertTrue("testAddRevokeCertBigIntegerDate", cert != null);
        assertTrue("testAddRevokeCertBigIntegerDate", cert.verify(rsaPublicKey));
        assertTrue("testAddRevokeCertStringDate", cert.isRevoke(serialNumber));
        assertTrue("testAddRevokeCertStringDate", cert.isRevoke("1920086"));

        java.security.cert.X509CRL certx = crlFrom(certBytes);

        X509CRLEntry crlEntry = certx.getRevokedCertificate(serialNumber);

        assertTrue("testAddRevokeCertBigIntegerDate", crlEntry != null);
        assertTrue("testAddRevokeCertBigIntegerDate", revokeTime.equals(crlEntry.getRevocationDate()));
    }

    public void testAddRevokeCertBigIntegerDateInt() throws PKIException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final int reason = 7;
        final BigInteger serialNumber = new BigInteger("1920086", 16);
        final Date revokeTime = dateFrom("2017/12/18 12:34:56");

        gen.addRevokeCert(serialNumber, revokeTime, reason);

        byte[] certBytes = gen.generateCRL(rsaPrivateKey, session);

        X509CRL cert = new X509CRL(certBytes);
        assertTrue("testAddRevokeCertStringDateInt", cert != null);
        assertTrue("testAddRevokeCertStringDateInt", cert.verify(rsaPublicKey));
        assertTrue("testAddRevokeCertStringDate", cert.isRevoke(serialNumber));
        assertTrue("testAddRevokeCertStringDate", cert.isRevoke("1920086"));

        java.security.cert.X509CRL certx = crlFrom(certBytes);

        X509CRLEntry crlEntry = certx.getRevokedCertificate(serialNumber);

        assertTrue("testAddRevokeCertStringDateInt", crlEntry != null);
        assertTrue("testAddRevokeCertStringDateInt", serialNumber.equals(crlEntry.getSerialNumber()));
        assertTrue("testAddRevokeCertStringDateInt", revokeTime.equals(crlEntry.getRevocationDate()));
    }

    public void testSetIssuer() throws PKIException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final String RDN = "C=CN, O=CFCA TEST CA, OU=Local RA, OU=Individual-1, CN=051@SADK测试@11232311213132@1";

        final String DN = "CN = 051@SADK测试@11232311213132@1,OU = Individual-1,  OU = Local RA,O = CFCA TEST CA,C = CN";

        gen.setIssuer(DN);

        byte[] certBytes = gen.generateCRL(rsaPrivateKey, session);

        X509CRL cert = new X509CRL(certBytes);
        assertTrue("testSetIssuer", cert != null);
        assertTrue("testSetIssuer", cert.verify(rsaPublicKey));
        assertTrue("testSetIssuer", cert.getIssuer().equals(RDN));
        assertTrue("testSetIssuer", cert.getIssuer(CFCAStyle.INSTANCE).equals(RDN));
        assertTrue("testSetIssuer",
                "c=CN,o=CFCA TEST CA,ou=Local RA,ou=Individual-1,cn=051@SADK测试@11232311213132@1".equals(cert.getIssuer(RFC4519Style.INSTANCE)));
        assertTrue("testSetIssuer", "CN=051@SADK测试@11232311213132@1,OU=Individual-1,OU=Local RA,O=CFCA TEST CA,C=CN".equals(cert.getIssuer(BCStyle.INSTANCE)));

        java.security.cert.X509CRL certx = crlFrom(certBytes);

        assertTrue("testSetIssuer", certx != null);
        assertTrue("testSetIssuer", "CN=051@SADK测试@11232311213132@1,OU=Individual-1,OU=Local RA,O=CFCA TEST CA,C=CN".equals(certx.getIssuerDN().getName()));
        assertTrue("testSetIssuer",
                "C=CN,O=CFCA TEST CA,OU=Local RA,OU=Individual-1,CN=051@SADK测试@11232311213132@1".equals(certx.getIssuerX500Principal().getName()));
    }

    public void testSetThisUpdate() throws PKIException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final Date Time = dateFrom("2017/12/18 12:34:56");

        gen.setThisUpdate(Time);

        byte[] certBytes = gen.generateCRL(rsaPrivateKey, session);

        X509CRL cert = new X509CRL(certBytes);
        assertTrue("testSetThisUpdate", cert != null);
        assertTrue("testSetThisUpdate", cert.verify(rsaPublicKey));
        assertTrue("testSetThisUpdate", Time.equals(cert.getThisUpdate()));

        java.security.cert.X509CRL certx = crlFrom(certBytes);

        assertTrue("testSetThisUpdate", Time.equals(certx.getThisUpdate()));
    }

    public void testSetNextUpdate() throws PKIException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final Date Time = dateFrom("2017/12/18 12:34:56");

        gen.setNextUpdate(Time);

        byte[] certBytes = gen.generateCRL(rsaPrivateKey, session);

        X509CRL cert = new X509CRL(certBytes);
        assertTrue("testSetNextUpdate", cert != null);
        assertTrue("testSetNextUpdate", cert.verify(rsaPublicKey));
        assertTrue("testSetNextUpdate", Time.equals(cert.getNextUpdate()));

        java.security.cert.X509CRL certx = crlFrom(certBytes);

        assertTrue("testSetNextUpdate", Time.equals(certx.getNextUpdate()));
    }

    public void testSetSignatureAlg() throws PKIException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        final String signatureAlgorithm = Mechanism.SHA1_RSA;
        gen.setSignatureAlg(signatureAlgorithm);

        byte[] certBytes = gen.generateCRL(rsaPrivateKey, session);

        X509CRL cert = new X509CRL(certBytes);
        assertTrue("testSetSignatureAlg", cert != null);
        assertTrue("testSetSignatureAlg", cert.verify(rsaPublicKey));
        assertTrue("testSetSignatureAlg", signatureAlgorithm.equals(cert.getSignatureAlgName()));

        java.security.cert.X509CRL certx = crlFrom(certBytes);

        ASN1ObjectIdentifier oid = Mechanism.getObjectIdentifier(signatureAlgorithm);
        assertTrue("testSetSignatureAlg", certx.getSigAlgName().equals(oid.getId()));
    }

    public void testAddExtensions() throws PKIException, IOException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SM3_SM2), sm2PublicKey);

        byte[] keyIdentifier = HashUtil.RSAHashMessageByBC(sm2PublicKey.getEncoded(), new Mechanism(Mechanism.SHA1), false);

        Extension basicConstraints = new Extension(Extension.basicConstraints, true, new BasicConstraints(true).getEncoded());
        Extension keyUsage = new Extension(Extension.keyUsage, true, new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyCertSign).getEncoded());
        Extension authorityKeyIdentifier = new Extension(Extension.authorityKeyIdentifier, true, new AuthorityKeyIdentifier(keyIdentifier).getEncoded());

        Vector<Extension> extensions = new Vector<Extension>();
        extensions.add(basicConstraints);
        extensions.add(keyUsage);
        extensions.add(authorityKeyIdentifier);

        gen.addExtensions(extensions);

        byte[] certBytes = gen.generateCRL(sm2PrivateKey, session);

        X509CRL cert = new X509CRL(certBytes);
        assertTrue("testAddExtension", cert != null);
        assertTrue("testAddExtension", cert.verify(sm2PublicKey));

        java.security.cert.X509CRL certx = crlFrom(certBytes);

        assertTrue("testAddExtension", Arrays.equals(basicConstraints.getExtnValue().getEncoded(), certx.getExtensionValue(Extension.basicConstraints.getId())));
        assertTrue("testAddExtension", Arrays.equals(keyUsage.getExtnValue().getEncoded(), certx.getExtensionValue(Extension.keyUsage.getId())));
        assertTrue("testAddExtension",
                Arrays.equals(authorityKeyIdentifier.getExtnValue().getEncoded(), certx.getExtensionValue(Extension.authorityKeyIdentifier.getId())));
    }

    public void testAddExtension() throws PKIException, IOException {
        X509CRLGenerator gen = buildCRLGenerator(new Mechanism(Mechanism.SHA256_RSA), rsaPublicKey);

        byte[] keyIdentifier = HashUtil.RSAHashMessageByBC(rsaPublicKey.getEncoded(), new Mechanism(Mechanism.SHA1), false);

        Extension basicConstraints = new Extension(Extension.basicConstraints, true, new BasicConstraints(true).getEncoded());
        Extension keyUsage = new Extension(Extension.keyUsage, true, new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyCertSign).getEncoded());
        Extension authorityKeyIdentifier = new Extension(Extension.authorityKeyIdentifier, true, new AuthorityKeyIdentifier(keyIdentifier).getEncoded());

        gen.addExtension(basicConstraints);
        gen.addExtension(keyUsage);
        gen.addExtension(authorityKeyIdentifier);

        byte[] certBytes = gen.generateCRL(rsaPrivateKey, session);

        X509CRL cert = new X509CRL(certBytes);
        assertTrue("testAddExtension", cert != null);
        assertTrue("testAddExtension", cert.verify(rsaPublicKey));

        java.security.cert.X509CRL certx = crlFrom(certBytes);

        assertTrue("testAddExtension", Arrays.equals(basicConstraints.getExtnValue().getEncoded(), certx.getExtensionValue(Extension.basicConstraints.getId())));
        assertTrue("testAddExtension", Arrays.equals(keyUsage.getExtnValue().getEncoded(), certx.getExtensionValue(Extension.keyUsage.getId())));
        assertTrue("testAddExtension",
                Arrays.equals(authorityKeyIdentifier.getExtnValue().getEncoded(), certx.getExtensionValue(Extension.authorityKeyIdentifier.getId())));

    }

    public void testGenerateCRL() throws PKIException {
        callGenerateCRL(new Mechanism(Mechanism.SHA256_RSA), 1024);
        callGenerateCRL(new Mechanism(Mechanism.SHA512_RSA), 1024);
        callGenerateCRL(new Mechanism(Mechanism.SHA1_RSA), 1024);
        callGenerateCRL(new Mechanism(Mechanism.SM3_SM2), 256);

    }

    final byte[] callGenerateCRL(final Mechanism mechanism, int kLength) throws PKIException {

        KeyPair keyPair = null;
        if (mechanism.getMechanismType().contains("SM2")) {
            keyPair = KeyUtil.generateKeyPair(new Mechanism(Mechanism.SM2), kLength, session);
        } else {
            keyPair = KeyUtil.generateKeyPair(new Mechanism(Mechanism.RSA), kLength, session);
        }

        X509CRLGenerator gen = buildCRLGenerator(mechanism, keyPair.getPublic());

        byte[] certBytes = gen.generateCRL(keyPair.getPrivate(), session);

        X509CRL cert = new X509CRL(certBytes);

        assertTrue("callX509CRLGenerator", cert != null);

        assertTrue("callX509CRLGenerator", cert.getIssuer().equals(RDN));
        assertTrue("callX509CRLGenerator", cert.getNextUpdate().equals(nextUpdate));
        assertTrue("callX509CRLGenerator", cert.getThisUpdate().equals(thisUpdate));
        assertTrue("callX509CRLGenerator", cert.getSignatureAlgName().equals(mechanism.getMechanismType()));
        assertTrue("callX509CRLGenerator", cert.verify(keyPair.getPublic()));

        java.security.cert.X509CRL certx = crlFrom(certBytes);
        assertTrue("callX509CRLGenerator", certx.getVersion() == 2);
        assertTrue("callX509CRLGenerator", certx.getIssuerDN().getName().equals("CN=CFCA CS SM2 CA,O=China Financial Certification Authority,C=CN"));
        assertTrue("callX509CRLGenerator", certx.getNextUpdate().equals(nextUpdate));
        assertTrue("callX509CRLGenerator", certx.getThisUpdate().equals(thisUpdate));

        ASN1ObjectIdentifier oid = Mechanism.getObjectIdentifier(mechanism.getMechanismType());
        assertTrue("callX509CRLGenerator", certx.getSigAlgName().equals(oid.getId()));
        assertTrue("callX509CRLGenerator", certx.getSigAlgOID().equals(oid.getId()));

        return certBytes;

    }

    final X509CRLGenerator buildCRLGenerator(final Mechanism mechanism, final PublicKey publicKey) throws PKIException {

        try {
            X509CRLGenerator gen = new X509CRLGenerator();
            gen.setIssuer(DN);

            gen.setThisUpdate(thisUpdate);
            gen.setNextUpdate(nextUpdate);
            gen.setSignatureAlg(mechanism.getMechanismType());
            gen.addRevokeCert("10001", formatter.parse("2015/05/18 12:34:56"));
            gen.addRevokeCert(new BigInteger("20001"), formatter.parse("2015/05/19 12:34:56"));
            for (int reason = CRLReason.unspecified; reason <= CRLReason.aACompromise; reason++) {
                gen.addRevokeCert("3000" + reason, formatter.parse("2015/05/21 12:34:56"), reason);
                gen.addRevokeCert(new BigInteger("4000" + reason), formatter.parse("2015/05/21 12:34:56"), reason);
            }
            return gen;
        } catch (PKIException e) {
            throw e;
        } catch (ParseException e) {
            throw new PKIException("buildCRLGenerator failure", e);
        }
    }

    final java.security.cert.X509CRL crlFrom(byte[] certBytes) throws PKIException {
        return SM2TestData.crlFrom(certBytes);

    }

    final Date dateFrom(String date) throws PKIException {
        try {
            return formatter.parse("2017/12/18 12:34:56");
        } catch (ParseException e) {
            throw new PKIException("dateFrom failure", e);
        }
    }

}
