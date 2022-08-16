$(function(){
//側邊選單
$(".sideMenu li").click(function(){ 
	$(this).toggleClass('on');
	});
});



//popup close按鈕
$(function(){
$(".close a, .closeBtn").click(function(){ 
	$(".popBlock").addClass('off');
	});
	});