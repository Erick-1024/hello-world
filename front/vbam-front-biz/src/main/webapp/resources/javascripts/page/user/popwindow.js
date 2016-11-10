var editPwd;
var setPayPwd;
var editPayPwd;
function initPopWindow(){
	$(".leftMenu ul li").click(function(){
        if(!$(this).hasClass("leftMenu-current")){
            $(this).addClass("leftMenu-current").siblings().removeClass("leftMenu-current");
            showCnt($(this).index());
        }
    });

    function showCnt(index){
        $(".userCenter-wrap .userCenter-content").eq(index).removeClass("hidden").siblings().addClass("hidden");
    }

    //修改密码弹窗
    editPwd= new PopWindow(".editPwd", {
        title: "修改登录密码",
        width: 420,
        reload: true,
        template: "#template-editPwd"
    }).init();

    //设置支付密码
   setPayPwd= new PopWindow(".payPwd", {
        title: "设置支付密码",
        width: 420,
        reload: true,
        template: "#template-payPwd"
    }).init();

    //修改支付密码
   editPayPwd= new PopWindow(".editPayPwd", {
        title: "修改支付密码",
        width: 420,
        reload: true,
        template: "#template-editPayPwd"
    }).init();

    //组织机构代码证
    new PopWindow(".upState-link", {
        title: "更新组织机构代码证",
        width: 680,
        reload: true,
        template: "#template-updateState"
    }).init();

    //营业执照
    new PopWindow(".upLicense-link", {
        title: "更新营业执照",
        width: 680,
        reload: true,
        template: "#template-updateLicense"
    }).init();

    //税务登记证
    new PopWindow(".updateTax-link", {
        title: "更新税务登记证",
        width: 680,
        reload: true,
        template: "#template-updateTax"
    }).init();

    //修改姓名
    new PopWindow(".editName-link", {
        title: "姓名",
        width: 600,
        reload: true,
        template: "#template-editName"
    }).init();
    
    //修改职位
    new PopWindow(".editJob-link", {
        title: "职位",
        width: 600,
        reload: true,
        template: "#template-editJob"
    }).init();

    //修改手机号码
    new PopWindow(".editCellphone-link", {
        title: "手机号码",
        width: 600,
        reload: true,
        template: "#template-editCellhpone"
    }).init();

    //修改邮箱
    new PopWindow(".editEmail-link", {
        title: "电子邮箱",
        width: 600,
        reload: true,
        template: "#template-editEmail"
    }).init();
}



