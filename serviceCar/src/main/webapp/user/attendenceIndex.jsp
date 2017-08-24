<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- 引入头 -->
<%@ include file="/common/header.jsp" %>
<!-- 数据表 -->
<table id="dg" class="easyui-datagrid" data-options="fit:true,pagination:true,singleSelect:true,url:'${pageContext.request.contextPath}'+'/user/attendenceList',method:'get',toolbar:'#tb'">
   
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
         <label>用户名:</label>
         <input name="username" class="easyui-validatebox" required="true">
      </div>
      <div class="fitem">
         <label>密码:</label>
         <input name="password" class="easyui-validatebox" required="true">
      </div>
    	<div class="fitem">
			<label>类型:</label> <input id="cc" class="easyui-combobox" name="usertype"
    			data-options="valueField:'id',textField:'typename',url:'${pageContext.request.contextPath}'+'/util/typeList'">
        <div>
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
         <label>用户名:</label>
         <input name="id" type="hidden">
         <input name="username" class="easyui-validatebox" required="true">
      </div>
      <div class="fitem">
			<label>类型:</label> <input id="cc" class="easyui-combobox" name="usertype"
    			data-options="valueField:'id',textField:'typename',url:'${pageContext.request.contextPath}'+'/util/typeList'">
     <div>
   </form>
</div>
<%@ include file="/common/passwordReset.jsp" %>
<div id="mm" class="easyui-menu" style="width:150px;">
   <div data-options="iconCls:'icon-undo'" name="reset">重置密码</div>
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
   	$('#dg').datagrid({
   		onRowContextMenu: onRowContextMenu,
							columns : [ [
									{
										field : 'id',
										title : 'id',
										width : 100,
										align : 'right',
                                   		rowspan: 2
									},
									{
										title : '司机车辆信息',
										width : 100,
	                                    colspan: 4
									},
									{
										title : '订单信息',
										width : 180,
	                                    colspan: 5
									},
									{
										title : '加班信息',
										width : 180,
	                                    colspan: 3
									}],
									[
									{
										field : 'driverId',
										title : '司机id',
										width : 100,
										align : 'right'
									},{
										field : 'nickname',
										title : '司机id',
										width : 100,
										align : 'right'
									},
									{
										field : 'contact',
										title : '司机联系方式',
										width : 100,
										align : 'right'
									},
									{
										field : 'number',
										title : '车牌号',
										width : 100,
										align : 'right'
									},
									{
										field : 'orderId',
										title : '订单id',
										width : 100,
										align : 'right'
									},
									{
										field : 'dest',
										title : '出发地',
										width : 180,
										align : 'right'
									},
									{
										field : 'dest',
										title : '目的地',
										width : 180,
										align : 'right'
									},
									{
										field : 'starttime',
										title : '出发时间',
										formatter : formatDate,
										width : 150,
										align : 'right'
									},
									{
										field : 'endtime',
										title : '返回时间',
										formatter : formatDate,
										width : 150,
										align : 'right'
									},
									{
										field : 'morningExtra',
										title : '上午加班时间',
										width : 100,
										align : 'right'
									},
									{
										field : 'eveningExtra',
										title : '下午加班时间',
										width : 100,
										align : 'right'
									},
									{
										field : 'totalHours',
										title : '总时间',
										width : 100,
										align : 'right'
									}]
									]
   		})
   		})
   			
    function search(value,name){
     $('#dg').datagrid('reload',{'keywords':value});
    }
    //新增用户
    function insert() {
      $('#fm-add').form('submit', {
        url : "${pageContext.request.contextPath}"+'/user/add',
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
         url : "${pageContext.request.contextPath}"+'/user/update',
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