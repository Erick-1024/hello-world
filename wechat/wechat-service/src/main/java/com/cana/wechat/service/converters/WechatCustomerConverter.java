package com.cana.wechat.service.converters;

import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.wechat.member.user.CustomerWechatDetailDTO;

public class WechatCustomerConverter {

	public static CustomerWechatDetailDTO converterCustomerDetail2WechatDetail(CustomerDetailDTO queryCustomerDetail) {
		CustomerWechatDetailDTO customerWechatDetailDTO = new CustomerWechatDetailDTO();
		customerWechatDetailDTO.setId(queryCustomerDetail.getId());
		customerWechatDetailDTO.setContactMail(queryCustomerDetail.getContactMail());
		customerWechatDetailDTO.setContactName(queryCustomerDetail.getContactName());
		customerWechatDetailDTO.setContactTel(queryCustomerDetail.getContactTel());
		customerWechatDetailDTO.setJobTitle(queryCustomerDetail.getJobTitle());
		return customerWechatDetailDTO;
	}

}
