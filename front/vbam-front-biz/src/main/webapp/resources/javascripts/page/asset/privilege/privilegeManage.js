var inData = [];
var outData =[];
var $currentAll = 'false';
$(function(){
	 
	 //所有权限点击全选
	 $("body").on("change",".all-authority",function(){
		var all = $("#all-authority").is(":checked");
		$("#all-authority").val(all);
	 });
	 //当前所有权限点击全选
	 $("body").on("change",".currentAll",function(){
			var currentAll = $("#currentAll").is(":checked");
			$("#currentAll").val(currentAll);
		 });
		
	    //初始化新增权限弹窗2
	    new PopWindow(".limit-next-btn", {
	        title: "新增权限",
	        width: 800,
	        reload: true,
	        template: "#template-resetPwd-limits"
	    }).init();

	    //新增权限弹窗2 表格全选功能
        $("body").on("click",".all-checked",function(){
            outData=[];
            inData=[];
            var $type = $("[name=all-checked]").is(":checked");
            if($type==true){
                $("[name=limits-choose]").prop("checked","checked");
                $currentAll = "true";
            }else if($type==false){
                $("[name=limits-choose]").removeAttr("checked","checked");
                $currentAll = "false";
            }
        });

        $("body").on("click","input[name=limits-choose]",function(){
            var $type = $(this).is(":checked");
            var $id = $(this).val();
            if($currentAll=="true"){
                if($type==true){
                    $.each(outData,function(index,item){
                        if(item==$id){
                            outData.splice(index,1);
                        }
                    })
                }else if($type==false){
                    outData.push($id);
                }
            }else if($currentAll=="false"){
                if($type==true){
                    inData.push($id);
                }else if($type==false){
                    $.each(inData,function(index,item){
                        if(item==$id){
                            inData.splice(index,1);
                        }
                    })
                }
            }

        });
      //新增权限弹框２点击搜索
      $("body").on("click","#searchList-two",function (){
      	getAddPrivilegeCustomerListTwoInfo(); 
      	$("#customerNameQuerySting").val($("#customerNameQuery").val());
      });
        //新增权限点击事件触发弹窗2
        $("body").on("click","#limits-next-btn",function(){
        	var id = $('input[name=client-choose]:checked').val();
        	if(id == undefined) {
        		showAlertWin("请选择客户");
        		return;
        	}
            closeThePop();
            $(".limit-next-btn").click();
    		$("#masterId").val(id);
            $(".k-widget.k-window").css("top","25%");
            //调用新增２列表权限列表
            getAddPrivilegeCustomerListTwoInfo();
        });
 });       
 
//弹窗2表格初始化
function getAddPrivilegeCustomerListTwoInfo(){
	$("#limits-out-grid").empty();
	var sequence = 0;
	 var grid =$("#limits-out-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: false,  //列排序
        dataSource:{
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                	type : "post",
                    data: {
                    	customerName :$.trim($("#customerNameQuery").val()),
                    },
                    url:basepath + "/asset/privilege/queryCustomer4Add"
                }
        },
        serverPaging: true,
		serverSorting: true,
		serverFiltering: true,
        schema: {
			data: "data",
			total: function (data) {
				var total = data.totalNum;
  				if(total == 0){
  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","auto");
  				}else {
  					$("#manual-repayGrid .k-grid-header-wrap").css("overflow-x","");
				}
  				return total;
			}
		}
    },
    dataBound: onDataBound,
        columns: [{
            field: "sequence",
            title: "<label class ='currentAll'><input type='checkbox' id='currentAll' name='all-checked' value='false' class='input-new all-checked'>全选</label>",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template: function(item){
                return "<input type='checkbox' name='limits-choose' value='" +item.customerId + "' class='input-new customerId'>";
            }
        },{
            field: "sequence",
            title: "客户编号",
            width: 120,
            attributes: {
                style: "text-align: center"
            },
            template:function (data){
            	sequence ++;
            	return sequence;
            }
        },{
            field: "customerName",
            title: "客户名称",
            width: 300,
            attributes:{
                style:"text-align:center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 5,  //显示页数
            pageSize: 5,
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
//选中翻页之后回显被选中
function onDataBound(arg) {
    var $data = $("[name=limits-choose]");
    if($currentAll=="true"){
        $("[name=limits-choose]").prop("checked","checked");
        for(var i=0;i<$data.length;i++){
            var $o = $data.eq(i).val();
            $.each(outData,function(index,item){
                if(item==$o){
                    $("[name=limits-choose]").eq(i).removeAttr("checked","checked");
                }
            })
        }
    }else if($currentAll=="false"){
        for(var i=0;i<$data.length;i++){
            var $d = $data.eq(i).val();
            $.each(inData,function(index,item){
                if(item==$d){
                    $("[name=limits-choose]").eq(i).prop("checked","checked");
                }
            })
        }
    }

}
    

    