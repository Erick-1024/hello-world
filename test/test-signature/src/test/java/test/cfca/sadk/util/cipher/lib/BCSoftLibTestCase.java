package test.cfca.sadk.util.cipher.lib;

import cfca.sadk.lib.crypto.bcsoft.BCSoftLib;

public final class BCSoftLibTestCase extends SessionTestCase {

    protected void setUp() throws Exception {
        session = new BCSoftLib();
    }

    protected void tearDown() throws Exception {
        session = null;
    }

}
