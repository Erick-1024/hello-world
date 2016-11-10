import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;

public class GenerateFile {
	
	private FileOutputStream fOutputStream;
	public void writeFileToTmp() throws Exception{
		fOutputStream=new FileOutputStream(new File("/tmp/test/20150703.txt"));
		for(int i=0;i<10000;i++){
			fOutputStream.write(appendFlight(i+"11").getBytes());
		}
		fOutputStream.close();
	}
	@Test
	public void f() throws Exception{
//		writeFileToTmp();
		String an[]="a||||||a".split("\\|\\|");
 		System.out.println(an);
	}
	
	public String appendFlight(String recordId){
		StringBuffer sBuffer=new StringBuffer();
		sBuffer.append(recordId);
		sBuffer.append("||");
		sBuffer.append("10101000111");
		sBuffer.append("||");
		sBuffer.append("上海不夜城");
		sBuffer.append("||");
		sBuffer.append("true");
		sBuffer.append("||");
		sBuffer.append("1222222990");
		sBuffer.append("||");
		sBuffer.append("normal");
		sBuffer.append("||");
		sBuffer.append("false");
		sBuffer.append("||");
		sBuffer.append("2016-01-18 07:50:00");
		sBuffer.append("||");
		sBuffer.append("1234567890112");
		sBuffer.append("||");
		sBuffer.append("C1");
		sBuffer.append("||");
		sBuffer.append("不夜城");
		sBuffer.append("||");
		sBuffer.append("credit");
		sBuffer.append("||");
		sBuffer.append("120000");
		sBuffer.append("||");
		sBuffer.append("2000000");
		sBuffer.append("||");
		sBuffer.append("5000");
		sBuffer.append("||");
		sBuffer.append("0");
		sBuffer.append("||");
		sBuffer.append("iii");
		sBuffer.append("||");
		sBuffer.append("89");
		sBuffer.append("||");
		sBuffer.append("CA566");
		sBuffer.append("||");
		sBuffer.append("SHACAN");
		sBuffer.append("||");
		sBuffer.append("2016-01-23 16:40");
		sBuffer.append("||");
		sBuffer.append("M");
		sBuffer.append("||");
		sBuffer.append("李");
		sBuffer.append("||");
		sBuffer.append("ADT");
		sBuffer.append("||");
		sBuffer.append("NI");
		sBuffer.append("||");
		sBuffer.append("12299845395439985");
		sBuffer.append("||");
		sBuffer.append("192345485785");
		sBuffer.append("||");
		sBuffer.append("1232343345435");
		sBuffer.append("\r\n");
		return sBuffer.toString();
	}

}
