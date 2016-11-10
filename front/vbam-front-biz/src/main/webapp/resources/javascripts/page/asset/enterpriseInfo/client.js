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
//        var fi = $(this).parent().siblings().find("input[type=file]");
        $(this).parent().prev().prev().prev().find("input[type=file]").val('')
//        resetFileInput(fi);
        $(this).parent().siblings().find(".ok-icon img").hide();
        $(this).parent().siblings().find(".annex-txt").val("");
        $(this).parent().find(".blue").css('display','none')
        $(this).parent().prev().find(".blue").css('display','none')
        $(this).parent().prev().prev().prev().find("input[name='mediaId']").val('');
    });
    //点击添加附件增加一行并把成功的变成不可编辑
    $("body").on("click",".add-acc",function(){
    	var historyData = $(this).parent().prev().find("ul.client-ul-st .info");
    	var nextSeq = parseInt(historyData.find("#sequence").val()) + 1;
        var htm = $('<ul class="client-ul-su">'+
                    '<li>'+
                    '<div class="float-left client-gray">文件名：</div>'+
                    '<div class="float-left success-txt" title="'+ historyData.find("#fileName").val() +'">'+ historyData.find("#fileName").val() +'</div>'+
                    '</li>'+
                    '<li>'+
                    '<div class="float-left client-gray">附件说明：</div>'+
                    '<div class="float-left success-txt" title="'+ historyData.find("#remark").val() +'">'+ historyData.find("#remark").val() +'</div>'+
                    '<div class="info" style="display:none;"><input id="categlory" name="categlory" value="'+ historyData.find("#categlory").val() +'">'+
                    '<input type="hidden" id="itemType" name="itemType" value="'+ historyData.find("#itemType").val() +'">'+
                    '<input type="hidden" id="fileName" name="fileName" value="'+ historyData.find("#fileName").val() +'">'+
                    '<input type="hidden" id="mediaId" name="mediaId" value="'+ historyData.find("#mediaId").val() +'">'+
                    '<input type="hidden" id="remark" name="remark" value="'+ historyData.find("#remark").val() +'">'+
                    '<input type="hidden" id="sequence" name="sequence" value="'+ historyData.find("#sequence").val() +'">'+
                    '</div></li>'+
                    '<li><a class="blue" href="' + mediaserver + '/imageservice?mediaImageId=' + historyData.find("#mediaId").val() + '&mediaType=video" target="_blank">查看</a></li>'+
                    '<li><a class="blue remove-acc" href="javascript:void(0);">删除</a></li>'+
                    '</ul>');
        var htl = $('<ul class="client-ul-st">'+
                    '<li class="up-div">'+
                    '<input id="'+ historyData.find("#categlory").val() + '-' + historyData.find("#itemType").val()  +'" name="image" class="input-file" type="file">'+
                    '<a class="input-file-a file" href="javascript:void(0);">上传附件</a>'+
                    '<span class="ok-icon" style="margin-left:4px;"><img src="'+ host +'images/success.png" style="display:none;"></span>'+
                    '<div class="info" style="display:none;"><input id="categlory" name="categlory" value="'+ historyData.find("#categlory").val() +'">'+
                    '<input type="hidden" id="itemType" name="itemType" value="'+ historyData.find("#itemType").val() +'">'+
                    '<input type="hidden" id="fileName" name="fileName">'+
                    '<input type="hidden" id="mediaId" name="mediaId">'+
                    '<input type="hidden" id="remark" name="remark">'+
                    '<input type="hidden" id="sequence" name="sequence" value="'+ nextSeq +'">'+
                    '</div></li>'+
                    '<li>'+
                    '<label style="margin-left:20px;">'+
                    '附件说明&emsp;<input class="annex-txt" type="text" placeholder="请输入附件说明" style="width:255px;" maxlength="20">'+
                    '</label>'+
                    '</li>'+
                    '<li><a class="blue look-acc" href="javascript:void(0);" style="display:none;" target="_blank">查看</a></li>'+
                    '<li><a class="blue delete-acc" href="javascript:void(0);" style="display:none;">删除</a></li>'+
                    '</ul>');
        var nul = $(this).parent().prev().find(".input-file").val();
        if(nul!=''){
            $(this).parent().prev().find(".client-ul-st").remove();
            var _this = $(this).parent().prev();
            htm.appendTo(_this);
            htl.appendTo(_this);
        }
    });

});
//清空input[type=file]的值
function resetFileInput(file){
    file.after(file.clone().val(""));
    file.remove();
}

//表格操作
var chTr_index='';    //用于标记修改的行
//表格操作（增加）
function addClientRow(tab,row,trHtml){
    var $tr=$("#"+tab+" tr").eq(row);
    if($tr.size()==0){
        messageBox("指定的table id或行数不存在！");
        return;
    }
    $tr.after(trHtml);
}
//表格操作（修改读取要修改数据）
function amendClientRowRead(ckb){
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
//表格操作（修改保存修改后的数据）
function amendClientRowWrite(){
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
//表格操作（删除）
function removeClientRow(ckb){
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
        $('.new-table-one tr:eq('+i+') td:first').text(i);
    }
}