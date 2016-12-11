var msgList = new Array();
// 指定用户名和内容发送消息
function sendto(receiver, msgText) {
	if (receiver == "") {
		$.messager.alert('系統提示','请先指定一个论文题目!','info');
		return;
	}
	if ($('#sendBoxDiv').length > 0){
		$("#sendText").val(msgText);
		$("#sendBoxDiv").dialog('open');
	}else{
		var dls = '<div id="sendBoxDiv">'
			+'<textarea id="sendText" cols="32" rows="4">'+msgText+'</textarea>'
			+'</div>';
		$("body").append(dls);
		$("#sendBoxDiv").dialog({
			title : '编辑要发送的消息',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : true,
			height : 190,
			width : 320,
			//left : 100,
			//top : 100,
			toolbar : [{
				text : '发送消息',
				iconCls : 'icon-add',
				handler : function() {
					msgService.save(receiver, $("#sendText").val(), function(data) {
						if (data == 1) {
							$.messager.alert('系統提示','发送成功!','info');
							$("#sendBoxDiv").dialog('close');
						} else {
							$.messager.alert('系統提示','发送失败!','error');
						}
						
					});
					
				}
			}]
		});
	}
	
}

// 指定别人用户名和内容，给自己发送消息
function send(receiver, msgTitle, msgText) {
	if (jQuery.inArray(receiver, msgList) == -1) {
		msgService.sysSend(receiver, msgText, function(data) {
			if (data == 1) {
				msgList.push(receiver);
				// $.messager.alert('提示','send发送成功');
			} else {
				// $.messager.alert('提示','send发送失败');
			}
		});
	} else {
		$('#aa').accordion('select', receiver + " - " + msgTitle);
	}

}

function sendToLeader(msgText) {
	if ($('#sendBoxDiv').length > 0){
		$("#sendText").val(msgText);
		$("#sendBoxDiv").dialog('open');
	}else{
		var dls = '<div id="sendBoxDiv">'
			+'<textarea id="sendText" cols="32" rows="4">'+msgText+'</textarea>'
			+'</div>';
		$("body").append(dls);
		$("#sendBoxDiv").dialog({
			title : '编辑要发送的消息',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : true,
			height : 190,
			width : 320,
			//left : 100,
			//top : 100,
			toolbar : [{
				text : '发送消息',
				iconCls : 'icon-add',
				handler : function() {
					msgService.sendToLeader($("#sendText").val(), function(data) {
						if (data == 1) {
							$.messager.alert('系统提示','消息发送成功。','info');
							$("#sendBoxDiv").dialog('close');
						} else {
							jQuery.messager.alert('系统提示:','消息发送失败!','error');
						}
						
					});
					
				}
			}]
		});
	}
	
}

function sendToMyTeacher(msgText) {
	//alert('adfff');
	if ($('#sendBoxDiv').length > 0){
		$("#sendText").val(msgText);
		$("#sendBoxDiv").dialog('open');
	}else{
		var dls = '<div id="sendBoxDiv">'
			+'<textarea id="sendText" cols="32" rows="4">'+msgText+'</textarea>'
			+'</div>';
		$("body").append(dls);
		$("#sendBoxDiv").dialog({
			title : '编辑要发送的消息',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : true,
			height : 190,
			width : 320,
			//left : 100,
			//top : 100,
			toolbar : [{
				text : '发送消息',
				iconCls : 'icon-add',
				handler : function() {
					msgService.sendToMyTeacher($("#sendText").val(),function(data){
						var str1 = "" ;
						if(data==1){
							str1 = '消息发送成功。' ;
						}else{
							str1 = '发送失败！' ;
						}
						jQuery.messager.show({   
							title:'系统提示:',   
							msg:str1,   
							timeout:3000,   
							showType:'slide'  
							});
						
						$("#sendBoxDiv").dialog('close');
					});
				}
			}]
		});
	}
}

// 注册监听，异步获取新的消息
function listenNewMsg() {

	$('#ddd').dialog({
		title : '我的消息',
		collapsible : true,
		minimizable : false,
		maximizable : false,
		resizable : true,
		height : 480,
		width : 320
	// closed : true
	});

	setInterval(function() {
		msgService.getNewMsg(function(data) {

			for ( var n in data) {
				// chatBox(data[n]);
				addMsg(data[n]);

			}

		});
	}, 3000); // 消息刷新间隔，毫秒

}

function openChatBox(ruid) {
	var msg = {};
	msg.sender = ruid;
	chatBox(msg);
}
// 与一个用户的聊天窗口，
function chatBox(msg) {
	var msgid = msg.msgId;
	// var sname = msg.receiver ;
	var rname = msg.sender;
	var msgText = msg.msgText;

	// 判断聊天窗口是否存在，存在则添加消息，不存在则创建
	if ($('#cbox' + rname).length > 0) {

		$("#in1" + rname).append(msgText + "\r\n");// 新增一行
		msgService.overRead(msgid);// 向服务端发送已读状态

	} else {
		var strdlg = '<div id="cbox' + rname + '">' + '<input id="rname'
				+ rname + '" type="hidden" value="' + rname + '"/><br>'
				+ '收到消息：<textarea id="in1' + rname
				+ '" cols="36" rows="8"></textarea><br>'
				+ '编辑消息：<textarea id="in2' + rname
				+ '" cols="36" rows="3"></textarea><br>' + '<input id="bt1'
				+ rname + '" type="button" value="发送"/>' + '</div>';
		$('body').append(strdlg);

		$("#in1" + rname).append(msgText + "\r\n");// 新增一行
		msgService.overRead(msgid);// 向服务端发送已读状态

		$("#bt1" + rname).click(
				function() {
					sendto($("#rname" + rname).val(), $("#in2" + rname).val());
					$("#in1" + rname).append(
							"--> " + $("#in2" + rname).val() + "\r\n");// 新增一行
					$("#in2" + rname).attr("value", "");
				});
		$('#cbox' + rname).dialog({
			title : '我的消息-与 ' + rname + ' 交谈中',
			collapsible : true,
			minimizable : false,
			maximizable : false,
			resizable : true,
			height : 380,
			width : 450
		// closed : true
		});
	}
}

function selectPanel() {
	$.messager.prompt('Prompt', 'Please enter the panel title:', function(s) {
		if (s) {
			$('#aa').accordion('select', s);
		}
	});
}

function addMsg(msg) {
	var msgid = msg.msgId;
	var rname = msg.sender;// 对方用户名
	var msgText = msg.msgText;// 接收到的消息
	var msgDate = msg.sendDate;

	if ($('#msgList' + rname).length > 0) {
		if ($('#m' + msgid).length > 0) {
			// 消息已存在，不作处理
		} else {
			$('#msgList' + rname).append(
					'<div id="m' + msgid + '">' + msgDate + ' : ' + msgText
							+ '</div><br>');
		}

	} else {
		if (jQuery.inArray(rname, msgList) == -1) {
			msgList.push(rname);
			addPanel(msg);
		}

	}
}
function addPanel(msg) {
	var msgid = msg.msgId;
	var rname = msg.sender;// 对方用户名
	var msgText = msg.msgText;// 接收到的消息
	var msgDate = msg.sendDate;

	// $(window.top.mainFrame.document.getElementById('aa')).accordion('add',{title:"test",content:"test"});
	$(window.top.mainFrame.document.getElementById('aa'))
			.accordion(
					'add',
					{
						title : rname + ' - ' + msg.msgTitle,
						content : '<div style=\"padding:10px\">'
								+ '<div id="msgList'
								+ rname
								+ '"><div id="m'
								+ msgid
								+ '">'
								+ msgDate
								+ ' : '
								+ msgText
								+ '</div><br></div>'
								+ '<textarea id="text'
								+ rname
								+ '" cols="24" rows="3"></textarea>'
								+ '<input id="btt'
								+ rname
								+ '"type="button" value=\"发送消息\" onclick=\"toSend(&quot;'
								+ rname + '&quot;);\">' + '</div>'

					});

}
function toSend(rname) {
	sendto(rname, $("#text" + rname).val());
	$('#msgList' + rname).append(
			'<div><font color="red">' + $("#text" + rname).val()
					+ '</font></div><br>');
	$("#text" + rname).val('');
}
function removePanel() {
	var pp = $('#aa').accordion('getSelected');
	if (pp) {
		var index = $('#aa').accordion('getPanelIndex', pp);
		$('#aa').accordion('remove', index);
	}
}