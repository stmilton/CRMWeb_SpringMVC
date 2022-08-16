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
		<span class="edit">編輯區</span>
	</h3>
	<form:form method="post" id="sForm" name="sForm" action="save.do" enctype="multipart/form-data">
		<form:hidden path="id" />
		<table class="tbType" cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody>
				<tr>
					<th scope="row"><span class="red">※</span>總公司名稱</th>
					<td>
						<form:input path="head_office_name" maxlength="25" />
						<form:errors path="head_office_name" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>特約商名稱</th>
					<td>
						<form:input path="special_dealer_name" maxlength="25" />
						<form:errors path="special_dealer_name" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>特約商代碼</th>
					<td>
						<form:input path="special_dealer_code" maxlength="25" />
						<form:errors path="special_dealer_code" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>特約商分類</th>					
					<td>
						<form:radiobuttons path="special_dealer_class" items="${specialDealerClassOption}"/>
						<form:errors path="special_dealer_class" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>郵遞區號</th>
					<td>
<!-- 						<div id="twzipcode" class="zipcode"></div> -->
						<div id="twzipcode">						  
						  <div data-role="county" data-style="Style Name" ></div>
						  <div data-role="district" data-style="Style Name" ></div>
						  <div data-role="zipcode" data-style="Style Name" data-value="${zipcode}"></div>
						</div>
						
					</td>
				</tr>
									
				<tr>
					<th scope="row">地址</th>
					<td>
						<form:input path="address" maxlength="50" />
						<form:errors path="address" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>是否顯示地址</th>
					<td>
						<form:radiobuttons path="show_address" items="${showAddressOption}"/>
						<form:errors path="show_address" cssClass="error"/>						
					</td>
				</tr>
				
				<tr>
					<th scope="row">電話</th>
					<td>
						<form:input path="phone" maxlength="15" />
						<form:errors path="phone" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>是否顯示電話</th>
					<td>
						<form:radiobuttons path="show_phone" items="${showPhoneOption}"/>
						<form:errors path="show_phone" cssClass="error"/>						
					</td>
				</tr>
			
			
				<tr>
					<th scope="row">簡介(PC)<br/>(最多150字)</th>
					<td>
						<form:textarea path="introduce_pc" rows="10" cols="20" maxlength="100" style="margin: 0px; width: 400px; height: 300px;"/>
						<form:errors path="introduce_pc" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row">簡介(APP)<br/>(最多150字)</th>
					<td>
						<form:textarea path="introduce_app" rows="10" cols="20" maxlength="100" style="margin: 0px; width: 400px; height: 300px;"/>
						<form:errors path="introduce_app" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>圖片<br/>(圖片尺寸 320*320)</th>
					<td>
						<form:hidden path="image"/>
						<c:if test="${not empty command.image}">
							<img src="<c:out value="${imagePath}"/><c:out value="${command.image}"/>" width="320" height="320"/>
						</c:if>
						<br/>
						
						<input type="file" name="img_file" accept=".jpg,.gif,.png" />
						<form:errors path="image" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>行動裝置圖片<br/>(圖片尺寸 160 * 160 )</th>
					<td>
						<form:hidden path="image_app"/>
						<c:if test="${not empty command.image_app}">
							<img src="<c:out value="${imagePath}"/><c:out value="${command.image_app}"/>" width="160" height="160"/>
						</c:if>
						<br/>
						<input type="file" name="image_app_file" accept=".jpg,.gif,.png" />
						<form:errors path="image_app" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row">TOP27用圖片<br/>(圖片尺寸 215 * 110 )</th>
					<td>
						<form:hidden path="top27_image"/>
						<c:if test="${not empty command.top27_image}">
							<img src="<c:out value="${imagePath}"/><c:out value="${command.top27_image}"/>" width="215" height="110"/>
						</c:if>
						<br/>
						<input type="file" name="top27_image_file" accept=".jpg,.gif,.png" />
						<form:errors path="top27_image" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>基本兌點方式(PC)<br/>(最多150字)</th>
					<td>
						<form:textarea path="basic_redemption_pc" rows="10" cols="20" maxlength="100" style="margin: 0px; width: 400px; height: 300px;"/>
						<form:errors path="basic_redemption_pc" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>基本兌點方式(APP)<br/>(最多150字)</th>
					<td>
						<form:textarea path="basic_redemption_app" rows="10" cols="20" maxlength="100" style="margin: 0px; width: 400px; height: 300px;"/>
						<form:errors path="basic_redemption_app" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>是否呈現</th>
					<td>
						<form:radiobuttons path="is_show" items="${isShowOption}"/>
						<form:errors path="is_show" cssClass="error"/>						
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>手機條碼哪裡用</th>
					<td>
						<form:radiobuttons path="mobile_phone_barcode" items="${mobilePhoneBarcodeOption}"/>
						<form:errors path="mobile_phone_barcode" cssClass="error"/>						
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="red">※</span>合約起訖日期</th>
					<td>
						<form:input path="contract_start_time" />
						<form:errors path="contract_start_time" cssClass="error"/>
						<h*>~</h*>
						<form:input path="contract_end_time" />
						<form:errors path="contract_end_time" cssClass="error"/>
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
					<th scope="row">是否顯示連結網址</th>
					<td>
						<form:radiobuttons path="show_link" items="${showLinkOption}"/>
						<form:errors path="show_link" cssClass="error"/>						
					</td>
				</tr>
								
				<tr>
					<th scope="row"><span class="red">※</span>連結開啟方式</th>
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
	
	$('#twzipcode').twzipcode({
	});


	$("#contract_start_time").datepicker(
			{
				showSecond: false,
				changeYear : true,
				changeMonth : true,				
				currentText: '現在',
				closeText: '完成',
				showOn : 'both',
				dateFormat: 'yy-mm-dd',
				buttonImage : '<c:out value="${resourcesPath}"/>admin/images/calendar.jpg'
			});
	$("#contract_end_time").datepicker(
			{
				showSecond: false,
				changeYear : true,
				changeMonth : true,				
				currentText: '現在',
				closeText: '完成',
				showOn : 'both',
				dateFormat: 'yy-mm-dd',
				buttonImage : '<c:out value="${resourcesPath}"/>admin/images/calendar.jpg'
			});
	</script>
</div>
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/tinymce/tinymce.min.js"></script>
<script>var tinymce_selector = "#description";</script>
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>admin/js/tinymce/tinymce.custom.js"></script>