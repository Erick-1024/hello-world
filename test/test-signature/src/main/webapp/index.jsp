<%@page import="com.cana.member.authorization.common.MemberAuthConfig"%>
<%@page import="com.cana.vbam.common.utils.WebEnv"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>首页</title>
	</head>
	<body>
		<form method="post" action="login" style="font-size: 20px" enctype="multipart/form-data">  
			<label>用户名:</label><input type="text" name="userName" /> <br> 
			<label>密码:</label><input type="password" name="password" />  <br>
			<label>验证码:</label><input type="text" name="captcha" /> <a href="."><img src="captcha"> <br></a>
			<label>上传文件：</label><input type="file" name="upFile"> <br>
			<input type="submit" value="submit" />
		</form>
			<div>
				<a href="facade/gotoRegister"><input type="button" value="注册"></a>
			</div>
		<form method="post" action="customer/list">
			<div>
				<input type="submit" value="企业账号列表">
			</div>
		</form>
		<h2><a href="role/gotoCompanyRole" target="_blank">企业角色管理</a></h2>
		<h2><a href="role/gotoStaffRole" target="_blank">员工角色管理</a></h2>
	</body>
</html>
<%
response.sendRedirect(WebEnv.getVBAMPlatformPath().substring(0, WebEnv.getVBAMPlatformPath().length()-1) + MemberAuthConfig.get("member.url.loginSuccess"));
%>
