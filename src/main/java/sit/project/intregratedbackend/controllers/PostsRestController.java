package sit.project.intregratedbackend.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sit.project.intregratedbackend.models.Posts;
import sit.project.intregratedbackend.repositories.PostsRepository;

@CrossOrigin
@RestController
public class PostsRestController {
	@Autowired
	PostsRepository postsRepo;

	@GetMapping("/posts")
	public List<Posts> showAllPosts(){
		return postsRepo.findAll();
	}
	
    @GetMapping("/posts/{postNumber}")
    public Posts showPosts(@PathVariable int postNumber) {
        return postsRepo.findById(postNumber).orElse(null);
    }
    
    //Delete
    @DeleteMapping("/posts/{postNumber}")
    public String deletePost(@PathVariable Integer postNumber) throws IOException {
    	postsRepo.findById(postNumber).orElse(null);
    	postsRepo.deleteById(postNumber);
		return "Delete Post Success";
    }
    
    //Add
    @PostMapping("/create")
    public Posts createPost(@RequestBody Posts newPost) {
		return postsRepo.save(newPost);
    }
}
