package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

/**
 * @author jiangzhou.Ren
 * @time 2016年8月2日下午3:14:10
 */
public class CommonAreaCode implements Serializable{
	
	private static final long serialVersionUID = 2879362470066236877L;

		private String areaCode;

	    private String province;

	    private String city;

	    private String address;

		public String getAreaCode() {
			return areaCode;
		}

		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
	    
}
