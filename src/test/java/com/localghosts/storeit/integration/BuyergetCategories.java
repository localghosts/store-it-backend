package com.localghosts.storeit.integration;

import java.net.URI;

import com.localghosts.storeit.model.Buyer;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BuyergetCategories {

	@Test
	public void buerLoginfunc() throws Exception {

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
		System.out.println(request.getBody().getName());

		ResponseEntity<String> result = template.postForEntity(uri, request, String.class);

		// Verify request succeed
		String body = result.getBody();
		System.out.println("0");
		System.out.println(body);
		System.out.println(result.getStatusCodeValue());
		System.out.println(result.getHeaders());
		System.out.println(result.getBody());
		Assert.assertEquals(200, result.getStatusCodeValue());

		JSONObject myobj = new JSONObject(body);
		System.out.println(myobj.getString("token"));
		// System.out.println(myobj.getString("storeSlug"));
		String bearerString = myobj.getString("token");
		String storeslug = "goodfoods";
		// String bearerString = result.getBody().get

		TestRestTemplate templatestore = new TestRestTemplate();
		final String baseUrlstore = "http://localhost:8080/store/" + storeslug + "/category";
		URI uristore = new URI(baseUrlstore);
		HttpHeaders headerstore = new HttpHeaders();
		headerstore.setContentType(MediaType.APPLICATION_JSON);
		headerstore.add("Authorization", bearerString);

		ResponseEntity<String> entity = templatestore.exchange(
				uristore, HttpMethod.GET, new HttpEntity<Object>(headerstore),
				String.class);
		System.out.print(entity.getBody());
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());

	}

}