var Signature = [];

(function($) {
    $.CryptoAgent;
    $.SignCrytoAgent;
    $.softwareUrls = [];
    $.DNFilter = "";
    $.installCertUrl = basepath+"/guide/apply/cert";
    $.installCertParam = {};
    
    $.onLoadCertInsallSoftware = function (){
    	var fDiv = document.createElement("div");
        if (navigator.appName.indexOf("Internet") >= 0 || navigator.appVersion.indexOf("Trident") >= 0) {
            if (window.navigator.cpuClass == "x86") {
            	fDiv.innerHTML = "<object id=\"CryptoAgent\" codebase=\""+host+"/software/CryptoKit.CertEnrollment.Pro.x86.cab\" classid=\"clsid:71BB5253-EF2B-4C5B-85FF-1FD6A03C29A6\" ></object>";
            }
            else {
            	fDiv.innerHTML = "<object id=\"CryptoAgent\" codebase=\""+host+"/software/CryptoKit.CertEnrollment.Pro.x64.cab\" classid=\"clsid:9E7B8F05-ADBE-4067-ABC6-28E0230A5C18\" ></object>";
            }
        }
        else {
        	fDiv.innerHTML = "<embed id=\"CryptoAgent\" type=\"application/npCryptoKit.CertEnrollment.Pro.x86\" style=\"height: 0px; width: 0px\">";
        }
        document.body.appendChild(fDiv);
        
        $.CryptoAgent = document.getElementById("CryptoAgent");
    }
    


    $.onLoadSignatureSoftware =  function (){

        var eDiv = document.createElement("div");
        if (navigator.appName.indexOf("Internet") >= 0 || navigator.appVersion.indexOf("Trident") >= 0) {
            if (window.navigator.cpuClass == "x86") {
                eDiv.innerHTML = "<object id=\"SignCryptoAgent\" codebase=\""+host+"/software/CryptoKit.Ultimate.x86.cab\" classid=\"clsid:4C588282-7792-4E16-93CB-9744402E4E98\" ></object>";
            }
            else {
                eDiv.innerHTML = "<object id=\"SignCryptoAgent\" codebase=\""+host+"/software/CryptoKit.Ultimate.x64.cab\" classid=\"clsid:B2F2D4D4-D808-43B3-B355-B671C0DE15D4\" ></object>";
            }
        }
        else {
            eDiv.innerHTML = "<embed id=\"SignCryptoAgent\" type=\"application/npCryptoKit.Ultimate.x86\" style=\"height: 0px; width: 0px\">";
        }
        document.body.appendChild(eDiv);
        
        $.SignCryptoAgent = document.getElementById("SignCryptoAgent");  
    }
    
    $.onLoad = function() {
    	$.onLoadCertInsallSoftware();
    	$.onLoadSignatureSoftware();
    }
    
    $.judeCertSoftwareInstall = function(){
    	try {
            $.CryptoAgent.CFCA_GetVersion();
            return true;
        }
        catch (e) {
        	return false;
        }
    }
    
    $.judeSignSoftwareInstall = function(){
    	try {

            InfoContent = $.SignCryptoAgent.GetSignCertInfo("SubjectDN");
            if (!InfoContent) {
            	return false;
            }
        } catch (e) {
        	
        	try{
        		$.SignCryptoAgent.GetLastErrorDesc();
        	}catch(e){
        		return false;
        	}
           
        }
        return true;
    }
    
    $.judeSoftwareInstall = function(intallobject){
    	$.softwareUrls = [];
    	if(!$.judeCertSoftwareInstall()){
    		$.softwareUrls.push(host+"software/CryptoKit.CertEnrollment.Pro.exe");
    	}
    	if(!$.judeSignSoftwareInstall()){
    		$.softwareUrls.push(host+"software/npCryptoKit.Ultimate.x86.exe");
    	}
    	if($.softwareUrls.length > 0){
        	intallobject.show();
        }else{
        	intallobject.hide();
        }
    }
    
    $.setDNFilter = function(DNFilter) {
    	$.DNFilter = DNFilter;
    }
    
    $.selectCertificateOnClick = function() {
        try {                  
                var subjectDNFilter = $.DNFilter;
                var issuerDNFilter = "China Financial Certification Authority";
                var serialNumFilter = "";
                var bSelectCertResult = "";

                bSelectCertResult = $.SignCryptoAgent.SelectCertificate(subjectDNFilter, issuerDNFilter, serialNumFilter);                        
                // Opera浏览器，NPAPI函数执行结果为false时，不能触发异常，需要自己判断返回值。
                if (!bSelectCertResult) 
                {
                    var errorDesc = $.SignCryptoAgent.GetLastErrorDesc();
                    alert(errorDesc);
                    return bSelectCertResult;
                }
                
                return bSelectCertResult;
             }                  
     
        catch (e) {
//            var errorDesc = $.SignCryptoAgent.GetLastErrorDesc();
//            alert(errorDesc);
            return false;
        }
    }
    
    $.setInstallCertUrl = function(url) {
    	$.installCertUrl = url;
    }
    
    $.setInstallCertParam = function(param) {
    	$.installCertParam = param;
    }
    
    $.installCert = function(options){
    	if($.softwareUrls.length > 0){
    		alert("需要安装软件!");
    		options.error();
    		return;
    	}
    	if($.selectCertificateOnClick()){
//    		alert("已经存在证书!");
    		options.callback();
    		return;
    	}
    	try{
        	var cspName = "";
        	var keyAlgorithm = "RSA";
        	var keyLength = 2048;// Sign message
        	var strSubjectDN = "CN=certRequisition,O=CFCA TEST CA,C=CN";
        	var cspInfo = $.CryptoAgent.CFCA_GetCSPInfo();
            if (!cspInfo) {
                var errorDesc = $.CryptoAgent.GetLastErrorDesc();
                alert(errorDesc);
                return;
            }
            
    	    if(cspInfo.indexOf('Microsoft Enhanced Cryptographic Provider v1.0') != -1){
            	cspName = "Microsoft Enhanced Cryptographic Provider v1.0";
    		}else{
    			return;
    		}
    		
    		var res1 = $.CryptoAgent.CFCA_SetCSPInfo(keyLength, cspName);
    	    if (!res1) {
    	        var errorDesc = $.CryptoAgent.GetLastErrorDesc();
    	        alert(errorDesc);
    	        return;
    	    }
    		var res2= $.CryptoAgent.CFCA_SetKeyAlgorithm(keyAlgorithm);
    		if (!res2) {
    		    var errorDesc = $.CryptoAgent.GetLastErrorDesc();
    		    alert(errorDesc);
    		    return;
    		}
    		
    		var pkcs10Requisition = $.CryptoAgent.CFCA_PKCS10CertRequisition(strSubjectDN, 1, 0);
    		
    		if (!pkcs10Requisition) {
    	        var errorDesc = $.CryptoAgent.GetLastErrorDesc();
    	        alert(errorDesc);
    	        return;
    	    }
    	    
    		var contianerName = $.CryptoAgent.CFCA_GetContainer();
    		if (!contianerName) {
    		    var errorDesc = $.CryptoAgent.GetLastErrorDesc();
    		    alert(errorDesc);
    		    return;
    		}
    		
    		$.installCertParam.p10 = pkcs10Requisition;
    		
    		jQuery.ajax({
    			url:$.installCertUrl,
    			data:$.installCertParam,
    			type:'post',
    			timeout : 15000,
    			dataType:'json',
    			traditional:true,
    			success : function(response) {
    				if (response.status.toLowerCase() == 'failed') {
    					alert("证书申请失败!");
//    					jQuery("#tip-box-window .notice-content").text("证 书 申 请 失败");
//    					jQuery("#tip-box-window .dlg-del-row a").show();
    					options.error();
    				} else if(response.status.toLowerCase() == 'success'){
    					//sign cert
    	                var bResult = $.CryptoAgent.CFCA_ImportSignCert(1, response.data, contianerName);
    	                if (!bResult) {
    	                    var errorDesc = $.CryptoAgent.GetLastErrorDesc();
    	                    alert(errorDesc);
    	                    return;
    	                }       
//    	                alert("单证书安装成功"); 
    	                options.callback();
    	                return;          
    				}
    			},
    			error : function(response){
    				if(response != undefined)
    					alert('网络异常!');
    				else
    					alert('网络异常!');
    				options.error();
    			},
    			timeout : function(){
    				alert("请求超时!");
    				options.error();
    			}
    		});
        }
        catch (e)
        {
            var LastErrorDesc = $.CryptoAgent.GetLastErrorDesc();
            alert(LastErrorDesc);
        }
    }
    
    $.signMessageOnClick = function (source, signType) {

    	if(!$.selectCertificateOnClick()){
    		alert("您还未安装证书,请先申请证书!");
    		return
    	}
    	
        try {
            var signature = "";
            var selectedAlg = "SHA-1";
            
            if ("Attach" == signType) {
                // PKCS#7 Attach
                signature = $.SignCryptoAgent.SignMsgPKCS7(source, selectedAlg, true);
            }
            else if ("Detach" == signType) {
                // PKCS#7 Detach
                  signature = $.SignCryptoAgent.SignMsgPKCS7(source, selectedAlg, false);                 
            }
            if (!signature) {
                var errorDesc = $.SignCryptoAgent.GetLastErrorDesc();
                alert(errorDesc);
                return;
            }
            return signature;
    		
        } catch (e) {
            var errorDesc = $.SignCryptoAgent.GetLastErrorDesc();
            alert(errorDesc);
        }
    }
})(Signature);


