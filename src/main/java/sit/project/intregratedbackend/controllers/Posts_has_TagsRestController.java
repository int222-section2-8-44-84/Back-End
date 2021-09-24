package sit.project.intregratedbackend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	PostsRepository postsRepo;
	
	@GetMapping("/showPostsHasTags")
	public List<Posts_has_Tags> showAllPosts(){
		return posts_has_TagsRepo.findAll();
	}

		
	@GetMapping("/showPostsHasTags/{postNumber}")
	private List<Posts_has_Tags> findTagsByPostNumber(@PathVariable Integer postNumber){
		
		List<Posts_has_Tags> TagsThisPost = new ArrayList<>();
		for (int i = 0; i < showAllPosts().size() ; i++) {
			Posts_has_Tags postsTags = showAllPosts().get(i);
			if(postsTags.getPostsNumber()==postNumber) {
				TagsThisPost.add(postsTags);
			}
		}
		return TagsThisPost ;
	}
	
	
}