package test.cfca.sadk.testdata;

import test.cfca.sadk.perfermance.Constants;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;

public final class TestReady {

    public static final Session openSession() throws Exception {

        final String deviceName = Constants.cryptoType;
        JCrypto.getInstance().initialize(deviceName, null);
        Session session = JCrypto.getInstance().openSession(deviceName);

        return session;
    }
}
