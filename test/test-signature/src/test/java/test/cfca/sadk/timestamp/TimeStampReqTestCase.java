package test.cfca.sadk.timestamp;

import java.io.IOException;

import junit.framework.TestCase;
import cfca.sadk.algorithm.common.GMObjectIdentifiers;
import cfca.sadk.algorithm.common.PKCSObjectIdentifiers;
import cfca.sadk.org.bouncycastle.asn1.ASN1Boolean;
import cfca.sadk.org.bouncycastle.asn1.ASN1Integer;
import cfca.sadk.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import cfca.sadk.org.bouncycastle.asn1.tsp.MessageImprint;
import cfca.sadk.org.bouncycastle.asn1.tsp.TimeStampReq;
import cfca.sadk.org.bouncycastle.asn1.util.ASN1Dump;
import cfca.sadk.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import cfca.sadk.org.bouncycastle.asn1.x509.Extensions;
import cfca.sadk.org.bouncycastle.tsp.TimeStampRequest;
import cfca.sadk.org.bouncycastle.tsp.TimeStampRequestGenerator;
import cfca.sadk.system.FileHelper;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class TimeStampReqTestCase extends TestCase {

    final String path = "TestData/timestamp/rfc3161/";

    final byte[] data = "TEST+测试".getBytes();

    final String[] reqFiles = new String[] { "timestamp-hash-sm3xxx-req-binary.msg",//
            "timestamp-hash-sha1xx-std-req-binary.msg",//
            "timestamp-hash-sha1xx-ext-req-binary.msg",//
            "timestamp-hash-sha256-std-req-binary.msg",//
            "timestamp-hash-sha256-ext-req-binary.msg",//
            "timestamp-hash-sha512-std-req-binary.msg",//
            "timestamp-hash-sha512-ext-req-binary.msg",//

    };

    final String[] hashValues = new String[] { "10809615345853c7455f0d5394dce49786ab220402057b71c05923f386db60e5",//
            "c1ff3944436d61a23cbe0e1eb1869e34bbf1ff82",//
            "c1ff3944436d61a23cbe0e1eb1869e34bbf1ff82",//
            "3a3a35c935a1965413c43a75d06a336424a6e5db96d287ff87a5cd9c06da621f",//
            "3a3a35c935a1965413c43a75d06a336424a6e5db96d287ff87a5cd9c06da621f",//
            "63cd795371b89db96c6e8f04ab72d528d4767092a72dea1ed0d34eb32902a21964061ad62b0df30bdeee77cb8f70b0c1198eda81877b606a316b202f48c23f12",//
            "63cd795371b89db96c6e8f04ab72d528d4767092a72dea1ed0d34eb32902a21964061ad62b0df30bdeee77cb8f70b0c1198eda81877b606a316b202f48c23f12",//

    };

    final ASN1ObjectIdentifier[] hashAlgs = new ASN1ObjectIdentifier[] { GMObjectIdentifiers.sm3,//
            PKCSObjectIdentifiers.sha1,//
            PKCSObjectIdentifiers.sha1,//
            PKCSObjectIdentifiers.sha256,//
            PKCSObjectIdentifiers.sha256,//
            PKCSObjectIdentifiers.sha512,//
            PKCSObjectIdentifiers.sha512,//
    };

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testTimeStampReq() throws IOException {

        for (int i = 0; i < reqFiles.length; i++) {
            byte[] reqBytes = readBytesFrom(reqFiles[i]);
            byte[] hash = HexBin.decode(hashValues[i]);

            ASN1ObjectIdentifier hashAlgorithm = hashAlgs[i];

            TimeStampReq req = TimeStampReq.getInstance(reqBytes);
            MessageImprint messageImprint = req.getMessageImprint();
            byte[] hashValue = messageImprint.getHashedMessage();

            // System.err.println(HexBin.encode(hashValue).toLowerCase());
            assertTrue("testTimeStampReq", req.getVersion().getPositiveValue().intValue() == 1);
            assertTrue("testTimeStampReq", hashAlgorithm.equals(messageImprint.getHashAlgorithm().getAlgorithm()));
            assertTrue("testTimeStampReq", java.util.Arrays.equals(hash, hashValue));

            TimeStampRequestGenerator tg = new TimeStampRequestGenerator();
            tg.setCertReq(true);
            TimeStampRequest request = tg.generate(hashAlgorithm, hashValue);
            byte[] newBytes = request.getEncoded();

            assertTrue("testTimeStampReq", java.util.Arrays.equals(newBytes, reqBytes));

        }

    }

    public void testTimeStampReqEncoded() throws IOException {
        byte[] hash = HexBin.decode("c1ff3944436d61a23cbe0e1eb1869e34bbf1ff82");
        AlgorithmIdentifier hashAlgorithm = new AlgorithmIdentifier(GMObjectIdentifiers.sm3);
        MessageImprint messageImprint = new MessageImprint(hashAlgorithm, hash);

        ASN1ObjectIdentifier tsaPolicy = new ASN1ObjectIdentifier("1.2.12036.7.1");
        ASN1Integer nonce = new ASN1Integer(100);
        ASN1Boolean certReq = ASN1Boolean.TRUE;
        Extensions extensions = null;
        TimeStampReq req = new TimeStampReq(messageImprint, tsaPolicy, nonce, certReq, extensions);

        TimeStampReq decodedReq = TimeStampReq.getInstance(req.getEncoded());

        System.err.println(ASN1Dump.dumpAsString(decodedReq));

        assertTrue("testTimeStampReqEncoded", java.util.Arrays.equals(req.getEncoded(), decodedReq.getEncoded()));
        assertTrue("testTimeStampReqEncoded", decodedReq.equals(req));
        assertTrue("testTimeStampReqEncoded", 1 == decodedReq.getVersion().getPositiveValue().intValue());
        assertTrue("testTimeStampReqEncoded", hashAlgorithm.equals(decodedReq.getMessageImprint().getHashAlgorithm()));
        assertTrue("testTimeStampReqEncoded", java.util.Arrays.equals(hash, decodedReq.getMessageImprint().getHashedMessage()));
        assertTrue("testTimeStampReqEncoded", tsaPolicy.equals(decodedReq.getReqPolicy()));
        assertTrue("testTimeStampReqEncoded", nonce.equals(decodedReq.getNonce()));
        assertTrue("testTimeStampReqEncoded", certReq.equals(decodedReq.getCertReq()));
        assertTrue("testTimeStampReqEncoded", null == decodedReq.getExtensions());

    }

    final byte[] readBytesFrom(String filename) throws IOException {
        return FileHelper.read(path + filename);
    }
}
