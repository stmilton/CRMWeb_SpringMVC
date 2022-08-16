<%--
    Document   : paging
    Created on : 2011/2/24, 下午 05:03:33
    Author     : Ringo

	pN	: page number
	pA	: pages available
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp" %>
<%
	request.setAttribute("reURI", request.getAttribute("javax.servlet.forward.request_uri"));
%>
<c:set var="pageStart" value="${list.pagesAvailable > 10 ? ((list.pageNo - 5) <= 0 ? 1 : ((list.pagesAvailable - list.pageNo) < 5 ? (list.pagesAvailable - 10) : list.pageNo - 4)) : 1}"/>
<c:set var="pageEnd" value="${list.totalPage}"/>
<c:if test="${not empty list}">
	<div class="page">
		<ul>
			<c:if test="${1 < list.pageNo}">
				<li><a href="#" onclick="javascript:doPaging(<c:out value='1'/>);return false;">第一頁</a></li>
			</c:if>
			<c:if test="${5 < list.pageNo}">
				<li><a href="#" onclick="javascript:doPaging(<c:out value='${(list.page - 10 <= 0 ? 1 : list.page - 10)}'/>);return false;">上十頁</a></li>
			</c:if>
       	  	<c:forEach var="pageNo" begin="${pageStart}" end="${(pageEnd > list.pagesAvailable) ? list.pagesAvailable : pageEnd}">
	       	  	<c:choose>
					<c:when test="${list.pageNo != pageNo}">
						<li><a href="#" onclick="doPaging(<c:out value='${pageNo}'/>);return false;"><c:out value="${pageNo}"/></a></li>
					</c:when>
					<c:otherwise>
						<li class="current"><a href="#" onclick="return false;"><c:out value="${pageNo}"/></a></li>
					</c:otherwise>
				</c:choose>
       	  	</c:forEach>
       	  	<c:if test="${page.pageNo <= page.pagesAvailable - 5}">
				<li><a href="#" onclick="javascript:doPaging(<c:out value='${(paging.page + 10 > page.pagesAvailable) ? page.pagesAvailable : page.pageNo+ 10}'/>);return false;">下十頁</a></li>
			</c:if>
			<c:if test="${page.pageNo < page.pagesAvailable}">
				<li><a href="#" onclick="javascript:doPaging(<c:out value='${page.pagesAvailable}'/>);return false;">最終頁</a></li>
			</c:if>
			<li>共 ${empty list.total ? 0 : list.total} 筆</li>
       	</ul>
       	<div class="clr"></div>
    </div>
	<form name="form4paging" id="form4paging" method="post" action="<c:out value="${reURI}"/>">
		<input type="hidden" name="pN" id="pN" value="${list.pageNo}" />
		<input type="hidden" name="pA" id="pA" value="${list.pagesAvailable}" />
		<input type="hidden" name="iA" id="iA" value="${list.total}" />
		<c:forEach items="${param}" var="p">
			<c:if test="${p.key ne 'pN' and p.key ne 'pA' and p.key ne 'iA'}">
				<input type='hidden' name='<c:out value="${p.key}"/>' id='<c:out value="${p.key}"/>' value='<c:out value="${p.value}"/>'/>
			</c:if>
		</c:forEach>
	</form>
	<script type="text/javascript">
		function doPaging(pN) {
			$("#form4paging #pN").val(pN);
			$("#form4paging").submit();
		};
	</script>
</c:if>