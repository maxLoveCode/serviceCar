<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!--派单表单--> 
 	<div id="dispatch-form" class="easyui-dialog"
 		style="width:400px ; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dispatch-buttons">
		<div class="ftitle">订单信息</div>
		<form id="dispatch-fm" method="post" novalidate>
			<div class="fitem">
				<label>执行司机:</label> 
				
				<select id="cc" class="easyui-combogrid" name="driverid" style="width:150px;"
		        data-options="
        	    panelWidth:280,
            	value:'选择司机',
	            idField:'id',
    	        textField:'driverName',
        	    url:'${pageContext.request.contextPath}'+'/driver/selectList',
        	    mode:'remote',
            	columns:[[
                	{field:'id',title:'id',width:40},
	                {field:'driverName',title:'司机名',width:100},
    	            {field:'companyName',title:'所属公司',width:120},
            	]]
            	">
            	</select>
            </div>
            <div class="fitem">
				<label>分配车辆:</label>
    			<select id="cc" class="easyui-combogrid" name="carid" style="width:150px;"
		        data-options="
        	    panelWidth:350,
            	value:'选择车辆',
	            idField:'id',
    	        textField:'number',
        	    url:'${pageContext.request.contextPath}'+'/car/selectList',
        	    mode:'remote',
            	columns:[[
                	{field:'id',title:'id',width:40},
	                {field:'number',title:'车牌号',width:80},
    	            {field:'model',title:'车型',width:120},
    	            {field:'price',title:'价格(/60公里)',width:90},
            	]]
            	">
            	</select>
            </div>
            <input type="hidden" name="orderid" id="orderid">
            <div class="fitem">
				<label>专管员:</label> <input id="cc" class="easyui-combobox" name="adminId"
    				data-options="valueField:'id',textField:'nickname',url:'${pageContext.request.contextPath}'+'/admin/adminList'">
            <div>
		</form>	
	</div>
<!--派单按钮 -->

	<div id="dispatch-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="dispatch()">派单</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dispatch-form').dialog('close')">Cancel</a>
	</div>

<script>	
	function dispatch() {
	var orderid = $('#dg').datagrid('getSelected').id;
		$('#dispatch-fm').form('submit', {
			url : "${pageContext.request.contextPath}"+'/order/dispatchRoute',
			onSubmit : function() {
				$('#orderid').val(orderid);
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dispatch-form').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
					$.messager.show({
						title : "派单成功",
						msg : "驾驶员将会收到派单消息"
					});
				} else {
					$.messager.show({
						title : result.msg,
						msg : result.msg
					});
				}
			}
		});
	}
</script>