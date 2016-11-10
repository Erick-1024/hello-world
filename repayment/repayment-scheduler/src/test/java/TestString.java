import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class TestString {

	@Test
	public void test() {
		System.out.println(String.valueOf(true));
		System.out.println(String.valueOf(false));
		
		System.out.println(new BigDecimal(12).setScale(-4, RoundingMode.CEILING).longValue());
	}

}
