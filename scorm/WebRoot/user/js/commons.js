var userAgent = navigator.userAgent.toLowerCase();
var is_opera = userAgent.indexOf('opera') != -1 && opera.version();
var is_moz = (navigator.product == 'Gecko')
		&& userAgent.substr(userAgent.indexOf('firefox') + 8, 3);
var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera)
		&& userAgent.substr(userAgent.indexOf('msie') + 5, 3);
var is_safari = (userAgent.indexOf('webkit') != -1 || userAgent
		.indexOf('safari') != -1);

function G(id) {
	if (typeof (id) == "string") {
		return document.getElementById(id);
	}
	return id;
}

function LoadDialogWindow(URL, parent, loc_x, loc_y, width, height) {
	// alert(width+","+height);
	if (is_ie)// window.open(URL);
		window.showModalDialog(URL, parent,
				"edge:raised;scroll:1;status:0;help:0;resizable:1;dialogWidth:"
						+ width + "px;dialogHeight:" + height + "px;dialogTop:"
						+ loc_y + "px;dialogLeft:" + loc_x + "px", true);
	else
		window.open(URL,parent,"height="+ height+ ",width="+ width
								+ ",status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,top="
								+ loc_y+ ",left="+ loc_x+ ",resizable=yes,modal=yes,dependent=yes,dialog=yes,minimizable=no",
						true);
}

function openWin(URL, win, width, height) {
	if (width == undefined)
		width = $(window).width() + 40;
	if (height == undefined)
		height = $(window).height() + 50;
	// alert(width+","+height);
	window.open(URL,win,"height="+ height+ ",width="+ width+ ",status=0,top=0,left=0,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes,modal=yes,dependent=yes,dialog=yes,minimizable=no");
}

function openWin2(URL, win) {
	window.open(URL,win,"top=0,left=0,fullscreen=yes,status=0,titlebar=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes,modal=yes,dependent=yes,dialog=yes,minimizable=no");
}

function openWinDialog(URL,open_mode){
	if(open_mode=='_self'){
		
	}else if(open_mode=='_blank'){
		
	}else if(open_mode=='modal'){
		
	}
}

function open_modal_dialog(MODULE_ID, TO_ID, TO_NAME, FORM_NAME, URL_APPEND,
		width, height) {
	URL = URL_APPEND + "MODULE_ID=" + MODULE_ID + "&TO_ID=" + TO_ID
			+ "&TO_NAME=" + TO_NAME + "&FORM_NAME=" + FORM_NAME;
	loc_y = loc_x = 200;
	if (is_ie) {
		loc_x = document.body.scrollLeft + event.clientX - 200;
		loc_y = document.body.scrollTop + event.clientY + 70;
	}
	loc_x = (window.screen.width - width) / 2;
	loc_y = (window.screen.height - height) / 2;
	LoadDialogWindow(URL, self, loc_x, loc_y, width, height);
}

function select_data(MODULE_ID, TO_ID, TO_NAME, FORM_NAME, URL_APPEND) {
	open_modal_dialog(MODULE_ID, TO_ID, TO_NAME, FORM_NAME, URL_APPEND, 720,
			420);
}

function select_data1(MODULE_ID, TO_ID, TO_NAME, FORM_NAME, URL_APPEND) {
	open_modal_dialog(MODULE_ID, TO_ID, TO_NAME, FORM_NAME, URL_APPEND, 300,
			400);
}

function clear_data(TO_ID, TO_NAME) {
	if (TO_ID == "" || TO_ID == "undefined" || TO_ID == null) {
		TO_ID = "TO_ID";
		TO_NAME = "TO_NAME";
	}
	document.getElementById(TO_ID).value = "";
	document.getElementById(TO_NAME).value = "";
}

function SelectUser(MODULE_ID, TO_ID, TO_NAME, FORM_NAME, MANAGE_FLAG,
		URL_APPEND) {
	URL = URL_APPEND + "/commons/user_select?MODULE_ID=" + MODULE_ID
			+ "&TO_ID=" + TO_ID + "&TO_NAME=" + TO_NAME + "&FORM_NAME="
			+ FORM_NAME;
	loc_y = loc_x = 200;
	if (is_ie) {
		loc_x = document.body.scrollLeft + event.clientX - 100;
		loc_y = document.body.scrollTop + event.clientY + 170;
	}
	LoadDialogWindow(URL, self, loc_x, loc_y, 400, 350);
}

function ClearUser(TO_ID, TO_NAME) {
	if (TO_ID == "" || TO_ID == "undefined" || TO_ID == null) {
		TO_ID = "TO_ID";
		TO_NAME = "TO_NAME";
	}
	document.getElementsByName(TO_ID)[0].value = "";
	document.getElementsByName(TO_NAME)[0].value = "";
}

function SelectUserSingle(MODULE_ID, TO_ID, TO_NAME, MANAGE_FLAG, FORM_NAME,
		URL_APPEND) {
	URL = URL_APPEND + "/commons/user_select_single.php?MODULE_ID=" + MODULE_ID
			+ "&TO_ID=" + TO_ID + "&TO_NAME=" + TO_NAME + "&FORM_NAME="
			+ FORM_NAME;
	loc_y = loc_x = 200;
	if (is_ie) {
		loc_x = document.body.scrollLeft + event.clientX - 100;
		loc_y = document.body.scrollTop + event.clientY + 170;
	}
	LoadDialogWindow(URL, self, loc_x, loc_y, 400, 350);
}

function SelectDept(MODULE_ID, TO_ID, TO_NAME, PRIV_OP, URL_APPEND) {
	URL = URL_APPEND + "/commons/dept_select.php?MODULE_ID=" + MODULE_ID
			+ "&TO_ID=" + TO_ID + "&TO_NAME=" + TO_NAME + "&PRIV_OP=" + PRIV_OP;
	loc_y = loc_x = 200;
	if (is_ie) {
		loc_x = document.body.scrollLeft + event.clientX - 100;
		loc_y = document.body.scrollTop + event.clientY + 170;
	}
	LoadDialogWindow(URL, self, loc_x, loc_y, 400, 350);
}

function SelectDeptSingle(MODULE_ID, TO_ID, TO_NAME, PRIV_OP, URL_APPEND) {
	URL = URL_APPEND + "/commons/dept_select_single.php?MODULE_ID=" + MODULE_ID
			+ "&TO_ID=" + TO_ID + "&TO_NAME=" + TO_NAME + "&PRIV_OP=" + PRIV_OP;
	loc_y = loc_x = 200;
	if (is_ie) {
		loc_x = document.body.scrollLeft + event.clientX - 100;
		loc_y = document.body.scrollTop + event.clientY + 170;
	}
	LoadDialogWindow(URL, self, loc_x, loc_y, 200, 350);
}

function iframeAutoFit() {
	var ex;
	try {
		if (window != parent) {
			// var parentHeight = screen.availHeight - 300;
			var a = document.getElementsByTagName("iframe");
			for ( var i = 0; i < a.length; i++) {
				a[i].style.height = "460px";
				var h1 = 0, h2 = 0;
				if (document.documentElement
						&& document.documentElement.scrollHeight) {
					h1 = document.documentElement.scrollHeight;
				}

				if (document.body)
					h2 = document.body.scrollHeight;
				var h = Math.max(h1, h2);
				// if(h<parentHeight) h=parentHeight;
				h = h - 18;// alert(h1+","+h2+","+h);
				a[i].style.height = h + "px";

			}
			// alert(document.body.scrollHeight+" "+window.screen.availHeight+"
			// "+document.body.scrollHeight+" "+parentHeight+"
			// "+document.documentElement.scrollHeight);
		}
	} catch (ex) {
	}
}

function doane(event) {
	e = event ? event : window.event;
	if (is_ie) {
		e.returnValue = false;
		e.cancelBubble = true;
	} else if (e) {
		e.stopPropagation();
		e.preventDefault();
	}
}

/**
 * cookie操作
 */
function getcookie(name) {
	var cookie_start = document.cookie.indexOf(name);
	var cookie_end = document.cookie.indexOf(";", cookie_start);
	return cookie_start == -1 ? '' : unescape(document.cookie.substring(
			cookie_start + name.length + 1,
			(cookie_end > cookie_start ? cookie_end : document.cookie.length)));
}

function setcookie(cookieName, cookieValue, seconds, path, domain, secure) {
	var expires = new Date();
	expires.setTime(expires.getTime() + seconds);
	document.cookie = escape(cookieName) + '=' + escape(cookieValue)
			+ (expires ? '; expires=' + expires.toGMTString() : '')
			+ (path ? '; path=' + path : '/')
			+ (domain ? '; domain=' + domain : '') + (secure ? '; secure' : '');
}

/**
 * 正则表达式
 * 
 */
function ismail(email) {
	var mail = trim(email);
	if (mail == "")
		return;

	var myReg = /^[_\.0-9a-zA-Z+-]+@([0-9a-zA-Z]+[0-9a-zA-Z-]*\.)+[a-zA-Z]{2,4}$/;
	if (myReg.test(email) == false) {
		return false;
	}
	return true;
}

function checkint(str, alt_msg) {
	var reg = /^\d{1,10}?$/;
	if (!reg.test(str)) {
		if (alt_msg != "") {
			alert(alt_msg);
		}
		return false;
	}
	return true;
}

function checkdecimal(str, digit, alt_msg) {
	var reg = new RegExp('^\\d{1,10}(\\.\\d{1,' + digit + '})?$');
	if (!reg.test(str)) {
		if (alt_msg != "") {
			alert(alt_msg);
		}
		return false;
	}
	return true;
}

function checkTime(str) {

	var reg = /^(\d+)-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	var r = str.match(reg);
	if (r == null)
		return false;
	r[2] = r[2] - 1;
	var d = new Date(r[1], r[2], r[3], r[4], r[5], r[6]);
	if (d.getFullYear() != r[1])
		return false;
	if (d.getMonth() != r[2])
		return false;
	if (d.getDate() != r[3])
		return false;
	if (d.getHours() != r[4])
		return false;
	if (d.getMinutes() != r[5])
		return false;
	if (d.getSeconds() != r[6])
		return false;
	return true;
}

function checkShortTime(str) {
	var r = str.match(/^([0-]\d|2[0-3]):[0-5]\d$/);
	if (r == null)
		return false;
	return true;
}

function checkFullShortTime(str) {
	var r = str.match(/^([0-]\d|2[0-3]):[0-5]\d:[0-5]\d$/);
	if (r == null)
		return false;
	return true;
}

/**
 * 字符串操作
 * 
 */

function mb_strlen(str) {
	aMatch = str.match(/[^\x00-\x80]/g);
	return (str.length + (!aMatch ? 0 : aMatch.length));
}

function strlen(str) {
	return (is_ie && str.indexOf('\n') != -1) ? str.replace(/\r?\n/g, '_').length
			: str.length;
}

function updatestring(str1, str2, clear) {
	str2 = '_' + str2 + '_';
	return clear ? str1.replace(str2, '') : (str1.indexOf(str2) == -1 ? str1
			+ str2 : str1);
}

function trim(str) {
	return (str + '').replace(/(\s+)$/g, '').replace(/^\s+/g, '');
}

function onlyChinese(str) {
	var re = /[^\u3447-\uFA29]/;

	if (re.test(str)) {
		return false;
	} else {
		return true;
	}
}

/**
 * 数字操作
 */
// 四舍五入
function math_round(val, num) {
	return parseFloat(val).toFixed(num);// num表示保留num位小數

}

// 仅能输入0-9的数字

function input_num(obj) {
	return obj.value.replace(/[^0-9]/g, '');
}

// 仅能输入正整数

function input_int(obj) {
	return (obj.value.replace(/[^0-9.]/g, '')).replace(/[.][0-9]*[.]/, ".");
}

// ---------统计当前输入的字符数量（可以处理汉字）begin---------
// @obj=textarea或者input 对象
// @max=最大的字符串数量

// @v=剩余字数显示的html对象
function string_count(obj, max, v) {
	var reg = /[^\x00-\xff]/gm;
	var str = obj.value.replace(reg, 'aa');// 全换成单字节字符计算
	if (v != null)
		var v = document.getElementById('v');

	if (str.length > max) {
		var n = 0, strr = [];
		for ( var i = 0; i < obj.value.length; i++) {// 得到50字节以内的字符

			/[^\x00-\xff]/.test(obj.value.charAt(i)) ? n += 2 : n += 1;
			if (n > max)
				break;
			strr.push(obj.value.charAt(i));
		}
		obj.value = strr.join('');
		if (v != null)
			v.innerHTML = 0; // 剩余数量
	} else {
		if (v != null)
			v.innerHTML = max - str.length;// 剩余数量
	}
}
// ---------统计当前输入的字符数量（可以处理汉字）end---------

// 身份证

function isIdCardNo(num) {
	var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
			2, 1);
	var error;
	var varArray = new Array();
	var intValue;
	var lngProduct = 0;
	var intCheckDigit;
	var intStrLen = num.length;
	var idNumber = num;
	// initialize
	if ((intStrLen != 15) && (intStrLen != 18)) {
		// error = "输入身份证号码长度不对！";
		// alert(error);
		// frmAddUser.txtIDCard.focus();
		return false;
	}
	// check and set value
	for (i = 0; i < intStrLen; i++) {
		varArray[i] = idNumber.charAt(i);
		if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
			// error = "错误的身份证号码！.";
			// alert(error);
			// frmAddUser.txtIDCard.focus();
			return false;
		} else if (i < 17) {
			varArray[i] = varArray[i] * factorArr[i];
		}
	}
	if (intStrLen == 18) {
		// check date
		var date8 = idNumber.substring(6, 14);
		/*
		 * if (checkDate(date8) == false) { //error = "身份证中日期信息不正确！.";
		 * //alert(error); return false; }
		 */
		// calculate the sum of the products
		for (i = 0; i < 17; i++) {
			lngProduct = lngProduct + varArray[i];
		}
		// calculate the check digit
		intCheckDigit = 12 - lngProduct % 11;
		switch (intCheckDigit) {
		case 10:
			intCheckDigit = 'X';
			break;
		case 11:
			intCheckDigit = 0;
			break;
		case 12:
			intCheckDigit = 1;
			break;
		}
		// check last digit
		if (varArray[17].toUpperCase() != intCheckDigit) {
			// error = "身份证效验位错误!正确为： " + intCheckDigit + ".";
			// alert(error);
			return false;
		}
	} else { // length is 15
		// check date
		/*
		 * var date6 = idNumber.substring(6,12); if (checkDate(date6) == false) {
		 * //alert("身份证日期信息有误！."); return false; }
		 */
	}
	// alert ("Correct.");
	return true;
}

// 邮编
function isPostalCode(val) {
	var pattern = /^[0-9]{6}$/;
	if (val != "") {
		if (!pattern.exec(val)) {
			return false;
		}
	}
	return true;
}
// 电话
function isTel(str) {
	var patrn = /^[0-9]{7,18}$/;
	if (!patrn.exec(str))
		return false
	return true
}

function confirmSubmit(msg, submitID) {
	if (confirm(msg)) {
		document.getElementById(submitID).disabled = true;
		return true;
	}
	return false;
}

function show_hide(obj, name) {
	var el = document.getElementById(name);
	if (el.style.display == 'none') {
		el.style.display = '';
		obj.src = 'images/minus.png';
	} else {
		el.style.display = 'none';
		obj.src = 'images/plus.png';
	}
}

function js_showHide(el_id) {
	el = document.getElementById(el_id);
	if (el.style.display == 'none') {
		el.style.display = '';
	} else {
		el.style.display = 'none';
	}
}

function js_showHideVisible(el_id) {
	el = document.getElementById(el_id);
	if (el.style.visibility == 'hidden') {
		el.style.visibility = 'visible';
	} else {
		el.style.visibility = 'hidden';
	}
}

/**
 * Toggles visibility of inner tables
 */
function toggleVisibility(obj, img) {
	if (!obj) {
		return;
	}
	Element.extend(obj);
	if (obj.visible()) {
		obj.hide();
		img ? img.className = 'plus' : null;
		return 'hidden';
	} else {
		obj.show();
		img ? img.className = 'minus' : null;
		return 'visible';
	}
}

function js_findPos(obj) {
	var curleft = curtop = 0;
	if (obj.offsetParent) {
		curleft = obj.offsetLeft
		curtop = obj.offsetTop
		while (obj = obj.offsetParent) {
			curleft += obj.offsetLeft
			curtop += obj.offsetTop
		}
	}
	return [ curleft, curtop ];
}

function getQueryString(name) {
	var reg = new RegExp("(^|&|\\?)" + name + "=([^&]*)(&|$)");
	var r = window.location.href.match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function getObj(objectId)// 获取id的函数
{
	if (document.getElementById && document.getElementById(objectId)) {
		// W3C DOM
		return document.getElementById(objectId);
	} else if (document.all && document.all(objectId)) {
		// MSIE 4 DOM
		return document.all(objectId);
	} else if (document.layers && document.layers[objectId]) {
		// NN 4 DOM.. note: this won't find nested layers
		return document.layers[objectId];
	} else {
		return false;
	}
}

function checkIsValidDate(str) {
	if (str == "")
		return true;
	var arrDate = str.split("-");
	if (parseInt(arrDate[0], 10) < 100)
		arrDate[0] = 2000 + parseInt(arrDate[0], 10) + "";
	var month = parseInt(arrDate[1], 10) - 1;
	var date = new Date(arrDate[0], month, arrDate[2]);
	/*
	 * alert(arrDate[0] + "===" + arrDate[1] + "===" + arrDate[2] + "===>" +
	 * date.getFullYear() + "===" + date.getMonth() + "===" + date.getDate());
	 * alert(date.getFullYear() == arrDate[0]); alert(date.getMonth() == month);
	 * alert(date.getDate() == arrDate[2]);
	 */
	if (date.getFullYear() == arrDate[0] && date.getMonth() == month
			&& date.getDate() == arrDate[2]) {
		return true;
	} else
		return false;
}

function checkDateEarlier(strStart, strEnd) {
	if (checkIsValidDate(strStart) == false
			|| checkIsValidDate(strEnd) == false) {
		alert('非法日期格式');
		return false;
	}
	var arr1 = strStart.split("-");
	var arr2 = strEnd.split("-");
	var date1 = new Date();
	date1.setFullYear(arr1[0], parseInt(arr1[1].replace(/^0/, ""), 10) - 1,
			arr1[2]);
	var d1 = date1.getTime();

	var date2 = new Date();
	date2.setFullYear(arr2[0], parseInt(arr2[1].replace(/^0/, ""), 10) - 1,
			arr2[2]);
	var d2 = date2.getTime();

	// alert(d1<=d2?'true':'false');
	return (d1 <= d2 ? true : false);
}

function trim(str) {
	return str.replace(/(^\s*)|(\s*$)|(　*)/g, "");
}

function GET_TOP_URL() {
	return "http://" + location.host + location.pathname + location.search;
}

// 计算字符字节数
function check_code_size(string_data) {
	var chars = 0; // 字节数变量
	for ( var i = 0; i < string_data.length; i++) {
		var charinit = string_data.charCodeAt(i);
		if ((charinit >= 0x0001 && charinit <= 0x007e)
				|| (0xff60 <= charinit && charinit <= 0xff9f)) {
			chars++; // 单字节加1
		} else {
			chars += 2; // 双字节加2
		}
	}
	return chars;
}

// 修复 IE 下 PNG 图片不能透明显示的问题
function fixPNG(myImage) {
	var arVersion = navigator.appVersion.split("MSIE");
	var version = parseFloat(arVersion[1]);
	if ((version >= 5.5) && (version < 7) && (document.body.filters)) {
		var imgID = (myImage.id) ? "id='" + myImage.id + "' " : "";
		var imgClass = (myImage.className) ? "class='" + myImage.className
				+ "' " : "";
		var imgTitle = (myImage.title) ? "title='" + myImage.title + "' "
				: "title='" + myImage.alt + "' ";
		var imgStyle = "display:inline-block;" + myImage.style.cssText;
		var strNewHTML = "<span " + imgID + imgClass + imgTitle + " style=\""
				+ "width:" + myImage.width + "px; height:" + myImage.height
				+ "px;" + imgStyle + ";"
				+ "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
				+ "(src=\'" + myImage.src
				+ "\', sizingMethod='scale');\"></span>";
		myImage.outerHTML = strNewHTML;
	}
}

function iFrameHeight(frameName) {
	var ifm = document.getElementById(frameName);
	var subWeb = document.frames ? document.frames[frameName].document
			: ifm.contentDocument;
	if (ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight+30;
	}
}
