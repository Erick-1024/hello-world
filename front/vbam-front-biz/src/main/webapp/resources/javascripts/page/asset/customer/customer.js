$(function(){

//初始化数据表格
    $("#shareholder-monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 5,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                 data: { q: "html5" },
                 type : "POST",
                 url: ""
                 }
            }
        },
        //解析远程响应的数据
        schema:{
            model: {
                id: "id",
                fields: {
                    grade: {type: "int"}
                }
            }
        },
        columns: [{
            field: "sequence",
            title: "序号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customer",
            title: "股东类型",
            width: 100,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "grade",
            title: "股东名称",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "risk",
            title: "出资额（万元）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "target",
            title: "出资比例",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "出资方式",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "是否到位",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 5,  //显示页数
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
    $("#executive-monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 5,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                 data: { q: "html5" },
                 type : "POST",
                 url: ""
                 }
            }
        },
        //解析远程响应的数据
        schema:{
            model: {
                id: "id",
                fields: {
                    grade: {type: "int"}
                }
            }
        },
        columns: [{
            field: "sequence",
            title: "序号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customer",
            title: "姓名",
            width: 100,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "grade",
            title: "性别",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "risk",
            title: "身份证号",
            width: 150,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "target",
            title: "职务",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "学历",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "专业",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "主要经历",
            width: 200,
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 5,  //显示页数
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
    $("#purchase-monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 5,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                 data: { q: "html5" },
                 type : "POST",
                 url: ""
                 }
            }
        },
        //解析远程响应的数据
        schema:{
            model: {
                id: "id",
                fields: {
                    grade: {type: "int"}
                }
            }
        },
        columns: [{
            field: "sequence",
            title: "序号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customer",
            title: "供应商名称",
            width: 100,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "grade",
            title: "上年采购量（万元）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "risk",
            title: "占总采购量（%）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "target",
            title: "结算方式",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "应付账款余额（万元）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 5,  //显示页数
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
    $("#sell-monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 5,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                 data: { q: "html5" },
                 type : "POST",
                 url: ""
                 }
            }
        },
        //解析远程响应的数据
        schema:{
            model: {
                id: "id",
                fields: {
                    grade: {type: "int"}
                }
            }
        },
        columns: [{
            field: "sequence",
            title: "序号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customer",
            title: "客户名称",
            width: 150,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "grade",
            title: "上年销售额（万元）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "risk",
            title: "占销售总额（%）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "target",
            title: "合作年限",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "应收账款余额（万元）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 5,  //显示页数
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
    $("#financing-monitorList-grid").kendoGrid({
        selectable: "row",  //设置可选择数据行
        sortable: true,  //列排序
        dataSource:{
            pageSize: 5,
            type: "json", //后台返回的数据类型
            method: "post",
            transport: {
                read: {
                 data: { q: "html5" },
                 type : "POST",
                 url: ""
                 }
            }
        },
        //解析远程响应的数据
        schema:{
            model: {
                id: "id",
                fields: {
                    grade: {type: "int"}
                }
            }
        },
        columns: [{
            field: "sequence",
            title: "序号",
            width: 80,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "customer",
            title: "金融机构名称",
            width: 150,
            attributes:{
                style:"text-align:center"
            }
        },{
            field: "grade",
            title: "金额（万元）",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "risk",
            title: "起始日",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "target",
            title: "到期日",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "担保方式",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        },{
            field: "measure",
            title: "备注",
            width: 100,
            attributes: {
                style: "text-align: center"
            }
        }],
        pageable: {
            pageSizes: false,  //设置每页显示行数
            buttonCount: 5,  //显示页数
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
    
});
