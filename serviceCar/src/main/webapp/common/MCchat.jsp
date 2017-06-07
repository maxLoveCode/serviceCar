<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

    <div id="chatBox" class="easyui-dialog" title="聊天" style="width:500px;height:700px;padding:10px;background:#fafafa;" data-options="iconCls:'icon-save',closable:true,
                collapsible:true,minimizable:true,maximizable:true" closed="true">
        <table id="msg-dg" class="easyui-datagrid" style="width:100%;height:500px"  singleSelect="false" fitColumns="true">
        </table>
        <input id="tb" type="text" style="width:100%; height:100px; margin-top: 10px;">
        <a href="#" id = "btn" class="easyui-linkbutton">发送</a>
    </div>

    <script type="text/javascript">
        $(function() {
            $('#msg-dg').datagrid({
				autoLoad:false,
				onBeforeLoad:function(){
				var opts = $(this).datagrid('options');
				return opts.autoLoad;
			},
                //url: "${pageContext.request.contextPath}" + '/admin/conversationDetail',
                view:cardview,
                columns: [
                    [{
                        field: 'content',
                        title: '内容',
                        width: 300
                    }]
                ]
            });

        });


        var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
            renderRow: function(target, fields, frozen, rowIndex, rowData) {
                var cc = [];
                console.log(rowData);
                if(rowData.senderType !=2)
                {
                cc.push('<div id="received">');
                cc.push('<div style="margin-left:20px; margin-top:20px; width: 200px">');
                cc.push('<p><span class="c-label">' + rowData.senderName + '</span> ' + '</p>');
                cc.push('</div>');
                
                cc.push('<div style="margin-left:25px; border-radius: 16px; border: 1px solid #ccc;background-color:#a2e65b; width: 200px">');
                cc.push('<div style="margin-left:20px; margin-right:20px; padding-top:2px ; padding-bottom:2px">');
                cc.push('<p><span class="c-label">' + rowData.content + '</span> ' + '</p>');
                cc.push('</div>');
                cc.push('</div>');
                cc.push('</div>');
                }
                else
                {
                cc.push('<div id="sent">');
                cc.push('<div style="margin-left:auto ;margin-right:20px !important; margin-top:20px; width: 200px">');
                cc.push('<p style="text-align:right"><span class="c-label">' + rowData.senderName + '</span> ' + '</p>');
                cc.push('</div>');
                
                cc.push('<div style="margin-left:auto ; margin-right:25px; border-radius: 16px; border: 1px solid #ccc;background-color:#ffffff; width: 200px">');
                cc.push('<div style="margin-left:20px; margin-right:20px; padding-top:2px ; padding-bottom:2px">');
                cc.push('<p><span class="c-label">' + rowData.content + '</span> ' + '</p>');
                cc.push('</div>');
                cc.push('</div>');
                cc.push('</div>');
                }
                return cc.join('');
            }
        });
        
        $('#tb').textbox({
   			 multiline:true
   		});
   		
   		$(function(){
    	$('#btn').bind('click', function(){
        var content = $('#tb').textbox('getText');
        var msgs = $('#msg-dg').datagrid('getData').rows;
        var rctype,rcid,parentMsgId;
        
        for (var i = 0; i < msgs.length; i++) {
            var msg = msgs[i];
        
        	
        	if(msg.senderType != 2)
        	{
        		rcid = msg.senderId;
        		rctype = msg.senderType;
        		parentMsgId= msg.parentMsgId;
        		break;
        	}
        }
        if(content!="")
        {
        console.log(rctype);
        console.log(rcid);
        	$.post( '${pageContext.request.contextPath}' + '/admin/replyMsg', { "content": content ,"receiverId":rcid, "receiverType":rctype, "parentMsgId":parentMsgId} )
        	.done(function( data ) {
        		if(data.success)
        		{
        			$('#msg-dg').datagrid('reload');
        			$('#tb').textbox('setText',"");
        		}
		  });;
        }
    });
});
    </script>