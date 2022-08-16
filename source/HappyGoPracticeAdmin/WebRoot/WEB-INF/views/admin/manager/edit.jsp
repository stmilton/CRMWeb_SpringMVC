<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp"%>
<script type="text/javascript">
	$(function() {
		if(${permissions} != 1){
			$("input").attr("disabled" , true);
			$("select").attr("disabled" , true);
		}
	});
</script>
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
	<form:form method="post" id="sForm" name="sForm" action="save.do"
		enctype="multipart/form-data">
		<form:hidden path="id" />
		<table class="tbType" cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody>
				<tr>
					<th scope="row"><span class="red">※</span>帳號</th>
					<td>
					<c:choose>
						<c:when test="${empty command.id}">
							<form:input path="account" maxlength="20" />
							<span style="color: green;">帳號長度3~20碼英文或數字</span>
						</c:when>
						<c:otherwise>
							<c:out value="${command.account}" />
							<form:hidden path="account" />
						</c:otherwise>
					</c:choose>
					<br>
					<form:errors path="account" cssClass="red" /></td>
				</tr>
				<tr class="bg">
					<th scope="row">
						<span class="red">※</span>密碼
					</th>
					<td>
						<form:password path="newPwd" size="20" maxlength="20" /> 
						<form:hidden path="pwd" />
						<span style="color: green;">密碼長度3~20碼英文或數字</span> <br>
					<form:errors path="pwd" cssClass="red" /></td>
				</tr>
				<tr>
					<th scope="row"><span class="red">※</span>姓名</th>
					<td><form:input path="name" maxlength="20" /> <br>
					<form:errors path="name" cssClass="red" /></td>
				</tr>
				<tr>
					<th scope="row"><span class="red">※</span>email</th>
					<td><form:input path="email" /> <form:errors path="email"
							cssClass="red" /></td>
				</tr>
				<tr class="bg">
					<th scope="row"><span class="red">※</span>電話</th>
					<td><form:input path="mobile" maxlength="50" /> <br>
					<form:errors path="mobile" cssClass="red" /></td>
				</tr>
				<tr>
					<th scope="row"><span class="red"></span>電話</th>
					<td>
						<div class="inputSet editTitle short">
							<form:input path="tel" maxlength="20" />
						</div> <form:errors path="tel" cssClass="error" />
					</td>
				</tr>
				<tr class="bg">
					<th scope="row"><span class="red">※</span>所屬角色</th>
					<td>
						<form:select path="role_id">
							<form:option value="" label="請選擇" />
							<c:forEach items="${roleList}" var="data">
								<form:option value="${data.id}" label="${data.name}" />
							</c:forEach>
						</form:select> <br>
					<form:errors path="role_id" cssClass="red" />
				</td>
				</tr>
				<tr>
					<th scope="row"><span class="red">※</span>狀態</th>
					<td>
						<c:choose>
							<c:when test="${command.id ne '1'}">
								<form:radiobutton path="status" value="1" /> 啟用
	    						<form:radiobutton path="status" value="0" /> 停用
								<form:errors path="status" cssClass="red" />
							</c:when>
							<c:otherwise>啟用<form:hidden path="status" /></c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btnSet line">
			<a href="#" onclick="$('#sForm').submit();">儲存</a>
			<a href="list.do">取消</a>
		</div>
	</form:form>
</div>
