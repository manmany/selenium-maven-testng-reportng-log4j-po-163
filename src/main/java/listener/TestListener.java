package listener;

import Utils.logging;

public class TestListener extends logging {
	/**
	 * 测试方法
	 */
	public static void startTest(String testCaseName) {
		info("******************************************************************************************************************");
		info("$$$$$$$$$$$$$$$$$$$$$$$$ 开始测试 " + testCaseName + " $$$$$$$$$$$$$$$$$$$$$$$$");
		info("******************************************************************************************************************");
	}
	
	public static void endTest(String testCaseName) {
		info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		info("$$$$$$$$$$$$$$$$$$$$$$$$    结 束 测 试    $$$$$$$$$$$$$$$$$$$$$$$$");
		info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	public static String getTestCaseName(Class class1) {
		return class1.getName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
}
