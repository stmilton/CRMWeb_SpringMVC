//大網選單效果
$(function(){ 
		var _default = 0, 
			$block = $('#abgne-block'), 
			$tabs = $block.find('.tabs'), 
			$tabsLi = $tabs.find('li'), 
			$tab_content = $block.find('.tab_content'), 
			$tab_contentLi = $tab_content.find('li'), 
			_width = $tab_content.width();
		//var timer = setInterval(hide, 500);
		var isOut = false;
		var isIn = false;
		var isMenu = false;
		var $this;
		$('#abgne-block li').on('click',function() {
			if ($(this).hasClass( "m8" )) {
				$tab_content.slidUp();
				slideUpHeaderMenu();
			}else{
				$tab_content.slideDown();
				slideDownHeaderMenu(); //slick banner must be stop.( RD callback function)
			}
		});
		
		function slideDownHeaderMenu() {
			if ($('.variable-width ul').length > 0) {
				try {
				    $('.variable-width ul').slick('slickPause');
				}catch(err) {
				}	
			}
			if ($(".one-time").length > 0) {
				try {
				    $('.one-time').slick('slickPause');
				}catch(err) {
				}
			}
		}
		/**
		$block.hover(function(){
			isIn = true;
			if(!$tab_content.is(":animated")){
				$tab_content.slideDown();
			}
		}, function() {
			isIn = false;
			if(!$tab_content.is(":animated")){
				$tab_content.slideUp();
			} else {
				isOut = true;
			}
		}, function(){});
		$tab_content.hover(function(){
			isMenu = false;
		});
		function hide(){
			if(isOut && !$tab_content.is(":animated")){
				if(isIn){
					isOut = false;
					isIn = false;
				} else {
					isOut = false;
					$tab_content.slideUp();
				}				
			}
		}
		function judgePosition($this){
			//alert(isMenu);
			if(isMenu){
				$this.toggleClass('hover');
				var $active = $tabsLi.filter('.active').removeClass('active'), 
					_activeIndex = $active.index(),  
					$this = $this.addClass('active').removeClass('hover'), 
					_index = $this.index(), 
					isNext = _index > _activeIndex;
				if(_activeIndex == _index) return;
				$tab_contentLi.eq(_activeIndex).stop().animate({
					left: isNext ? -_width : _width
				}).end().eq(_index).css('left', isNext ? _width : -_width).stop().animate({
					left: 0
				});
				
				isMenu = false;
			}
		}**/
		$("body").on('click touchstart', fun);
		function fun(evt) {
		    var target = $(evt.target);    
		    if ($(target).parents('#abgne-block').length > 0) {
		    } else {
		    	$tab_content.slideUp();
		    	slideUpHeaderMenu(); //slick banner must be play.( RD callback function)
		    }
		}
		$tabsLi.click(function(){
			var $this = $(this);
			if($this.hasClass('active')) return;
			/**
			if($tab_content.is(':visible')){				
				isMenu = true;
				setTimeout(function() { judgePosition($this) }, 200);
			} else {**/
				$this.toggleClass('hover');
				var $active = $tabsLi.filter('.active').removeClass('active'), 
					_activeIndex = $active.index(),  
					$this = $(this).addClass('active').removeClass('hover'), 
					_index = $this.index(), 
					isNext = _index > _activeIndex;
				if(_activeIndex == _index) return;
				$tab_contentLi.eq(_activeIndex).stop().animate({
					left: isNext ? -_width : _width
				}).end().eq(_index).css('left', isNext ? _width : -_width).stop().animate({
					left: 0
				});
			//}
		});
		$tabsLi.eq(_default).addClass('active');
		$tab_contentLi.eq(_default).siblings().css({
			left: _width
		});
	});
$.fn.isChildOf = function(element)
{
    return $(element).has(this).length > 0;
}