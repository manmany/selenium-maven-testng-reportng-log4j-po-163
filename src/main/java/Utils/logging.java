package Utils;

public class logging implements ILogger {
	/**
	 * 封装log方法
	 */
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void warn(String message) {
		logger.warn(message);
	}
	public static void error(String message) {
		logger.error(message);
	}
}
