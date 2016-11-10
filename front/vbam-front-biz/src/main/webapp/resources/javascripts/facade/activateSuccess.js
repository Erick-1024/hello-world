$(document).ready(function()
{
	var setting = {
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkDisabledInherit: false,
				chkboxType:{ "Y" : "ps", "N" : "" }
			},
			data: {
				simpleData: {
					enable: true
				},
				key:
				{
				    title:"name"
				}
			},
			view: {
				showIcon: false
			}
		};	
	var treeNodes = "";
    $.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json", 
        data:{roleId:$("#roleId").data("roleid")},
        url:  basepath + "/role/facade/getActivateSuccessPermissionsTree",
        error: function (data) {//请求失败处理函数  
        	showAlertWin(data.responseText);  
        },  
        success:function(data){ //请求成功后处理函数。 
            treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes  
            
        }
    });
   	$.fn.zTree.init($("#roletree"), setting, treeNodes);
});







