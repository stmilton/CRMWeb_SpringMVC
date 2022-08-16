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
			<li><a href="#">首頁廣告管理</a></li>
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
					<th scope="row"><span class="red">※</span>訊息內容<br/>(最多100字)</th>
					<td>
					<form:textarea path="name" rows="10" cols="20" maxlength="100" style="margin: 0px; width: 400px; height: 300px;"/>
					<form:errors path="name" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>開始時間</th>
					<td>
						<form:input path="start_time" />
						<form:errors path="start_time" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>結束時間</th>
					<td>
						<form:input path="end_time" />
						<form:errors path="end_time" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row">連結網址</th>
					<td>
					<form:input path="link" maxlength="100" />
					<form:errors path="link" cssClass="error"/>
					</td>
				</tr>
				<tr>
					<th scope="row">連結開啟方式</th>
					<td>
						<form:radiobuttons path="open_method" items="${openMethodOption}"/>
						<form:errors path="open_method" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>狀態</th>
					<td>
						<form:radiobuttons path="status" items="${statusOption}"/>
						<form:errors path="status" cssClass="error"/>
					</td>
				</tr>
				
				
			</tbody>
		</table>
		<div class="btnSet line">
			<a href="#" onclick="$('#sForm').submit();">儲存</a>
			<a href="list.do">取消</a>
		</div>
	</form:form>
	<script>
	$("#start_time").datetimepicker(
			{
				showSecond: false,
				changeYear : true,
				changeMonth : true,
				timeText: '時間',
				hourText:'小時',
				minuteText: '分鐘',
				currentText: '現在',
				closeText: '完成',
				showOn : 'both',
				dateFormat: 'yy-mm-dd',
			 	timeFormat: 'hh:mm',
				buttonImage : '<c:out value="${resourcesPath}"/>admin/images/calendar.jpg'
			});
	$("#end_time").datetimepicker(
			{
				showSecond: false,
				changeYear : true,
				changeMonth : true,
				timeText: '時間',
				hourText:'小時',
				minuteText: '分鐘',
				currentText: '現在',
				closeText: '完成',
				showOn : 'both',
				dateFormat: 'yy-mm-dd',
			 	timeFormat: 'hh:mm',
				buttonImage : '<c:out value="${resourcesPath}"/>admin/images/calendar.jpg'
			});
	</script>
</div>
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/tinymce/tinymce.min.js"></script>
<script>var tinymce_selector = "#description";</script>
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/tinymce/tinymce.custom.js"></script>