package Ratul.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedtestcase implements IRetryAnalyzer{

	
	int count = 0;
	int maxRun =1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		//When any test failed first time, this method will execute.
		//if this method return true then it will run again. so below is the logic to run failed testcase 1 more time.
		if(count<maxRun) {
			count++;
			return true;
		}
		
		return false;
	}
	
	

}
