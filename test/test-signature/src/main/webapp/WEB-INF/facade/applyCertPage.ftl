<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Cert demo</title>  
        <style type="text/css">
        .style1
        {
            width: 500px;
        }
        .style4
        {
            width: 720px;
            border: thin solid green;
        }      
    </style>
</head>
<script src="${host}/js/common/jquery-1.11.2.min.js"></script>
<script language="javascript" type="text/javascript">
        var CryptoAgent = "";
        var SignCryptoAgent = "";
        function OnLoad() {
            if (navigator.appName.indexOf("Internet") >= 0 || navigator.appVersion.indexOf("Trident") >= 0) {
                if (window.navigator.cpuClass == "x86") {
                    document.getElementById("FakeCryptoAgent").innerHTML = "<object id=\"CryptoAgent\" codebase=\"${basepath}/resources/CryptoKit.CertEnrollment.Pro.x86.cab\" classid=\"clsid:71BB5253-EF2B-4C5B-85FF-1FD6A03C29A6\" ></object>";
                }
                else {
                    document.getElementById("FakeCryptoAgent").innerHTML = "<object id=\"CryptoAgent\" codebase=\"${basepath}/resources/CryptoKit.CertEnrollment.Pro.x64.cab\" classid=\"clsid:9E7B8F05-ADBE-4067-ABC6-28E0230A5C18\" ></object>";
                }
            }
            else {
                document.getElementById("FakeCryptoAgent").innerHTML = "<embed id=\"CryptoAgent\" type=\"application/npCryptoKit.CertEnrollment.Pro.x86\" style=\"height: 0px; width: 0px\">";
            }
            var KenAlgorithm = document.getElementById("KenAlgorithm")
            KenAlgorithm[0].selected="selected";
            KenAlgorithm[1].selected="";
            CryptoAgent = document.getElementById("CryptoAgent");
            
            
            try {
                var eDiv = document.createElement("div");
                if (navigator.appName.indexOf("Internet") >= 0 || navigator.appVersion.indexOf("Trident") >= 0) {
                    if (window.navigator.cpuClass == "x86") {
                        eDiv.innerHTML = "<object id=\"SignCryptoAgent\" codebase=\"${basepath}/resources/CryptoKit.Ultimate.x86.cab\" classid=\"clsid:4C588282-7792-4E16-93CB-9744402E4E98\" ></object>";
                    }
                    else {
                        eDiv.innerHTML = "<object id=\"SignCryptoAgent\" codebase=\"${basepath}/resources/CryptoKit.Ultimate.x64.cab\" classid=\"clsid:B2F2D4D4-D808-43B3-B355-B671C0DE15D4\" ></object>";
                    }
                }
                else {
                    eDiv.innerHTML = "<embed id=\"SignCryptoAgent\" type=\"${basepath}/resources/npCryptoKit.Ultimate.x86\" style=\"height: 0px; width: 0px\">";
                }
                document.body.appendChild(eDiv);
            }
            catch (e) {
                alert(e);
                return;
            }
            SignCryptoAgent = document.getElementById("SignCryptoAgent");
            
            //判断证书是否存在
            if(SelectCertificateOnClick()){
            	noNeedApplyCert();
            }
        }
        
        function GetSelectedItemIndex(itemName) 
        {
            var ele = document.getElementsByName(itemName);
            for (i = 0; i < ele.length; i++) {
                if (ele[i].checked) {
                    return i;
                }
            }
        }
        
        function FindProvider(){
			try{
				indexEnhanced = 0;
				var cryptprov = document.getElementById("TextCSPName");
		
				for(var i=0;i<cryptprov.length;i++){
					cryptprov.remove(i);
				}
	            var cspInfo = CryptoAgent.CFCA_GetCSPInfo();
	            if (!cspInfo) {
	                var errorDesc = CryptoAgent.GetLastErrorDesc();
	                alert(errorDesc);
	                return;
	            }
				var csps = cspInfo.split("||");
                
			    if(cspInfo.indexOf('Microsoft Enhanced Cryptographic Provider v1.0') != -1)
                {
                    var opt = document.createElement("OPTION");
                    opt.value = "Microsoft Enhanced Cryptographic Provider v1.0";
					opt.text = "Microsoft Enhanced Cryptographic Provider v1.0";
					cryptprov.options.add(opt);
				}
                
				for(var i=0;i<csps.length;i++){					
	                if((-1==csps[i].indexOf("Microsoft")))
                    {
                        var opt = document.createElement("OPTION");
						opt.value = csps[i];
						opt.text = csps[i];
						cryptprov.options.add(opt);
					}
				}

				cryptprov.selectedIndex = indexEnhanced;
			}catch(e){
				var lastErrorDesc = CryptoAgent.GetLastErrorDesc();
				if(!(-1==lastErrorDesc.indexOf("0x00000000"))){
					alert(e.number + e.message);
				}else{
					alert(lastErrorDesc);
				}
            }
		}
        
        function VerifyCertificateIntegrity() {
        try {
            var result = CryptoAgent.CFCA_CheckCertificateChain();
            if (!result) {
                var errorDesc = CryptoAgent.GetLastErrorDesc();
                alert(errorDesc);
                return;
            }
            alert("证书链完整");
        }
        catch (e) {
            var LastErrorDesc = CryptoAgent.GetLastErrorDesc();
            alert(LastErrorDesc);
        }
    }

	function GetCSPInfo() {
    	try 
        {
            var cspNames = CryptoAgent.CFCA_GetCSPInfo();
            if (!cspNames) {
                var errorDesc = CryptoAgent.GetLastErrorDesc();
                alert(errorDesc);
                return;
            }
            document.getElementById("textareaCSPInfo").value = cspNames; 
        }
        catch (e)
        {
            var LastErrorDesc = CryptoAgent.GetLastErrorDesc();
            alert(LastErrorDesc);
        }
	}
    
	function GetAllCertInfo() {
    	try 
        {
            var certInfo = CryptoAgent.CFCA_GetAllCertInfo();
            if (!certInfo) {
                var errorDesc = CryptoAgent.GetLastErrorDesc();
                alert(errorDesc);
                return;
            }
            document.getElementById("TextAllCertInfo").value = certInfo; 
        }
        catch (e)
        {
            var LastErrorDesc = CryptoAgent.GetLastErrorDesc();
        	alert(e+": "+LastErrorDesc);
        }
	}	
	function PKCS10Requisition_SingleCert() {
    	try 
        {
        	var keyAlgorithm  = document.getElementById("KenAlgorithm").value;
			var selectValue  = document.getElementById("KenLength").value;
            var keyLength = 0;
			if(selectValue == "1024"){
			   keyLength = 1024;
			}else if(selectValue == "2048"){
			    keyLength = 2048;		    
			}else if(selectValue == "4096"){
			    keyLength = 4096;
			}else{
			 	keyLength = 256;
			}
	        var cspName = document.getElementById("TextCSPName").value;
	        if ("" == cspName) {
	            alert("请选择“生成密钥的CSP名称”");
	            FindProvider();
	            return;
	        }

		    var strSubjectDN = document.getElementById("TextSubjectDN").value;
		    var res1 = CryptoAgent.CFCA_SetCSPInfo(keyLength, cspName);
		    if (!res1) {
		        var errorDesc = CryptoAgent.GetLastErrorDesc();
		        alert(errorDesc);
		        return;
		    }
			var res2= CryptoAgent.CFCA_SetKeyAlgorithm(keyAlgorithm);
			if (!res2) {
			    var errorDesc = CryptoAgent.GetLastErrorDesc();
			    alert(errorDesc);
			    return;
			}

            var pkcs10Requisition = 0;
            if(keyAlgorithm == "RSA")
            {
                var selectedIndex = GetSelectedItemIndex("radio_DigestAlgorithm");
                if(selectedIndex == 1)
                {
                    //RSA单证 md5 
			        pkcs10Requisition = CryptoAgent.CFCA_PKCS10CertRequisitionwithMD5(strSubjectDN, 1, 0);
                }
                else
                {
                    //RSA单证 sha1 
                    pkcs10Requisition = CryptoAgent.CFCA_PKCS10CertRequisition(strSubjectDN, 1, 0);
                }                
            }
            else
            {
                //SM2单证
		        pkcs10Requisition = CryptoAgent.CFCA_PKCS10CertRequisition(strSubjectDN, 1, 0);
            }

		    if (!pkcs10Requisition) {
		        var errorDesc = CryptoAgent.GetLastErrorDesc();
		        alert(errorDesc);
		        return;
		    }
			document.getElementById("textareaP10RSASingleCert").value = pkcs10Requisition;
			var contianerName = CryptoAgent.CFCA_GetContainer();
			if (!contianerName) {
			    var errorDesc = CryptoAgent.GetLastErrorDesc();
			    alert(errorDesc);
			    return;
			}
			document.getElementById("TextContianerName").value = contianerName;
			//...need to sent pkcs10 requisition to CA
        }
        catch (e)
        {
            var LastErrorDesc = CryptoAgent.GetLastErrorDesc();
            alert(LastErrorDesc);
        }
	}
	
	function PKCS10Requisition_DoubleCert() {
    	try 
        {
        	var keyAlgorithm  = document.getElementById("KenAlgorithm").value;
			var selectValue  = document.getElementById("KenLength").value;
            var keyLength = 0;
	        
			if(selectValue == "1024"){
			   keyLength = 1024;
			}else if(selectValue == "2048"){
			    keyLength = 2048;		    
			}else if(selectValue == "4096"){
			    keyLength = 4096;
			}else{
			 	keyLength = 256;
			}
	        var cspName = document.getElementById("TextCSPName").value;
	        if ("" == cspName) {
	            alert("请选择“生成密钥的CSP名称”");
	            FindProvider();
	            return;
	        }
		    var strSubjectDN = document.getElementById("TextSubjectDN").value;
		    var res1 = CryptoAgent.CFCA_SetCSPInfo(keyLength, cspName);
		    if (!res1) {
		        var errorDesc = CryptoAgent.GetLastErrorDesc();
		        alert(errorDesc);
		        return;
		    }
			var res2= CryptoAgent.CFCA_SetKeyAlgorithm(keyAlgorithm);
			if (!res2) {
			    var errorDesc = CryptoAgent.GetLastErrorDesc();
			    alert(errorDesc);
			    return;
			}

			//P10 生成
            var pkcs10Requisition = 0;
            if(keyAlgorithm == "RSA")
            {
                var selectedIndex = GetSelectedItemIndex("radio_DigestAlgorithm");
                if(selectedIndex == 1)
                {
                    //RSA双证 md5 
			        pkcs10Requisition = CryptoAgent.CFCA_PKCS10CertRequisitionwithMD5(strSubjectDN, 2, 0);
                }
                else
                {
                    //RSA双证 sha1 
                    pkcs10Requisition = CryptoAgent.CFCA_PKCS10CertRequisition(strSubjectDN, 2, 0);
                }                
            }
            else
            {
                //SM2双证
		        pkcs10Requisition = CryptoAgent.CFCA_PKCS10CertRequisition(strSubjectDN, 2, 0);
            }
		    if (!pkcs10Requisition) {
		        var errorDesc = CryptoAgent.GetLastErrorDesc();
		        alert(errorDesc);
		        return;
		    }
            
			document.getElementById("textareaP10RSASingleCert").value = pkcs10Requisition;
			var contianerName = CryptoAgent.CFCA_GetContainer();
			if (!contianerName) {
			    var errorDesc = CryptoAgent.GetLastErrorDesc();
			    alert(errorDesc);
			    return;
			}
			document.getElementById("TextContianerName").value = contianerName;
			//...need to sent pkcs10 requisition to CA
        }
        catch (e)
        {
            var LastErrorDesc = CryptoAgent.GetLastErrorDesc();
            alert(LastErrorDesc);
        }
	}	
	
	function KeyAlgorithm_onchange(selectedItemIndex)
    {		
		try{
			var kenLength_select= document.getElementById("KenLength");
			var optionNum = document.getElementById("KenLength").length;
			//remove select's options
			for(var i=optionNum; i>=0; i-- ){
				kenLength_select.remove(i);
			}
			
			if(selectedItemIndex == 0){				
				//RSA
                //show digest algorithm
                document.getElementById("tr_DigestAlgorithm").style.display = "";
                                
                //key length
				var optionTag = document.createElement('option');
				optionTag.text = "1024";
                optionTag.value = "1024";
				kenLength_select.options.add(optionTag);
				optionTag = document.createElement('option');
				optionTag.text = "2048";
                optionTag.value = "2048";
				kenLength_select.options.add(optionTag);
				optionTag = document.createElement('option');
				optionTag.text = "4096";
                optionTag.value = "4096";
				kenLength_select.options.add(optionTag);
				document.getElementById("TextCSPName").value = "Microsoft Enhanced Cryptographic Provider v1.0";
			}else{
				//SM2			
                //hide digest algorithm
                document.getElementById("tr_DigestAlgorithm").style.display = "none";									
				
				var optionTag = document.createElement('option');
				optionTag.text = "256";
                optionTag.value = "256";
				kenLength_select.options.add(optionTag);
				//document.getElementById("TextCSPName").value = "CIDC Cryptographic Service Provider v3.0.0";
			}		
		
		}catch(e){
			alert(e.number + e.message);
		}
	}
	
	
	function GetTempPublickey(){
    	try 
        {
			//申请RSA加密证书
        	var keyAlgorithm  = document.getElementById("KenAlgorithm").value;
			var selectValue  = document.getElementById("KenLength").value;
            var keyLength = 0;
			if(selectValue == "1024"){
			   keyLength = 1024;
			}else if(selectValue == "2048"){
			    keyLength = 2048;		    
			}else if(selectValue == "4096"){
			    keyLength = 4096;
			}else{
			 	keyLength = 256;
			}
	        var cspName = document.getElementById("TextCSPName").value;
	        if ("" == cspName) {
	            alert("请选择“生成密钥的CSP名称”");
	            FindProvider();
	            return;
	        }
		    var strSubjectDN = document.getElementById("TextSubjectDN").value;
		    var res1 = CryptoAgent.CFCA_SetCSPInfo(keyLength, cspName);
		    if (!res1) {
		        var errorDesc = CryptoAgent.GetLastErrorDesc();
		        alert(errorDesc);
		        return;
		    }
		    var res2 = CryptoAgent.CFCA_SetKeyAlgorithm(keyAlgorithm);
		    if (!res2) {
		        var errorDesc = CryptoAgent.GetLastErrorDesc();
		        alert(errorDesc);
		        return;
		    }
		    var tmpPublicKey = CryptoAgent.CFCA_GetTempPublickey();
		    if (!tmpPublicKey) {
		        var errorDesc = CryptoAgent.GetLastErrorDesc();
		        alert(errorDesc);
		        return;
		    }		
			document.getElementById("textareaP10RSASingleCert").value = tmpPublicKey;
			var contianerName = CryptoAgent.CFCA_GetContainer();
			if (!contianerName) {
			    var errorDesc = CryptoAgent.GetLastErrorDesc();
			    alert(errorDesc);
			    return;
			}
			document.getElementById("TextContianerName").value = contianerName;	
        }
        catch (e)
        {
            var LastErrorDesc = CryptoAgent.GetLastErrorDesc();
            alert(LastErrorDesc);
        }

    }

    function VerifyGenerateKeyPairPermission() {
        try {
            var result = CryptoAgent.CFCA_HavePermissiontoGenerateKeyPair();
            if(result)
            {
            	alert("您有权限生成密钥对");
            }
            else
            {
            	alert("没有权限生成密钥对");  
            }
            
        }
        catch (e) {
            var LastErrorDesc = CryptoAgent.GetLastErrorDesc();
            alert(LastErrorDesc);           
        }
    }
	
	function InstallCert(certType)
    {
    	try 
        {
        	var cspName = document.getElementById("TextCSPName").value;
            if(cspName == "")
            {
                alert("CSP名称为空");
                return;
            }                        
        	var keyAlgorithm  = document.getElementById("KenAlgorithm").value;
             if(keyAlgorithm == "")
            {
                alert("密钥生成算法为空");
                 return;
            }
			var selectValue  = document.getElementById("KenLength").value;
            var keyLength = 0;
			if(selectValue == "1024"){
			   keyLength = 1024;
			}else if(selectValue == "2048"){
			    keyLength = 2048;		    
			}else if(selectValue == "4096"){
			    keyLength = 4096;
			}else if(selectValue == "256"){
			 	keyLength = 256;
			}
            else
            {
               alert("密钥长度错误："+keyLength); 
               return;
            }
            var hResult = CryptoAgent.CFCA_SetKeyAlgorithm(keyAlgorithm);
            if (!hResult) {
                var errorDesc = CryptoAgent.GetLastErrorDesc();
                alert(errorDesc);
                return;
            }
        	var bResult = CryptoAgent.CFCA_SetCSPInfo(keyLength,cspName);
        	if (!bResult) {
        	    var errorDesc = CryptoAgent.GetLastErrorDesc();
        	    alert(errorDesc);
        	    return;
        	}
        	var signCert = document.getElementById("textareaSignCert").value;
            if(signCert == "")
            {
                alert("签名公钥证书内容为空");
                return;
            }
            
        	var containerName = document.getElementById("TextContianerName").value;
            if(containerName == "")
            {
                alert("密钥容器名称内容为空");
                return;
            }       	
            
            if(certType == 1)
            {
                //sign cert
                var bResult = CryptoAgent.CFCA_ImportSignCert(1, signCert, containerName);
                if (!bResult) {
                    var errorDesc = CryptoAgent.GetLastErrorDesc();
                    alert(errorDesc);
                    return;
                }       
                alert("单证书安装成功"); 
                return;          
            }
            else
            {
                //double cert
               	var encryptCert_PublickeyCert = document.getElementById("textareaEncryptCert_PublickeyCert").value;
                if(encryptCert_PublickeyCert == "")
                {
                    alert("加密证书公钥证书内容为空");
                    return;
                }
                
                var encryptCert_PrivateData = document.getElementById("textareaEncryptCert_PrivatekeyCert").value;
                if(encryptCert_PrivateData == "")
                {
                    alert("加密证书私钥数据为空");
                    return;
                }

                var bResult = CryptoAgent.CFCA_ImportSignCert(2, signCert, containerName); 
                if (!bResult) {
                    var errorDesc = CryptoAgent.GetLastErrorDesc();
                    alert(errorDesc);
                    return;
                }
               	bResult = CryptoAgent.CFCA_ImportEncryptCert(encryptCert_PrivateData, encryptCert_PublickeyCert, containerName, 0x1 | 0x2);
               	if (!bResult) {
               	    var errorDesc = CryptoAgent.GetLastErrorDesc();
               	    alert(errorDesc);
               	    return;
               	}
                alert("双证书安装成功");
            }
        }
        catch (e)
        {
            var LastErrorDesc = CryptoAgent.GetLastErrorDesc();
            alert(e+";"+LastErrorDesc);
            if(certType == 1)            
                alert("单证书安装失败");
            else
                alert("双证书安装失败");
        }
    }

    function GetVersion() {
        try {
            var hResult = false;
            hResult = CryptoAgent.CFCA_GetVersion();
            alert(hResult);
        }
        catch (e) {
            alert("控件未安装");
            var errorDesc = CryptoAgent.GetLastErrorDesc();
            alert(errorDesc);
        }
    }
    
    
    
    // Select certificate
    function SelectCertificateOnClick() {
        try {                  
                var subjectDNFilter = "";
                var issuerDNFilter = "China Financial Certification Authority";
                var serialNumFilter = "";
                var bSelectCertResult = "";

                bSelectCertResult = SignCryptoAgent.SelectCertificate(subjectDNFilter, issuerDNFilter, serialNumFilter);                        
                // Opera浏览器，NPAPI函数执行结果为false时，不能触发异常，需要自己判断返回值。
                if (!bSelectCertResult) 
                {
                    var errorDesc = SignCryptoAgent.GetLastErrorDesc();
                    alert(errorDesc);
                    needApplyCert();
                    return bSelectCertResult;
                }
                
                return bSelectCertResult;
             }                  
     
        catch (e) {
            var errorDesc = SignCryptoAgent.GetLastErrorDesc();
            alert(errorDesc);
            needApplyCert();
            return false;
        }
    }
    
    function needApplyCert(){
    	$("#applyCert").show();
    }
    
    function noNeedApplyCert(){
    	$("#applyCert").hide();
    }
    
    function installCert1(){
    	if(SelectCertificateOnClick()){
    		noNeedApplyCert();
    		alert("已经存在证书!");
    		return;
    	}
    	try{
	    	var cspName = "";
	    	var keyAlgorithm = "RSA";
	    	var keyLength = 2048;// Sign message
	    	var strSubjectDN = "CN=certRequisition,O=CFCA TEST CA,C=CN";
	    	var cspInfo = CryptoAgent.CFCA_GetCSPInfo();
	        if (!cspInfo) {
	            var errorDesc = CryptoAgent.GetLastErrorDesc();
	            alert(errorDesc);
	            return;
	        }
	        
		    if(cspInfo.indexOf('Microsoft Enhanced Cryptographic Provider v1.0') != -1){
	        	cspName = "Microsoft Enhanced Cryptographic Provider v1.0";
			}else{
				return;
			}
			
			var res1 = CryptoAgent.CFCA_SetCSPInfo(keyLength, cspName);
		    if (!res1) {
		        var errorDesc = CryptoAgent.GetLastErrorDesc();
		        alert(errorDesc);
		        return;
		    }
			var res2= CryptoAgent.CFCA_SetKeyAlgorithm(keyAlgorithm);
			if (!res2) {
			    var errorDesc = CryptoAgent.GetLastErrorDesc();
			    alert(errorDesc);
			    return;
			}
			
			var pkcs10Requisition = CryptoAgent.CFCA_PKCS10CertRequisition(strSubjectDN, 1, 0);
			
			if (!pkcs10Requisition) {
		        var errorDesc = CryptoAgent.GetLastErrorDesc();
		        alert(errorDesc);
		        return;
		    }
		    
			var contianerName = CryptoAgent.CFCA_GetContainer();
			if (!contianerName) {
			    var errorDesc = CryptoAgent.GetLastErrorDesc();
			    alert(errorDesc);
			    return;
			}
			
			$.ajax({
				url:"${basepath}/facade/apply/cert",
				data:{p10: pkcs10Requisition},
				type:'post',
				timeout : 15000,
				dataType:'json',
				traditional:true,
				success : function(response) {
					if (response.status.toLowerCase() == 'failed') {
						alert("申请失败!");
					} else if(response.status.toLowerCase() == 'success'){
						//sign cert
		                var bResult = CryptoAgent.CFCA_ImportSignCert(1, response.data, contianerName);
		                if (!bResult) {
		                    var errorDesc = CryptoAgent.GetLastErrorDesc();
		                    alert(errorDesc);
		                    return;
		                }       
		                alert("单证书安装成功"); 
		                noNeedApplyCert();
		                return;          
					}
				},
				error : function(response){
					if(response != undefined)
						alert('网络异常!');
					else
						alert('网络异常!');
				},
				timeout : function(){
					alert("请求超时!");
				}
			});
        }
        catch (e)
        {
            var LastErrorDesc = CryptoAgent.GetLastErrorDesc();
            alert(LastErrorDesc);
        }
    }

	// Sign message
    function SignOnClick() {
    
    	if(!SelectCertificateOnClick()){
    		alert("您还未安装证书,请先申请证书!");
    		return
    	}
    	
        try {
            var source = "";
            var signature = "";
            var signType = "";
            var selectedAlg = "SHA-1";

            document.getElementById("Signature").value = "";

            // source
            source = document.getElementById("SourceData").value;

            // sign type
            // certificate information identifier
            signType = GetSelectedItemValue("SignType");

            if ("Attach" == signType) {
                // PKCS#7 Attach
                signature = SignCryptoAgent.SignMsgPKCS7(source, selectedAlg, true);
            }
            else if ("Detach" == signType) {
                // PKCS#7 Detach
                  signature = SignCryptoAgent.SignMsgPKCS7(source, selectedAlg, false);                 
            }
            if (!signature) {
                var errorDesc = SignCryptoAgent.GetLastErrorDesc();
                alert(errorDesc);
                return;
            }
            document.getElementById("Signature").value = signature;
            
            $.ajax({
				url:"${basepath}/facade/sign/message",
				data:{signData: signature, signType: signType, source: source},
				type:'post',
				timeout : 15000,
				dataType:'json',
				traditional:true,
				success : function(response) {
					if (response.status.toLowerCase() == 'failed') {
						alert(response.message);
						return;
					} else if(response.status.toLowerCase() == 'success'){
						     
		                alert(response.message);
		                return;          
					}
				},
				error : function(response){
					if(response != undefined)
						alert('网络异常!');
					else
						alert('网络异常!');
				},
				timeout : function(){
					alert("请求超时!");
				}
			});
			
        } catch (e) {
            var errorDesc = SignCryptoAgent.GetLastErrorDesc();
            alert(errorDesc);
        }
    }
    
    function GetSelectedItemValue(itemName) {
        var ele = document.getElementsByName(itemName);
        for (i = 0; i < ele.length; i++) {
            if (ele[i].checked) {
                return ele[i].value;
            }
        }
    }       
</script> 

<body onload="javascript:OnLoad();FindProvider();">
<div id="FakeCryptoAgent"></div>
<div style="display:none">
<br />
<div>
    <input id="Button3" type="button" value="获取控件版本号" onclick="return GetVersion()" />
</div>
<br />
<br />
<div>
    <input id="Button2" type="button" value="验证是否有权限生成密钥对" onclick="return VerifyGenerateKeyPairPermission()" />
</div>
<br />
<br />
<div>
    <input id="Button1" type="button" value="验证证书链表完整性" onclick="return VerifyCertificateIntegrity()" />
</div>
<br />
<hr />
<br />
<div>
    <input id="GetCSPInfo" type="button" value="取得CSP名称" onclick="return GetCSPInfo()" />
    <table border="0" cellpadding="10" cellspacing="0">
        <tr>
        	<td colspan="2">
          		<textarea id="textareaCSPInfo" name="textareaCSPInfo"  rows="10" class="style4"></textarea>
            </td>
        </tr> 
   	</table>
    <input id="GetAllCertInfo" type="button" value="取得所有证书信息" onclick="return GetAllCertInfo()" />
    <table border="0" cellpadding="10" cellspacing="0">
        <tr>
        	<td colspan="2">
          		<textarea id="TextAllCertInfo" name="TextAllCertInfo"  rows="10" class="style4"></textarea>
            </td>
        </tr> 
   	</table>
</div>
<br />
<hr />
<br />
<div>
    <h3>P10生成</h3>
    <table border="0" cellpadding="10" cellspacing="0">
           <tr>
			<td height="30">
				 密钥生成算法：
			</td>
			<td>
				<select name="KenAlgorithm" id="KenAlgorithm" onchange="KeyAlgorithm_onchange(this.selectedIndex)">
					<option value ="RSA">RSA</option>
					<option value ="SM2">SM2</option>
				</select>
			</td>
  		</tr>
        <tr id = "tr_DigestAlgorithm">
			<td height="30">
				 摘要算法：
			</td>
			<td>
                <input type="radio" name="radio_DigestAlgorithm" checked="checked"/>SHA1
                <input type="radio" name="radio_DigestAlgorithm"/>MD5
			</td>
  		</tr>
        <tr>
			<td height="30">
				 密钥长度：
			</td>
			<td>
				<select name="KenLength" id="KenLength" >
					<option value ="1024">1024</option>
					<option value ="2048">2048</option>
					<option value="4096">4096</option>
				</select>
			</td>           
  		</tr>
  		<tr>
  			<td height="30">
				 生成密钥的CSP名称：
			</td>
			<td>
              	<select name="TextCSPName" id="TextCSPName">
					<option value="1" selected>
						Microsoft Enhanced Cryptographic Provider v1.0
					</option>
				</select>
  			</td>
        </tr>
  		<tr>	
  			<td height="30">
				 证书主题：
			</td>
			<td>
				<input id="TextSubjectDN" name="TextSubjectDN" height="20" class="style1" value="CN=certRequisition,O=CFCA TEST CA,C=CN" />
  			</td>
        </tr>        
        <tr>
        	<td colspan="2">
            	<input id="PKCS10Requisition_SingleCert" type="button" value="单证P10申请" onclick="return PKCS10Requisition_SingleCert()" />
            	<input id="PKCS10Requisition_DoubleCert" type="button" value="双证P10申请" onclick="return PKCS10Requisition_DoubleCert()" />
            	<input id="TempPublickey_botton" type="button" value="申请加密证书临时公钥" onclick="return GetTempPublickey()" />
            </td>
        </tr>
        <tr>
        	<td colspan="2">
          		<textarea id="textareaP10RSASingleCert" name="textareaP10RSASingleCert"  rows="15" class="style4"></textarea>
            </td>
        </tr>
        
		<tr>
			<td height="30">
				 密钥容器名称：
			</td>
			<td>
				<input id="TextContianerName" name="TextContianerName" height="20" class="style1" />
  			</td>
		</tr>
   	</table>
</div>
<br />
<hr />
<br />
<div>
    <h3>证书安装</h3>
	<table cellpadding="10">
        <tr>
        	<td>
            	<input id="InstallSingleCert" type="button" value="安装签名单证书" onclick="return InstallCert(1)" />
                <input id="InstallDoubleCert" type="button" value="安装双证书" onclick="return InstallCert(2)" />
            </td>
        </tr>
		<tr>
			<td colspan="2">签名公钥证书内容（Base64编码）</td>
		</tr>
		<tr>
        	<td colspan="2">
        		<textarea id="textareaSignCert" name="textareaSignCert"  rows="15" class="style4"></textarea>
        	</td>    	
        </tr>
		<tr>
			<td colspan="2" >加密证书公钥证书内容（Base64编码）</td>
            <td colspan="2">加密证书私钥数据（Base64编码）</td>
		</tr>
		<tr>
        	<td colspan="2">
        		<textarea id="textareaEncryptCert_PublickeyCert" name="textareaEncryptCert_PublickeyCert"  rows="15" class="style4"></textarea>
        	</td>   
            <td colspan="2">
        		<textarea id="textareaEncryptCert_PrivatekeyCert" name="textareaEncryptCert_PrivatekeyCert"  rows="15" class="style4"></textarea>
        	</td> 
        </tr>
	</table>
</div>
<br />
</div>
<br />
<br />
<input id="applyCert" type="button" value="安装签名证书" onclick="return installCert1()" />

 <label>
        <h3>
            消息签名</h3>
    </label>
    <label>
        签名原文：
        <br />
        <textarea id="SourceData" rows="8" style="width: 650px">
</textarea>
    </label>
    <br />
    <br />
    <label>
        
            签名类型：
        
    </label>
        
            <input type="radio" name="SignType" value="Attach" checked="checked" />带原文签名
            <input type="radio" name="SignType" value="Detach" />不带原文签名
        
    <br />
    <br />
    <label>
        
            <input type="button" name="SignButton" value="Sign[签名]" onclick="SignOnClick()">
        
    </label>
    <br />
    <br />
    <label>
        签名结果：
        <br />
        <textarea id="Signature" rows="8" style="width: 650px"></textarea>
    </label>
    <br />
    <br />
</body>
</html>
