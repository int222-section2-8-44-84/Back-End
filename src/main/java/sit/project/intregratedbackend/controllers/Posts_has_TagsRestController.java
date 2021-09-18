package sit.project.intregratedbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import sit.project.intregratedbackend.models.Posts_has_Tags;
import sit.project.intregratedbackend.repositories.PostsRepository;
import sit.project.intregratedbackend.repositories.Posts_has_TagsRepository;

@CrossOrigin
@RestController
public class Posts_has_TagsRestController {
	@Autowired
	//PostsRepository postsRepo;
	Posts_has_TagsRepository posts_has_TagsRepo;
	
	@GetMapping("/showPostsHasTags")
	public List<Posts_has_Tags> showAllPosts(){
		return posts_has_TagsRepo.findAll();
	}
//		
//	@GetMapping("/showPostsHasTags/{postNumber}")
//	private List<Posts_has_Tags> findTagsByPostNumber(@PathVariable Integer postNumber){
//		return posts_has_TagsRepo.findByPostNumber(postNumber);
//	}
}
