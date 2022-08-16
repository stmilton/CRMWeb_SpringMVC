
//首頁輪播 大網
$('.variable-width ul').slick({
  arrows:true,
  infinite: true,
  autoplay:false,
  autoplaySpeed: 6000,
  speed: 1000,
  slidesToShow: 1  
});
try {
    $('.variable-width ul').slick('slickPlay');
}catch(err) {
}
//首頁登入輪播 大網
$('.member_gift ul').slick({
  dots: true,
  arrows:false,
  infinite: true,
  autoplay:false,
  autoplaySpeed: 6000,
  speed: 1000,
  slidesToShow: 1  
});
try {
    $('.variable-width ul').slick('slickPlay');
}catch(err) {
}
//內頁輪播
$('.one-time').slick({
  dots: true,
  arrows:false,
  infinite: true,
  autoplay: false,
  speed: 800,
  slidesToShow: 1,
  adaptiveHeight: true
});
try {
    $('.one-time').slick('slickPlay');
}catch(err) {
}
//區塊左右觸碰滑動
$('.responsive ul').slick({
	dots:false,
  autoplay:false,
  infinite: true,
    draggable: false,
  touchMove: false,
  speed: 300,
  slidesToShow: 4,
  slidesToScroll: 1,
  responsive: [
{
      breakpoint: 603,
      settings: {
	dots:false,
  autoplay:false,
  infinite: true,
    draggable: false,
  touchMove: false,
        slidesToShow: 2,
        slidesToScroll:2
		
      }
    }
    // You can unslick at a given breakpoint now by adding:
    // settings: "unslick"
    // instead of a settings object
  ]
});

$('.cardSlider ul').slick({
  dots:false,
  autoplay:false,
  infinite: true,
  draggable: false,
  touchMove: false,
  speed: 300,
  slidesToShow: 3,
  slidesToScroll: 1,
  responsive: [
{
  breakpoint: 600,
  settings: {
  	touchMove: true,
    slidesToShow: 2
      }
    }
  ]
});

//首頁輪播 小網
$('.single-item').slick({
  dots: true,
  arrows:false,
  infinite: true,
  autoplay: false,
  speed: 1000,
  slidesToShow: 1,
  slidesToScroll: 1,
  adaptiveHeight: true
});
try {
    $('.single-item').slick('slickPlay');
}catch(err) {
}



