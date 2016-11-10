var zTreeObj;
var permissions="";
$(document).ready(function(){
	var validator;
	$(".roleNameCheck").blur(function() 
	{
    	setValidator($("#roleName"), "required", true, roleNameRule.required);
    	validator = $("#roleName").kendoValidator(
			{
    			rules: {
    				roleNameExit: employeeRoleNameRule.rule,
    			},
    			messages: {
    				roleNameExit: employeeRoleNameRule.ruleMessage,
    			},
    			needRuleAttrbute : false
			}).data("kendoValidator");
    	if(!validator.validate()) 
    	{
    		return;
    	};
	});
    $(".confirm-link").click(function(){submitEmployee()});
    $.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json", 
        data:{roleId:$("#roleName").data("roleid")},
        url: "getEditPermissionsTree",
        error: function (data) {//请求失败处理函数  
//        	showAlertWin("网络异常："+data.responseText); 
        	showAlertWin("网络异常!");
        },  
        success:function(data){ //请求成功后处理函数。 
            treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes  
            
        }
    });
    zTreeObj = $.fn.zTree.init($("#roletree"), setting, treeNodes);
    //初始化保存操作弹窗
    new PopWindow(".confirm-link", {
        title: "提示",
        width: 420,
        reload: true,
        template: "#template-saveFinished"
    }).init();
});
function setPermissions()
{
	permissions = "";
	var nodes = zTreeObj.getCheckedNodes(true);
	for(i in nodes)
	{
		permissions += nodes[i].id + ";";
	}
}
var roleId = "";
function gotoEmployeeRoleDetails()
{
	window.location="gotoEmployeeRoleDetails?roleId=" + roleId;
}
function submitEmployee()
{
	setPermissions();
	if($("#roleName").val() == "")
	{
		msg = "编辑后角色名称不能为空";
		setTimeout("setMsg()",10);
	}
	else if(permissions == "")
	{
		msg = "编辑后的角色权限不能为空";
		setTimeout("setMsg()",10);
	}
	else
	{
	    $.ajax({
	        type: 'POST',  
	        url: "updateEmployeeRole",
	        data: {roleId:$("#roleName").data("roleid"),roleName:$("#roleName").val(),"permissions":permissions},
	        error: function (data) { 
	        	$(".k-i-close").click();
//	        	showAlertWin("网络异常："+data.responseText);  
	        	showAlertWin("网络异常!");
	        },  
	        success:function(data){  
	        	if(data == "编辑角色失败")
				{	
					$(".dlg-notice").addClass("notice-icon01");
					$(".notice-content").text(data);
				}
				else
				{
			        $(".dlg-notice").addClass("notice-icon02");
					$(".notice-content").text(data);
					roleId = $("#roleName").data("roleid");
					if(roleId != "")
						setTimeout("gotoEmployeeRoleDetails()",1300);
				}
	        }
	    });
	}
}













