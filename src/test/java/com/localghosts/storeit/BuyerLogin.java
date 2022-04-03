package com.localghosts.storeit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.localghosts.storeit.controller.BuyerAuthController;

import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.JwtResponse;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BuyerLogin{
	
	    @Test
	    public void buyerLoginfunc() throws Exception {
		    
		 
		    TestRestTemplate template = new TestRestTemplate();
		    final String baseUrl = "http://localhost:8080/buyer/login";
	        URI uri = new URI(baseUrl);
	        
		    Buyer buyer = new Buyer();
		    
		  
		    buyer.setEmail("antdany1298@gmail.com");
		    buyer.setPassword("123");
		    
		    HttpHeaders headers = new HttpHeaders();     
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Buyer> request = new HttpEntity<Buyer>(buyer, headers);
	        
	        
	        System.out.println(request.getHeaders());
	        System.out.println(request.getClass());
	        System.out.println(request.getBody().getEmail());
	        System.out.println(request.getBody().getPassword());
	       // System.out.println(request.getBody().getName());
	        
	        ResponseEntity<String> result = template.postForEntity(uri,request ,String.class);
	        
	        //Verify request succeed
	        String body = result.getBody();
	        System.out.println("0");
	        System.out.println(body);
	        System.out.println(result.getStatusCodeValue());
	        System.out.println(result.getHeaders());
	        System.out.println(result.getBody());
	        Assert.assertEquals(200, result.getStatusCodeValue());
	    }

}
