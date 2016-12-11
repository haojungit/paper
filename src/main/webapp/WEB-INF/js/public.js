/**
 * 解析输入的dateStr,返回Date类型. dateStr: XXXX-XX-XX
 */
function parseDate(dateStr) {
	var strArray = dateStr.split("-");
	if (strArray.length == 3) {
		return new Date(strArray[0], strArray[1] -1, strArray[2]);
	} else {
		return new Date();
	}
}

function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}

function myparser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}

function loading(isShow, object, msg) {

	if (!isShow) {
		object.children("div.datagrid-mask-msg").remove();
		object.children("div.datagrid-mask").remove();
		return;
	}

	$("<div class=\"datagrid-mask\"></div>").css({
		display : "block",
		width : "100%",
		height : "100%"
	}).appendTo(object);

	$("<div class=\"datagrid-mask-msg\"></div>").html(msg).appendTo(object)
			.css({
				display : "block",
				left : (object.outerWidth() - 120) / 2,
				top : (object.height() - 20) / 2
			});
}