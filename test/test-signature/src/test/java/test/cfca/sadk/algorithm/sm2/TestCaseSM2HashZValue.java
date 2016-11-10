package test.cfca.sadk.algorithm.sm2;

import java.math.BigInteger;
import java.util.Arrays;

import junit.framework.TestCase;
import cfca.sadk.algorithm.sm2.SM2HashZValue;
import cfca.sadk.org.bouncycastle.util.BigIntegers;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class TestCaseSM2HashZValue extends TestCase {

    final byte[] userId = { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38 }; /* "1234567812345678" */

    final BigInteger affineXCoord = new BigInteger("26996171080475665573311653938507363657572534204979456314868954053127883049203");
    final BigInteger affineYCoord = new BigInteger("98621031979185433168996197298715335170371719097926342276209793254226056177168");
    final byte[] zvalue = HexBin.decode("75d2731b3311a013607f95e28f5ef211fd15132623768262e20ce6b7d592374b");

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetZa() {

        byte[] value = SM2HashZValue.getZa(affineXCoord, affineYCoord, userId);

        assertTrue("testGetZa", Arrays.equals(zvalue, value));
    }

    public void testGetSM2Za() {
        byte[] x = BigIntegers.asUnsignedByteArray(32, affineXCoord);
        byte[] y = BigIntegers.asUnsignedByteArray(32, affineYCoord);
        byte[] value = SM2HashZValue.getSM2Za(x, y, userId);

        assertTrue("testGetSM2Za", Arrays.equals(zvalue, value));

        value = SM2HashZValue.getSM2Za(x, y, null);

        assertTrue("testGetSM2Za", Arrays.equals(zvalue, value));

    }
}
