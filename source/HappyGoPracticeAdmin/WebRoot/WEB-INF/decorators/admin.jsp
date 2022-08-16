<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!doctype html>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${projectName}" /></title>
<link rel="stylesheet" type="text/css" href="<c:out value="${resourcesPath}"/>admin/css/global.css"
	media="all" />
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/common.js"></script>
<style>
.red {
	color: red;
}
.error {
	color: red;
}
</style>
<script>
	$(document).ready(function() {
		if ('${permissions}' != 1) {
			$("#isPermissions").remove();
			$(".isPermissions").remove();
		}
	});
</script>
<decorator:head />
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<div class="logo">
				<a href="<c:out value="${contextPath}"/>admin/index.do"><span class="sub">Happy Go 後台管理系統</span></a>
			</div>
			<ul class="topnav">
				<li>歡迎&nbsp;<c:out value="${manager.name}" />&nbsp;到訪！
				</li>
				<li><a href="#" onclick="location='<c:out value="${contextPath}"/>admin/logout.do';">登出</a></li>
			</ul>
		</div>
		<div class="content type1">
			<div class="sideMenu">
				<ul>
					<c:forEach items="${menuTasks}" var="parent" varStatus="status">
						<li
							<c:if test="${parent.id eq fn:substring(rightID, 0,3)}">class="on"</c:if>><a
							href="#"><c:out value="${parent.name}" /></a>
							<div class="subMenuBlock">
								<span class="ddicon"></span>
								<ul class="subMenu">
									<c:if test="${not empty parent.childs}">
										<c:forEach items="${parent.childs}" var="child" varStatus="p">
											<li <c:if test="${child.id eq rightID}">class="on"</c:if>>
												<c:if test="${not noLink}">
													<a href="<spring:url value="${child.url}" context="${contextPath}admin"/>"><c:out value="${child.name}" /></a>
												</c:if>
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</div></li>
					</c:forEach>
				</ul>
			</div>
			<decorator:body />
			<div class="footer">
				<p>
					版權所有Copyright © <fmt:formatDate value="${today}" pattern="yyyy" /> All rights reserved.
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/basic.js"></script>
</body>
<script>
	//計算footer的高度
	$(function() {

		$('input, textarea').placeholder({
			customClass : 'my-placeholder'
		});
		$(window).bind(
				'resize',
				function() {
					var pageheight = $(window).height()
							- $('.footer').outerHeight()
							- $('.header').outerHeight();
					if ($('.sidebar').height() < pageheight) {
						$('.main_wrapper').css("min-height", pageheight);
					} else {
						$('.main_wrapper').css("min-height",
								$(".sidebar").height());
					}
					$('.sidebar')
							.css("min-height", $('.main_wrapper').height());
				}).resize();
	});
</script>
</html>
