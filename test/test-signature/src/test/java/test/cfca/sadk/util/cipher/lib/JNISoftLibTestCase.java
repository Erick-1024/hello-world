package test.cfca.sadk.util.cipher.lib;

import cfca.sadk.lib.crypto.jni.JNISoftLib;
import cryptokit.jni.JNIInit;

public final class JNISoftLibTestCase extends SessionTestCase {

    protected void setUp() throws Exception {
        JNIInit.initOpenSSL();
        session = new JNISoftLib();
    }

    protected void tearDown() throws Exception {
        JNIInit.unInitOpenSSL();
        session = null;
    }

}
