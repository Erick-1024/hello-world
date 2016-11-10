package test.cfca.sadk.perfermance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public final class MainFrame {

    static final Logger logger = Logger.getLogger(MainFrame.class);

    public static void main(String[] args) throws Exception {
        DOMConfigurator.configure("./config/log4j.xml");
        CPUThread xx = new CPUThread();
        xx.setDaemon(true);
        xx.start();

        BufferedReader commandsReader = null;
        commandsReader = new BufferedReader(new FileReader("TestData/commands.txt"));

        logger.info(TestRSA1024P1.class.getName());

        String commandLine = null;
        while ((commandLine = commandsReader.readLine()) != null) {
            if (commandLine.startsWith("command")) {
                commandLine = commandLine.substring(1 + commandLine.indexOf('='));

                exec(commandLine);
                Thread.sleep(5000);
            }
        }
        if (commandsReader != null) {
            commandsReader.close();
        }

    }

    static final void exec(final String commandName) throws Exception {
        logger.info("command:=" + commandName);

        String[] commands = commandName.split(":");

        final int numProcess = Integer.parseInt(commands[0]);
        final String[] args = commands[1].split(" ");

        final String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        final String classpath = System.getProperty("java.class.path");
        String[] command = new String[3 + args.length];
        command[0] = java;
        command[1] = "-classpath";
        command[2] = classpath;
        System.arraycopy(args, 0, command, 3, args.length);

        final TestResult testResult = new TestResult();
        final Process[] ps = new Process[numProcess];

        for (int i = 0; i < ps.length; i++) {
            ps[i] = Runtime.getRuntime().exec(command);
            monitor(ps[i], testResult);
        }

        int codeResult = 0;
        for (int i = 0; i < ps.length; i++) {
            try {
                codeResult = ps[i].waitFor();
                logger.info(commandName + ": testResultFinished[" + i + "]= " + codeResult);
                if (codeResult != 0) {
                    ps[i].destroy();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        logger.info(commandName + ": testResultPerfermanceTPS= " + testResult.tps);
    }

    static final void monitor(final Process p, final TestResult testResult) {

        try {
            Thread eThread = new Thread(new Runnable() {
                public void run() {
                    try {

                        BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        String readLine = null;

                        while ((readLine = br.readLine()) != null) {
                            System.out.println("Hint: " + readLine);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            eThread.setDaemon(true);
            eThread.start();

            Thread iThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        String readLine = null;
                        while ((readLine = br.readLine()) != null) {
                            if (readLine.startsWith("tps:")) {
                                testResult.tps += Double.parseDouble(readLine.substring(1 + readLine.indexOf(':')));
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            iThread.setDaemon(true);
            iThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static final class TestResult {
        double tps;
    }

    static final class CPUThread extends Thread {

        public CPUThread() {
            super();
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

                    String cpuInfo = null;
                    while ((readLine = br.readLine()) != null) {
                        if (readLine.startsWith("Cpu(s):")) {
                            cpuInfo = readLine.substring(0, readLine.indexOf(','));
                            logger.info(cpuInfo);
                            System.out.println(cpuInfo);
                            continue;
                        }
                    }
                } else {
                    logger.info("window");
                    System.out.println("window cpuInfo");
                }

            } catch (Exception e) {
                e.printStackTrace();
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

}
