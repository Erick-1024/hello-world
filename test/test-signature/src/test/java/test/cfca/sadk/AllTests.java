package test.cfca.sadk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.cfca.sadk.algorithm.sm2.TestCaseSM2HashZValue;
import test.cfca.sadk.asn1.parser.ASN1ParserTestCase;
import test.cfca.sadk.asn1.pkcs.PKCS12TestCase;
import test.cfca.sadk.asn1.pkcs.PKCS12_SM2TestCase;
import test.cfca.sadk.interoperability.CMBCInteroperabilityTestCase;
import test.cfca.sadk.timestamp.TimeStampReqTestCase;
import test.cfca.sadk.timestamp.TimeStampRspTestCase;
import test.cfca.sadk.util.BERTestCase;
import test.cfca.sadk.util.Base64TestCase;
import test.cfca.sadk.util.CertUtilTestCase;
import test.cfca.sadk.util.EncryptUtilTestCase;
import test.cfca.sadk.util.EnvelopeUtilDES3TestCase;
import test.cfca.sadk.util.EnvelopeUtilRC4TestCase;
import test.cfca.sadk.util.EnvelopeUtilSM4TestCase;
import test.cfca.sadk.util.HashUtilTestCase;
import test.cfca.sadk.util.KeyUtilTestCase;
import test.cfca.sadk.util.P10RequestTestCase;
import test.cfca.sadk.util.P12FileKitTestCase;
import test.cfca.sadk.util.SM2WithoutZVerifyTestCase;
import test.cfca.sadk.util.SignatureTestCaseRSAmd5;
import test.cfca.sadk.util.SignatureTestCaseRSAmd5_3107;
import test.cfca.sadk.util.SignatureTestCaseRSAsha1;
import test.cfca.sadk.util.SignatureTestCaseRSAsha1_3107;
import test.cfca.sadk.util.SignatureTestCaseRSAsha256;
import test.cfca.sadk.util.SignatureTestCaseRSAsha256_3107;
import test.cfca.sadk.util.SignatureTestCaseRSAsha512;
import test.cfca.sadk.util.SignatureTestCaseRSAsha512_3107;
import test.cfca.sadk.util.SignatureTestCaseSM2;
import test.cfca.sadk.util.cipher.lib.BCSoftLibTestCase;
import test.cfca.sadk.util.cipher.lib.JNISoftLibTestCase;
import test.cfca.sadk.x509.certificate.CFCAStyleTestCase;
import test.cfca.sadk.x509.certificate.X509CRLGeneratorTestCase;
import test.cfca.sadk.x509.certificate.X509CRLTestCase;
import test.cfca.sadk.x509.certificate.X509CertGeneratorTestCase;
import test.cfca.sadk.x509.certificate.X509CertTestCase;
import test.cfca.sadk.x509.certificate.X509CertVerifierTestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses({ //
TestCaseSM2HashZValue.class, //
        CMBCInteroperabilityTestCase.class, //
        // CommandTestCase.class,//From Test Teams
        TimeStampReqTestCase.class,//
        TimeStampRspTestCase.class,//

        Base64TestCase.class,//
        CertUtilTestCase.class, //
        EncryptUtilTestCase.class, //
        EnvelopeUtilDES3TestCase.class, //
        EnvelopeUtilRC4TestCase.class, //
        EnvelopeUtilSM4TestCase.class, //

        HashUtilTestCase.class, //
        KeyUtilTestCase.class, //
        P10RequestTestCase.class, //
        SignatureTestCaseRSAmd5.class, //
        SignatureTestCaseRSAsha1.class, //
        SignatureTestCaseRSAsha512.class, //
        SignatureTestCaseRSAsha256.class, //
        SignatureTestCaseRSAsha512.class, //
        SignatureTestCaseSM2.class, //
        SM2WithoutZVerifyTestCase.class, //
        BCSoftLibTestCase.class, //
        JNISoftLibTestCase.class, //

        CFCAStyleTestCase.class, //
        X509CertGeneratorTestCase.class, //
        X509CertTestCase.class, //
        X509CertVerifierTestCase.class, //
        X509CRLGeneratorTestCase.class, //
        X509CRLTestCase.class, //

        SignatureTestCaseRSAmd5_3107.class, //
        SignatureTestCaseRSAsha1_3107.class, //
        SignatureTestCaseRSAsha512_3107.class, //
        SignatureTestCaseRSAsha256_3107.class, //
        SignatureTestCaseRSAsha512_3107.class, //

        PKCS12TestCase.class, //
        PKCS12_SM2TestCase.class, //
        P12FileKitTestCase.class,//
        ASN1ParserTestCase.class,//

        BERTestCase.class,//
})
public class AllTests {

}
