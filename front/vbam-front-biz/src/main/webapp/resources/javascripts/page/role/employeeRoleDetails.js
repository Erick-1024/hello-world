var zTreeObj;
var treeNodes = null;
$(document).ready(function(){
	var roleList = new Array();
	roleList[0] = $("#roleName").data("roleid");
    $.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json", 
        contentType : "application/json",
        data:JSON.stringify({roleIdList:roleList}),
        url: "getDetailsPermissionsTree",
        error: function (data) {//请求失败处理函数  
//        	showAlertWin("网络异常："+data.responseText);
        	showAlertWin("网络异常!");
        },  
        success:function(data){ //请求成功后处理函数。 
            treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes  
            
        }
    });
    zTreeObj = $.fn.zTree.init($("#roletree"), setting, treeNodes);
});













