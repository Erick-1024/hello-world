package com.cana.member.authorization.model;

import org.springframework.security.access.ConfigAttribute;

public class SimpleConfigAttribute implements ConfigAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7229734652768004185L;

	private String attribute;

	private String url;

	public SimpleConfigAttribute(String attr) {
		this.attribute = attr;
	}

	public SimpleConfigAttribute(String attr, String url) {
		this(attr);
		this.url = url;
	}

	@Override
	public String getAttribute() {
		return attribute;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Override
	public int hashCode() {
		return 31 * attribute.hashCode() + (url == null ? 0 : url.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof SimpleConfigAttribute)) {
			return false;
		}
		SimpleConfigAttribute target = (SimpleConfigAttribute) obj;
		return ((this.attribute == null ? target.attribute == null : this.attribute.equals(target.attribute)) &&
				(this.url == null ? target.url == null : this.url.equals(target.url)));
	}

	@Override
	public String toString() {
		return "SimpleConfigAttribute [attribute=" + attribute + ", url=" + url + "]";
	}

}
