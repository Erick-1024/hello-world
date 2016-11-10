var zTreeObj;
var permissions="";
$(document).ready(function(){
	var validator;
    $(".confirm-link").click(function(){submitPermission()});
    $.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json", 
        data:{userId:$("input[name=userId]").val()},
        url: basepath+"/role/"+$("input[name=type]").val()+"/getEditPermissionsTree",
        error: function (data) {//请求失败处理函数  
//        	showAlertWin("网络异常："+data.responseText); 
        	showAlertWin("网络异常!");
        },  
        success:function(data){ //请求成功后处理函数。 
            treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes  
            
        }
    });
    zTreeObj = $.fn.zTree.init($("#permissionstree"), setting, treeNodes);
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
var userId = "";
function gotoUserDetails()
{
	window.location="gotoEmployeeRoleDetails?roleId=" + roleId;
}
function submitPermission()
{
	setPermissions();
	if($("input[name=userId]").val() == "" || $("input[name=type]").val() == "")
	{
		msg = "页面异常，请刷新页面";
		setTimeout("setMsg()",10);
	}
	else
	{
	    $.ajax({
	        type: 'POST',  
	        url: basepath + "/"+$("input[name=type]").val()+"/editPermissions",
	        data: {userId:$("input[name=userId]").val(),"permissions":permissions},
	        error: function (data) { 
	        	$(".k-i-close").click();
//	        	showAlertWin("网络异常："+data.responseText);  
	        	showAlertWin("网络异常!");
	        },  
	        dataType: "json",
	        success:function(data){  
	        	if(data.status == "FAILED")
				{	
					$(".dlg-notice").addClass("notice-icon01");
					$(".notice-content").text(data.message);
				}
				else
				{
			        $(".dlg-notice").addClass("notice-icon02");
					$(".notice-content").text(data.message);
					userId = $("input[name=userId]").val();
					if(userId != "")
						location.reload();
				}
	        }
	    });
	}
}
