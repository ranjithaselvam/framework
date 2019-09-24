package com.atmecs.project.script;

import org.testng.annotations.Test;

import com.atmecs.project.testbase.Browsers;

public class Script extends Browsers  {
	
	@Test
	public void test()
	{
		int a=5,b=10;
		System.out.println(a+b);
	}

}
