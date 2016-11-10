var zTreeObj;
$(document).ready(function()
{
	var treeNodes;
    $.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json", 
        data:{roleId:$("#roleName").data("roleid")},
        url: basepath+"/role/getDetailsPermissionsTree",
        error: function () {//请求失败处理函数  
        	showAlertWin('请求失败');  
        },  
        success:function(data){ //请求成功后处理函数。 
            treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes  
            
        }
    });
    zTreeObj = $.fn.zTree.init($("#roletree"), setting, treeNodes);
});