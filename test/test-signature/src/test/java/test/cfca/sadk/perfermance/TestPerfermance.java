package test.cfca.sadk.perfermance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

import cfca.sadk.lib.crypto.JCrypto;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
public final class TestPerfermance {
    static final int keyType_all = 7;
    static final int keyType_sm2 = 1;
    static final int keyType_rsa1024 = 2;
    static final int keyType_rsa2048 = 4;

    static final int encTypeAll = 7;
    static final int encTypeP1 = 1;
    static final int encTypeP7Attach = 2;
    static final int encTypeP7Detach = 4;

    public static void main(String[] args) throws Exception {
        boolean display = false;
        for (String arg : args) {
            if (arg.equals("-help")) {
                display = true;
                break;
            }

        }
        if (display) {
            final Formatter buffer = new Formatter();
            buffer.format("command: <keyType> <sigType> <numThreads> <dataLength> <seconds> <milliseconds> {1-15} [-jni]");
            buffer.format("\n");
            buffer.format("\nkeyType={%-10s| %-10s| %-10s| %-10s}", "all", "sm2", "rsa1024", "rsa2048");
            buffer.format("\nencType={%-10s| %-10s| %-10s| %-10s}", "all", "p1", "p7attach", "p7detach");
            buffer.format("\nrunType={%-10s| %-10s| %-10s| %-10s}", "1=signed", "2=encrypt", "4=decrypt", "8=verify");
            buffer.format("\nrunType={%-10s+ %-10s+ %-10s+ %-10s}=15", "1=signed", "2=encrypt", "4=decrypt", "8=verify");
            System.out.println(buffer);
        }

        int keyType = keyType_all;
        int encType = encTypeAll;

        List<String> params = new ArrayList<String>();
        boolean report = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-jni")) {
                Constants.cryptoType = JCrypto.JNI_LIB;
                continue;
            }
            if (args[i].startsWith("-hard")) {
                Constants.cryptoType = JCrypto.JHARD_LIB;
                continue;
            }
            if (args[i].startsWith("-report")) {
                report = true;
                continue;
            }
            params.add(args[i].toLowerCase());
        }
        args = params.toArray(new String[0]);

        if (args.length >= 2) {
            String keyTypeText = args[0];
            if ("sm2".equals(keyTypeText)) {
                keyType = keyType_sm2;
            } else if ("rsa1024".equals(keyTypeText)) {
                keyType = keyType_rsa1024;
            } else if ("rsa2048".equals(keyTypeText)) {
                keyType = keyType_rsa2048;
            } else if ("all".equals(keyTypeText)) {
                keyType = keyType_all;
            } else {
                keyType = keyType_all;
            }
            String encTypeText = args[1];
            if ("all".equals(encTypeText)) {
                encType = encTypeAll;
            } else if ("p1".equals(encTypeText)) {
                encType = encTypeP1;
            } else if ("p7attach".equals(encTypeText)) {
                encType = encTypeP7Attach;
            } else if ("p7detach".equals(encTypeText)) {
                encType = encTypeP7Detach;
            } else {
                encType = encTypeAll;
            }

            args = Arrays.copyOfRange(args, 2, args.length);
        }

        if (display) {
            System.out.println();
            System.out.println();
            System.out.println(String.format("TestRunning:  keyType=%-4d,  encType=%-4d, sessionType=%10s  reportType=%10s", keyType, encType,
                    Constants.cryptoType, report));
        }

        runTests(report, keyType, encType, args);

    }

    static final void runTests(final boolean report, final int keyType, final int encType, final String[] args) throws Exception {

        try {
            if ((encType & encTypeP1) == encTypeP1 && (keyType & keyType_sm2) == keyType_sm2) {
                System.out.println(TestFrame.runTest(new TestSMxP1(), report, args));
            }
            if ((encType & encTypeP1) == encTypeP1 && (keyType & keyType_rsa1024) == keyType_rsa1024) {
                System.out.println(TestFrame.runTest(new TestRSA1024P1(), report, args));
            }
            if ((encType & encTypeP1) == encTypeP1 && (keyType & keyType_rsa2048) == keyType_rsa2048) {
                System.out.println(TestFrame.runTest(new TestRSA2048P1(), report, args));
            }
            if ((encType & encTypeP7Detach) == encTypeP7Detach && (keyType & keyType_sm2) == keyType_sm2) {
                System.out.println(TestFrame.runTest(new TestSMxP7Detach(), report, args));
            }
            if ((encType & encTypeP7Detach) == encTypeP7Detach && (keyType & keyType_rsa1024) == keyType_rsa1024) {
                System.out.println(TestFrame.runTest(new TestRSA1024P7Detach(), report, args));
            }
            if ((encType & encTypeP7Detach) == encTypeP7Detach && (keyType & keyType_rsa2048) == keyType_rsa2048) {
                System.out.println(TestFrame.runTest(new TestRSA2048P7Detach(), report, args));
            }
            if ((encType & encTypeP7Attach) == encTypeP7Attach && (keyType & keyType_sm2) == keyType_sm2) {
                System.out.println(TestFrame.runTest(new TestSMxP7Attach(), report, args));
            }
            if ((encType & encTypeP7Attach) == encTypeP7Attach && (keyType & keyType_rsa1024) == keyType_rsa1024) {
                System.out.println(TestFrame.runTest(new TestRSA1024P7Attach(), report, args));
            }
            if ((encType & encTypeP7Attach) == encTypeP7Attach && (keyType & keyType_rsa2048) == keyType_rsa2048) {
                System.out.println(TestFrame.runTest(new TestRSA2048P7Attach(), report, args));
            }
        } finally {

        }

    }
}
