package com.localghosts.storeit.controller;
import static org.assertj.core.api.Assertions.assertThat;
import com.localghosts.storeit.model.Category;
Import.com.localghosts.storeit.controller.CategoryController;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class CategoryControllerTest {
@Autowired
        private CategoryController controller;
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
                             Category test = new Category(name=“New”, image=“image”, description=“desc”);
CategoryController auth = new CategoryController();

try{
auth.getCategory(test,”store1”);
}
catch (Exception e) { System.out.println(e );}
//Store added

try{
auth.deleteCategory(test,”store1”);
}
catch (Exception e) { System.out.println(e );}
//Store deleted

}
}
