var sequence = 0;
var pageSize = 10;

$(function() {
	
	// 用于加载表格中数据的方法
	searchResult();
	
	//初始化新增员工弹窗
    new PopWindow(".addStaff-link", {
        title: "新增员工",
        width: 800,
        reload: true,
        template: "#template-createStaff"
    }).init();

  //初始化员工详情弹窗
    new PopWindow(".staffDetail-link", {
        title: "员工信息详情",
        width: 800,
        reload: true,
        template: "#template-staffDetail"
    }).init();
    
    //初始化编辑员工弹窗
    new PopWindow(".staffEdit-link", {
        title: "编辑员工信息",
        width: 800,
        reload: true,
        template: "#template-editStaff"
    }).init();

    //初始化删除员工弹窗
    new PopWindow(".staffDel-link", {
        title: "删除员工信息",
        width: 420,
        reload: true,
        template: "#template-delStaff"
    }).init();

    //初始化重置密码员工弹窗
    new PopWindow(".resetStaff-link", {
        title: "重置密码",
        width: 420,
        reload: true,
        template: "#template-resetStaff"
    }).init();
    
    //初始化重发邮件弹窗
    new PopWindow(".resendMail-link", {
        title: "重发邮件",
        width: 420,
        reload: true,
        template: "#template-resendMail"
    }).init();

    //关闭弹窗
    $("body").on("click", ".dlg-wrapper .back-link", function(e){
        var win = $(e.target).closest(".k-window");
        var overlay = win.prev(".k-overlay");
        if(win.is(":visible")){
            win.css("display", "none");
            overlay.css("display", "none");
        }
    });
    
    document.onkeydown=keyDownSearch;  
    
    function keyDownSearch(e) {    
        var theEvent = e || window.event;    
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
        if (code == 13) {    
        	searchResult();  
            return false;    
        }    
        return true;    
    } 
    
	
    // 点击查询触发事件
	$(".form-search-link").click(function(){
		searchResult();
	});
    
    // 新增员工
	$("body").delegate(".addStaff-link", "click", function() {
		$("#multiSelect-box-add").kendoMultiSelect({
	        dataTextField: "text",
	        dataValueField: "value",
	        autoClose: false
	    });
		setValidator($("input[name='contactMailForAdd']"), "required", true, emailNoRule.required);
		setValidator($("input[name='contactMailForAdd']"), "pattern", emailNoRule.pattern, emailNoRule.message);
		setValidator($("input[name='contactTelForAdd']"), "required", true, mobileNoRule.required);
		setValidator($("input[name='contactTelForAdd']"), "pattern", mobileNoRule.pattern, mobileNoRule.message);
		setValidator($("input[name='realNameForAdd']"), "required", true, realNameRule.required);
		$.ajax({
			type: "POST",
			url: basepath+"/employee/gotoAdd",
			data: {},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					$("span[name='idForAdd']").text(data.data);
				}else{
					var win = $(e.target).closest(".k-window");
                    var overlay = win.prev(".k-overlay");
                    if(win.is(":visible")){
                        win.css("display", "none");
                        overlay.css("display", "none");
                    }
                    showAlertWin(data.message);
				}
			}
		});		
	});
	
	// 新建员工信息的校验
	addInputCheck();
	
	// 点击详细按钮
	$("body").delegate(".staffDetail-link", "click", function() {
		$.ajax({
			type: "POST",
			url: basepath+"/employee/employeeDetail",
			data: {employeeId:$.trim($(this).parent().next().text())},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
						$("span[name='idForShow']").text(data.data.id == null ? "  ":data.data.id);
						$("span[name='contactMailForShow']").text(data.data.employeeMail == null ? "  ":data.data.employeeMail);
						var employeeRoleName = "";
						for(var i = 0 ; i < data.data.roleDTOList.length ; i++) {
							employeeRoleName += data.data.roleDTOList[i].roleName + "<br/>";
		            	}
						$("span[name='roleNameForShow']").html(employeeRoleName);
						$("span[name='realNameForShow']").text(data.data.realName == null ? "  ":data.data.realName);
						$("span[name='contactTelForShow']").text(data.data.employeeTel == null ? "  ":data.data.employeeTel);
						$("span[name='jobTitleForShow']").text(data.data.employeeJobTitle == null ? "  ":data.data.employeeJobTitle);
						$("span[name='jobNoForShow']").text(data.data.jobNo == null ? "  ":data.data.jobNo);
						$("span[name='accountStatusForShow']").text(data.data.accountActivateStatus == null ? "  ":data.data.accountActivateStatus == "ACTIVATED" ? "已激活" : "未激活");
					}else{
						var win = $(e.target).closest(".k-window");
	                    var overlay = win.prev(".k-overlay");
	                    if(win.is(":visible")){
	                        win.css("display", "none");
	                        overlay.css("display", "none");
	                    }
	                    showAlertWin(data.message);
					}
			}
		});		
    });
	
	// 点击编辑按钮
	$("body").delegate(".staffEdit-link", "click", function() {
		$("#multiSelect-box").kendoMultiSelect({
	        dataTextField: "text",
	        dataValueField: "value",
	        autoClose: false
	    });
		setValidator($("input[name='contactMailForEdit']"), "required", true, emailNoRule.required);
		setValidator($("input[name='contactMailForEdit']"), "pattern", emailNoRule.pattern, emailNoRule.message);
		setValidator($("input[name='contactTelForEdit']"), "required", true, mobileNoRule.required);
		setValidator($("input[name='contactTelForEdit']"), "pattern", mobileNoRule.pattern, mobileNoRule.message);
		setValidator($("input[name='contactNameForEdit']"), "required", true, realNameRule.required);
		$.ajax({
			type: "POST",
			url: basepath+"/employee/employeeDetail",
			data: {employeeId:$.trim($(this).parent().next().text())},
			dataType: "json",
			success: function(data){
				if(data.status == "SUCCESS"){
					if( data.data.accountActivateStatus=="UNACTIVATE" ){
						$("th[name='title']").text("用户编号");
						$("span[name='content']").text(data.data.id == null ? "":data.data.id);
						$("input[name='idForUpdate']").val(data.data.id == null ? "":data.data.id);
					}else{
						$("th[name='title']").text("用户名");
						$("input[name='idForUpdate']").val(data.data.id == null ? "":data.data.id);
						$("span[name='content']").text(data.data.username == null ? "":data.data.username);
					}
					$("input[name='contactMailForEdit']").val(data.data.employeeMail == null ? "":data.data.employeeMail);
					$("input[name='userIdForEdit']").val(data.data.id == null ? "":data.data.id);
					var employeeValue = new Array(data.data.roleDTOList.length);
					for(var i = 0 ; i < data.data.roleDTOList.length ; i++) {
						employeeValue[i] = data.data.roleDTOList[i].roleId;
	            	}
					var multiselect = $("#multiSelect-box").data("kendoMultiSelect");
					multiselect.value(employeeValue);
					$("input[name='contactNameForEdit']").val(data.data.realName == null ? "":data.data.realName);
					$("input[name='contactTelForEdit']").val(data.data.employeeTel == null ? "":data.data.employeeTel);
					$("input[name='jobTitleForEdit']").val(data.data.employeeJobTitle == null ? "":data.data.employeeJobTitle);
					$("input[name='jobNoForEdit']").val(data.data.jobNo == null ? "":data.data.jobNo);
					$("span[name='contactNameForEdit']").text(data.data.realName == null ? "":data.data.realName);
				}else{
					var win = $(e.target).closest(".k-window");
                    var overlay = win.prev(".k-overlay");
                    if(win.is(":visible")){
                        win.css("display", "none");
                        overlay.css("display", "none");
                    }
                    showAlertWin(data.message);
				}
			}
		});		
    });
	
	// 编辑员工信息的校验
	editInputCheck();
	
	// 删除按钮(给idForDelete赋值)
	$("body").delegate(".staffDel-link", "click", function(e) {
		$("#idForDelete").val($(this).parent().next().text());
    });
	
	// 删除->确定按钮
	$("body").delegate("#confirmBtnForDelete", "click", function(e) {
		$.ajax({
            type: "POST",
            url: basepath+"/employee/delete",
            data: {employeeId:$.trim($("#idForDelete").val())},
            dataType: "json",
            success: function(data){
            	if(data.status == "SUCCESS"){
            		var win = $(e.target).closest(".k-window");
            		var overlay = win.prev(".k-overlay");
            		if(win.is(":visible")){
            			win.css("display", "none");
            			overlay.css("display", "none");
            		}
            		searchResult();
				}else{
					var win = $(e.target).closest(".k-window");
            		var overlay = win.prev(".k-overlay");
            		if(win.is(":visible")){
            			win.css("display", "none");
            			overlay.css("display", "none");
            		}
            		showAlertWin(data.message);
				}
            }
        });
    });
	
	// 重置密码按钮(给idForRestPassword赋值)
	$("body").delegate(".resetStaff-link", "click", function(e) {
		$("#idForRestPassword").val($(this).parent().next().text());
    });
	
	// 重置密码->确定按钮
	$("body").delegate("#confirmBtnForRestPassword", "click", function(e) {
		$.ajax({
            type: "POST",
            url: basepath+"/employee/restPassword",
            data: {userId:$.trim($("#idForRestPassword").val())},
            dataType: "json",
            success: function(data){
            	if(data.status == "SUCCESS"){
            		var win = $(e.target).closest(".k-window");
            		var overlay = win.prev(".k-overlay");
            		if(win.is(":visible")){
            			win.css("display", "none");
            			overlay.css("display", "none");
            		}
            		searchResult();
				}else{
					var win = $(e.target).closest(".k-window");
					var overlay = win.prev(".k-overlay");
					if(win.is(":visible")){
						win.css("display", "none");
						overlay.css("display", "none");
					}
					showAlertWin(data.message);
				}
            }
        });
    });
	
	// 重发邮件按钮(给idForResendMail赋值)
	$("body").delegate(".resendMail-link", "click", function(e) {
		$("#idForResendMail").val($(this).parent().next().text());
		$("span[name='mailAddr']").text($(this).parent().prev().prev().prev().text());
    });
	
	// 重发邮件 -> 提交
	$("body").delegate("#confirmBtnForResendMail", "click", function(e) {
		$.ajax({
            type: "POST",
            url: basepath+"/employee/resend",
            data: {userId:$.trim($("#idForResendMail").val())},
            dataType: "json",
            success: function(data){
            	if(data.status == "SUCCESS"){
            		var win = $(e.target).closest(".k-window");
            		var overlay = win.prev(".k-overlay");
            		if(win.is(":visible")){
            			win.css("display", "none");
            			overlay.css("display", "none");
            		}
				}else{
					var win = $(e.target).closest(".k-window");
					var overlay = win.prev(".k-overlay");
					if(win.is(":visible")){
						win.css("display", "none");
						overlay.css("display", "none");
					}
					showAlertWin(data.message);
				}
            }
        });
	});
	
	// 个性权限
	$("body").delegate(".permission-link", "click", function(e) {
		var id = $(this).parent().next().text();
		window.open(basepath + "/employee/gotoEditPermissions?userId=" + id);
    });
});

function searchResult(){
	$(".staffListGrid").remove();
	$("#staffListGridWrap").append("<div class=\"staffListGrid\"></div>");
	
	sequence = 0;
	var grid = $(".staffListGrid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            transport: {
                read: {
                    data: {
                    	realName:$.trim($("input[name='contactNameForSearch']").val()),
                    	username:$.trim($("input[name='usernameForSearch']").val()),
                    	roleId:$.trim($("#roleForSearch").val())=="ALL"?"":$.trim($("#roleForSearch").val()),
                    	accountActivateStatus:$.trim($("#activeStatusForSearch").val())=="ALL"?"":$.trim($("#activeStatusForSearch").val())
                    },
                    type : "POST",
                    url: basepath+"/employee/result"
                }
            },
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			//解析远程响应的数据
			schema:{
				data: "data",
				total: function(data) {
					var total = data.totalNum;
	  				if(total == 0){
	  					$("#staffListGridWrap .k-grid-header-wrap").css("overflow-x","auto");
	  				}else {
	  					$("#staffListGridWrap .k-grid-header-wrap").css("overflow-x","");
					}
	  				return total;
				}
			}
        },
        columns: [{
            field: "sequence",
            title: "序号",
            width: 50,
            template: function(){
            	sequence++;
            	return sequence;
            },
			attributes: {
                style: "text-align: center"
            }
        },{
            field: "realName",
            title: "员工姓名",
            width: 120
        },{
            field: "username",
            title: "用户名",
            width: 120
        },{
            field: "employeeMail",
            title: "邮箱",
            width: 150
        },{
            field: "roleDTOList",
            title: "角色",
            width: 150,
            template: function(data){
            	var roleNameStr="";
            	for(var i = 0;i < data.roleDTOList.length; i++) {
            		roleNameStr += data.roleDTOList[i].roleName + " ";
            	}
            	return roleNameStr; 
            }
        },{
        	field : "accountActivateStatus",
			title : "账号状态",
			width: 150,
			attributes:{
	            	style: "text-align:center"
	        },
			template: function (data) {
				if(data.accountActivateStatus == "UNACTIVATE")
					return "未激活";
				return "已激活";
			},
			attributes: {
                style: "text-align: center"
            }
        },{
            title: "操作",
            sortable: false,
            width:300,
            template: function(data){
            	var url="";
            	if(employeeDetailAuth){
            		url += "<a class='comRow-link staffDetail-link' href='javascript:void(0);'>详情</a>";
            	}
            	if(employeeEditAuth){
            		url += "<a class='comRow-link staffEdit-link' href='javascript:void(0);'>编辑</a>";
            	}
            	if(employeeDeleteAuth){
            		url += "<a class='comRow-link staffDel-link' href='javascript:void(0);'>删除</a>";
            	}
            	if(data.accountActivateStatus == "ACTIVATED" && employeeResetPasswordAuth){
            		url += "<a class='comRow-link resetStaff-link' href='javascript:void(0);'>重置密码</a>";
            	}else{
            		if(employeeRetryMailAuth){
            			url += "<a class='comRow-link resendMail-link' href='javascript:void(0);'>重发邮件</a>";
            		}
            	}
            	if(permissionsAuth){
            		url += "<a class='comRow-link permission-link' href='javascript:void(0);'>个性权限</a>";
            	}
            	return url; 
            },
            editable: "popup"
        },{
        	field: "id",
        	title: "id",
        	hidden: true
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            pageSize: pageSize,
            page: 1,
            buttonCount: 5,  //显示页数
            messages: {
                display: "共<span class='sumData'>{2}</span>条数据",
                empty: "没有数据",
                page: "页",
                of: "/ {0}",
                itemsPerPage: "条/页",
                first: "第一页",
                previous: "前一页",
                next: "下一页",
                last: "最后一页"
            }
        }
	});
	grid.data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}

// 新增员工有效性验证
function addInputCheck(){
	// 邮箱格式验证
	$("body").delegate("input[name='contactMailForAdd']", "blur", function() {
		var validator = $("input[name='contactMailForAdd']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
    });
	// 联系电话格式验证
	$("body").delegate("input[name='contactTelForAdd']", "blur", function() {
		var validator = $("input[name='contactTelForAdd']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
    });
	// 联系人姓名必要性验证
	$("body").delegate("input[name='realNameForAdd']", "blur", function() {
		var validator = $("input[name='realNameForAdd']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
    });
	// 新增员工 -> 保存
	$("body").delegate("#saveBtnForAdd", "click", function(e) {
		var validator = $("input[name='contactMailForAdd']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
			return;
		};
		var validator = $("input[name='contactTelForAdd']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
			return;
		};
		var validator = $("input[name='realNameForAdd']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
			return;
		};
		if($("#multiSelect-box-add").val() == null || $("#multiSelect-box-add").val().length < 1){
			$("#validateForRoleAdd").css('display','block'); 
			return;
		}else{
			$("#validateForRoleAdd").css('display','none'); 
		}
//		var validator = $("#addEmployeeForm").kendoValidator().data("kendoValidator");
//		if(!validator.validate()) {
//    		return;
// 		};
 		$.ajax({
 			type: "POST",
 			url: basepath+"/employee/add",
 			contentType : "application/json",
            data: JSON.stringify({id:$.trim($("span[name='idForAdd']").text()),realName:$.trim($("input[name='realNameForAdd']").val()),employeeMail:$.trim($("input[name='contactMailForAdd']").val()),jobNo:$.trim($("input[name='jobNoForAdd']").val()),
            	roleIdList:$("#multiSelect-box-add").val(),employeeTel:$.trim($("input[name='contactTelForAdd']").val()),employeeJobTitle:$.trim($("input[name='jobTitleForAdd']").val())}),
 				dataType: "json",
 				success: function(data){
 					if(data.status == "SUCCESS"){
 						var win = $(e.target).closest(".k-window");
 						var overlay = win.prev(".k-overlay");
 						if(win.is(":visible")){
 							win.css("display", "none");
 							overlay.css("display", "none");
 						}
 						searchResult();	
 					}else{
 						var win = $(e.target).closest(".k-window");
 						var overlay = win.prev(".k-overlay");
 						if(win.is(":visible")){
 							win.css("display", "none");
 							overlay.css("display", "none");
 						}
 						showAlertWin(data.message);
 					}
 				}
 		});
    });
}

// 编辑员工信息有效性验证
function editInputCheck(){
	// 邮箱格式验证
	$("body").delegate("input[name='contactMailForEdit']", "blur", function() {
		var validator = $("input[name='contactMailForEdit']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
	});
	// 联系电话格式验证
	$("body").delegate("input[name='contactTelForEdit']", "blur", function() {
		var validator = $("input[name='contactTelForEdit']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
	});
	// 员工姓名验证
	$("body").delegate("input[name='contactNameForEdit']", "blur", function() {
		var validator = $("input[name='contactNameForEdit']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
	});
	// 编辑 -> 提交
	$("body").delegate("#saveBtnForEdit", "click", function(e) {
		var validator = $("input[name='contactMailForEdit']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
 		var validator = $("input[name='contactTelForEdit']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
 		var validator = $("input[name='contactNameForEdit']").kendoValidator().data("kendoValidator");
		if(!validator.validate()) {
    		return;
 		};
		if($("#multiSelect-box").val() == null || $("#multiSelect-box").val().length < 1){
			$("#validateForRole").css('display','block'); 
			return;
		}else{
			$("#validateForRole").css('display','none'); 
		}
		$.ajax({
            type: "POST",
            url: basepath+"/employee/modify",
            dataType: "json",
            contentType : "application/json",
            data: JSON.stringify({id:$.trim($("input[name='idForUpdate']").val()),employeeMail:$.trim($("input[name='contactMailForEdit']").val()),jobNo:$.trim($("input[name='jobNoForEdit']").val()),
            		roleIdList:$("#multiSelect-box").val(),employeeTel:$.trim($("input[name='contactTelForEdit']").val()),employeeJobTitle:$.trim($("input[name='jobTitleForEdit']").val())}),
            success: function(data){
            	if(data){
                    var win = $(e.target).closest(".k-window");
                    var overlay = win.prev(".k-overlay");
                    if(win.is(":visible")){
                        win.css("display", "none");
                        overlay.css("display", "none");
                    }
            	}
            	searchResult();	
            },
            error: function(XMLHttpRequest){
            	var win = $(e.target).closest(".k-window");
            	var overlay = win.prev(".k-overlay");
            	if(win.is(":visible")){
            		win.css("display", "none");
            		overlay.css("display", "none");
            	}
            	showAlertWin(XMLHttpRequest.responseText);
			}
        });
    });
}
