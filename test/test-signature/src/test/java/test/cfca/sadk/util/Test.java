package test.cfca.sadk.util;

import java.math.BigInteger;
import java.util.Random;

import cfca.sadk.org.bouncycastle.jcajce.provider.asymmetric.sm.SM2Params;
import cfca.sadk.org.bouncycastle.math.ec.ECPoint;
import cfca.sadk.org.bouncycastle.math.ec.FixedPointCombMultiplier;
import cfca.sadk.org.bouncycastle.math.ec.WNafUtil;

public final class Test {

    public static void main(String[] args) {
        final int bitLength = 256;

        final ECPoint G = SM2Params.sm2ParameterSpec.getG();
        final BigInteger TWO = BigInteger.valueOf(2);
        final BigInteger n = SM2Params.n;
        final int minWeight = bitLength >>> 2;

        final FixedPointCombMultiplier multiplier = new FixedPointCombMultiplier();
        Random random = new Random();
        BigInteger d = new BigInteger(256, random);
        
        System.err.println(n.bitLength());

        int total = 0;
        int okay = 0;
        ECPoint Q = null;
        while (true) {
            total++;
            d = new BigInteger(bitLength, random);

            if (d.compareTo(TWO) < 0 || (d.compareTo(n) >= 0)) {
                continue;
            }

            if (d.bitLength() != bitLength) {
                continue;
            }
            if (WNafUtil.getNafWeight(d) < minWeight) {
                continue;
            }

            Q = multiplier.multiply(G, d);
            if (Q.getX().bitLength() < 249) {
                continue;
            }
            if (Q.getY().bitLength() < 249) {
                continue;
            }
            okay++;
            if (total % 1000 == 0) {
                System.err.println(String.format("%d:%d--->%.4f", total, okay, (okay * 1.0 / total)));
            }
        }

    }
}
