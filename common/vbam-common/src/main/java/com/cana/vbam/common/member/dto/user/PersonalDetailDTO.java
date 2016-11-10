package com.cana.vbam.common.member.dto.user;

import java.io.Serializable;
import java.util.List;

public class PersonalDetailDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	    *主键
	    */
	    private String id;

	    /**
	    *主账号Id
	    */
	    private String masterId;

	    /**
	    *用户名
	    */
	    private String username;

	    
	    
	    /**
	     *员工工号
	     */
	     private String jobNo;

	     /**
	     *员工姓名
	     */
	     private String realName;
	     
	     /**
	      *员工电话
	      */
	      private String employeeTel;

	      /**
	      *员工邮件
	      */
	      private String employeeMail;

	      /**
	      *员工职称
	      */
	      private String employeeJobTitle;
	      
	      /**
	       * 角色列表
	       */
	      private List<RoleDTO> roleDTOList;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getMasterId() {
			return masterId;
		}

		public void setMasterId(String masterId) {
			this.masterId = masterId;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getJobNo() {
			return jobNo;
		}

		public void setJobNo(String jobNo) {
			this.jobNo = jobNo;
		}

		public String getRealName() {
			return realName;
		}

		public void setRealName(String realName) {
			this.realName = realName;
		}

		public String getEmployeeTel() {
			return employeeTel;
		}

		public void setEmployeeTel(String employeeTel) {
			this.employeeTel = employeeTel;
		}

		public String getEmployeeMail() {
			return employeeMail;
		}

		public void setEmployeeMail(String employeeMail) {
			this.employeeMail = employeeMail;
		}

		public String getEmployeeJobTitle() {
			return employeeJobTitle;
		}

		public void setEmployeeJobTitle(String employeeJobTitle) {
			this.employeeJobTitle = employeeJobTitle;
		}

		public List<RoleDTO> getRoleDTOList() {
			return roleDTOList;
		}

		public void setRoleDTOList(List<RoleDTO> roleDTOList) {
			this.roleDTOList = roleDTOList;
		}
}
