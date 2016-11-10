$(function(){
    //初始化消息弹窗
    new PopWindow(".open-pop-btn",{
        title: "修改",
        width: 275,
        reload: true,
        template: "#modify_calendar_template"
    }).init();
    
    $('body').on('click', 'a[name=tipBoxButton]', function() {
		tipBoxTemplate.close();
	});
    
    //修改点击事件
    $("body").on("click",".amend-date-btn",function(){
        $(".open-pop-btn").click();
        $('#year').text($(this).parent().siblings(':eq(0)').text());
        $('#monthDay').text($(this).parent().siblings(':eq(1)').text());
        var currentRadio = $('#' + ($(this).parent().siblings(':eq(2)').text() == '是' ? 'true' : 'false'));
        currentRadio.addClass("public-radio-currentitem");
        currentRadio.parent().siblings().children().removeClass("public-radio-currentitem");
        $('#description').val($(this).parent().siblings(':eq(3)').text());
        $('#modifyOk').attr('date', $(this).attr('date'));
    });
    
    /*点击单选框切换*/
    $("body").on("click",".public-radio-item",function(){
        var $this = $(this);
        $this.find('i').addClass("public-radio-currentitem");
        $this.siblings().find('i').removeClass("public-radio-currentitem");
    });

    $('body').on('click', '#modifyOk', function() {
    	cana.post(basepath + "/setting/calendar/modify", {date:$(this).attr('date'), isHoliday:$('.public-radio-currentitem').attr('id'), description: $('#description').val()}, modifySuccess, modifyFail, null);
    });
    
    $('.form-search-link').click(function() {
		var grid = $("#monitorList-grid").data("kendoGrid");
		grid.setDataSource(getDataSource());
	});
    
    //文件上传
    $("#photos").kendoUpload({
        async: {
            saveUrl: basepath + "/setting/calendar/import",
            autoUpload: true
        },
        multiple: false,
        showFileList: false,
        upload: onUpload,
        success:onSuccess,
        error:onError
    });
    
    $('#export').click(function() {
    	window.open(basepath + "/setting/calendar/export?startDate=" + $('#startDate').val() + "&endDate=" + $('#endDate').val());
    });
    
    bindEntry();
    
	loadListGrid();
    
})

function bindEntry() {
	$("body").on("keydown", function(){
    	var evt = window.event || arguments.callee.caller.arguments[0];
    	//回车按钮事件
    	if(evt.keyCode==13){
    		if($('#modify-calendar-box-window').is(":visible")) {
    			$('#modifyOk').click();
    		} else if($('#tip-box-window').is(':visible')) {
    			tipBoxTemplate.close();
    		} else{
    			$('.form-search-link').click();
    		}
    	}
    });
}

function getDataSource(){
	return new kendo.data.DataSource({
		pageSize : 10,
		type : "json", // 后台返回的数据类型
		method : "post",
		transport : {
			read : {
				type : "post",
				url: basepath + "/setting/calendar/get/list",
				data: {
					startDate: $('#startDate').val(),
					endDate: $('#endDate').val()
				}
			}
		},
		serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
		schema : {
			data: "data",
			total: function(data){
				var total = data.totalNum;
				if(total == 0){
					$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","auto");
				}else{
					$("#monitorSrl-grid .k-grid-header-wrap").css("overflow-x","");
				}
				return total;
			}
		}
    });
}

function onSuccess(e){
	$("#upload-a-btn").text("Excel导入");
    if(e.response.status != "SUCCESS"){
    	$.each(e.files, function () {
    		showTipBox("notice-icon03", e.response.message);
        });
    }
    showTipBox("notice-icon02", '导入成功');
    var grid = $("#monitorList-grid").data("kendoGrid");
	grid.setDataSource(getDataSource());
}
function onError(){
	$("#upload-a-btn").text("Excel导入");
	showTipBox("notice-icon03", '导入失败');
}
function onUpload(e) {
	var files = e.files;
    $("#upload-a-btn").text("导入中...");
    $.each(files, function () {
       if (this.extension.toLowerCase() != ".xlsx" && this.extension.toLowerCase() != ".xls") {
    	   $("#upload-a-btn").text("Excel导入");
    	   showTipBox("notice-icon03", '文件格式不支持');
    	   e.preventDefault();
       }
   });
}

function modifySuccess(data) {
	$('#modifyCancel').click();
	showTipBox("notice-icon02", "修改成功");
	var grid = $("#monitorList-grid").data("kendoGrid");
	grid.dataSource.fetch();
}

function modifyFail(data) {
	$('#modifyCancel').click();
	showTipBox("notice-icon03", data);
}

function loadListGrid() {
	//初始化表格
	var grid = $("#monitorList-grid").kendoGrid({
	    selectable: "row",  //设置可选择数据行
	    sortable: false,  //列排序
        columns: [{
            field: "year",
            title: "年份",
            width: 120,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "risk",
            title: "日期",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                return data.month + "月" + data.day + "日";
            }
        },{
            field: "isHoliday",
            title: "是否假期",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
                return data.isHoliday ? "是" : "否";
            }
        },{
            field: "description",
            title: "假期名称",
            width: 140,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "操作",
            width: 100,
            attributes: {
                style: "text-align: center"
            },
            template: function(data){
            	if(set_calendar_modify) {
            		return "<a class='public-grid-link amend-date-btn' href='javascript:void(0);' date='" + data.date + "'>修改</a>";
            	} else {
            		return '';
            	}
            }
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
	}).data("kendoGrid");
	grid.setDataSource(getDataSource());
}