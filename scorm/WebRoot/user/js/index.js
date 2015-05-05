// JavaScript Document
window.onload = function() {
	
	// nav
	oNav = document.getElementById('nav');
	aDiv = oNav.getElementsByTagName('div') ;
	for( var i = 0 ; i < aDiv.length ; i ++ ) {
		
		aDiv[i].index = i ;
		aDiv[i].onmouseover = function() {
			
			if( this.index == 0 ) {
				aDiv[this.index].className = 'nav_first active' ;
			} else {
				aDiv[this.index].className = 'active' ;	
			}
		}
		aDiv[i].onmouseout = function() {
			
			if( this.index == 0 ) {
				aDiv[this.index].className = 'nav_first' ;
			} else {
				aDiv[this.index].className = '' ;
			}
		}
	}
	// img 图片滑动
	var oImg = document.getElementById('_main_img') ;
	var aSpa = oImg.getElementsByTagName('span');
	var oFoc = document.getElementById('_focus') ;
	var aDiv = oFoc.getElementsByTagName('div') ;
	var timer ;
	var currentFocus = 0 ;
	var maxImg = 5 ;
	var tmp = 0;
	for( var i = 0 ; i < aDiv.length ; i ++ ) {
		aDiv[i].index = i ;
		aDiv[i].onclick = function() {
			clearInterval(timer);
			
			this.className = 'onfocus' ;
			$(this).siblings(this).removeClass("onfocus") ;
			currentFocus = this.index ;
			if(tmp != this.index){
				tmp = this.index;
				$("#_main_img").animate({left:"-=800px"},function(){//动画效果：<ul>左移800px
					$("#_main_img").css({left:0}).find("span:first").appendTo("#_main_img");//改变<ul>left样式，把第一张图片放到最后
				});
			}
			timer = setInterval(doStart, 2000) ; 
		}
	}	
	// 图片随时间滑动
	function doStart() {
		currentFocus = (currentFocus + 1) % maxImg ;
		$(".focus div").eq(currentFocus).trigger("click");
	}
	
	timer = setInterval(doStart, 2000) ; 
	
	
	
}

