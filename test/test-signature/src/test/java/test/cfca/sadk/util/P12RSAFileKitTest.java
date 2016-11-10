package test.cfca.sadk.util;

import java.security.PrivateKey;

import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.util.P12FileKit;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;

public final class P12RSAFileKitTest {

    public static void main(String[] args) throws Exception {
        String password = "cfca1234";
        int bitLength = 1024;

        // 第一阶段：产生并保存密钥
        String base64EncryptedKeyData = P12FileKit.RSAGenerateKeyPair(bitLength, password);

        FileHelper.write("c:/tmp/test.key", base64EncryptedKeyData.getBytes());

        // 第二阶段：产生并保存申请文件
        base64EncryptedKeyData = new String(FileHelper.read("c:/tmp/test.key"));

        String base64CertData = P12FileKit.RSAGenerateP10(base64EncryptedKeyData, password);

        FileHelper.write("c:/tmp/test.p10", base64CertData.getBytes());

        Thread.sleep(300 * 1000);// 5分钟

        // 第三阶段：到RA/CA申请证书，拿回X509证书
        // 保存到："c:/tmp/test.cer"

        // 第四阶段：根据密钥和证书合成P12
        base64EncryptedKeyData = new String(FileHelper.read("c:/tmp/test.key"));
        base64CertData = new String(FileHelper.read("c:/tmp/test.cer"));
        String base64RSA = P12FileKit.RSACombineP12(base64EncryptedKeyData, base64CertData, password);

        String outFile = "c:/tmp/test.p12";
        FileHelper.write(outFile, Base64.decode(base64RSA));

        PrivateKey privateKey = KeyUtil.getPrivateKeyFromPFX(outFile, password);

        X509Cert cert = CertUtil.getCertFromPFX(outFile, password);

        final String deviceName = JCrypto.JSOFT_LIB;
        JCrypto.getInstance().initialize(deviceName, null);
        final Session session = JCrypto.getInstance().openSession(deviceName);

        byte[] sourceData = "TESTING".getBytes();
        Signature signature = new Signature();

        final String signAlg = Mechanism.SHA256_RSA;

        byte[] base64P1SignedData = signature.p1SignMessage(signAlg, sourceData, privateKey, session);
        boolean verifyResult = signature.p1VerifyMessage(signAlg, sourceData, base64P1SignedData, cert.getPublicKey(), session);
        System.err.println("verifyResult: " + verifyResult);

    }

}
