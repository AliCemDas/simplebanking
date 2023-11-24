package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

@SpringBootTest
@ContextConfiguration
class DemoApplicationTests {

	@Autowired
	private AccountController accountController;

	@Test
	void contextLoads() {
		Assert.isTrue(accountController != null);
	}

}
