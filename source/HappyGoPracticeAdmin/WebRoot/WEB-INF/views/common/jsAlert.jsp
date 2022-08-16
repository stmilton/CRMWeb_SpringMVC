<%--
    Document   : jsAlert
    Created on : 2009/4/15, 下午 03:59:15
    Author     : Ringo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<%
//request.setAttribute("sitemesh", "skip");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
   		<script>
			<c:if test="${not empty message}">alert("<spring:escapeBody javaScriptEscape="true"><c:out value="${message}"/></spring:escapeBody>");</c:if>
			<c:if test="${not empty relativeUrl}">
			<c:choose>
		    	<c:when test="${relativeUrl eq 'fancybox'}">
	    			parent.jQuery.fancybox.close();
	    			parent.window.location.href = parent.window.location.href;
		    	</c:when>
		    	<c:otherwise>
		    		window.location.replace("<spring:url value="${relativeUrl}"/>");
		    	</c:otherwise>
	    	</c:choose>
			</c:if>
		</script>
    </body>
</html>
