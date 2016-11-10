/**
 * 
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author shuiren
 *
 */
public class RSAUtilTest {

	@Test
	public void test() throws Exception{
		// 生成秘钥对，有java版和net版， 目前只提供java版的demo
		RSAKeyPair keyPair = RSAUtil.generateKeyPair();
		System.out.println("public key for java:" + keyPair.getPublicKeyJava());
		System.out.println("private key for java:" + keyPair.getPrivateKeyJava());
		System.out.println("public key for net:" + keyPair.getPublicKeyNet());
		System.out.println("private key for net:" + keyPair.getPrivateKeyNet());
		//公钥加密，私钥解密
		String plain = "this is a plain string";
		String sign = new String(RSAUtil.encryptByPublicKey(plain.getBytes(), keyPair.getPublicKeyJava()));
		assertEquals(plain, new String(RSAUtil.decryptByPrivateKey(sign.getBytes(), keyPair.getPrivateKeyJava())));
		//私钥加密，公钥解密
        sign = new String(RSAUtil.encryptByPrivateKey(plain.getBytes(), keyPair.getPrivateKeyJava()));
        assertEquals(plain, new String(RSAUtil.decryptByPublicKey(sign.getBytes(), keyPair.getPublicKeyJava())));
        //私钥签名，公钥验签
        byte[] signArr = RSAUtil.sign(plain.getBytes(), keyPair.getPrivateKeyJava());
        assertTrue(RSAUtil.verify(plain.getBytes(), keyPair.getPublicKeyJava(), signArr));
	}
}
