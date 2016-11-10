package test.cfca.sadk.util.cipher.lib;

import java.security.Security;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cfca.sadk.org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sansec.jce.provider.SwxaProvider;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public final class SwxaJCETest {

    public static void main(String[] args) throws Exception {

        Security.addProvider(new BouncyCastleProvider());
        Security.addProvider(new SwxaProvider());

        SecretKey key = new SecretKeySpec("12345678abcdefgh12345678".getBytes(), "DESEDE");
        IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());

        byte[] data = new byte[128];
        new Random().nextBytes(data);

        Cipher engine = Cipher.getInstance("DESEDE/CBC/PKCS7Padding", "SwxaJCE");
        engine.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] encrypedData1 = engine.doFinal(data);

        engine = Cipher.getInstance("DESEDE/CBC/PKCS7Padding", "BC");
        engine.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] encrypedData2 = engine.doFinal(data);

        System.err.println(HexBin.encode(encrypedData1));
        System.err.println(HexBin.encode(encrypedData2));

    }
}
