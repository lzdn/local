<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<html>
	<!-- 第一个装饰页面 -->

	<head>
		<!-- 从被装饰页面获取title标签内容,并设置默认值-->
		<title>
			<decorator:title default="后台管理系统" />
		</title>
		<!-- 从被装饰页面获取head标签内容 -->
		<decorator:head />
	</head>

	<body>
		<h2>SiteMesh装饰header</h2>
		<hr />
		<!-- 从被装饰页面获取body标签内容 -->
		<decorator:body />
		<hr />
		<h2>SiteMesh装饰footer</h2>
	</body>

</html>