package test.cfca.sadk.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

import junit.framework.TestCase;
import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.algorithm.sm2.SM2PrivateKey;
import cfca.sadk.algorithm.sm2.SM2PublicKey;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.org.bouncycastle.jce.provider.BouncyCastleProvider;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.sadk.util.KeyUtil;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class KeyUtilTestCase extends TestCase {

    final String pfxPath = "./TestData/cert/CFCACSP1024qm.pfx";
    final String pfxPWD = "123123";

    final String jksFilePath = "./TestData/cert/kserver.keystore";
    final String jksPWD = "123456";
    final String alias = "ssl-kserver@signer";

    final String sm2Path = "./TestData/cert/sm2pfx.SM2";
    final String sm2PWD = "1";

    static final byte[] dBytes = HexBin.decode("414A871991374F62A6E0EADD7C701377649DA95E6CEF4753A6B9B2C623B009F8");
    static final byte[] xBytes = HexBin.decode("FBB1414B4CAE30043AD29EBD6AF1DC0C86BFB0B3E2DF75AF5514D89DD09E1861");
    static final byte[] yBytes = HexBin.decode("02F4A528A44113D8C3E53EFDC92B6537B496844B391AFD140303F57CED036286");
    static PrivateKey pfxPriKey = null;
    static PrivateKey jksPriKey = null;
    static PrivateKey sm2PriKey = null;
    static PublicKey sm2PubKey = null;
    static {
        try {

            sm2PriKey = new SM2PrivateKey(dBytes, xBytes, yBytes);
            sm2PubKey = new SM2PublicKey(xBytes, yBytes);

            pfxPriKey = RSAPrivateKey(HexBin
                    .decode("30820275020100300D06092A864886F70D01010105000482025F3082025B02010002818100A8EF5059131E021D8BB3512238C5BCDA0D8D0336D09EE18B85B8E0AC892A6856F1B3DCB01624775C8F9C5FD3F1407FA92DF6B02EDC86926E413DF16E8E914A9B8706698AEDAD989589CB6873B218F7A90FAFA102ABAEAC78786AA4B6A5DD811955CC8A8F9690EA462B44C3DD0AF3F6466203595BE3EE5466530A709FC0A154CF020301000102818000B6CA3B26268951C0A6686061A70C4E4BA25DF21C5D9F8C6C6B449FD61094D25141365C942B6149C5DD9E5611FDF8CC294292AA100A8D91E3BAB44B8854BAE0FDA5607D976AB41E0C9F29B1FE3F2058FC983C12B3421FDCD1E3DEF198761CD6F08B2AFA49ED2AA20AD17521054CB98C4FC975EC4A2D35AC3B910EDE7D16A5F9024100DBD8B8E8BAE55400A39CFE078A11BF27167670F05678247A9048C333A95E407EA506D3A25FDD2BEA71081427CC70E583682191E5250DD0D71BE386D4A937F07D024100C4B74488681CCCAA6585EBAA2172CD328BF6F40F410AFB9114C776CC8C040EA659CCA052F6832B3830FED08969428353C390B7FBBC912C46E853CBB6CC6D083B024030B0F04B435195684AD47382D1F69A429AFC0289886D358F1BB3B1E0184BF2B6D9F00821775FF1E10BC5C1490B4399E19FC7879B9BB8529FC1E283B3480346B502403D6820429C8D70EE3826E7FC01D8FE692AFBFA0FD0F406B7B14EDAACDB8060BA5909E17A446D89CE4EAD1C97E87DD4D50F155BF97F51AA0AB07787FE162E6B0502401C3268E0F930B05A62B189784A7195FDAC0280BC42D92F4599FA706F62CC08B8EFBC8A3F2BFC3FDB3F65946C31A4CCF745B87EBDEED5A5F435FF02C91D0E2608"));
            jksPriKey = RSAPrivateKey(HexBin
                    .decode("30820276020100300D06092A864886F70D0101010500048202603082025C0201000281810099BD449569B28274A2DA89BC80A04DE1A4618DDF9117E6CF8F07B69315920D72D533716AABA6292144CFCE7A8C645B45034DE197047415D1D492E072B92F4E37DF17B045BB8D23016E36E41E9BB671E12DB32EBD20BD9970A87E544CD9DA138C79D89ACA871B115275B576D70B48117F6C0B81F7C22ED514BD7C5583C8B6C5FF0203010001028180564EE309DE64D0C81A0505FE4557B9614B9F58BE81FE211DB4F66790FB331B49B45744FCD5905AD6CA82BF301D50D9CC4CA69456EBB11FEEC4A0105157952F1B58F0AABC0A4AEA068A9AE04D45CBECCF1ED2D891C09DFF6FE0D1D2B6000BF53E1F7440FD8820CDFB6E4767712D262793A797A4E613E7A8ED31F1F4E02DB6FBC1024100FEA76D281CD6F6E4BB1FB33DDEE3C779AEB8B1C61E2E4D8FE1F04D7A2581878AB4DD3F8169EB1EE45FAE76F2A38ACBB8F0E0950F42FF8EE63F7D34E5B39F29730241009A8D4B193E40B0CE3C840CCE6FF1350AF4EAD9C7E8E6775DD9F843E35433370C7AE04CF7AB5D8C97313A3A006D96B851DEC8A58FFC2AAD1231DA906EA36E7E4502405FB321E5823912DDD0A8D01E7F4610AFDD0691E087751536EE4CF580E2D973CB9FBEC9DFE3A3359FD3896830355FB2C1D4DC418853C060BE8CBB8C068AE3549B024034AEDD5EA97DDBC73191C1DBCD04E813E810F653D2EC5CDC3D2507DF4220832F59E383D9192D3673837059BD339E210EA766B62932C8495EDE67A6BC5031A6110241008BF77025E574C00847A8BCE64196E4DC035B81B1267D77781716CA01B174BDE3AF3DD08F0EE9A3274FD3583FC6E3B517FF6D82AC0DF219941E66E2EC6FFD7A27"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    Session session = null;

    protected void setUp() throws Exception {
        super.setUp();
        session = TestReady.openSession();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
        session = null;
    }

    static final RSAPrivateKey RSAPrivateKey(byte[] data) throws SecurityException {

        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(data);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new SecurityException(e.getMessage());
        }
    }

    public void testGetPrivateKeyFromPFXInputStreamString() throws FileNotFoundException, PKIException {

        PrivateKey key = KeyUtil.getPrivateKeyFromPFX(new FileInputStream(pfxPath), pfxPWD);

        assertTrue("testGetPrivateKeyFromPFXInputStreamString", key != null);

        assertTrue("testGetPrivateKeyFromPFXInputStreamString", key.equals(pfxPriKey));
    }

    public void testGetPrivateKeyFromPFXStringString() throws PKIException {

        PrivateKey key = KeyUtil.getPrivateKeyFromPFX(pfxPath, pfxPWD);

        assertTrue("testGetPrivateKeyFromPFXStringString", key != null);
        assertTrue("testGetPrivateKeyFromPFXStringString", key.equals(pfxPriKey));
    }

    public void testGetPrivateKeyFromPFXByteArrayString() throws PKIException, IOException {

        byte[] data = FileHelper.read(pfxPath);

        PrivateKey key = KeyUtil.getPrivateKeyFromPFX(data, pfxPWD);

        assertTrue("testGetPrivateKeyFromPFXByteArrayString", key != null);
        assertTrue("testGetPrivateKeyFromPFXByteArrayString", key.equals(pfxPriKey));
        
        key = KeyUtil.getPrivateKeyFromPFX(Base64.encode(data), pfxPWD);

        assertTrue("testGetPrivateKeyFromPFXByteArrayString", key != null);
        assertTrue("testGetPrivateKeyFromPFXByteArrayString", key.equals(pfxPriKey));
    }

    public void testGetPrivateKeyFromSM2StringString() throws PKIException {

        PrivateKey key = KeyUtil.getPrivateKeyFromSM2(sm2Path, sm2PWD);

        assertTrue("testGetPrivateKeyFromSM2StringString", key != null);
        assertTrue("testGetPrivateKeyFromSM2StringString", key.equals(sm2PriKey));

    }

    public void testGetPrivateKeyFromSM2InputStreamString() throws FileNotFoundException, PKIException {
        PrivateKey key = KeyUtil.getPrivateKeyFromSM2(new FileInputStream(sm2Path), sm2PWD);

        assertTrue("testGetPrivateKeyFromSM2InputStreamString", key != null);
        assertTrue("testGetPrivateKeyFromSM2InputStreamString", key.equals(sm2PriKey));
    }

    public void testGetPrivateKeyFromSM2ByteArrayString() throws PKIException, IOException {
        byte[] data = FileHelper.read(sm2Path);
        PrivateKey key = KeyUtil.getPrivateKeyFromSM2(data, sm2PWD);

        assertTrue("testGetPrivateKeyFromSM2ByteArrayString", key != null);
        assertTrue("testGetPrivateKeyFromSM2ByteArrayString", key.equals(sm2PriKey));
        
        key = KeyUtil.getPrivateKeyFromSM2(Base64.decode(data), sm2PWD);

        assertTrue("testGetPrivateKeyFromSM2ByteArrayString", key != null);
        assertTrue("testGetPrivateKeyFromSM2ByteArrayString", key.equals(sm2PriKey));
    }

    public void testGetPrivateKeyFromJKSStringStringString() throws PKIException {

        PrivateKey key = KeyUtil.getPrivateKeyFromJKS(jksFilePath, jksPWD, alias);

        assertTrue("testGetPrivateKeyFromJKSStringStringString", key != null);
        assertTrue("testGetPrivateKeyFromJKSStringStringString", key.equals(jksPriKey));
    }

    public void testGetPrivateKeyFromJKSInputStreamStringString() throws FileNotFoundException, PKIException {

        PrivateKey key = KeyUtil.getPrivateKeyFromJKS(new FileInputStream(jksFilePath), jksPWD, alias);

        assertTrue("testGetPrivateKeyFromJKSInputStreamStringString", key != null);
        assertTrue("testGetPrivateKeyFromJKSInputStreamStringString", key.equals(jksPriKey));
    }

    public void testGenerateKeyPair() throws PKIException, IOException {
        String sourceFilePath = "TestData/rsa/test.dat";
        Mechanism mechanism = null;
        byte[] sourceData = FileHelper.read(sourceFilePath);
        byte[] signedData = null;
        boolean testResult;

        KeyPair keypair = KeyUtil.generateKeyPair(new Mechanism(Mechanism.SM2), 256, session);
        assertTrue("testGenerateKeyPair", keypair != null);
        assertTrue("testGenerateKeyPair", keypair.getPrivate() != null);
        assertTrue("testGenerateKeyPair", keypair.getPublic() != null);
        mechanism = new Mechanism(Mechanism.SM3_SM2);
        signedData = session.sign(mechanism, keypair.getPrivate(), sourceData);
        testResult = session.verify(mechanism, keypair.getPublic(), sourceData, signedData);
        assertTrue("testGenerateKeyPair", testResult);

        keypair = KeyUtil.generateKeyPair(new Mechanism(Mechanism.RSA), 1024, session);
        assertTrue("testGenerateKeyPair", keypair != null);
        assertTrue("testGenerateKeyPair", keypair.getPrivate() != null);
        assertTrue("testGenerateKeyPair", keypair.getPublic() != null);
        assertTrue("testGenerateKeyPair", ((RSAPublicKey) (keypair.getPublic())).getModulus().bitLength() == 1024);
        mechanism = new Mechanism(Mechanism.SHA256_RSA);
        signedData = session.sign(mechanism, keypair.getPrivate(), sourceData);
        testResult = session.verify(mechanism, keypair.getPublic(), sourceData, signedData);
        assertTrue("testGenerateKeyPair", testResult);

        keypair = KeyUtil.generateKeyPair(new Mechanism(Mechanism.RSA), 2048, session);
        assertTrue("testGenerateKeyPair", keypair != null);
        assertTrue("testGenerateKeyPair", keypair.getPrivate() != null);
        assertTrue("testGenerateKeyPair", keypair.getPublic() != null);
        assertTrue("testGenerateKeyPair", ((RSAPublicKey) (keypair.getPublic())).getModulus().bitLength() == 2048);
        mechanism = new Mechanism(Mechanism.SHA256_RSA);
        signedData = session.sign(mechanism, keypair.getPrivate(), sourceData);
        testResult = session.verify(mechanism, keypair.getPublic(), sourceData, signedData);
        assertTrue("testGenerateKeyPair", testResult);

    }

    public void testGenerateKeyMechanismSession() throws PKIException {
        Key key = KeyUtil.generateKey(new Mechanism(Mechanism.DES3_KEY), session);
        assertTrue("testGenerateKeyMechanismSession", key != null && key.getEncoded().length == 24);
        key = KeyUtil.generateKey(new Mechanism(Mechanism.RC4_KEY), session);

        assertTrue("testGenerateKeyMechanismSession", key != null && key.getEncoded().length == 16);
        key = KeyUtil.generateKey(new Mechanism(Mechanism.SM4_KEY), session);
        assertTrue("testGenerateKeyMechanismSession", key != null && key.getEncoded().length == 16);
    }

    public void testGenerateKeyMechanismByteArraySession() throws PKIException {
        byte[] keyData = new byte[24];
        KeyUtil.generateKey(new Mechanism(Mechanism.DES3_KEY), keyData, session);
        assertTrue("testGenerateKeyMechanismByteArraySession", keyData.length == 24);

        keyData = new byte[16];
        KeyUtil.generateKey(new Mechanism(Mechanism.RC4_KEY), keyData, session);
        assertTrue("testGenerateKeyMechanismByteArraySession", keyData.length == 16);

        keyData = new byte[16];
        KeyUtil.generateKey(new Mechanism(Mechanism.SM4_KEY), keyData, session);
        assertTrue("testGenerateKeyMechanismByteArraySession", keyData.length == 16);
    }

    public void testGetSM2PublicKey() {
        SM2PublicKey key = KeyUtil.getSM2PublicKey(xBytes, yBytes);
        assertTrue("testGetSM2PublicKey", key != null);
        assertTrue("testGetSM2PublicKey", key.equals(sm2PubKey));
    }

    public void testGetSM2PrivateKey() {
        SM2PrivateKey key = KeyUtil.getSM2PrivateKey(dBytes, xBytes, yBytes);
        assertTrue("testGetSM2PrivateKey", key != null);
        assertTrue("testGetSM2PrivateKey", key.equals(sm2PriKey));
    }

}
