<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp"%>
<div class="mainBlock">
	<div class="breadcrumbs">
		<ul>
			<li><a href="<c:out value="${contextPath}"/>admin/index.do">首頁</a></li>
			<li><a href="#">權限管理</a></li>
			<li><a href="#"><c:out value="${menuName}" /></a></li>
		</ul>
	</div>
	<h3 class="titline">
		<span class="edit">編輯區</span>
	</h3>
	<form:form method="post" id="sForm" name="sForm" action="save.do" enctype="multipart/form-data">
		<form:hidden path="id" />
		<table class="tbType" cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody>
				<tr>
					<th scope="row"><span class="red">※</span>角色名稱</th>
					<td><form:input path="name" maxlength="50" /> <form:errors path="name" cssClass="red" /></td>
				</tr>
				<tr class="bg">
					<th scope="row"><span class="red">※</span>角色權限</th>
					<td>
						<table>
							<c:forEach items="${taskList}" var="parent" varStatus="status">
								<c:if test="${status.count eq 0||status.count%2 eq 1}">
									<tr>
								</c:if>
								<td style="vertical-align:top;">
									<div align="left">
										<form:checkbox path="parentItems" value="${parent.id}" onchange="selectTaskAll(this)" />
										<label><strong><c:out value="${status.count}" />.<c:out value="${parent.name}" /></strong></label>
									</div>
									<div align="left">
										<blockquote>
											<c:forEach items="${parent.childs}" var="child" varStatus="status2">
												<div>
													<form:checkbox path="tasksAry" value="${child.id}" cssClass="task${parent.id}" />
													<label><c:out value="${status.count}.${status2.count}.${child.name}" /></label>
												</div>
											</c:forEach>
										</blockquote>
									</div>
								</td>
								<c:if test="${status.count%2 eq 0}">
									</tr>
								</c:if>
							</c:forEach>
						</table> <form:errors path="tasksAry" cssClass="red" />
					</td>
				</tr>
				<tr>
					<th scope="row"><span class="red">※</span>狀態</th>
					<td><c:choose>
							<c:when test="${command.id ne '1'}">
								<form:radiobutton path="status" value="1" /> 啟用
	    					<form:radiobutton path="status" value="0" /> 停用
							<form:errors path="status" cssClass="red" />
							</c:when>
							<c:otherwise>
							啟用
							<form:hidden path="status" />
							</c:otherwise>
						</c:choose></td>
				</tr>
			</tbody>
		</table>
		<div class="btnSet line">
			<a href="#" onclick="$('#sForm').submit();">儲存</a> <a href="list.do">取消</a>
		</div>
	</form:form>
</div>