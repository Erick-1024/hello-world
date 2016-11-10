package test.cfca.sadk.timestamp;

import java.io.IOException;

import junit.framework.TestCase;
import cfca.sadk.algorithm.common.GMObjectIdentifiers;
import cfca.sadk.algorithm.common.PKCSObjectIdentifiers;
import cfca.sadk.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import cfca.sadk.org.bouncycastle.asn1.cmp.PKIStatus;
import cfca.sadk.org.bouncycastle.asn1.cmp.PKIStatusInfo;
import cfca.sadk.org.bouncycastle.asn1.cms.ContentInfo;
import cfca.sadk.org.bouncycastle.asn1.cms.SignedData;
import cfca.sadk.org.bouncycastle.asn1.cms.SignerInfo;
import cfca.sadk.org.bouncycastle.asn1.tsp.TimeStampResp;
import cfca.sadk.system.FileHelper;

/**
 * @Author qazhang
 * @Description 当前测试代码不完备 2015-06-11
 * @CodeReviewer
 * 
 */
public class TimeStampRspTestCase extends TestCase {

    final String path = "TestData/timestamp/rfc3161/";

    final byte[] data = "TEST+测试".getBytes();

    final String[] reqFiles = new String[] { "timestamp-hash-sm3xxx-rsp-binary.msg",//
            "timestamp-hash-sha1xx-std-rsp-binary.msg",//
            "timestamp-hash-sha1xx-ext-rsp-binary.msg",//
            "timestamp-hash-sha256-std-rsp-binary.msg",//
            "timestamp-hash-sha256-ext-rsp-binary.msg",//
            "timestamp-hash-sha512-std-rsp-binary.msg",//
            "timestamp-hash-sha512-ext-rsp-binary.msg",//

    };

    final ASN1ObjectIdentifier[] algorithms = new ASN1ObjectIdentifier[] { GMObjectIdentifiers.sm2SignedData,//
            PKCSObjectIdentifiers.signedData,//
            PKCSObjectIdentifiers.signedData,//
            PKCSObjectIdentifiers.signedData,//
            PKCSObjectIdentifiers.signedData,//
            PKCSObjectIdentifiers.signedData,//
            PKCSObjectIdentifiers.signedData,//
    };

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testTimeStampRsp() throws IOException {

        for (int i = 0; i < reqFiles.length; i++) {
            byte[] reqBytes = readBytesFrom(reqFiles[i]);

            TimeStampResp rsp = TimeStampResp.getInstance(reqBytes);
            PKIStatusInfo pkiStatusInfo = rsp.getStatus();
            assertTrue("testTimeStampRsp", pkiStatusInfo != null);
            assertTrue("testTimeStampRsp", PKIStatus.GRANTED == pkiStatusInfo.getStatus().intValue());
            ContentInfo contentInfo = rsp.getTimeStampToken();

            assertTrue("testTimeStampRsp", PKIStatus.GRANTED == pkiStatusInfo.getStatus().intValue());

            assertTrue("testTimeStampRsp", contentInfo != null);
            assertTrue("testTimeStampRsp", algorithms[i].equals(contentInfo.getContentType()));

            assertTrue("testTimeStampRsp", contentInfo.getContent() != null);

            SignedData signedData = SignedData.getInstance(contentInfo.getContent());

            assertTrue("testTimeStampRsp", signedData.getVersion() != null);
            assertTrue("testTimeStampRsp", signedData.getVersion().getPositiveValue().intValue() == 3);

            assertTrue("testTimeStampRsp", signedData.getSignerInfos() != null);
            assertTrue("testTimeStampRsp", signedData.getSignerInfos().size() == 1);

            assertTrue("testTimeStampRsp", signedData.getCertificates() != null);
            assertTrue("testTimeStampRsp", signedData.getCertificates().size() == 1);

            assertTrue("testTimeStampRsp", signedData.getCRLs() == null);

            SignerInfo signerInfo = SignerInfo.getInstance(signedData.getSignerInfos().getObjectAt(0));

            assertTrue("testTimeStampRsp", signerInfo.getVersion().getPositiveValue().intValue() == 1);

            TimeStampResp decodedRsp = TimeStampResp.getInstance(rsp.getEncoded());

            assertTrue("testTimeStampRsp", decodedRsp.equals(rsp));
            assertTrue("testTimeStampRsp", java.util.Arrays.equals(decodedRsp.getEncoded(), rsp.getEncoded()));

        }

    }

    final byte[] readBytesFrom(String filename) throws IOException {
        return FileHelper.read(path + filename);
    }

}
