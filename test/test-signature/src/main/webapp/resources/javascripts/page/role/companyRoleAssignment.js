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
		    title:"id"
		}
	},
	view: {
		showIcon: false
	}
};
function createPermissionsTree()
{
    $.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json", 
        data:{roleId:$("#roleId").val()},
        url: "getDetailsPermissionsTree",
        error: function (data) {//请求失败处理函数  
//        	showAlertWin("网络异常："+data.responseText); 
        	showAlertWin("网络异常!");
        },  
        success:function(data){ //请求成功后处理函数。 
            treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes  
            
        }
    });
    $.fn.zTree.init($("#roletree"), setting, treeNodes);
}
var userId = "";
function gotoCustomerDetail()
{
	window.location = basepath + "/customer/customerDetail?customerId=" + userId + "&curSubMenu=企业列表";
}
$(document).ready(function()
{
    //初始化保存操作弹窗
    var popWindow = new PopWindow(".confirm-link", {
        title: "提示",
        width: 420,
        reload: true,
        template: "#template-saveFinished"
    }).init();
	createPermissionsTree();
	$("#roleId").change(function()
	{
		createPermissionsTree();
	});
	$(".confirm-link").click(function()
	{
		$.post("updateRoleOfUser",
				{id:$("#user").data("id"),roleId:$("#roleId").val()},
				function(data)
				{ 
					if(data == "角色分配成功")
					{
						$(".dlg-notice").addClass("notice-icon02");
						$(".notice-content").text(data);
						userId = $("#user").data("id");
						if(userId != "")
							setTimeout("gotoCustomerDetail()",1300);
					}
					else
					{
						$(".dlg-notice").addClass("notice-icon01");
						$(".notice-content").text(data);
					}
				});
	});
});













