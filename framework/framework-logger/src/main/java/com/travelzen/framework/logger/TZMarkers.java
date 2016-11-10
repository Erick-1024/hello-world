package com.travelzen.framework.logger;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * marker使用：
 * 1. error 对应的marker：p0、p1和p2, 其中p0级别最高；
 * 2. warn ：p3；
 * 3. info ：p4；
 * 4. debug和trace 无需指定marker。
 */

/**
 *  use class in framework-logger-core
 *
 */
@Deprecated
public class TZMarkers {

	/**
	 * Urgent Event（P0）
	 * 1.大部分交易无法正常执行，出现致命错误
	 * 2.关键数据丢失
	 * 3.数据库崩溃
	 * 4.系统性能降到正常10%以下
	 * 5.系统资源可用性降到1%以下
	 * 6.关键性系统配置错误
	 * 7.关键安全性问题
	 */
	public static Marker p0 = MarkerFactory.getMarker("p0");

	/**
	 * High Priority Issue（P1）
	 * 1.小部分交易无法正常执行
	 * 2.非关键数据丢失
	 * 3.数据库性能降到正常10%以下
	 * 4.系统性能降到正常20%以下
	 * 5.系统资源可用性降到5%以下
	 * 6.非关键性系统配置错误
	 * 7.潜在安全性问题
	 */
	public static Marker p1 = MarkerFactory.getMarker("p1");

	/**
	 * Medium Priority Issue(P2)
	 * 1.极小部分交易无法正常执行
	 * 2.功能有错误，但不影响使用
	 * 3.边界条件出错
	 * 4.单条关键数据出错
	 * 5.单次网络通信异常
	 * 6.默认级别
	 */
	public static Marker p2 = MarkerFactory.getMarker("p2");

	/**
	 * Assert(P3)
	 * 1.软设计或者代码实现有问题， 出现异常的逻辑或数据问题
	 * 2. 不可接受，无法继续的函数异常输入参数
	 */
	public static Marker p3 = MarkerFactory.getMarker("p3");

	/**
	 * Validation（P4）
	 * 1. 用户输入错误数据
	 */
	public static Marker p4 = MarkerFactory.getMarker("p4");

}
