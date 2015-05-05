// JavaScript Document
function getStyle(obj, attr) {
	if ( obj.currentStyle ) return obj.currentStyle[attr];
	return getComputedStyle(obj, false)[attr];
}
function catchShaDow(s) {
	s = s.replace(/rgb\(.*\)\s/g, '');
	s = s.split(' ');
	return s[2];
}

function startMove(obj, json, common, fn) {
	clearInterval(common.timer);
	for (var attr in json) {
		if ( parseInt(getStyle(obj, attr)) == json[attr] ) {
			delete json[attr];
		}
	}
	common.sum = 0;
	common.iSpeed = 0;
	common.timer = setInterval(function() {
		var bStop = true;	
		for (var attr in json) {
			var iCur = parseInt(getStyle(obj, attr));
			common.iSpeed += (json[attr]-iCur)/5;
			common.iSpeed *= 0.7;			
			common.sum = iCur+common.iSpeed;									
			if ( Math.abs(common.iSpeed) >= 1 || Math.abs(common.sum-json[attr]) >= 1 )	{
				bStop = false;	
				obj.style[attr] = common.sum+'px';			
			}else {
				obj.style[attr] = json[attr]+'px';
				clearInterval(common.timer);
			}
		}
		if ( bStop ) {	
			clearInterval(common.timer);
			if ( fn ) fn();
		}
	}, 30);
}

function bufferMove(obj, json, fn) {
	clearInterval(obj.timer);
	obj.timer = setInterval(function() {
		var bStop = true;
		for (var attr in json) {
			var iCur = 0;
			if ( attr == "boxShadow" ) {
				var s = getStyle(obj, 'webkitBoxShadow');
				if ( s == 'none' || !s ) {
					s = getStyle(obj, 'boxShadow');					
				}
				if ( s == 'none' || !s ) s = getStyle(obj,'mozBoxShadow');
				if ( s == 'none' || !s ) {
					iCur = 0;
				}
				else iCur = parseInt(catchShaDow(s));
			} else if ( attr == 'opacity' ) {
				iCur = parseInt(parseFloat(getStyle(obj,attr))*100);
			}else iCur = parseInt(getStyle(obj, attr));
			var iSpeed = (json[attr]-iCur)/8;
			//alert(json[attr] + " | " + iCur + " | " + iSpeed);
			iSpeed = iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
			if ( iCur != json[attr] )	bStop = false;
			if ( attr == 'opacity' ) {
				obj.style.filter='alpha(opacity:'+(iCur+iSpeed)+')';
				obj.style.opacity=(iCur+iSpeed)/100;
			}else {
				if ( attr == "boxShadow" ) {					
					obj.style.webkitBoxShadow = "rgb(204, 204, 204) 0px 0px "+(iCur+iSpeed)+"px 0px";
					obj.style.mozBoxShadow = "rgb(204, 204, 204) 0px 0px "+(iCur+iSpeed)+"px 0px"
					obj.style.boxShadow = "rgb(204, 204, 204) 0px 0px "+(iCur+iSpeed)+"px 0px"								
				}else obj.style[attr] = (iCur+iSpeed)+'px';
			}
		}
		if ( bStop ) {
			clearInterval(obj.timer);	
			if ( fn ) fn();
		}
	}, 30);
}

function classLoader(oParent, cls) {
	var aResult = [];
	var aObj = oParent.getElementsByTagName("*");
	for (var i = 0; i < aObj.length; i ++) {
		if ( aObj[i].className == cls ) {			
			aResult.push(aObj[i]);
		}
	}
	return aResult;
}