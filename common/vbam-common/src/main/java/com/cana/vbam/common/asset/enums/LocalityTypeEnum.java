package com.cana.vbam.common.asset.enums;


/**
 * 所属地区枚举类
 * @author jiangzhou.Ren
 * @time 2016年7月27日上午10:06:41
 */
public enum LocalityTypeEnum {
	
	SHANGHAI("上海"), 
	BEIJING("北京"), 
	TIANJIN("天津");
	
	private String desc;

	LocalityTypeEnum(String desc) {
		this.desc = desc;
	}

	public String Desc() {
		return desc;
	}
	
	
}


/**
 * <option value="北京市">北京市</option>
                                <option value="上海市">上海市</option>
								<option value="天津市">天津市</option>
                                <option value="广东省">广东省</option>
								<option value="河北省">河北省</option>
								<option value="山西省">山西省</option>
								<option value="内蒙古自治区">内蒙古自治区</option>
								<option value="辽宁省">辽宁省</option>
								<option value="吉林省">吉林省</option>
								<option value="黑龙江省">黑龙江省</option>
								<option value="江苏省">江苏省</option>
								<option value="浙江省">浙江省</option>
								<option value="安徽省">安徽省</option>
								<option value="福建省">福建省</option>
								<option value="江西省">江西省</option>
								<option value="山东省">山东省</option>
								<option value="河南省">河南省</option>
								<option value="湖北省">湖北省</option>
								<option value="湖南省">湖南省</option>
								<option value="广西壮族自治区">广西壮族自治区</option>
								<option value="海南省">海南省</option>
								<option value="四川省">四川省</option>
								<option value="重庆市">重庆市</option>
								<option value="贵州省">贵州省</option>
								<option value="云南省">云南省</option>
								<option value="西藏自治区">西藏自治区</option>
								<option value="陕西省">陕西省</option>
								<option value="甘肃省">甘肃省</option>
								<option value="青海省">青海省</option>
								<option value="宁夏回族自治区">宁夏回族自治区</option>
								<option value="新疆维吾尔族自治区">新疆维吾尔族自治区</option>
								<option value="台湾">台湾</option>
								<option value="香港">香港</option>
								<option value="澳门">澳门</option>

 * 
 */

