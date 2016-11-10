package com.travelzen.framework.config.tops;

public class TopsConfEnum {
	public enum ConfScope {
		G("G", "app global"), M("M", "machine"), GA("GA", "global  appid"), MA("MA", "machine appid"), U("U", "unique"), R("R", "global");

		private String scopeType;
		private String scopeName;

		ConfScope(String scopeType, String scopeName) {
			this.scopeType = scopeType;
			this.scopeName = scopeName;
		}

		public String getScopeType() {
			return scopeType;
		}

		public void setScopeType(String scopeType) {
			this.scopeType = scopeType;
		}

		public String getScopeName() {
			return scopeName;
		}

		public void setScopeName(String scopeName) {
			this.scopeName = scopeName;
		}
	}

	public enum ConfLocation {
		ZK("zookeeper"), //
		LOCALHOST("location"), //
		INNERAPP("innerAPP"), //
		CACHE("cache")//
		;
		private String loation;

		ConfLocation(String location) {
			this.loation = location;
		}

		public String getLoation() {
			return loation;
		}

		public void setLoation(String loation) {
			this.loation = loation;
		}

	}

	public enum ConfSeqType {
		IP("ip"), APP("app");
		private String type;

		ConfSeqType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}
}