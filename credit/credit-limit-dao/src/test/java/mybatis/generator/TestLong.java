package mybatis.generator;

import org.junit.Test;

public class TestLong {

	@Test
	public void test() {
		Long a = 4l;
		Long b = a;
		b = 2l;
		System.out.println(a);
		System.err.println(b);
	}

}
