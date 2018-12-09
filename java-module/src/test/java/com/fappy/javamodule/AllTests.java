package com.fappy.javamodule;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.fappy.javamodule.repository.FamilySpaceRepositoryTest;
import com.fappy.javamodule.repository.UserRepositoryTest;

@RunWith(Suite.class)
@SuiteClasses({ 
		JavaModuleApplicationTests.class,
		UserRepositoryTest.class,
		FamilySpaceRepositoryTest.class
	})
public class AllTests {

}
