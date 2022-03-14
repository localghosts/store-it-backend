package com.localghosts.storeit.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

	/**
	 * @Autowired
	 *            CategoryRepo repo;
	 * 
	 *            @PostMapping("/category")
	 *            public Category addCategory(@RequestBody Category category)
	 *            {
	 *            Category newcategory = new
	 *            Category(category.getName(),category.isEnabled());
	 *            repo.save(newcategory);
	 *            System.out.println(newcategory);
	 *            return newcategory;
	 *            }
	 * 
	 *            @GetMapping("/category")
	 *            public List<Category> getCategory(){
	 *            return repo.findAll();
	 *            }
	 * 
	 * 
	 * 
	 * 
	 *            @PutMapping("/category/{id}")
	 *            public Category updateorsaveCategory(@RequestBody Category
	 *            newcategory, @PathVariable Long id) {
	 * 
	 *            Category category = repo.findByCategoryID(id);
	 *            if(category != null) {
	 *            category.setName(newcategory.getName());
	 *            category.setEnabled(newcategory.isEnabled());
	 * 
	 *            if( repo.save(category).getCategoryID().equals(id)) {
	 *            System.out.println("Succesfully saved");
	 *            return category;
	 *            }
	 *            }
	 * 
	 *            System.out.println("no prev entry - hence just saving");
	 *            Category savecategory = new Category(newcategory.getName()
	 *            ,newcategory.isEnabled());
	 *            repo.save(savecategory);
	 *            return savecategory;
	 * 
	 *            }
	 */

}
