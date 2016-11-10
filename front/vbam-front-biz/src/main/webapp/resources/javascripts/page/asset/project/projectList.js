var PAGESIZE = 10;
$(function(){
	//初始化列表
	searchProjectList();
	
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-btn').click();
    	}
    });
	
	//点击"搜索"
	$(".form-search-btn").click(function(){
		searchProjectList();
    });
	
	//点击"详情"
	$("body").on("click", ".detail-link", function(e){
    	var id = $(this).parent().next().text();
    	window.location.href=(encodeURI(basepath + "/asset/project/projectDetail?projectId=" + id  ));//+ "&curSubMenu=" + curSubMenu
    });
	
	//点击"修改"
    $("body").on("click", ".update-link", function(e){
    	var id = $(this).parent().next().text();
    	window.location.href=(encodeURI(basepath + "/asset/project/projectUpdate?projectId=" + id ));//+ "&curSubMenu=" + curSubMenu ?projectId=" + id
    });
});

/**
 * 根据条件查询项目列表
 */
function searchProjectList(){
	$("#monitorList-grid").empty();
	sequence = 0;
	var grid = $("#monitorList-grid").kendoGrid({
		selectable: "row",  // 设置可选择数据行
		sortable: true,  // 列排序
		dataSource:{
			type: "json", // 后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
						name: $.trim($("#name").val()),  					    // 项目名字
						coreCompanyName: $.trim($("#coreCompanyName").val()),   // 核心企业
						type: $.trim($("#type").val()),            				// 业务品种
						coreIndustry: $.trim($("#coreIndustry").val()),         // 所处行业
					},
					url: basepath + "/asset/project/searchList"
				}
			},
		serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema:{
			data: "data",
			total: function(data){
				var total = data.totalNum;
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
	columns: [
				{
					field : "sequence",
					title : "项目序号",
					width : 80,
					template: function(data){
						sequence ++;
						return sequence;
					},
					attributes : {
						style : "text-align: center"
					}
				},
				{
					field : "name",
					title : "项目名称",
					width : 100,
					attributes : {
						style : "text-align:center"
					}
				},
				{
					field : "coreCompanyName",
					title : "核心企业",
					width : 150,
					attributes : {
						style : "text-align: center"
					}
				},
				{
					field : "typeDesc",
					title : "业务品种",
					width : 100,
					attributes : {
						style : "text-align: center"
					}
				},
				{
					field : "coreIndustry",
					title : "所处行业",
					width : 100,
					attributes : {
						style : "text-align: center"
					}
				},
				{
					field : "statusDesc",
					title : "项目状态",
					width : 100,
					attributes : {
						style : "text-align: center"
					}
				},
				{
					field : "measure",
					title : "操作",
					width : 100,
					attributes : {
						style : "text-align: center"
					},
					template : function(data) {
						var url = "";
						if (detailAuth)
							url += "<a class='comRow-link detail-link' href='javascript:void(0); '>详情</a>";
						if (updateAuth) {
							url += "<a class='comRow-link update-link project-update' href='javascript:void(0);'>修改</a>";
						}
						return url;
					}
				}, {
					field : "id",
					title : "id",
					hidden : true
				} ],
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
	grid.data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}