/**
 * 
 */
function confirm_todo(url, txt) {
	art.dialog({
		title:"确认执行该操作",
		content : txt,
		icon : "question",
		lock : "true",
		window : "top",
		ok : function() {
			location.href = url;
		},
		cancel : function() {
		}
	});
}
function dialog_open_yesno(url, title, height, width) {
	if (height == undefined)
		height = "80%";
	if (width == undefined)
		width = "80%";
	if (title == undefined)
		title = "";
	art.dialog.open(url, {
		title : title,
		height : height,
		width : width,
		lock : true,
		yesText : "返回",
		loadingTip : "载入中...",
		window : "top",
		/*ok : function(iframeWin, topWin) {
		},*/
		cancel : function() {
			location.reload();
		}
	});
}

function dialog_open(url, title, height, width) {
	if (height == undefined)
		height = "80%";
	if (width == undefined)
		width = "80%";
	if (title == undefined)
		title = "";
	art.dialog.open(url, {
		title : title,
		height : height,
		width : width,
		lock : true,
		loadingTip : "载入中..."
	});
}

function dialog_alert(txt) {
	ad_alert(txt,3);
}

function ad_alert(txt,wait) {
	art.dialog({
		time : wait,
		content : txt,
		lock : true,
		icon : "warning",
		title : "提示消息"
	});
}

function disalog_alert_success(msg,yesFunc){
	art.dialog({
		content : msg,
		lock : true,
		icon : "face-smile",
		title : "提示消息",
		ok : yesFunc
	});
}

function dialog_alert_success2(msg,redirect_url){
	art.dialog({
		time : 3,
		content : msg,
		lock : true,
		icon : "face-smile",
		title : "提示消息",
		ok : function(iframeWin, topWin) {
			window.location.href = redirect_url;
		}
	});
}

function disalog_alert_failure(msg,noFunc){
	art.dialog({
		content : msg,
		lock : true,
		icon : "face-sad",
		title : "提示消息",
		ok : noFunc
	});
}

function dialog_alert_failure2(msg,redirect_url){
	art.dialog({
		time : 3,
		content : msg,
		lock : true,
		icon : "face-sad",
		title : "提示消息",
		ok : function(iframeWin, topWin) {
			window.location.href = redirect_url;
		}
	});
}