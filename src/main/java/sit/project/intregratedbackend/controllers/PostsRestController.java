package sit.project.intregratedbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sit.project.intregratedbackend.models.Posts;
import sit.project.intregratedbackend.repositories.Posts_has_TagsRepository;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostsRestController {
	
	@Autowired
	PostsRestController PostsRepo;
	
	@Autowired
	Posts_has_TagsRepository PostsTagRepo;
	
	//Add Posts
	@PostMapping("/add")
	public void addPosts(@RequestBody Posts newPosts) {
		
	}
	
}