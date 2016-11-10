package test.cfca.sadk.util;

import java.math.BigInteger;

import javax.crypto.Cipher;

import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.org.bouncycastle.asn1.pkcs.CertificationRequest;
import cfca.sadk.org.bouncycastle.crypto.params.RSAKeyParameters;
import cfca.sadk.org.bouncycastle.crypto.util.PublicKeyFactory;
import cfca.sadk.org.bouncycastle.jcajce.provider.asymmetric.rsa.BCRSAPublicKey;
import cfca.sadk.org.bouncycastle.util.BigIntegers;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.sadk.util.P10Request;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public final class P10Test {

    public static void main(String[] args) throws Exception {
        // 创建签名设备
        JCrypto crypto = JCrypto.getInstance();
        final String deviceType = JCrypto.JSOFT_LIB;
        crypto.initialize(deviceType, null);
        final Session session = crypto.openSession(deviceType);

        byte[] data = FileHelper.read("d:/P10.txt");

        CertificationRequest certificationRequest = CertificationRequest.getInstance(Base64.decode(data));

        System.err.println(certificationRequest.getSignatureAlgorithm().getAlgorithm().getId());

        byte[] sourceData = certificationRequest.getCertificationRequestInfo().getEncoded();
        byte[] signData = certificationRequest.getSignature().getBytes();

        System.err.println(signData.length);

        RSAKeyParameters param = (RSAKeyParameters) PublicKeyFactory.createKey(certificationRequest.getCertificationRequestInfo().getSubjectPublicKeyInfo());
        BCRSAPublicKey publicKey = new BCRSAPublicKey(param);
        BigInteger e = publicKey.getPublicExponent();
        BigInteger n = publicKey.getModulus();

        BigInteger s = new BigInteger(1, signData);

        BigInteger x = s.modPow(e, n);
        System.err.println(HexBin.encode(BigIntegers.asUnsignedByteArray(128, x)));

        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] out = cipher.doFinal(signData);
        System.err.println(HexBin.encode(out));

        boolean verify = session.verify(new Mechanism(Mechanism.MD5_RSA), publicKey, sourceData, signData);

        System.err.println(verify);

        P10Request p10 = new P10Request(session);
        p10.load(data);
        System.err.println(p10.getSubject());

    }
}
