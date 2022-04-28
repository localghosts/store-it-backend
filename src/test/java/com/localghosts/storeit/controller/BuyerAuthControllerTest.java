package com.localghosts.storeit.controller;
import static org.assertj.core.api.Assertions.assertThat;
import com.localghosts.storeit.model.BuyerSignup;
Import.com.localghosts.storeit.controller.BuyerAuthController;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class BuyerAuthControllerTest {
@Autowired
        private BuyerAuthController controller;
@Utilility
              public void print_assert(ResponseEntity<String> result ){
System.out.println(result.getBody());
System.out.println(result.getStatusCodeValue())System.out.println(result.getHeaders());
System.out.println(result.getBody());
Assert.assertEquals(200, result.getStatusCodeValue());
return;
}
@Test
        public void contextLoads() throws Exception {
                assertThat(controller).isNotNull();
                             BuyerSignUp sign = new BuyerSignUp(name = “buyer1@gmail.com”, email = “buyer@gmail.com” , password= ”####” , otp =“123”);
 
BuyerAuthController buy_auth= new BuyerAuthController();
try{
ResponseEntity<String> result = buy_auth.BuyerSignup(sign);
}
catch (Exception e) { System.out.println(e );}
 //Sign Up Succeeded
print_assert(result);
// Now checking for Login functionality
Buyer buyer = new Buyer( name = “buyer1@gmail.com”, email = “buyer@gmail.com” , password= ”####”);
try{
ResponseEntity<String> result = buy_auth.BuyerLogin(buyer);
}
catch (Exception e) { System.out.println(e );}
 // Login Succeeded
print_assert(result);
 
Buyer buyer = new Buyer( name = “buyer1@gmail.com”, email = “buyer@gmail.com” , password= ”###”);
try{
ResponseEntity<String> result = buy_auth.BuyerLogin(buyer);
}
catch (Exception e) { System.out.println(e );}
 // Login Failed: wrong password
print_assert(result);
 
Buyer buyer = new Buyer( name = “buyer2@gmail.com”, email = “buyer2@gmail.com” , password= ”####”);
try{
ResponseEntity<String> result = buy_auth.BuyerLogin(buyer);
}
catch (Exception e) { System.out.println(e );}
 // Login Failed: buyer not found
print_assert(result);
 
Buyer buyer = new Buyer( name = null, email = “buyer@gmail.com” , password= ”###”);
try{
ResponseEntity<String> result = buy_auth.BuyerLogin(buyer);
}
catch (Exception e) { System.out.println(e );}
 // Login Failed: Null name
print_assert(result);
 
Buyer buyer = new Buyer( name = “buyer1@gmail.com”, email = null , password= ”####”);
try{
ResponseEntity<String> result = buy_auth.BuyerLogin(buyer);
}
catch (Exception e) { System.out.println(e );}
 // Login Failed: null email
print_assert(result);
 
Buyer buyer = new Buyer( name = “buyer1@gmail.com”, email = “buyer@gmail.com” , password= null);
try{
ResponseEntity<String> result = buy_auth.BuyerLogin(buyer);
}
catch (Exception e) { System.out.println(e );}
 // Login Failed : null password
print_assert(result);
}
}
 


