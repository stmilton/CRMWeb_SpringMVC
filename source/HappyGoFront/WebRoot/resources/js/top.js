$(function(){
    $(window).scroll(function() {
        if ( $(this).scrollTop() > 300){
            $('#topbtn').fadeIn("fast");
        } else {
            $('#topbtn').stop().fadeOut("fast");
        }
    });
    window.onscroll=function(){
        var sHeight=document.documentElement.scrollTop||document.body.scrollTop;//滚动高度
        var wHeight=document.documentElement.clientHeight;//window 
        var dHeight=document.documentElement.offsetHeight;//整个文档高度
        if(dHeight-(sHeight+wHeight)<150)
        {
            /*$('#topbtn').stop().fadeOut("fast");*/
            /*document.getElementById("topbtn").style.position = "absolute";*/
            /*document.getElementById("topbtn").style.bottom = "300px";
        } else {
            document.getElementById("topbtn").style.bottom = "50px";*/
        }
    };
});