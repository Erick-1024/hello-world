/**
 * Created by DELLO on 2016/7/21.
 */
$(function(){
    //页面展示隐藏
    $(".pro-title-right").click(function(){
        $(this).parent().next().toggle();
        var ty = $(this).parent().next().is(":hidden");
        if(ty==false){
            $(this).html("折叠");
        }else if(ty==true){
            $(this).html("展开");
        }
    });

 
    
    //绑定上传文件事件
    $('body').on('click','.file', function() {
        $(this).prev().click();
    });
    //左侧导航栏鼠标滑动效果插件启动
    $('#client-nav').onePageNav();
    //新建企业资料弹窗
    $("body").on("click",".add-client-popup",function(){
        $(".client-popup").show();
    });
    //关闭新建企业资料弹窗
    $("body").on("click",".client-back",function(){
        $(".client-popup").hide();
    });
    //有两条数据时，删除第一条数据，删除整行
    $("body").on("click",".remove-acc",function(){
        $(this).parent().parent().remove();
    });

    //删除当前行数据，清空内容
    $("body").on("click",".delete-acc",function(){
        var fi = $(this).parent().siblings().find("input[type=file]");
        resetFileInput(fi);
        $(this).parent().siblings().find(".ok-icon img").hide();
        $(this).parent().siblings().find(".annex-txt").val("");
    });

    //时间空间 （针对弹窗中时间空间失效的情况）
    $("body").delegate(".startDate,.endDate", "focusin", function(){
        $(this).datepicker();
    });
    
    
    //客户信息（高级信息）新增        shareholder executive purchase sell financing  shareholder-add
    $("body").on("click",".shareholder-add",function(){
    	$(".client-shareholder").click();
    	moneyFormat();
        var validator = $("#stk-pp").kendoValidator().data("kendoValidator");
        $("#customer-stk").click(function() {
        	if(!validator.validate()) {
    			return;
    		};
    		shareholderOpen()
        });
    });
    $("body").on("click",".executive-add",function(){
    	$(".client-executive").click();
        var validator = $("#magid").kendoValidator().data("kendoValidator");
        $("#magid-btn").click(function() {
        	if(!validator.validate()) {
    			return;
    		};
    		executiveOpen();
        });
    });
    $("body").on("click",".purchase-add",function(){
    	$(".client-purchase").click();
    	moneyFormat();
        var validator = $("#purchase-form").kendoValidator().data("kendoValidator");
        $("#purchase-btn").click(function() {
        	if(!validator.validate()) {
    			return;
    		};
    		purchaseOpen();
        });
    });
    $("body").on("click",".sell-add",function(){
    	$(".client-sell").click();
    	moneyFormat();
        var validator = $("#sell-form").kendoValidator().data("kendoValidator");
        $("#sell-btn").click(function() {
        	if(!validator.validate()) {
    			return;
    		};
    		sellOpen();
        });
    });
    //新版新增（融资）
    $("body").on("click",".financing-add-btn",function(){
    	$(".client-financing").click();
    	moneyFormat();
        var validator = $("#out-new-form").kendoValidator().data("kendoValidator");
        $("#five-btn").click(function() {
        	if(!validator.validate()) {
    			return;
    		};
    		financingOpen();
        
        });
    });

    //客户信息（高级信息）修改  shareholder executive purchase sell financing
    //修改1
    $(".shareholder-ch-btn").on("click",function(){
        var $index = amendClientRowRead("shareholder");
        if($index!=undefined){
            $(".client-shareholder").click();
            moneyFormat();
            var $td = $("#shareholder tr").eq($index).find("td");
        
            $(".s-type").prev().find(".k-input").text($td.eq(0).find("span").html());
            $("#stkType").val($td.eq(0).find("input").val());
            $(".s-name").val($td.eq(1).find("span").html());
            $(".s-num").val($td.eq(2).find("span").html());
            $(".s-scale").val($td.eq(3).find("span").html());
            $(".s-mode").prev().find(".k-input").text($td.eq(4).find("span").html());
            $("#stkPayamtType").val($td.eq(4).find("input").val());
            $(".shareholder-index-id").val($index);
            if($td.eq(5).find("span").html()=="否"){
                $(".s-if").eq(0).find("input[type=radio]").removeAttr("checked");
                $(".s-if").eq(1).find("input[type=radio]").prop("checked","checked");
            }
            
            var validator = $("#stk-pp").kendoValidator().data("kendoValidator");
            $("#customer-stk").click(function() {
            	if(!validator.validate()) {
        			return;
        		};
        		shareholderOpen();
            
            });
        }
    });
    //修改2
    $(".executive-ch-btn").on("click",function(){
        var $index = amendClientRowRead("executive");
        if($index!=undefined){
            $(".client-executive").click();
            var $td = $("#executive tr").eq($index).find("td");

            $(".e-name").val($td.eq(0).find("span").html());
            $(".e-card").val($td.eq(2).find("span").html());
            $(".e-work").val($td.eq(3).find("span").html());
            $(".e-edu").prev().find(".k-input").text($td.eq(4).find("span").html());
            $("#magEducation").val($td.eq(4).find("input").val());
            $(".e-pro").val($td.eq(5).find("span").html());
            $(".e-exp").val($td.eq(6).find("div").html());
            $(".executive-index-id").val($index);
            if($td.eq(1).find("span").html()=="女"){
                $(".e-sex").eq(0).find("input[type=radio]").removeAttr("checked");
                $(".e-sex").eq(1).find("input[type=radio]").prop("checked","checked");
            }
            var validator = $("#magid").kendoValidator().data("kendoValidator");
            $("#magid-btn").click(function() {
            	if(!validator.validate()) {
        			return;
        		};
        		executiveOpen();
            
            });
        }
    });
    //修改3
    $(".purchase-ch-btn").on("click",function(){
        var $index = amendClientRowRead("purchase");
        if($index!=undefined){
            $(".client-purchase").click();
            moneyFormat();
            var $td = $("#purchase tr").eq($index).find("td");

            $(".p-name").val($td.eq(0).find("span").html());
            $(".p-num").val($td.eq(1).find("span").html());
            $(".p-scale").val($td.eq(2).find("span").html());
            $(".p-mode").prev().find(".k-input").text($td.eq(3).find("span").html());
            $("#settleType").val($td.eq(3).find("input").val());
            $(".p-bal").val($td.eq(4).find("span").html());
            $(".purchase-index-id").val($index);
            
            var validator = $("#purchase-form").kendoValidator().data("kendoValidator");
            $("#purchase-btn").click(function() {
            	if(!validator.validate()) {
        			return;
        		};
        		purchaseOpen();
            
            });
        }
    });
    //修改4
    $(".sell-ch-btn").on("click",function(){
        var $index = amendClientRowRead("sell");
        if($index!=undefined){
            $(".client-sell").click();
            moneyFormat();
            var $td = $("#sell tr").eq($index).find("td");
            
            $(".l-name").val($td.eq(0).find("span").html());
            $(".l-num").val($td.eq(1).find("span").html());
            $(".l-scale").val($td.eq(2).find("span").html());
            $(".l-year").val($td.eq(3).find("span").html());
            $(".l-bal").val($td.eq(4).find("span").html());
            $(".sell-index-id").val($index);
            
            var validator = $("#sell-form").kendoValidator().data("kendoValidator");
            $("#sell-btn").click(function() {
            	if(!validator.validate()) {
        			return;
        		};
        		sellOpen();
            
            });
        }
    });
    //修改5
    $(".financing-ch-btn").on("click",function(){
        var $index = amendClientRowRead("financing");
        if($index!=undefined){
            $(".client-financing").click();
            moneyFormat();
            var $td = $("#financing tr").eq($index).find("td");

            $(".f-name").val($td.eq(0).find("span").html());
            $(".f-bal").val($td.eq(1).find("span").html());
            $(".f-star").val($td.eq(2).find("span").html());
            $(".f-end").val($td.eq(3).find("span").html());
            $(".f-mode").val($td.eq(4).find("span").html());
            $(".f-other").val($td.eq(5).find("div").html());
            $(".financing-index-id").val($index);
            
            var validator = $("#out-new-form").kendoValidator().data("kendoValidator");
            $("#five-btn").click(function() {
            	if(!validator.validate()) {
        			return;
        		};
        		financingOpen();
            
            });
        }
    });
    //客户信息（高级信息）删除
    $(".client-del-btn").on("click",function(){
        var $this = $(this).parent().next().attr("id");
        removeClientRow($this);
    });

    
    //初始化股东操作弹窗
    new PopWindow(".client-shareholder", {
        title: "股东信息",
        width: 680,
        reload: true,
        template: "#template-resetPwd-one"
    }).init();
    //初始化高管人员操作弹窗
    new PopWindow(".client-executive", {
        title: "高管人员信息",
        width: 680,
        reload: true,
        template: "#template-resetPwd-two"
    }).init();
    //初始化采购操作弹窗
    new PopWindow(".client-purchase", {
        title: "采购信息",
        width: 680,
        reload: true,
        template: "#template-resetPwd-three"
    }).init();
    //初始化销售操作弹窗
    new PopWindow(".client-sell", {
        title: "销售信息",
        width: 680,
        reload: true,
        template: "#template-resetPwd-four"
    }).init();
    //初始化融资操作弹窗
    new PopWindow(".client-financing", {
        title: "融资信息",
        width: 680,
        reload: true,
        template: "#template-resetPwd-five"
    }).init();
    
    
    
    //初始化消息弹窗
    new PopWindow(".client-message", {
        title: "提示",
        width: 680,
        reload: true,
        template: "#template-resetPwd"
    }).init();
  //初始化消息弹窗
    new PopWindow(".suc-message-btn", {
        title: "提示",
        width: 680,
        reload: true,
        template: "#template-resetPwd-suc"
    }).init();
});

//清空input[type=file]的值
function resetFileInput(file){
    file.after(file.clone().val(""));
    file.remove();
}



//表格操作（增加）
function addClientRow(tab,trHtml){
    var $tr=$("#"+tab+" tr").eq(-1);
    if($tr.size()==0){
        $(".client-message").click();
        $(".client-x-txt").html("指定的table id或行数不存在！");
        return;
    }
    $tr.after(trHtml);
}
//表格操作（判断修改的行，返回修改的行号）
function amendClientRowRead(ckb){
    var ckbs=$("input[name="+ckb+"]:checked");
    if(ckbs.size()==0){
        $(".client-message").click();
        $(".client-x-txt").html("请选择要修改的行！");
    }else if(ckbs.size()!=1){
        $(".client-message").click();
        $(".client-x-txt").html("一次只能修改一行！");
    }else{
        var chTr_index =ckbs.parent().parent().index();  //用于标记修改的行,仅用于修改操作
        return chTr_index;
    }
}

//表格操作（删除）
function removeClientRow(ckb){
//获取选中的复选框，然后循环遍历删除
    var ckbs=$("input[name="+ckb+"]:checked");
    if(ckbs.size()==0){
        $(".client-message").click();
        $(".client-x-txt").html("请选择要删除的行！");
        return;
    }
    ckbs.each(function(){
        $(this).parent().parent().remove();
    });
    //删除后更新序号
    var len = $("#"+ckb+" tr").length;
    for(var i = 1;i<len;i++){
    	 $("#"+ckb+" tr:eq("+i+") th:last").html(i);
         if(ckb=='shareholder'){
             var shareholderTr = $("#"+ckb+" tr:eq("+i+")").find("td");
             shareholderTr.eq(0).find("input").attr("name","customerStks["+i+"].stkType");
             shareholderTr.eq(1).find("input").attr("name","customerStks["+i+"].stkName");
             shareholderTr.eq(2).find("input").attr("name","customerStks["+i+"].stkPayamt");
             shareholderTr.eq(3).find("input").attr("name","customerStks["+i+"].stkPcnt");
             shareholderTr.eq(4).find("input").attr("name","customerStks["+i+"].stkPayamtType");
             shareholderTr.eq(5).find("input").attr("name","customerStks["+i+"].stkStatus");
         }else if(ckb=='executive'){
             var executiveTr = $("#"+ckb+" tr:eq("+i+")").find("td");
             executiveTr.eq(0).find("input").attr("name","customerMags["+i+"].magName");
             executiveTr.eq(1).find("input").attr("name","customerMags["+i+"].magSex");
             executiveTr.eq(2).find("input").attr("name","customerMags["+i+"].magIdentityCardNo");
             executiveTr.eq(3).find("input").attr("name","customerMags["+i+"].magDutyType");
             executiveTr.eq(4).find("input").attr("name","customerMags["+i+"].magEducation");
             executiveTr.eq(5).find("input").attr("name","customerMags["+i+"].magProfession");
             executiveTr.eq(6).find("input").attr("name","customerMags["+i+"].experience");
         }else if(ckb=='purchase'){
             var purchaseTr = $("#"+ckb+" tr:eq("+i+")").find("td");
             purchaseTr.eq(0).find("input").attr("name","customerPurchases["+i+"].supplierName");
             purchaseTr.eq(1).find("input").attr("name","customerPurchases["+i+"].purchaseLastYear");
             purchaseTr.eq(2).find("input").attr("name","customerPurchases["+i+"].percent");
             purchaseTr.eq(3).find("input").attr("name","customerPurchases["+i+"].settleType");
             purchaseTr.eq(4).find("input").attr("name","customerPurchases["+i+"].accountPayableBalance");
         }else if(ckb=='sell'){
             var sellTr = $("#"+ckb+" tr:eq("+i+")").find("td");
             sellTr.eq(0).find("input").attr("name","customerSales["+i+"].saleCustomerName");
             sellTr.eq(1).find("input").attr("name","customerSales["+i+"].saleLastYear");
             sellTr.eq(2).find("input").attr("name","customerSales["+i+"].percent");
             sellTr.eq(3).find("input").attr("name","customerSales["+i+"].cooperationPeriod");
             sellTr.eq(4).find("input").attr("name","customerSales["+i+"].accountReceivableBalance");
         }else if(ckb=='financing'){
             var financingTr = $("#"+ckb+" tr:eq("+i+")").find("td");
             financingTr.eq(0).find("input").attr("name","customerMass["+i+"].financialInstitutionName");
             financingTr.eq(1).find("input").attr("name","customerMass["+i+"].amount");
             financingTr.eq(2).find("input").attr("name","customerMass["+i+"].startDate");
             financingTr.eq(3).find("input").attr("name","customerMass["+i+"].endDate");
             financingTr.eq(4).find("input").attr("name","customerMass["+i+"].guaranteeType");
             financingTr.eq(5).find("input").attr("name","customerMass["+i+"].remark");
         }
    }
}

//自动关闭弹窗
function closeClientPop(){
    $(".k-overlay").hide();
    $(".k-window").hide();
}


//输入数字校验
function checkNumTwo(obj) {
	obj.value = obj.value.replace(/[^\d]/g,"");
}
function checkNum(obj) {
	//obj.value = obj.value.replace(/[^\d]/g,"");
	obj.value = obj.value.replace(/^(100|100\\.[0]{1,3}|\d{1,2}|\d{1,2}\\.\d{1,3})$/);
}
//身份证校验
function checkIdentityCardNo(cardNo) {
	cardNo.value = cardNo.value.replace(/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/);
}
//百分比不能大于100  shareholder executive purchase sell financing
function shareholderOpen(){
        var $type = $(".s-type").prev().find(".k-input").text(),
        	$ty=$("#stkType").val(),
            $name = $(".s-name").val(),
            $num = $(".s-num").val(),
            $scale = $(".s-scale").val(),
            $mode = $(".s-mode").prev().find(".k-input").text(),
            $mo=$('#stkPayamtType').val(),
            $if = $(".s-if").find("input[type=radio]:checked").val(),
            $yes = $if=='yes'?"是":"否",
            $index = $(".shareholder-index-id").val(),
            $ind = $("#shareholder tr").length;

        closeClientPop();
        if($index==''){
            var html = $('<tr class="client-add-tr">'+
                        '<th><input type="checkbox" class="input-new" name="shareholder"></th>'+
                        '<th><span>'+$ind+'</span></th>'+
                        '<td><span name="stkType">'+$type+'</span><input type="hidden" name="customerStks['+$ind+'].stkType" value="'+$ty+'"></td>'+
                        '<td><span name="stkName">'+$name+'</span><input type="hidden" name="customerStks['+$ind+'].stkName" value="'+$name+'"></td>'+
                        '<td><span name="stkPayamt">'+$num+'</span><input type="hidden" name="customerStks['+$ind+'].stkPayamt" value="'+$num+'"></td>'+
                        '<td><span name="stkPcnt">'+$scale+'</span><input type="hidden" name="customerStks['+$ind+'].stkPcnt" value="'+$scale+'"></td>'+
                        '<td><span name="stkPayamtType">'+$mode+'</span><input type="hidden" name="customerStks['+$ind+'].stkPayamtType" value="'+$mo+'"></td>'+
                        '<td><span name="stkStatus">'+$yes+'</span><input type="hidden" name="customerStks['+$ind+'].stkStatus" value="'+$yes+'"></td>'+
                        '</tr>');
            addClientRow("shareholder",html);
        }else{
            var $td_ch = $("#shareholder tr").eq($index).find("td");
            $td_ch.eq(0).find("span").html($type);
            $td_ch.eq(0).find("input").val($ty);
            $td_ch.eq(1).find("span").html($name);
            $td_ch.eq(1).find("input").val($name);
            $td_ch.eq(2).find("span").html($num);
            $td_ch.eq(2).find("input").val($num);
            $td_ch.eq(3).find("span").html($scale);
            $td_ch.eq(3).find("input").val($scale);
            $td_ch.eq(4).find("span").html($mode);
            $td_ch.eq(4).find("input").val($mo);
            $td_ch.eq(5).find("span").html($yes);
            $td_ch.eq(5).find("input").val($yes);
        }
}
	
	
	
function executiveOpen(){
	var $name = $(".e-name").val(),
    $sex = $(".e-sex").find("input[type=radio]:checked").val(),//获取中文值
    $sex_c = $sex=='men'?"男":"女",//获取英文值
    $card = $(".e-card").val(),
    $work = $(".e-work").val(),
    $edu = $(".e-edu").prev().find(".k-input").text(),//获取中文值
    $education =$("#magEducation").val(),//获取英文值
    $pro = $(".e-pro").val(),
    $exp = $(".e-exp").val(),
    $index = $(".executive-index-id").val(),
    $ind = $("#executive tr").length;
closeClientPop();
if($index==''){
    var html = $('<tr class="client-add-tr">'+
        '<th><input type="checkbox" class="input-new" name="executive"></th>'+
        '<th>'+$ind+'</th>'+
        '<td><span name ="magName">'+$name+'</span><input type="hidden" name="customerMags['+$ind+'].magName" value="'+$name+'"></td>'+
        '<td><span name ="magSex">'+$sex_c+'</span><input type="hidden" name="customerMags['+$ind+'].magSex" value="'+$sex+'"></td>'+
        '<td><span name ="magIdentityCardNo">'+$card+'</span><input type="hidden" name="customerMags['+$ind+'].magIdentityCardNo" value="'+$card+'"></td>'+
        '<td><span name ="magDutyType">'+$work+'</span><input type="hidden" name="customerMags['+$ind+'].magDutyType" value="'+$work+'"></td>'+
        '<td><span name ="magEducation">'+$edu+'</span><input type="hidden" name="customerMags['+$ind+'].magEducation" value="'+$education+'"></td>'+
        '<td><span name ="magProfession">'+$pro+'</span><input type="hidden" name="customerMags['+$ind+'].magProfession" value="'+$pro+'"></td>'+
        '<td><div name ="experience" class="over-text">'+$exp+'</div><input type="hidden" name="customerMags['+$ind+'].experience" value="'+$exp+'"></td>'+
        '</tr>');
    addClientRow("executive",html);
}else{
    var $td_ch = $("#executive tr").eq($index).find("td");
    $td_ch.eq(0).find("span").html($name);
    $td_ch.eq(0).find("input").val($name);
    $td_ch.eq(1).find("span").html($sex_c);
    $td_ch.eq(1).find("input").val($sex);
    $td_ch.eq(2).find("span").html($card);
    $td_ch.eq(2).find("input").val($card);
    $td_ch.eq(3).find("span").html($work);
    $td_ch.eq(3).find("input").val($work);
    $td_ch.eq(4).find("span").html($edu);
    $td_ch.eq(4).find("input").val($education);
    $td_ch.eq(5).find("span").html($pro);
    $td_ch.eq(5).find("input").val($pro);
    $td_ch.eq(6).find("div").html($exp);
    $td_ch.eq(6).find("input").val($exp);
}
}



function purchaseOpen(){
	 var $name =$(".p-name").val(),
     $num =$(".p-num").val(),
     $scale = $(".p-scale").val(),
     $mode = $(".p-mode").prev().find(".k-input").text(),
     $settleType =$("#settleType").val(),
     $bal = $(".p-bal").val(),
     $index = $(".purchase-index-id").val(),
     $ind = $("#purchase tr").length;
 closeClientPop();
 if($index==''){
     var html = $('<tr class="client-add-tr">'+
         '<th><input type="checkbox" class="input-new" name="purchase"></th>'+
         '<th>'+$ind+'</th>'+
         '<td><span name ="supplierName">'+$name+'</span><input type="hidden" name="customerPurchases['+$ind+'].supplierName" value="'+$name+'"></td>'+
         '<td><span name ="purchaseLastYear">'+$num+'</span><input type="hidden" name="customerPurchases['+$ind+'].purchaseLastYear" value="'+$num+'"></td>'+
         '<td><span name ="percent">'+$scale+'</span><input type="hidden" name="customerPurchases['+$ind+'].percent" value="'+$scale+'"></td>'+
         '<td><span name ="settleType">'+$mode+'</span><input type="hidden" name="customerPurchases['+$ind+'].settleType" value="'+$settleType+'"></td>'+
         '<td><span name ="accountPayableBalance">'+$bal+'</span><input type="hidden" name="customerPurchases['+$ind+'].accountPayableBalance" value="'+$bal+'"></td>'+
         '</tr>');
     addClientRow("purchase",html);
 }else{
     var $td_ch = $("#purchase tr").eq($index).find("td");
     $td_ch.eq(0).find("span").html($name);
     $td_ch.eq(0).find("input").val($name);
     $td_ch.eq(1).find("span").html($num);
     $td_ch.eq(1).find("input").val($num);
     $td_ch.eq(2).find("span").html($scale);
     $td_ch.eq(2).find("input").val($scale);
     $td_ch.eq(3).find("span").html($mode);
     $td_ch.eq(3).find("input").val($settleType);
     $td_ch.eq(4).find("span").html($bal);
     $td_ch.eq(4).find("input").val($bal);
 }
}




function sellOpen(){
	var $name =$(".l-name").val(),
    $num =$(".l-num").val(),
    $scale = $(".l-scale").val(),
    $mode = $(".l-year").val(),
    $bal = $(".l-bal").val(),
    $index = $(".sell-index-id").val(),
    $ind = $("#sell tr").length;
closeClientPop();
if($index==''){
    var html = $('<tr class="client-add-tr">'+
        '<th><input type="checkbox" class="input-new" name="sell"></th>'+
        '<th>'+$ind+'</th>'+
        '<td><span name ="saleCustomerName">'+$name+'</span><input type="hidden" name="customerSales['+$ind+'].saleCustomerName" value="'+$name+'"></td>'+
        '<td><span name ="saleLastYear">'+$num+'</span><input type="hidden" name="customerSales['+$ind+'].saleLastYear" value="'+$num+'"></td>'+
        '<td><span name ="percent">'+$scale+'</span><input type="hidden" name="customerSales['+$ind+'].percent" value="'+$scale+'"></td>'+
        '<td><span name ="cooperationPeriod">'+$mode+'</span><input type="hidden" name="customerSales['+$ind+'].cooperationPeriod" value="'+$mode+'"></td>'+
        '<td><span name ="accountReceivableBalance">'+$bal+'</span><input type="hidden" name="customerSales['+$ind+'].accountReceivableBalance" value="'+$bal+'"></td>'+
        '</tr>');
    addClientRow("sell",html);
}else{
    var $td_ch = $("#sell tr").eq($index).find("td");
    $td_ch.eq(0).find("span").html($name);
    $td_ch.eq(0).find("input").val($name);
    $td_ch.eq(1).find("span").html($num);
    $td_ch.eq(1).find("input").val($num);
    $td_ch.eq(2).find("span").html($scale);
    $td_ch.eq(2).find("input").val($scale);
    $td_ch.eq(3).find("span").html($mode);
    $td_ch.eq(3).find("input").val($mode);
    $td_ch.eq(4).find("span").html($bal);
    $td_ch.eq(4).find("input").val($bal);
}
}




function financingOpen(){
		var $name =$(".f-name").val(),
	  $bal = $(".f-bal").val(),
	  $star = $(".f-star").val(),
	  $end = $(".f-end").val(),
	  $mode = $(".f-mode").val(),
	  $other = $(".f-other").val(),
	  $index = $(".financing-index-id").val(),
	  $ind = $("#financing tr").length;
	closeClientPop();
	if($index==''){
	  var html = $('<tr class="client-add-tr">'+
	      '<th><input type="checkbox" class="input-new" name="financing"></th>'+
	      '<th>'+$ind+'</th>'+
	      '<td><span name ="financialInstitutionName">'+$name+'</span><input type="hidden" name="customerMass['+$ind+'].financialInstitutionName" value="'+$name+'"></td>'+
	      '<td><span name ="amount">'+$bal+'</span><input type="hidden" name="customerMass['+$ind+'].amount" value="'+$bal+'"></td>'+
	      '<td><span name ="startDate">'+$star+'</span><input type="hidden" name="customerMass['+$ind+'].startDate" value="'+$star+'"></td>'+
	      '<td><span name ="endDate">'+$end+'</span><input type="hidden" name="customerMass['+$ind+'].endDate" value="'+$end+'"></td>'+
	      '<td><span name ="guaranteeType">'+$mode+'</span><input type="hidden" name="customerMass['+$ind+'].guaranteeType" value="'+$mode+'"></td>'+
	      '<td><div name ="remark" class="over-text">'+$other+'</div><input type="hidden" name="customerMass['+$ind+'].remark" value="'+$other+'"></td>'+
	      '</tr>');
	  addClientRow("financing",html);
	}else{
	  var $td_ch = $("#financing tr").eq($index).find("td");
	  $td_ch.eq(0).find("span").html($name);
	  $td_ch.eq(0).find("input").val($name);
	  $td_ch.eq(1).find("span").html($bal);
	  $td_ch.eq(1).find("input").val($bal);
	  $td_ch.eq(2).find("span").html($star);
	  $td_ch.eq(2).find("input").val($star);
	  $td_ch.eq(3).find("span").html($end);
	  $td_ch.eq(3).find("input").val($end);
	  $td_ch.eq(4).find("span").html($mode);
	  $td_ch.eq(4).find("input").val($mode);
	  $td_ch.eq(5).find("div").html($other);
	  $td_ch.eq(5).find("input").val($other);
	}
}
//金额转换
function moneyFormat(){
	$("body input.moneyNum").on({
        focus: function(){
            $(this).attr("data-oval",$(this).val()); //将当前值存入自定义属性
        },
        blur: function(){
            var oldVal=($(this).attr("data-oval")); //获取原值
            var newVal=($(this).val()); //获取当前值
            if (oldVal!=newVal) {
                if(newVal == "" || isNaN(newVal)){
                    this.value = "";
                    return this.value;
                }
                var s = this.value;
                var temp;
                //n = n > 0 && n <= 20 ? n : 2;
                if(/.+(\..*\.|\-).*/.test(s)){
                    return;
                }
                s = parseFloat((s + "").replace(/[^\d\.\-]/g, "")).toFixed(2) + "";
                var l = s.split(".")[0].split("").reverse(),
                    r = s.split(".")[1];
                t = "";
                for(i = 0; i < l.length; i ++ ) {
                    t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length && (l[i+1]!='-')? "," : "");
                }
                temp = t.split("").reverse().join("") + "." + r;
                this.value = temp;
                return this.value;
            }
        }
    });
}







