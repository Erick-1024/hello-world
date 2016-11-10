var PAGESIZE = 10;
$(function() {
	//初始化页面
	searchProjectDetailFactorList();
	
	$("body").on("keydown", function() {
		var evt = window.event || arguments.callee.caller.arguments[0];
		// 键盘回车事件
		if (evt.keyCode == 13) {
			$('.form-search-link').click();
		}
	})
	
	//合同下载
	$(".pro-down").click(function(){
		
    });
});
//项目详情查询
function searchProjectDetailFactorList() {
	sequence = 0;
	var id=$("#projectId").val();
	$("#monitorList-grid").empty();
    //初始化数据表格
    $("#monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                     data: {projectId:id },
                     type : "POST",
                     url: basepath + "/asset/project/projectDetails"
                 }
        },
		serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema:{
			data: "data",
			total: function(data){
				var total = data.total;
				if(total == 0){
					$(".accountManageGrid .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$(".accountManageGrid .k-grid-header-wrap").css("overflow-x","");
					}
				return total;
				}
			}
		},
		sortable: false,
		groupable: false,
        columns: [{
            field: "sequence",
            title: "序号",
            width: 30,
            template: function(data){
				sequence ++;
				return sequence;
			},
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "companyName",
            title: "资金方名称",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "organizationCode",
            title: "组织机构代码",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "businessLicenceCode",
            title: "营业执照号码",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "taxRegistrationCertificateCode",
            title: "税务登记证号码",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "accountNo",
            title: "银行账号",
            width: 100,
            template: function(data){
				return data.accountNo.formatBankAccountNo();
			},
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
    		pageSizes: false,  //设置每页显示行数
    		pageSize: PAGESIZE,
    		buttonCount: 5,  //显示页数
    		page: 1,
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
}


