<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="mdbs" uri="/WEB-INF/tlds/tags" %>
<%@taglib prefix="f" uri="/WEB-INF/tlds/functions" %>
<%
request.setAttribute("contextPath", com.mdbs.util.WebUtil.getWebContextPath(request));
request.setAttribute("resourcesPath", com.mdbs.util.WebUtil.getWebContextPath(request) + "resources/");
request.setAttribute("projectName", "Happy Go-首頁");
%>
