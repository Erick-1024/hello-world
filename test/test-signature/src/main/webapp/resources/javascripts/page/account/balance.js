$(function(){
	
	getAccountBalance();
	
	getSpecialAccountBalances();
	
	getFinaceBalance();
	//点击"刷新" --账户余额
	$("body").on("click", ".accountBalanceButton", function(e){
		getAccountBalance();
	});
	
	//点击"刷新" --专用账户总余额
	$("body").on("click", ".specialAccountBalancesButton", function(e){
		getSpecialAccountBalances();
	});
	
});

/**
 * 刷新--获取一个账户余额
 */
var isAccountBalanceRequesting = false;
function getAccountBalance(){
	if(isAccountBalanceRequesting)
		return;
	isAccountBalanceRequesting = true;
	$(".accountBalance").text("获取中");
	cana.post(basepath + "/account/trade/queryAccountBalance",
			{accountNoString: $("#accountNo").text().parseBankAccountNo()},
			function(data){
				var accountBalance = parseFloat(data.accountBalances[0]).toFixed(2);
				if(!isNaN(accountBalance)){
					$(".accountBalance").text(accountBalance.toString().formatMoney());
					fillValues();
				}
			},function(data){
				$(".accountBalance").text("获取失败");
			},function(){
				isAccountBalanceRequesting = false;
			});
}

/**
 * 刷新--获取多个专用账户余额并计算专用账户总余额
 */
var isSpecialAccountBalanceRequesting = false;
function getSpecialAccountBalances(){
	if(isSpecialAccountBalanceRequesting)
		return;
	isSpecialAccountBalanceRequesting = true;
	var accountNos = "";
	var specialBalances = 0;
	$("td.specialBalance").each(function(i,e){
		accountNos = accountNos + $(e).prev("td").text().parseBankAccountNo() + ";";
	})
	if(accountNos != ""){
		$("td.specialBalance").each(function(i,e){
			$(e).text("获取中");
		})
		$(".specialBalances").text("获取中");
		cana.post(basepath + "/account/trade/queryAccountBalance",
				{accountNoString: accountNos},
				function(data){
					$("td.specialBalance").each(function(i,e){
						var balance = parseFloat(data.accountBalances[i]);
						specialBalances = specialBalances + balance;
						if(!isNaN(balance))
							$(e).text(balance.toFixed(2).toString().formatMoney());
					})
					if(!isNaN(specialBalances)){
						$(".specialBalances").text(specialBalances.toFixed(2).toString().formatMoney());
						fillValues();
					}
				},function(data){
					$("td.specialBalance").each(function(i,e){
						$(e).text("获取失败");
					})
					$(".specialBalances").text("获取失败");
				},function(){
					isSpecialAccountBalanceRequesting = false;
				});
	}
}

/**
 * 获取融资余额
 */
function getFinaceBalance(){
	$(".financingBalance").text("获取中");
	$.ajax({
        type: 'POST',  
        url: basepath + "/account/trade/queryFinaceBalance",
        data: {
        	accountSupervisionId: $("#accountSupervisionId").val()
        },
        success:function(data){
        	var financingBalance = parseFloat(data).toFixed(2);
        	if(!isNaN(financingBalance)){
	        	$(".financingBalance").text(financingBalance.toString().formatMoney());
	        	fillValues();
        	}
        	else
        		$(".financingBalance").text("");
        },
        error:function(data){
        	$(".financingBalance").text("获取失败");
        }
    });

}

/**
 * 根据账户余额，专用账户总余额，融资余额计算账户总余额，资金覆盖率
 * 余额获取失败就不会调此方法
 */
function fillValues(){
	var totalBalance; //账户总余额
	
	var accountBalance = $(".accountBalance").text().parseMoney();
	//账户余额 获取中，获取失败
	if(isNaN(accountBalance))
		return;
	else
		totalBalance = (parseFloat(accountBalance)).toFixed(2);
	var specialBalances = $(".specialBalances").text().parseMoney();
	
	//专用账户不一定存在，如果存在就加到总余额里，如果不存在，总余额就是账户余额
	if(specialBalances != ""){
		//专用账户总余额 获取中，获取失败
		if(isNaN(specialBalances))
			return;
		else{
			totalBalance = (parseFloat(accountBalance) + parseFloat(specialBalances)).toFixed(2);
			$(".accountTotalBalance").text(totalBalance.toString().formatMoney());
		}
	}
	//计算资金覆盖率
	var financingBalance = $(".financingBalance").text().parseMoney();
	if(financingBalance!="" && financingBalance!="0.00" && !isNaN(financingBalance))
		$(".fundCoverage").text((totalBalance/financingBalance*100).toFixed(2)+"%");
}

