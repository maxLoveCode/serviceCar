<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
_menus = {
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
										+ "/department/index"
							}]
				},
				{
					"menuid" : "10",
					"icon" : "icon-sys",
					"menuname" : "统计报表",
					"menus" : [
							{
								"menuid" : "102",
								"menuname" : "已完成订单",
								"icon" : "icon-nav",
								"url" : "${pageContext.request.contextPath}"
										+ "/order/statIndex"
							}
							 ]
				} ]
	};
	