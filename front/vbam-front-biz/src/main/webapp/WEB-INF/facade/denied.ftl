<html>
	<head>
		<meta charset="utf-8" />
		<title>访问被拒绝！</title>

		<script type="text/javascript">
			function heartbeat(second) {
				second = second - 1;
				if (second <= 0) {
					window.location = '${basepath}/';
				} else {
					document.getElementById('timer').innerHTML = ' ' + second + ' ';
					setTimeout(function(){ heartbeat(second); }, 1000);
				}
			}
			window.onload=function(){
				setTimeout(function(){ heartbeat(5); }, 1000);
			}
		</script>

	</head>
	<body>
		您没有访问指定资源的权限。<strong id="timer" style="font-size:medium;color:red;font-size:50px;"> 5 </strong>秒后返回欢迎页……
		<br><br>
		<a href="${basepath}/">直接返回欢迎页</a>
	</body>
</html>
