package com.travelzen.framework.config.tops.util;


/**
 * 
 *  use TopsAppRegistry  instead
 */
@Deprecated
public class TopsConfigReaderUtil {

	/**
	 * 需要系统管理员在hosts文件中指定的hostname名称
	 */
//	@SuppressWarnings("unused")
//	private static final String HOSTNAME_MACHINE = "zenlv-machine-ip";
//	private static String applicationName = getApplicationName();

	public static void setApplicationName(String name) {
		TopsAppRegistry.setApplicationName( name);
//		applicationName = name;
	}

	/**
	 * 获得tops系统的工程名字 譬如: /home/jiangningcui/workspace/tz/tz-common/framework
	 * 需要截取最后一个名字. 1) 需要特别注意.在不同的工程里执行的名字不同,特别是工程继承的时候,需要特别注意使用此方法
	 * 譬如appName继承于appName1,appName1继承appName2
	 * 同一个方法里,在appName2,appName1,appName里分别执行,得出的结果分别是appName2,
	 * appName1,appName.
	 * 
	 * 还得注意: 如果是tomcat应用里面的话,可能会有不同哦
	 * 
	 */
	public static String getApplicationName() {
		return TopsAppRegistry.getApplicationName();
//		if (StringUtils.isNotBlank(applicationName)) {
//			return applicationName;
//		}
//
//		String projectName = getProjectName();
//		if (TZUtil.isEmpty(projectName)) {
//			throw new RuntimeException();
//		}
//		if (projectName.lastIndexOf(File.separator) != -1)
//			projectName = projectName.substring(projectName.lastIndexOf(File.separator) + 1);
//		return projectName;
	}

	/**
	 * 获取应用所在的user.dir
	 * 
	 * @return user.dir
	 */
//	private static String getProjectName() {
//
//		ClassLoader classLoader = TopsConfigReaderUtil.class.getClassLoader();
//		String projectName = null;
//		if (classLoader != null && classLoader.getResource("") != null) {
//			projectName = classLoader.getResource("").toString();
//		} else {
//			projectName = TopsConfigReaderUtil.class.getResource("").toString();
//		}
//		System.out.println(projectName);
//		if (projectName.contains(File.separator + "WEB-INF")) {// tomcat应用
//			projectName = projectName.replaceAll(File.separator + "WEB-INF.*", "");
//		} else if (projectName.contains("jar:file:")) {// 非tomcat应用 
//			if (projectName.indexOf("/target") != -1) {
//				projectName = StringUtils.substringBeforeLast(projectName, "/target");
//				projectName = StringUtils.substringAfterLast(projectName, "/");
//			} else {
//				projectName = StringUtils.substringBeforeLast(projectName, "/lib");
//				projectName = StringUtils.substringAfterLast(projectName, "/");
//			}
//		} else {
//			projectName = System.getProperty("user.dir");
//		}
//		System.out.println(projectName);
//		return projectName;
//	}

	/**
	 * 获得主机IP,一般来说,主机会 这个地方需要修改
	 * 
	 * @return
	 */
	public static String getLocalIP() {
		return TopsAppRegistry.getLocalIP();
//		return LocalPropertiesUtil.getPropertyFromLocalFile(TopsConfigPathResolver.getTopsIpPath(), "ip");
	}

	/**
	 * 获得主机名,不同机器如果有相同ip可以使用
	 * 
	 * @return
	 */
	public static String getLocalName() {
		return TopsAppRegistry.getLocalName();
//		return LocalPropertiesUtil.getPropertyFromLocalFile(TopsConfigPathResolver.getTopsIpPath(), "name");
	}

	/**
	 * 获得主机名,不同机器如果有相同ip可以使用
	 * 
	 * @return
	 */
	public static boolean isLocal() {
		return TopsAppRegistry.isLocal() ;
//		String local = LocalPropertiesUtil.getPropertyFromLocalFile(TopsConfigPathResolver.getTopsIpPath(), "local");
//		if (local != null) {
//			if ("TRUE".equalsIgnoreCase(local)) {
//				return true;
//			}
//		}
//		return false;
	}
}
