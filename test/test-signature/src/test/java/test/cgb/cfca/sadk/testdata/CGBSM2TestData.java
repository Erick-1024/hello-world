package test.cgb.cfca.sadk.testdata;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;

import test.cgb.cfca.sadk.CGBTestData;
import cfca.sadk.algorithm.sm2.SM2PrivateKey;
import cfca.sadk.algorithm.sm2.SM2PublicKey;
import cfca.sadk.asn1.pkcs.PKCS12_SM2;
import cfca.sadk.org.bouncycastle.asn1.ASN1InputStream;
import cfca.sadk.org.bouncycastle.asn1.sm2.ASN1SM2Signature;
import cfca.sadk.org.bouncycastle.math.ec.ECPoint;
import cfca.sadk.org.bouncycastle.util.BigIntegers;
import cfca.sadk.org.bouncycastle.util.encoders.Hex;
import cfca.sadk.util.Base64;
import cfca.sadk.x509.certificate.X509Cert;

/**
 * SM2TestData for testing
 * 
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public final class CGBSM2TestData {

    static final String sm2UserPass = "123123";
    public static final String sm2UserPfxText = "MIIC3AIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFoBDDHKo2zugcLR1Kdy2wBWczX+ZYtefwRzT8QLBjNfw7KYmk9L+huHohgb905xaG/85swggKMBgoqgRzPVQYBBAIBBIICfDCCAngwggIdoAMCAQICBRABKRlTMAwGCCqBHM9VAYN1BQAwHzELMAkGA1UEBhMCQ04xEDAOBgNVBAoMB0JPQyBTTTIwHhcNMTUwNDAxMDgyNzA2WhcNMTcwNDAxMDgyNzA2WjB6MQswCQYDVQQGEwJDTjEVMBMGA1UECgwMQ0ZDQSBURVNUIENBMREwDwYDVQQLDAhMb2NhbCBSQTEVMBMGA1UECwwMSW5kaXZpZHVhbC0xMSowKAYDVQQDDCEwNTFA5rCR55Sf5rWL6K+VQDExMjMyMzExMjEzMTMyQDEwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAAQ7r0qWKsc3NskRMPax/njhaDTWoisulQamBpgTd6HI89oJhqgLGU4L6fxtQAzrjJPYHaXIJSdRk4KkYFA7y+YQo4HoMIHlMB8GA1UdIwQYMBaAFGv+GNqPQjqmuG2zLoiDOjSiwTDhMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL3VjcmwuY2ZjYS5jb20uY24vU00yL2NybDg0Ni5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQJz1J2I2w4bwtGk9AEmwp07hFU7zATBgNVHSUEDDAKBggrBgEFBQcDAjAMBggqgRzPVQGDdQUAA0cAMEQCIB8ZzwkUhcu0QcQ7dAKhdAl0tTWoJG8eFuhb8SjCDczzAiA6AZuG7DpyveTAD1eiK3QY3rhtIHfnetCrWHv4eZsOAA==";
    static final String sm2UserKeyText = "MD0CAQAwCwYHKoZIzj0CAQUABCswKQIBAQQgA95G6XYwvjyYujovQDGP13JZk0qgrMDczrui3g+zcRCgAgUA";
    static final String sm2UserCertText = "MIICeDCCAh2gAwIBAgIFEAEpGVMwDAYIKoEcz1UBg3UFADAfMQswCQYDVQQGEwJDTjEQMA4GA1UECgwHQk9DIFNNMjAeFw0xNTA0MDEwODI3MDZaFw0xNzA0MDEwODI3MDZaMHoxCzAJBgNVBAYTAkNOMRUwEwYDVQQKDAxDRkNBIFRFU1QgQ0ExETAPBgNVBAsMCExvY2FsIFJBMRUwEwYDVQQLDAxJbmRpdmlkdWFsLTExKjAoBgNVBAMMITA1MUDmsJHnlJ/mtYvor5VAMTEyMzIzMTEyMTMxMzJAMTBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABDuvSpYqxzc2yREw9rH+eOFoNNaiKy6VBqYGmBN3ocjz2gmGqAsZTgvp/G1ADOuMk9gdpcglJ1GTgqRgUDvL5hCjgegwgeUwHwYDVR0jBBgwFoAUa/4Y2o9COqa4bbMuiIM6NKLBMOEwSAYDVR0gBEEwPzA9BghggRyG7yoBATAxMC8GCCsGAQUFBwIBFiNodHRwOi8vd3d3LmNmY2EuY29tLmNuL3VzL3VzLTE0Lmh0bTA3BgNVHR8EMDAuMCygKqAohiZodHRwOi8vdWNybC5jZmNhLmNvbS5jbi9TTTIvY3JsODQ2LmNybDALBgNVHQ8EBAMCA+gwHQYDVR0OBBYEFAnPUnYjbDhvC0aT0ASbCnTuEVTvMBMGA1UdJQQMMAoGCCsGAQUFBwMCMAwGCCqBHM9VAYN1BQADRwAwRAIgHxnPCRSFy7RBxDt0AqF0CXS1Nagkbx4W6FvxKMINzPMCIDoBm4bsOnK95MAPV6IrdBjeuG0gd+d60KtYe/h5mw4A";
    static final String sm2CACertText = " MIIB9jCCAZmgAwIBAgIFIAAAAAgwDAYIKoEcz1UBg3UFADBGMQswCQYDVQQGEwJDTjEZMBcGA1UECgwQQ0ZDQSBURVNUIFNNMiBDQTEcMBoGA1UEAwwTQ0ZDQSBDUyBURVNUIFNNMiBDQTAeFw0xMjEyMjUxMjI1MDZaFw0zMjA3MjMxMjI1MDZaMB8xCzAJBgNVBAYTAkNOMRAwDgYDVQQKDAdCT0MgU00yMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEM7hYCW3nWOebujU6CRJRlsz0/vVBdXcXKKZ3qzTt36kXeAtZQ88/SyWUn5jMjWGpMmAvus4fRuv2j11tTnf0RaOBmDCBlTAfBgNVHSMEGDAWgBS12JBvXPDYM9JjvX6yw43GTxJ6YTAMBgNVHRMEBTADAQH/MDgGA1UdHwQxMC8wLaAroCmGJ2h0dHA6Ly8yMTAuNzQuNDEuODcvcmNhc20yL1NNMi9jcmwxLmNybDALBgNVHQ8EBAMCAf4wHQYDVR0OBBYEFGv+GNqPQjqmuG2zLoiDOjSiwTDhMAwGCCqBHM9VAYN1BQADSQAwRgIhAMy54+ZNRjoHMXs2yFVtZmkyTCTzGTuV0LxwDf3BowBaAiEA1+XOlir3YEJxZ9qv1bAql/hccjo/kQ3bqex/DbL3uFc=";
    static final String sm2Text = "YWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYQ==";

    static final String dataTest = "TUlJQlZRWUtLb0VjejFVR0FRUUNBNkNDQVVVd2dnRkJBZ0VDTVlHZE1JR2FBZ0VDZ0JRSnoxSjJJMnc0Ynd0R2s5QUVtd3AwN2hGVTd6QU5CZ2txZ1J6UFZRR0NMUU1GQUFSd2djRVNjOGFyWEIrczZpbDAxUzBJZko5SWZNMlErRmpGMmNTVXYveUQ2aHdNTERuODdoaE92ZHcwdW9QQjFTOEJ1cS9jL3cxQytuc3V6MjJaSVdwRkxQaEYvWEtmWFhabEZmTGpLM29wcG4zVXlaWDBVYUt6UWZqSGFock5kRzhNNUpqNFZZSVI1dUZWdnozcTRSRmVRekNCbXdZS0tvRWN6MVVHQVFRQ0FUQWJCZ2NxZ1J6UFZRRm9CQkQ1dzNNUDdzSTBuMklpU2RqaFhreUZnSENKdGVLQWd6WFNzS0k3amduQXJoZ3o5cDVITlZnZm9QNEVRZkwvQjBCbHdGNklob1dWQjRaT1d0MWhTa1hIQzhGN0d0YmlYak5zZnFhb2JGT2htbUlVN2U3cE9kRVRrVFJhdXF1K3hCUWJpRXp1QitsUkJHUEJra1ZHWVBMaDljSFFHZG0vTkc0bGJuRjAzdjNoZmU3RQ==";
    static final String encryptedText = "Xg7HPrUBiXA3OI8y4xhSFAg3o9dPnD5WsLqji3K6xmQF/t6rBrO/O3/sorK6A77AEjYCpzoAKKBcy+MkQttjcRazr43goNbVINT2pSdqr3Po7ZyjCDExwpnQMW+vzUjsRAt10d+RAGcMy1eUOEQGfIEBohgiE7ChvskMFlwFpSI5mzr435S/nesz10TaWf5BnOV+XiTs6adeDwaUrNNabSDS6f7sMtM34B+tg8lRlcqqoTd6/2Vio3qmggyEKWPXdu0xVH6UluB5ITTDjcjn/CM0gLJTjCKrKVV8/JmAVQtNMU1SSHs4c2H0SFUz2s5XmBreIbGiTan1kNpbCRooOGkD6fnXuPUQD0ao8tHvnm/ytDpEpPOiifw0z+gOAqRoYPCJ4mRhKV8SFka1+CPoy4y11ZqclSq4Itmvs8hB8/LeZZDXmZh6bUxmjhk/vX0YQCBn3xSVhTtuKAMbdUoOxOt9ZAJp1rnZDTX3gKOcK5SuQ0VLEsHSsTVf+fvIVYWVQu7fbeZ1sgVDrAAaYw7gyNsUmFkvItadTa+VuuMFDN4NShKXrJfBKzOfjv6J9CCx8zxWxZ/ySCACckMsxVJQ6+wgJW2NyXOfQbIIbyYOWkaOLfGHN9vWvkUOirXDlsX701/BJytMiDqF26Ykdb3pvNT5o96xpJpDNQO28VXdd7YnjY3rOGgT2Ah59ccWpxzqoZB8KXgUmORM6fZeWfWpVVBw9SiUk0VZazJVCQ==";

    static final String sm2ZText = "ddJzGzMRoBNgf5Xij17yEf0VEyYjdoJi4gzmt9WSN0s=";
    static final String sm2HashText = "lTGfDlOsMJ5zBBGPWpCa+aaXABjqtKJI/lLj/yo1c30=";
    static final String sm2SignText = "MEUCIQDmuPJlCbdOJfj7e024MgaZqfEoSVoZZKByvC4pumFbsQIgAebpzLJK6psvipS1fEMulqV6j/n1f0Vhe8j7zg7Bpbc=";

    public static final String base64PriKey = "MD0CAQAwCwYHKoZIzj0CAQUABCswKQIBAQQgA95G6XYwvjyYujovQDGP13JZk0qgrMDczrui3g+zcRCgAgUA";
    public static final String base64PubKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEO69KlirHNzbJETD2sf544Wg01qIrLpUGpgaYE3ehyPPaCYaoCxlOC+n8bUAM64yT2B2lyCUnUZOCpGBQO8vmEA==";

    public static SM2PrivateKey userPriKey;
    public static SM2PublicKey userPubKey;
    public static BigInteger userD;
    public static ECPoint userPoint;
    public static X509Cert userCert;
    public static X509Cert cacert;

    public static byte[] sm2Data;
    public static byte[] sm2ZValue;
    public static byte[] sm2HashValue;
    public static byte[] sm2SignValue;
    public static BigInteger r = new BigInteger("e6b8f26509b74e25f8fb7b4db8320699a9f128495a1964a072bc2e29ba615bb1", 16);
    public static BigInteger s = new BigInteger("01e6e9ccb24aea9b2f8a94b57c432e96a57a8ff9f57f45617bc8fbce0ec1a5b7", 16);

    public static byte[] signBytes = new byte[64];

    public static byte[] dataBytes;
    public static byte[] encryptedBytes;
    static {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sm2UserPfxText));
            ASN1InputStream ais = new ASN1InputStream(bis);

            PKCS12_SM2 object = PKCS12_SM2.getInstance(ais.readObject());
            userPriKey = object.getPrivateKey(sm2UserPass);
            userCert = object.getPublicCert()[0];
            userPubKey = (SM2PublicKey) userCert.getPublicKey();
            userD = userPriKey.dBigInteger();
            userPoint = userPubKey.getQ();
            ais.close();
            bis.close();

            cacert = new X509Cert(new ByteArrayInputStream(Base64.decode(sm2CACertText)));

            sm2Data = Base64.decode(sm2Text);
            sm2ZValue = Base64.decode(sm2ZText);
            sm2HashValue = Base64.decode(sm2HashText);
            sm2SignValue = Base64.decode(sm2SignText);

            dataBytes = Base64.decode(dataTest);
            encryptedBytes = Base64.decode(encryptedText);

            ASN1SM2Signature sigVal = ASN1SM2Signature.getInstance(CGBSM2TestData.sm2SignValue);

            System.arraycopy(BigIntegers.asUnsignedByteArray(32, sigVal.getR().getValue()), 0, signBytes, 0, 32);
            System.arraycopy(BigIntegers.asUnsignedByteArray(32, sigVal.getS().getValue()), 0, signBytes, 32, 32);

            boolean writeFile = false;
            if (writeFile) {
                FileOutputStream fos = new FileOutputStream(CGBTestData.TESTDATA_DIR + "sm2/test.dat");
                fos.write(sm2Data);
                fos.close();
                fos = new FileOutputStream(CGBTestData.TESTDATA_DIR + "sm2/test.sig");
                fos.write(sm2SignValue);
                fos.close();
                fos = new FileOutputStream(CGBTestData.TESTDATA_DIR + "sm2/test.bin");
                int i = 0;
                while (i++ < 512) {
                    fos.write(dataBytes);

                }
                fos.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.err.println(userPriKey);
        System.err.println(userCert);
        System.err.println(cacert);

        System.err.println(new String(Hex.encode(sm2SignValue)));

    }

}
