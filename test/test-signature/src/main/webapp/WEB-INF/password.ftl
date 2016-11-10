<html>
	<head>
	<title>请输入密码</title>
	</head>
	<body>
		<form method="post" action="finish">
			<table>
				<tr>
					<td>您的用户名为：</td>
					<td>${username}</td>
				</tr>
				<tr>
					<td>您的邮箱为：</td>
					<td>${email}</td>
				</tr>
				<tr>
					<td>请输入密码：</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>请确认密码：</td>
					<td><input type="password"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="checkbox">我已经阅读</td>
				</tr>
				<tr>
					<td><input type="submit" value="确认"></td>
					<td><input type="button" value="取消"></td>
				</tr>
			</table>
		</form>
	</body>
</html>