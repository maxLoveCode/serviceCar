<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="${pageContext.request.contextPath}/static/images/favicon.ico">
    <title>登录</title>
    
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet">
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body background="${pageContext.request.contextPath}/static/images/bg.png">
  <div class="container">
    <form action="login" method="post" class="form-signin" role="form">
      <h2 class="form-signin-heading">登录</h2>
      <input type="text" class="form-control" name="username" value="${username}" placeholder="请输入用户名" autofocus>
      <input type="password" class="form-control" name="password" placeholder="请输入密码">
      
      <div id="errorWarn" class="alert alert-danger alert-dismissible fade in <c:if test='${empty error }'>hide</c:if>" role="alert">
        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
        <p id="errorMsg">${error}</p>
      </div>
      <button id="loginBtn" class="btn btn-lg btn-primary btn-block" data-loading-text="登录中..." type="submit">登录</button>
    </form>
  </div>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.js"></script>
  <script>
    $("#loginBtn").click(function(){
    	var $btn = $(this).button('loading');
    	var username = $("input[name=username]").val();
    	var password = $("input[name=password]").val();
    	if(!username){
    		$("#errorMsg").text("账号不能为空");
    		$("#errorWarn").show();
    		$btn.button("reset");
    		return;
    	}
    	if(!password){
    		$("#errorMsg").text("密码不能为空");
    		$("#errorWarn").show();
    		$btn.button("reset");
    		return;
    	}
    	
    	$("#errorWarn").hide();
    });
  </script>
</body>
</html>
