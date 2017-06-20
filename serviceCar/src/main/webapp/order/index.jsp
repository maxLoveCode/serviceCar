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
   <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="remove()">删除</a>
   <input class="easyui-searchbox" searcher="search" prompt="请输入关键字" name="keywords">
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
                                    	return row.car.id;
                                    }
                                }, {
                                    field: 'car.name',
                                    title: '车牌号',
                                    width: 100,
                                    formatter:function(value,row){
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
   	
</script>