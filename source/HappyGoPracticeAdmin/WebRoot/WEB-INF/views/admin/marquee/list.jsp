<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp" %>
	<div class="mainBlock">
      <div class="breadcrumbs">
        <ul>
          <li><a href="<c:out value="${contextPath}"/>admin/index.do">首頁</a></li>
          <li><a href="#">首頁廣告管理</a></li>
          <li><c:out value="${menuName}"/></li>
        </ul>
      </div>
      <h3 class="tit"><c:out value="${menuName}"/></h3>
      <form:form method="post" name="sForm" id="sForm" action="list.do">
      <table class="tbType" width="100%" cellspacing="0" cellpadding="0" border="0">
        <tbody>
          <tr class="bg">
            <th width="20%">訊息內容</th>
            <td><form:input path="name"/></td>
          </tr>
          <tr >
            <th>狀態</th>
            <td>
            	<form:select path="status">
					<form:option value="">全部</form:option>
					<form:options items="${statusOption}" />
				</form:select>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="btnSet"> 
	      <a href="#" onclick="$('#sForm').submit();">查詢</a> 
	      <a href="#" class="del" onclick="location.replace('list.do');">清除</a> 
      </div>
      </form:form>
      <h3 class="tit"> 查詢結果
        <ul class="Rmenu">
          <li><a href="#" class="add"  onclick="add();">新增</a></li>
          <li><a href="#" onclick="update('1');">啟用</a></li>
          <li><a href="#" onclick="update('0');">停用</a></li>
          <li><a style="cursor: pointer;" class="bt sty2 hvr-shadow" onclick="deleteMultiple();">刪除</a></li>
        </ul>
      </h3>
      <form:form method="post" name="uForm" id="uForm" action="list.do">
      <table class="tbType" width="100%" cellspacing="0" cellpadding="0" border="0">
          <tbody>
          <tr>
            <th scope="col"><input type="checkbox" id="all" name="all" onclick="selectAll();"/>全選</th>
			<th scope="col">項次</th>
			<th scope="col">排序</th>
			<th scope="col">訊息內容</th>
			<th scope="col">開始時間</th>
			<th scope="col">結束時間</th>
			<th scope="col">狀態</th>
			<th scope="col">最後異動<br/>人員/時間</th>
			<th scope="col">功能</th>
          </tr>
          
          <c:forEach items="${page.pageItems}" var="data" varStatus="st">
			  <tr  <c:if test="${st.count % 2 == 0}">class="bg"</c:if>>
				<td align="center" width="8%"><c:if test="${data.id ne 'admin'}"><input type="checkbox" name="ids" value="${data.id}" /></c:if></td>
				<td align="center" width="5%"><c:out value="${(page.pageNo-1) * page.pageSize + st.count}"/></td>
				<td align="center" width="5%">
	              	<select name="sort" id="sort" onchange="updateSort('<c:out value="${data.id}"/>', this.value);">
						<c:forEach begin="1" end="${countList}" var="data1" >
							<option value="<c:out value="${data1}"/>" <c:if test="${data.sort == data1}">selected</c:if>><c:out value="${data1}"/></option>
						</c:forEach>
					</select>
				</td>
				<td align="center" width="15%"><c:out value="${data.name}"/></td>
				<td align="center" width="15%"><fmt:formatDate value="${data.start_time}" pattern="yyyy-MM-dd  HH:mm"/></td>
				<td align="center" width="15%"><fmt:formatDate value="${data.end_time}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td align="center" width="10%"><c:out value="${statusOption[data.status]}"/></td>
				<td align="center"><c:out value="${data.modifier}"/><br/><fmt:formatDate value="${data.modify_time}" pattern="yyyy-MM-dd HH:mm"/></td>
				
				<td align="center" width="5%">
					<div class="tbBtn"> <a href="#" class="edit" onclick="location.href='edit.do?id=<c:out value="${data.id}"/>';">編輯</a> </div>
				</td>
			  </tr>
		   </c:forEach>
		   
		   </tbody>
      </table>
      </form:form>
      <div class="clr"></div>
      <%@include file="/WEB-INF/views/common/admin/paging.jsp" %>
    </div>
