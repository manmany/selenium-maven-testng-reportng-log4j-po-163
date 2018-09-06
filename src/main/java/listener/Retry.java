package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import Utils.logging;

public class Retry implements IRetryAnalyzer {
	private int retryCount = 0;
	public boolean retry(ITestResult result) {
		int maxRetryCount = 1;
		if(retryCount < maxRetryCount) {
			retryCount ++;
			logging.info("Retry #" + retryCount + "for test" + result.getMethod().getMethodName() +", on thread: " + Thread.currentThread().getName());
			return true;
		}
		return false;
	}

}
