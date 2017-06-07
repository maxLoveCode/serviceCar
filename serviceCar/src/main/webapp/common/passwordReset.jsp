<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<div id="password-reset" class="easyui-dialog"
		style="width:400px ; height: 280px; padding: 10px 20px" closed="true"
		buttons="#password-buttons">
		<div class="ftitle">密码重置</div>
		<form id="password-fm" method="post" novalidate>
			<div class="fitem">
				<label>新密码:</label> <input id = "newpass" name="newpass"
					class="easyui-passwordbox" required="true">
			</div>
			<div class="fitem">
				<label>再输入一次:</label> <input id = "repass" name="repass"
					class="easyui-passwordbox" required=" true">
			</div>
			<input type="hidden" name="userId" id="userId">
		</form>
	</div>


	<div id="password-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="resetPassword()">确认重置</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#password-reset').dialog('close')">取消</a>
	</div>
