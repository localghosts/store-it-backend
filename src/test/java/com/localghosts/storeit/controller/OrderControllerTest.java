package com.localghosts.storeit.controller;
import static org.assertj.core.api.Assertions.assertThat;
import.com.localghosts.storeit.controller.OrderController;
import org.junit.jupiter.api.Test;
import com.localghosts.storeit.model.Order;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderControllerTest {
@Autowired
        private OrderController controller;
@Utilility
        public void print_assert(ResponseEntity<String> result ){
              System.out.println(result.getBody());
              System.out.println(result.getStatusCodeValue());
	      System.out.println(result.getHeaders());
              System.out.println(result.getBody());
              Assert.assertEquals(200, result.getStatusCodeValue());
              return;
         }
@Test
        public void contextLoads() throws Exception {
                assertThat(controller).isNotNull();
                Category test = new Category(name=“New”, image=“image”, description=“desc”);
                OrderController auth = new OrderController();

                try{
                List<Order> orders = auth.fetchOrders(“store1”,”deepank.kansal@gmail.com”)
                }
                catch (Exception e) { System.out.println(e );}
                //Order List

                try{
                auth.updateStatus(“3”, “store1”);
                }
                catch (Exception e) { System.out.println(e );}
                //Store order updates

}
}
