 $(function(){
 	$('.special-view').eq(0).removeClass("hidden");
    $(".applyAut-back").click(function(){
        if($(".applyAut-wrap01").is(":hidden")){
            $(".applyAut-wrap01").show();
            $(".applyAut-wrap02").addClass("hidden");
        }
    });

    $(".account-expt01 .add-account").on('click',function(){
    	addRow('#acount-table','','');
    });
    $(".accountList-content").on("click", ".del-account", function(e){
        resort($(this).parent().parent());
        $(e.target).closest("tr").remove();
    });
    
    $('body').on('click','.import-excel',function(){
    });
    
    $('body').on('change','.excel',function(){
    	$.ajaxFileUpload({
    	    url: 'validateBuyerExcel',
    	    type: 'post',
    	    secureuri: false, //一般设置为false
    	    fileElementId: $(this).attr('id'), // 上传文件的id、name属性名
    	    dataType: 'text', //返回值类型，一般设置为json、application/json
			success : function(response) {
				$('.excel').val('');
				window.console.log(response);
				var json = $.parseJSON(response);
				if (json.status.toLowerCase() == 'success') {
					$('#acount-table01 tbody').empty();
					var excel = json.data.excel;
					var invalid = json.data.invalid;
					for (var index = 0; index < excel.length; index++) {
						var msg = '';
						if (invalid.SAME == excel[index]) {
							msg = '重复输入';
						}
						if (invalid.EXISTED == excel[index]) {
							msg = '已经存在';
						}
						addRow('#acount-table01', excel[index], msg);
					}
				} else {
					$('.excel').val('');
					cana.alert("上传失败");
				}
			},error:function(response,status,e){
				$('.excel').val('');
		    	//cana.alert("上传失败");
		    }
    	});
    });
 });
 // 被删除节点后面的元素序号减一
    function resort($this){
        $next = $this.next();
        if($next[0] != null && $next[0] != undefined){
            $next.find("td:first").text(parseInt($next.find("td:first").text())-1);
            resort($next);
        }
    }
    //添加行
    function addRow(table,val,invalid){
        var num = parseInt($(table+" tr:last-child").find("td:first").text());
        if(isNaN(num)){
            num = 0;
        }
        var row = '<tr>' +
                '<td>'+(num+1)+'</td>' +
                '<td><input type="text" class="buyer-name" style="width:160px; height:30px;" name="buyerNames" placeholder="买方企业名" value="'+val+'">'+
                '<span class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" style="display:none;"></span>'+
                '</td>' +
                '<td>' +
                '<a class="accountList-link del-account" href="javascript:void(0);">删除</a>' +
                '</td>' +
                '</tr>';
        $(table+" tbody").append(row);
    }

    function getSpecialInput(){
    	if(!$('.account-expt01').is('.hidden')){
    		return $('.account-expt01 input').not('.frontage');
    	}
    	else if(!$('.account-expt02').is('.hidden')){
    		return $('.account-expt02 input').not('.frontage');
    	}
    	return $();
    }
