import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;

public class TestLong extends TestCase{
	
	@Test
	public void test(){
		
		Long objectLong = 1L;
		System.out.println(objectLong == 1);
		
		System.out.println(new Date().getTime() / 1000 % 3600);
		
	}

}
