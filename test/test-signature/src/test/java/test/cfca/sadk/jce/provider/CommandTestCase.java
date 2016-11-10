/**
 * 
 */
package test.cfca.sadk.jce.provider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import junit.framework.Assert;
import junit.framework.TestCase;
import cfca.sadk.org.bouncycastle.asn1.ASN1InputStream;
import cfca.sadk.org.bouncycastle.asn1.ASN1Integer;
import cfca.sadk.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import cfca.sadk.org.bouncycastle.asn1.ASN1Sequence;
import cfca.sadk.org.bouncycastle.asn1.DERSequence;
import cfca.sadk.org.bouncycastle.asn1.sm2.ASN1SM2Cipher;
import cfca.sadk.org.bouncycastle.asn1.x500.X500Name;
import cfca.sadk.org.bouncycastle.asn1.x500.X500NameBuilder;
import cfca.sadk.org.bouncycastle.asn1.x500.style.CFCAStyle;
import cfca.sadk.org.bouncycastle.asn1.x500.style.RFC4519Style;
import cfca.sadk.org.bouncycastle.asn1.x509.GeneralName;
import cfca.sadk.org.bouncycastle.asn1.x509.GeneralNames;
import cfca.sadk.org.bouncycastle.asn1.x509.KeyPurposeId;
import cfca.sadk.org.bouncycastle.asn1.x509.KeyUsage;
import cfca.sadk.org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import cfca.sadk.org.bouncycastle.cert.X509CertificateHolder;
import cfca.sadk.org.bouncycastle.cert.X509v3CertificateBuilder;
import cfca.sadk.org.bouncycastle.jce.provider.BouncyCastleProvider;
import cfca.sadk.org.bouncycastle.operator.ContentSigner;
import cfca.sadk.org.bouncycastle.operator.ContentVerifierProvider;
import cfca.sadk.org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import cfca.sadk.org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import cfca.sadk.org.bouncycastle.util.BigIntegers;
import cfca.sadk.org.bouncycastle.util.encoders.Base64;
import cfca.sadk.org.bouncycastle.util.encoders.Hex;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public class CommandTestCase extends TestCase {

    static final Random random = new Random();

    static byte[] data = null;
    static byte[] plainBytes = null;

    static final int bitLength = 2048;// 1024/1536/2048/3072/4096

    static PrivateKey sm2PrivateKey = null;
    static PublicKey sm2PublicKey = null;
    static PrivateKey rsaPrivateKey = null;
    static PublicKey rsaPublicKey = null;
    static {
        Security.addProvider(new BouncyCastleProvider());
        Security.insertProviderAt(new BouncyCastleProvider(), 1);

        data = new byte[16 + random.nextInt(1024)];
        random.nextBytes(data);

        plainBytes = data;

        try { // BUILD SM2 KEY
            KeyPairGenerator gen = KeyPairGenerator.getInstance("SM2", "BC");
            gen.initialize(256);// 256
            KeyPair keypair = gen.generateKeyPair();

            sm2PrivateKey = keypair.getPrivate();
            sm2PublicKey = keypair.getPublic();
            // System.out.println("sm2PrivateKey: " + keypair.getPrivate());
            // System.out.println("sm2PublicKey: " + keypair.getPublic());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try { // BUILD RSA KEY
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA", "BC");
            gen.initialize(bitLength);
            KeyPair keypair = gen.generateKeyPair();
            System.out.println("RSA keypair: " + gen.generateKeyPair());

            rsaPrivateKey = keypair.getPrivate();
            rsaPublicKey = keypair.getPublic();
            // System.out.println("rsaPrivateKey: " + keypair.getPrivate());
            // System.out.println("rsaPublicKey: " + keypair.getPublic());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    protected void setUp() throws Exception {

    }

    protected void tearDown() throws Exception {

    }

    /**
     * DEMO: 3DES ENCRYP AND DECRYPT MODE：DESede/CBC/PKCS5Padding
     * 
     * @throws Exception
     */
    public void testDESCBCPKCS5PaddingEncryptDecrypt() throws Exception {

        System.out.println();
        System.out.println("testDESEncryptDecrypt: ");

        // BUILD KEY
        byte[] keyBytes = new byte[24];
        random.nextBytes(keyBytes);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        // BUILD IV FOR CBC MODE
        byte[] ivBytes = new byte[8];
        random.nextBytes(ivBytes);
        IvParameterSpec ivParam = new IvParameterSpec(ivBytes);// iv

        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);

        Cipher engine = Cipher.getInstance("DESede/CBC/PKCS5Padding", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key, ivParam);
        baos.write(engine.update(plainBytes));
        baos.write(engine.doFinal());

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(data.length);
        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key, ivParam);
        baos.write(engine.update(encryptedBytes));
        baos.write(engine.doFinal());

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testDESEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));

    }

    /**
     * DEMO: 3DES ENCRYP AND DECRYPT MODE：DESede/CBC/PKCS5Padding case：不添加向量函数
     * 
     * @throws Exception
     */
    public void yichang1testDESCBCPKCS5PaddingEncryptDecrypt() throws Exception {

        System.out.println();
        System.out.println("testDESEncryptDecrypt: ");

        // BUILD KEY
        byte[] keyBytes = new byte[24];
        random.nextBytes(keyBytes);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);

        Cipher engine = Cipher.getInstance("DESede/CBC/PKCS5Padding", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key);
        baos.write(engine.update(plainBytes));
        baos.write(engine.doFinal());

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(data.length);
        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key);
        baos.write(engine.update(encryptedBytes));
        baos.write(engine.doFinal());

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testDESEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));

    }

    /**
     * <pre>
     * DEMO: 3DES ENCRYP AND DECRYPT 
     * 
     * MODE：DESede/ECB/PKCS5Padding
     * 
     * @throws Exception
     */
    public void testDESECBPKCS5PaddingEncryptDecrypt() throws Exception {

        System.out.println();
        System.out.println("testDESEncryptDecrypt: ");

        // BUILD KEY
        byte[] keyBytes = new byte[24];
        random.nextBytes(keyBytes);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);

        Cipher engine = Cipher.getInstance("DESede/ECB/PKCS5Padding", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key);
        baos.write(engine.update(plainBytes));
        baos.write(engine.doFinal());

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(data.length);
        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key);
        baos.write(engine.update(encryptedBytes));
        baos.write(engine.doFinal());

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testDESEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));

    }

    /**
     * <pre>
     * DEMO: 3DES ENCRYP AND DECRYPT 
     * 
     * MODE：DESede/ECB/PKCS5Padding
     * case：添加向量函数
     * 
     * @throws Exception
     */
    public void yichang2testDESECBPKCS5PaddingEncryptDecrypt() throws Exception {

        System.out.println();
        System.out.println("testDESEncryptDecrypt: ");

        // BUILD KEY
        byte[] keyBytes = new byte[24];
        random.nextBytes(keyBytes);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        // BUILD IV FOR CBC MODE
        byte[] ivBytes = new byte[8];
        random.nextBytes(ivBytes);
        IvParameterSpec ivParam = new IvParameterSpec(ivBytes);// iv

        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);

        Cipher engine = Cipher.getInstance("DESede/ECB/PKCS5Padding", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key, ivParam);
        baos.write(engine.update(plainBytes));
        baos.write(engine.doFinal());

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(data.length);
        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key, ivParam);
        baos.write(engine.update(encryptedBytes));
        baos.write(engine.doFinal());

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testDESEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));

    }

    /**
     * <pre>
     * DEMO: 3DES ENCRYP AND DECRYPT 
     * MODE：DESede/ECB/NoPadding
     * 
     * @throws Exception
     */
    public void testDESECBNoPaddingEncryptDecrypt() throws Exception {

        System.out.println();
        System.out.println("testDESEncryptDecrypt: ");

        byte[] plainBytes = new byte[128];
        random.nextBytes(plainBytes);

        // BUILD KEY
        byte[] keyBytes = new byte[24];
        random.nextBytes(keyBytes);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);

        System.err.println("plainBytes length: " + plainBytes.length);
        Cipher engine = Cipher.getInstance("DESede/ECB/NOPadding", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key);
        baos.write(engine.update(plainBytes));
        baos.write(engine.doFinal());

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(data.length);
        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key);
        baos.write(engine.update(encryptedBytes));
        baos.write(engine.doFinal());

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testDESEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));

    }

    /**
     * <pre>
     * DEMO: 3DES ENCRYP AND DECRYPT 
     * MODE：DESede/ECB/NoPadding
     * case：输入不是8的倍数
     * 
     * @throws Exception
     */
    public void yichang3beitestDESECBNoPaddingEncryptDecrypt() throws Exception {

        System.out.println();
        System.out.println("testDESEncryptDecrypt: ");

        byte[] plainBytes = new byte[127];//修改
        random.nextBytes(plainBytes);

        // BUILD KEY
        byte[] keyBytes = new byte[24];
        random.nextBytes(keyBytes);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);

        System.err.println("plainBytes length: " + plainBytes.length);
        Cipher engine = Cipher.getInstance("DESede/ECB/NOPadding", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key);
        baos.write(engine.update(plainBytes));
        baos.write(engine.doFinal());

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(data.length);
        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key);
        baos.write(engine.update(encryptedBytes));
        baos.write(engine.doFinal());

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testDESEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));

    }

    /**
     * <pre>
     * DEMO: 3DES ENCRYP AND DECRYPT 
     * MODE：DESede/ECB/NoPadding
     * case：添加向量函数
     * 
     * @throws Exception
     */
    public void yichang3testDESECBNoPaddingEncryptDecrypt() throws Exception {

        System.out.println();
        System.out.println("testDESEncryptDecrypt: ");

        byte[] plainBytes = new byte[128];
        random.nextBytes(plainBytes);

        // BUILD KEY
        byte[] keyBytes = new byte[24];
        random.nextBytes(keyBytes);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        // BUILD IV FOR CBC MODE
        byte[] ivBytes = new byte[8];
        random.nextBytes(ivBytes);
        IvParameterSpec ivParam = new IvParameterSpec(ivBytes);// iv
        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);
        Cipher engine = Cipher.getInstance("DESede/ECB/NOPadding", "BC");

        System.err.println("plainBytes length: " + plainBytes.length);

        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key, ivParam);
        baos.write(engine.update(plainBytes));
        baos.write(engine.doFinal());

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(data.length);
        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key, ivParam);
        baos.write(engine.update(encryptedBytes));
        baos.write(engine.doFinal());

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testDESEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));

    }

    /**
     * DEMO: SM4
     * 
     * @throws Exception
     */
    public void testSM4ECBXEncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testSM4EncryptDecrypt: ");

        // BUILD KEY
        byte[] keyBytes = new byte[16];
        random.nextBytes(keyBytes);
        byte[] plainBytes = new byte[16 + 16 * random.nextInt(100)];
        random.nextBytes(plainBytes);

        SecretKey key = new SecretKeySpec(keyBytes, "SM4");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(plainBytes.length);

        Cipher engine = Cipher.getInstance("SM4/ECB/NoPadding", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key);
        baos.write(engine.doFinal(plainBytes));

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(plainBytes.length);
        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key);
        baos.write(engine.doFinal(encryptedBytes));

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));

        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));
        System.err.println(Arrays.equals(plainBytes, decryptedBytes));

    }

    /**
     * DEMO: SM4
     * 
     * @throws Exception
     */
    public void testSM4CBCXEncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testSM4EncryptDecrypt: ");

        // BUILD KEY
        byte[] keyBytes = new byte[16];
        random.nextBytes(keyBytes);
        byte[] plainBytes = new byte[16 + 16 * random.nextInt(100)];
        random.nextBytes(plainBytes);

        SecretKey key = new SecretKeySpec(keyBytes, "SM4");

        // BUILD IV FOR CBC MODE
        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        IvParameterSpec ivParam = new IvParameterSpec(ivBytes);// iv

        ByteArrayOutputStream baos = new ByteArrayOutputStream(plainBytes.length);

        Cipher engine = Cipher.getInstance("SM4/CBC/PKCS5Padding", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key, ivParam);
        baos.write(engine.doFinal(plainBytes));

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(plainBytes.length);
        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key, ivParam);
        baos.write(engine.doFinal(encryptedBytes));

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));

        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));
        System.err.println(Arrays.equals(plainBytes, decryptedBytes));

    }

    /**
     * DEMO: SM4
     * 
     * @throws Exception
     */
    public void testSM4ECBEncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testSM4EncryptDecrypt: ");

        // BUILD KEY
        byte[] keyBytes = Hex.decode("01 23 45 67 89 ab cd ef fe dc ba 98 76 54 32 10".replaceAll(" ", ""));
        byte[] plainBytes = Hex.decode("01 23 45 67 89 ab cd ef fe dc ba 98 76 54 32 10".replaceAll(" ", ""));

        byte[] dstBytes = Hex.decode("68 1e df 34 d2 06 96 5e 86 b3 e9 4f 53 6e 42 46".replaceAll(" ", ""));

        SecretKey key = new SecretKeySpec(keyBytes, "SM4");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(plainBytes.length);

        Cipher engine = Cipher.getInstance("SM4/ECB/NoPadding", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key);
        baos.write(engine.doFinal(plainBytes));

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(plainBytes.length);
        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key);
        baos.write(engine.doFinal(encryptedBytes));

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("            dstBytes: " + Hex.toHexString(dstBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));
        System.err.println(Arrays.equals(plainBytes, decryptedBytes));

        System.err.println(Arrays.equals(encryptedBytes, dstBytes));

    }

    /**
     * DEMO: SM4
     * 
     * @throws Exception
     */
    public void testSM4ECB100WEncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testSM4EncryptDecrypt: ");

        // BUILD KEY
        byte[] keyBytes = Hex.decode("01 23 45 67 89 ab cd ef fe dc ba 98 76 54 32 10".replaceAll(" ", ""));
        byte[] plainBytes = Hex.decode("01 23 45 67 89 ab cd ef fe dc ba 98 76 54 32 10".replaceAll(" ", ""));

        byte[] dstBytes = Hex.decode("59 52 98 c7 c6 fd 27 1f 04 02 f8 04 c3 3d 3f 66".replaceAll(" ", ""));

        SecretKey key = new SecretKeySpec(keyBytes, "SM4");

        Cipher engine = Cipher.getInstance("SM4/ECB/NoPadding", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key);

        int i = 0;
        byte[] encryptedBytes = plainBytes;
        while (i++ < 1000000) {
            encryptedBytes = engine.doFinal(encryptedBytes);
            if (i % 10000 == 0) {
                System.err.println(i);
            }
        }

        // DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, key);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("            dstBytes: " + Hex.toHexString(dstBytes));

        System.err.println(Arrays.equals(encryptedBytes, dstBytes));

    }

    /**
     * DEMO: SM4 case：删除，SM4算法已更新
     * 
     * @throws Exception
     */
    public void yichang4testSM4EncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testSM4EncryptDecrypt: ");

        // BUILD KEY
        byte[] keyBytes = new byte[16];
        random.nextBytes(keyBytes);
        SecretKey key = new SecretKeySpec(keyBytes, "SM4");

        // BUILD IV FOR CBC MODE
        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        IvParameterSpec ivParam = new IvParameterSpec(ivBytes);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);

        Cipher engine = Cipher.getInstance("SM4", "BC");
        // ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, key, ivParam);
        baos.write(engine.update(plainBytes));
        baos.write(engine.doFinal());

        byte[] encryptedBytes = baos.toByteArray();

        baos = new ByteArrayOutputStream(data.length);
        // DECRYPT OPERATION
        engine = Cipher.getInstance("SM4", "BC");
        engine.init(Cipher.DECRYPT_MODE, key);
        baos.write(engine.update(encryptedBytes));
        baos.write(engine.doFinal());

        byte[] decryptedBytes = baos.toByteArray();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testSM4EncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));
    }

    /**
     * DEMO: SM3
     * 
     * @throws Exception
     */
    public void testSM3Digest() throws Exception {
        System.out.println();
        System.out.println("testSM3Digest: ");

        MessageDigest engine = MessageDigest.getInstance("SM3", "BC");
        engine.update(data);

        byte[] digest = engine.digest();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println(" sm3: " + Hex.toHexString(digest));

        {
            byte[] data = Hex.decode("616263".replaceAll(" ", ""));
            engine = MessageDigest.getInstance("SM3", "BC");
            engine.update(data);
            digest = engine.digest();

            byte[] correst = Hex.decode("66c7f0f4 62eeedd9 d1f2d46b dc10e4e2 4167c487 5cf2f7a2 297da02b 8f4ba8e0".replaceAll(" ", ""));

            Assert.assertTrue("SM3 Okay", Arrays.equals(digest, correst));

            System.out.println("T0001: " + Hex.toHexString(data));
            System.out.println("TSM3x: " + Hex.toHexString(digest));
            System.out.println("TSM3y: " + Hex.toHexString(correst));
        }
        {
            byte[] data = Hex.decode("61626364 61626364 61626364 61626364 61626364 61626364 61626364 61626364"
                    + "61626364 61626364 61626364 61626364 61626364 61626364 61626364 61626364".replaceAll(" ", ""));
            engine = MessageDigest.getInstance("SM3", "BC");
            engine.update(data);
            digest = engine.digest();

            byte[] correst = Hex.decode("debe9ff9 2275b8a1 38604889 c18e5a4d 6fdb70e5 387e5765 293dcba3 9c0c5732".replaceAll(" ", ""));

            Assert.assertTrue("SM3 Okay", Arrays.equals(digest, correst));

            System.out.println("T0001: " + Hex.toHexString(data));
            System.out.println("TSM3x: " + Hex.toHexString(digest));
            System.out.println("TSM3y: " + Hex.toHexString(correst));
        }
    }

    public void testSM2EncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testSM2EncryptDecrypt: ");

        Cipher engine = Cipher.getInstance("SM2", "BC");
        // PUBLIC KEY ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, sm2PublicKey);
        byte[] encryptedBytes = engine.doFinal(data);

        // PRIVATE KEY DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, sm2PrivateKey);
        byte[] decryptedBytes = engine.doFinal(encryptedBytes);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testSM2EncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));
    }

    /**
     * DEMO: SM2
     * 
     * @throws Exception
     */
    public void yichang8testSM2EncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testSM2EncryptDecrypt: ");

        Cipher engine = Cipher.getInstance("SM2", "BC");
        // PUBLIC KEY ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        byte[] encryptedBytes = engine.doFinal(data);

        ASN1SM2Cipher xxx = ASN1SM2Cipher.getInstance(encryptedBytes);
        System.err.println(xxx);

        byte[] data = new byte[encryptedBytes.length - 3];
        System.arraycopy(encryptedBytes, 3, data, 0, data.length);

        encryptedBytes = data;

        // PRIVATE KEY DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, sm2PrivateKey);
        byte[] decryptedBytes = engine.doFinal(encryptedBytes);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testSM2EncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));
    }

    public void testSM2WithSM3() throws Exception {
        System.out.println();
        System.out.println("testSM2WithSM3: ");
        Signature engine = Signature.getInstance("SM3WithSM2", "BC");
        // PRIVATE KEY SIGNATUTE OPERATION
        engine.initSign(sm2PrivateKey);
        engine.update(data);
        byte[] sign = engine.sign();

        // PUBLIC KEY SIGNVERIFY OPERATION
        engine.initVerify(sm2PublicKey);
        engine.update(data);
        boolean signResult = engine.verify(sign);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println("sign: " + Hex.toHexString(sign));

        Assert.assertTrue("testSM2WithSM3", signResult);
    }

    public void yichang10testSM2WithSM3() throws Exception {
        System.out.println();
        System.out.println("testSM2WithSM3: ");
        Signature engine = Signature.getInstance("SM3WithSM2", "BC");
        // PRIVATE KEY SIGNATUTE OPERATION
        engine.initSign(sm2PrivateKey);
        engine.update(data);
        byte[] sign = engine.sign();

        ASN1InputStream xxx = new ASN1InputStream(sign);

        ASN1Sequence obj = (ASN1Sequence) xxx.readObject();

        ASN1Integer r = ASN1Integer.getInstance(obj.getObjectAt(0));
        ASN1Integer s = ASN1Integer.getInstance(obj.getObjectAt(1));

        System.err.println(r);
        System.err.println(s);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write((byte) 0x04);
        baos.write(BigIntegers.asUnsignedByteArray(32, r.getValue()));
        baos.write(BigIntegers.asUnsignedByteArray(32, s.getValue()));

        sign = baos.toByteArray();
        System.err.println(sign.length);

        // PUBLIC KEY SIGNVERIFY OPERATION
        engine.initVerify(rsaPublicKey);
        engine.update(data);
        boolean signResult = engine.verify(sign);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println("sign: " + Hex.toHexString(sign));

        Assert.assertTrue("testSM2WithSM3", signResult);
    }

    /**
     * DEMO: SHA1
     * 
     * @throws Exception
     */
    public void testSHA1Digest() throws Exception {
        System.out.println();
        System.out.println("testSHA1Digest: ");

        MessageDigest engine = MessageDigest.getInstance("SHA1", "BC");
        engine.update(data);

        byte[] digest1 = engine.digest();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println("sha1: " + Hex.toHexString(digest1));

    }

    /**
     * DEMO: SHA1 fenkuai
     * 
     * @throws Exception
     */
    public void yichang0fenkuaitestSHA1Digest() throws Exception {
        System.out.println();
        System.out.println("testSHA1Digest: ");

        MessageDigest engine = MessageDigest.getInstance("SHA1", "BC");
        engine.update(data);

        byte[] digest1 = engine.digest();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println("sha1: " + Hex.toHexString(digest1));

        engine = MessageDigest.getInstance("SHA1", "BC");

        ByteArrayInputStream bais = new ByteArrayInputStream(data);

        // FileInputStream bais = new FileInputStream("d:/xx");

        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = bais.read(buffer)) >= 0) {
            engine.update(buffer, 0, length);
        }

        byte[] digest2 = engine.digest();

        System.out.println("sha1: " + Hex.toHexString(digest2));
        Assert.assertTrue("testSHA1Digest", Arrays.equals(digest2, digest1));

    }

    /**
     * DEMO: SHA224
     * 
     * @throws Exception
     */
    public void testSHA224Digest() throws Exception {
        System.out.println();
        System.out.println("testSHA224Digest: ");

        MessageDigest engine = MessageDigest.getInstance("SHA224", "BC");
        engine.update(data);

        byte[] digest = engine.digest();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("  data: " + Hex.toHexString(data));
        System.out.println("sha224: " + Hex.toHexString(digest));
    }

    /**
     * DEMO: SHA256
     * 
     * @throws Exception
     */
    public void testSHA256Digest() throws Exception {
        System.out.println();
        System.out.println("testSHA256Digest: ");

        MessageDigest engine = MessageDigest.getInstance("SHA256", "BC");
        engine.update(data);

        byte[] digest = engine.digest();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("  data: " + Hex.toHexString(data));
        System.out.println("sha256: " + Hex.toHexString(digest));
    }

    /**
     * DEMO: SHA384
     * 
     * @throws Exception
     */
    public void testSHA384Digest() throws Exception {
        System.out.println();
        System.out.println("testSHA384Digest: ");

        MessageDigest engine = MessageDigest.getInstance("SHA384", "BC");
        engine.update(data);

        byte[] digest = engine.digest();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("  data: " + Hex.toHexString(data));
        System.out.println("sha384: " + Hex.toHexString(digest));
    }

    /**
     * DEMO: SHA512
     * 
     * @throws Exception
     */
    public void testSHA512Digest() throws Exception {
        System.out.println();
        System.out.println("testSHA512Digest: ");

        MessageDigest engine = MessageDigest.getInstance("SHA512", "BC");
        engine.update(data);

        byte[] digest = engine.digest();

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("  data: " + Hex.toHexString(data));
        System.out.println("sha512: " + Hex.toHexString(digest));
    }

    /**
     * MODE: RSA/ECB/PKCS1PADDING
     * 
     * @throws Exception
     */
    public void testRSAECBPKCS1PADDINGEncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testRSAEncryptDecrypt: ");

        byte[] plainBytes = new byte[bitLength / 8 - 11];
        random.nextBytes(plainBytes);

        Cipher engine = Cipher.getInstance("RSA/ECB/PKCS1PADDING", "BC");
        // PUBLIC KEY ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

        byte[] encryptedBytes = engine.doFinal(plainBytes);

        // PRIVATE KEY DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

        byte[] decryptedBytes = engine.doFinal(encryptedBytes);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testRSAEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));
    }

    /**
     * MODE: RSA/ECB/PKCS1PADDING
     * 
     * @throws Exception
     */
    public void yichang5testRSAECBPKCS1PADDINGEncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testRSAEncryptDecrypt: ");

        byte[] plainBytes = new byte[bitLength / 8 - 1];
        random.nextBytes(plainBytes);

        Cipher engine = Cipher.getInstance("RSA/ECB/PKCS1PADDING", "BC");
        // PUBLIC KEY ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

        byte[] encryptedBytes = engine.doFinal(plainBytes);

        // PRIVATE KEY DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

        byte[] decryptedBytes = engine.doFinal(encryptedBytes);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testRSAEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));
    }

    /**
     * MODE: RSA/ECB/PKCS1PADDING case：密钥对象不是RSA公私钥
     * 
     * @throws Exception
     */
    public void yichang7testRSAECBPKCS1PADDINGEncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testRSAEncryptDecrypt: ");

        byte[] plainBytes = new byte[bitLength / 8 - 11];
        random.nextBytes(plainBytes);

        Cipher engine = Cipher.getInstance("RSA/ECB/PKCS1PADDING", "BC");
        // PUBLIC KEY ENCRYPT OPERATION
        // engine.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        engine.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

        byte[] encryptedBytes = engine.doFinal(plainBytes);

        // PRIVATE KEY DECRYPT OPERATION
        // engine.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        engine.init(Cipher.DECRYPT_MODE, sm2PrivateKey);//修改

        byte[] decryptedBytes = engine.doFinal(encryptedBytes);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testRSAEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));
    }

    /**
     * MODE: RSA/ECB/NoPadding
     * 
     * @throws Exception
     */
    public void testRSAECBNOPADDINGEncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testRSAEncryptDecrypt: ");

        System.err.println("modules: " + ((RSAPublicKey) rsaPublicKey).getModulus().toString(16));

        byte[] plainBytes = new byte[bitLength / 8 - 1];
        random.nextBytes(plainBytes);

        Cipher engine = Cipher.getInstance("RSA/ECB/NoPadding", "BC");
        // PUBLIC KEY ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

        byte[] encryptedBytes = engine.doFinal(plainBytes);

        // PRIVATE KEY DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

        byte[] decryptedBytes = engine.doFinal(encryptedBytes);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testRSAEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));
    }

    /**
     * MODE: RSA/ECB/NoPadding case：输入为大于公钥的模数
     * 
     * @throws Exception
     */
    public void yichang6dayutestRSAECBNOPADDINGEncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testRSAEncryptDecrypt: ");

        System.err.println("modules: " + ((RSAPublicKey) rsaPublicKey).getModulus().toString(16));

        byte[] plainBytes = new byte[bitLength / 8 + 1];
        random.nextBytes(plainBytes);

        Cipher engine = Cipher.getInstance("RSA/ECB/NoPadding", "BC");
        // PUBLIC KEY ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

        byte[] encryptedBytes = engine.doFinal(plainBytes);

        // PRIVATE KEY DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

        byte[] decryptedBytes = engine.doFinal(encryptedBytes);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testRSAEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));
    }

    /**
     * MODE: RSA/ECB/NoPadding case：输入等于公钥的模数
     * 
     * @throws Exception
     */
    public void yichang6testRSAECBNOPADDINGEncryptDecrypt() throws Exception {
        System.out.println();
        System.out.println("testRSAEncryptDecrypt: ");

        String module = ((RSAPublicKey) rsaPublicKey).getModulus().toString(16);

        System.err.println("modules: " + module);

        byte[] plainBytes = new byte[bitLength / 8];// 修改
        System.err.println("x: " + plainBytes.length);

        random.nextBytes(plainBytes);
        plainBytes = Hex.decode(module);

        System.err.println("y: " + plainBytes.length);

        Cipher engine = Cipher.getInstance("RSA/ECB/NoPadding", "BC");
        // PUBLIC KEY ENCRYPT OPERATION
        engine.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

        byte[] encryptedBytes = engine.doFinal(plainBytes);

        // PRIVATE KEY DECRYPT OPERATION
        engine.init(Cipher.DECRYPT_MODE, rsaPrivateKey);

        byte[] decryptedBytes = engine.doFinal(encryptedBytes);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("----plainBytes: " + Hex.toHexString(plainBytes));
        System.out.println("encryptedBytes: " + Hex.toHexString(encryptedBytes));
        System.out.println("decryptedBytes: " + Hex.toHexString(decryptedBytes));

        Assert.assertTrue("testRSAEncryptDecrypt", Arrays.equals(plainBytes, decryptedBytes));
    }

    public void testRSAWithSHA1() throws Exception {
        System.out.println();
        System.out.println("testRSAWithSHA1: ");
        Signature engine = Signature.getInstance("SHA1WithRSA", "BC");
        // PRIVATE KEY SIGNATUTE OPERATION
        engine.initSign(rsaPrivateKey);
        engine.update(data);
        byte[] sign = engine.sign();

        // PUBLIC KEY SIGNVERIFY OPERATION
        engine.initVerify(rsaPublicKey);
        engine.update(data);

        boolean signResult = engine.verify(sign);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println("sign: " + Hex.toHexString(sign));

        Assert.assertTrue("testRSAWithSHA1", signResult);

    }

    /**
     * MODE: RSA case：密钥对象不是RSA公私钥
     * 
     * @throws Exception
     */
    public void yichang9testRSAWithSHA1() throws Exception {
        System.out.println();
        System.out.println("testRSAWithSHA1: ");
        Signature engine = Signature.getInstance("SHA1WithRSA", "BC");
        // PRIVATE KEY SIGNATUTE OPERATION
        engine.initSign(rsaPrivateKey);//修改
        engine.update(data);
        byte[] sign = engine.sign();

        // PUBLIC KEY SIGNVERIFY OPERATION
        engine.initVerify(rsaPublicKey);
        engine.update(data);

        boolean signResult = engine.verify(sign);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println("sign: " + Hex.toHexString(sign));

        Assert.assertTrue("testRSAWithSHA1", signResult);

    }

    public void testRSAWithSHA224() throws Exception {
        System.out.println();
        System.out.println("testRSAWithSHA224: ");
        Signature engine = Signature.getInstance("SHA224WithRSA", "BC");
        // PRIVATE KEY SIGNATUTE OPERATION
        engine.initSign(rsaPrivateKey);
        engine.update(data);
        byte[] sign = engine.sign();
        // PUBLIC KEY SIGNVERIFY OPERATION
        engine.initVerify(rsaPublicKey);
        engine.update(data);
        boolean signResult = engine.verify(sign);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println("sign: " + Hex.toHexString(sign));

        Assert.assertTrue("testRSAWithSHA224", signResult);
    }

    public void testRSAWithSHA256() throws Exception {
        System.out.println();
        System.out.println("testRSAWithSHA256: ");
        Signature engine = Signature.getInstance("SHA256WithRSA", "BC");
        // PRIVATE KEY SIGNATUTE OPERATION
        engine.initSign(rsaPrivateKey);
        engine.update(data);

        byte[] sign = engine.sign();
        // PUBLIC KEY SIGNVERIFY OPERATION
        engine.initVerify(rsaPublicKey);
        engine.update(data);
        boolean signResult = engine.verify(sign);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println("sign: " + Hex.toHexString(sign));

        Assert.assertTrue("testRSAWithSHA256", signResult);
    }

    public void testRSAWithSHA384() throws Exception {
        System.out.println();
        System.out.println("testRSAWithSHA384: ");
        Signature engine = Signature.getInstance("SHA384WithRSA", "BC");
        // PRIVATE KEY SIGNATUTE OPERATION
        engine.initSign(rsaPrivateKey);
        engine.update(data);

        byte[] sign = engine.sign();
        // PUBLIC KEY SIGNVERIFY OPERATION
        engine.initVerify(rsaPublicKey);
        engine.update(data);
        boolean signResult = engine.verify(sign);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println("sign: " + Hex.toHexString(sign));

        Assert.assertTrue("testRSAWithSHA384", signResult);
    }

    public void testRSAWithSHA512() throws Exception {
        System.out.println();
        System.out.println("testRSAWithSHA512: ");
        Signature engine = Signature.getInstance("SHA512WithRSA", "BC");
        // PRIVATE KEY SIGNATUTE OPERATION
        engine.initSign(rsaPrivateKey);
        engine.update(data);

        byte[] sign = engine.sign();
        // PUBLIC KEY SIGNVERIFY OPERATION
        engine.initVerify(rsaPublicKey);
        engine.update(data);
        boolean signResult = engine.verify(sign);

        System.out.println("Provider: " + engine.getProvider());
        System.out.println("data: " + Hex.toHexString(data));
        System.out.println("sign: " + Hex.toHexString(sign));

        Assert.assertTrue("testRSAWithSHA512", signResult);

    }

    public void testSM2CertsDecoded() {

        try {
            final String sm2cert = "" + //
                    "MIICCjCCAa2gAwIBAgIEGCa1dTAMBggqgRzPVQGDdQUAMFgxCzAJBgNVBAYTAkNO" + //
                    "MTAwLgYDVQQKDCdDaGluYSBGaW5hbmNpYWwgQ2VydGlmaWNhdGlvbiBBdXRob3Jp" + //
                    "dHkxFzAVBgNVBAMMDkNGQ0EgR1QgU00yIENBMB4XDTEyMDgyMTA3MjgwOVoXDTQy" + //
                    "MDgyMTA3MjgwOVowWDELMAkGA1UEBhMCQ04xMDAuBgNVBAoMJ0NoaW5hIEZpbmFu" + //
                    "Y2lhbCBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTEXMBUGA1UEAwwOQ0ZDQSBHVCBT" + //
                    "TTIgQ0EwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAATUsjvLXGBLcKuvM97InNIl" + //
                    "/wXS0iripBCzAhXEqnUVdbuVKgbyqK0AyK1PXkLHpXYYkxo+JMX7ZpYpEtiE2QKe" + //
                    "o2MwYTAfBgNVHSMEGDAWgBQLMV20swd1R3ZtmHivBc6r+qoUNjAPBgNVHRMBAf8E" + //
                    "BTADAQH/MA4GA1UdDwEB/wQEAwIBBjAdBgNVHQ4EFgQUCzFdtLMHdUd2bZh4rwXO" + //
                    "q/qqFDYwDAYIKoEcz1UBg3UFAANJADBGAiEA+gRtWm6oZnq8RmId5ADn7x9ZKx0S" + //
                    "5e/MTwZAdNZVpy0CIQC0xpUW8abWOCSZrchTWPlfo7TvNVempkbYsuM9Pz4Y9w==";//

            byte[] data = Base64.decode(sm2cert);

            ByteArrayInputStream bIn = new ByteArrayInputStream(data);
            CertificateFactory fact = CertificateFactory.getInstance("X.509", "BC");

            X509Certificate cert = (X509Certificate) fact.generateCertificate(bIn);

            System.err.println(cert);

            Assert.assertTrue("Okay testSM2CertsDecoded", true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue("Failure testSM2CertsDecoded", false);
        }

    }

    public void testSM2CertsBuilder() throws Exception {

        // distinguished name table.
        //
        X500NameBuilder builder = new X500NameBuilder(CFCAStyle.INSTANCE);

        builder.addRDN(RFC4519Style.c, "CN");
        builder.addRDN(RFC4519Style.o, "CFCA");
        builder.addRDN(RFC4519Style.l, "Beijing");
        builder.addRDN(RFC4519Style.st, "YZ");
        builder.addRDN(RFC4519Style.cn, "qazhang@cfca.com.cn");

        //
        // extensions
        //

        //
        // create the certificate - version 3 - without extensions
        //

        final ContentSigner sigGen = new JcaContentSignerBuilder("SM3WithSM2Encryption").build(sm2PrivateKey);

        final X500Name issuer = builder.build();
        final X500Name subject = builder.build();
        final BigInteger serial = BigInteger.valueOf(1);

        final Calendar calendar = Calendar.getInstance();

        final Date notBefore = calendar.getTime();
        calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) + 2));
        final Date notAfter = calendar.getTime();

        SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(new ASN1InputStream(sm2PublicKey.getEncoded()).readObject());
        X509v3CertificateBuilder certGen = new X509v3CertificateBuilder(issuer, serial, notBefore, notAfter, subject, publicKeyInfo);

        certGen.addExtension(new ASN1ObjectIdentifier("2.5.29.15"), true, new KeyUsage(KeyUsage.encipherOnly));
        certGen.addExtension(new ASN1ObjectIdentifier("2.5.29.37"), true, new DERSequence(KeyPurposeId.anyExtendedKeyUsage));
        certGen.addExtension(new ASN1ObjectIdentifier("2.5.29.17"), true, new GeneralNames(new GeneralName(GeneralName.rfc822Name, "test@test.test")));

        X509CertificateHolder certH = certGen.build(sigGen);

        assertTrue(certH.isValidOn(new Date()));

        ByteArrayInputStream bIn = new ByteArrayInputStream(certH.getEncoded());
        CertificateFactory fact = CertificateFactory.getInstance("X.509", "BC");

        X509Certificate cert = (X509Certificate) fact.generateCertificate(bIn);

        System.err.println(cert);

        ContentVerifierProvider contentVerifierProvider = new JcaContentVerifierProviderBuilder().build(sm2PublicKey);

        assertTrue(certH.isSignatureValid(contentVerifierProvider));

    }

}
