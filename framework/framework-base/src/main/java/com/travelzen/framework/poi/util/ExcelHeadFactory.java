package com.travelzen.framework.poi.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelHeadFactory {

	@SuppressWarnings("rawtypes")
	protected static ExcelHead generateHead(List<ExcelColumn> columns) {
		ExcelHead head = new ExcelHead();
		head.setColumnCount(1);
		head.setColumns(columns);
		Map<String, Map> excelColumnsConvertMap = new HashMap<String, Map>();
		head.setColumnsConvertMap(excelColumnsConvertMap);
		return head;
	}

    public static ExcelHead getTicketIssuHead(String type) {
        if ("provider".equals(type)) {
            int i = 0;
            List<ExcelColumn> columns = Arrays.asList(
                    new ExcelColumn(i++, "purAuditStatus", "审核状态"),
                    new ExcelColumn(i++, "orderNum", "订单号"),
                    new ExcelColumn(i++, "ticketNum", "票号"),
                    new ExcelColumn(i++, "ticketNumComplate","票号(13位)"),
                    new ExcelColumn(i++, "pnr","PNR"),
                    new ExcelColumn(i++, "departureDateTime","出发时间","time"),
                    new ExcelColumn(i++, "ticketDate", "出票时间", "date"),
                    new ExcelColumn(i++, "passengerName", "乘客名"),
                    new ExcelColumn(i++, "cabin", "舱位"),
                    new ExcelColumn(i++, "flightNum", "航班"),
                    new ExcelColumn(i++, "voyage", "航程"),
                    new ExcelColumn(i++, "office", "OFFICE号"),
                    new ExcelColumn(i++, "nominalPrice", "票面价", "money"),
                    new ExcelColumn(i++, "agentsRates", "代理费率"),
                    new ExcelColumn(i++, "reservePrice", "底价", "money"),
                    new ExcelColumn(i++, "agencyFees", "代理费", "money"),
                    new ExcelColumn(i++, "macConstTax", "机建税", "money"),
                    new ExcelColumn(i++, "fuelTax", "燃油税", "money"),
                    new ExcelColumn(i++, "extraFee", "手续费", "money"),
                    new ExcelColumn(i++, "disbursements", "实收款", "money"),
                    new ExcelColumn(i++, "supplierXCommRatio", "后返让利扣点"),
                    new ExcelColumn(i++, "supplierXCommAmount", "后返让利金额", "money"),
                    new ExcelColumn(i++, "airplusFee", "AIRPLUS服务费", "money"),
                    new ExcelColumn(i++, "isAirplus", "AIRPLUS订单"),
                    new ExcelColumn(i++, "amountReceived", "销售价", "money"),
                    new ExcelColumn(i++, "ticketStatus", "机票状态"),
                    new ExcelColumn(i++, "payStatus", "付款状态"),
                    new ExcelColumn(i++, "partnerName", "供应商名称"),
                    new ExcelColumn(i++, "isFlightHotelOrderCN", "机酒子订单")

            );
            return generateHead(columns);
        }
        if ("purchaser".equals(type)) {
            int i = 0;
            List<ExcelColumn> columns = Arrays.asList(
                    new ExcelColumn(i++, "purAuditStatus", "审核状态"),
                    new ExcelColumn(i++, "orderNum", "订单号"),
                    new ExcelColumn(i++, "ticketNum", "票号"),
                    new ExcelColumn(i++, "ticketDate", "出票时间", "date"),
                    new ExcelColumn(i++, "passengerName", "乘客名"),
                    new ExcelColumn(i++, "cabin", "舱位"),
                    new ExcelColumn(i++, "flightNum", "航班"),
                    new ExcelColumn(i++, "voyage", "航程"),
                    new ExcelColumn(i++, "costPrice", "成本价", "money"),
                    new ExcelColumn(i++, "amountReceived", "实收款", "money"),
                    new ExcelColumn(i++, "profit", "利润", "money"),
                    new ExcelColumn(i++, "nominalPrice", "票面价", "money"),
                    new ExcelColumn(i++, "agentsRates", "代理费率"),
                    new ExcelColumn(i++, "reservePrice", "底价", "money"),
                    new ExcelColumn(i++, "agencyFees", "代理费", "money"),
                    new ExcelColumn(i++, "macConstTax", "机建税", "money"),
                    new ExcelColumn(i++, "fuelTax", "燃油税", "money"),
                    new ExcelColumn(i++, "ticketStatus", "机票状态")
            );
            return generateHead(columns);
        }
        throw new RuntimeException(type + " is not be expect");
    }

    public static ExcelHead getTicketRefundHead(String type) {
        if ("provider".equalsIgnoreCase(type)) {
            int i = 0;
            List<ExcelColumn> columns = Arrays.asList(
                    new ExcelColumn(i++, "proAuditStatus", "审核状态"),
                    new ExcelColumn(i++, "orderNum", "订单号"),
                    new ExcelColumn(i++, "ticketNum", "票号"),
                    new ExcelColumn(i++, "ticketRefundDate", "退票审核日期", "time"),
                    new ExcelColumn(i++, "completeRefundDate", "退票完成日期", "time"),
                    new ExcelColumn(i++, "passengerName", "乘客名"),
                    new ExcelColumn(i++, "office", "OFFICE号"),
                    new ExcelColumn(i++, "nominalPrice", "票面价", "money"),
                    new ExcelColumn(i++, "reservePrice", "底价", "money"),
                    new ExcelColumn(i++, "macConstFees", "机建", "money"),
                    new ExcelColumn(i++, "fuelTax", "燃油", "money"),
                    new ExcelColumn(i++, "refundNominalPrice", "退票面价", "money"),
                    new ExcelColumn(i++, "refundReservePrice", "退底价", "money"),
                    new ExcelColumn(i++, "refundMacConstFees", "退机建", "money"),
                    new ExcelColumn(i++, "refundFuelTax", "退燃油", "money"),
                    new ExcelColumn(i++, "refundTicketFees", "退票费", "money"),
                    new ExcelColumn(i++, "actualRefund", "实退款", "money"),
                    new ExcelColumn(i++, "supplierXRefundAmount", "后返让利金额", "money"),
                    new ExcelColumn(i++, "airplusFee", "AIRPLUS服务费", "money"),
                    new ExcelColumn(i++, "isAirplus", "AIRPLUS订单"),
                    new ExcelColumn(i++, "payStatus", "付款状态"),
                    new ExcelColumn(i++, "partnerName", "供应商名称"),
                    new ExcelColumn(i++, "isFlightHotelOrderCN", "机酒子订单")
            );
            return generateHead(columns);
        }
        if ("purchaser".equalsIgnoreCase(type)) {
            int i = 0;
            List<ExcelColumn> columns = Arrays.asList(
                    new ExcelColumn(i++, "purAuditStatus", "审核状态"),
                    new ExcelColumn(i++, "orderNum", "订单号"),
                    new ExcelColumn(i++, "ticketNum", "票号"),
                    new ExcelColumn(i++, "refundRecordDate", "退票录入日期", "date"),
                    new ExcelColumn(i++, "passengerName", "乘客名"),
                    new ExcelColumn(i++, "supplierRefund", "供应商退票款", "money"),
                    new ExcelColumn(i++, "purchaserRefund", "采购商退票款", "money"),
                    new ExcelColumn(i++, "refundTicketFees", "退票手续费", "money"),
                    new ExcelColumn(i++, "profit", "利润", "money")
            );
            return generateHead(columns);
        }
        throw new RuntimeException(type + " is not be expect");
    }

    public static ExcelHead getFailTicketRefundHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "processStatus", "处理状态"),
                new ExcelColumn(i++, "office", "OFFICE号"),
                new ExcelColumn(i++, "orderNum", "订单号"),
                new ExcelColumn(i++, "ticketNum", "票号"),
                new ExcelColumn(i++, "importDate", "导入日期", "date"),
                new ExcelColumn(i++, "refundFee", "导入退票费", "money"),
                new ExcelColumn(i++, "actualAmount", "导入实退款", "money"),
                new ExcelColumn(i++, "refundTicketFees", "系统退票费", "money"),
                new ExcelColumn(i++, "actualRefund", "系统实退款","money"),
                new ExcelColumn(i++, "failureReason", "失败原因"),
                new ExcelColumn(i++, "errorReason", "错误原因")

        );
        return generateHead(columns);
    }

    public static ExcelHead getFailTicketIssuHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "processStatus", "处理状态"),
                new ExcelColumn(i++, "office", "OFFICE号"),
                new ExcelColumn(i++, "orderNum", "订单号"),
                new ExcelColumn(i++, "ticketNum", "票号"),
                new ExcelColumn(i++, "importDate", "导入日期", "date"),
                new ExcelColumn(i++, "nominalPrice", "导入票面价", "money"),
                new ExcelColumn(i++, "agencyFees", "导入代理费", "money"),
                new ExcelColumn(i++, "machineConstructionTax", "导入机建税", "money"),
                new ExcelColumn(i++, "fuelTax", "导入燃油税", "money"),
                new ExcelColumn(i++, "sysNominalPrice", "系统票面价", "money"),
                new ExcelColumn(i++, "sysAgencyFees", "系统代理费", "money"),
                new ExcelColumn(i++, "sysMacConstTax", "系统机建税", "money"),
                new ExcelColumn(i++, "sysFuelTax", "系统燃油税", "money"),
                new ExcelColumn(i++, "failureReason", "失败原因"),
                new ExcelColumn(i++, "errorReason", "错误原因")
        );
        return generateHead(columns);
    }

    public static ExcelHead getApplyTicketIssuHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "orderNum", "订单号"),
                new ExcelColumn(i++, "ticketDate", "出票时间", "date"),
                new ExcelColumn(i++, "ticketNum", "票号"),
                new ExcelColumn(i++, "passengerName", "乘客名"),
                new ExcelColumn(i++, "nominalPrice", "票面价", "money"),
                new ExcelColumn(i++, "agentsRates", "代理费率"),
                new ExcelColumn(i++, "reservePrice", "底价", "money"),
                new ExcelColumn(i++, "macConstTax", "机建税", "money"),
                new ExcelColumn(i++, "fuelTax", "燃油税", "money"),
                new ExcelColumn(i++, "disbursements", "实付款", "money"),
                new ExcelColumn(i++, "ticketStatus", "机票状态")
        );
        return generateHead(columns);
    }

    public static ExcelHead getApplyTicketRefundHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "orderNum", "订单号"),
                //TODO 不确定！！！ 退票时间 使用那个
                new ExcelColumn(i++, "refundRecordDate", "退票时间","date"),
                new ExcelColumn(i++, "ticketNum", "票号"),
                new ExcelColumn(i++, "passengerName", "乘客名"),
                new ExcelColumn(i++, "nominalPrice", "票面价", "money"),
                new ExcelColumn(i++, "reservePrice", "底价", "money"),
                new ExcelColumn(i++, "macConstFees", "机建", "money"),
                new ExcelColumn(i++, "fuelTax", "燃油", "money"),
                new ExcelColumn(i++, "refundNominalPrice", "退票面价", "money"),
                new ExcelColumn(i++, "refundReservePrice", "退底价", "money"),
                new ExcelColumn(i++, "refundMacConstFees", "退机建", "money"),
                new ExcelColumn(i++, "refundFuelTax", "退燃油", "money"),
                new ExcelColumn(i++, "refundTicketFees", "退票费", "money"),
                new ExcelColumn(i++, "actualRefund", "实退款", "money")
        );
        return generateHead(columns);
    }

    public static ExcelHead getPayApplyTicketHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "payApplyNum", "申请票号"),
                new ExcelColumn(i++, "auditStatus", "审核状态"),
                new ExcelColumn(i++, "finishStatus", "完成状态"),
                new ExcelColumn(i++, "applyDate", "申请时间", "date"),
                new ExcelColumn(i++, "ticketType", "票类"),
                new ExcelColumn(i++, "office", "OFFICE号"),
                new ExcelColumn(i++, "bizType", "业务类型"),
                new ExcelColumn(i++, "provider", "供应商"),
                new ExcelColumn(i++, "amountPay", "收款金额", "money")
        );
        return generateHead(columns);
    }

    //---酒店现付返佣审核
    public static ExcelHead getHotelSpotPayAuditCommission() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "orderId", "订单号"),
                new ExcelColumn(i++, "externalOrderId", "外部订单号"),
                new ExcelColumn(i++, "auditCommissionStateDesc", "审核状态"),
                new ExcelColumn(i++, "hotelName", "酒店名称"),
                new ExcelColumn(i++, "inName", "入住人"),
                new ExcelColumn(i++, "checkoutDate", "离店日期", "date"),
                new ExcelColumn(i++, "sellingFeeYuan", "销售价"),
                new ExcelColumn(i++, "supplierCommissionAccountYuan", "返佣"),
                new ExcelColumn(i++, "roomCat", "房型")
        );
        return generateHead(columns);
    }

    //---酒店现付返佣确认
    public static ExcelHead getHotelSpotPayConfirmCommission() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "applyNo", "申请单号"),
                new ExcelColumn(i++, "confirmStatusView", "确认状态"),
                new ExcelColumn(i++, "supplierName", "供应商名称"),
                new ExcelColumn(i++, "applyDate", "申请日期","date"),
                new ExcelColumn(i++, "applyOperator", "申请人"),
                new ExcelColumn(i++, "confirmDate", "确认日期", "date"),
                new ExcelColumn(i++, "confirmOperator", "确认人"),
                new ExcelColumn(i++, "commissionAmountYuan", "返佣"),
                new ExcelColumn(i++, "invoiceNo", "发票号"),
                new ExcelColumn(i++, "paymentNo", "交款单号")
        );
        return generateHead(columns);
    }
    //---酒店预付确认
    public static ExcelHead getHotelPrePayConfirm() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "orderId", "订单号"),
                new ExcelColumn(i++, "externalOrderNo", "外部订单号"),
                new ExcelColumn(i++, "auditStatusView", "审核状态"),
                new ExcelColumn(i++, "customerName", "客户名称"),
                new ExcelColumn(i++, "hotelName", "酒店名称"),
                new ExcelColumn(i++, "inClient", "入住人"),
                new ExcelColumn(i++, "inTime", "入住日期","date"),
                new ExcelColumn(i++, "leaveTime", "离店日期", "date"),
                new ExcelColumn(i++, "roomType", "房型"),
                new ExcelColumn(i++, "receiveMoney", "销售价","money")
        );
        return generateHead(columns);
    }
  //---酒店预付处理
    public static ExcelHead getHotelPrePaypProcess() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "hotelOrderId", "订单号"),
                new ExcelColumn(i++, "externalOrderNo", "外部订单号"),
                new ExcelColumn(i++, "processStatusView", "处理状态"),
                new ExcelColumn(i++, "customerName", "客户名称"),
                new ExcelColumn(i++, "hotelName", "酒店名称"),
                new ExcelColumn(i++, "inClient", "入住人"),
                new ExcelColumn(i++, "inTime", "入住日期","date"),
                new ExcelColumn(i++, "checkoutDate", "离店日期", "date"),
                new ExcelColumn(i++, "roomcat", "房型"),
                new ExcelColumn(i++, "realSellingPrice", "销售价","money"),
                new ExcelColumn(i++, "sellingPrice", "导入销售价","money"),
                new ExcelColumn(i++, "errorReason", "错误原因")
        );
        return generateHead(columns);
    }
    //---酒店出账
    public static ExcelHead getHotelOutAccountApplyHead(String bookingType) {
        if("SELF_PAID".equals(bookingType)){
            int i = 0;
            List<ExcelColumn> columns = Arrays.asList(
                    new ExcelColumn(i++, "orderId", "订单号"),
                    new ExcelColumn(i++, "code", "业务编号"),
                    new ExcelColumn(i++, "applyStatus", "出账状态"),
                    new ExcelColumn(i++, "payTime", "付款时间", "date"),
                    new ExcelColumn(i++, "payType", "付款类型"),
                    new ExcelColumn(i++, "checkTime", "审核时间", "date"),
                    new ExcelColumn(i++, "outAccountTime", "出账时间", "date"),
                    new ExcelColumn(i++, "inTime", "入住日期", "date"),
                    new ExcelColumn(i++, "leaveTime", "离店时间", "date"),
                    new ExcelColumn(i++, "confirmTime", "确认收入成本时间", "date"),
                    new ExcelColumn(i++, "createTime", "订单完成时间", "date"),
                    new ExcelColumn(i++, "inClient", "入住人"),
                    new ExcelColumn(i++, "customerName", "客户名称"),
                    new ExcelColumn(i++, "hotelName", "酒店名称"),
                    new ExcelColumn(i++, "cityName", "城市"),
                    new ExcelColumn(i++, "supplierName", "收款人"),
                    new ExcelColumn(i++, "hotelBank", "开户银行"),
                    new ExcelColumn(i++, "accountNumber", "开户账号"),
                    new ExcelColumn(i++, "roomType", "房型"),
                    new ExcelColumn(i++, "roomNumber", "房间数"),
                    new ExcelColumn(i++, "roomNightNumber", "间夜数"),
                    new ExcelColumn(i++, "receiveMoney", "佣金收入"),
                    new ExcelColumn(i++, "payMoney", "佣金支出"),
                    new ExcelColumn(i++, "hangMoney", "挂账金额"),
                    new ExcelColumn(i++, "operator", "操作员"),
                    new ExcelColumn(i++, "hangRemark", "备注"),
                    new ExcelColumn(i++, "isFlightHotelOrderCN", "机酒子订单")
            );
            return generateHead(columns);
        }else{
            int i = 0;
            List<ExcelColumn> columns = Arrays.asList(
                    new ExcelColumn(i++, "orderId", "订单号"),
                    new ExcelColumn(i++, "originalOrderId", "源订单号"),
                    new ExcelColumn(i++, "code", "业务编号"),
                    new ExcelColumn(i++, "applyStatus", "出账状态"),
                    new ExcelColumn(i++, "payTime", "付款时间", "date"),
                    new ExcelColumn(i++, "payType", "付款类型"),
                    new ExcelColumn(i++, "checkTime", "审核时间", "date"),
                    new ExcelColumn(i++, "outAccountTime", "出账时间", "date"),
                    new ExcelColumn(i++, "inTime", "入住日期", "date"),
                    new ExcelColumn(i++, "leaveTime", "离店时间", "date"),
                    new ExcelColumn(i++, "confirmTime", "确认收入成本时间", "date"),
                    new ExcelColumn(i++, "createTime", "订单完成时间", "date"),
                    new ExcelColumn(i++, "inClient", "入住人"),
                    new ExcelColumn(i++, "customerName", "客户名称"),
                    new ExcelColumn(i++, "hotelName", "酒店名称"),
                    new ExcelColumn(i++, "cityName", "城市"),
                    new ExcelColumn(i++, "supplierName", "收款人"),
                    new ExcelColumn(i++, "hotelBank", "开户银行"),
                    new ExcelColumn(i++, "accountNumber", "开户账号"),
                    new ExcelColumn(i++, "roomType", "房型"),
                    new ExcelColumn(i++, "roomNumber", "房间数"),
                    new ExcelColumn(i++, "roomNightNumber", "间夜数"),
                    new ExcelColumn(i++, "receiveMoney", "收款额"),
                    new ExcelColumn(i++, "payMoney", "成本"),
                    new ExcelColumn(i++, "hangMoney", "挂账金额"),
                    new ExcelColumn(i++, "leaveMoney", "留账金额"),
                    new ExcelColumn(i++, "useLeaveMoney", "使用留账"),
                    new ExcelColumn(i++, "actualMoney", "实挂金额"),
                    new ExcelColumn(i++, "receiveMoneyRMB", "收款额(RMB)"),
                    new ExcelColumn(i++, "payMoneyRMB", "成本(RMB)"),
                    new ExcelColumn(i++, "hangMoneyRMB", "挂账金额(RMB)"),
                    new ExcelColumn(i++, "operator", "操作员"),
                    new ExcelColumn(i++, "hangRemark", "备注"),
                    new ExcelColumn(i++, "isFlightHotelOrderCN", "机酒子订单")
            );
            return generateHead(columns);
        }
    }

    public static ExcelHead getHotelOutAccountHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "code", "业务编号"),
                new ExcelColumn(i++, "applyStatus", "申请单状态"),
                new ExcelColumn(i++, "payTime", "付款时间", "date"),
                new ExcelColumn(i++, "checkTime", "审核时间", "date"),
                new ExcelColumn(i++, "outAccountTime", "出账时间", "date"),
                new ExcelColumn(i++, "hotelName", "酒店名称"),
                new ExcelColumn(i++, "supplierName", "收款人"),
                new ExcelColumn(i++, "hotelBank", "开户银行"),
                new ExcelColumn(i++, "accountNumber", "开户账号"),
                new ExcelColumn(i++, "receiveMoney", "收款额"),
                new ExcelColumn(i++, "actualMoney", "实挂金额"),
                new ExcelColumn(i++, "hangOperatorName", "提交人")
        );
        return generateHead(columns);
    }
    public static ExcelHead getJourneyPayApplyHead(Boolean isDetail,String businessType) {
        int i = 0;
        if(isDetail){
            if("journey".equals(businessType)){
                List<ExcelColumn> columns = Arrays.asList(
                        new ExcelColumn(i++, "payStatusCN", "付款状态"),
                        new ExcelColumn(i++, "orderId", "订单号"),
                        new ExcelColumn(i++, "businessTypeCN", "业务类型"),
                        new ExcelColumn(i++, "tourGroupMark", "团号"),
                        new ExcelColumn(i++, "tourRouterInfo", "产品名称"),
                        new ExcelColumn(i++, "receiveMoney", "收款金额"),
                        new ExcelColumn(i++, "adultPrice", "成人底价"),
                        new ExcelColumn(i++, "countOfAdult", "成人数"),
                        new ExcelColumn(i++, "childPrice", "儿童底价"),
                        new ExcelColumn(i++, "countOfChild", "儿童数"),
                        new ExcelColumn(i++, "balancePrice", "单房差"),
                        new ExcelColumn(i++, "countOfBalance", "单房差数"),
                        new ExcelColumn(i++, "ajustMoney", "调价金额"),
                        new ExcelColumn(i++, "abortMoney", "退团费"),
                        new ExcelColumn(i++, "allPayMoney", "付款总额"),
                        new ExcelColumn(i++, "earnestMoney", "定金"),
                        new ExcelColumn(i++, "payMoney", "付款金额"),
                        new ExcelColumn(i++, "departureDate", "出发日期","date"),
                        new ExcelColumn(i++, "backDate", "归团日期","date"),
                        new ExcelColumn(i++, "applyCode", "申请单号")
                );
                return generateHead(columns);
            }else{
                List<ExcelColumn> columns = Arrays.asList(
                        new ExcelColumn(i++, "payStatusCN", "付款状态"),
                        new ExcelColumn(i++, "orderId", "订单号"),
                        new ExcelColumn(i++, "businessTypeCN", "业务类型"),
                        new ExcelColumn(i++, "tourGroupMark", "团号"),
                        new ExcelColumn(i++, "tourRouterInfo", "产品名称"),
                        new ExcelColumn(i++, "receiveMoney", "收款金额"),
                        new ExcelColumn(i++, "abortMoney", "退团费"),
                        new ExcelColumn(i++, "alterMoney", "变更手续费"),
                        new ExcelColumn(i++, "allPayMoney", "付款总额"),
                        new ExcelColumn(i++, "earnestMoney", "定金"),
                        new ExcelColumn(i++, "payMoney", "付款金额"),
                        new ExcelColumn(i++, "departureDate", "出发日期","date"),
                        new ExcelColumn(i++, "backDate", "归团日期","date"),
                        new ExcelColumn(i++, "applyCode", "申请单号")
                );
                return generateHead(columns);
            }
        }else{
            if("journey".equals(businessType)){
                List<ExcelColumn> columns = Arrays.asList(
                        new ExcelColumn(i++, "payStatusCN", "付款状态"),
                        new ExcelColumn(i++, "orderId", "订单号"),
                        new ExcelColumn(i++, "businessTypeCN", "业务类型"),
                        new ExcelColumn(i++, "tourGroupMark", "团号"),
                        new ExcelColumn(i++, "tourRouterInfo", "产品名称"),
                        new ExcelColumn(i++, "receiveMoney", "收款金额"),
                        new ExcelColumn(i++, "adultPrice", "成人底价"),
                        new ExcelColumn(i++, "countOfAdult", "成人数"),
                        new ExcelColumn(i++, "childPrice", "儿童底价"),
                        new ExcelColumn(i++, "countOfChild", "儿童数"),
                        new ExcelColumn(i++, "balancePrice", "单房差"),
                        new ExcelColumn(i++, "countOfBalance", "单房差数"),
                        new ExcelColumn(i++, "ajustMoney", "调价金额"),
                        new ExcelColumn(i++, "abortMoney", "退团费"),
                        new ExcelColumn(i++, "allPayMoney", "付款总额"),
                        new ExcelColumn(i++, "earnestMoney", "定金"),
                        new ExcelColumn(i++, "hasPaySettleMoney", "已付金额"),
                        new ExcelColumn(i++, "settleMoney", "待付金额"),
                        new ExcelColumn(i++, "departureDate", "出发日期","date"),
                        new ExcelColumn(i++, "backDate", "归团日期","date"),
                        new ExcelColumn(i++, "applyCode", "申请单号")
                );
                return generateHead(columns);
            }else {
                List<ExcelColumn> columns = Arrays.asList(
                        new ExcelColumn(i++, "payStatusCN", "付款状态"),
                        new ExcelColumn(i++, "orderId", "订单号"),
                        new ExcelColumn(i++, "businessTypeCN", "业务类型"),
                        new ExcelColumn(i++, "tourGroupMark", "团号"),
                        new ExcelColumn(i++, "tourRouterInfo", "产品名称"),
                        new ExcelColumn(i++, "receiveMoney", "收款金额"),
                        new ExcelColumn(i++, "abortMoney", "退团费"),
                        new ExcelColumn(i++, "alterMoney", "变更手续费"),
                        new ExcelColumn(i++, "allPayMoney", "付款总额"),
                        new ExcelColumn(i++, "earnestMoney", "定金"),
                        new ExcelColumn(i++, "hasPaySettleMoney", "已付金额"),
                        new ExcelColumn(i++, "settleMoney", "待付金额"),
                        new ExcelColumn(i++, "departureDate", "出发日期","date"),
                        new ExcelColumn(i++, "backDate", "归团日期","date"),
                        new ExcelColumn(i++, "applyCode", "申请单号")
                );
                return generateHead(columns);
            }
        }
    }
    public static ExcelHead getJourneyPayHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "applyCode", "申请单号"),
                new ExcelColumn(i++, "payStatusCN", "付款状态"),
                new ExcelColumn(i++, "tourGroupMark", "团号"),
                new ExcelColumn(i++, "businessTypeCN", "业务类型"),
                new ExcelColumn(i++, "supplierName", "供应商"),
                new ExcelColumn(i++, "payMoney", "付款总额"),
                new ExcelColumn(i++, "applyDate", "申请日期","date"),
                new ExcelColumn(i++, "applyOperatorName", "申请人"),
                new ExcelColumn(i++, "payDate", "付款日期","date"),
                new ExcelColumn(i++, "payOperatorName", "付款人")
        );
        return generateHead(columns);
    }
    //票证中心导入 add by mobangwei//
    public static ExcelHead getBillcenterStorageHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "wareHouseInCode", "入库单号"),
                new ExcelColumn(i++, "billtype", "票证类型"),
                new ExcelColumn(i++, "status", "入库状态"),
                new ExcelColumn(i++, "fromCode", "起始单号"),
                new ExcelColumn(i++, "toCode", "终止单号"),
                new ExcelColumn(i++, "number", "入库数量"),
                new ExcelColumn(i++, "office", "office号"),
                new ExcelColumn(i++, "remark", "备注"),
                new ExcelColumn(i++, "operateTime", "操作时间", "date"),
                new ExcelColumn(i++, "operateUser", "操作员")
        );
        return generateHead(columns);
    }

    public static ExcelHead getBillcenterDetailHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "serialCode", "流水号"),
                new ExcelColumn(i++, "billtype", "票证类型"),
                new ExcelColumn(i++, "ticketCode", "票号"),
                new ExcelColumn(i++, "useType", "使用状态"),
                new ExcelColumn(i++, "office", "office号"),
                new ExcelColumn(i++, "clientName", "客户名称"),
                new ExcelColumn(i++, "aircraftPerson", "乘机人"),
                new ExcelColumn(i++, "remark", "备注"),
                new ExcelColumn(i++, "operateTime", "操作时间", "date"),
                new ExcelColumn(i++, "operateUser", "操作员")
        );
        return generateHead(columns);
    }
    /**
     * ---订单查询--运营商
     * 运营商订单监控也使用这个方法
     * @param isDomestic
     * @return
     */
    public static ExcelHead getClientFlightOrderHead(Boolean isDomestic) {
        int i = 0;
        List<ExcelColumn> columns=null;
        if(isDomestic){
            columns= Arrays.asList(
                    new ExcelColumn(i++, "flightOrderItemId", "订单编号"),
                    new ExcelColumn(i++, "pnr", "PNR"),
                    new ExcelColumn(i++, "departureAirplainCode", "出发城市"),
                    new ExcelColumn(i++, "arrivalAirplainCode", "到达城市"),
                    new ExcelColumn(i++, "flightNo", "航班号"),
                    new ExcelColumn(i++, "departmentDate", "航班日期"),
                    new ExcelColumn(i++, "classCode", "舱位"),
                    new ExcelColumn(i++, "ticketNo", "票号"),
                    new ExcelColumn(i++, "passengerName", "乘机人"),
                    new ExcelColumn(i++, "idNo", "证件号码"),
                    new ExcelColumn(i++, "passengerType", "旅客类型"),
                    new ExcelColumn(i++, "airFare", "票款"),
                    new ExcelColumn(i++, "tax", "税款"),
                    new ExcelColumn(i++, "customerPayAmount", "应收款"),
                    new ExcelColumn(i++, "gatheringType", "付款方式"),
                    new ExcelColumn(i++, "customerName", "客户名称"),
                    new ExcelColumn(i++, "purchaserType", "采购商类型"),
                    new ExcelColumn(i++, "bookingSystem", "建单平台"),
                    new ExcelColumn(i++, "bookingType", "建单方式"),
                    new ExcelColumn(i++, "orderCreatorName", "创建工号"),
                    new ExcelColumn(i++, "ticketIssueOperatorName", "出票工号"),
                    new ExcelColumn(i++, "gatheringOperatorName", "收款员"),
                    new ExcelColumn(i++, "issueDate", "出票日期"),
                    new ExcelColumn(i++, "ticketOfficeNo", "OFFICE号"),
                    new ExcelColumn(i++, "airPlainCode", "航司代码"),
                    new ExcelColumn(i++, "isDomestic", "国内/国际"),
                    new ExcelColumn(i++, "gatheringState", "收款状态"),
                    new ExcelColumn(i++, "orderState", "订单状态"),
                    new ExcelColumn(i++, "shareProfitSuccess", "分润状态"),
                    new ExcelColumn(i++, "orderTypeDesc", "票证类型"),
                    new ExcelColumn(i++, "isAdjust", "是否调帐"),
                    new ExcelColumn(i++, "outerMemo", "订单备注"),
                    new ExcelColumn(i++, "reviewerName", "审核人"),
                    new ExcelColumn(i++, "customerRefundCommAmount", "退废票费"),
                    new ExcelColumn(i++, "reviewDate", "审核日期"),
                    new ExcelColumn(i++, "supplierPayType", "供应商付款"),
                    new ExcelColumn(i++, "innerMemo", "内部备注"),
                    new ExcelColumn(i++, "tripAgrementId", "三方协议"),
                     new ExcelColumn(i++, "autoIssue", "是否自动出票"),
                     new ExcelColumn(i++, "autoIssuTypeExcel", "自动出票机构"),
                     new ExcelColumn(i++, "supplierName", "供应商名称"));
        }else{
            columns= Arrays.asList(
                    new ExcelColumn(i++, "flightOrderItemId", "订单编号"),
                    new ExcelColumn(i++, "pnr", "PNR"),
                    new ExcelColumn(i++, "itinerary", "航程"),
                    new ExcelColumn(i++, "flightNo", "航班号"),
                    new ExcelColumn(i++, "departmentDate", "航班日期"),
                    new ExcelColumn(i++, "classCode", "舱位"),
                    new ExcelColumn(i++, "ticketNo", "票号"),
                    new ExcelColumn(i++, "passengerName", "乘机人"),
                    new ExcelColumn(i++, "idNo", "证件号码"),
                    new ExcelColumn(i++, "passengerType", "旅客类型"),
                    new ExcelColumn(i++, "airFare", "票款"),
                    new ExcelColumn(i++, "tax", "税款"),
                    new ExcelColumn(i++, "customerPayAmount", "应收款"),
                    new ExcelColumn(i++, "gatheringType", "付款方式"),
                    new ExcelColumn(i++, "customerName", "客户名称"),
                    new ExcelColumn(i++, "purchaserType", "采购商类型"),
                    new ExcelColumn(i++, "bookingSystem", "建单平台"),
                    new ExcelColumn(i++, "bookingType", "建单方式"),
                    new ExcelColumn(i++, "orderCreatorName", "创建工号"),
                    new ExcelColumn(i++, "ticketIssueOperatorName", "出票工号"),
                    new ExcelColumn(i++, "gatheringOperatorName", "收款员"),
                    new ExcelColumn(i++, "issueDate", "出票日期"),
                    new ExcelColumn(i++, "ticketOfficeNo", "OFFICE号"),
                    new ExcelColumn(i++, "airPlainCode", "航司代码"),
                    new ExcelColumn(i++, "isDomestic", "国内/国际"),
                    new ExcelColumn(i++, "gatheringState", "收款状态"),
                    new ExcelColumn(i++, "orderState", "订单状态"),
                    new ExcelColumn(i++, "shareProfitSuccess", "分润状态"),
                    new ExcelColumn(i++, "orderTypeDesc", "票证类型"),
                    new ExcelColumn(i++, "isAdjust", "是否调帐"),
                    new ExcelColumn(i++, "outerMemo", "订单备注"),
                    new ExcelColumn(i++, "reviewerName", "审核人"),
                    new ExcelColumn(i++, "customerRefundCommAmount", "退废票费"),
                    new ExcelColumn(i++, "reviewDate", "审核日期"),
                    new ExcelColumn(i++, "supplierPayType", "供应商付款"),
                    new ExcelColumn(i++, "innerMemo", "内部备注"),
                    new ExcelColumn(i++, "tripAgrementId", "三方协议"),
                    new ExcelColumn(i++, "reviewEfficiency", "审核效率"),
                    new ExcelColumn(i++, "issueEfficiency", "出票效率"),
                    new ExcelColumn(i++, "supplierName", "供应商名称"));
        }
      /*new ExcelColumn(i++, "airConstructionFee", "机建"),
        new ExcelColumn(i++, "fuelSurcharge", "燃油"),
        new ExcelColumn(i++, "purchaserPreCommRatio", "客户返点"),
        new ExcelColumn(i++, "commission", "佣金"),
        new ExcelColumn(i++, "returnCommission", "后返佣金"),
        new ExcelColumn(i++, "extraFee", "附加费"),
        new ExcelColumn(i++, "state", "客票状态"),
        new ExcelColumn(i++, "consecutiveSeqNo", "票序号"),*/

        return generateHead(columns);
    }

    //---客票明细--运营商
    public static ExcelHead getClientTicketHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "ticketNo", "票号"),
                new ExcelColumn(i++, "flightOrderItemId", "订单编号"),
                new ExcelColumn(i++, "pnr", "PNR"),
                new ExcelColumn(i++, "flightNo", "航班号"),
                new ExcelColumn(i++, "lastTenTicketNo", "票号（10位）"),
                new ExcelColumn(i++, "passengerName", "乘机人"),
                new ExcelColumn(i++, "passengerType", "旅客类型"),
                new ExcelColumn(i++, "itineraryNo", "行程单号"),
                new ExcelColumn(i++, "itinerary", "航程"),
                new ExcelColumn(i++, "departureCityName", "出发城市"),
                new ExcelColumn(i++, "arrivalCityName", "到达城市"),
                new ExcelColumn(i++, "departmentDate", "航班日期"),
                new ExcelColumn(i++, "allDepartmentDate", "所有航班日期"),
                new ExcelColumn(i++, "classCode", "航位"),
                new ExcelColumn(i++, "airFare", "票款"),
                new ExcelColumn(i++, "tax", "税款"),
                new ExcelColumn(i++, "airConstructionFee", "机建"),
                new ExcelColumn(i++, "fuelSurcharge", "燃油"),
                new ExcelColumn(i++, "purchaserPreCommRatio", "客户返点"),
                new ExcelColumn(i++, "purchaserPreFixCommAmount", "客户返款"),
                new ExcelColumn(i++, "commission", "佣金"),
                new ExcelColumn(i++, "returnCommission", "后返佣金"),
                new ExcelColumn(i++, "customerPay", "应收款"),
                new ExcelColumn(i++, "extraFee", "附加费"),
                new ExcelColumn(i++, "supplierXCommRatio", "后返让利扣点"),
                new ExcelColumn(i++, "supplierXCommAmount", "后返让利金额"),
                new ExcelColumn(i++, "superiorCustomerKeepAmount", "分销商利润"),
                new ExcelColumn(i++, "gatheringType", "付款方式"),
                new ExcelColumn(i++, "customerName", "客户名称"),
                new ExcelColumn(i++, "purchaserType", "采购商类型"),
                new ExcelColumn(i++, "bookingSystem", "建单平台"),
                new ExcelColumn(i++, "bookingType", "建单方式"),
                new ExcelColumn(i++, "orderCreatorName", "创建工号"),
                new ExcelColumn(i++, "ticketIssueOperatorName", "出票工号"),
                new ExcelColumn(i++, "gatheringOperatorName", "收款员"),
                new ExcelColumn(i++, "issueDate", "出票日期"),
                new ExcelColumn(i++, "ticketOfficeNo", "OFFIC号"),
                new ExcelColumn(i++, "airPlainCode", "航司代码"),
                new ExcelColumn(i++, "isDomestic", "国内/国际"),
                new ExcelColumn(i++, "gatheringState", "收款状态"),
                new ExcelColumn(i++, "orderType", "票证类型"),
                new ExcelColumn(i++, "isAdjust", "是否调帐"),
                new ExcelColumn(i++, "idNo", "证件号码"),
                new ExcelColumn(i++, "outerMemo", "订单备注"),
                new ExcelColumn(i++, "consecutiveSeqNo", "票序号"),
                new ExcelColumn(i++, "reviewerName", "审核人"),
                new ExcelColumn(i++, "customerActualPayAmount", "退废票费"),
                new ExcelColumn(i++, "reviewDate", "审核时间"),
                new ExcelColumn(i++, "state", "客票状态"),
                new ExcelColumn(i++, "supplierPayType", "供应商付款方式"),
                new ExcelColumn(i++, "innerMemo", "内部备注"),
                new ExcelColumn(i++,"refundMemo","退票备注"),
                new ExcelColumn(i++, "tripAgrementId", "三方协议"),
                new ExcelColumn(i++, "autoIssue", "是否自动出票"),
                new ExcelColumn(i++, "autoIssueTypeExcel", "自动出票机构"),
                new ExcelColumn(i++, "uatp", "AIRPLUS"),
                new ExcelColumn(i++, "serviceFee", "AIRPLUS服务费"),
                new ExcelColumn(i++, "supplierName", "供应商"),
                new ExcelColumn(i++, "tktState", "客票状态")
        );
        return generateHead(columns);
    }
    //---客票明细--采购商
    public static ExcelHead getPurchaserClientTicketHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "ticketNo", "票号"),
                new ExcelColumn(i++, "flightOrderItemId", "订单编号"),
                new ExcelColumn(i++, "pnr", "PNR"),
                new ExcelColumn(i++, "flightNo", "航班号"),
                new ExcelColumn(i++, "lastTenTicketNo", "票号（10位）"),
                new ExcelColumn(i++, "passengerName", "乘机人"),
                new ExcelColumn(i++, "passengerType", "旅客类型"),
                new ExcelColumn(i++, "itineraryNo", "行程单号"),
                new ExcelColumn(i++, "itineraryType", "航程类型"),
                new ExcelColumn(i++, "itinerary", "行程"),
                new ExcelColumn(i++, "departmentDate", "航班日期"),
                new ExcelColumn(i++, "allDepartmentDate", "所有航班日期"),
                new ExcelColumn(i++, "classCode", "航位"),
                new ExcelColumn(i++, "airFare", "票款"),
                new ExcelColumn(i++, "tax", "税款"),
                new ExcelColumn(i++, "airFareAndTax", "应收票款"),
                new ExcelColumn(i++, "airConstructionFee", "机建"),
                new ExcelColumn(i++, "fuelSurcharge", "燃油"),
                new ExcelColumn(i++, "purchaserPreCommRatio", "客户返点"),
                new ExcelColumn(i++, "commission", "佣金"),
                new ExcelColumn(i++, "returnCommission", "后返佣金"),
                new ExcelColumn(i++, "customerPay", "应付款"),
                new ExcelColumn(i++, "extraFee", "附加费"),
                new ExcelColumn(i++, "gatheringType", "付款方式"),
                new ExcelColumn(i++, "issueDate", "出票日期"),
                new ExcelColumn(i++, "airPlainCode", "航司代码"),
                new ExcelColumn(i++, "isDomestic", "国内/国际"),
                new ExcelColumn(i++, "gatheringState", "付款状态"),
                new ExcelColumn(i++, "orderType", "票证类型"),
                new ExcelColumn(i++, "isAdjust", "是否调帐"),
                new ExcelColumn(i++, "idNo", "证件号码"),
                new ExcelColumn(i++, "outerMemo", "订单备注"),
                new ExcelColumn(i++, "consecutiveSeqNo", "票序号"),
                new ExcelColumn(i++, "customerActualPayAmount", "退废票费"),
                new ExcelColumn(i++, "state", "客票状态"),
                new ExcelColumn(i++, "tripAgrementId", "三方协议"),
                new ExcelColumn(i++, "orderCreatorFullName", "建单员")
        );
        return generateHead(columns);
    }
	//---客票明细--下级采购商
	public static ExcelHead getSubPurchaserClientTicketHead() {
		int i = 0;
		List<ExcelColumn> columns = Arrays.asList(
				new ExcelColumn(i++, "ticketNo", "票号"),
			    new ExcelColumn(i++, "flightOrderItemId", "订单编号"),
			    new ExcelColumn(i++, "pnr", "PNR"),
			    new ExcelColumn(i++, "flightNo", "航班号"),
			    new ExcelColumn(i++, "lastTenTicketNo", "票号（10位）"),
			    new ExcelColumn(i++, "passengerName", "乘机人"),
			    new ExcelColumn(i++, "passengerType", "旅客类型"),
			    new ExcelColumn(i++, "itineraryNo", "行程单号"),
			    new ExcelColumn(i++, "itineraryType", "航程类型"),
			    new ExcelColumn(i++, "itinerary", "行程"),
			    new ExcelColumn(i++, "departmentDate", "航班日期"),
			    new ExcelColumn(i++, "allDepartmentDate", "所有航班日期"),
			    new ExcelColumn(i++, "classCode", "舱位"),
			    new ExcelColumn(i++, "airFare", "票款"),
			    new ExcelColumn(i++, "tax", "税款"),
			    new ExcelColumn(i++, "airFareAndTax", "票款合计"),
			    new ExcelColumn(i++, "airConstructionFee", "机建"),
			    new ExcelColumn(i++, "fuelSurcharge", "燃油"),
			    new ExcelColumn(i++, "purchaserPreCommRatio", "客户返点"),
			    new ExcelColumn(i++, "commission", "佣金"),
			    new ExcelColumn(i++, "returnCommission", "客户返佣"),
			    new ExcelColumn(i++, "customerPay", "应付款"),
			    new ExcelColumn(i++, "extraFee", "附加费"),
			    new ExcelColumn(i++, "gatheringType", "付款方式"),
			    new ExcelColumn(i++, "issueDate", "出票日期"),
			    new ExcelColumn(i++, "airPlainCode", "航司代码"),
			    new ExcelColumn(i++, "isDomestic", "国内/国际"),
			    new ExcelColumn(i++, "gatheringState", "付款状态"),
			    new ExcelColumn(i++, "orderType", "票证类型"),
			    new ExcelColumn(i++, "isAdjust", "是否调帐"),
			    new ExcelColumn(i++, "idNo", "证件号码"),
			    new ExcelColumn(i++, "outerMemo", "订单备注"),
			    new ExcelColumn(i++, "consecutiveSeqNo", "票序号"),
			    new ExcelColumn(i++, "customerActualPayAmount", "退废票费"),
			    new ExcelColumn(i++, "state", "客票状态")
		);
		return generateHead(columns);
	}
	//---客票明细--采购商分销管理
	public static ExcelHead getPurchaserSubClientTicketHead() {
		int i = 0;
		List<ExcelColumn> columns = Arrays.asList(
				new ExcelColumn(i++, "customerName", "客户名称"),
				new ExcelColumn(i++, "ticketNo", "票号"),
			    new ExcelColumn(i++, "flightOrderItemId", "订单编号"),
			    new ExcelColumn(i++, "pnr", "PNR"),
			    new ExcelColumn(i++, "flightNo", "航班号"),
			    new ExcelColumn(i++, "lastTenTicketNo", "票号（10位）"),
			    new ExcelColumn(i++, "passengerName", "乘机人"),
			    new ExcelColumn(i++, "passengerType", "旅客类型"),
			    new ExcelColumn(i++, "itineraryNo", "行程单号"),
			    new ExcelColumn(i++, "itineraryType", "航程类型"),
			    new ExcelColumn(i++, "itinerary", "行程"),
			    new ExcelColumn(i++, "departmentDate", "航班日期"),
			    new ExcelColumn(i++, "allDepartmentDate", "所有航班日期"),
			    new ExcelColumn(i++, "classCode", "舱位"),
			    new ExcelColumn(i++, "airFare", "票款"),
			    new ExcelColumn(i++, "tax", "税款"),
			    new ExcelColumn(i++, "airFareAndTax", "票款合计"),
			    new ExcelColumn(i++, "airConstructionFee", "机建"),
			    new ExcelColumn(i++, "fuelSurcharge", "燃油"),
			    new ExcelColumn(i++, "purchaserPreCommRatio", "客户返点"),
			    new ExcelColumn(i++, "commission", "佣金"),
			    new ExcelColumn(i++, "returnCommission", "客户返佣"),
			    new ExcelColumn(i++, "customerPay", "应付款"),
			    new ExcelColumn(i++, "extraFee", "附加费"),
			    new ExcelColumn(i++, "superiorCustomerKeepRatio", "留点"),
			    new ExcelColumn(i++, "superiorCustomerKeepAmount", "留佣"),
			    new ExcelColumn(i++, "gatheringType", "付款方式"),
			    new ExcelColumn(i++, "issueDate", "出票日期"),
			    new ExcelColumn(i++, "airPlainCode", "航司代码"),
			    new ExcelColumn(i++, "isDomestic", "国内/国际"),
			    new ExcelColumn(i++, "gatheringState", "付款状态"),
			    new ExcelColumn(i++, "orderType", "票证类型"),
			    new ExcelColumn(i++, "isAdjust", "是否调帐"),
			    new ExcelColumn(i++, "idNo", "证件号码"),
			    new ExcelColumn(i++, "outerMemo", "订单备注"),
			    new ExcelColumn(i++, "consecutiveSeqNo", "票序号"),
			    new ExcelColumn(i++, "customerActualPayAmount", "退废票费"),
			    new ExcelColumn(i++, "state", "客票状态")
		);
		return generateHead(columns);
	}
    //---行程单
    //入库
    public static ExcelHead getTravelWareHouseInHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "wareHouseInCode", "入库单号"),
                new ExcelColumn(i++, "fromCode", "起始单号"),
                new ExcelColumn(i++, "toCode", "终止单号"),
                new ExcelColumn(i++, "number", "入库数量"),
                new ExcelColumn(i++, "status", "状态"),
                new ExcelColumn(i++, "notUseNumber", "未适用", "long"),
                new ExcelColumn(i++, "usedNumber", "已使用", "long"),
                new ExcelColumn(i++, "invalidNumber", "已作废", "long"),
                new ExcelColumn(i++, "recycledNumber", "已回收", "long"),
                new ExcelColumn(i++, "validNumber", "已生效", "long"),
                new ExcelColumn(i++, "office", "office"),
                new ExcelColumn(i++, "summary", "备注"),
                new ExcelColumn(i++, "operateTime", "操作时间", "date"),
                new ExcelColumn(i++, "operateUser", "操作员")
        );
        return generateHead(columns);
    }

    //明细
    public static ExcelHead getTravelTicketDetailInHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "serialCode", "流水号"),
                new ExcelColumn(i++, "travelCode", "行程单号"),
                new ExcelColumn(i++, "ticketCode", "票号"),
                new ExcelColumn(i++, "useType", "使用状态"),
                new ExcelColumn(i++, "recycleType", "回收状态"),
                new ExcelColumn(i++, "office", "office"),
                new ExcelColumn(i++, "clientName", "客户名称"),
                new ExcelColumn(i++, "tradePoint", "营业点"),
                new ExcelColumn(i++, "summary", "备注"),
                new ExcelColumn(i++, "operateTime", "操作时间", "date"),
                new ExcelColumn(i++, "operateUser", "操作人")
        );
        return generateHead(columns);
    }

    //库存
    public static ExcelHead getTravelWareHouseStoreHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "wareHouseInCode", "入库单号"),
                new ExcelColumn(i++, "fromCode", "起始单号"),
                new ExcelColumn(i++, "toCode", "终止单号"),
                new ExcelColumn(i++, "number", "库存数量"),
                new ExcelColumn(i++, "office", "office"),
                new ExcelColumn(i++, "summary", "备注"),
                new ExcelColumn(i++, "operateTime", "操作时间", "date"),
                new ExcelColumn(i++, "operateUser", "操作员")
        );
        return generateHead(columns);
    }

    //申请
    public static ExcelHead getTravelApplyConfrimHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "applyCode", "申请单号"),
                new ExcelColumn(i++, "status", "状态"),
                new ExcelColumn(i++, "fromCode", "起始单号"),
                new ExcelColumn(i++, "toCode", "终止单号"),
                new ExcelColumn(i++, "applyNumber", "申请数量", "long"),
                new ExcelColumn(i++, "actualNumber", "实发数量", "long"),
                new ExcelColumn(i++, "office", "office"),
                new ExcelColumn(i++, "applyClient", "申请客户"),
                new ExcelColumn(i++, "summary", "备注"),
                new ExcelColumn(i++, "applyTime", "申请时间", "date"),
                new ExcelColumn(i++, "applyUser", "申请人"),
                new ExcelColumn(i++, "tradePoint", "营业点")
        );
        return generateHead(columns);
    }

    //分发
    public static ExcelHead getTravelTicketDistributeHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "status", "状态"),
                new ExcelColumn(i++, "fromCode", "起始单号"),
                new ExcelColumn(i++, "toCode", "终止单号"),
                new ExcelColumn(i++, "applyCode", "申请单号"),
                new ExcelColumn(i++, "actualNumber", "分发数量", "long"),
                new ExcelColumn(i++, "office", "office"),
                new ExcelColumn(i++, "applyClient", "申请客户"),
                new ExcelColumn(i++, "summary", "备注"),
                new ExcelColumn(i++, "applyTime", "申请时间", "date"),
                new ExcelColumn(i++, "applyUser", "申请人"),
                new ExcelColumn(i++, "tradePoint", "营业点"),
                new ExcelColumn(i++, "operateUser", "操作人")
        );
        return generateHead(columns);
    }

    //作废
    public static ExcelHead getTravelInvalidHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "serialCode", "流水号"),
                new ExcelColumn(i++, "travelCode", "行程单号"),
                new ExcelColumn(i++, "ticketCode", "票号"),
                new ExcelColumn(i++, "useType", "使用状态"),
                new ExcelColumn(i++, "recycleType", "回收状态"),
                new ExcelColumn(i++, "office", "office"),
                new ExcelColumn(i++, "clientName", "客户名称"),
                new ExcelColumn(i++, "tradePoint", "营业点"),
                new ExcelColumn(i++, "summary", "备注"),
                new ExcelColumn(i++, "operateTime", "操作时间", "date"),
                new ExcelColumn(i++, "operateUser", "操作人")
        );
        return generateHead(columns);
    }

    //统计
    public static ExcelHead getTravelStatisticConfirmHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "wareHouseInCode", "入库单号"),
                new ExcelColumn(i++, "fromCode", "起始单号"),
                new ExcelColumn(i++, "toCode", "终止单号"),
                new ExcelColumn(i++, "number", "入库数量", "long"),
                new ExcelColumn(i++, "notUseNumber", "未使用", "long"),
                new ExcelColumn(i++, "useNumber", "已使用", "long"),
                new ExcelColumn(i++, "invalidNumber", "已作废", "long"),
                new ExcelColumn(i++, "recycledNumber", "已回收", "long"),
                new ExcelColumn(i++, "extractNumber", "已抽调", "long"),
                new ExcelColumn(i++, "validNumber", "已生效", "long"),
                new ExcelColumn(i++, "office", "office"),
                new ExcelColumn(i++, "operateTime", "操作时间", "date"),
                new ExcelColumn(i++, "clientName", "客户名称")
        );
        return generateHead(columns);
    }

    //酒店订单明细
    public static ExcelHead getPurchaserHotelOrderHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "id", "订单号"),
                new ExcelColumn(i++, "hotelName", "酒店名称"),
                new ExcelColumn(i++, "cityName", "城市"),
                new ExcelColumn(i++, "roomCat", "房型"),
                new ExcelColumn(i++, "roomNo", "房间数"),
                new ExcelColumn(i++, "roomNightNo", "间夜数"),
                new ExcelColumn(i++, "guestNames", "入住人"),
                new ExcelColumn(i++, "createDate", "下单日期","Date"),
                new ExcelColumn(i++, "checkinDate", "入住日期","Date"),
                new ExcelColumn(i++, "checkoutDate", "离店日期","Date"),
                new ExcelColumn(i++, "state", "订单状态"),
                new ExcelColumn(i++, "totalRoomFeeYuan", "房费"),
                new ExcelColumn(i++, "totalAddServiceFeeYuan", "增值"),
                new ExcelColumn(i++, "totalOrderFeeYuan", "应收合计"),
                new ExcelColumn(i++, "pagePaymentType", "支付方式"),
                new ExcelColumn(i++, "payWay", "付款状态"),
                new ExcelColumn(i++, "creatorName", "操作员")
        );
        return generateHead(columns);
    }

    //机票供应商付款报表
    public static ExcelHead getSupplierPayReportHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "ticketType", "票类"),
                new ExcelColumn(i++, "supplierName", "供应商名称"),
                new ExcelColumn(i++, "airline", "航空公司"),
                new ExcelColumn(i++, "office", "OFFICE号"),
                new ExcelColumn(i++, "earlierConfirmButNotPayDisbursements", "期初已确认收入成本未付款"),
                new ExcelColumn(i++, "earlierConfirmButNotPayRefund", "期初应收供应商退款"),
                new ExcelColumn(i++, "earlierConfirmButNotPayRemainder", "期初应付供应商余额"),
                new ExcelColumn(i++, "earlierNotConfirmButPayRefund", "期初供应商已退款未确认成本"),
                new ExcelColumn(i++, "periodConfirmRemainder", "本期成本"),
                new ExcelColumn(i++, "periodPayDisbursements", "本期付款金额"),
                new ExcelColumn(i++, "periodPayRefund", "本期收到供应商退款金额"),
                new ExcelColumn(i++, "periodPayRemainder", "本期付款"),
                new ExcelColumn(i++, "latestConfirmButNotPayDisbursements", "期末已确认收入成本未付款"),
                new ExcelColumn(i++, "latestConfirmButNotPayRefund", "期末应收供应商退款"),
                new ExcelColumn(i++, "latestConfirmButNotPayRemainder", "期末应付供应商余额"),
                new ExcelColumn(i++, "latestNotConfirmButPayRefund", "期末供应商已退款未确认成本")
        );
        return generateHead(columns);
    }

    //保险保单报表
    public static ExcelHead getInsurancePolicyReportHead(){
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++, "id", "订单号"),
                new ExcelColumn(i++, "insurancePolicyCode", "保单号"),
                new ExcelColumn(i++, "externalOrderId", "外部订单号"),
                new ExcelColumn(i++, "insurancePolicyStatus", "保单状态"),
                new ExcelColumn(i++, "gatheringState", "收款状态"),
                new ExcelColumn(i++, "insureeName", "被保人"),
                new ExcelColumn(i++, "docType", "证件类型"),
                new ExcelColumn(i++, "docCode", "证件号码"),
                new ExcelColumn(i++, "gender", "性别"),
                new ExcelColumn(i++, "insuranceSaleCompany", "保险公司"),
                new ExcelColumn(i++, "insuranceSaleName", "保险产品"),
                new ExcelColumn(i++, "customerName", "客户名称"),
                new ExcelColumn(i++, "orderType", "采购类型"),
                new ExcelColumn(i++, "insurerName", "投保人"),
                new ExcelColumn(i++, "insuranceAmount", "保单份数"),
                new ExcelColumn(i++, "insuranceSalePerPrice", "保费","Date"),
                new ExcelColumn(i++, "insureDate", "投保日期","Date"),
                new ExcelColumn(i++, "abortInsureDate", "退保日期","Date"),
                new ExcelColumn(i++, "creatorName", "创建人")
        );
        return generateHead(columns);
    }

    public static ExcelHead getInvoiceOrderHead(){
        int i=0;
        List<ExcelColumn> columns = Arrays.asList(
            new ExcelColumn(i++, "orderId", "订单号"),
            new ExcelColumn(i++, "applyId", "申请单号"),
            new ExcelColumn(i++, "customerName", "客户名称"),
            new ExcelColumn(i++, "businessType", "业务类型"),
            new ExcelColumn(i++, "checkinDate", "入住日期", "date"),
            new ExcelColumn(i++, "receiveMoney", "收款金额", "double"),
            new ExcelColumn(i++, "invoicedMoney", "已开票金额", "double"),
            new ExcelColumn(i++, "availableMoney", "可开票金额", "double"),
            new ExcelColumn(i++, "invoiceStatus", "开票状态"),
            new ExcelColumn(i++, "invoiceNo", "发票号"),
            new ExcelColumn(i++, "remark", "相关消息")
        );
        return generateHead(columns);
    }

    public static ExcelHead getInvoiceApplyMoneyDetailHead(){
        int i=0;
        List<ExcelColumn> columns = Arrays.asList(
            new ExcelColumn(i++, "applyId", "申请单号"),
            new ExcelColumn(i++, "produceOrderId", "订单号"),
            new ExcelColumn(i++, "consumeOrderId", "抵销订单"),
            new ExcelColumn(i++, "money", "金额", "double")
        );
        return generateHead(columns);
    }

    /**
     *
     * @return
     */
    public static ExcelHead getInvoiceApplyHead(){
        int i=0;
        List<ExcelColumn> columns = Arrays.asList(
            new ExcelColumn(i++, "applyId", "申请单号"),
            new ExcelColumn(i++, "customerName", "客户名称"),
            new ExcelColumn(i++, "applyDate", "申请时间", "date"),
            new ExcelColumn(i++, "applyStatus", "申请状态"),
            new ExcelColumn(i++, "invoiceTitle", "发票抬头"),
            new ExcelColumn(i++, "invoiceDate", "开票时间", "date"),
            new ExcelColumn(i++, "invoiceMoney", "开票金额", "double"),
            new ExcelColumn(i++, "invoiceNo", "发票号"),
            new ExcelColumn(i++, "applicantName", "提交人"),
            new ExcelColumn(i++, "needPost", "是否邮寄", "boolean"),
            new ExcelColumn(i++, "postAddress", "邮寄信息"),
            new ExcelColumn(i++, "remark", "备注")
        );
        return generateHead(columns);
    }

    public static ExcelHead getInvoiceOrderProviderHead(){

        int i=0;
        List<ExcelColumn> columns = Arrays.asList(
            new ExcelColumn(i++, "orderId", "订单号"),
            new ExcelColumn(i++, "supplierName", "供应商名称"),
            new ExcelColumn(i++, "businessType", "业务类型"),
            new ExcelColumn(i++, "outAccountDate", "出账日期", "date"),
            new ExcelColumn(i++, "payMoney", "付款金额", "double"),
            new ExcelColumn(i++, "invoicedMoney", "已收发票金额", "double"),
            new ExcelColumn(i++, "dueInInvoiceMoney", "待收金额", "double"),
            new ExcelColumn(i++, "invoiceProviderStatus", "发票状态"),
            new ExcelColumn(i++, "invoiceNo", "发票号"),
            new ExcelColumn(i++, "remark", "备注"),
            new ExcelColumn(i++, "contractManager", "签约经理"),
            new ExcelColumn(i++, "hotelName", "酒店名称"),
            new ExcelColumn(i++, "inClient", "入住人"),
            new ExcelColumn(i++, "inTime", "入住日期","date"),
            new ExcelColumn(i++, "leaveTime", "离店时间","date"),
            new ExcelColumn(i++, "roomType", "房型"),
            new ExcelColumn(i++, "code", "付款编号")
        );
        return generateHead(columns);

    }

    //机票供应商汇总报表
    public static ExcelHead getSupplierSummaryReportHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "supplierName", "供应商名称"),
                new ExcelColumn(i++, "area", "区域"),
                new ExcelColumn(i++, "flightTicketNum", "出票张数"),
                new ExcelColumn(i++, "salesAmount", "销售额"),
                new ExcelColumn(i++, "issueEfficiency", "出票效率(分)"),
                new ExcelColumn(i++, "refundTicketNum", "退废张数"),
                new ExcelColumn(i++, "refundAmount", "应退款"),
                new ExcelColumn(i++, "refundEfficiency", "退票效率(小时)")
        );
        return generateHead(columns);
    }

    //机票采购商汇总报表
    public static ExcelHead getPurchaserSummaryReportHead(String condDomestic) {
        int i = 0;
        List<ExcelColumn> columns = new ArrayList<ExcelColumn>();
        columns.add(new ExcelColumn(i++, "customerName", "客户名称"));
        columns.add(new ExcelColumn(i++, "customerType", "客户类型"));
        columns.add(new ExcelColumn(i++, "area", "区域"));
        columns.add(new ExcelColumn(i++, "customerSourcename", "注册平台"));
        columns.add(new ExcelColumn(i++, "salesMan", "销售员"));
        if("All".equals(condDomestic)){
            columns.add(new ExcelColumn(i++, "saleCountDomestic", "机票张数(国内)"));
            columns.add(new ExcelColumn(i++, "saleCountInternational", "机票张数(国际)"));
            columns.add(new ExcelColumn(i++, "saleCount", "机票张数"));
            columns.add(new ExcelColumn(i++, "saleAmountDomestic", "销售额(国内)"));
            columns.add(new ExcelColumn(i++, "saleAmountInternational", "销售额(国际)"));
            columns.add(new ExcelColumn(i++, "saleAmount", "销售额"));
        }else if("Domestic".equals(condDomestic)){
            columns.add(new ExcelColumn(i++, "saleCountDomestic", "机票张数(国内)"));
            columns.add(new ExcelColumn(i++, "saleAmountDomestic", "销售额(国内)"));
        }else if("International".equals(condDomestic)){
            columns.add(new ExcelColumn(i++, "saleCountInternational", "机票张数(国际)"));
            columns.add(new ExcelColumn(i++, "saleAmountInternational", "销售额(国际)"));
        }

        return generateHead(columns);
    }

    //机票-员工出票汇总报表
    public static ExcelHead getEmployeeIssueSummaryReportHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "employeeID", "员工号"),
                new ExcelColumn(i++, "employeeName", "员工姓名"),
                new ExcelColumn(i++, "issueSales", "出票销售额"),
                new ExcelColumn(i++, "createOrderNum", "建单张数"),
                new ExcelColumn(i++, "fitTicketNum", "出票张数(散客)"),
                new ExcelColumn(i++, "groupTicketNum", "出票张数(团队)"),
                new ExcelColumn(i++, "issueTicketNum", "出票张数（总计）"),
                new ExcelColumn(i++, "issueEfficiency", "出票效率（分钟）")
        );
        return generateHead(columns);
    }

    //机票-员工退废票汇总报表
    public static ExcelHead getEmployeeRefundSummaryReportHead() {
        int i = 0;
        List<ExcelColumn> columns = Arrays.asList(
                new ExcelColumn(i++, "employeeID", "员工号"),
                new ExcelColumn(i++, "employeeName", "员工姓名"),
                new ExcelColumn(i++, "refundReviewNum", "退票审核数"),
                new ExcelColumn(i++, "refundCompleteNum", "退票完成数"),
                new ExcelColumn(i++, "abondonReviewNum", "废票审核数"),
                new ExcelColumn(i++, "abondonCompleteNum", "废票完成数"),
                new ExcelColumn(i++, "totalReviewNum", "合计审核数"),
                new ExcelColumn(i++, "totalCompleteNum", "合计完成数")
        );
        return generateHead(columns);
    }

    //酒店供应商汇总报表 add by Weipeng.Guo
    public static ExcelHead getHotelSupplierSummaryReportHead(List<String> headCondition, String bookingType){
        int i = 0;
        List<ExcelColumn> columns = new ArrayList<ExcelColumn>();
        if(!headCondition.isEmpty()){
            for(String headStr : headCondition){
                if(headStr.equals("Region")){
                    columns.add(new ExcelColumn(i++, "regionName", "区域"));
                }
                else if(headStr.equals("Province")){
                    columns.add(new ExcelColumn(i++, "provinceName", "省/直辖市"));
                }
                else if(headStr.equals("City")){
                    columns.add(new ExcelColumn(i++, "cityName", "城市"));
                }
                else if(headStr.equals("ContractManager")){
                    columns.add(new ExcelColumn(i++, "contractManagerName", "签约经理"));
                }
                else if(headStr.equals("Supplier")){
                    columns.add(new ExcelColumn(i++, "supplierName", "供应商"));
                }
                else if(headStr.equals("Hotel")){
                    columns.add(new ExcelColumn(i++, "hotelName", "酒店名称"));
                }
            }
        }
        columns.add(new ExcelColumn(i++, "orderNo", "订单数"));
        columns.add(new ExcelColumn(i++, "roomNo", "房间数"));
        columns.add(new ExcelColumn(i++, "roomNightNo", "间夜数"));
        columns.add(new ExcelColumn(i++, "totalOrderFee", "销售额"));
        if(bookingType.equals("PRE_PAID")){
            columns.add(new ExcelColumn(i++, "totalCost", "成本"));
        }
        else if(bookingType.equals("SPOT_PAID")){
            columns.add(new ExcelColumn(i++, "totalSupplierCommission", "供应商返佣"));
            columns.add(new ExcelColumn(i++, "totalCustomerCommission", "客户返佣"));
        }
        columns.add(new ExcelColumn(i++, "totalProfit", "利润"));

        return generateHead(columns);
    }

    //酒店采购商汇总报表 add by Weipeng.Guo
        public static ExcelHead getHotelPurchaserSummaryReportHead(List<String> headCondition, String bookingType){
            int i = 0;
            List<ExcelColumn> columns = new ArrayList<ExcelColumn>();
            if(headCondition.size() != 0){
                for(String headStr : headCondition){
                    if(headStr.equals("Creator")){
                        columns.add(new ExcelColumn(i++, "creatorName", "建单员"));
                    }else if(headStr.equals("Merchandiser")){
                        columns.add(new ExcelColumn(i++, "merchandiserName", "受理员"));
                    }else if(headStr.equals("ContractManager")){
                        columns.add(new ExcelColumn(i++, "contractManagerName", "签约经理"));
                    }else if(headStr.equals("Customer")){
                        columns.add(new ExcelColumn(i++, "customerName", "客户名称"));
                    }
                }
            }
            columns.add(new ExcelColumn(i++, "orderNo", "订单数"));
            columns.add(new ExcelColumn(i++, "roomNo", "房间数"));
            columns.add(new ExcelColumn(i++, "roomNightNo", "间夜数"));
            columns.add(new ExcelColumn(i++, "totalOrderFee", "销售额"));
            if(bookingType.equals("PRE_PAID")){
                columns.add(new ExcelColumn(i++, "totalCost", "成本"));
            }
            else if(bookingType.equals("SPOT_PAID")){
                columns.add(new ExcelColumn(i++, "totalSupplierCommission", "供应商返佣"));
                columns.add(new ExcelColumn(i++, "totalCustomerCommission", "客户返佣"));
            }
            columns.add(new ExcelColumn(i++, "totalProfit", "利润"));

            return generateHead(columns);
        }

    //酒店预留房汇总报表 add by Weipeng.Guo
    public static ExcelHead getHotelRoomReserveSummaryReportHead(List<String> headCondition){
        int i = 0;
        List<ExcelColumn> columns = new ArrayList<ExcelColumn>();
        if(!headCondition.isEmpty()){
            for(String headStr : headCondition){
                if(headStr.equals("Region")){
                    columns.add(new ExcelColumn(i++, "regionName", "区域"));
                }else if(headStr.equals("Province")){
                    columns.add(new ExcelColumn(i++, "provinceName", "省/直辖市"));
                }else if(headStr.equals("City")){
                    columns.add(new ExcelColumn(i++, "cityName", "城市"));
                }else if(headStr.equals("ContractManager")){
                    columns.add(new ExcelColumn(i++, "contractManagerName", "签约经理"));
                }else if(headStr.equals("Hotel")){
                    columns.add(new ExcelColumn(i++, "hotelName", "酒店名称"));
                }
            }
        }

        columns.add(new ExcelColumn(i++, "orderNoComplete", "完成订单数"));
        columns.add(new ExcelColumn(i++, "orderNoRRComplete", "有预留房完成订单数"));
        columns.add(new ExcelColumn(i++, "orderNoCancel", "取消订单数"));
        columns.add(new ExcelColumn(i++, "orderNoRRCancel", "有预留房取消订单数"));
        columns.add(new ExcelColumn(i++, "roomNightNoComplete", "完成间夜数"));
        columns.add(new ExcelColumn(i++, "roomNightNoRRComplete", "有预留房完成间夜数"));
        columns.add(new ExcelColumn(i++, "roomNightNoCancel", "取消间夜数"));
        columns.add(new ExcelColumn(i++, "roomNightNoRRCancel", "有预留房取消间夜数"));

        return generateHead(columns);
    }

    //旅游-游客信息汇总表
    public static ExcelHead getVisitorReprotHead(){

        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"id_number","护照号码"),
                new ExcelColumn(i++,"birthday","出生年月日"),
                new ExcelColumn(i++,"gender","性别"),
                new ExcelColumn(i++,"id_valid_date","护照有效期"),
                new ExcelColumn(i++,"py_name","姓名（拼音）"),
                new ExcelColumn(i++,"cn_name","姓名（中文）"),
                new ExcelColumn(i++,"mobel_phone","手机号码"),
                new ExcelColumn(i++,"birthplace","出生地点"),
                new ExcelColumn(i++,"visaplace","签发地点"),
                new ExcelColumn(i++,"visadate","签发日期"),
                new ExcelColumn(i++,"sale","销售"),
                new ExcelColumn(i++,"cusname","客户名称"),
                new ExcelColumn(i++,"createdate","报名时间")
                );
        return generateHead(columns);
    }

    public static ExcelHead getTourVisitorReportHead(){
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"index","编号"),
                new ExcelColumn(i++,"idNumber","护照号码"),
                new ExcelColumn(i++,"birthday","出生年月日"),
                new ExcelColumn(i++,"gender","性别"),
                new ExcelColumn(i++,"idValidDate","护照有效期"),
                new ExcelColumn(i++,"pyName","姓名（拼音）"),
                new ExcelColumn(i++,"cnName","姓名（中文）"),
                new ExcelColumn(i++,"mobelPhone","手机号码"),
                new ExcelColumn(i++,"birthplace","出生地点"),
                new ExcelColumn(i++,"visaplace","签证地点"),
                new ExcelColumn(i++,"visaDate","签证日期"),
                new ExcelColumn(i++,"saleName","销售"),
                new ExcelColumn(i++,"cusName","客户名称"),
                new ExcelColumn(i++,"createDate","报名时间")
                );
        return generateHead(columns);
    }

    //旅游-基础数据-国家
    public static ExcelHead getJnBasedataCountryHead(){
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"continent","大州"),
                new ExcelColumn(i++,"nameCn","中文名"),
                new ExcelColumn(i++,"nameEn","英文名"),
                new ExcelColumn(i++,"hotLevel","热点级别"),
                new ExcelColumn(i++,"code","编码")
                );
        return generateHead(columns);
    }

    //旅游-基础数据-城市
    public static ExcelHead getJnBasedataCityHead(){
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"country","国家"),
                new ExcelColumn(i++,"nameCn","中文名"),
                new ExcelColumn(i++,"nameEn","英文名"),
                new ExcelColumn(i++,"hotLevel","热点级别")
                );
        return generateHead(columns);
    }

    //旅游-基础数据-景点
    public static ExcelHead getJnBasedataAttractionHead(){
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"city","城市"),
                new ExcelColumn(i++,"nameCn","中文名"),
                new ExcelColumn(i++,"nameEn","英文名"),
                new ExcelColumn(i++,"summary","概述")
                );
        return generateHead(columns);
    }

    // 签证面谈统计表
    public static ExcelHead getVisaAuditionReportHead(){
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++, "auditionDateDay", "面谈日期","Date"),
                new ExcelColumn(i++, "auditionDateTime", "签证时间"),
                new ExcelColumn(i++, "nameCn", "客人姓名"),
                new ExcelColumn(i++, "reserveCity", "领馆"),
                new ExcelColumn(i++, "visaGroupCode", "团号"),
                new ExcelColumn(i++, "salesmanName", "销售"),
                new ExcelColumn(i++, "passportState", "护照"),
                new ExcelColumn(i++, "photoState", "照片"),
                new ExcelColumn(i++, "passportCode", "护照号码"),
                new ExcelColumn(i++, "reserveBarcode", "AA条形码"),
                new ExcelColumn(i++, "remark", "备注")
        );
        return generateHead(columns);
    }
    public static ExcelHead getAccuracyReportHead() {

        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"reportDate","时间"),
                new ExcelColumn(i++,"orderId","订单号"),
                new ExcelColumn(i++,"pnr","PNR"),
                new ExcelColumn(i++,"itinerary","航段"),
                new ExcelColumn(i++,"flightSegmentNum","航段数"),
                new ExcelColumn(i++,"supplierPricePolicyId","成人政策编号"),
                new ExcelColumn(i++,"childSupplierPricePolicyId","儿童政策编号"),
                new ExcelColumn(i++,"customerPayAmountWhenCreate","订单金额"),
                new ExcelColumn(i++,"customerPayAmountWhenReview","订单金额(自)"),
                new ExcelColumn(i++,"airFareWhenCreate","成人票款"),
                new ExcelColumn(i++,"airFareWhenReview","成人票款(自)"),
                new ExcelColumn(i++,"taxWhenCreate","成人税款"),
                new ExcelColumn(i++,"taxWhenReview","成人税款(自)"),
                new ExcelColumn(i++,"purchaserPreBaseCommRatioWhenCreate","成人代理费"),
                new ExcelColumn(i++,"purchaserPreBaseCommRatioWhenReview","成人代理费(自)"),
                new ExcelColumn(i++,"purchaserPreCommRatioWhenCreate","成人奖励扣率"),
                new ExcelColumn(i++,"purchaserPreCommRatioWhenReview","成人奖励扣率(自)"),
                new ExcelColumn(i++,"purchaserPreFixCommAmountWhenCreate","成人奖励金额"),
                new ExcelColumn(i++,"purchaserPreFixCommAmountWhenReview","成人奖励金额(自)"),
                new ExcelColumn(i++,"issueTicketFeeWhenCreate","成人开票费"),
                new ExcelColumn(i++,"issueTicketFeeWhenReview","成人开票费(自)"),
                new ExcelColumn(i++,"childAirFareWhenCreate","儿童票款"),
                new ExcelColumn(i++,"childAirFareWhenReview","儿童票款(自)"),
                new ExcelColumn(i++,"childTaxWhenCreate","儿童税款"),
                new ExcelColumn(i++,"childTaxWhenReview","儿童税款(自)"),
                new ExcelColumn(i++,"childPurchaserPreBaseCommRatioWhenCreate","儿童代理费"),
                new ExcelColumn(i++,"childPurchaserPreBaseCommRatioWhenReview","儿童代理费(自)"),
                new ExcelColumn(i++,"childPurchaserPreCommRatioWhenCreate","儿童奖励扣率"),
                new ExcelColumn(i++,"childPurchaserPreCommRatioWhenReview","儿童奖励扣率(自)"),
                new ExcelColumn(i++,"childPurchaserPreFixCommAmountWhenCreate","儿童奖励金额"),
                new ExcelColumn(i++,"childPurchaserPreFixCommAmountWhenReview","儿童奖励金额(自)"),
                new ExcelColumn(i++,"childIssueTicketFeeWhenCreate","儿童开票费"),
                new ExcelColumn(i++,"childIssueTicketFeeWhenReview","儿童开票费(自)"),
                new ExcelColumn(i++,"totalElapseTimeSec","政策匹配总耗时(秒)"),
                new ExcelColumn(i++,"fareByPnrElapseTimeSec","获取运价耗时(秒)"),
                new ExcelColumn(i++,"stepPnrParseElapseTimeSec","获取分段价格耗时(秒)"),
                new ExcelColumn(i++,"flightPriceElapseTimeSec","匹配政策价格耗时(秒)")
                );
        return generateHead(columns);
    }

    /**
     * 签证结算下载详情
     * @return
     */
    public static ExcelHead getVisaSettlementDetail() {
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"orderId","订单号"),
                new ExcelColumn(i++,"visaGroupCode","签证团号"),
                new ExcelColumn(i++,"productName","产品名称"),
                new ExcelColumn(i++,"payMoney","付款金额","money"),
                new ExcelColumn(i++,"auditDate","审核时间","date"),
                new ExcelColumn(i++,"orderType","订单类型")
                );
        return generateHead(columns);
    }

    /**
     * 签证审核
     * @return
     */
    public static ExcelHead getVisaAuditHead(){
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"auditStatusCn","审核状态"),
                new ExcelColumn(i++,"orderId","订单号"),
                new ExcelColumn(i++,"visaGroupCode","签证团号"),
                new ExcelColumn(i++,"productName","产品名称"),
                new ExcelColumn(i++,"totalMoneyStr","销售额","money"),
                new ExcelColumn(i++,"payMoneyStr","付款金额","money"),
                new ExcelColumn(i++,"completeDate","订单完成时间","date"),
                new ExcelColumn(i++,"auditDate","审核时间","date"),
                new ExcelColumn(i++,"orderType","订单类型"),
                new ExcelColumn(i++,"applyCode","申请单号")
                );
        return generateHead(columns);
    }

    /**
     * 签证付款申请
     * @return
     */
    public static ExcelHead getVisaApplyHead(){
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"payStatusCn","付款状态"),
                new ExcelColumn(i++,"applyCode","申请单号"),
                new ExcelColumn(i++,"visaGroupCode","签证团号"),
                new ExcelColumn(i++,"productName","产品名称"),
                new ExcelColumn(i++,"payMoneyStr","付款金额","money"),
                new ExcelColumn(i++,"completeDate","完成时间","date"),
                new ExcelColumn(i++,"applyDate","申请日期","date")
                );
        return generateHead(columns);
    }
    //酒店订单效率总报表 add by jiajing.hu
    public static ExcelHead getHotelOrderEfficiSummaryReportHead(List<String> headCondition) {
        int i = 0;
        List<ExcelColumn> columns = new ArrayList<ExcelColumn>();
        if (!headCondition.isEmpty()) {
            for (String headStr : headCondition) {
                if (headStr.equals("OrderCreator")) {
                    columns.add(new ExcelColumn(i++, "orderCreator", "建单员"));
                } else if (headStr.equals("Hotel")) {
                    columns.add(new ExcelColumn(i++, "hotelName", "酒店名称"));
                }
            }
        }

        columns.add(new ExcelColumn(i++, "orderNoComplete", "完成订单数"));
        columns.add(new ExcelColumn(i++, "efficiOrderComplete", "完成订单效率（小时）"));
        columns.add(new ExcelColumn(i++, "orderNoCancel", "取消订单数"));
        columns.add(new ExcelColumn(i++, "efficiOrderCancel", "取消订单效率（小时）"));

        return generateHead(columns);
    }
    //酒店业务绩效报表 add by weipeng.guo
    public static ExcelHead getHotelPerformanceSummaryReportHead() {
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"orderCreator","建单员"),
                new ExcelColumn(i++,"totalSale","销售额","money"),
                new ExcelColumn(i++,"totalCost","成本","money"),
                new ExcelColumn(i++,"totalProfit","利润","money")
                );
        return generateHead(columns);
    }

    public static ExcelHead getHotelPerformanceDetailReportHead() {
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"orderCreator","建单员"),
                new ExcelColumn(i++,"orderId","订单号"),
                new ExcelColumn(i++,"orderType","订单类型"),
                new ExcelColumn(i++,"purchaserName","客户"),
                new ExcelColumn(i++,"cityName","城市"),
                new ExcelColumn(i++,"hotelName","酒店"),
                new ExcelColumn(i++,"guestName","客人姓名"),
                new ExcelColumn(i++,"checkinDate","入住日期","date"),
                new ExcelColumn(i++,"checkoutDate","退房日期","date"),
                new ExcelColumn(i++,"roomNightNo","间夜数"),
                new ExcelColumn(i++,"totalSale","销售价","money"),
                new ExcelColumn(i++,"totalCost","成本","money"),
                new ExcelColumn(i++,"totalProfit","利润","money")
                );
        return generateHead(columns);
    }

    //酒店业务订单明细报表 add by min.zhang
    public static ExcelHead getHotelOrderEfficiDetailReportHead() {
        int i=0;
        List<ExcelColumn> columns=Arrays.asList(
                new ExcelColumn(i++,"orderId","订单号"),
                new ExcelColumn(i++,"hotelName","酒店名称"),
                new ExcelColumn(i++,"orderCreator","建单员"),
                new ExcelColumn(i++,"bookingType","业务类型"),
                new ExcelColumn(i++,"checkinDate","入住日期","date"),
                new ExcelColumn(i++,"orderStatus","订单类型"),
                new ExcelColumn(i++,"orderEfficiency","订单效率")
                );
        return generateHead(columns);
    }

    //酒店业务采购商预付报表 add by min.zhang
        public static ExcelHead getHotelPurchaserDetailReportHead() {
            int i=0;
            List<ExcelColumn> columns=Arrays.asList(
                    new ExcelColumn(i++,"orderId","订单号"),
                    new ExcelColumn(i++,"hotelName","酒店名称"),
                    new ExcelColumn(i++,"customerName","客户名称"),
                    new ExcelColumn(i++,"contractManagerName","签约经理"),
                    new ExcelColumn(i++,"creatorName","建单员"),
                    new ExcelColumn(i++,"merchandiserName","受理员"),
                    new ExcelColumn(i++,"bookingType","业务类型"),
                    new ExcelColumn(i++,"bookingDate","预订日期","date"),
                    new ExcelColumn(i++,"checkinDate","入住日期","date"),
                    new ExcelColumn(i++,"checkoutDate","离店日期","date"),
                    new ExcelColumn(i++,"roomNo","房间数"),
                    new ExcelColumn(i++,"roomNightNo","间夜数"),
                    new ExcelColumn(i++,"totalOrderFee","销售价"),
                    new ExcelColumn(i++,"totalCost","成本"),
                    new ExcelColumn(i++,"totalProfit","利润")
                    );
            return generateHead(columns);
        }

        //酒店业务采购商现付报表 add by min.zhang
                public static ExcelHead getHotelPurchaserSpotpaidDetailReportHead() {
                    int i=0;
                    List<ExcelColumn> columns=Arrays.asList(
                            new ExcelColumn(i++,"orderId","订单号"),
                            new ExcelColumn(i++,"hotelName","酒店名称"),
                            new ExcelColumn(i++,"customerName","客户名称"),
                            new ExcelColumn(i++,"creatorName","建单员"),
                            new ExcelColumn(i++,"merchandiserName","受理员"),
                            new ExcelColumn(i++,"bookingType","业务类型"),
                            new ExcelColumn(i++,"bookingDate","预订日期","date"),
                            new ExcelColumn(i++,"checkinDate","入住日期","date"),
                            new ExcelColumn(i++,"checkoutDate","离店日期","date"),
                            new ExcelColumn(i++,"roomNo","房间数"),
                            new ExcelColumn(i++,"roomNightNo","间夜数"),
                            new ExcelColumn(i++,"orderNo","订单数"),
                            new ExcelColumn(i++,"totalOrderFee","销售价"),
                            new ExcelColumn(i++,"totalSupplierCommissionAmount","供应商返佣金额"),
                            new ExcelColumn(i++,"totalCustomerCommissionAmount","客户返佣金额"),
                            new ExcelColumn(i++,"totalProfit","利润")
                            );
                    return generateHead(columns);
                }
        /**
         * 通用订单导出Excel头模板
         * @return ExcelHead
         */
        public static ExcelHead getGeneralOrderHead() {
            int i=0;
            List<ExcelColumn> columns=Arrays.asList(
                    new ExcelColumn(i++,"generalOrderId","订单号"),
                    new ExcelColumn(i++,"generalOrderType","订单类型"),
                    new ExcelColumn(i++,"state","状态 "),
                    new ExcelColumn(i++,"customerName","客户名称"),
                    new ExcelColumn(i++,"supplierName","供应商"),
                    new ExcelColumn(i++,"businessType","业务类型"),
                    new ExcelColumn(i++,"productName","产品名称"),
                    new ExcelColumn(i++,"shareProfitState","分润状态"),
                    new ExcelColumn(i++,"associatedOrderIdsDetail","关联订单号"),
                    new ExcelColumn(i++,"productCount","数量"),
                    new ExcelColumn(i++,"facePrice","面价","money"),
                    new ExcelColumn(i++,"costPrice","底价","money"),
                    new ExcelColumn(i++,"customerPayAmount","应收合计","money"),
                    new ExcelColumn(i++,"profitAmount","利润合计","money"),
                    new ExcelColumn(i++,"gatheringType","收款方式"),
                    new ExcelColumn(i++,"gatheringState","收款状态"),
                    new ExcelColumn(i++,"passengerNamesDetail","旅客姓名"),
                    new ExcelColumn(i++,"creatorName"," 建单人"),
                    new ExcelColumn(i++,"createDate","建单日期","date"),
                    new ExcelColumn(i++,"innerMemo","内部备注")
                    );
            return generateHead(columns);
        }

				//酒店业务供应商预付报表 add by min.zhang
				public static ExcelHead getHotelSupplierDetailReportHead() {
					int i=0;
					List<ExcelColumn> columns=Arrays.asList(
							new ExcelColumn(i++,"orderId","订单号"),
							new ExcelColumn(i++,"regionName","区域"),
							new ExcelColumn(i++,"provinceName","省/直辖市"),
							new ExcelColumn(i++,"cityName","城市"),
							new ExcelColumn(i++,"hotelName","酒店名称"),
							new ExcelColumn(i++,"supplierName","供应商"),
							new ExcelColumn(i++,"contractManagerName","签约经理"),
							new ExcelColumn(i++,"bookingType","业务类型"),
							new ExcelColumn(i++,"bookingDate","预订日期","date"),
							new ExcelColumn(i++,"checkinDate","入住日期","date"),
							new ExcelColumn(i++,"checkoutDate","离店日期","date"),
							new ExcelColumn(i++,"roomNo","房间数"),
							new ExcelColumn(i++,"roomNightNo","间夜数"),
							new ExcelColumn(i++,"totalOrderFee","销售价"),
							new ExcelColumn(i++,"totalCost","成本"),
							new ExcelColumn(i++,"totalProfit","利润")
							);
					return generateHead(columns);
				}

				//酒店业务供应商现付报表 add by min.zhang
				public static ExcelHead getHotelSupplierSpotpaidDetailReportHead() {
					int i=0;
					List<ExcelColumn> columns=Arrays.asList(
							new ExcelColumn(i++,"orderId","订单号"),
							new ExcelColumn(i++,"regionName","区域"),
							new ExcelColumn(i++,"provinceName","省/直辖市"),
							new ExcelColumn(i++,"cityName","城市"),
							new ExcelColumn(i++,"hotelName","酒店名称"),
							new ExcelColumn(i++,"supplierName","供应商"),
							new ExcelColumn(i++,"bookingType","业务类型"),
							new ExcelColumn(i++,"bookingDate","预订日期","date"),
							new ExcelColumn(i++,"checkinDate","入住日期","date"),
							new ExcelColumn(i++,"checkoutDate","离店日期","date"),
							new ExcelColumn(i++,"roomNo","房间数"),
							new ExcelColumn(i++,"roomNightNo","间夜数"),
							new ExcelColumn(i++,"totalOrderFee","销售价"),
							new ExcelColumn(i++,"totalSupplierCommissionAmount","供应商返佣金额"),
							new ExcelColumn(i++,"totalCustomerCommissionAmount","客户返佣金额"),
							new ExcelColumn(i++,"totalProfit","利润")
							);
					return generateHead(columns);
				}
}
