package test.cfca.sadk.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import cfca.sadk.org.bouncycastle.asn1.ASN1InputStream;
import cfca.sadk.org.bouncycastle.asn1.ASN1Primitive;
import cfca.sadk.org.bouncycastle.asn1.DERIA5String;
import cfca.sadk.org.bouncycastle.asn1.DEROctetString;
import cfca.sadk.org.bouncycastle.asn1.x509.CRLDistPoint;
import cfca.sadk.org.bouncycastle.asn1.x509.DistributionPoint;
import cfca.sadk.org.bouncycastle.asn1.x509.DistributionPointName;
import cfca.sadk.org.bouncycastle.asn1.x509.Extension;
import cfca.sadk.org.bouncycastle.asn1.x509.GeneralName;
import cfca.sadk.org.bouncycastle.asn1.x509.GeneralNames;
import cfca.sadk.util.Base64;

public class CRLVerifier {

    /**
     * Extracts the CRL distribution points from the certificate (if available)
     * and checks the certificate revocation status against the CRLs coming from
     * the distribution points. Supports HTTP, HTTPS, FTP and LDAP based URLs.
     * 
     * @param cert
     *            the certificate to be checked for revocation
     * @throws CertificateVerificationException
     *             if the certificate is revoked
     */

    private CRLVerifier() {

    }

    public static void main(String[] args) throws Exception {

        final String base64 = "MIIDsjCCAxugAwIBAgIQeaySxeLx5M7Pw/7Z0pklQjANBgkqhkiG9w0BAQUFADAkMQswCQYDVQQGEwJDTjEVMBMGA1UEChMMQ0ZDQSBURVNUIENBMB4XDTExMDkwMTAyMDI0OVoXDTEyMDkwMTAyMDI0OVoweDELMAkGA1UEBhMCQ04xFTATBgNVBAoTDENGQ0EgVEVTVCBDQTESMBAGA1UECxMJZGV2VGVzdDAxMRIwEAYDVQQLEwlFbXBsb3llZXMxKjAoBgNVBAMUITA0MUBDZGV2VGVzdDAxQGRldlRlc3QwMUAwMDAwMDAwMTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAxQQutfnuIpcw6FZDrXAaJg3ruWcdM/rpVxkIZRL8ziwF0+yRXXHQtk1egwmg/Sc6lKJYgOcUih1eUbg3mho4Lm6lg/uqQubvzHaeklDqrPQMaYqbLaKr4/Kwraok9VJIhVdVK7jfvk1a1ig6QO3sk3099dT9L1FmfOFvUEgA6DcCAwEAAaOCAY8wggGLMB8GA1UdIwQYMBaAFEZy3CVynwJOVYO1gPkL2+mTs/RFMB0GA1UdDgQWBBRFx+tjg0Nt/zx88HDl5RFiC3BgDTALBgNVHQ8EBAMCBaAwDAYDVR0TBAUwAwEBADA7BgNVHSUENDAyBggrBgEFBQcDAQYIKwYBBQUHAwIGCCsGAQUFBwMDBggrBgEFBQcDBAYIKwYBBQUHAwgwgfAGA1UdHwSB6DCB5TBPoE2gS6RJMEcxCzAJBgNVBAYTAkNOMRUwEwYDVQQKEwxDRkNBIFRFU1QgQ0ExDDAKBgNVBAsTA0NSTDETMBEGA1UEAxMKY3JsMTI2XzE4NDCBkaCBjqCBi4aBiGxkYXA6Ly90ZXN0bGRhcC5jZmNhLmNvbS5jbjozODkvQ049Y3JsMTI2XzE4NCxPVT1DUkwsTz1DRkNBIFRFU1QgQ0EsQz1DTj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0P2Jhc2U/b2JqZWN0Y2xhc3M9Y1JMRGlzdHJpYnV0aW9uUG9pbnQwDQYJKoZIhvcNAQEFBQADgYEAMx850dPQuBB4x40FFVu9BIfixpZu9D00wz6NAToz361Ur4NPIdwvz2EcT0n3Df5Nmj1NhQEI5SKmXZksSrIPL3tf5GA22AVmKKCzqrmsDabVcRPMacoL9/xoicBjXLX+ZG7hQ/uQhGmEq2U619vBZgLy26rZkMHyP+CWDicGZQA=";

        X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(
                new ByteArrayInputStream(Base64.decode(base64)));

        verifyCertificateCRLs(certificate);
    }

    public static void verifyCertificateCRLs(X509Certificate cert) throws CertificateVerificationException {
        try {
            List<String> crlDistPoints = getCrlDistributionPoints(cert);
            System.err.println(crlDistPoints);
            for (String crlDP : crlDistPoints) {
                X509CRL crl = downloadCRL(crlDP);

                System.err.println("#####anqing: " + crl.getIssuerDN());

                if (crl.isRevoked(cert)) {
                    throw new CertificateVerificationException("The certificate is revoked by CRL: " + crlDP);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (ex instanceof CertificateVerificationException) {
                throw (CertificateVerificationException) ex;
            } else {
                throw new CertificateVerificationException("Can not verify CRL for certificate: " + cert.getSubjectX500Principal());
            }
        }
    }

    /**
     * Downloads CRL from given URL. Supports http, https, ftp and ldap based
     * URLs.
     */
    static X509CRL downloadCRL(String crlURL) throws IOException, CertificateException, CRLException, CertificateVerificationException, NamingException {
        if (crlURL.startsWith("http://") || crlURL.startsWith("https://") || crlURL.startsWith("ftp://")) {
            return downloadCRLFromWeb(crlURL);
        } else if (crlURL.startsWith("ldap://")) {
            return downloadCRLFromLDAP2(crlURL);
        } else {
            throw new CertificateVerificationException("Can not download CRL from certificate " + "distribution point: " + crlURL);
        }
    }

    /**
     * Downloads a CRL from given HTTP/HTTPS/FTP URL, e.g.
     * http://crl.infonotary.com/crl/identity-ca.crl
     */
    static X509CRL downloadCRLFromWeb(String crlURL) throws MalformedURLException, IOException, CertificateException, CRLException {
        URL url = new URL(crlURL);
        InputStream crlStream = url.openStream();
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return (X509CRL) cf.generateCRL(crlStream);
        } finally {
            crlStream.close();
        }
    }

    /**
     * Extracts all CRL distribution point URLs from the
     * "CRL Distribution Point" extension in a X.509 certificate. If CRL
     * distribution point extension is unavailable, returns an empty list.
     */
    public static List<String> getCrlDistributionPoints(X509Certificate cert) throws CertificateParsingException, IOException {
        byte[] crldpExt = cert.getExtensionValue(Extension.cRLDistributionPoints.getId());
        if (crldpExt == null) {
            return new ArrayList<String>();
        }
        ASN1InputStream oAsnInStream = new ASN1InputStream(new ByteArrayInputStream(crldpExt));
        ASN1Primitive derObjCrlDP = oAsnInStream.readObject();
        DEROctetString dosCrlDP = (DEROctetString) derObjCrlDP;
        byte[] crldpExtOctets = dosCrlDP.getOctets();
        ASN1InputStream oAsnInStream2 = new ASN1InputStream(new ByteArrayInputStream(crldpExtOctets));
        ASN1Primitive derObj2 = oAsnInStream2.readObject();
        CRLDistPoint distPoint = CRLDistPoint.getInstance(derObj2);
        List<String> crlUrls = new ArrayList<String>();
        for (DistributionPoint dp : distPoint.getDistributionPoints()) {
            DistributionPointName dpn = dp.getDistributionPoint();
            // Look for URIs in fullName
            if (dpn != null && dpn.getType() == DistributionPointName.FULL_NAME) {
                GeneralName[] genNames = GeneralNames.getInstance(dpn.getName()).getNames();
                // Look for an URI
                for (int j = 0; j < genNames.length; j++) {
                    if (genNames[j].getTagNo() == GeneralName.uniformResourceIdentifier) {
                        String url = DERIA5String.getInstance(genNames[j].getName()).getString();
                        crlUrls.add(url);
                    }
                }
            }
        }
        return crlUrls;
    }

    /**
     * Downloads a CRL from given LDAP url, e.g.
     * ldap://ldap.infonotary.com/dc=identity-ca,dc=infonotary,dc=com
     */
    static X509CRL downloadCRLFromLDAP(String ldapURL) throws CertificateException, NamingException, CRLException, CertificateVerificationException {
        Map<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapURL);

        DirContext ctx = new InitialDirContext((Hashtable) env);
        Attributes avals = ctx.getAttributes("");
        Attribute aval = avals.get("certificateRevocationList;binary");
        byte[] val = (byte[]) aval.get();
        if ((val == null) || (val.length == 0)) {
            throw new CertificateVerificationException("Can not download CRL from: " + ldapURL);
        } else {
            InputStream inStream = new ByteArrayInputStream(val);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return (X509CRL) cf.generateCRL(inStream);
        }
    }

    /**
     * 从指定的LDAP服务器中下载CRL证书吊销文件
     * 
     * @param ip
     *            LDAP服务器地址
     * @param port
     *            LDAP服务器端口
     * @param dn
     *            搜索范围DN信息
     * @param cn
     *            过滤目标CN信息
     * @return 证书吊销列表对象
     * @throws Exception
     */
    static X509CRL downloadCRLFromLDAP2(String ldapURL) throws CertificateException, NamingException, CRLException, CertificateVerificationException {

        int ldapIndex = ldapURL.indexOf("ldap://");

        ldapURL = ldapURL.substring(ldapIndex + 7, ldapURL.length());
        ldapIndex = ldapURL.indexOf(":");
        String ip = ldapURL.substring(0, ldapIndex);
        ldapURL = ldapURL.substring(ldapIndex + 1, ldapURL.length());
        ldapIndex = ldapURL.indexOf("/");
        String port = ldapURL.substring(0, ldapIndex);
        ldapURL = ldapURL.substring(ldapIndex + 1, ldapURL.length());
        ldapIndex = ldapURL.indexOf("?");
        String dn = ldapURL.substring(0, ldapIndex);
        String cn = ldapURL.substring(ldapURL.indexOf("=") + 1, ldapURL.indexOf(","));

        DirContext ctx = null;
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        // TODO 所有字符串拼接的地方都改成StringBuffer ,@reviewer maben
        env.put(Context.PROVIDER_URL, "ldap://" + ip + ":" + port);
        // 查询二进制属性使用
        env.put("java.naming.ldap.attributes.binary", "certificateRevocationList");
        try {
            ctx = new InitialDirContext(env);
        } catch (NamingException e) {
            throw e;
        }
        SearchControls tConstraints = new SearchControls();
        tConstraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration tResults = null;

        // TODO 这种CRL文件下载存在隐患，大文件的时候，应该写磁盘
        // 设置查询条件
        // String filter =
        // "(&(&(objectclass=cRLDistributionPoint)(certificateRevocationList;binary=*))(cn="+cn+"))";
        String filter = "(&(objectclass=cRLDistributionPoint)(cn=" + cn + "))";
        String[] attrs = { "certificateRevocationList;binary" };
        X509CRL crl = null;
        try {
            tResults = ctx.search(dn, filter, attrs, tConstraints);
            if (tResults != null && tResults.hasMore()) {
                while (tResults.hasMore()) {
                    SearchResult tSearchRel = (SearchResult) tResults.next();
                    Attributes allAttrs = tSearchRel.getAttributes();
                    Attribute attCRL = allAttrs.get("certificateRevocationList;binary");
                    byte[] bCRL = (byte[]) attCRL.get(0);
                    InputStream inStream = new ByteArrayInputStream(bCRL);
                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    return (X509CRL) cf.generateCRL(inStream);
                }
            }
            ctx.close();
        } catch (Exception ex) {
            throw new CertificateException(ex);
        } catch (Throwable e) {
            throw new CertificateException(e);
        }
        return crl;
    }

}
