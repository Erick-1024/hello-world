/**
 * Created by DELLO on 2016/6/13.
 */
$(function(){
    $(".pro-title-right").click(function(){
        $(this).parent().next().toggle();
        var ty = $(this).parent().next().is(":hidden");
        if(ty==false){
            $(this).html("折叠");
        }else if(ty==true){
            $(this).html("展开");
        }
    });
    
    $("body").on("click",".del-once",function(){
        var state = $(this).hasClass("close-btn");
        if(state==false){
            $(this).parent().siblings().find("input").val('');
            $(this).addClass("close-btn");
            $(this).prev().addClass("close-btn");
        }
    });

    $("body").on("click",".removeNew",function(){
    	$(this).parent().parent().parent().remove();
    });
	//绑定时间控件
    $("body").delegate(".endDate", "focusin", function(){
        $(this).datepicker();
    });
});
//提示框封装
function messageBox(txt){
    $(".txt-cot").html(txt);
    $(".popup-img-one").addClass("notice-icon01").show();
    $('.xp-out').show();
}
////////添加一行、删除一行封装方法///////
/**
 * 为table指定行添加一行
 *
 * tab 表id
 * row 行数，如：0->第一行 1->第二行 -2->倒数第二行 -1->最后一行
 * trHtml 添加行的html代码
 *
 */
var chTr_index='';    //用于标记修改的行
function addTr(tab, row, trHtml){
    //获取table最后一行 $("#tab tr:last")
    //获取table第一行 $("#tab tr").eq(0)
    //获取table倒数第二行 $("#tab tr").eq(-2)
    var $tr=$("#"+tab+" tr").eq(row);
    if($tr.size()==0){
        messageBox("指定的table id或行数不存在！");
        return;
    }
    $tr.after(trHtml);
}

function delTr(ckb){
    //获取选中的复选框，然后循环遍历删除
    var ckbs=$("input[name="+ckb+"]:checked");
    if(ckbs.size()==0){
        messageBox("要删除指定行，需选中要删除的行！");
        return;
    }
    ckbs.each(function(){
        $(this).parent().parent().remove();
    });
    //删除后更新序号
    var len = $('.new-table-one tr').length;
    for(var i = 1;i<len;i++){
      //  var j = i-1;
        $('.new-table-one tr:eq('+i+') td:first').text(i);
      /*  $('.new-table-one tr:eq('+i+') td:eq(1)').find("input").attr("name","projectFactors["+j+"].companyName");
        $('.new-table-one tr:eq('+i+') td:eq(2)').find("input").attr("name","projectFactors["+j+"].organizationCode");
        $('.new-table-one tr:eq('+i+') td:eq(3)').find("input").attr("name","projectFactors["+j+"].businessLicenceCode");
        $('.new-table-one tr:eq('+i+') td:eq(4)').find("input").attr("name","projectFactors["+j+"].taxRegistrationCertificateCode");
        $('.new-table-one tr:eq('+i+') td:eq(5)').find("input").attr("name","projectFactors["+j+"].accountNo");
        $('.new-table-one tr:eq('+i+') td:eq(6)').find("input").attr("name","projectFactors["+j+"].status");*/
    }
}
//修改 获取表格数据显示到弹出窗口
function chTr(ckb){
    var ckbs=$("input[name="+ckb+"]:checked");
    if(ckbs.size()==0){
        messageBox("要修改指定行，需选中要修改的行！");
    }else if(ckbs.size()!=1){
        messageBox("一次只能修改一行！");
    }else{
        var _index = ckbs.parent().parent().index();
        chTr_index = _index;
        var tab = $(".m-table tr").eq(_index).find("td");
        for(var i=1;i<6;i++){
            $(".trCh"+i+"").val(tab.eq(i).find("span").html());
        }
        $(".trCh6").val(tab.eq(i).find("input").val());
        $(".style-box").show();
        return chTr_index;
    }
}
//只修该状态
function chTrIn(ckb){
    var ckbs=$("input[name="+ckb+"]:checked");
    if(ckbs.size()==0){
        messageBox("要修改指定行，需选中要修改的行！");
    }else if(ckbs.size()!=1){
        messageBox("一次只能修改一行！");
    }else{
        var _index = ckbs.parent().parent().index();
        chTr_index = _index;
        var tab = $(".m-table tr").eq(_index).find("td");
        for(var i=1;i<6;i++){
            $(".trCh"+i+"").html(tab.eq(i).find("span").html());
        }
        $(".trCh6").val(tab.eq(6).find("input").val());
        $(".style-box").show();
        return chTr_index;
    }
}
function trChangeTwo(){
    var array = $('.trCh6').val();
    var ch_tab = $(".new-table-one tr").eq(chTr_index).find("td");
    var zt = "";
    if(array=="NORMAL"){
        zt="正常";
    }else if(array=="PAUSE"){
        zt="暂停";
    }
    ch_tab.eq(6).find("span").html(zt);
    ch_tab.eq(6).find("input").val(array);

    $(".style-box").hide();
}
//修改 修改完成保存数据到页面
function trChange(){
    var array = $('.ch-form').serializeArray();
    var ch_tab = $(".new-table-one tr").eq(chTr_index).find("td");
    var zt = "";
    if(array[5].value=="NORMAL"){
        zt="正常";
    }else if(array[5].value=="PAUSE"){
        zt="暂停";
    }
    for(var i=0;i<5;i++){
        ch_tab.eq(i+1).find("span").html(array[i].value);
        ch_tab.eq(i+1).find("input").val(array[i].value);
    }
    ch_tab.eq(6).find("span").html(zt);
    ch_tab.eq(6).find("input").val(array[5].value);
    $(".style-box").hide();
}
//全选，
function allCheck(allCkb, items){
    $("#"+allCkb).click(function(){
        $('[name='+items+']:checkbox').attr("checked", this.checked );
    });
}

////////添加一行、删除一行测试方法///////
$(function(){
    //全选
    allCheck("allCkb", "ckb");
});

function addTr2(tab, row,xs,txt1,txt2,txt3,txt4,txt5,txt6){
    var nx = xs-1;
	var txt7="";
	if(txt6=="NORMAL"){
	txt7="正常";	
	}else if(txt6=="PAUSE"){
	txt7="暂停";
         }
    var trHtml=$("<tr class=\"top-list-two\">" +
                "<th><input type=\"checkbox\" class=\"input-new\" name='ckb'></th>" +
                "<td>"+xs+"</td>" +
                "<td><span name='companyName'>"+txt1+"</span><input type='hidden' name='projectFactors["+nx+"].companyName' value='"+txt1+"'></td>" +
                "<td><span name='organizationCode'>"+txt2+"</span><input type='hidden' name='projectFactors["+nx+"].organizationCode' value='"+txt2+"'></td>" +
                "<td><span name='businessLicenceCode'>"+txt3+"</span><input type='hidden' name='projectFactors["+nx+"].businessLicenceCode' value='"+txt3+"'></td>" +
                "<td><span name=taxRegistrationCertificateCode>"+txt4+"</span><input type='hidden' name='projectFactors["+nx+"].taxRegistrationCertificateCode' value='"+txt4+"'></td>" +
                "<td><span name='accountNo'>"+txt5+"</span><input type='hidden' name='projectFactors["+nx+"].accountNo' value='"+txt5+"'></td>" +
                "<td><span name='factorStatus'>"+txt7+"</span><input type='hidden' id= 'project-factor-status' name='projectFactors["+nx+"].status' value='"+txt6+"'></td>" +
                "</tr>");
    addTr(tab, row, trHtml);
}

function delTr2(){
    delTr('ckb');
}
function addTr3(){
    var array = $('.out-new-form').serializeArray();
    var tb = $(".new-table-one").find("tr").length;
    closeCr();
    addTr2('tab', -1,tb,array[0].value,array[1].value,array[2].value,array[3].value,array[4].value,array[5].value);
}
function closeCh(){
    $(".style-box").hide();
}
function closeCr(){
    $(".new-style-box").hide();
}
function newCr(){
    $(".out-new-form").find("input").val("");
    $(".out-new-form").find("select").val("正常");
    $(".new-style-box").show();
}
function closeNews(){
    $(".news-box").hide();
}
var clickNum =1;
function newTr(tab){
    var _html = $('<tr>'+
        '<th>合同版本日期</th>'+
        '<td>'+
        '<input type="text" class="version-class" name="version" style="width:120px;border:1px solid #fff !important;outline:none;">'+
        '<span class="pro-error">错误提示</span>'+
        '</td>'+
        '<th>名称</th>'+
        '<td>'+
        '<input type="text" name="documentName" >'+
        '<span class="pro-error">错误提示</span>'+
        '</td>'+
        '<td class="file-top">'+
        '<label><input type="file" class="fileBox" name="file" value="" id ="additionInformationMedia'+clickNum+'"><input type="hidden" name="mediaId" id="additionInformationMedia'+clickNum+'hidden"><a class="form-search-link" href="javascript:void(0);">上传附件</a></label>'+
        '<a class="form-search-link close-btn" href="javascript:void(0);">查看</a>'+
        '<a class="form-search-link close-btn" href="javascript:void(0);" onclick="removeTr($(this));">删除</a>'+
        '</td>'+
        '</tr>');
    var $tr=$("#"+tab+" tr").eq(-1);
    $tr.after(_html);
	clickNum+=1;
}
function removeTr(ths){
    var state = ths.hasClass("close-btn");
    if(state==false){
    	ths.parent().parent().remove();
    }
}
//修改合同删除
function removeNew(ths){
	ths.parent().parent().remove();
}


