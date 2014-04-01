<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>

</head>
<body>
	<h3>nutz+sitemesh+httl+jext 整合测试</h3>


	<a href="http://nutzam.github.io/nutz/">nutz</a>
	<a href="http://httl.github.io/zh/">httl</a>
	<a href="http://subchen.github.io/jetbrick-template/overview.html">jetbrick-template</a>

	<a href="https://github.com/sitemesh/sitemesh3">sitemesh3</a>
	<br />
	<br />
	<table border="1">
		<tr>
			<th>作为sitemesh的decorator</th>
			<th>view为jsp</th>
			<th>view为httl</th>
			<th>view为jetx</th>

		</tr>
		<tr>
			<td>httl</td>
			<td><a href="${base }/httl/jsp" target="_blank">/httl/jsp</a></td>
			<td><a href="${base }/httl/httl" target="_blank">/httl/httl</a></td>
			<td><a href="${base }/httl/jetx" target="_blank">/httl/jetx</a></td>

		</tr>
		<tr>
			<td>jetx</td>
			<td><a href="${base }/jetx/jsp" target="_blank">/jetx/jsp</a></td>
			<td><a href="${base }/jetx/httl" target="_blank">/jetx/httl</a></td>
			<td><a href="${base }/jetx/jetx" target="_blank">/jetx/jetx</a></td>

		</tr>
		<tr>
			<td>结果</td>
			<td>成功</td>
			<td>成功</td>
			<td>失败</td>
		</tr>
	</table>
</body>
</html>