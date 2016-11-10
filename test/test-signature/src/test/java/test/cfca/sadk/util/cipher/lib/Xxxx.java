package test.cfca.sadk.util.cipher.lib;

import java.util.Arrays;

import org.junit.Assert;

import test.cfca.sadk.testdata.SM2TestData;
import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.bcsoft.BCSoftLib;
import cfca.sadk.lib.crypto.jni.JNISoftLib;
import cryptokit.jni.JNIInit;

public final class Xxxx {

    public static void main(String[] args) throws PKIException {
        JNIInit.initOpenSSL();
        JNISoftLib session = new JNISoftLib();

        byte[] data = SM2TestData.dataBytes;
        byte[] encryptedData = session.encrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPubKey, data);

        byte[] decryptedData = session.decrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPriKey, encryptedData);

        Assert.assertTrue("testSM2EncryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));

        BCSoftLib engine = new BCSoftLib();
        decryptedData = engine.decrypt(new Mechanism(Mechanism.SM2), SM2TestData.userPriKey, encryptedData);
        
        Assert.assertTrue("testSM2EncryptMechanismKeyByteArray", Arrays.equals(decryptedData, data));
        
        System.err.println("Okay");

    }

}
