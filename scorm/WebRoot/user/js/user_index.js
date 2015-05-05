// JavaScript Document

window.onload = function() {
	
	// 顶部nav
	var oNav = document.getElementById('nav') ;
	var aNav = oNav.getElementsByTagName('div') ;
	var oMenu = document.getElementById('menu') ;
	var aMenu = oMenu.getElementsByTagName('div') ;
	var timer ;
	
	for( var i = 0 ; i < aNav.length ; i ++ ) {
		
		aNav[i].index = i ;
		aNav[i].onmouseover = function() {
			
			clearTimeout(timer) ;
			for( var j = 0 ; j < aNav.length ; j ++ ) {
				aNav[j].className = '' ;
				if( j < aNav.length - 1 ) aMenu[j].className = '' ;
			}
			this.className = 'active' ;
			if( this.index != 0 ) {
				aMenu[this.index - 1].className = 'menu_active' ;
			}
		}
		
		aNav[i].onmouseout = function() {
			
			timer = setTimeout(function(){
				for( var j = 0 ; j < aNav.length ; j ++ ) {
					if( j < aNav.length - 1 ) aMenu[j].className = '' ;
				}
			}, 800) ;
		}
	}
	
	// 1级菜单
	var aList = oMenu.getElementsByTagName('span') ;
	for( var i = 0 ; i < aList.length ; i ++ ) {
	
		aList[i].onmouseover = function() {
			clearTimeout(timer) ;
			this.style.color = "#3CC" ;
		}	
		
		aList[i].onmouseout = function() {
			this.style.color = "#000" ;	
			timer = setTimeout(function(){
				for( var j = 0 ; j < aNav.length ; j ++ ) {
					if( j < aNav.length - 1 ) aMenu[j].className = '' ;
				}
			}, 800) ;
		}
	}
	
	//
	// nav 导航条
	// 
	var oNav1 = document.getElementById('nav1') ;
	var aList1 = oNav1.getElementsByTagName('div') ;
	var aContent = new Array(
		document.getElementById('nav1_content1'),
		document.getElementById('nav1_content2'),
		document.getElementById('nav1_content3'),
		document.getElementById('nav1_content4'),
		document.getElementById('nav1_content5')
	) ;
	
	for( var i = 0 ; i < aList1.length ; i ++ ) {
		aList1[i].index = i ;
		
		aList1[i].onmouseover = function() {
			for( var j = 0 ; j < aList1.length ; j ++ ) {
				if( aList1[j].className != 'nav_selected' ) {
					aList1[j].className = '' ;
				}
			}
			if( this.className != 'nav_selected' ) {
				this.className = 'nav_moved' ;
			}
		}
		
		aList1[i].onmouseout = function() {
			for( var j = 0 ; j < aList1.length ; j ++ ) {
				if( aList1[j].className != 'nav_selected' ) {
					aList1[j].className = '' ;
				}
			}
		}
		
		aList1[i].onclick = function() {
			for( var j = 0 ; j < aList1.length ; j ++ ) {
				aList1[j].className = '' ;
				aContent[j].className = 'display_none' ;
				
			}
			this.className = 'nav_selected' ;
			aContent[this.index].className = 'display_blcok' ;
		}
	}
	
	//
	//	我的学习任务事件
	//
	var oStudy = document.getElementById('_user_study') ;
	var oSpa = oStudy.getElementsByTagName('span')[0] ;
	var oContent = document.getElementById('_user_study_content') ;
	
	oStudy.onmouseover = function() {
		oSpa.style.color = "#09c" ;
	}
	oStudy.onmouseout = function() {
		oSpa.style.color = "#000" ;
	}
	
	oStudy.onclick = function() {
		if( oContent.className != "user_study_content display_block" ) {
			oContent.className = "user_study_content display_block" ;	
		} else {
			oContent.className = "user_study_content display_none" ;
		}
	}
	
	//
	//	nav2
	//
	var oNav2 = document.getElementById('nav2') ;
	var aList2 = oNav2.getElementsByTagName('div') ;
	var aContent2 = new Array(
		document.getElementById('nav2_content1'),
		document.getElementById('nav2_content2'),
		document.getElementById('nav2_content3'),
		document.getElementById('nav2_content4')
	) ;
	for( var i = 0 ; i < aList2.length ; i ++ ) {
		aList2[i].index = i ;
		aList2[i].onmouseover = function() {
			
			for( var j = 0 ; j < aList2.length ; j ++ ) {
				if( aList2[j].className != 'nav_selected' ) {
					aList2[j].className = '' ;
				}
			}
			if( this.className != 'nav_selected' ) {
				this.className = 'nav_moved' ;
			}
		}
		
		aList2[i].onmouseout = function() {
			for( var j = 0 ; j < aList2.length ; j ++ ) {
				if( aList2[j].className != 'nav_selected' ) {
					aList2[j].className = '' ;
				}
			}
		}
		
		aList2[i].onclick = function() {
			for( var j = 0 ; j < aList2.length ; j ++ ) {
				aList2[j].className = '' ;
				aContent2[j].className = 'display_none'
			}
			this.className = 'nav_selected' ;
			aContent2[this.index].className = 'display_block' ;
		}
	}
	
	//
	// nav2 content
	//
	var nav2_content = document.getElementById('nav2_content').getElementsByTagName('div') ;
	for( i = 0 ; i < nav2_content.length; i ++ ) {
		overAndOut(nav2_content[i]) ;
	}
	function overAndOut(content) {
		var aDiv = content.getElementsByTagName('div') ;
		for( var j = 0 ; j < aDiv.length ; j ++ ) {
			
			aDiv[j].onmouseover = function() {
				this.style.background = 'url(image/nav2_content_mouseover.jpg) repeat' ;
			}
			
			aDiv[j].onmouseout = function() {
				this.style.background = '' ;
			}
		}
	}
	
	//
	// nav3
	//
	var nav3 = document.getElementById('nav3') ;
	var aDiv = nav3.getElementsByTagName('div') ;
	var aContent3 = new Array(
		document.getElementById('nav3_content1'),
		document.getElementById('nav3_content2'),
		document.getElementById('nav3_content3')
	) ;
	for( i = 0 ; i < aDiv.length; i ++ ) {
		aDiv[i].index = i ;
		aDiv[i].onmouseover = function() {
			
			for( var j = 0 ; j < aDiv.length ; j ++ ) {
				if( aDiv[j].className != 'nav_selected' ) {
					aDiv[j].className = '' ;
				}
			}
			if( this.className != 'nav_selected' ) {
				this.className = 'nav_moved' ;
			}
		}
		
		aDiv[i].onmouseout = function() {
			for( var j = 0 ; j < aDiv.length ; j ++ ) {
				if( aDiv[j].className != 'nav_selected' ) {
					aDiv[j].className = '' ;
				}
			}
		}
		
		aDiv[i].onclick = function() {
			for( var j = 0 ; j < aDiv.length ; j ++ ) {
				aDiv[j].className = '' ;
				aContent3[j].className = 'display_none'
			}
			this.className = 'nav_selected' ;
			aContent3[this.index].className = 'display_block' ;
		}
	}
	
	//
	// nav3 content
	//
	var nav3_div = document.getElementById('nav3_content').getElementsByTagName('div') ;
	for( i = 0 ; i < nav3_div.length ; i ++ ) {
		nav3_div[i].onmouseover = function() {
			this.style.background = "#F0F0F0" ;
		}
		
		nav3_div[i].onmouseout = function() {
			this.style.background = "#FFF" ;	
		}
	}
}