package com.localghosts.storeit.controller;
import static org.assertj.core.api.Assertions.assertThat;
import com.localghosts.storeit.model.OTP;
Import.com.localghosts.storeit.controller.OTP Controller;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class OTPControllerTest {
@Autowired
        private OTPController controller;
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
                             OTP otp_test = new OTP(email= “deepank.kansal@gmail.com”, otp =“123”, used = False);

OTPController otp_auth = new OTPController();
// Message Received
try{
otp_auth.getOTP(otp_test);
}
catch (Exception e) { System.out.println(e );}

try{
otp_auth.getOTP(otp_test);
}
catch (Exception e) { System.out.println(e );}
// OTP already sent error


OTP otp_test = new OTP(email= null, otp =“123”, used = False);

try{
otp_auth.getOTP(otp_test);
}
catch (Exception e) { System.out.println(e );}

// Null check for email 
}
}
 


