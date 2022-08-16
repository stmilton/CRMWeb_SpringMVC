<%--
    Document   : expired-error
    Created on : 2014/3/28
    Author     : Ringo
--%>

<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp" %>

<!DOCTYPE html>
<html>
    <head>
		<meta http-equiv="expires" content="Tue, 20 Aug 1999 01:00:00 GMT"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${contextPath}/resources/js/MM.js"></script>
	</head>
	<body onLoad="MM_preloadImages('${contextPath}/resources/images/01_addid_f2.gif', '${contextPath}/resources/images/01_delete_f2.gif')">
		<div align="center">
			<table width="960" border="0" cellpadding="0" cellspacing="0" bordercolor="#666666">
				<tr>
					<td><table width="960"  border="0" cellpadding="2" cellspacing="0" bordercolor="#666666">
							<tr align="center" valign="middle">
								<td width="960">
									<c:import url="/WEB-INF/views/commons/header.jsp" charEncoding="utf-8" />
								</td>
							</tr>
							<tr>
								<td valign="top"><table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
										<tr>
											<td width="13" height="17" background="${contextPath}/resources/images/01.gif"></td>
											<td background="${contextPath}/resources/images/02.gif"></td>
											<td width="17" height="17" background="${contextPath}/resources/images/06.gif"></td>
										</tr>
										<tr>
											<td background="${contextPath}/resources/images/03.gif">&nbsp;</td>
											<td>
												<h1>表單已過期!</h1>
											</td>
											<td background="${contextPath}/resources/images/07.gif">&nbsp;</td>
										</tr>
										<tr>
											<td background="${contextPath}/resources/images/04.gif"></td>
											<td background="${contextPath}/resources/images/05.gif">&nbsp;</td>
											<td background="${contextPath}/resources/images/08.gif"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3">&nbsp;</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
	</body>
</html>
