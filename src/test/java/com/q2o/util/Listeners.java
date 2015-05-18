package com.q2o.util;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.q2o.base.TestBase;

public class Listeners extends TestListenerAdapter implements IInvokedMethodListener{
	
	public void onTestSuccess(ITestResult tr) {
		
		TestBase.app_log.debug("Passed TC : "+tr.getName());
		System.out.println("Passed TC : "+tr.getName());
	}
	
	public void onTestFailure(ITestResult tr) {
		TestBase.app_log.debug("Failed TC : "+tr.getName());
		System.out.println("Failed TC : "+tr.getName());
	
    }
	
	public void onTestSkipped(ITestResult tr){
		TestBase.app_log.debug("Skipped TC : "+tr.getName());
		System.out.println("Skipped TC : "+tr.getName());
	
		
	}
	public void onFinish(ITestResult tr) {
		TestBase.app_log.debug("Success : "+tr.getName());
		System.out.println("Success : "+tr.getName());
	}
	
	public void onTestStart(ITestResult tr){
		
		TestBase.app_log.debug("Start Executing  : "+tr.getName());
		System.out.println("Start Executing  : "+tr.getName());
	}
	
	public void afterInvocation(IInvokedMethod method,ITestResult result){
		
	}
	
    public void beforeInvocation(IInvokedMethod argO,ITestResult result){
		
	}

}
