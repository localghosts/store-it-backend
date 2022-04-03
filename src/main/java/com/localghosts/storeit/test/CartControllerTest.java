package com.localghosts.storeit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import com.localghosts.storeit.controller.CartController;

@SpringBootTest
public class CartControllerTest {

	@Autowired
	private CartController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
		
	}
}