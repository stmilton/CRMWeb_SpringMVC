<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!doctype html>
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width; initial-scale=1; maximum-scale=1; minimum-scale=1; user-scalable=no;">
<meta name="format-detection" content="telephone=no" />
<title><c:out value="${projectName}" /></title>

<link rel="shortcut icon" href="<c:out value="${resourcesPath}"/>ico/favicon.ico"
	type="image/vnd.microsoft.icon" />
<link rel="icon" href="<c:out value="${resourcesPath}"/>ico/favicon.ico" type="image/vnd.microsoft.icon" />
<link rel="stylesheet"
	href="<c:out value="${resourcesPath}"/>css/common_new.css" />
<link rel="stylesheet" type="text/css"
	href="<c:out value="${resourcesPath}"/>css/slick_new.css" />
<link rel="stylesheet"
	media="only screen and (min-width: 604px) and (max-width: 999px)"
	href="<c:out value="${resourcesPath}"/>css/pad_new.css">
<link rel="stylesheet" media="only screen and (max-width: 603px)"
	href="<c:out value="${resourcesPath}"/>css/m-index_new.css">
<link rel="stylesheet"
	href="<c:out value="${resourcesPath}"/>css/pushy_new.css">

<!-- 輪播效果-->
<script type="text/javascript"
	src="<c:out value="${resourcesPath}"/>js/jquery.min.js"></script>
<!-- 選單 -->
<script src="<c:out value="${resourcesPath}"/>js/menu.js"></script>
<!-- 時鐘-->
<script type="text/javascript"
	src="<c:out value="${resourcesPath}"/>js/jquery.plugin.min.js"></script>
<script type="text/javascript"
	src="<c:out value="${resourcesPath}"/>js/jquery.countdown.js"></script>

<!-- 小網選單 -->
<script src="<c:out value="${resourcesPath}"/>js/pushy.min.js"></script>

</head>
<body>

	<!-- Pushy Menu -->
	<nav class="pushy pushy-left">
		<div class="findarea p-block">
			<div class="findbox type3">
				<ul>
					<li class="findtext"><input type="text" value=""
						placeholder="請輸入關鍵字"></li>
					<li class="findselect"><select name="select" id="select">
							<option>特約商店</option>
							<option>熱門活動</option>
					</select></li>
					<li class="findpush"><input type="button" value="" class="btn">
					</li>
				</ul>
			</div>
		</div>
		<div class="clr"></div>
		<ul>
			<li><a href="#"><img
					src="<c:out value="${resourcesPath}"/>images/phone/icon_1.png">我的專區</a>
				<div class="submenu">
					<ul>
						<li><a href="#">現有點數</a>
							<p>
								<span>333</span> 點
							</p></li>
						<li><a href="#">建議兌換商品</a>
							<p>
								<a href="#"><span>ibon中杯熱拿鐵ibon中杯熱拿鐵</span></a>
							</p></li>
						<li><a href="#">收藏則數</a>
							<p>
								<a href="#"><span>6</span> 則</a>
							</p></li>
					</ul>
				</div></li>
			<li><a href="#"><img
					src="<c:out value="${resourcesPath}"/>images/phone/icon_2.png">回首頁</a></li>
			<li class="main active"><a href="#">卡友好康</a>
				<div class="submenu">
					<ul>
						<li><a href="#">卡友好康首頁</a></li>
						<li><a href="#">集點活動</a></li>
						<li><a href="#">兌點活動</a></li>
						<li><a href="#">持卡優惠</a></li>
						<li><a href="#">免費得點</a></li>
						<li><a href="#">主題活動</a></li>
						<li><a href="#">活動公告</a></li>
					</ul>
				</div></li>
			<li class="main"><a href="#">卡友中心</a></li>
			<li class="main"><a href="#">主題活動</a></li>
			<li class="main"><a href="#">愛心捐點</a></li>
			<li class="main"><a href="#">快樂購特約商</a></li>
			<li class="main"><a href="#">快樂購卡介紹</a></li>
			<li class="main"><a href="#">客服園地</a></li>
			<li class="main active"><a href="#">相關連結</a>
				<div class="submenu">
					<ul>
						<li><a href="#">關於HAPPY GO</a></li>
						<li><a href="#">快樂購卡友權益需知</a></li>
						<li><a href="#">智慧財產權保護聲明</a></li>
						<li class="active"><a href="#">隱私權政策</a></li>
						<li><a href="#">快樂購卡特約商服務</a></li>
						<li><a href="#">網站友好夥伴</a></li>
					</ul>
				</div></li>
			<li><a href="#">加入粉絲團</a></li>
			<li><a href="#">快去下載APP</a></li>
			<li><a href="#">線上申辦</a></li>
		</ul>
	</nav>

	<!-- Site Overlay -->
	<div class="site-overlay"></div>
	<!-- 手機版測邊選單 -->
	<div class="m-header">
		<div class="menu-btn"></div>
		<div class="logo"></div>
		<!--<div class="login-btn"></div>-->
		<div class="logout-btn"></div>
	</div>
	<div class="top">
		<div class="topnav">
			<!-- Header -->
			<ul>
				<c:forEach items="${headerList}" var="headerModel" varStatus="st">
					<c:if test="${headerModel.open_method == 0}">
						<li><a href="${headerModel.link}">${headerModel.name}</a></li>
					</c:if>
					<c:if test="${headerModel.open_method == 1}">
						<li><a href="${headerModel.link}" target=_blank>${headerModel.name}</a></li>
					</c:if>

				</c:forEach>

			</ul>
			<div class="top_fb">
				<img src="<c:out value="${resourcesPath}"/>images/fb-like.png">
			</div>
			<div class="top_fbAdd">
				<a href="#">加入粉絲團</a>
			</div>
			<div class="top_appDl">
				<a href="#">快去下載APP！</a>
			</div>
		</div>
	</div>
	<div class="header">
		<div class="com">
			<div class="logo">
				<a href="#"><img
					src="<c:out value="${resourcesPath}"/>images/logo.png"
					alt="HAPPY GO"></a>
			</div>
			<div class="searchArea">
				<span class="keywords">熱門： <a href="#">跨年集點趣</a>、<a href="#">點數變現金</a></span>
				<div class="search">
					<input class="query-input" id="nav-search-input"
						placeholder="請輸入查詢關鍵字" /> <select>
						<option>特約商店</option>
						<option>熱門活動</option>
					</select>
					<div class="search_btn">
						<a href="#"></a>
					</div>
				</div>
			</div>
			<div class="clock">
				<div class="clocktit">點數優惠兌換倒數</div>
				<div id="clock">
					<a data-type="countdown" data-id="3386560" class="tickcounter"
						style="display: block; width: 100%; position: relative; padding-bottom: 25%"
						title="Countdown" href="//www.tickcounter.com/">Countdown</a>
					<script>
						(function(d, s, id) {
							var js, pjs = d.getElementsByTagName(s)[0];
							if (d.getElementById(id))
								return;
							js = d.createElement(s);
							js.id = id;
							js.src = "//www.tickcounter.com/static/js/loader.js";
							pjs.parentNode.insertBefore(js, pjs);
						}(document, "script", "tickcounter-sdk"));
					</script>
				</div>
			</div>
		</div>
		<div id="abgne-block">
			<div class="inner">
				<ul class="tabs">
					<!-- 主選單 -->
					<c:forEach items="${majorFunctionList}" var="majorFunctionModel"
						varStatus="st">
						<!-- 如果類型為"次選單" -->
						<c:if test="${majorFunctionModel.type == 0}">
							<li>
								<!-- 如果顯示icon為"NEW" --> <c:if
									test="${majorFunctionModel.show_icon == 1}">
									<div class="new"></div>
								</c:if> <!-- 如果顯示icon為"HOT" --> <c:if
									test="${majorFunctionModel.show_icon == 2}">
									<div class="hot"></div>
								</c:if> <a href="#"
								style="color:${majorFunctionModel.color}; caret-color:${majorFunctionModel.cursor_color}">${majorFunctionModel.name}</a>
							</li>
						</c:if>

						<!-- 如果類型為"連結" -->
						<c:if test="${majorFunctionModel.type == 1}">
							<li>
								<!-- 如果顯示icon為"NEW" --> <c:if
									test="${majorFunctionModel.show_icon == 1}">
									<div class="new"></div>
								</c:if> <!-- 如果顯示icon為"HOT" --> <c:if
									test="${majorFunctionModel.show_icon == 2}">
									<div class="hot"></div>
								</c:if> <a
								style="color:${majorFunctionModel.color}; caret-color:${majorFunctionModel.cursor_color}"
								href="${majorFunctionModel.link}">${majorFunctionModel.name}</a>
							</li>
						</c:if>
					</c:forEach>

				</ul>
				<!-- 次功能選單 -->
				<div class="tab_container">
					<ul class="tab_content">
						<c:forEach items="${majorFunctionList}" var="majorFunctionModel"
							varStatus="st">
							<li class="m01">
								<div class="subnavbox">
									<div class="mainL">
										<div class="fL">
											<img
												src="<c:out value="${majorFunctionImagePath}"/><c:out value="${majorFunctionModel.image}"/>"
												width="186" height="186">
										</div>
										<h2>${majorFunctionModel.ad_name}</h2>
										<p>${majorFunctionModel.ad_proposal}</p>
										<input type="button" value="更多詳情">
									</div>
									<div class="menuR">
										<c:forEach items="${majorFunctionModel.minorFunctions}"
											var="minorFunctionModel" varStatus="st">
											<div>
												<!-- 判斷另開視窗 -->
												<c:if test="${minorFunctionModel.open_method == 0}">
													<a href="${minorFunctionModel.link}">${minorFunctionModel.name}</a>
												</c:if>
												<c:if test="${minorFunctionModel.open_method == 1}">
													<a href="${minorFunctionModel.link}" target=_blank>${minorFunctionModel.name}</a>
												</c:if>
											</div>
										</c:forEach>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<decorator:body />

	<div class="footer">

		<div class="wm-auto">
			<div class="f-linkbox m-none">
				<ul>
					<li class="l1"><a href="#">答客問</a></li>
					<li class="l2"><a href="#">客服信箱</a></li>
					<li class="l3"><a href="#">語音客服專線</a></li>
					<li class="l4"><a href="#">HAPPY GO線上通</a></li>
				</ul>
			</div>
		</div>
		<div class="f-subnav m-none">
			<ul>
				<c:forEach items="${footerList}" var="footerModel" varStatus="st">

					<c:if test="${footerModel.open_method == 0}">
						<li><a href="${footerModel.link}">${footerModel.name}</a></li>
					</c:if>
					<c:if test="${footerModel.open_method == 1}">
						<li><a href="${footerModel.link}" target=_blank>${footerModel.name}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<div class="wm-auto">
			<div class="copybox clr">
				<p>本網站刊登之所有特約商促銷訊息，均以特約商門市公告為主，若有調整，恕不另行通知，歡迎卡友告知刊登調整！</p>
				<p class="copyright">鼎鼎聯合行銷(股)公司版權所有 Copyright 2017 All Right
					Reserved by DDIM</p>
				<div class="logo">
					<a href="#"></a>
				</div>
			</div>
		</div>
		<!-- wm-auto -->
	</div>


</body>

</html>
