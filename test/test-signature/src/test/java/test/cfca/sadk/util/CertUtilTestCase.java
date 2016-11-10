package test.cfca.sadk.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Arrays;

import junit.framework.TestCase;
import cfca.sadk.org.bouncycastle.asn1.ASN1Object;
import cfca.sadk.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import cfca.sadk.org.bouncycastle.asn1.x509.Extensions;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.sadk.util.CertUtil;
import cfca.sadk.x509.certificate.X509Cert;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 *
 */
public class CertUtilTestCase extends TestCase {

    final String sm2FilePath = "./TestData/cert/sm2pfx.SM2";
    final byte[] sm2CertByte = HexBin
            .decode("308201CC30820171A00302010202052000000092300C06082A811CCF5501837505003021310B300906035504061302434E31123010060355040A0C0943464341204F434136301E170D3132313131393032313934395A170D3133303232373032313934395A3066310B300906035504061302636E311B3019060355040A0C1243464341204F7065726174696F6E204341323110300E060355040B0C07424F432D54504331143012060355040B0C0B496E646976696475616C323112301006035504030C097273616666303031313059301306072A8648CE3D020106082A811CCF5501822D03420004FBB1414B4CAE30043AD29EBD6AF1DC0C86BFB0B3E2DF75AF5514D89DD09E186102F4A528A44113D8C3E53EFDC92B6537B496844B391AFD140303F57CED036286A34F304D301F0603551D2304183016801400900AEBEFA38A110D16C25825ED0E2790ED1827300B0603551D0F0404030206C0301D0603551D0E041604148CCC0B4E9BB5C93893E6B7AB98E6C1D422A21C56300C06082A811CCF5501837505000347003044022017DA5EB289A65F09D483444204D40313D9EA764AD5A967766C187CC29A3E13F902203E71C7BB0DEB5A2A6A89764F412F1B4F14FE43D5E90740C2CDF3585426893C36");

    final String pfxFilePath = "./TestData/cert/kserver.p12";
    final String pfxFilePass = "123456";
    final byte[] pfxCertByte = HexBin
            .decode("3082025F308201C8A003020102020101300D06092A864886F70D01010505003052310B300906035504061302434E310D300B060355040A0C0443464341310B300906035504070C02595A310F300D06035504080C064265696A6E673116301406035504030C0D53534C20536572766572204341301E170D3135303430393039333030355A170D3136303430393039333030355A3056310B300906035504061302434E310D300B060355040A0C0443464341310B300906035504070C02595A310F300D06035504080C064265696A6E67311A301806035504030C1153534C20536572766572205369676E657230819F300D06092A864886F70D010101050003818D003081890281810099BD449569B28274A2DA89BC80A04DE1A4618DDF9117E6CF8F07B69315920D72D533716AABA6292144CFCE7A8C645B45034DE197047415D1D492E072B92F4E37DF17B045BB8D23016E36E41E9BB671E12DB32EBD20BD9970A87E544CD9DA138C79D89ACA871B115275B576D70B48117F6C0B81F7C22ED514BD7C5583C8B6C5FF0203010001A341303F300E0603551D0F0101FF0404030200A9300F0603551D25040830060604551D2500301C0603551D110101FF04123010810E7465737440746573742E74657374300D06092A864886F70D01010505000381810067B49EFB03DE44DA72389B8A3ADDAC47C5097155CB25B24C318F29847C9258B40154C61AA53FE8A2C735A89B368DCEFD2D604E2F88302C1A1F9E61A2CE4FA1DA984EA2BC92675D296687FA2D1B07C1BAC6F7AB6F2F93E5D5FB6B2E0AC39E94671E81AFEEE58872455C5181FFCA6AE3B227B01C0BDBAF8F22ADE33988945FC5F9");

    final byte[] extBytes = HexBin
            .decode("303F300E0603551D0F0101FF0404030200A9300F0603551D25040830060604551D2500301C0603551D110101FF04123010810E7465737440746573742E74657374");

    final String jksFilePath = "./TestData/cert/kserver.keystore";
    final String jksFilePass = "123456";
    final byte[] jksCertByte = HexBin
            .decode("3082025F308201C8A003020102020101300D06092A864886F70D01010505003052310B300906035504061302434E310D300B060355040A0C0443464341310B300906035504070C02595A310F300D06035504080C064265696A6E673116301406035504030C0D53534C20536572766572204341301E170D3135303430393039333030355A170D3136303430393039333030355A3056310B300906035504061302434E310D300B060355040A0C0443464341310B300906035504070C02595A310F300D06035504080C064265696A6E67311A301806035504030C1153534C20536572766572205369676E657230819F300D06092A864886F70D010101050003818D003081890281810099BD449569B28274A2DA89BC80A04DE1A4618DDF9117E6CF8F07B69315920D72D533716AABA6292144CFCE7A8C645B45034DE197047415D1D492E072B92F4E37DF17B045BB8D23016E36E41E9BB671E12DB32EBD20BD9970A87E544CD9DA138C79D89ACA871B115275B576D70B48117F6C0B81F7C22ED514BD7C5583C8B6C5FF0203010001A341303F300E0603551D0F0101FF0404030200A9300F0603551D25040830060604551D2500301C0603551D110101FF04123010810E7465737440746573742E74657374300D06092A864886F70D01010505000381810067B49EFB03DE44DA72389B8A3ADDAC47C5097155CB25B24C318F29847C9258B40154C61AA53FE8A2C735A89B368DCEFD2D604E2F88302C1A1F9E61A2CE4FA1DA984EA2BC92675D296687FA2D1B07C1BAC6F7AB6F2F93E5D5FB6B2E0AC39E94671E81AFEEE58872455C5181FFCA6AE3B227B01C0BDBAF8F22ADE33988945FC5F9");

    final String p7bFilePath = "./TestData/cert/test.p7b";
    final byte[] p7bCertByte = HexBin
            .decode("3082027B308201E4A00302010202043CFC8C5E300D06092A864886F70D01010505003020310B300906035504061302434E3111300F060355040A13084346434120524341301E170D3034303831303038333633375A170D3134303732353136303030305A3024310B300906035504061302434E31153013060355040A130C43464341205445535420434130819F300D06092A864886F70D010101050003818D0030818902818100D8AECE7433B86AF32D0BCC58DF37750F4331A2060D642E29CFA89712C7BA115DECB5E56A4B2CC2ED85C7E9F469FFEEE5E6B4D2FA6AD86BF75617CEB07745CA79DD4DF3A9270613D87692D150DD235D64FE9CFD5490FB6EE9015C564540BE17AA6652C9E8FA377599FB588E129157CCAF091A5257A1C3CE12CAF37C5B92809F890203010001A381BD3081BA30420603551D1F043B30393037A035A033A431302F310B300906035504061302434E3111300F060355040A13084346434120524341310D300B0603550403130443524C31300B0603551D0F040403020106301F0603551D23041830168014009A34F251F9531461746E72A106DEC781701BBC301D0603551D0E041604144672DC25729F024E5583B580F90BDBE993B3F445300C0603551D13040530030101FF301906092A864886F67D074100040C300A1B0456362E3003020490300D06092A864886F70D0101050500038181009EBD9746352BE6B212FA23CC11DFCE8CCC7F46B469031A11B8A297E44943C96FC161D939E6914DC24EC7288FA10E4366A844BAC5DABC53D7E09FE61371F93C29D5E6D17BB523BBBC482E86CF26D885F1ACE642A4A9822557AF35D57DC749BD783EB6F4D54919FEBCDEB68CC77F45B781D015A2D3F845DB9D2016BD87A63F2396");

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetCertExtensionData() throws Exception {
        final X509Cert certx = CertUtil.getCertFromPFX(pfxFilePath, pfxFilePass);

        {
            final ASN1ObjectIdentifier oid = new ASN1ObjectIdentifier("2.5.29.37");
            ASN1Object ext = certx.getExtensionData(oid);
            final byte[] extValue = HexBin.decode("30060604551D2500");

            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, ext.getEncoded()));
            byte[] data = certx.getExtensionByteData(oid);
            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, data));
        }
        {
            final ASN1ObjectIdentifier oid = new ASN1ObjectIdentifier("2.5.29.15");
            ASN1Object ext = certx.getExtensionData(oid);
            final byte[] extValue = HexBin.decode("030200a9");

            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, ext.getEncoded()));
            byte[] data = certx.getExtensionByteData(oid);
            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, data));
        }
        {
            final ASN1ObjectIdentifier oid = new ASN1ObjectIdentifier("2.5.29.17");
            ASN1Object ext = certx.getExtensionData(oid);
            final byte[] extValue = HexBin.decode("3010810e7465737440746573742e74657374");

            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, ext.getEncoded()));
            byte[] data = certx.getExtensionByteData(oid);
            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, data));
        }

        X509Cert certz = CertUtil.getCertFromSM2(sm2FilePath);
        {
            final ASN1ObjectIdentifier oid = new ASN1ObjectIdentifier("2.5.29.35");
            ASN1Object ext = certz.getExtensionData(oid);
            final byte[] extValue = HexBin.decode("3016801400900aebefa38a110d16c25825ed0e2790ed1827");

            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, ext.getEncoded()));
            byte[] data = certz.getExtensionByteData(oid);
            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, data));
        }
        {
            final ASN1ObjectIdentifier oid = new ASN1ObjectIdentifier("2.5.29.15");
            ASN1Object ext = certz.getExtensionData(oid);
            final byte[] extValue = HexBin.decode("030206c0");

            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, ext.getEncoded()));
            byte[] data = certz.getExtensionByteData(oid);
            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, data));
        }
        {
            final ASN1ObjectIdentifier oid = new ASN1ObjectIdentifier("2.5.29.14");
            ASN1Object ext = certz.getExtensionData(oid);
            final byte[] extValue = HexBin.decode("04148ccc0b4e9bb5c93893e6b7ab98e6c1d422a21c56");

            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, ext.getEncoded()));
            byte[] data = certz.getExtensionByteData(oid);
            assertTrue("testGetCertExtensionData", Arrays.equals(extValue, data));
        }

    }

    public void testGetCertExtensionsData() throws Exception {
        X509Cert cert = CertUtil.getCertFromPFX(pfxFilePath, pfxFilePass);
        Extensions exts = cert.getExtensionsData();

        final Extensions dest = Extensions.getInstance(extBytes);

        assertTrue("testGetCertExtensionsData", exts.equals(dest));

    }

    public void testGetCertFromSM2String() throws Exception {

        X509Cert cert = CertUtil.getCertFromSM2(sm2FilePath);
        assertTrue("testGetCertFromSM2String", cert != null);

        assertTrue("testGetCertFromSM2String", Arrays.equals(sm2CertByte, cert.getEncoded()));

    }

    public void testGetCertFromSM2InputStream() throws Exception {
        X509Cert cert = CertUtil.getCertFromSM2(new FileInputStream(sm2FilePath));
        assertTrue("testGetCertFromSM2InputStream", cert != null);
        assertTrue("testGetCertFromSM2InputStream", Arrays.equals(sm2CertByte, cert.getEncoded()));
    }

    public void testGetCertFromSM2ByteArray() throws Exception {
        byte[] certBytes = FileHelper.read(sm2FilePath);
        X509Cert cert = CertUtil.getCertFromSM2(certBytes);
        assertTrue("testGetCertFromSM2ByteArray", cert != null);
        assertTrue("testGetCertFromSM2ByteArray", Arrays.equals(sm2CertByte, cert.getEncoded()));

        cert = CertUtil.getCertFromSM2(Base64.decode(certBytes));
        assertTrue("testGetCertFromSM2ByteArray", cert != null);
        assertTrue("testGetCertFromSM2ByteArray", Arrays.equals(sm2CertByte, cert.getEncoded()));
    }

    public void testGetCertFromPFXStringString() throws Exception {
        X509Cert cert = CertUtil.getCertFromPFX(pfxFilePath, pfxFilePass);
        assertTrue("testGetCertFromPFXStringString", cert != null);

        assertTrue("testGetCertFromPFXStringString", Arrays.equals(pfxCertByte, cert.getEncoded()));

    }

    public void testGetCertFromPFXInputStreamString() throws Exception {
        X509Cert cert = CertUtil.getCertFromPFX(new FileInputStream(pfxFilePath), pfxFilePass);
        assertTrue("testGetCertFromPFXInputStreamString", cert != null);
        assertTrue("testGetCertFromPFXInputStreamString", Arrays.equals(pfxCertByte, cert.getEncoded()));
    }

    public void testGetCertFromPFXByteArrayString() throws Exception {
        byte[] certBytes = FileHelper.read(pfxFilePath);
        X509Cert cert = CertUtil.getCertFromPFX(certBytes, pfxFilePass);
        assertTrue("testGetCertFromPFXByteArrayString", cert != null);
        assertTrue("testGetCertFromPFXByteArrayString", Arrays.equals(pfxCertByte, cert.getEncoded()));
        
         cert = CertUtil.getCertFromPFX(Base64.encode(certBytes), pfxFilePass);
        assertTrue("testGetCertFromPFXByteArrayString", cert != null);
        assertTrue("testGetCertFromPFXByteArrayString", Arrays.equals(pfxCertByte, cert.getEncoded()));
    }

    public void testGetCertFromJKS() throws Exception {

        KeyStore ks = KeyStore.getInstance("JKS");

        ks.load(new FileInputStream(jksFilePath), jksFilePass.toCharArray());

        String alias = (String) ks.aliases().nextElement();

        X509Cert cert = CertUtil.getCertFromJKS(jksFilePath, jksFilePass, alias);
        assertTrue("testGetCertFromJKS", cert != null);
        assertTrue("testGetCertFromJKS", Arrays.equals(jksCertByte, cert.getEncoded()));

    }

    public void testParseP7BString() throws Exception {

        X509Cert[] certs = CertUtil.parseP7B(p7bFilePath);

        assertTrue("testparseP7BString", certs != null && certs.length > 0);
        assertTrue("testparseP7BString", certs.length == 2);

        assertTrue("testParseP7BString", Arrays.equals(p7bCertByte, certs[0].getEncoded()));

    }

    public void testParseP7BByteArray() throws Exception {

        byte[] data = FileHelper.read(p7bFilePath);
        X509Cert[] certs = CertUtil.parseP7B(data);

        assertTrue("testparseP7BString", certs != null && certs.length > 0);
        assertTrue("testparseP7BString", certs.length == 2);

        assertTrue("testParseP7BString", Arrays.equals(p7bCertByte, certs[0].getEncoded()));

    }

    public void testGenerateP7BFileX509CertArrayString() throws Exception {

        byte[] data = FileHelper.read(p7bFilePath);
        X509Cert[] certs = CertUtil.parseP7B(data);

        String saveFilePath = "./TestData/out/test.p7b";
        CertUtil.generateP7BFile(certs, saveFilePath);

        byte[] datax = FileHelper.read(saveFilePath);
        assertTrue("testGenerateP7BFileX509CertArrayString", datax != null);
        certs = CertUtil.parseP7B(datax);
        assertTrue("testGenerateP7BFileX509CertArrayString", certs != null && certs.length > 0);

        assertTrue("testGenerateP7BFileX509CertArrayString", Arrays.equals(p7bCertByte, certs[0].getEncoded()));
    }

    public void testGenerateP7BFileX509CertArrayOutputStream() throws Exception {
        byte[] data = FileHelper.read(p7bFilePath);
        X509Cert[] certs = CertUtil.parseP7B(data);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CertUtil.generateP7BFile(certs, out);

        byte[] outBytes = out.toByteArray();
        assertTrue("testGenerateP7BFileX509CertArrayOutputStream", outBytes != null);
        certs = CertUtil.parseP7B(outBytes);
        assertTrue("testGenerateP7BFileX509CertArrayOutputStream", certs != null && certs.length > 0);

        assertTrue("testGenerateP7BFileX509CertArrayOutputStream", Arrays.equals(p7bCertByte, certs[0].getEncoded()));
    }

    public void testGenerateP7BData() throws Exception {

        byte[] data = FileHelper.read(p7bFilePath);
        X509Cert[] certs = CertUtil.parseP7B(data);

        byte[] outBytes = CertUtil.generateP7BData(certs);

        assertTrue("testGenerateP7BData", outBytes != null);

        certs = CertUtil.parseP7B(outBytes);
        assertTrue("testGenerateP7BData", certs != null && certs.length > 0);

        assertTrue("testGenerateP7BData", Arrays.equals(p7bCertByte, certs[0].getEncoded()));

    }

    public void testIsSM2Cert() throws Exception {
        byte[] p7SignData = FileHelper.read(p7bFilePath);
        X509Cert cert = CertUtil.parseP7B(p7SignData)[0];

        boolean result = CertUtil.isSM2Cert(cert);

        assertFalse("testIsSM2Cert", result);

        cert = CertUtil.getCertFromSM2(sm2FilePath);
        result = CertUtil.isSM2Cert(cert);
        assertTrue("testIsSM2Cert", result);

        assertTrue("testIsSM2Cert", Arrays.equals(sm2CertByte, cert.getEncoded()));

    }

    public void testIsCACert() throws Exception {
        byte[] p7SignData = FileHelper.read(p7bFilePath);
        X509Cert cert = CertUtil.parseP7B(p7SignData)[0];

        boolean result = CertUtil.isCACert(cert);

        assertTrue("testIsCACert", result);

        cert = CertUtil.getCertFromSM2(sm2FilePath);
        assertTrue("testIsCACert", result);

        assertTrue("testIsSM2Cert", Arrays.equals(sm2CertByte, cert.getEncoded()));
    }

}
