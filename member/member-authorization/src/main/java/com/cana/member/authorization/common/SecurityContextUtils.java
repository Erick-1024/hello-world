package com.cana.member.authorization.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cana.member.authorization.model.UserDetailsDTO;
import com.cana.vbam.common.member.dto.permission.PermissionDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;

/**
 *
 * @since Nov 7, 201511:52:47 AM
 * @author dev1
 *
 */
public class SecurityContextUtils {

    public static final String ANONYMOUT_USER = "anonymousUser";
    public static final String DENIED_MESSAGE = "DENIED_MESSAGE";
    public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    private static Set<PermissionDTO> allPermissions;
    public static int permVersion = 0;

    /** url -> permission */
    private static Map<String, PermissionDTO> urlPerms = new HashMap<>();

    /** key -> permission */
    private static Map<String, PermissionDTO> keyPerms = new HashMap<>();

    public static UserDetailsDTO getUserFromSession() {
    	SecurityContext secCxt = SecurityContextHolder.getContext();
        if (secCxt == null) {
            return null;
        }
        if (secCxt.getAuthentication() == null) {
            return null;
        }
        Object principal = secCxt.getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsDTO) {
            return (UserDetailsDTO) principal;
        } else {
            return null;
        }
    }
    
    public static UserSessionDTO getUserDTOFromSession(){
    	UserDetailsDTO userDetail = getUserFromSession();
    	if(null == userDetail){
    		return null;
    	}
    	return userDetail.getUserData();
    }
    
    public static String getOperatorId(){
    	UserDetailsDTO userDetail = getUserFromSession();
    	UserSessionDTO userDTO= userDetail.getUserData();
    	return userDTO.getId();
    }
    
    public static String getCustomerId(){
    	UserDetailsDTO userDetail = getUserFromSession();
    	if(null == userDetail){
    		return null;
    	}
    	
    	UserSessionDTO userDTO= userDetail.getUserData();
    	String userId =  userDTO.getId();
    	if(StringUtils.isNotBlank(userDTO.getMasterId())){
    		userId = userDTO.getMasterId();
    	}
    	return userId;
    }

    public static void saveUserDetailsToSession() {
    	HttpServletRequest request = MemberAuthUtils.getRequest();
    	SecurityContext context = SecurityContextHolder.getContext();
    	request.getSession().setAttribute(SPRING_SECURITY_CONTEXT, context);
    	request.getSession().setAttribute("__changed__", "");
    }

	public static Set<PermissionDTO> getAllPermissions() {
		return allPermissions;
	}

	public synchronized static void setAllPermissions(List<PermissionDTO> all) {
		if (CollectionUtils.isEmpty(all)) {
			return;
		}
		allPermissions = new HashSet<>(all);
//		permVersion = MemberMiscUtils.getPermVersion(all);
		for (PermissionDTO pm : allPermissions) {
			keyPerms.put(pm.getId(), pm);
			if (StringUtils.isNotBlank(pm.getUrl())) {
				urlPerms.put(pm.getUrl(), pm);
			}
		}
	}

	public static void addPermission(PermissionDTO pm) {
		keyPerms.put(pm.getId(), pm);
		if (StringUtils.isNotBlank(pm.getUrl())) {
			urlPerms.put(pm.getUrl(), pm);
		}
	}

	public static List<PermissionDTO> getPermissionByUrl(String url) {
		List<PermissionDTO> permList = new ArrayList<>();
		for(PermissionDTO perm : allPermissions){
			if(url.equals(perm.getUrl())){
				permList.add(perm);
			} 
		}
		return permList;
	}

	public static PermissionDTO getPermissionByKey(String key) {
		return keyPerms.get(key);
	}

	/**
	 * 判断当前用户对指定URL是否有访问权限
	 */
	public static boolean authorizeUrl(String url) {
		if (StringUtils.isBlank(url)) {
			return true;
		}
		List<PermissionDTO> permList = getPermissionByUrl(url);
		if (CollectionUtils.isEmpty(permList)) {
			return true;
		}
		UserDetailsDTO tud = getUserFromSession();
		if (tud == null || tud.getPermissions() == null) {
			return false;
		}
		for(PermissionDTO perm : permList){
			if(tud.getPermissions().contains(perm.getId())){
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断当前用户是否拥有指定权限
	 */
	public static boolean authorizePermKey(String permKey) {
		if (StringUtils.isBlank(permKey)) {
			return true;
		}
		UserDetailsDTO tud = getUserFromSession();
		if (tud == null || tud.getPermissions() == null) {
			return false;
		}
		return tud.getPermissions().contains(permKey);
	}

	public static Set<PermissionDTO> getUserPermission(){
		Set<PermissionDTO> permissions = new TreeSet<>();
		UserDetailsDTO tud = getUserFromSession();
		if (tud == null || tud.getPermissions() == null) {
			return permissions;
		}
		for(String key : tud.getPermissions()){
			if(StringUtils.isNotBlank(key))
				if(null != keyPerms.get(key))
					permissions.add(keyPerms.get(key));
		}
		
		return permissions;
	}
}
