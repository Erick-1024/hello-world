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
		key:{
		    title:"id"
		}
	},
	view: {
		showIcon: false
	}
};
function createPermissionsTree(){
	if($("#multiSelect-box").val()==null || $("#multiSelect-box").val().length < 1){
		$("#roletree").html("");
		return ;
	}
	var treeNodes = null;

	$.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json",
        contentType : "application/json",
        data:JSON.stringify({ roleIdList : $("#multiSelect-box").val()}),
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
function gotoCustomerDetail(){
	window.location = basepath + "/customer/customerDetail?customerId=" + userId + "&curSubMenu=企业列表";
}

$(document).ready(function(){
    //初始化保存操作弹窗
//    var popWindow = new PopWindow(".confirm-link", {
//        title: "提示",
//        width: 420,
//        reload: true,
//        template: "#template-saveFinished"
//    }).init();
	
	$("#multiSelect-box").kendoMultiSelect({
        dataTextField: "text",
        dataValueField: "value",
        change: function(e) {
    	    createPermissionsTree();
    	},
        autoClose: false
    });
    
	createPermissionsTree();

	$(".confirm-link").click(function(){
		if($("#multiSelect-box").val()==null || $("#multiSelect-box").val().length < 1){
			showAlertWin("请至少选择一个角色");
			return;
		}
		
		$.ajax({  
			async : false,  
			cache:false,  
			type: 'POST',  
			dataType : "text",
			contentType : "application/json",
			data:JSON.stringify({id:$("#user").data("id"), roleIdList : $("#multiSelect-box").val()}),
			url: "updateRoleOfUser",
			success:function(data){ //请求成功后处理函数。 
				if(data == "角色分配成功")	{
//					$(".dlg-notice").addClass("notice-icon02");
//					$(".notice-content").text(data);
					showSuccessWin(data);
					userId = $("#user").data("id");
					if(userId != "")
						setTimeout("gotoCustomerDetail()",1300);
				}else{
					$(".dlg-notice").addClass("notice-icon01");
					$(".notice-content").text(data);
				} 
			},
			error: function (data) {
	        	showAlertWin("网络异常!");
	        }
		});
	});
});













