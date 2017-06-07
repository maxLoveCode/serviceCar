<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	// 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String socketPath = "ws://" + request.getServerName() + ":" + request.getServerPort() + path;
	// 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。
	pageContext.setAttribute("basePath", basePath);
	pageContext.setAttribute("socketPath", socketPath);
%>

  <%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/index.css">
<script type="text/javascript"
	src='${pageContext.request.contextPath}/static/js/index.js'>
	
</script>
<script type="text/javascript">
	var _menus = {
		"menus" : [
				{
					"menuid" : "1",
					"icon" : "icon-sys",
					"menuname" : "系统管理",
					"menus" : [
							{
								"menuid" : "12",
								"menuname" : "用户管理",
								"icon" : "icon-users",
								"url" : "${pageContext.request.contextPath}"
										+ "/user/index"
							}
							]
				},
				{
					"menuid" : "8",
					"icon" : "icon-sys",
					"menuname" : "供车管理",
					"menus" : [
							{
								"menuname" : "车辆管理",
								"icon" : "icon-nav",
								"url" : "${pageContext.request.contextPath}"
										+ "/car/index"
							},
							{
								"menuname" : "部门管理",
								"icon" : "icon-nav",
								"url" : "${pageContext.request.contextPath}"
										+ "/driver/index"
							}]
				},
				{
					"menuid" : "10",
					"icon" : "icon-sys",
					"menuname" : "订单管理",
					"menus" : [
							{
								"menuid" : "102",
								"menuname" : "订单列表",
								"icon" : "icon-nav",
								"url" : "${pageContext.request.contextPath}"
										+ "/order/index"
							},
							{
								"menuname" : "历史订单",
								"icon" : "icon-nav",
								"url" : "${pageContext.request.contextPath}"
										+ "/order/historyList"
							}
							 ]
				} ]
	};
	
	
	//设置登陆窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
	}
	//关闭登陆窗口
	function closePwd() {
		$('#w').window('close');
	}
	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');
		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请在一次输入密码！', 'warning');
			return false;
		}
		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}
		$.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(
				msg) {
			msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
			$newpass.val('');
			$rePass.val('');
			closePwd();
		})
	}
	
	//获取信息
	function getNotify(){
		$.get( '${pageContext.request.contextPath}/order/orderStats', function( data ) {
  		var panels = $('.nav');
  		var notify;
  		for(i=0; i<data.length; i++)
  		{
  			var object = data[i];
  			if(object.status == 0)
  			{
  				notify=object.count;
  			}
  		}
  		$.each(panels,function(i, n) {
  			if(n.innerHTML == "订单列表")
  			{
  				if(notify != null && notify != 0)
  				{			
	  				n.innerHTML = "订单列表"+"("+notify+")";
  				}
  			}
  		})
	});
	}
	$(function() {
		openPwd();
		$('#editpass').click(function() {
			$('#w').window('open');
		});
		$('#btnEp').click(function() {
			serverLogin();
		})
		$('#btnCancel').click(function() {
			closePwd();
		})
		$('#loginOut')
				.click(
						function() {
							$.messager
									.confirm(
											'系统提示',
											'您确定要退出本次登陆吗?',
											function(r) {
												if (r) {
													location.href = '${pageContext.request.contextPath}/admin/logout';
												}
											});
						})
	});
</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true"
	scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img
				src="${pageContext.request.contextPath}/static/images/noscript.gif"
				alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #D2E0F2; z-index: 20000">
		<div id="pageloading"
			style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; border: 2px solid #8DB2E3; width: 200px; height: 40px; font-size: 14px; padding: 10px; font-weight: bold; background: #fff; color: #15428B;">
			<img
				src="${pageContext.request.contextPath}/static/images/loading.gif"
				align="absmiddle" /> 正在加载中,请稍候...
		</div>
	</div>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 107px; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="qy_toplrk">
			<tr>
				<td width="401" height="78" align="left" valign="middle"
					class="qy_top2bg" style="background-image:url(${pageContext.request.contextPath}/static/images/bg1.png);background-repeat:no-repeat;">
				</td>
				<td align="right"  valign="middle" class="qy_top2bg" style="background-image:url(${pageContext.request.contextPath}/static/images/bg2.png);background-repeat:x;">
					<table width="350" border="0" cellspacing="0" cellpadding="0"  >
						<tr>
							<td><a href="index" target="_parent"><img
									src="${pageContext.request.contextPath}/static/images/menu_homepage.png"
									width="60" height="60" border="0" alt="" /></a></td>
							<td><a href="javascript:void(0)" id="editpass"><img
									src="${pageContext.request.contextPath}/static/images/menu_changePass.png"
									width="60" height="60" border="0" alt="" /></a></td>
							<td><a href="login" target="_parent"
								onclick="return confirm('你确认要重新登陆吗?');"><img
									src="${pageContext.request.contextPath}/static/images/menu_relogin.png"
									width="60" height="60" border="0" alt="你确认要重新登陆吗" /></a></td>
							<td><a href="logout" target="_parent"
								onclick="return confirm('你确认要退出吗?');"><img
									src="${pageContext.request.contextPath}/static/images/menu_exit.png"
									width="60" height="60" border="0" alt="你确认要退出吗" /></a></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="27" colspan="2" class="qy_topmenubor">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						bgcolor="#F0F5F9">
						<tr>
							<td height="27" align="left" valign="middle"
								class="qy_menuleftbg" style="width: 880px">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="26" align="right" valign="middle"><img
											src="${pageContext.request.contextPath}/static/images/340.gif"
											width="16" height="16" /></td>
										<td class="qy_topk2px" colspan="2">&nbsp;用户名：<span
											class="qy_lsfont">${admin.username }</span> &nbsp;&nbsp</span>&nbsp;&nbsp;登陆时间：<fmt:formatDate
												value="${admin.logintime }" pattern="yyyy-MM-dd HH:mm:ss" />
											<span style="color: red"></span>
										</td>
									</tr>
								</table>
							</td>
							<!-- <td align="right" valign="middle" class="qy_menurighbg">
                             <table width="266" border="0" cellspacing="0" cellpadding="0">
                                 <tr>
                                     <td width="21" align="left" valign="middle">
                                         <img src="/static/images/320.gif" width="16" height="16" alt="" />
                                     </td>
                                     <td align="left" valign="middle" class="qy_date">
                                     </td>
                                 </tr>
                             </table>
                         </td> -->
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2;">
		<div class="footer">By 华印 Email:luke_yale@163.com</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width: 180px;"
		id="west">
		<div id="nav">
			<!--  导航内容 -->
		</div>
	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="首页" style="padding: 20px; overflow: hidden;" id="home">
				<h1>欢迎来到大机段班车后台管理系统</h1>
			</div>
		</div>
	</div>

	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)">确定</a> <a id="btnCancel"
					class="easyui-linkbutton" icon="icon-cancel"
					href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
	</div>

</body>
</html>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/sockjs-0.3.4.min.js"></script>
<script>
	var websocket;
	if ('WebSocket' in window) {
		websocket = new WebSocket("${socketPath}/webSocketServer");
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket("${socketPath}/webSocketServer");
	} else {
		websocket = new SockJS("${basePath}sockjs/webSocketServer");
	}
	websocket.onopen = function(evnt) {
	};
	websocket.onmessage = function(evnt) {
		alert(evnt.data);//打印推送的内容
	};
	websocket.onerror = function(evnt) {
	};
	websocket.onclose = function(evnt) {
	}
</script>