package com.cana.vbam.front.biz.controller.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.front.common.util.ImageLegitimacyUtil;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.member.dto.user.CustomerRegisterDTO;
import com.cana.vbam.common.member.dto.user.UserActivationDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.utils.PasswordEncoderUtil;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil.MediaType;

@Controller
@RequestMapping(value="/facade")
public class FacadeController {
	
	@Resource
	private IUserApi userApi;
	
	private static final Logger LGR = LoggerFactory.getLogger(FacadeController.class);
	
	@RequestMapping(value="/signin")
	public String gotoLogin() {
		return "facade/signin";
	}
	
	@RequestMapping(value="/denied")
	public String gotoDenied() {
		return "facade/denied";
	}
	
	@RequestMapping(value="/maintenance")
	public String gotoMaintenance() {
		return "facade/maintenance";
	}
	
	@RequestMapping(value="/forget/password")
	public String gotoForgetPassword() {
		return "facade/forgetPassword";
	}
	
	@RequestMapping(value="/gotoRegister")
	public String gotoRegister(Model model) {
		model.addAttribute("userTypes", UserType.nonIndividualUserTypes());
		return "facade/register";
	}
	
	@RequestMapping(value="/verifyUsername")
	@ResponseBody
	public boolean verifyUsername(String value) {
		boolean usernameExist = false;
		try {
			usernameExist = userApi.checkUsernameExist(value);
		} catch (Exception e) {
			LGR.error("检查用户名唯一性失败，用户名为："+value, e);
		}
		return usernameExist;
	}
	
	@RequestMapping(value="/verifyCompanyName")
	@ResponseBody
	public boolean verifyCompanyName(String name, String userType) {
		boolean companyNameExist = false;
		if(StringUtils.isBlank(userType)){
			LGR.info("检查公司名称唯一性失败，枚举类型为空");
			return companyNameExist;
		}
		UserType type = null;
		try{
			type = UserType.valueOf(userType);
		}catch(IllegalArgumentException e){
			LGR.info("检查公司名称唯一性失败，枚举类型错误 : "+userType);
		}
		try {
			companyNameExist = userApi.checkCompanyNameExist(name, type);
		} catch (Exception e) {
			LGR.info("检查公司名称唯一性失败，公司名称为："+name +"用户类型为: "+type.desc(), e);
		}
		return companyNameExist;
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public void uploadImage(MultipartFile image, HttpServletResponse httpServletResponse) throws IOException{
		String imageId = "";
		try {
			image = ImageLegitimacyUtil.verifyImage(image);
			imageId = MediaClientUtil.upload(image.getBytes(), MediaType.IMAGE);
		} catch(WebException we){
			LGR.error(we.getMessage(), we);
			imageId = "LARGE:" + we.getMessage();
		} catch (Exception e) {
			LGR.error("图片上传失败", e);
			imageId = "FAILED";
		}
		httpServletResponse.setContentType("text/html");
		httpServletResponse.getWriter().write(imageId);
	}
	
	@RequestMapping(value="saveFile",method=RequestMethod.POST)
	public void uploadFile(MultipartFile file, HttpServletResponse httpServletResponse) throws IOException{
		String mediaId = "";
		try {
			mediaId = MediaClientUtil.upload(file.getBytes(), MediaType.IMAGE, file.getOriginalFilename());
		} catch (Exception e) {
			LGR.error("文件上传失败", e);
			mediaId = "FAILED";
		}
		httpServletResponse.setContentType("text/html");
		httpServletResponse.getWriter().write(mediaId);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(CustomerRegisterDTO customerRegisterDTO, Model model){
		try {
			model.addAttribute("customerId", userApi.addCustomer(customerRegisterDTO));
		} catch (Exception e) {
			LGR.error("添加企业客户失败", e);
		}
		return "redirect:/facade/showRegisterResult";
	}
	
	@RequestMapping(value="/showRegisterResult")
	public String showRegisterResultPage(@RequestParam String customerId){
		return "facade/success";
	}
	
	@RequestMapping(value="/userActivation/goToActive")
	public String goToUserActivation(@RequestParam String userId,@RequestParam String securityCode,String usernameForRead, String passwordForContact,String passwordForContactSecond,Model model) throws Exception {
		UserActivationDTO userActivationResponseDTO = userApi.goToActivation(userId, securityCode);
		model.addAttribute("userActivationResponseDTO", userActivationResponseDTO);
		model.addAttribute("userId", userId);
		model.addAttribute("securityCode", securityCode);
		model.addAttribute("usernameForRead", usernameForRead);
		model.addAttribute("passwordForContact", passwordForContact);
		model.addAttribute("passwordForContactSecond", passwordForContactSecond);
		return "facade/active";
	}
	
	@RequestMapping(value="/userActivation/active")
	public String activateUser(UserActivationDTO userActivationDTO,Model model)throws Exception{
		if(StringUtils.isBlank(userActivationDTO.getPassword())){
			throw WebException.instance("密码不能未空");
		}
		userActivationDTO.setPassword(PasswordEncoderUtil.encrypt(userActivationDTO.getPassword()));
		UserActivationDTO userDTO = userApi.activate(userActivationDTO);
		model.addAttribute("userDTO", userDTO);
		return "facade/activateSuccess";
	}
	
	@RequestMapping(value="/gotoContract")
	public String gotoContact(@RequestParam String userId, @RequestParam String securityCode, String usernameForRead, String passwordForContact,String passwordForContactSecond, Model model){
		model.addAttribute("userId", userId);
		model.addAttribute("securityCode", securityCode);
		model.addAttribute("usernameForRead", usernameForRead);
		model.addAttribute("passwordForContact", passwordForContact);
		model.addAttribute("passwordForContactSecond", passwordForContactSecond);
		return "facade/contract";
	}
}
