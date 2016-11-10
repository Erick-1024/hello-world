package test.cfca.sadk.perfermance;

import java.util.Arrays;

import cfca.sadk.lib.crypto.JCrypto;

public class Constants {

    public static final String PLAT_SM2_PATH = "TestData/";
    public static final String PLAT_SM2_PWD = "111111";
    public static final String BASE_CERT_PATH = "TestData/";
    public static final String PARAM_SIGN = "anqing/signed";
    public static final String PARAM_BODY = "anqing/body";
    public static String cryptoType = JCrypto.JSOFT_LIB;
    static {
        try {
            JCrypto.getInstance().initialize(JCrypto.JNI_LIB, null);
        } catch (Exception e) {
        } catch (Error e) {
        }
    }

    private Constants() {
    }

    public static final boolean equals(byte[] a, byte[] b) {
        return Arrays.equals(a, b);
        
//        int length = Math.min(a.length, b.length);
//        for (int i = 0; i < length; i++) {
//            if (a[i] != b[i]) {
//                return false;
//            }
//        }
//        return true;
    }
}
