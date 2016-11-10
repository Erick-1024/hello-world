package test.cgb.cfca.sadk.perfermance;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import sun.security.util.BitArray;
import test.cgb.cfca.sadk.CGBTestData;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.cgb.toolkit.Castle;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
abstract class CGBTestFrame {
    static boolean jniFlag = false;
    static final String charsetName = "UTF8";
    long strTime = System.currentTimeMillis();
    AtomicInteger total = new AtomicInteger(0);
    long seconds = 5;
    int numThread = 16;
    int dataLength = 2000;
    long runTime;

    boolean running = true;
    Castle castle = null;
    String data = "12314";

    final String sm2Path = CGBTestData.TESTDATA_DIR +"sm2/cmbc.sm2";
    final String sm2Pass = "123123";

    CGBTestFrame() {

    }

    abstract void init() throws Exception;

    abstract String signedMessage(String message) throws UnsupportedEncodingException, PKIException;

    abstract String envelopedMessage(String signedData) throws UnsupportedEncodingException, PKIException;

    abstract String openEnvelopedMessage(String encryptedData) throws UnsupportedEncodingException, PKIException;

    abstract boolean verifiedMessage(String message, String signature) throws UnsupportedEncodingException, PKIException;

    abstract String getTestName();

    public void run(final int numThread, final boolean detail, String[] args) {

        int num = 0;
        long xTime = System.currentTimeMillis();
        long endTime = 0;
        String signedText = null;
        String encryptedText = null;
        String decryptedText = null;
        boolean result = false;

        try {
            signedText = signedMessage(data);
            encryptedText = envelopedMessage(signedText);
            decryptedText = openEnvelopedMessage(encryptedText);
            result = verifiedMessage(data, decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int option = 0x0f;
        if (args.length > 3) {
            option = Integer.parseInt(args[3]);
        }
        boolean signedFlag = (option & 0x01) == 0x01;
        boolean encryptedFlag = (option & 0x02) == 0x02;
        boolean decryptedFlag = (option & 0x04) == 0x04;
        boolean verifyFlag = (option & 0x08) == 0x08;

        BitArray sedv = new BitArray(new boolean[] { signedFlag, encryptedFlag, decryptedFlag, verifyFlag });

        while (running) {
            try {
                xTime = System.currentTimeMillis();

                if (signedFlag) {
                    // signed
                    signedText = signedMessage(data);
                }
                if (encryptedFlag) {
                    // encrypted
                    encryptedText = envelopedMessage(signedText);
                }
                if (decryptedFlag) {
                    // decrypted
                    decryptedText = openEnvelopedMessage(encryptedText);
                }
                if (verifyFlag) {
                    // verified
                    result = verifiedMessage(data, decryptedText);
                }
                // busy

                endTime = System.currentTimeMillis();
                num = total.getAndIncrement();

                if (detail && num % 100 == 0) {
                    System.err.println(String.format(
                            "%1$tY-%1$tm-%1$te %0$tT %-16s, testBits=%s, tps=%-2.4f, thisTime=%5.3f, dataLength=%s thread=%s:%s, total=%d result=%s",
                            Calendar.getInstance(), getTestName(), sedv, num * 1000.0 / (endTime - strTime), (endTime - xTime) / 1000.0, data.length(),
                            numThread, Thread.currentThread().getName(), num, result));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String toString() {

        final int num = total.get();
        final double tps = num * 1.0 / runTime;

        return String.format("TestFrame[%-16s, tps=%-8.2f, numThread=%d, dataLength=%d, runTime=%d, numTotal=%-16s]", getTestName(), tps, numThread,
                dataLength, runTime, total);

    }

    public static String runTest(final CGBTestFrame commands, final boolean report, final String[] args) throws Exception {
        final int numThread = args.length > 0 ? Integer.parseInt(args[0]) : 16;
        final int dataLength = args.length > 1 ? Integer.parseInt(args[1]) : 2000;

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < dataLength; i++) {
            buffer.append("a");
        }

        commands.init();
        commands.data = buffer.toString();
        commands.strTime = System.currentTimeMillis();
        for (int i = 0; i < numThread; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    commands.run(numThread, report, args);
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

        final long seconds = (args.length > 2 ? Integer.parseInt(args[2]) : 15);
        final long milliseconds = 1000 * seconds;

        Thread.sleep(milliseconds);
        commands.running = false;
        commands.numThread = numThread;
        commands.dataLength = dataLength;
        commands.seconds = seconds;
        commands.runTime = (System.currentTimeMillis() - commands.strTime) / 1000;

        return commands.toString();

    }
}
