package com.localghosts.storeit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import com.localghosts.storeit.controller.BuyerAuthController;

@SpringBootTest
public class BuyerAuthControllerTest {

	@Autowired
	private BuyerAuthController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		
	}
}