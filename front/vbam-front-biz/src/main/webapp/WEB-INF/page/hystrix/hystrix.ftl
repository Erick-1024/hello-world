<#import "/common/htmlBase.ftl" as hb>
<@hb.htmlBase title="" curTopMenu = "" curSubMenu = "" jsFiles=["common/cana.util.js"] cssFiles=[] localCssFiles=[] removeExtHeader = true removeExtFooter = true>

hystrix测试
<br>
--------------------
<br>
<a id="common" href="javascript:void(0);" >非hystrix</a>
<br>
<p id="common_area"><p>
<br>
--------------------
<br>
<a id="normal" href="javascript:void(0);" >hystrix普通</a>
<br>
<p id="normal_area"><p>
<br>
--------------------
<br>
<a id="normalExcel" href="javascript:void(0);" >hystrix普通Excel</a>
<br>
<p id="normalExcel_area"><p>
<br>
--------------------
<br>
<a id="annotation" href="javascript:void(0);" >hystrixAnnotation</a>
<br>
<p id="annotation_area"><p>

</@hb.htmlBase>

<script>
	$(function() {
		$('#common').click(function(){
			$('#common_area').text('waiting');
			cana.post(basepath + "/hystrix/get/list", null, function(data){$('#common_area').text(JSON.stringify(data))}, function(data){$('#common_area').text(JSON.stringify(data))}, null);
		});
		$('#normal').click(function(){
			$('#normal_area').text('waiting');
			cana.post(basepath + "/hystrix/get/normal/list", null, function(data){$('#normal_area').text(JSON.stringify(data))}, function(data){$('#normal_area').text(JSON.stringify(data))}, null);
		});
		$('#normalExcel').click(function(){
			$('#normalExcel_area').text('waiting');
			cana.post(basepath + "/hystrix/get/normal/excel", null, function(data){$('#normalExcel_area').text(JSON.stringify(data))}, function(data){$('#normalExcel_area').text(JSON.stringify(data))}, null);
		});
		$('#annotation').click(function(){
			$('#annotation_area').text('waiting');
			cana.post(basepath + "/hystrix/get/annotation/list", null, function(data){$('#annotation_area').text(JSON.stringify(data))}, function(data){$('#annotation_area').text(JSON.stringify(data))}, null);
		});
	})
</script>