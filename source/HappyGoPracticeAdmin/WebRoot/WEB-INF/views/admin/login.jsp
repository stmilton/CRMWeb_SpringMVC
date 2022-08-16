<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:out value="${projectName}" /></title>
<link href="<c:out value="${resourcesPath}"/>admin/css/global.css" rel="stylesheet" type="text/css" media="all" />
<script src="<c:out value="${resourcesPath}"/>admin/js/jquery-2.2.0.min.js"></script>

<script type="text/javascript">
	$(function() {
		$('#target').click(function() {
			$('input[name="target"]').val("");
		});

		$('#sForm').each(function() {
			$(this).find('input').keypress(function(e) {
				// Enter pressed?
				if (e.which == 10 || e.which == 13) {
					this.form.submit();
				}
			});

			$(this).find('input[type=submit]').hide();
		});
	});

	function clearForm() {
		$('#sForm').trigger("reset");
	}
</script>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<div class="logo">
				<a href="index.html"><span class="sub">HappyGo</span></a>
			</div>
		</div>
		<form:form id="sForm" name="sForm" method="post">
			<input name="type" value="login" type="hidden" />
			<div class="loginArea">
				<div class="loginbox">

					<div class="inputbox">
						<span class="inputTit">帳&nbsp;&nbsp;號</span> <input type="text"
							name="account" id="account" />
					</div>
					<div class="inputbox">
						<span class="inputTit">密&nbsp;&nbsp;碼</span> <input
							type="password" name="pwd" id="pwd" />
					</div>
					<div class="logbtn">
						<a href="#" onclick="$('#sForm').submit();">登入</a>
					</div>

				</div>
				<div class="footer">
					<p>版權所有Copyright © <fmt:formatDate value="${today}" pattern="yyyy" /> All rights reserved.</p>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>