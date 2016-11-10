var pageSize = 10;
var sequence = 0;
var dataSource;
var serverData = {
					roleName: $.trim($("#roleName").val()),
					beginTime: $.trim($("#beginTime").val()),
					endTime: $.trim($("#endTime").val()),
					userType:$.trim($("#userType").val())
				};
var columns = [{
			    field: "sequence",
			    title: "序号",
			    width: 50,
			    template : function(data)
			    	{
						sequence ++;
						return sequence;
			    	},
					attributes: {
		                style: "text-align: center"
		            }
				},
                {
			    field: "roleName",
			    title: "角色名称",
			    width: 120,
			    template : function(data)
			    	{
			    		return data.roleName + '<param class value="1" />';
			    	}
				},
				{
			    field: "roleType",
			    title: "所属类型",
			    width: 120
				},
				{
			    field: "createtime",
			    title: "创建时间",
			    width: 150,
	            template : function(data){
	            		return new Date(data.createtime).format("yyyy-MM-dd hh:mm:ss");
					},
					attributes: {
		                style: "text-align: center"
		            },
					attributes: {
		                style: "text-align: center"
		            }
				},
				{
                title: "操作",
                sortable: false,
                width:80,
                template: function(data)
                {
                	var link = "";
                	if(companyDetailsAuth)
                		link += "<a target='_blank' href='gotoCompanyRoleDetails?roleId=" + data.id + "'>详情</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                	if(companyEditAuth)
                		link += "<a target='_blank' href='gotoEditCompanyRole?roleId=" + data.id + "'>编辑</a>";
                	return link;
                }
	            }
			];
var pageable = {
			      pageSizes: false,  //设置每页显示行数
			      buttonCount: 5,  //显示页数
			      pageSize: pageSize,//每页显示多少行
			      page: 1,  //当前页，默认设为1
			      messages: {
			          display: "共<span class='sumData'>{2}</span>条数据",
			          empty: "没有数据",
			          page: "页",
			          of: "/ {0}",
			          itemsPerPage: "条/页",
			          first: "第一页",
			          last: "最后一页",
				      previous: "前一页",
				      next: "下一页"
			      }
			  };
function setDataSource()
{
	dataSource = new kendo.data.DataSource({
					type: "json", //后台返回的数据类型
					transport: {
						    read: {
						    		  data: serverData,
									  type: "post",
								      url: basepath + "/role/getCompanyRoleList"	      
								  }
						  		},
					serverPaging: true,
					serverSorting: true,
					serverFiltering: true,
			  		schema:{
							data: "data",
							total: function(data) {
								var total = data.totalNum;
				  				if(total == 0){
				  					$("#companyRoleGrid .k-grid-header-wrap").css("overflow-x","auto");
				  				}else {
				  					$("#companyRoleGrid .k-grid-header-wrap").css("overflow-x","");
								}
				  				return total;
							}
			  		}	  		
	});
}
function generateGrid()
{
	$(".roleManageGrid").remove();
	$("#companyRoleGrid").append("<div class=\"roleManageGrid\"></div>");
	sequence = 0;
	var kendoGrid = $(".roleManageGrid").kendoGrid(
					{
				        selectable: "row",  //设置可选择数据行
						columns: columns,
						dataSource: {
							type: "json", //后台返回的数据类型
							pageSize: pageSize,//必须设置，否则无法向后台传回page 和 pageSize
							transport: {
								    read: {
								    		  data: serverData,
											  type: "post",
										      url: basepath + "/role/getCompanyRoleList"	      
										  }
								  		},
							serverPaging: true,
							serverSorting: true,
							serverFiltering: true,
					  		schema:{
									data: "data",
									total: "totalNum"
								   }	  		
						  },
						pageable: pageable
					});
	kendoGrid.data("kendoGrid").pager.bind('change',function()
	{
		sequence = 0;
	});
}
$(document).ready(function()
{
    $("#roleName").val("");
    $("#beginTime").val("");
    $("#endTime").val("");
	generateGrid();
    $("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		$('.form-search-link').click();
    	}
    });
	$(".form-search-link").click(function()
	{
		serverData = {
				roleName: $.trim($("#roleName").val()),
				beginTime: $.trim($("#beginTime").val()),
				endTime: $.trim($("#endTime").val()),
				userType:$.trim($("#userType").val())
		};
		generateGrid();
	});
});
