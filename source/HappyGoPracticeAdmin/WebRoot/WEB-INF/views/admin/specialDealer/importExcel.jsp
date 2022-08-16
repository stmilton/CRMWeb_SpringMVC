<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp"%>
<link rel="stylesheet" href="<c:out value="${resourcesPath}"/>admin/css/datepicker.css" type="text/css" />
    <link rel="stylesheet" href="<c:out value="${resourcesPath}"/>admin/css/jquery-ui-1.10.4.custom.css" type="text/css" />
	<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/jquery-ui-1.10.4.custom.js"></script>
	<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/jquery.ui.datepicker-zh-TW.js"></script>
    <script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/jquery-ui-timepicker-addon.js"></script>
    <script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/jquery.twzipcode.js"></script>
    
    
<div class="mainBlock">
	<div class="breadcrumbs">
		<ul>
			<li><a href="<c:out value="${contextPath}"/>admin/index.do">首頁</a></li>
			<li><a href="#">特約商管理</a></li>
			<li><a href="#"><c:out value="${menuName}" /></a></li>
		</ul>
	</div>
	<h3 class="titline">
		<span class="importExcel">匯入區</span>
	</h3>
	<form:form method="post" id="sForm" name="sForm" action="saveExcel.do" enctype="multipart/form-data">
		<form:hidden path="id" />
		<table class="tbType" cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody>
				<tr>
					<th scope="row"><span class="red">※</span>上傳EXCEL檔</th>
						
					<td>
					
						<P>匯入注意事項:<br/></P>										
						<ol type="I" start="1">
							<li>1.匯入EXCEL格式請於此<a href="http://localhost:8080/upload/happygo/example.xlsx">下載</a></li>
							<li>2.特約商分類....</li>
							<li>3.....</li>							
							<li>4.....</li>							
							<li>5.....</li>							
							<li>6.....</li>							
							<li>7.....</li>							
						</ol>		
														        
										
						<%--  action爲上傳路徑     method上傳文件的時候必須爲post   上傳文件還必須加上enctype="multipart/form-data  --%>
						<form action="/upload/happygo/excel/" method="post" enctype="multipart/form-data">
				        <input type="file" name="excel_file" style="width: 200px" accept=".xlsx,.xls">
					    </form>
					    <form:errors path="excel" cssClass="error"/>
						
					</td>
				</tr>
				
				
			</tbody>
		</table>
		<div class="btnSet line">
			<a href="#" onclick="$('#sForm').submit();">確認</a>
			<a href="list.do">取消</a>
		</div>
	</form:form>
	
</div>
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/tinymce/tinymce.min.js"></script>
<script>var tinymce_selector = "#description";</script>
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/tinymce/tinymce.custom.js"></script>