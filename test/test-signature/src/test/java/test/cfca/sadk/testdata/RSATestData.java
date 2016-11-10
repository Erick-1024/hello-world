package test.cfca.sadk.testdata;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

import cfca.sadk.org.bouncycastle.asn1.x509.Certificate;
import cfca.sadk.util.Base64;
import cfca.sadk.x509.certificate.X509Cert;

public final class RSATestData {
    static final String sm2UserPass = "123123";
    static final String pfxText = "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";
    public  static final String certText = "MIID+zCCAuOgAwIBAgIFEAEpGVYwDQYJKoZIhvcNAQEFBQAwGzELMAkGA1UEBhMCQ04xDDAKBgNVBAoTA0JPQzAeFw0xNTA0MDEwODMwMzNaFw0xNzA0MDEwODMwMzNaMHcxCzAJBgNVBAYTAkNOMRUwEwYDVQQKEwxDRkNBIFRFU1QgQ0ExETAPBgNVBAsTCExvY2FsIFJBMRUwEwYDVQQLEwxJbmRpdmlkdWFsLTExJzAlBgNVBAMMHjA1MUDmsJHnlJ9SU0FAMTIzMjMyMzIzMjMyMzJAMTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAO/5yImE/uKQThGmepv2JvdnHicv5xFdMzwuRWKZQC4Y9/1zwPk9mhtuoxIuEW3jHZSIW8OzoQ18FLRE8JvxH0jsrq6bSmQlPfg0Xt8Ir+fR3WoRbJukaMFyGLVvwUOpCY/wyqmuPWK5iCBLC12syLBGImMMr8NYRRJK/qNv/7J+0KFPElh4oAfjtJIDtdeOFTVsM/vyIWQB2a7a0zsAfhV6sP6EswfjXXN12WyLuI33Ui89zthXCjYGemko/UfPMISqU/FSfpuQ0Nua9KisWPG8KNe7IuV0YKBOzYAz5621to6DTrgHit9PHoUXME3418nUh9jJEC25Xf/Qgg5b+0ECAwEAAaOB6TCB5jAfBgNVHSMEGDAWgBTPcJ1h6518Lrj3ywJA9wmd/jN0gDBIBgNVHSAEQTA/MD0GCGCBHIbvKgEBMDEwLwYIKwYBBQUHAgEWI2h0dHA6Ly93d3cuY2ZjYS5jb20uY24vdXMvdXMtMTQuaHRtMDgGA1UdHwQxMC8wLaAroCmGJ2h0dHA6Ly91Y3JsLmNmY2EuY29tLmNuL1JTQS9jcmwxNjM5LmNybDALBgNVHQ8EBAMCA+gwHQYDVR0OBBYEFJfkSleFR4y/8xEqD9DQlcXzF4V/MBMGA1UdJQQMMAoGCCsGAQUFBwMCMA0GCSqGSIb3DQEBBQUAA4IBAQBKazYcS+1IzXWnfUuRbrQ2Gvgso4YYOl19rYtLCIGSH5d9K8csLznIVuMjBjQzDl3l7SYb/txIAjmu76AeGbjaiD0Z3t9p/eL2sJ18eD1nLf5sWe4k0a2Qn/1DJvG0xhXLCt6RO077E/MwM8L6cdz1ip718ooU6w79G+qJm+bOISWFKIq9ZzkFMUPjrVH3nsJC9OAgQsezyooV6RkmjWJ6FPd5dUIWOluhIACShiHWOj8nYVq/LDDkf9V/M6OTHOpvrvdk94f1LFKopWN7aRrnnpmes7f4vnA5WBIFsA25TZDVAFPoOyCU8YrnXYn2MB7r8NbHYBEGUJDvvYcL8Lv8";
    static final String keyText = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDv+ciJhP7ikE4Rpnqb9ib3Zx4nL+cRXTM8LkVimUAuGPf9c8D5PZobbqMSLhFt4x2UiFvDs6ENfBS0RPCb8R9I7K6um0pkJT34NF7fCK/n0d1qEWybpGjBchi1b8FDqQmP8Mqprj1iuYggSwtdrMiwRiJjDK/DWEUSSv6jb/+yftChTxJYeKAH47SSA7XXjhU1bDP78iFkAdmu2tM7AH4VerD+hLMH411zddlsi7iN91IvPc7YVwo2BnppKP1HzzCEqlPxUn6bkNDbmvSorFjxvCjXuyLldGCgTs2AM+ettbaOg064B4rfTx6FFzBN+NfJ1IfYyRAtuV3/0IIOW/tBAgMBAAECggEALM/KANQS/J/R1D3wO1t131EkMlEhpv5uaNiDIGxZrcH/3RZWfLRiKKk6TlTH8GsuxOPJrvvGIUyAWUFeZOBb5TQ9UmGxPI4vyj+NT7zBjFqGLB5g15eV8DmsH4Vk44uXO4fNcG4XDG502wLodm+jcjdGiJR/5cx/6XCTqFVrvBrRqqKJkhVUY0Yd9GyXXjKwoIUDNLwYvXTXML72taSqgPVaGZC/u3qFeXDZEKCn8casDy44bfbw6mTju0WKs4KoDGtqlPCXN65+kwo3Jlfzx5tWMc4xvWBPPIc0jXaRd6qb/iWAvrADNHtXWlFE7WLfcjniTLZ9wPqNz2OJLOWtoQKBgQD7dB8BjD+jTfYkK9OGy/yRfgjlLDfxGqZLaMl2KUp189EFz02hKKEt41/2tWaOtliOWsyVvYNsLwTzIpZr/YWLgsqLSKkiUBg3g6V+jVJvDQNk31SHGwPqBF5f69dBi4WVUiSqka+XCHWwo3pXwjn9JOCNUW7SWYg6eh4p+SQy9QKBgQD0UIkhz/gWnFa3YT1zTnbjlMvdMAzpQcCJWHkL+gCqbLifyR2FrADGWARt1YZL+N4cBm54UQTB2omUU05fu7iQO9RXZh3KEbNKcu1XTZ26ixVwOxSo2lYX33RmnuGhBkf75sgtVDkJitH8V9SsDz4641iLJXq9MQWHOIVw937vnQKBgQCEpIWyoFP1hiugewnzImnSKeTxVzkPU1qDjvwu+6sOu5X4dZliuPcFKNs3XguCnsfqGBWxq63HBKqcwxMMVkj7BfxoKuUld5C+wBAtyMjCUeAHdxO0zVhsOu+5s8PyDHJkCVfULlOs3UaP7jycTJWgZrKZeWp3Rclp538/7u/cfQKBgQCT6zrLtP2wuuEpz/8DlAsZSRqRBambhErNHTrUjH3gsUiqvXpZV0V00Q+FbFUOJFRdxWsW/36Q2/6WRkXaYm6hLQgoDlSU5liWB7U5OW345oKJW8vrW53qgEkaZLGY65OjqRujji2mYF04mfjSX7w+rS0Luhpw94AwWYE5Xy+EZQKBgGI7nWbEs9Vac15YjIy1znDgYkl2SWgQEdCqq3Q/cQwLfDn709WQODC4G+DLaAX9xBbjRFtCdYn6sXcWcz2ItJ3SKvj0blRjHU3YrKnlkj5/YjO4/kM1dnm6fem7KEA2oW+CqYIquR+8c+/j+r9GtUzLdWVbEY+2WLRQSfxuwABD";
    static final String dataText = "YWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYQ==";
    static final String hashText = "KBZZeIjkoNOja4K4MxarMmgOuPAPjNO5BNaBJG0oWg4=";
    static final String signedText = "HWrCOErYaLErWi19E6IcjBaOg257j40JoY+kCBuXvQG9NdbliNEuMw8aESiK/jFsLmT6mo9Y73ruB/bk3esKxwh3bLCvR+VqnWd5Jpc+a2EVrUjzmBZYm9cO3tXn79a+Dt2zdHAusjsBhR/QfZMTLAGA4WF7Fv4rhH4xuqwfWF5cxfBV4WZSgCSEDcSdcnBQyjxlSVPYeStJEFy6s9lOgLbOY94um8MrdAqJZRMvaKSQaFyMlnCRw7RdkfdMUfzZKZFdDsYSzs91yVqXiRFTAKWocJm9mL/75xls8nTikbiRT0B+4uratib+yLtDvNFnCirIjLuNjb/jUOTO5mw4Ng==";
    static final String encryptedText = "MIIC0wYJKoZIhvcNAQcDoIICxDCCAsACAQIxggEwMIIBLAIBAoAUl+RKV4VHjL/zESoP0NCVxfMXhX8wDQYJKoZIhvcNAQEBBQAEggEABjrCr7sYr7KfPbb7QrxsHUMmqDvC6MD4e9k+z3zQ//OiDB0Ql8EkICchp5mWjGrZqhA0FWpeADh6FDXFUkWWRQ+RnXzs5WgHVYc4Z9TGWU5HDRWksdDQI5t5D/oeGjzkhVzDA3An+nWN+iA0ERLK2fdQr0PS21E6Bh1h6Ce/zXaHRj9z+swPuZN5gBuzjKXOq56JW1b1YgMr66GLKFnBa89Kr1x1LS6OANfbBIEqur36pVYcqpW4/YAOFvUBVFLZepZh0MfRoQrptelUIQGItneQJCUZYJe5hw3v10oe3axDIkSeu7trFMU8zvmAIpeYMLBaC555r52Jaf0K9QiBxDCCAYUGCSqGSIb3DQEHATAUBggqhkiG9w0DBwQI2iLN/gmvzdKAggFgFqUKfZXm4M2SuQ71p81Bv+7A7UWThok4QtG356YsAUPRF+InQCQAfalNogi2AQb3H7pBHssMCv1nHDc+f/eesquS9hTzDGAklwy/sIteRb9OKkzmyLNnWXlvw9QnuABAxD+/fRdqePFZMMN0564rpyw9/D7Ox0xyogIIkXxPJ2r5W+9UypRSVwkpzJSMnPZ+v7tCS34B2PkT4WVJpJsOpwRi9pv29LBW9byb/QDyM0XM2b32DNXGxvswLM4zZfBk4qYQeX+ee00sbqr8JoTggC1J0HhaTh5kPR06vOtHk7mLtMBWuayGqg0Z4/SNf55Laz6I9nmfoMo/m6SNiAJcCeo3Ul659MmAideuZdIoX/foreNAiog5KeAp+Ze2Vrcq5dlfxDPHJjH/zI86mI/1a+grUl68N1dGXvoEb28ct1DZi25SsLZFp4UQGLXIoUZotB56x933iu6OgmdAXbqd8w==";
    static final String decryptedText = "HWrCOErYaLErWi19E6IcjBaOg257j40JoY+kCBuXvQG9NdbliNEuMw8aESiK/jFsLmT6mo9Y73ruB/bk3esKxwh3bLCvR+VqnWd5Jpc+a2EVrUjzmBZYm9cO3tXn79a+Dt2zdHAusjsBhR/QfZMTLAGA4WF7Fv4rhH4xuqwfWF5cxfBV4WZSgCSEDcSdcnBQyjxlSVPYeStJEFy6s9lOgLbOY94um8MrdAqJZRMvaKSQaFyMlnCRw7RdkfdMUfzZKZFdDsYSzs91yVqXiRFTAKWocJm9mL/75xls8nTikbiRT0B+4uratib+yLtDvNFnCirIjLuNjb/jUOTO5mw4Ng==";

    public static RSAPrivateKey userPriKey;
    public static RSAPublicKey userPubKey;
    public static X509Certificate userCert;

    public static X509Cert UserCert;

    public static byte[] data;
    public static byte[] hash;
    public static byte[] sign;
    static {
        try {

            {
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(keyText));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                userPriKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            }
            {
                ByteArrayInputStream bIn = new ByteArrayInputStream(Base64.decode(certText));
                CertificateFactory fact = CertificateFactory.getInstance("X.509");

                userCert = (X509Certificate) fact.generateCertificate(bIn);
                userPubKey = (RSAPublicKey) userCert.getPublicKey();

                UserCert = new X509Cert(Certificate.getInstance(RSATestData.userCert.getEncoded()));

            }

            data = Base64.decode(dataText);
            hash = Base64.decode(hashText);
            sign = Base64.decode(signedText);

            boolean writeFile = false;
            if (writeFile) {
                FileOutputStream fos = new FileOutputStream("TestData/rsa/test256.dat");
                fos.write(data);
                fos.close();
                fos = new FileOutputStream("TestData/rsa/test256.sig");
                fos.write(sign);
                fos.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    {

    }

    public static void main(String[] args) {
        System.err.println(sign);
    }

}
