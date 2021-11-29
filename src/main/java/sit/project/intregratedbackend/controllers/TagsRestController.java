package sit.project.intregratedbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sit.project.intregratedbackend.models.Tags;
import sit.project.intregratedbackend.repositories.TagsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TagsRestController {
	@Autowired
	TagsRepository tagsRepo;
	
	@GetMapping("/showAllTags")
	public List<Tags> allTags(){
		return tagsRepo.findAll();
	}
}
