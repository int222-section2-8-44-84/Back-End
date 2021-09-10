package sit.project.intregratedbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sit.project.intregratedbackend.models.Categories;
import sit.project.intregratedbackend.repositories.CategoriesRepository;

@CrossOrigin
@RestController
public class CategoriesRestController {
	@Autowired
	CategoriesRepository CategoriesRepo;
	
	@GetMapping("/showallcategories")
	public List<Categories> allCategories(){
		return CategoriesRepo.findAll();
	}
}