function isCompanyRoleNameExit()
{
	var result;
	$.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json", 
        data:{id:$("#roleName").data("roleid"),roleName:$("#roleName").val(),userType:$("#userType").val()},
        url: "checkCompanyRoleName",
        error: function (data) {//请求失败处理函数  
//        	showAlertWin("网络异常："+data.responseText);
        	showAlertWin("网络异常!");
        },  
        success:function(data){ //请求成功后处理函数。 
        	result = data;   //把后台封装好的简单Json格式赋给treeNodes     
        }
    });
   return result;
}

function isEmployeeRoleNameExit()
{
	var result;
	$.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json", 
        data:{id:$("#roleName").data("roleid"),roleName:$("#roleName").val()},
        url: "checkEmployeeRoleName",
        error: function (data) {//请求失败处理函数  
//        	showAlertWin("网络异常："+data.responseText); 
        	showAlertWin("网络异常!");
        },  
        success:function(data){ //请求成功后处理函数。 
        	result = data;   //把后台封装好的简单Json格式赋给treeNodes     
        }
    });
   return result;
}
var setting = {
	check: {
		enable: true,
		chkStyle: "checkbox",
		chkDisabledInherit: false,
		chkboxType:{ "Y" : "ps", "N" : "s" }
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

function reloadCompanyRolePage()
{
	location.replace(basepath + "/role/gotoCompanyRoleList");
}
function reloadEmployeeRolePage()
{
	location.replace(basepath + "/role/gotoEmployeeRoleList");
}
var msg = ""
function setMsg()
{
	$(".dlg-notice").addClass("notice-icon01");
	$(".notice-content").text(msg);
}
