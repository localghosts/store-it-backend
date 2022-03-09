package com.localghosts.storeit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
//import com.localghosts.storeit.dao.ProductRepo;
import com.localghosts.storeit.model.Product;
import com.localghosts.storeit.config.ProductRepo;


@RestController
public class ProductController {
	
	@Autowired
	ProductRepo repo;
	
	//have to add feature such that it can only be accessed by seller
	// even not sure if we should implement these at all 
	// as the requests are to be performed on the store table
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product product)
	{
		Product prod = new Product(product.getName(), product.getPrice() ,product.getCategoryID() ,product.isInstock());
		repo.save(prod);
		System.out.println(prod);
		return prod;
		//return product;
	}
	
	@GetMapping("/product")
	public List<Product> getProducts(){
		return repo.findAll();
	}
	
	
	@PutMapping("/product/{id}")
	public Product updateorsaveProduct(@RequestBody Product newproduct, @PathVariable Long id) {

       Product prod =  repo.findByProductID(id);
       if(prod != null) {
    	   prod.setName(newproduct.getName());
           prod.setPrice(newproduct.getPrice());
           prod.setCategoryID(newproduct.getCategoryID());
           prod.setInstock(newproduct.isInstock());
           
           if( repo.save(prod).getProductID().equals(id)) {
        	   System.out.println("Succesfully saved");
        	   return prod;
           }   
       }
    	 
       System.out.println("no prev entry - hence just saving");
       Product saveprod = new Product(newproduct.getName(), newproduct.getPrice() ,newproduct.getCategoryID(),newproduct.isInstock());
	   return repo.save(saveprod);   
    }

	
}