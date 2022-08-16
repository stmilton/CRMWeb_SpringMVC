//首頁 imgSet區塊 hover效果
		if (Modernizr.touch) {
            $(".imgSet").click(function(e){
                if (!$(this).hasClass("hover")) {
                    $(this).addClass("hover");
                }
            });
        } else {
            $(".imgSet").mouseenter(function(){
                $(this).addClass("hover");
            })
            .mouseleave(function(){
                $(this).removeClass("hover");
            });
        }
		
//首頁 點數快換/推薦活動 滑動效果
	$(window).scroll( function(){
        $('.block_effect').each( function(i){
            var bottom_of_object = $(this).position().top + 50;
            var bottom_of_window = $(window).scrollTop() + $(window).height();
            if( bottom_of_window > bottom_of_object ){
                $(this).animate({'opacity':'1', 'left':'0'},{duration:500},100);
            }
        });
	}); 
//右側滑動廣告
$(function(){
    $(window).scroll(function() {
        if ( $(this).scrollTop() > 300){
            $('#adbox').fadeIn("fast");
        } else {
            $('#adbox').stop().fadeOut("fast");
        }
    });
});
