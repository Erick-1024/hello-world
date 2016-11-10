package test.cfca.sadk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.ecitic.cfca.sadk.util.ECITICBase64TestCase;
import test.ecitic.cfca.sadk.util.ECITICCertUtilTestCase;
import test.ecitic.cfca.sadk.util.ECITICEncryptUtilTestCase;
import test.ecitic.cfca.sadk.util.ECITICEnvelopeUtilDES3TestCase;
import test.ecitic.cfca.sadk.util.ECITICEnvelopeUtilRC4TestCase;
import test.ecitic.cfca.sadk.util.ECITICEnvelopeUtilSM4TestCase;
import test.ecitic.cfca.sadk.util.ECITICKeyUtilTestCase;
import test.ecitic.cfca.sadk.util.ECITICP10RequestTestCase;
import test.ecitic.cfca.sadk.util.ECITICSignatureTestCase;
import test.ecitic.cfca.sadk.util.ECITICSignatureTestCaseRSAmd5;
import test.ecitic.cfca.sadk.util.ECITICSignatureTestCaseRSAsha1;
import test.ecitic.cfca.sadk.util.ECITICSignatureTestCaseRSAsha224;
import test.ecitic.cfca.sadk.util.ECITICSignatureTestCaseRSAsha256;
import test.ecitic.cfca.sadk.util.ECITICSignatureTestCaseSM2;
import test.ecitic.cfca.sadk.x509.certificate.ECITICX509CRLGeneratorTestCase;
import test.ecitic.cfca.sadk.x509.certificate.ECITICX509CRLTestCase;
import test.ecitic.cfca.sadk.x509.certificate.ECITICX509CertGeneratorTestCase;
import test.ecitic.cfca.sadk.x509.certificate.ECITICX509CertTestCase;
import test.ecitic.cfca.sadk.x509.certificate.ECITICX509CertVerifierTestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses({ //
ECITICBase64TestCase.class, //
        ECITICCertUtilTestCase.class, //
        ECITICEncryptUtilTestCase.class,//
        ECITICEnvelopeUtilDES3TestCase.class,//
        ECITICEnvelopeUtilRC4TestCase.class,//
        ECITICEnvelopeUtilSM4TestCase.class,//

        ECITICKeyUtilTestCase.class, //
        ECITICP10RequestTestCase.class,//
        ECITICSignatureTestCase.class,//
        ECITICSignatureTestCaseRSAmd5.class,//
        ECITICSignatureTestCaseRSAsha1.class,//
        ECITICSignatureTestCaseRSAsha224.class,//
        ECITICSignatureTestCaseRSAsha256.class,//
        ECITICSignatureTestCaseSM2.class,//

        ECITICX509CertGeneratorTestCase.class, //
        ECITICX509CertTestCase.class, //
        ECITICX509CertVerifierTestCase.class, //
        ECITICX509CRLGeneratorTestCase.class, //
        ECITICX509CRLTestCase.class, //
})
public class AllTestsECITIC {

}