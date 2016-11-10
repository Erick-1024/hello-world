package test.cfca.sadk.jce.provider;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cfca.sadk.org.bouncycastle.jce.provider.BouncyCastleProvider;
import cfca.sadk.org.bouncycastle.util.encoders.Hex;

public class CommandFrame extends JFrame {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2500371362282385141L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        Security.addProvider(new BouncyCastleProvider());

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CommandFrame frame = new CommandFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    final JButton btnSignButton = new JButton("Sign");
    final JButton btnVeriButton = new JButton("Verify");

    final JTextArea outputTextArea = new JTextArea();
    final JTextPane plainTextPane = new JTextPane();
    final JTextPane encryptTextPane = new JTextPane();
    final JTextPane decryptTextPane = new JTextPane();

    final JTextPane dataTextPane = new JTextPane();
    final JTextPane signTextPane = new JTextPane();

    final JComboBox bitLengthComboBox = new JComboBox();
    final JComboBox typeComboBox = new JComboBox();
    final JComboBox cipherModeComboBox = new JComboBox();
    final JComboBox cipherFormatComboBox = new JComboBox();
    final JComboBox signerAlgsComboBox = new JComboBox();

    final Random random = new Random();
    final JTextPane xTextPane = new JTextPane();;
    final JTextPane yTextPane = new JTextPane();;
    final JScrollPane xScrollPane = new JScrollPane(xTextPane);
    final JScrollPane yScrollPane = new JScrollPane(yTextPane);

    /**
     * Create the frame.
     */
    public CommandFrame() {
        setTitle("DeviceJCE Testing Tools");
        final int withFrame = 1200;
        final int highFrame = 840;
        final int withPane = withFrame - 20;
        final int withHalfPane = (int) (withPane * 0.50);
        final int withPartPane = (int) (withPane * 0.33);

        final int highCenterPane = (int) (highFrame * 0.68);
        final int highOutputPane = (int) (highFrame * 0.32);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, withFrame, highFrame);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        final JPanel northPane = new JPanel();
        northPane.setBorder(new TitledBorder(null, "Selector", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        northPane.setPreferredSize(new Dimension(withPane - 10, 240));

        contentPane.add(northPane, BorderLayout.NORTH);
        northPane.setLayout(null);

        typeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final Object selectedItem = typeComboBox.getSelectedItem();
                if ("SM2".equals(selectedItem)) {
                    btnSignButton.setEnabled(true);
                    btnVeriButton.setEnabled(true);
                    bitLengthComboBox.setEnabled(false);
                    cipherModeComboBox.setModel(new DefaultComboBoxModel(new String[] { "SM2" }));
                    signerAlgsComboBox.setModel(new DefaultComboBoxModel(new String[] { "SM3WithSM2" }));
                    xScrollPane.setBorder(new TitledBorder(null, "Private Key", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                    yScrollPane.setBorder(new TitledBorder(null, "Public Key", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                    xTextPane.setText("");
                    yTextPane.setText("");
                    return;
                }
                if ("SM4".equals(selectedItem)) {
                    btnSignButton.setEnabled(false);
                    btnVeriButton.setEnabled(false);
                    bitLengthComboBox.setEnabled(false);
                    cipherModeComboBox.setModel(new DefaultComboBoxModel(new String[] { "SM4" }));
                    signerAlgsComboBox.setModel(new DefaultComboBoxModel(new String[0]));
                    xScrollPane.setBorder(new TitledBorder(null, "KEY", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                    yScrollPane.setBorder(new TitledBorder(null, "IV", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                    xTextPane.setText("");
                    yTextPane.setText("");
                    return;
                }
                if ("RSA".equals(selectedItem)) {
                    btnSignButton.setEnabled(true);
                    btnVeriButton.setEnabled(true);
                    bitLengthComboBox.setEnabled(true);
                    cipherModeComboBox.setModel(new DefaultComboBoxModel(new String[] { "RSA/ECB/PKCS1Padding" }));
                    signerAlgsComboBox.setModel(new DefaultComboBoxModel(new String[] { "SHA1WithRSA", "SHA224WithRSA", "SHA256WithRSA", "SHA384WithRSA",
                            "SHA512WithRSA" }));
                    xScrollPane.setBorder(new TitledBorder(null, "Private Key", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                    yScrollPane.setBorder(new TitledBorder(null, "Public Key", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                    xTextPane.setText("");
                    yTextPane.setText("");
                    return;
                }
                if ("DESede".equals(selectedItem)) {
                    btnSignButton.setEnabled(false);
                    btnVeriButton.setEnabled(false);
                    bitLengthComboBox.setEnabled(false);
                    cipherModeComboBox.setModel(new DefaultComboBoxModel(new String[] { "DESede/CBC/PKCS5Padding" }));
                    signerAlgsComboBox.setModel(new DefaultComboBoxModel(new String[0]));
                    xScrollPane.setBorder(new TitledBorder(null, "KEY", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                    yScrollPane.setBorder(new TitledBorder(null, "IV", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                    xTextPane.setText("");
                    yTextPane.setText("");
                    return;
                }
            }
        });
        typeComboBox.setModel(new DefaultComboBoxModel(new String[] { "SM2", "SM4", "RSA", "DESede" }));
        typeComboBox.setBounds(10, 25, 240, 25);
        northPane.add(typeComboBox);

        final JButton cipherBtnButton = new JButton("Generate");
        cipherBtnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final Object selectedItem = typeComboBox.getSelectedItem();
                if ("SM2".equals(selectedItem)) {
                    try {
                        KeyPairGenerator gen = KeyPairGenerator.getInstance("SM2", "BC");
                        gen.initialize(256);
                        KeyPair keypair = gen.generateKeyPair();

                        PrivateKey privateKey = keypair.getPrivate();
                        PublicKey publicKey = keypair.getPublic();

                        xTextPane.setText(Hex.toHexString(privateKey.getEncoded()));
                        yTextPane.setText(Hex.toHexString(publicKey.getEncoded()));
                        return;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        String message = "Get Generate Failure for " + selectedItem + ": " + ex.getMessage();
                        println(message);
                        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if ("SM4".equals(selectedItem)) {
                    byte[] keyBytes = new byte[16];
                    random.nextBytes(keyBytes);
                    xTextPane.setText(Hex.toHexString(keyBytes));

                    byte[] ivBytes = new byte[16];
                    random.nextBytes(ivBytes);
                    yTextPane.setText(Hex.toHexString(ivBytes));
                    return;
                }
                if ("RSA".equals(selectedItem)) {
                    try {
                        int bitLength = Integer.parseInt((String) bitLengthComboBox.getSelectedItem());
                        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA", "BC");
                        gen.initialize(bitLength);
                        KeyPair keypair = gen.generateKeyPair();

                        PrivateKey privateKey = keypair.getPrivate();
                        PublicKey publicKey = keypair.getPublic();

                        xTextPane.setText(Hex.toHexString(privateKey.getEncoded()));
                        yTextPane.setText(Hex.toHexString(publicKey.getEncoded()));
                        return;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        String message = "Get Generate Failure for " + selectedItem + ": " + ex.getMessage();
                        println(message);
                        JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if ("DESede".equals(selectedItem)) {
                    byte[] keyBytes = new byte[24];
                    random.nextBytes(keyBytes);
                    xTextPane.setText(Hex.toHexString(keyBytes));

                    byte[] ivBytes = new byte[8];
                    random.nextBytes(ivBytes);
                    yTextPane.setText(Hex.toHexString(ivBytes));
                    return;
                }
            }

        });
        cipherBtnButton.setBounds(260, 25, 300, 25);
        northPane.add(cipherBtnButton);

        xScrollPane.setBorder(new TitledBorder(null, "Private Key", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        xScrollPane.setBounds(10, 60, withPane - 40, 100);
        northPane.add(xScrollPane);

        yScrollPane.setBorder(new TitledBorder(null, "Public Key", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        yScrollPane.setBounds(10, 170, withPane - 40, 100);
        northPane.add(yScrollPane);

        bitLengthComboBox.setEnabled(false);
        bitLengthComboBox.setModel(new DefaultComboBoxModel(new String[] { "1024", "2048" }));
        bitLengthComboBox.setBounds(510, 27, 77, 21);
        northPane.add(bitLengthComboBox);

        JTabbedPane contentTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentTabbedPane.setBounds(36, 126, withPane, highCenterPane);
        contentPane.add(contentTabbedPane, BorderLayout.CENTER);

        JPanel cipherPane = new JPanel();
        cipherPane.setLayout(new BorderLayout(0, 0));
        cipherPane.setBounds(36, 36, withPane, highCenterPane);
        contentTabbedPane.addTab("Cipher", null, cipherPane, null);
        cipherPane.setLayout(new BorderLayout(0, 0));

        plainTextPane.setBorder(new TitledBorder(null, "DataText", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        plainTextPane.setPreferredSize(new Dimension(withPartPane, highCenterPane));
        cipherPane.add(plainTextPane, BorderLayout.WEST);

        encryptTextPane.setBorder(new TitledBorder(null, "EncrypedText", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        encryptTextPane.setPreferredSize(new Dimension(withPartPane, highCenterPane));
        cipherPane.add(encryptTextPane, BorderLayout.CENTER);

        decryptTextPane.setBorder(new TitledBorder(null, "DecrypedText", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        decryptTextPane.setPreferredSize(new Dimension(withPartPane, highCenterPane));
        cipherPane.add(decryptTextPane, BorderLayout.EAST);

        JPanel cipherOperationPane = new JPanel();
        cipherPane.add(cipherOperationPane, BorderLayout.SOUTH);

        cipherModeComboBox.setModel(new DefaultComboBoxModel(new String[] { "SM2" }));
        cipherModeComboBox.setPreferredSize(new Dimension(240, 25));
        cipherOperationPane.add(cipherModeComboBox);

        cipherFormatComboBox.setModel(new DefaultComboBoxModel(new String[] { "ASN1(C1C3C2)" }));
        cipherFormatComboBox.setPreferredSize(new Dimension(150, 25));
        cipherOperationPane.add(cipherFormatComboBox);

        JButton btnEncryptButton = new JButton("Encrypt");
        btnEncryptButton.setPreferredSize(new Dimension(150, 25));
        btnEncryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                callEncryptOperation();

            }
        });

        JButton btnPlainButton = new JButton("Randon");
        btnPlainButton.setPreferredSize(new Dimension(150, 25));
        btnPlainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                byte[] plainBytes = new byte[10 + random.nextInt(100)];
                random.nextBytes(plainBytes);
                plainTextPane.setText(Hex.toHexString(plainBytes));
            }
        });
        cipherOperationPane.add(btnPlainButton);
        cipherOperationPane.add(btnEncryptButton);

        JButton btnDecryptButton = new JButton("Decrypt");
        btnDecryptButton.setPreferredSize(new Dimension(150, 25));
        btnDecryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                callDecryptOperation();

            }
        });
        cipherOperationPane.add(btnDecryptButton);

        final JPanel signerPane = new JPanel();
        signerPane.setBounds(36, 36, withPane, highCenterPane);
        contentTabbedPane.addTab("Signer", null, signerPane, null);
        signerPane.setLayout(new BorderLayout(0, 0));

        dataTextPane.setBorder(new TitledBorder(null, "DataText", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        dataTextPane.setPreferredSize(new Dimension(withHalfPane - 5, highCenterPane));
        signerPane.add(dataTextPane, BorderLayout.WEST);

        signTextPane.setBorder(new TitledBorder(null, "SignText", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        signTextPane.setPreferredSize(new Dimension(withHalfPane - 5, highCenterPane));
        signerPane.add(signTextPane, BorderLayout.EAST);

        JPanel signerOperationPane = new JPanel();
        signerPane.add(signerOperationPane, BorderLayout.SOUTH);

        signerAlgsComboBox.setModel(new DefaultComboBoxModel(new String[] { "SM3WithSM2" }));
        signerAlgsComboBox.setPreferredSize(new Dimension(150, 25));
        signerOperationPane.add(signerAlgsComboBox);

        btnSignButton.setPreferredSize(new Dimension(150, 25));
        btnSignButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                callSignOperation();
            }
        });

        JButton btnDataRandomButton = new JButton("Random");
        btnDataRandomButton.setPreferredSize(new Dimension(150, 25));
        btnDataRandomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                byte[] dataBytes = new byte[10 + random.nextInt(100)];
                random.nextBytes(dataBytes);
                dataTextPane.setText(Hex.toHexString(dataBytes));
            }
        });
        signerOperationPane.add(btnDataRandomButton);
        signerOperationPane.add(btnSignButton);

        btnVeriButton.setPreferredSize(new Dimension(150, 25));
        btnVeriButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                callVerifyOperation();
            }
        });
        signerOperationPane.add(btnVeriButton);

        JPanel southPane = new JPanel();
        southPane.setPreferredSize(new Dimension(withPane, highOutputPane));
        southPane.setBorder(new TitledBorder(null, "Output Logging", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(southPane, BorderLayout.SOUTH);

        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setPreferredSize(new Dimension(withPane - 10, highOutputPane - 80));
        southPane.add(outputScrollPane, BorderLayout.CENTER);

        JPanel operationPane = new JPanel();
        operationPane.setPreferredSize(new Dimension(withPane - 10, 40));
        southPane.add(operationPane, BorderLayout.NORTH);

        JButton cleanLoggingButton = new JButton("Clean Logging");
        cleanLoggingButton.setPreferredSize(new Dimension(150, 25));
        cleanLoggingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText("");
            }
        });
        operationPane.add(cleanLoggingButton);

    }

    final void callSignOperation() {

        String dataText = dataTextPane.getText().trim();
        println("-----dataText: Hex=" + dataText);
        println("-----dataText: Len=" + dataText.length());
        if (dataText.length() == 0) {
            JOptionPane.showMessageDialog(null, "No DataText", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        byte[] dataBytes = Hex.decode(dataText);
        if (dataBytes == null) {
            JOptionPane.showMessageDialog(null, "No DataBytes", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        final String algorithm = (String) typeComboBox.getSelectedItem();
        if (!"SM2".equals(algorithm) && !"RSA".equals(algorithm)) {
            JOptionPane.showMessageDialog(null, "Not support for " + algorithm, "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        PrivateKey privateKey;
        try {
            privateKey = buildPrivateKey(algorithm);
        } catch (Exception ex) {
            String message = "Failure on build PrivateKey: " + ex.getMessage();
            println(message);
            JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Signature signature = Signature.getInstance((String) signerAlgsComboBox.getSelectedItem(), "BC");
            signature.initSign(privateKey);
            signature.update(dataBytes);
            byte[] signBytes = signature.sign();

            String signText = Hex.toHexString(signBytes);
            println("-----signText: Hex=" + signText);
            println("-----signText: Len=" + signText.length());
            signTextPane.setText(signText);

        } catch (Exception ex) {
            String message = "Failure on signed: " + ex.getMessage();
            println(message);
            JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
            return;

        }

    }

    final void callVerifyOperation() {

        String signText = signTextPane.getText().trim();

        if (signText.length() == 0) {
            JOptionPane.showMessageDialog(null, "No SignText", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        byte[] signBytes = Hex.decode(signText);
        if (signBytes == null) {
            JOptionPane.showMessageDialog(null, "No SignBytes", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        println("-----signText: Hex=" + signText);
        println("-----signText: Len=" + signText.length());

        String dataText = dataTextPane.getText().trim();
        println("-----dataText: Hex=" + dataText);
        println("-----dataText: Len=" + dataText.length());
        if (dataText.length() == 0) {
            JOptionPane.showMessageDialog(null, "No DataText", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        byte[] dataBytes = Hex.decode(dataText);
        if (dataBytes == null) {
            JOptionPane.showMessageDialog(null, "No DataBytes", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        final String algorithm = (String) typeComboBox.getSelectedItem();
        if (!"SM2".equals(algorithm) && !"RSA".equals(algorithm)) {
            JOptionPane.showMessageDialog(null, "Not support for " + algorithm, "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        PublicKey publicKey = null;
        try {
            publicKey = buildPublicKey(algorithm);
        } catch (Exception ex) {
            String message = "Failure on build PublicKey: " + ex.getMessage();
            println(message);
            JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Signature signature = Signature.getInstance((String) signerAlgsComboBox.getSelectedItem(), "BC");
            signature.initVerify(publicKey);
            signature.update(dataBytes);
            boolean signResult = signature.verify(signBytes);
            println("signResult: " + signResult);
            if (!signResult) {
                JOptionPane.showMessageDialog(null, "Verify Failure", "Failure", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Verify Successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            String message = "Failure on signed: " + ex.getMessage();
            println(message);
            JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    final void callEncryptOperation() {

        String dataText = plainTextPane.getText().trim();
        println("-----dataText: Hex=" + dataText);
        println("-----dataText: Len=" + dataText.length());
        if (dataText.length() == 0) {
            JOptionPane.showMessageDialog(null, "No PlainText", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        byte[] plainBytes = Hex.decode(dataText);
        if (plainBytes == null) {
            JOptionPane.showMessageDialog(null, "No PlainBytes", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        byte[] encryptedBytes = null;
        final String algorithm = (String) typeComboBox.getSelectedItem();
        if ("SM2".equals(algorithm) || "RSA".equals(algorithm)) {
            PublicKey publicKey = null;
            try {
                publicKey = buildPublicKey(algorithm);
            } catch (Exception ex) {
                String message = "Failure on build PublicKey: " + ex.getMessage();
                println(message);
                JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Cipher cipher = Cipher.getInstance((String) cipherModeComboBox.getSelectedItem(), "BC");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                encryptedBytes = cipher.doFinal(plainBytes);
            } catch (Exception ex) {
                ex.printStackTrace();
                String message = "Failure on Encryped: " + ex.getMessage();
                println(message);
                JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if ("SM4".equals(algorithm) || "DESede".equals(algorithm)) {
            SecretKey key;
            try {
                key = buildSecretKey(algorithm);
            } catch (Exception ex) {
                ex.printStackTrace();
                String message = "Failure on build SecretKey: " + ex.getMessage();
                println(message);
                JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }
            IvParameterSpec iv;
            try {
                iv = buildSecretIV(algorithm);
            } catch (Exception ex) {
                ex.printStackTrace();
                String message = "Failure on build  IvParameter: " + ex.getMessage();
                println(message);
                JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Cipher cipher = Cipher.getInstance((String) cipherModeComboBox.getSelectedItem(), "BC");
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);
                encryptedBytes = cipher.doFinal(plainBytes);
            } catch (Exception ex) {
                ex.printStackTrace();
                String message = "Failure on Encryped: " + ex.getMessage();
                println(message);
                JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String encryptedText = Hex.toHexString(encryptedBytes);
        println("encryptedText: Hex=" + encryptedText);
        println("encryptedText: Len=" + encryptedText.length());
        encryptTextPane.setText(encryptedText);

    }

    final void callDecryptOperation() {

        String encryptedText = encryptTextPane.getText().trim();
        println("encryptedText: Hex=" + encryptedText);
        println("encryptedText: Len=" + encryptedText.length());

        if (encryptedText.length() == 0) {
            JOptionPane.showMessageDialog(null, "No EncryptedText", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        byte[] encryptedBytes = Hex.decode(encryptedText);
        if (encryptedBytes == null) {
            JOptionPane.showMessageDialog(null, "No EncryptedBytes", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dataText = plainTextPane.getText().trim();
        println("-----dataText: Hex=" + dataText);
        println("-----dataText: Len=" + dataText.length());

        byte[] plainBytes = null;
        if (dataText.length() != 0) {
            plainBytes = Hex.decode(dataText);
        }

        byte[] decryptedBytes = null;
        final String algorithm = (String) typeComboBox.getSelectedItem();
        if ("SM2".equals(algorithm) || "RSA".equals(algorithm)) {
            PrivateKey privateKey;
            try {
                privateKey = buildPrivateKey(algorithm);
            } catch (Exception ex) {
                String message = "Failure on build PrivateKey: " + ex.getMessage();
                println(message);
                JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Cipher cipher = Cipher.getInstance((String) cipherModeComboBox.getSelectedItem(), "BC");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                decryptedBytes = cipher.doFinal(encryptedBytes);

            } catch (Exception ex) {
                ex.printStackTrace();
                String message = "Failure on Decryped: " + ex.getMessage();
                println(message);
                JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if ("SM4".equals(algorithm) || "DESede".equals(algorithm)) {
            SecretKey key;
            try {
                key = buildSecretKey(algorithm);
            } catch (Exception ex) {
                ex.printStackTrace();
                String message = "Failure on build SecretKey: " + ex.getMessage();
                println(message);
                JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }
            IvParameterSpec iv;
            try {
                iv = buildSecretIV(algorithm);
            } catch (Exception ex) {
                ex.printStackTrace();
                String message = "Failure on build  IvParameter: " + ex.getMessage();
                println(message);
                JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Cipher cipher = Cipher.getInstance((String) cipherModeComboBox.getSelectedItem(), "BC");
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
                decryptedBytes = cipher.doFinal(encryptedBytes);
            } catch (Exception ex) {
                ex.printStackTrace();
                String message = "Failure on Decryped: " + ex.getMessage();
                println(message);
                JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String decryptedText = Hex.toHexString(decryptedBytes);
        println("decryptedText: Hex=" + decryptedText);
        println("decryptedText: Len=" + decryptedText.length());

        decryptTextPane.setText(decryptedText);

        if (plainBytes != null && !Arrays.equals(plainBytes, decryptedBytes)) {
            JOptionPane.showMessageDialog(null, "Failure on decrypted: (decryptedBytes!=plainBytes)", "Failure", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    final PrivateKey buildPrivateKey(final String algorithm) throws Exception {
        String keyText = xTextPane.getText().trim();
        println("-----PrivateKeyText: Hex=" + keyText);
        println("-----PrivateKeyText: Len=" + keyText.length());
        if (keyText.length() == 0) {
            throw new Exception("No PrivateKeyText");
        }

        byte[] keyBytes = Hex.decode(keyText);
        if (keyBytes == null) {
            throw new Exception("No PrivateKeyBytes");
        }

        PrivateKey privateKey;

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm, "BC");

        privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;

    }

    final PublicKey buildPublicKey(String algorithm) throws Exception {
        String keyText = yTextPane.getText().trim();
        println("-----PublicKeyText: Hex=" + keyText);
        println("-----PublicKeyText: Len=" + keyText.length());
        if (keyText.length() == 0) {
            throw new Exception("No PublicKeyText");
        }

        byte[] keyBytes = Hex.decode(keyText);
        if (keyBytes == null) {
            throw new Exception("No PublicKeyBytes");
        }

        PublicKey publicKey;

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm, "BC");
        publicKey = keyFactory.generatePublic(keySpec);

        return publicKey;

    }

    final SecretKey buildSecretKey(final String algorithm) throws Exception {
        String keyText = xTextPane.getText().trim();
        println("-----SecretKeyText: Hex=" + keyText);
        println("-----SecretKeyText: Len=" + keyText.length());
        if (keyText.length() == 0) {
            throw new Exception("No SecretKeyText");
        }

        byte[] keyBytes = Hex.decode(keyText);
        if (keyBytes == null) {
            throw new Exception("No SecretKeyBytes");
        }
        if ("SM4".equals(algorithm) && keyBytes.length != 16) {
            throw new Exception("length!=16 for SecretKeyBytes");
        }
        if ("DESede".equals(algorithm) && keyBytes.length != 24) {
            throw new Exception("length!=24 for SecretKeyBytes");
        }

        SecretKey key = new SecretKeySpec(keyBytes, algorithm);

        return key;

    }

    final IvParameterSpec buildSecretIV(String algorithm) throws Exception {
        String keyText = yTextPane.getText().trim();
        println("-----SecretIVText: Hex=" + keyText);
        println("-----SecretIVText: Len=" + keyText.length());
        if (keyText.length() == 0) {
            throw new Exception("No SecretIVText");
        }

        byte[] ivBytes = Hex.decode(keyText);
        if (ivBytes == null) {
            throw new Exception("No SecretIVBytes");
        }
        if ("SM4".equals(algorithm) && ivBytes.length != 16) {
            throw new Exception("length!=16 for SecretIVBytes");
        }
        if ("DESede".equals(algorithm) && ivBytes.length != 8) {
            throw new Exception("length!=8 for SecretIVBytes");
        }

        IvParameterSpec ivParam = new IvParameterSpec(ivBytes);

        return ivParam;

    }

    /**
     * 将不满足长度的字节数据高位填充满足指定的字节数据长度
     * 
     * @param block
     * @param bytesLength
     * @return bytes
     */
    final byte[] formatBytes(byte[] block, int bytesLength) {
        byte[] bytes = block;
        if (block != null && block.length < bytesLength) {
            bytes = new byte[bytesLength];
            System.arraycopy(block, 0, bytes, bytes.length - block.length, block.length);
        }
        return bytes;
    }

    final void println(Object message) {
        outputTextArea.append("\n" + message);
    }
}
