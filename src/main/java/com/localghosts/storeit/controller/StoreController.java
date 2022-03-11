package com.localghosts.storeit.controller;

import java.lang.module.FindException;
import java.util.List;

import javax.crypto.BadPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.localghosts.storeit.config.CategoryRepo;
import com.localghosts.storeit.config.ProductRepo;
import com.localghosts.storeit.config.StoreRepo;
import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Product;
import com.localghosts.storeit.model.Store;

@RestController
public class StoreController {
	
	@Autowired
	StoreRepo repo;
	
	@Autowired
	ProductRepo repoproduct;
	
	@Autowired
	CategoryRepo repocategory;
	
	@GetMapping("/store")
	public List<Store> getStores(){
		return repo.findAll();
	}
	
	
	@PostMapping("/store")
	public Store addStore(@RequestBody Store store)
	{   
		//make the storeslug
		String storeslugString = makeStorestlug(store.getStorename());
		Store storecheck=new Store();
		
		//while loop that computes till a unique storeslug is found
		while(storecheck != null) {
			storeslugString +="_";
			storecheck =  repo.findByStoreslug(storeslugString);
			
		}
		
		Store storeobj = new Store();
		storeobj.setStoreslug(storeslugString);
		storeobj.setSellerid(store.getSellerid());
		storeobj.setStorename(store.getStorename());
		repo.save(storeobj);
		System.out.println(storeobj);
		return storeobj;
	}
	
	@PutMapping("/store/{storeslug}")
	public Store updateorsaveStore(@RequestBody Store newstore, @PathVariable String storeslug) {
		
       Store store =  repo.findByStoreslug(storeslug);
       
       if(store != null) {
    	//  these fields should be immutable  
    	//  store.setStoreslug(store.getStoreslug());
   		//	store.setSellerid(store.getSellerid());
   			store.setStorename(store.getStorename());
           
           if( repo.save(store).getStoreslug().equals(storeslug)) {
        	   System.out.println("Succesfully saved");
        	   return store;
           }   
       }
    	 
       System.out.println("no prev entry - hence just saving");
       Store storeobj = new Store();
	   return storeobj;
    }
	
	
	
	@GetMapping("/store/{storeslug}/product")
	public List<Product> getStoresProducts(@PathVariable String storeslug){
		Store store = repo.findByStoreslug(storeslug);
		
		List<Product> productlist =store.getProducts();
		return productlist;
	}
	
	
	@PostMapping("/store/{storeslug}/product")
	public Product addStoreProducts(@RequestBody Product product,@PathVariable String storeslug)
	{
		//adding product to the category
		Product newprod = new Product(product.getName(), product.getPrice() ,product.getCategoryID(),product.isInstock());
		Category category = repocategory.findByCategoryID(product.getCategoryID());
		category.getProducts().add(newprod);
		repocategory.save(category);
	
		System.out.println(newprod);
		
		//adding product to the store field
		//Store store = repo.findByStoreslug(storeslug);
		//store.getProducts().add(newprod);
		//repo.save(store);
		return newprod;
		
	}

	@GetMapping("/store/{storeslug}/category")
	public List<Category> getStoresCategories(@PathVariable String storeslug){
		Store store = repo.findByStoreslug(storeslug);
		List<Category> categorylist =store.getCategories();
		return categorylist;
	}
	
	
	@PostMapping("/store/{storeslug}/category")
	public Store addStoreCategories(@RequestBody Category category,@PathVariable String storeslug)
	{
		//TODO does it replace all the prev categories
		Category newcategory = new Category(category.getName() ,category.isEnabled());	
		Store store = repo.findByStoreslug(storeslug);
		System.out.println(newcategory);
		store.getCategories().add(newcategory);
		repo.save(store);
		return store;
		
	}
	
	
	
	public String makeStorestlug(String storename) {
		String withoutspaceString = storename.replaceAll("\\s", "");
		String lowercaseaplhaString = withoutspaceString.replaceAll("[^a-zA-Z]", "").toLowerCase();
		return lowercaseaplhaString;
	}
	
	
	

}


