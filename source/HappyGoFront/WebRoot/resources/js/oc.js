// 問與答 開合
$(function(){ 
$(".octit").next().hide();
$(".octit").click(function(){
	
	if($(this).next().is(":hidden")){
		$(this).next().slideDown();
		$(this).children().removeClass("open");
		$(this).children().addClass("close");
	}else{
		$(this).next().slideUp();
		$(this).children().removeClass("close");
		$(this).children().addClass("open");
	}
});
});
