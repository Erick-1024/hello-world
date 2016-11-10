function showContractTable(){
	 //我的合同列表
	$("#agreementList-grid").empty();
    $("#agreementList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 5,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                	data:{},
                    type: "post",
                    url: basepath+"/user/contract"
                }
          },
        serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
        //解析远程响应的数据
        schema:{
        	data: "data",
			total: function(data){
				var total = data.totalNum;
				if(total == 0){
					$("#agreementList-grid .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$("#agreementList-grid .k-grid-header-wrap").css("overflow-x","");
				}
				return total;
			}
        }
        },
        columns: [{
            field: "createDate",
            title: "时间",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "fileName",
            title: "文件名称",
            width: 200
        },{
            field: "fileType",
            title: "文件类型",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
        	field:"mediaId",
            title: "操作",
            sortable: false,
            width:100,
            template:function(item){
            	return "<a class='userCenter-link' href='"+mediaserver+"imageservice?mediaImageId="+item.mediaId+"&mediaType=download'>下载</a>";
            },
//            command: [{
//                name: "detail",
//                text: "下载",
//                template: function(item){
//                    return "<a class='userCenter-link' href='javascript:void(0);'>"+ item.text +"</a>";
//                }
//            }],
            attributes: {
                style: "text-align: center"
            },
            editable: "popup"
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
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

};