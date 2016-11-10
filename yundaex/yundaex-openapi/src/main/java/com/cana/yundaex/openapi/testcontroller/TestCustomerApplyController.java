package com.cana.yundaex.openapi.testcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.vbam.common.dto.ObjectResult;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyRequestDTO;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.security.RSAUtil;

@Controller
@RequestMapping(value="test")
public class TestCustomerApplyController {

	@RequestMapping(value="customerApply")
	public String customerApply(Model model){
		boolean flag = "/tops/prod".equals(TopsConfReader.getConfContent("properties/zkService.properties", "zkBasePath", ConfScope.R));
		if(flag)
			return "";
		else
			return "page/test/testcustomerapply";
	}
	
	
	@RequestMapping(value="ydGetSign",method=RequestMethod.POST)
	@ResponseBody
	public ObjectResult<?> getSign(@RequestBody YdCustomerApplyRequestDTO jsonAuditResult,Model model){
		System.out.println("开始getSign");
		StringBuffer str = new StringBuffer();
		str.append(jsonAuditResult.getStationNo()).append(jsonAuditResult.getStationName()).append(jsonAuditResult.getStationMgr())
		   .append(jsonAuditResult.getCustName()).append(jsonAuditResult.getCustIdno()).append(jsonAuditResult.getTelephone());
		String sign ="";
		try {
			sign = new String(RSAUtil.sign(str.toString().getBytes(),"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIrOv2wQsA6AXCi/3xMS8UkPj81g+r+S4CaB/DsSwQ34VVoxNWgwr0F8HPY9kpfviMlQkdZbx+ClAOnF9yRxxW+Lw5VYRnGE67Vxh+VldS49CoFv652ovCuM1wTtHOz23kSF59rNJDjeyAMilJ3Pq8/SiphCIgk0DILSD+mzf0idAgMBAAECgYBMor8O09vP9dBr8xsfNcsfckcLtipakKXOvN9cYgtSQjQlepuo7ZAlEHgQ/4eq+OXFeAU8mymsfo0VhGXhqiq9i+0n3S8Y+h4rfmh6hvND/JTyh7+DEazJy5LnEcPmeBR9kjHKSfAjRXg8hkenkvE/P3jLNf5BStflf5y8cy4Q5QJBANkFV4xOUf9jcDI9XAKNXuE+y14MwbPwKeNuWGksSQVD8QuZ8BYNGAXxslfMWQKtE09yrchI5lMYeL44F1l6NYMCQQCjvSVvQ4zUwj1rBAiGEDlCrCCA3XlI8U927gr8LqxyvuoEkkYDyfq8aafR4J2Ic8sWwFz9HC2lem+4991VNk9fAkEAovwrEvljBZ1ljqWca2JGxn1FeH22H/AXVXHyvhH/SRAMgLz8nWL6DsTFPsD+fE8FeJ5Uu11cdT2kuJ8hkhaBBwJANtzemSxSGMvIln3weTMgbIWOEn+i7tzkGl5iUeM1pvDvKn70dLqNh+oC8CDJx/m8d7AWuDxj0wl2O8zZXX7oEwJBAMO37in/MaURTQew9Pyh/DdwGHuxyyCpbD5HLdlCYU/CgBDRujf+mUevWSPhXE1I7DhBY0/9BH5JBU2cHc6kIPU="));
		} catch (Exception e) {
			e.printStackTrace();
			return ObjectResult.fail(e.getMessage());
		}
		return ObjectResult.success("success", sign);
	}
	
}
