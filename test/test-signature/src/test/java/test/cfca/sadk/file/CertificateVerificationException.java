package test.cfca.sadk.file;

public final class CertificateVerificationException extends Exception {

    private static final long serialVersionUID = -5083978513259359808L;

    public CertificateVerificationException() {
    }

    public CertificateVerificationException(String message) {
        super(message);
    }

    public CertificateVerificationException(Throwable cause) {
        super(cause);
    }

    public CertificateVerificationException(String message, Throwable cause) {
        super(message, cause);
    }

}
