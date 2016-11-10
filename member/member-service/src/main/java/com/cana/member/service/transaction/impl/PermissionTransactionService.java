package com.cana.member.service.transaction.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.member.dao.mapper.MemberTableLockMapper;
import com.cana.member.dao.mapper.gen.PermissionMapper;
import com.cana.member.dao.mapper.gen.RoleMapper;
import com.cana.member.dao.po.Permission;
import com.cana.member.dao.po.PermissionExample;
import com.cana.member.dao.po.Role;
import com.cana.member.service.transaction.IPermissionTransactionService;
import com.cana.member.service.xml.bean.PermissionsXml;
import com.cana.member.service.xml.bean.PermissionsXml.AbstractPerm;
import com.cana.member.service.xml.bean.PermissionsXml.Button;
import com.cana.member.service.xml.bean.PermissionsXml.Module;
import com.cana.member.service.xml.bean.PermissionsXml.Perm;
import com.cana.vbam.common.enums.PermissionType;
import com.cana.vbam.common.enums.Platform;
import com.cana.vbam.common.enums.RoleType;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.utils.Constants;
import com.thoughtworks.xstream.XStream;

/**
 *
 * @since Nov 6, 20155:31:05 PM
 * @author dev1
 *
 */
@Service(value = "permissionTransactionService")
public class PermissionTransactionService implements IPermissionTransactionService{

	private int order = -1;

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionTransactionService.class);
	
	private final String PERMISSION_VERSION = "permisson_version";

	@Resource
	private PermissionMapper permissionMapper;
	
	@Resource
	private RoleMapper roleMapper;

	@Resource
	private PropertiesMapper propertiesMapper;
	
	@Resource
	private MemberTableLockMapper tableLockMapper;
	
	@Override
	public void loadPermissionDefinitions() {
		XStream xs = new XStream();
		xs.processAnnotations(PermissionsXml.class);
		xs.autodetectAnnotations(true);

		PermissionsXml pxOperator = (PermissionsXml) xs.fromXML(this.getClass().getResourceAsStream("/data/perm-define-vbam-biz.xml"));

		//get globalVersion first, if xml files do not change, return immediately
		int globalVersion = pxOperator.getVersion();
		LOGGER.info("global version of permission definitions is {}", globalVersion);
		System.out.println("version:"+globalVersion);
		System.out.println(pxOperator.getModules().toString());
		
		Properties property = propertiesMapper.selectByPrimaryKey(PERMISSION_VERSION);
		if (property == null) {
			LOGGER.info("no version info of permissions found, create a new one and load permission to database.");
			property = new Properties();
			property.setName(PERMISSION_VERSION);
			propertiesMapper.insertSelective(property);
		} else if (property.getValue() == null || "".equals(property.getValue())) {
			LOGGER.info("no permission version recorded, do load permission to database.");
		} else if (Integer.parseInt(property.getValue()) < globalVersion) {
			LOGGER.info("newer permission version detected, do load permission to database.");
		} else {
			LOGGER.info("no need to reload permission definitions.");
			return;
		}

//		if (metaDao.getModifying()) {
//			LOGGER.info("someone is loading permissions to database.");
//			return;
//		}

//		process xml files
		order  = -1;

		this.postDeserialize(pxOperator);
		LOGGER.info("verify operator permissions({}) ...", pxOperator.getVersion());
		if (!PermDefinesAnalyst.verify(pxOperator)) {
			LOGGER.error("operator permission definitions invalid!");
			return;
		}
		List<Permission> operatorPerms = new ArrayList<>(PermDefinesAnalyst.permissions);
		
//		update permission
		PermissionExample perExample = new PermissionExample();
		permissionMapper.deleteByExample(perExample);
		for(Permission permission : operatorPerms){
			permissionMapper.insert(permission);
		}
		
		LOGGER.info("update permission version info...");

		property.setValue(String.valueOf(globalVersion));
		propertiesMapper.updateByPrimaryKeySelective(property);
		LOGGER.info("permissions updated successfully.");
		updateCANAPerssions();
		LOGGER.info("CANA's role permissions updated successfully.");
	}

	private void postDeserialize(PermissionsXml permissionXml){
		Iterator<Module> it = permissionXml.getModules().iterator();
		this.post4Module(it, null);
	}

	private void post4Module(Iterator<Module> it, Module parent){
		while(it.hasNext()){
			order++;
			Module module = it.next();
			module.setParent(parent == null ? "" : parent.getKey());
			module.setType(PermissionType.MODULE);
			module.setOrder(order);
			List<Module> module_list = module.getModules();
			List<Button> button_list = module.getButtons();
			if(!CollectionUtils.isEmpty(module_list)){
				this.post4Module(module_list.iterator(), module);
			}
			if(!CollectionUtils.isEmpty(button_list)){
				this.post4Button(button_list.iterator(),  module);
			}
		}
	}

	private <T extends AbstractPerm> void post4Button(Iterator<Button> it, T parent){
		while(it.hasNext()){
			order++;
			Button button = it.next();
			button.setParent(parent.getKey());
			button.setType(PermissionType.BUTTON);
			button.setOrder(order);
			List<Button> button_list = button.getButtons();
			List<Perm> perm_list = button.getPerms();
			if(!CollectionUtils.isEmpty(button_list)){
				this.post4Button(button_list.iterator(),  button);
			}
			if(!CollectionUtils.isEmpty(perm_list)){
				this.post4Perm(perm_list.iterator(), button);
			}
		}
	}

	private void post4Perm(Iterator<Perm> it, Button parent){
		while(it.hasNext()){
			order++;
			Perm perm = it.next();
			perm.setParent(parent.getKey());
			perm.setType(PermissionType.PERM);
			perm.setOrder(order);
		}
	}

	static class PermDefinesAnalyst {

		static Set<String> urls = new HashSet<>();
		static Set<String> keys = new HashSet<>();
		static List<Permission> permissions = new LinkedList<>();
		private static boolean passed;
		static Platform curPlatform;

		public static void reset() {
			urls.clear();
			keys.clear();
			permissions.clear();
		}

		public static boolean verify(PermissionsXml px) {
			reset();
			curPlatform = px.getPlatform();
			passed = true;
			for (Module mdl : px.getModules()) {
				traverseModule(mdl);
			}
			return passed;
		}

		private static void traverseModule(Module mdl) {
			if (StringUtils.isBlank(mdl.getKey()) || StringUtils.isBlank(mdl.getName())) {
				LOGGER.error("invalid module:\t{}", mdl);
				passed = false;
//			} else if ((!CollectionUtils.isEmpty(mdl.getModules()) && !CollectionUtils.isEmpty(mdl.getButtons()))) {
//				LOGGER.error("module cannot have both sub modules and buttons:\t{}", mdl);
//				passed = false;
			} else {
				addKey(mdl.getKey());
			}
			if (StringUtils.isNotBlank(mdl.getUrl())) {
				addUrl(mdl.getUrl());
			}
			addPermission(mdl.getKey(), mdl.getUrl(), mdl.getParent(), mdl.getType(), mdl.getName(), mdl.getOrder());
			if (!CollectionUtils.isEmpty(mdl.getModules())) {
				for (Module m : mdl.getModules()) {
					traverseModule(m);
				}
			}
			if (!CollectionUtils.isEmpty(mdl.getButtons())) {
				for (Button btn : mdl.getButtons()) {
					traverseButton(btn);
				}
			}
		}

		private static void traverseButton(Button btn) {
//			button must have a key
			if (StringUtils.isBlank(btn.getKey())) {
				passed = false;
				LOGGER.error("invalid button:\t{}", btn);
			}
//			if button has perms, it must not have url property, and now its key property is fake, does not show in web page
//			if(!CollectionUtils.isEmpty(btn.getPerms()) && StringUtils.isNotBlank(btn.getUrl())){
//				passed = false;
//				LOGGER.error("invalid button:\t{}", btn);
//			}
			addKey(btn.getKey());
			addUrl(btn.getUrl());
			addPermission(btn.getKey(), btn.getUrl(), btn.getParent(), btn.getType(), btn.getName(), btn.getOrder());
			if (!CollectionUtils.isEmpty(btn.getButtons())) {
				for (Button subbtn : btn.getButtons()) {
					traverseButton(subbtn);
				}
			}
			if (!CollectionUtils.isEmpty(btn.getPerms())) {
				for (Perm p : btn.getPerms()) {
					addKey(p.getKey());
					addUrl(p.getUrl());
					addPermission(p.getKey(), p.getUrl(), p.getParent(), p.getType(), p.getName(), p.getOrder());
				}
			}
		}

		private static void addKey(String key) {
			if (StringUtils.isBlank(key)) {
				return;
			}
			if (keys.contains(key)) {
				passed = false;
				LOGGER.error("KEY:\t\"{}\" duplicated", key);
			} else {
				keys.add(key);
			}
		}

		private static void addUrl(String url) {
			if (StringUtils.isBlank(url)) {
				return;
			}
			urls.add(url);
//			if (urls.contains(url)) {
//				passed = false;
//				LOGGER.error("URL:\t\"{}\" duplicated", url);
//			} else {
//				urls.add(url);
//			}
		}

		private static void addPermission(String key, String url, String parent, PermissionType type, String text, int order) {
			if (StringUtils.isBlank(key)) {
				return;
			}
			url = url != null ? url.trim() : null;
			if (url != null && !"N/A".equals(url)) {
				if (url.length() > 0) {
					url = url.charAt(0) == '/' ? url : '/' + url;
				}
			} else {
				url = "";
			}
			Permission p = new Permission();
			
			p.setId(key);
			p.setUrl(url);
			p.setPlatform(curPlatform.toString());
			p.setParentId(parent);
			p.setType(type.toString());
			p.setName(text);
//			p.setDescription(description);;
			p.setOrd(order);
			p.setCreateTime(new Date());
			permissions.add(p);
		}

	}

	@Override
	public List<Permission> getPermissionByRole(String roleId) throws Exception {
		LOGGER.info("get permissions by role.");
		Role role = roleMapper.selectByPrimaryKey(roleId);
		if(null == role){
			LOGGER.error("the role is not find.");
			return null;
		}
		String permissions = role.getPermissions();
		String[] permissionArr = permissions.split(";");
		
		PermissionExample permissionExample = new PermissionExample();
		permissionExample.createCriteria().andIdIn(Arrays.asList(permissionArr));
		LOGGER.info("example日志"+permissionExample.toString());
		List<Permission> permissions2 = permissionMapper.selectByExample(permissionExample);
		return permissions2;
//		return permissionMapper.selectByExample(permissionExample);
	}

	/**
	 * 更新cana角色的权限
	 * @throws Exception
	 */
	private void updateCANAPerssions(){
		List<Permission> permissions = permissionMapper.selectByExample(new PermissionExample());
		String keys = "";
		for (Permission permission : permissions) {
			keys += permission.getId() + ";";
		}
		Role cana = tableLockMapper.lockMemberRoleById(Constants.CANA_ROLE_ID);
		if(null == cana){
			LOGGER.info("no record of CANA's role found, create a new one and load CANA's role to database.");
			cana = new Role();
			cana.setId(Constants.CANA_ROLE_ID);
			cana.setRoleName(Constants.CANA_ROLE_NAME);
			cana.setDescription(Constants.CANA_ROLE_DESC);
			cana.setType(RoleType.LEVEL1.name());
			cana.setPermissions(keys);
			cana.setOrd(0);
			cana.setCreatetime(new Date());
			cana.setRoleType(UserType.CANA.name());
			roleMapper.insertSelective(cana);
		}else {
			cana.setPermissions(keys);
			cana.setUpdatetime(new Date());
			roleMapper.updateByPrimaryKeySelective(cana);
		}
	}
}
