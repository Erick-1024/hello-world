package test.cfca.sadk.perfermance;

import test.cfca.sadk.testdata.TestReady;
import cfca.sadk.lib.crypto.jni.JNIDigest;
import cfca.sadk.org.bouncycastle.crypto.Digest;
import cfca.sadk.org.bouncycastle.crypto.digests.MD5Digest;
import cfca.sadk.org.bouncycastle.crypto.digests.SHA1Digest;
import cfca.sadk.org.bouncycastle.crypto.digests.SHA256Digest;
import cfca.sadk.org.bouncycastle.crypto.digests.SHA384Digest;
import cfca.sadk.org.bouncycastle.crypto.digests.SHA512Digest;
import cfca.sadk.org.bouncycastle.crypto.digests.SM3Digest;

public final class TestHash {

    public static void main(String[] args) throws Exception {
        TestReady.openSession();
        testHash("SM3", 16);
        testHash("SM3", 64);
        System.err.println();
        testHash("SHA1", 16);
        testHash("SHA1", 64);
        System.err.println();
        testHash("SHA256", 16);
        testHash("SHA256", 64);
        System.err.println();
        testHash("SHA512", 16);
        testHash("SHA512", 64);
        System.err.println();
        testHash("MD5", 16);
        testHash("MD5", 64);

    }

    public static void testHash(final String hashAlg, final int mBytes) throws Exception {

        long endTime = System.currentTimeMillis();
        long strTime = System.currentTimeMillis();

        byte[] data = new byte[1024 * 1024];

        Digest engine = Digest(hashAlg);
        for (int i = 0; i < mBytes; i++) {
            engine.update(data, 0, data.length);
        }

        byte[] out = new byte[engine.getDigestSize()];
        engine.doFinal(data, 0);

        endTime = System.currentTimeMillis();
        long xtime = endTime - strTime;
        System.err.println(String.format("Hash=%4s:%-10s dataLength=%10dMB time=%-10d", "java", hashAlg, (mBytes), xtime));

        strTime = System.currentTimeMillis();
        JNIDigest jniengine = JNIDigest(hashAlg);
        for (int i = 0; i < mBytes; i++) {
            jniengine.update(data);
        }
        out = new byte[jniengine.getDigestSize()];
        jniengine.doFinal(out);
        endTime = System.currentTimeMillis();
        long ztime = endTime - strTime;
        System.err.println(String.format("Hash=%4s:%-10s dataLength=%10dMB time=%-10d  %.2f", "cjni", hashAlg, (mBytes), ztime, xtime * 1.0 / ztime));

    }

    final static JNIDigest JNIDigest(final String hashAlg) throws Exception {
        JNIDigest engine = new JNIDigest();
        int hashType = 0;
        if ("SHA1".equals(hashAlg)) {
            hashType = JNIDigest.NID_sha1;
        } else if ("SHA256".equals(hashAlg)) {
            hashType = JNIDigest.NID_sha256;
        } else if ("SHA384".equals(hashAlg)) {
            hashType = JNIDigest.NID_sha384;
        } else if ("SHA512".equals(hashAlg)) {
            hashType = JNIDigest.NID_sha512;
        } else if ("SM3".equals(hashAlg)) {
            hashType = JNIDigest.NID_ChinaSM3;
        } else if ("MD5".equals(hashAlg)) {
            hashType = JNIDigest.NID_md5;
        }
        engine.init(hashType);
        return engine;

    }

    final static Digest Digest(String hashAlg) {
        if ("SHA1".equals(hashAlg)) {
            return new SHA1Digest();
        } else if ("SHA256".equals(hashAlg)) {
            return new SHA256Digest();
        } else if ("SHA384".equals(hashAlg)) {
            return new SHA384Digest();
        } else if ("SHA512".equals(hashAlg)) {
            return new SHA512Digest();
        } else if ("SM3".equals(hashAlg)) {
            return new SM3Digest();
        } else if ("MD5".equals(hashAlg)) {
            return new MD5Digest();
        }
        throw new IllegalArgumentException(hashAlg);
    }

}
