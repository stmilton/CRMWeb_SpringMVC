<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include.jsp"%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1; maximum-scale=1; minimum-scale=1; user-scalable=no;">
<meta name="format-detection" content="telephone=no" />
<%-- <title><c:out value="${projectName}" /></title> --%>
<%-- <link rel="shortcut icon" href="<c:out value="${resourcesPath}"/>ico/favicon.ico" type="image/vnd.microsoft.icon" /> --%>
<%-- <link rel="icon" href="<c:out value="${resourcesPath}"/>ico/favicon.ico" type="image/vnd.microsoft.icon" /> --%>
<link rel="stylesheet" href="<c:out value="${resourcesPath}"/>css/common_new.css" />
<link rel="stylesheet" type="text/css" href="<c:out value="${resourcesPath}"/>css/slick_new.css"/>
<link rel="stylesheet" media="only screen and (min-width: 604px) and (max-width: 999px)" href="<c:out value="${resourcesPath}"/>css/pad_new.css">
<link rel="stylesheet" media="only screen and (max-width: 603px)" href="<c:out value="${resourcesPath}"/>css/m-index_new.css">
<link rel="stylesheet" href="<c:out value="${resourcesPath}"/>css/pushy_new.css">
<!-- 輪播效果-->
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>js/jquery.min.js"></script>
<!-- 選單 -->
<script src="<c:out value="${resourcesPath}"/>js/menu.js"></script>
<!-- 時鐘-->
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>js/jquery.plugin.min.js"></script>
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>js/jquery.countdown.js"></script>

<!-- 小網選單 -->
<script src="<c:out value="${resourcesPath}"/>js/pushy.min.js"></script>
</head>
<body>
<div id="slickbox">
	<div class="inner">
		<div class="topbox">
			<div class="variable-width">
			
				<!-- 黃金大Banner -->
				<ul>
					<c:forEach items="${bigBannerList}" var="bigBannerModel" varStatus="st">
						<li>
							<c:if test="${not empty bigBannerModel.image}">
								<img src="<c:out value="${bigBannerImagePath}"/><c:out value="${bigBannerModel.image}"/>" alt="" border="0" width="720" height="400"/>
							</c:if>
						
						</li>
					</c:forEach>
					
<!-- 					<li> -->
<!-- 						<div class="video-container"> -->
<!-- 							<iframe width="720" height="400" -->
<!-- 								src="https://www.youtube.com/embed/LokvcieQpjw?rel=0" -->
<!-- 								frameborder="0" allowfullscreen></iframe> -->
<!-- 						</div> -->
<!-- 					</li> -->
				</ul>
			</div>
		</div>
		<div class="loginbox">
			<div class="main">
				<h3>
					XXX<span>您好：</span>
				</h3>
				<div class="member_info">
					<ul>
						<li>您現有點數<span>XXX</span>點
						</li>
						<li>您收藏的優惠將於<br> <span>3</span>天內到期
						</li>
					</ul>
				</div>
				<div class="member_gift">
					<ul>
						<li>
							<div class="pic">
								<img src="<c:out value="${resourcesPath}"/>img/p11.png" alt="" border="0">
							</div>
							<p>
								建議兌換商品<br> 7-11 <span>20元現金抵用券</span>
							</p>
						</li>
						<li>
							<div class="pic">
								<img src="<c:out value="${resourcesPath}"/>img/p11.png" alt="" border="0">
							</div>
							<p>
								建議兌換商品<br> 7-11 <span>20元現金抵用券</span>
							</p>
						</li>
						<li>
							<div class="pic">
								<img src="<c:out value="${resourcesPath}"/>img/p11.png" alt="" border="0">
							</div>
							<p>
								建議兌換商品<br> 7-11 <span>20元現金抵用券</span>
							</p>
						</li>
					</ul>
				</div>
			</div>
			<div class="main" style="display: none;">
				<h2>HAPPYGO卡友</h2>
				<div class="login_bt">
					<a href="#">手機門號<br> 登入
					</a>
				</div>
				<ul>
					<li><a href="#">手機門號首次登入</a></li>
					<li><a href="#">忘記登記門號</a></li>
					<li><a href="#">忘記密碼</a></li>
				</ul>
				<!--<div class="member_gift2">
              <div class="pic"><img src="img/p11.png" alt="" border="0"></div>
              <p>建議兌換商品<br>
                7-11 <span>20元現金抵用券</span></p>
        </div>-->
			</div>
			<ul class="login_other">
				<li class="l1"><a href="#">點數查詢</a></li>
				<li class="l2"><a href="#">資料更新</a></li>
				<li class="l3"><a href="#">收藏優惠</a></li>
			</ul>
		</div>
	</div>
</div>
<div id="inContainer">
  <div class="boxarea">
  	
	<!-- 跑馬燈 -->
	<div class="marquee m-none" >
		<marquee scrollamount=8 >
			<c:forEach items="${marqueeList}" var="marqueeModel" varStatus="st">
			
				<c:if test="${marqueeModel.open_method == 0}">
					<a href="${marqueeModel.link}" style="color:red;"><c:out value="${marqueeModel.name}"/> </a>
					
				</c:if>
				<c:if test="${marqueeModel.open_method == 1}">
					<a href="${marqueeModel.link}" target=_blank style="color:red;"><c:out value="${marqueeModel.name}"/> </a>
				</c:if>				
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
		</marquee>
	</div>
	  
<!--     <div class="marquee m-none">六月份景氣消費觀測，只要花少許時間填寫我們的問卷，提供您寶貴的意見六月份景氣消費觀測，只要花少許時間填寫我們的問卷，提供您寶貴的意見...</div> -->
    <div class="box">
      <div class="title">
        <h2><img src="<c:out value="${resourcesPath}"/>images/icon_1n.png" />優惠推薦 </h2>
      </div>
      <div class="block">
        <div class="slider responsive">
          <ul class="product">
                    
			<!-- 優惠券Banner -->
          	<c:forEach items="${couponList}" var="couponModel" varStatus="st">	
          		<li class="imgSet">
          			<c:if test="${not empty couponModel.image}">
          			
          				<h3 class="tt_g"><a href="${couponModel.link}">${couponModel.ad_name}</a></h3>          				
						<div class="pic"><a href="${couponModel.link}"><img src="<c:out value="${couponImagePath}"/><c:out value="${couponModel.image}"/>" width="225" height="225" /></a></div>
					</c:if>
				</li>
			</c:forEach>
          
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="toparea">
    <div class="box_s cardSet">
      <ul class="grid" id="grid">
		<!-- 左側大正方 -->
		<li class="box_b">		
			<c:forEach items="${leftSquareList}" var="leftSquareModel" varStatus="st">				
				<c:if test="${not empty leftSquareModel.image}">
					<a href="#">
					<img src="<c:out value="${leftSquareImagePath}"/><c:out value="${leftSquareModel.image}"/>" />
					</a>
				</c:if>
			</c:forEach>
        </li>
        
        <!-- 右側小正方 -->
        <c:forEach items="${rightSquareList}" var="rightSquareModel" varStatus="st">
	        <li><a href="#">		        
			       	<img src="<c:out value="${rightSquareImagePath}"/><c:out value="${rightSquareModel.image}"/>" width="190" height="190"/>
			</a></li>
		</c:forEach>
      </ul>
      
      <!-- 長型廣告 -->
      <c:forEach items="${longAdsList}" var="longAdsModel" varStatus="st">
	      <div class="banner">	      
	          <a href="#">		        
	     	  	  <img src="<c:out value="${longAdsImagePath}"/><c:out value="${longAdsModel.image}"/>" width="600" height="170"/>
			  </a>
		  </div>
	      <div class="clr"></div>
      </c:forEach>
           
<%--       <div class="banner"><a href="#"><img src="<c:out value="${resourcesPath}"/>img/600x170.jpg" width="600" height="170" /></a></div> --%>
<!--       <div class="clr"></div> -->
    </div>
    <!--<div class="box_s">
        <ul class="grid" id="grid">
          <li class="box_b"><a href="#"><img src="img/big300.jpg"></a></li>
          <li>
            <div class="pic"><a href="#"><img src="img/s180_1.jpg" width="180" height="180" /></a></div>
            <h3><a href="#">卡友權益</a></h3>
          </li>
          <li>
            <div class="pic"><a href="#"><img src="img/s180_2.jpg" width="180" height="180" /></a></div>
            <h3><a href="#">點數查詢</a></h3>
          </li>
          <li>
            <div class="pic"><a href="#"><img src="img/s180_3.jpg" width="180" height="180" /></a></div>
            <h3><a href="#">熱門活動</a></h3>
          </li>
        </ul>
        <div class="banner"><a href="#"><img src="img/banner.jpg" width="584" height="138" /></a></div>
        <div class="clr"></div>
      </div>--> 
  </div>
  <div class="boxarea">
    <div class="box">
      <div class="title">
        <h2><img src="<c:out value="${resourcesPath}"/>images/icon_2n.png" />開心購物</h2>
      </div>
      <div class="block">
        <ul class="prodpic">
          <li>
            <div class="pic"><img src="<c:out value="${resourcesPath}"/>img/h100_1.png" width="325" height="100"></div>
          </li>
          <li>
            <div class="pic"><img src="<c:out value="${resourcesPath}"/>img/h100_2.png" width="325" height="100"></div>
          </li>
          <li>
            <div class="pic"><img src="<c:out value="${resourcesPath}"/>img/h100_3.png" width="325" height="100"></div>
          </li>
        </ul>
        <div class="clr"></div>
      </div>
    </div>
  </div>
</div>
<div class="ads"><a href="#"><img src="<c:out value="${resourcesPath}"/>img/ads_300x250.jpg"></a></div>

<div class="m-none">
  <div id="adbox"><img src="<c:out value="${resourcesPath}"/>images/ad.png" width="90" height="300"></div>
</div>
<!-- slick大圖輪播--> 
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>js/slick.min.js"></script> 
<script type="text/javascript" src="<c:out value="${resourcesPath}"/>js/slick.min.user.js"></script> 
<!-- 其它效果-->
</body>
</html>