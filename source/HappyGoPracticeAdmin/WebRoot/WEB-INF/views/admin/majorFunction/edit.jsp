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
				<tr>
					<th scope="row"><span class="red">※</span>主選單名稱</th>
					<td>
					<form:input path="name" maxlength="50" />
					<form:errors path="name" cssClass="error"/>
					</td>
				</tr>
								
				<tr>
					<th scope="row"><span class="red">※</span>文字顏色</th>
					<td>
					<form:input path="color" maxlength="50" />
					<span style="color: green;">HTML色碼範例：#000000 ~ #FFFFFF</span>
					<form:errors path="color" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>游標效果顏色</th>
					<td>
					<form:input path="cursor_color" maxlength="50" />
					<span style="color: green;">HTML色碼範例：#000000 ~ #FFFFFF</span>
					<form:errors path="cursor_color" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row">顯示icon</th>
					
					<td>
						<form:radiobuttons path="show_icon" items="${iconOption}"/>
						<form:errors path="show_icon" cssClass="error"/>
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
					<th scope="row"><span class="red">※</span>類型</th>
					<td>
						<form:radiobuttons path="type" items="${typeOption}"/>
						<form:errors path="type" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>主選單連結</th>
					<td>
					<form:input path="link" maxlength="100" />
					<form:errors path="link" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>廣告名稱<br/>(最多20字)</th>
					<td>
					<form:input path="ad_name" maxlength="20" />
					<form:errors path="ad_name" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>圖片<br/>(建議 185 * 185)</th>
					<td>
						<form:hidden path="image"/>
						
						<c:if test="${not empty command.image}">
							<img src="<c:out value="${imagePath}"/><c:out value="${command.image}"/>" width="185" height="185"/>
						</c:if>
						<br/>
						<input type="file" name="img_file" accept=".jpg,.gif,.png" />
						<form:errors path="image" cssClass="error"/>
						<div>1.圖檔格式只支援GIF,JPG,PNG三種格式,其他格式,系統將略過不處理!</div>
						<div>2.新上傳圖檔,會自動將舊圖檔直接覆蓋!</div>
					</td>
				</tr>
						
				<tr class="bg">
					<th scope="row"><span class="red">※</span>廣告文案<br/>(最多150字)</th>
					<td><form:textarea path="ad_proposal" rows="10" cols="20" maxlength="150" style="margin: 0px; width: 400px; height: 300px;"/> 
					<form:errors path="ad_proposal" cssClass="error" /></td>
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