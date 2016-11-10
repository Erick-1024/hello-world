package test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {
	
	@RequestMapping(value = "/test/login")
	public String login(){
		return "facade/login";
	}
}
