<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#dg')
				.datagrid(
						{
							url : "${pageContext.request.contextPath}"
									+ '/department/list',
							toolbar : '#toolbar',
							columns : [ [
									{
										field : 'id',
										title : '部门 id',
										width : 100,
										align : 'right'
									},
									{
										field : 'name',
										title : '部门',
										width : 100
									},
									{
										field : 'createtime',
										title : '创建日期',
										formatter : formatDate,
										width : 180
									}
							] ]
						});
	});
<%@ include file="/common/dateFormatter.jsp" %>
	
</script>
<table id="dg" class="easyui-datagrid" rownumbers="true"
	data-options="fit:true,pagination:true,singleSelect:true">
</table>
<div id="toolbar">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
		onclick="newCar()">新增</a> <a href="#" class="easyui-linkbutton"
		iconCls="icon-edit" plain="true" onclick="editCar()">编辑</a> <a
		href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
		onclick="removeCar()">删除</a> <input class="easyui-searchbox"
		searcher="search" prompt="请输入关键字" name="keywords">
</div>
<div id="dlg" class="easyui-dialog"
	style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
	buttons="#dlg-buttons">
	<div class="ftitle">部门信息</div>
	<form id="fm" method="post" novalidate>
		<div class="fitem">
			<label>部门名:</label> <input id="name"  name="name" class="easyui-validatebox"
				required="true">
		</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="save-btn" onclick="save()">Save</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
		onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>

<script>

	var url;
	function newCar() {
		$('#dlg').dialog('open').dialog('setTitle', '新增部门');
		$('#fm').form('clear');
		url = "${pageContext.request.contextPath}" + '/department/add';
	}
	function editCar() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑部门');
			$('#fm').form('load', row);
			url = "${pageContext.request.contextPath}" + '/department/update?id='
					+ row.id;
		}
	}

	function save() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				//return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				} else {
					$.messager.show({
						title : result,
						msg : result
					});
				}
			}
		});
	}
	
	function removeCar() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认框', '是否确认删除这条记录?', function(r) {
				if (r) {
					$.post(
							"${pageContext.request.contextPath}"
									+ '/department/delete', {
								id : row.id
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the user data
								} else {
									$.messager.show({ // show error message
										title : result,
										//title : 'Error',
										msg : result.msg
									});
								}
							}, 'json');
				}
			});
		}
	}
	function search(value, name) {
		$('#dg').datagrid('reload', {
			'keywords' : value
		});
	}
	
</script>






