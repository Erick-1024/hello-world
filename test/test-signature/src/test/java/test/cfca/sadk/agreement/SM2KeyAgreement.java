package test.cfca.sadk.agreement;

import java.math.BigInteger;

import cfca.sadk.org.bouncycastle.crypto.DerivationFunction;
import cfca.sadk.org.bouncycastle.crypto.agreement.SM2MQVBasicAgreement;
import cfca.sadk.org.bouncycastle.crypto.digests.SM3Digest;
import cfca.sadk.org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import cfca.sadk.org.bouncycastle.crypto.params.ECDomainParameters;
import cfca.sadk.org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import cfca.sadk.org.bouncycastle.crypto.params.ECPublicKeyParameters;
import cfca.sadk.org.bouncycastle.crypto.params.KDFParameters;
import cfca.sadk.org.bouncycastle.crypto.params.MQVPrivateParameters;
import cfca.sadk.org.bouncycastle.crypto.params.MQVPublicParameters;
import cfca.sadk.org.bouncycastle.jcajce.provider.asymmetric.sm.SM2Params;
import cfca.sadk.org.bouncycastle.jce.spec.ECParameterSpec;
import cfca.sadk.org.bouncycastle.math.ec.ECPoint;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class SM2KeyAgreement {

    public static void testParams() throws Exception {
        ECParameterSpec spec = SM2Params.sm2dhtest;
        ECDomainParameters params = new ECDomainParameters(spec.getCurve(), spec.getG(), spec.getN(), spec.getH());

        // dA/PA
        BigInteger dA = SM2Params.fromHex("6FCBA2EF 9AE0AB90 2BC3BDE3 FF915D44 BA4CC78F 88E2F8E7 F8996D3B 8CCEEDEE");
        BigInteger xPA = SM2Params.fromHex("3099093B F3C137D8 FCBBCDF4 A2AE50F3 B0F216C3 122D7942 5FE03A45 DBFE1655");
        BigInteger yPA = SM2Params.fromHex("3DF79E8D AC1CF0EC BAA2F2B4 9D51A4B3 87F2EFAF 48233908 6A27A8E0 5BAED98B");
        ECPoint PA = spec.getCurve().createPoint(xPA, yPA);

        // rA/RA
        BigInteger rA = SM2Params.fromHex("83A2C9C8 B96E5AF7 0BD480B4 72409A9A 327257F1 EBB73F5B 073354B2 48668563");
        BigInteger xRA = SM2Params.fromHex("6CB56338 16F4DD56 0B1DEC45 8310CBCC 6856C095 05324A6D 23150C40 8F162BF0");
        BigInteger yRA = SM2Params.fromHex("0D6FCF62 F1036C0A 1B6DACCF 57399223 A65F7D7B F2D9637E 5BBBEB85 7961BF1A");
        ECPoint RA = spec.getCurve().createPoint(xRA, yRA);
        // dB/PB
        BigInteger dB = SM2Params.fromHex("5E35D7D3 F3C54DBA C72E6181 9E730B01 9A84208C A3A35E4C 2E353DFC CB2A3B53");
        BigInteger xPB = SM2Params.fromHex("245493D4 46C38D8C C0F11837 4690E7DF 633A8A4B FB3329B5 ECE604B2 B4F37F43");
        BigInteger yPB = SM2Params.fromHex("53C0869F 4B9E1777 3DE68FEC 45E14904 E0DEA45B F6CECF99 18C85EA0 47C60A4C");
        ECPoint PB = spec.getCurve().createPoint(xPB, yPB);

        // rB/RB
        BigInteger rB = SM2Params.fromHex("33FE2194 0342161C 55619C4A 0C060293 D543C80A F19748CE 176D8347 7DE71C80");
        BigInteger xRB = SM2Params.fromHex("1799B2A2 C7782953 00D9A232 5C686129 B8F2B533 7B3DCF45 14E8BBC1 9D900EE5");
        BigInteger yRB = SM2Params.fromHex("54C9288C 82733EFD F7808AE7 F27D0E73 2F7C73A7 D9AC98B7 D8740A91 D0DB3CF4");
        ECPoint RB = spec.getCurve().createPoint(xRB, yRB);

        BigInteger X = SM2Params.fromHex("47C82653 4DC2F6F1 FBF28728 DD658F21 E174F481 79ACEF29 00F8B7F5 66E40905");
        BigInteger Y = SM2Params.fromHex("2AF86EFE 732CF12A D0E09A1F 2556CC65 0D9CCCE3 E249866B BB5C6846 A4C4A295");

        final ECPoint U = spec.getCurve().createPoint(X, Y);

        // params: static
        ECPrivateKeyParameters dAParam = null;
        ECPublicKeyParameters PAParam = null;
        // params: ephemeral
        ECPrivateKeyParameters rAParam = null;
        ECPublicKeyParameters RAParam = null;
        // params: static
        ECPrivateKeyParameters dBParam = null;
        ECPublicKeyParameters PBParam = null;
        // params: ephemeral
        ECPrivateKeyParameters rBParam = null;
        ECPublicKeyParameters RBParam = null;

        dAParam = new ECPrivateKeyParameters(dA, params);
        PAParam = new ECPublicKeyParameters(PA, params);
        rAParam = new ECPrivateKeyParameters(rA, params);
        RAParam = new ECPublicKeyParameters(RA, params);
        dBParam = new ECPrivateKeyParameters(dB, params);
        PBParam = new ECPublicKeyParameters(PB, params);
        rBParam = new ECPrivateKeyParameters(rB, params);
        RBParam = new ECPublicKeyParameters(RB, params);

        MQVPrivateParameters priAParams = null;
        MQVPrivateParameters priBParams = null;
        MQVPublicParameters pubAParams = null;
        MQVPublicParameters pubBParams = null;

        priAParams = new MQVPrivateParameters(dAParam, rAParam, RAParam);
        priBParams = new MQVPrivateParameters(dBParam, rBParam, RBParam);
        pubAParams = new MQVPublicParameters(PAParam, RAParam);
        pubBParams = new MQVPublicParameters(PBParam, RBParam);

        SM2MQVBasicAgreement agreement = new SM2MQVBasicAgreement();
        agreement.init(priAParams);
        ECPoint QA = agreement.calculateMqvAgreement(pubBParams);
        System.err.println(QA.getXCoord().toBigInteger().toString(16));
        System.err.println(QA.getYCoord().toBigInteger().toString(16));

        agreement = new SM2MQVBasicAgreement();
        agreement.init(priBParams);
        ECPoint QB = agreement.calculateMqvAgreement(pubAParams);
        System.err.println(QB.getXCoord().toBigInteger().toString(16));
        System.err.println(QB.getYCoord().toBigInteger().toString(16));

        System.err.println(U.equals(QA));
        System.err.println(U.equals(QB));

        byte[] agreementBytes = agreement.getAgreementBytes(U);
        System.err.println(HexBin.encode(agreementBytes));

        DerivationFunction kdf = new KDF2BytesGenerator(new SM3Digest());
        StringBuffer ZAWithZB = new StringBuffer();
        ZAWithZB.append("E4D1D0C3CA4C7F11BC8FF8CB3F4C02A78F108FA098E51A668487240F75E20F31");
        ZAWithZB.append("6B4B6D0E276691BD4A11BF72F4FB501AE309FDACB72FA6CC336E6656119ABD67");

        byte[] seed = SM2Params.concat(agreementBytes, HexBin.decode(ZAWithZB.toString()));
        kdf.init(new KDFParameters(seed, new byte[0]));

        byte[] secret = new byte[32];
        kdf.generateBytes(secret, 0, 32);
        System.err.println(HexBin.encode(secret));

    }

    public static void smxParams() throws Exception {
        ECParameterSpec spec = SM2Params.sm2ParameterSpec;
        ECDomainParameters params = new ECDomainParameters(spec.getCurve(), spec.getG(), spec.getN(), spec.getH());

        // dA/PA
        BigInteger dA = SM2Params.fromHex("df897e17dd9ee049e2323e0804f0f8408bc32bdd464391b7a3fd5215f09161c8");
        BigInteger xPA = SM2Params.fromHex("25c94ce0f1b858871d60eb784b24dc26b867ae073764b92f03678fc0fea66261");
        BigInteger yPA = SM2Params.fromHex("9736057de8caf8e842632624fcef6ad8bc8fa041c40744e8be52416a7cf5c65c");
        ECPoint PA = spec.getCurve().createPoint(xPA, yPA);

        // rA/RA
        BigInteger rA = SM2Params.fromHex("0759643f61d46bf1dcaa9df84d57c66027abfc5c54a800932e85e5fa99a40241");
        BigInteger xRA = SM2Params.fromHex("d0084f671b9108a334f36343e151b8d05de7963c0a7a6c7698411126facf6b09");
        BigInteger yRA = SM2Params.fromHex("cdec5e85937374bc154c1e8f16c82fc88a874d7bf86caccac345091911964a86");
        ECPoint RA = spec.getCurve().createPoint(xRA, yRA);

        // dB/PB
        BigInteger dB = SM2Params.fromHex("c3f0dc4580697bda66b8c1a2372d8c764e25472f31ff7b73d1c369773888f3f6");
        BigInteger xPB = SM2Params.fromHex("03b9656b0e3065671478aa726270efb395c8b9d69ca9d58de1ce302dcb2da885");
        BigInteger yPB = SM2Params.fromHex("f1bf1be9a4806cb766963a4a2a6b60b0236d4c1793e5ba67258ed7c69db1affc");
        ECPoint PB = spec.getCurve().createPoint(xPB, yPB);

        // rB/RB
        BigInteger rB = SM2Params.fromHex("f8567e5a8baaf0d688b9dd3dbd8170583757ba74cd42ec8d1cf75d51726ba65e");
        BigInteger xRB = SM2Params.fromHex("20e8eef5236c76b7a3330a46b84a200acfdaa3008f086bc5d1fa4180d27ba547");
        BigInteger yRB = SM2Params.fromHex("03ed1dc3f13c650f0b9755121707a3bf4167a023b49357252ca24b4709cd8a2a");
        ECPoint RB = spec.getCurve().createPoint(xRB, yRB);

        // params: static
        ECPrivateKeyParameters dAParam = null;
        ECPublicKeyParameters PAParam = null;
        // params: ephemeral
        ECPrivateKeyParameters rAParam = null;
        ECPublicKeyParameters RAParam = null;
        // params: static
        ECPrivateKeyParameters dBParam = null;
        ECPublicKeyParameters PBParam = null;
        // params: ephemeral
        ECPrivateKeyParameters rBParam = null;
        ECPublicKeyParameters RBParam = null;

        dAParam = new ECPrivateKeyParameters(dA, params);
        PAParam = new ECPublicKeyParameters(PA, params);
        rAParam = new ECPrivateKeyParameters(rA, params);
        RAParam = new ECPublicKeyParameters(RA, params);
        dBParam = new ECPrivateKeyParameters(dB, params);
        PBParam = new ECPublicKeyParameters(PB, params);
        rBParam = new ECPrivateKeyParameters(rB, params);
        RBParam = new ECPublicKeyParameters(RB, params);

        MQVPrivateParameters priAParams = null;
        MQVPrivateParameters priBParams = null;
        MQVPublicParameters pubAParams = null;
        MQVPublicParameters pubBParams = null;

        priAParams = new MQVPrivateParameters(dAParam, rAParam, RAParam);
        priBParams = new MQVPrivateParameters(dBParam, rBParam, RBParam);
        pubAParams = new MQVPublicParameters(PAParam, RAParam);
        pubBParams = new MQVPublicParameters(PBParam, RBParam);

        SM2MQVBasicAgreement agreement = new SM2MQVBasicAgreement();
        agreement.init(priAParams);
        ECPoint QA = agreement.calculateMqvAgreement(pubBParams);
        System.err.println("Xu:"+QA.getXCoord().toBigInteger().toString(16));
        System.err.println("Yu:"+QA.getYCoord().toBigInteger().toString(16));

        agreement = new SM2MQVBasicAgreement();
        agreement.init(priBParams);
        ECPoint QB = agreement.calculateMqvAgreement(pubAParams);
        System.err.println("Xv:"+QB.getXCoord().toBigInteger().toString(16));
        System.err.println("Yv:"+QB.getYCoord().toBigInteger().toString(16));

        BigInteger X = SM2Params.fromHex("5021dde8d0831fed45f35a6dcee82d31032571c5100bb3d796c63cbff32b7807");
        BigInteger Y = SM2Params.fromHex("bc15efe915a73391b7600fb25e3dbde6719ed592a7122b766e47f5b80ac90be2");

        final ECPoint U = spec.getCurve().createPoint(X, Y);

        System.err.println(U.equals(QA));
        System.err.println(U.equals(QB));

        byte[] agreementBytes = agreement.getAgreementBytes(U);
        System.err.println("XY: "+HexBin.encode(agreementBytes));

        DerivationFunction kdf = new KDF2BytesGenerator(new SM3Digest());
        StringBuffer ZAWithZB = new StringBuffer();
        ZAWithZB.append("46ea1a7940a853e426e665010c7d7ff7195cbbe7edc786270cef249b6320e2df");
        ZAWithZB.append("a9911600459de1c8048991844ec2d6479996cacf51a817a64b078f2bbddf7fab");

        byte[] seed = SM2Params.concat(agreementBytes, HexBin.decode(ZAWithZB.toString()));
        
        System.err.println("KDF: "+HexBin.encode(seed).toLowerCase());
        
        kdf.init(new KDFParameters(seed, new byte[0]));

        byte[] secret = new byte[32];
        kdf.generateBytes(secret, 0, 32);
        System.err.println(""+HexBin.encode(secret));

    }

    public static void main(String[] args) throws Exception {

        System.err.println();
        testParams();
        System.err.println();
        smxParams();

    }

}
