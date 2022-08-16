$(function(){
    $(window).scroll(function() {
        if ( $(this).scrollTop() > 300){
            $('#adbox').fadeIn("fast");
        } else {
            $('#adbox').stop().fadeOut("fast");
        }
    });
});