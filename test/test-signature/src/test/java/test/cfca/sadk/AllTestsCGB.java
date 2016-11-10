package test.cfca.sadk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.cgb.cfca.sadk.CGBBASE64ToolkitTestCase;
import test.cgb.cfca.sadk.CGBCastleTestCase;
import test.cgb.cfca.sadk.CGBSM2ToolkitTestCase;
import test.cgb.cfca.sadk.CGBSM3ToolkitTestCase;
import test.cgb.cfca.sadk.CGBSM4ToolkitTestCase;
import test.cgb.cfca.sadk.CGBX509CertValidatorTestCase;
import test.cgb.cfca.x509.certificate.CGBX509CertTestCase;
import test.cgb.cfca.x509.certificate.CGBX509CertVerifierChainTestCase;
import test.cgb.com.cfca.compatibility.CGBCompatibilityTestCase;

@RunWith(Suite.class)
@Suite.SuiteClasses({ //
CGBBASE64ToolkitTestCase.class, //
        CGBCastleTestCase.class, //
        CGBSM2ToolkitTestCase.class,//
        CGBSM3ToolkitTestCase.class,//
        CGBSM4ToolkitTestCase.class,//
        CGBX509CertValidatorTestCase.class, //
        CGBX509CertTestCase.class, //
        CGBX509CertVerifierChainTestCase.class, //
        CGBCompatibilityTestCase.class, //
        CGBCastleTestCase.class, //
        CGBX509CertValidatorTestCase.class, //
})
public class AllTestsCGB {

}