package test.cfca.sadk.testdata;

import java.math.BigInteger;

import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.asn1.DERHeader;
import cfca.sadk.org.bouncycastle.asn1.pkcs.CertificationRequest;
import cfca.sadk.org.bouncycastle.asn1.sm2.ASN1SM2Cipher;
import cfca.sadk.org.bouncycastle.asn1.sm2.ASN1SM2Signature;
import cfca.sadk.org.bouncycastle.jcajce.provider.asymmetric.sm.SM2Params;
import cfca.sadk.org.bouncycastle.jce.spec.ECParameterSpec;
import cfca.sadk.org.bouncycastle.math.ec.ECPoint;
import cfca.sadk.org.bouncycastle.util.BigIntegers;
import cfca.sadk.util.Base64;

public final class SM2 {

    static ECParameterSpec params = SM2Params.sm2ParameterSpec;

    private SM2() {
    }

    /**
     * RAW(C1+C3+C2)
     * 
     * @param encryptedBytes
     * @return
     */
    public static boolean isECITICEncryptedFormatRAWBytes(byte[] encryptedBytes) {
        DERHeader der = null;
        try {
            der = new DERHeader(encryptedBytes, 0);
            if ((der.getTag() == 0x30 || der.getDerLength() == encryptedBytes.length)) {
                return false;
            }
        } catch (Exception e) {
            der = null;
        }

        BigInteger x = BigIntegers.fromUnsignedByteArray(encryptedBytes, 0, 32);
        BigInteger y = BigIntegers.fromUnsignedByteArray(encryptedBytes, 32, 32);

        ECPoint point = params.getCurve().createPoint(x, y);
        return point.isValid();

    }

    /**
     * ASN1(C1+C3+C2)
     * 
     * @param encryptedBytes
     * @return
     */
    public static boolean isECITICEncryptedFormatASNBytes(byte[] encryptedBytes) {
        DERHeader der = null;
        try {
            der = new DERHeader(encryptedBytes, 0);
            if ((der.getTag() == 0x30 || der.getDerLength() == encryptedBytes.length)) {

                ASN1SM2Cipher obj = ASN1SM2Cipher.getInstance(encryptedBytes);
                BigInteger x = obj.getXCoordinate().getPositiveValue();
                BigInteger y = obj.getYCoordinate().getPositiveValue();
                ECPoint point = params.getCurve().createPoint(x, y);
                return point.isValid();
            }
        } catch (Exception e) {
            der = null;
        }

        return false;

    }

    public static final boolean sm2CheckedSignedBytesFormatForRAW(byte[] p10Bytes, final Mechanism mechanism) {
        boolean checkedSigned64Bytes = true;
        if (Mechanism.SM3_SM2.equals(mechanism.getMechanismType())) {
            CertificationRequest certReq = CertificationRequest.getInstance(Base64.decode(p10Bytes));

            byte[] signedBytes = certReq.getSignature().getBytes();
            checkedSigned64Bytes = (signedBytes.length == 64);
        }
        return checkedSigned64Bytes;
    }

    public static final boolean sm2CheckedSignedBytesFormatForASN1(byte[] p10Base64Bytes, final Mechanism mechanism) {
        if (Mechanism.SM3_SM2.equals(mechanism.getMechanismType())) {
            CertificationRequest certReq = CertificationRequest.getInstance(Base64.decode(p10Base64Bytes));

            byte[] signedBytes = certReq.getSignature().getBytes();

            try {
                DERHeader der = new DERHeader(signedBytes, 0);
                if ((der.getTag() == 0x30 || der.getDerLength() == signedBytes.length)) {
                    ASN1SM2Signature.getInstance(signedBytes);
                    return true;
                }
            } catch (Exception e) {

            }
            return false;
        } else {
            return true;
        }

    }



}
