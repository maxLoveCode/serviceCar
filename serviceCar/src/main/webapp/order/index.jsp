<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- 引入头 -->
<%@ include file="/common/header.jsp" %>
<!-- 数据表 -->
<table id="dg" class="easyui-datagrid" data-options="fit:true,pagination:true,singleSelect:true,url:'${pageContext.request.contextPath}'+'/order/list',method:'get',toolbar:'#tb'">
   <thead>
   </thead>
</table>
<!-- 工具栏 -->
<div id="tb">
   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$('#dlg-add').dialog('open')">新增</a>
   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
   <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="remove()">删除</a>
   <input class="easyui-searchbox" searcher="search" prompt="请输入关键字" name="keywords">
</div>
<!-- 增加对话框 -->
<div id="dlg-add" class="easyui-dialog" title="新增用户" style="width: 400px; height: 280px; padding: 10px 20px" closed="true" buttons="#dlg-add-buttons">
   <div class="ftitle">用户信息</div>
   <form id="fm-add" style="margin:0; padding:10px 30px;" method="post" novalidate>
      <div class="fitem">
         <label>手机号:</label>
         <input name="username" class="easyui-validatebox" required="true">
      </div>
      <div class="fitem">
         <label>密码:</label>
         <input name="password" class="easyui-validatebox" required="true">
      </div>
      <div class="fitem">
         <label>昵称:</label>
         <input name="nickname">
      </div>
   </form>
</div>
<!-- 增加保存按钮 -->
<div id="dlg-add-buttons">
   <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="insert()">保存</a>
   <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg-add').dialog('close')">取消</a>
</div>
<!-- 编辑对话框 -->
<div id="dlg-edit" class="easyui-dialog" title="编辑用户" style="width: 400px; height: 280px; padding: 10px 20px" closed="true" buttons="#dlg-edit-buttons">
   <div class="ftitle">用户信息</div>
   <form id="fm-edit" style="margin:0; padding:10px 30px;" method="post" novalidate>
      <div class="fitem">
         <label>手机号:</label>
         <input name="id" type="hidden">
         <input name="username" class="easyui-validatebox" required="true">
      </div>
      <div class="fitem">
         <label>昵称:</label>
         <input name="nickname">
      </div>
   </form>
</div>
<!-- 编辑保存按钮 -->
<div id="dlg-edit-buttons">
   <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="update()">保存</a>
   <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg-edit').dialog('close')">取消</a>
</div>
<!-- 引入尾 -->
<%@ include file="/common/footer.jsp" %>
<script type="text/javascript">
<%@ include file="/common/dateFormatter.jsp" %>
	$(function() {
		$('#dg')
				.datagrid(
						{
							url : "${pageContext.request.contextPath}"
									+ '/order/list',
							toolbar : '#toolbar',
							columns : [ [
									{
										field : 'id',
										title : '订单id',
										width : 60,
										align : 'right',
                                   		rowspan: 2
									},
									{
										field : 'dest',
										title : '目的地',
										width : 100,
                                    	rowspan: 2
									},
									{
										field : 'createtime',
										title : '创建日期',
										formatter : formatDate,
										width : 150,
                                    	rowspan: 2
									},
									{
										field : 'starttime',
										title : '开始时间',
										formatter : formatDate,
										width : 150,
                                    	rowspan: 2
									},
									{
										field : 'endtime',
										title : '结束时间',
										formatter : formatDate,
										width : 150,
                                    	rowspan: 2
									}, {
                                    	title: '用车部门',
	                                    colspan: 3
                               		}, {
                                    	title: '分配信息',
	                                    colspan: 4
                               		}, {
                                    	title: '费用',
	                                    colspan: 8
                               		},
									{
										field : 'statusName',
										title : '状态',
										width : 80,
                                    	rowspan: 2
									}
							],
                                [{
                                    field: 'department.id',
                                    title: '部门id',
                                    width: 50,
                                    formatter:function(value,row){
                                    	console.log(row.driver);
                                    	return row.department.id;
                                    }
                                }, {
                                    field: 'department.name',
                                    title: '部门名',
                                    width: 100,
                                    formatter:function(value,row){
                                    	console.log(row.driver);
                                    	return row.department.name;
                                    }
                                }, {
                                    field: 'reason',
                                    title: '用车事由',
                                    width: 100
                                },{
                                    field: 'driver.id',
                                    title: '司机id',
                                    width: 50,
                                    formatter:function(value,row){
                                    	console.log(row.driver);
                                    	return row.driver.id;
                                    }
                                }, {
                                    field: 'driver.name',
                                    title: '司机账号',
                                    width: 100,
                                    formatter:function(value,row){
                                    	return row.driver.username;
                                    }
                                }, {
                                    field: 'car.id',
                                    title: '车辆id',
                                    width: 50,
                                    formatter:function(value,row){
                                    	console.log(row.driver);
                                    	return row.car.id;
                                    }
                                }, {
                                    field: 'car.name',
                                    title: '车牌号',
                                    width: 100,
                                    formatter:function(value,row){
                                    	console.log(row.driver);
                                    	return row.car.carNumber;
                                    }
                                },
                                {
                                    field: 'unitPrice',
                                    title: '每公里单价',
                                    width: 65
                                },{
                                    field: 'distance',
                                    title: '路程',
                                    width: 65
                                },
                                {
                                    field: 'parkcost',
                                    title: '停车费',
                                    width: 65
                                }, {
                                    field: 'trafcost',
                                    title: '交通费',
                                    width: 65
                                }, {
                                    field: 'passcost',
                                    title: '过路费',
                                    width: 65
                                }, {
                                    field: 'extras',
                                    title: '其他费用',
                                    width: 65
                                }, {
                                    field: 'remark',
                                    title: '其他费用原因',
                                    width: 100
                                }, {
                                    field: 'total',
                                    title: '总价',
                                    width: 100
                                }] ]
						});
	});


    function search(value,name){
     $('#dg').datagrid('reload',{'keywords':value});
    }
    //新增用户
    function insert() {
      $('#fm-add').form('submit', {
        url : "${pageContext.request.contextPath}"+'/admin/add',
        onSubmit : function() {
          return $(this).form('validate');
        },
        success : function(result) {
          var result = eval('(' + result + ')');
          if (result.success) {
            $('#dlg-add').dialog('close'); // close the dialog
            $('#dg').datagrid('reload'); // reload the user data
          } else {
            $.messager.show({
              title : '错误',
              msg : result.msg
            });
          }
        }
      });
    }
    //编辑
    function edit(){
     var row = $('#dg').datagrid('getSelected');
     if(row) {
   	  $('#dlg-edit').dialog('open');
   	  $('#fm-edit').form('load',row);
     }
    }
    //更新用户
    function update(){
     $('#fm-edit').form('submit', {
         url : "${pageContext.request.contextPath}"+'/admin/update',
         onSubmit : function() {
           return $(this).form('validate');
         },
         success : function(result) {
           var result = eval('(' + result + ')');
           if (result.success) {
             $('#dlg-edit').dialog('close'); // close the dialog
             $('#dg').datagrid('reload'); // reload the user data
           } else {
             $.messager.show({
               title : '错误',
               msg : result.msg
             });
           }
         }
       });
    }
    //删除用户
    function remove() {
      var row = $('#dg').datagrid('getSelected');
      if (row) {
        $.messager.confirm('确认框', '是否确认删除这条记录?', function(r) {
          if (r) {
            $.post("${pageContext.request.contextPath}"+'/admin/delete', {
              id : row.id
            }, function(result) {
              if (result.success) {
                $('#dg').datagrid('reload'); // reload the user data
              } else {
                $.messager.show({ // show error message
                  title : '错误',
                  msg : result.msg
                });
              }
            }, 'json');
          }
        });
      }
    }
    
    function onRowContextMenu(e, rowIndex, rowData) {
   	    e.preventDefault();
   	    $(this).datagrid('selectRow', rowIndex); //选中当前行
   	    $('#mm').menu('show', {
   	        left: e.pageX,
   	        top: e.pageY
   	    });
   	}
   	
   $('#mm').menu({
   	    onClick:function(item){
   			console.log(item.name);
   	    	if(item.name == "reset")
   	    	{
   				$('#password-reset').dialog('open').dialog('setTitle', '编辑用户');
   	    	}
   	    }
   	});
   	
   	var passwordResetUrl =  "${pageContext.request.contextPath}"+'/admin/resetPassword';
   		
   function resetPassword() {
   $('#password-fm').form('submit', {
   url : passwordResetUrl,
   onSubmit : function() {
   	$('#userId').val($('#dg').datagrid('getSelected').id);
   	return $(this).form('validate');
   },
   success : function(result) {
   	var result = eval('(' + result + ')');
   	if (result.success) {
   		$.messager.show({
   			title : "成功",
   			msg : "修改成功"
   		});
   		$('#password-reset').dialog('close');
   	} else {
   		$.messager.show({
   			title : "失败",
   			msg : result.msg
   		});
   	}
   }
   });
   }
</script>