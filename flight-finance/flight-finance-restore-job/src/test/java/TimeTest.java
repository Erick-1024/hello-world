import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TimeTest {

	@Test
	public void f(){
		System.out.println(currentTime());
	}
	
	public String currentTime(){
		long time=System.currentTimeMillis();
		SimpleDateFormat simple=new SimpleDateFormat("MM-dd HH:mm:ss");		
		return simple.format(time);
	}
}
