var PAGESIZE = 10;
var sequence = 0;
$(function(){
	searchNotification();
		
	//点击"查询"
	$(".form-search-link").click(function(){
		searchNotification();
    });
	
	//点击"点击查看详情"
	$("body").on("click", "#detail-link", function(e){
		if($(this).parent().next().text()=="未读"){
			var obj = $(this);
			var notificationId = $(this).attr("notificationId");
			$.ajax({
		        type: 'POST',  
		        url: "updateReadStatus",
		        data: {
		        	notificationId: notificationId
		        },
		        success:function(data){
		    		if(data == "success")
						$(obj).parent().next().text("已读");
		        }
		    });
		}
	});
	
})

/**
 * 根据查询条件查询 消息中心
 */
function searchNotification(){
	$("#newsList-grid").remove();
	$("#notificationGridWrap").append("<div id=\"newsList-grid\" class=\"newsCenter-grid\"></div>");
    sequence = 0;
	var grid = $("#newsList-grid").kendoGrid({
		selectable: "row",  //设置可选择数据行
		sortable: true,  //列排序
		dataSource:{
			type: "json", //后台返回的数据类型
			transport: {
				read: {
					type: "post",
					data: {
							type: $.trim($("#notificationType").val()),
							beginTime: $.trim($("#beginTime").val()),
							endTime: $.trim($("#endTime").val()),
							isRead: $.trim($("#isRead .active").attr("name"))
					},
					url: basepath + "/message/notification/searchList"
				}
			},
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
			schema: {
				data: "data",
				total: "total"
			}
		},
		sortable: false,
		groupable: false,
		columns: [{
			field: "sequence",
			title: "序号",
			width: 50,
			template: function(data){
				sequence ++;
				return sequence;
			},
			attributes:{
                style: "text-align:center"
			}
		},{
			field: "typeDesc",
			title: "消息类型",
			width: 70
		},{
            title: "消息内容",
            width: 250,
            template: function(data){
            	var detailURL = data.detailURL;
            	var content = data.content;
            	if (detailURL != null) {
        			detailURL = basepath + detailURL;
            		content += "<a href='"+ detailURL +"' target='_blank' id='detail-link' notificationId='" + data.id + "'>点击查看详情</a>";
            	}
            	return content;
            }
        },{
            field: "readDesc",
            title: "消息状态",
            width: 50,
            attributes:{
                style: "text-align:center"
            }
        },{
			field: "createTime",
			title: "消息时间",
			width: 100,
			template: function(data){
				return new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss");
			},
            attributes:{
                style: "text-align:center"
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
	grid.data("kendoGrid").pager.bind('change', function(){
		sequence = 0;
	});
}