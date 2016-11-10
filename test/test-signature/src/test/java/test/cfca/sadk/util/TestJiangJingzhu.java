package test.cfca.sadk.util;

import cfca.sadk.algorithm.sm2.SM2PrivateKey;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.lib.crypto.bcsoft.BCSoftLib;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.EnvelopeUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.x509.certificate.X509Cert;

public class TestJiangJingzhu {

    public static void main(String[] args) throws Exception {
        String base64SM2File = "MIICLgIBATBHBgoqgRzPVQYBBAIBBgcqgRzPVQFoBDBmi2I0B0RzwZyZBY/wLzmIBDMdSUoPZpkW"
                + "wTEn2rCuINp6OR0oKxa6+pGDtjwFdcgwggHeBgoqgRzPVQYBBAIBBIIBzjCCAcowggFuoAMCAQIC"
                + "BRAAAABDMAwGCCqBHM9VAYN1BQAwIzELMAkGA1UEBhMCQ04xFDASBgNVBAoMC0NGQ0EgT0NBODg4"
                + "MB4XDTEzMDUxNjAzNDY1NloXDTE0MDMxMjAzNDY1NlowJTELMAkGA1UEBhMCQ04xFjAUBgNVBAMM"
                + "DXNtMmRvdWJsZWNlcnQwWTATBgcqhkjOPQIBBggqgRzPVQGCLQNCAASJd/fd5HhDC6sHaFisyxPz"
                + "/6nZwOu2N7xM0iM04miNdqYnl/2HVgXNDjqU0++6MTmUXJTljnPJ4akAhapGhGpio4GKMIGHMB8G"
                + "A1UdIwQYMBaAFACQCuvvo4oRDRbCWCXtDieQ7RgnMDgGA1UdHwQxMC8wLaAroCmGJ2h0dHA6Ly8x"
                + "OTIuMTY4LjEyMC4xMjcvY3JsL1NNMi9jcmwxLmNybDALBgNVHQ8EBAMCBDAwHQYDVR0OBBYEFJu5"
                + "LVzOme1SdrKWMBrgFTb52Lt7MAwGCCqBHM9VAYN1BQADSAAwRQIgK/XTEOXLlws6vrPBBpfg55Ka"
                + "2EXVsnnbIMqLEdttp4oCIQDnooBfDh/ka0yTcW83phYskvLKeHu+XMRZx4oa+irxvA==";

        byte[] sm2FileData = base64SM2File.getBytes();
        String sm2FilePwd = "111111";
        SM2PrivateKey privateKey = KeyUtil.getPrivateKeyFromSM2(sm2FileData, sm2FilePwd);

        X509Cert cert = CertUtil.getCertFromSM2(sm2FileData);

        Session session = new BCSoftLib();

        final byte[] base64EncryptData = FileHelper.read("TestData/jiangjingzhu/testText.enc");
        byte[] decryptData = EnvelopeUtil.openEvelopedMessage(base64EncryptData, privateKey, cert, session);

        System.err.println("TestResult:" + new String(decryptData) + ":Ending");

        String envelopedFilePath = "TestData/jiangjingzhu/testFile.enc";
        EnvelopeUtil.openEnvelopedFile(envelopedFilePath, envelopedFilePath + ".dec", privateKey, cert, session);

    }

}
