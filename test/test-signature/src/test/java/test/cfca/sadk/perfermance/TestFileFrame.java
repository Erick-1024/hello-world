package test.cfca.sadk.perfermance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.util.Calendar;
import java.util.Formatter;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import sun.security.util.BitArray;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.x509.certificate.X509Cert;

/**
 * @Author qazhang
 * @Description
 * @CodeReviewer
 * 
 */
abstract class TestFileFrame {

    static final Logger logger = Logger.getLogger(TestFileFrame.class);
    static final String charsetName = "UTF8";

    AtomicLong numTotal = new AtomicLong(0);
    long strTime = System.currentTimeMillis();
    long seconds = 15;
    int numThread = 16;
    int dataLength = 2000;
    long runTime;

    Session session = null;
    String sourceFile = "TestData/files/test.dat";

    PrivateKey priKey = null;
    X509Cert cert = null;
    X509Cert[] certs = null;
    boolean running = true;

    TestFileFrame() {
        try {
            JCrypto.getInstance().initialize(Constants.cryptoType, null);
            session = JCrypto.getInstance().openSession(Constants.cryptoType);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    abstract void init() throws Exception;

    abstract String signedFile(String sourceFile, String id) throws UnsupportedEncodingException, PKIException;

    abstract String envelopedFile(String sourceFile, String id) throws UnsupportedEncodingException, PKIException;

    abstract String openEnvelopedFile(String envelopedFile, String id) throws UnsupportedEncodingException, PKIException;

    abstract boolean verifiedFile(String sourceFile, String signedFile, String id) throws UnsupportedEncodingException, PKIException;

    abstract String getTestName();

    boolean[] testFlags = null;

    String signedFile = null;
    String encryptedFile = null;
    String decryptedFile = null;

    boolean testResult = false;

    String cpuInfo = "windows";

    final void initTestData(final int option) {
        final boolean sFlag = (option & 0x01) == 0x01;// sign
        final boolean eFlag = (option & 0x02) == 0x02;// encrypt
        final boolean dFlag = (option & 0x04) == 0x04;// decrypt
        final boolean vFlag = (option & 0x08) == 0x08;// verify

        this.testFlags = new boolean[] { sFlag, eFlag, dFlag, vFlag };

        String id = Long.toString(Thread.currentThread().getId());

        try {
            signedFile = signedFile(sourceFile, id);
            encryptedFile = envelopedFile(signedFile, id);
            decryptedFile = openEnvelopedFile(encryptedFile, id);
            testResult = verifiedFile(sourceFile, decryptedFile, id);

            if (!testResult) {
                System.err.println("testResult: " + testResult);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    final boolean doneOperations(final String id) {
        try {
            String outFile = null;
            if (testFlags[0]) {
                outFile = signedFile(sourceFile, id);
            }

            if (testFlags[1]) {
                outFile = envelopedFile(outFile, id);
            }

            if (testFlags[2]) {
                outFile = openEnvelopedFile(outFile, id);
            }

            if (testFlags[3]) {
                boolean verifyResult = verifiedFile(sourceFile, outFile, id);
                if (!verifyResult) {
                    System.err.println("verifyResult: " + verifyResult);
                }
            }

            numTotal.getAndIncrement();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String toString() {
        return toString(runTime, numTotal);

    }

    final String toString(final long milliseconds, final AtomicLong atomicLong) {

        final long num = atomicLong.get();

        Formatter formatter = new Formatter();
        try {
            formatter.format("%1$tY-%1$tm-%1$te %0$tT", Calendar.getInstance());
            formatter.format(",%-16s", getTestName());
            formatter.format(",testBits=%s", new BitArray(testFlags));
            formatter.format(",threads=%d", numThread);
            formatter.format(",dataLength=%d", dataLength);
            formatter.format(",tps=%-8.4f", (num * 1000.0 / milliseconds));
            formatter.format(",seconds=%5.3f", milliseconds / 1000.0);
            formatter.format(",total=%-16s", num);
            formatter.format(",testResult=%s", testResult);
            formatter.format(",cpu=%s", cpuInfo);

            return formatter.toString();

        } finally {
            formatter.close();
        }

    }

    public static String runTest(final TestFileFrame commands, final boolean reportFlag, final String[] args) throws Exception {
        final int numThread = args.length > 0 ? Integer.parseInt(args[0]) : 16;
        final int dataLength = args.length > 1 ? Integer.parseInt(args[1]) : 2000;
        final int seconds = args.length > 2 ? Integer.parseInt(args[2]) : 9;
        final int interval = args.length > 4 ? Integer.parseInt(args[3]) : 1000;
        final int option = args.length > 3 ? Integer.parseInt(args[4]) : 0x0f;

        DOMConfigurator.configure("./config/log4j.xml");

        logger.debug("TestFrame: " + commands.getTestName());

        commands.init();
        commands.initTestData(option);

        final AtomicLong numPeriod = new AtomicLong(0);

        if (reportFlag) {
            Thread cpuThread = new CPUThread(commands);
            cpuThread.setDaemon(true);
            cpuThread.start();

            Thread resportThread = new ReportThread(commands, numPeriod, interval);
            resportThread.setDaemon(true);
            resportThread.start();
        }

        commands.strTime = System.currentTimeMillis();
        for (int i = 0; i < numThread; i++) {
            Thread thread = new OperationsThread(commands, numPeriod);
            thread.setDaemon(true);
            thread.start();
        }
        Thread.sleep(seconds * 1000);

        commands.running = false;
        commands.numThread = numThread;
        commands.dataLength = dataLength;
        commands.seconds = seconds;
        commands.runTime = (System.currentTimeMillis() - commands.strTime);
        System.out.println("tps:" + (commands.numTotal.get() * 1000.0 / commands.runTime));
        return commands.toString();

    }

    static final class CPUThread extends Thread {

        final TestFileFrame commands;

        public CPUThread(TestFileFrame commands) {
            super();
            this.commands = commands;
        }

        public void run() {

            BufferedReader br = null;
            Process cmd = null;
            try {
                boolean linuxFlag = System.getProperty("os.name").toLowerCase().indexOf("linux") != -1;

                if (linuxFlag) {
                    cmd = Runtime.getRuntime().exec("top -b -S -d 1");
                    br = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
                    String readLine = null;
                    while ((readLine = br.readLine()) != null) {
                        if (readLine.startsWith("Cpu(s):")) {
                            commands.cpuInfo = readLine;
                            continue;
                        }
                    }
                    cmd.wait(1000 * commands.seconds + 2000);
                }

            } catch (Exception e) {
                commands.cpuInfo = e.getMessage();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {

                    }
                }
                if (cmd != null) {
                    cmd.destroy();
                }
            }
        }
    }

    static final class ReportThread extends Thread {

        final TestFileFrame commands;
        final AtomicLong numPeriod;
        final long millisecondsPeriod;

        public ReportThread(TestFileFrame commands, AtomicLong numPeriod, final int interval) {
            super();
            this.commands = commands;
            this.numPeriod = numPeriod;
            this.millisecondsPeriod = interval;
        }

        public void run() {

            long sTime = System.nanoTime();

            try {
                Thread.sleep(millisecondsPeriod);
            } catch (InterruptedException e) {
            }
            long milliseconds = 0;
            while (true) {
                milliseconds = (System.nanoTime() - sTime) / 1000000;

                if (logger.isDebugEnabled()) {
                    logger.debug(commands.toString(milliseconds, numPeriod));
                }

                try {
                    numPeriod.set(0);
                    sTime = System.nanoTime();
                    Thread.sleep(millisecondsPeriod);
                } catch (InterruptedException e) {

                }
            }

        }
    }

    static final class OperationsThread extends Thread {
        final TestFileFrame commands;
        final AtomicLong numPeriod;

        public OperationsThread(TestFileFrame commands, AtomicLong numPeriod) {
            super();
            this.commands = commands;
            this.numPeriod = numPeriod;
        }

        public void run() {
            final String id = Long.toString(Thread.currentThread().getId());
            while (commands.running) {
                if (commands.doneOperations(id)) {
                    numPeriod.getAndIncrement();
                }
            }
        }
    }
}
