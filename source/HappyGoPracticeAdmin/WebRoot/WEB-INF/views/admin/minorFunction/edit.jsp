<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp"%>
<link rel="stylesheet" href="<c:out value="${resourcesPath}"/>admin/css/datepicker.css" type="text/css" />
    <link rel="stylesheet" href="<c:out value="${resourcesPath}"/>admin/css/jquery-ui-1.10.4.custom.css" type="text/css" />
	<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/jquery-ui-1.10.4.custom.js"></script>
	<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/jquery.ui.datepicker-zh-TW.js"></script>
    <script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/jquery-ui-timepicker-addon.js"></script>
<div class="mainBlock">
	<div class="breadcrumbs">
		<ul>
			<li><a href="<c:out value="${contextPath}"/>admin/index.do">首頁</a></li>
			<li><a href="#">全站資訊管理</a></li>
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
			
				<tr >
            		<th><span class="red">※</span>主選單</th>
            		<td>
            		<form:select path="major_name">
					<form:options items="${majorNamesOption}" />
					<form:errors path="major_name" cssClass="error"/>
					</form:select>
            		</td>
          		</tr>

				<tr>
					<th scope="row"><span class="red">※</span>次功能內容名稱<br/>(最多10字)</th>
					<td>
					<form:input path="name" maxlength="10" />
					<form:errors path="name" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>狀態</th>
					<td>
						<form:radiobuttons path="status" items="${statusOption}"/>
						<form:errors path="status" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>連結</th>
					<td>
					<form:input path="link" maxlength="100" />
					<form:errors path="link" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>連結開啟方式</th>
					<td>
						<form:radiobuttons path="open_method" items="${openMethodOption}"/>
						<form:errors path="open_method" cssClass="error"/>
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
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/tinymce/tinymce.min.js"></script>
<script>var tinymce_selector = "#description";</script>
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/tinymce/tinymce.custom.js"></script>