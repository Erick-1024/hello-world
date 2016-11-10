package test.cfca.sadk.algorithm.sm2;

import java.math.BigInteger;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cfca.sadk.algorithm.sm2.SM2PrivateKey;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class TestCaseSM2PrivateKey {

    final byte[] encoding = HexBin
            .decode("308187020100301306072A8648CE3D020106082A811CCF5501822D046D306B020101022061895E2A50D6808125AD4265A6A020F04827756C6D92DA0980DA7F2EB2162D00A14403420004365CDF9CF093EFEB57C02953AC35ACB9D0402909E954520A223B304922133CED17204683389F5A4073BDF268C8FA6C1C2D6FD767F85A7A518A0F6137F6C9901C");

    final byte[] old_version = HexBin
            .decode("303D020100300B06072A8648CE3D02010500042B3029020101042061895E2A50D6808125AD4265A6A020F04827756C6D92DA0980DA7F2EB2162D00A0020500");

    final byte[] dBytes = (HexBin.decode("61895e2a50d6808125ad4265a6a020f04827756c6d92da0980da7f2eb2162d00"));
    final byte[] xBytes = (HexBin.decode("365cdf9cf093efeb57c02953ac35acb9d0402909e954520a223b304922133ced"));
    final byte[] yBytes = (HexBin.decode("17204683389f5a4073bdf268c8fa6c1c2d6fd767f85a7a518a0f6137f6c9901c"));

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSM2PrivateKeyByteArray() throws Exception {

        SM2PrivateKey k2 = new SM2PrivateKey(old_version);

        Assert.assertTrue("testSM2PrivateKeyByteArray", Arrays.equals(encoding, k2.getEncoded()));

        SM2PrivateKey k1 = new SM2PrivateKey(encoding);

        Assert.assertTrue("testSM2PrivateKeyByteArray", Arrays.equals(encoding, k1.getEncoded()));

    }

    @Test
    public void testSM2PrivateKeyByteArrayByteArrayByteArray() throws Exception {
        SM2PrivateKey k1 = new SM2PrivateKey(dBytes, xBytes, yBytes);

        Assert.assertTrue("testSM2PrivateKeyByteArrayByteArrayByteArray", Arrays.equals(encoding, k1.getEncoded()));

        k1 = new SM2PrivateKey(dBytes, null, null);

        Assert.assertTrue("testSM2PrivateKeyByteArrayByteArrayByteArray", Arrays.equals(encoding, k1.getEncoded()));
    }

    @Test
    public void testSM2PrivateKeyBigIntegerBigIntegerBigInteger() throws Exception {
        SM2PrivateKey k1 = new SM2PrivateKey(new BigInteger(1, dBytes), new BigInteger(1, xBytes), new BigInteger(1, yBytes));

        Assert.assertTrue("testSM2PrivateKeyBigIntegerBigIntegerBigInteger", Arrays.equals(encoding, k1.getEncoded()));

        k1 = new SM2PrivateKey(new BigInteger(1, dBytes), null, null);

        Assert.assertTrue("testSM2PrivateKeyBigIntegerBigIntegerBigInteger", Arrays.equals(encoding, k1.getEncoded()));
    }

}
