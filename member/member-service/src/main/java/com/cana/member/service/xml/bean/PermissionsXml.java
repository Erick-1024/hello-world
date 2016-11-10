package com.cana.member.service.xml.bean;

import java.util.List;

import com.cana.vbam.common.enums.PermissionType;
import com.cana.vbam.common.enums.Platform;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 权限定义
 * @since 2015-11-3 上午11:41:58
 * @author zhiwen.hu
 *
 */
@XStreamAlias("permissions")
public class PermissionsXml {

	@XStreamAsAttribute private Platform platform;
	@XStreamAsAttribute private int version;
	@XStreamImplicit private List<Module> modules;

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	@XStreamAlias("module")
	public static class Module extends AbstractPerm {
		@XStreamImplicit private List<Module> modules;
		@XStreamImplicit private List<Button> buttons;

		@Override
		public String toString() {
			return "Module [name=" + this.getName() + ", key=" + this.getKey() + ", url=" + this.getUrl() + "]";
		}

		public List<Module> getModules() {
			return modules;
		}

		public void setModules(List<Module> modules) {
			this.modules = modules;
		}

		public List<Button> getButtons() {
			return buttons;
		}

		public void setButtons(List<Button> buttons) {
			this.buttons = buttons;
		}
		
		
	}

	@XStreamAlias("button")
	public static class Button extends AbstractPerm {
		@XStreamImplicit private List<Perm> perms;
		@XStreamImplicit private List<Button> buttons;
		
		@Override
		public String toString() {
			return "Button [name=" + this.getName() + ", key=" + this.getKey() + ", url=" + this.getUrl() + "]";
		}

		public List<Perm> getPerms() {
			return perms;
		}

		public void setPerms(List<Perm> perms) {
			this.perms = perms;
		}

		public List<Button> getButtons() {
			return buttons;
		}
		
		public void setButtons(List<Button> buttons) {
			this.buttons = buttons;
		}
	}

	@XStreamAlias("perm")
	public static class Perm extends AbstractPerm{
		
	}
	public static abstract class AbstractPerm {
		@XStreamAsAttribute private String name;
		@XStreamAsAttribute private String key;
		@XStreamAsAttribute private String url;
		
		private String parent;
		
		private PermissionType type;
		
		private int order;

		@Override
		public String toString() {
			return "Perm [name=" + name + ", key=" + key + ", url=" + url + "]";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getParent() {
			return parent;
		}

		public void setParent(String parent) {
			this.parent = parent;
		}

		public PermissionType getType() {
			return type;
		}

		public void setType(PermissionType type) {
			this.type = type;
		}
		
		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

	}

}
