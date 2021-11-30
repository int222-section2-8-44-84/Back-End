package sit.project.intregratedbackend.controllers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sit.project.intregratedbackend.models.Posts;
import sit.project.intregratedbackend.models.Posts_has_Tags;
import sit.project.intregratedbackend.models.Tags;
import sit.project.intregratedbackend.repositories.PostsRepository;
import sit.project.intregratedbackend.repositories.Posts_has_TagsRepository;
import sit.project.intregratedbackend.uploadfiles.StorageFileNotFoundException;
import sit.project.intregratedbackend.uploadfiles.StorageService;

@CrossOrigin
@RestController
public class MainController {
	@Autowired
    PostsRepository postsJpaRepository;
    @Autowired
    Posts_has_TagsRepository posts_has_TagsJpaRepository;
    
    final StorageService storageService;
    
    @Autowired
    public MainController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @PostMapping("/createPostWithImage")
    public String createPost(@RequestParam("post") String newPost, @RequestParam("tags") String newTags, @RequestParam("file") MultipartFile file) {
    	Posts post = new Gson().fromJson(newPost, Posts.class);
    	postsJpaRepository.save(post);
    	
    	Type tagsType = new TypeToken<ArrayList<Tags>>(){}.getType();
    	ArrayList<Tags> tags = new Gson().fromJson(newTags, tagsType );
    	for (Tags tag : tags) { 
    		Posts_has_Tags postTag = new Posts_has_Tags(); 
    		postTag.setPostsNumber(post.getPostNumber());
    		postTag.setTagId(tag.getTagId());
    		posts_has_TagsJpaRepository.save(postTag);
    	}
    	storageService.store(file);
    	return "Complete";
    }
    
    @DeleteMapping("/deletePost/{postNumber}")
    public String deletePost(@PathVariable int postNumber) throws IOException {
    	Posts post = postsJpaRepository.findById(postNumber).orElse(null);
    	Set <Posts_has_Tags> postTags = post.getPostTags();
    	posts_has_TagsJpaRepository.deleteAll(postTags);
    	storageService.delete(post.getImageName());
    	postsJpaRepository.deleteById(postNumber);
       return "Delete Post Number: "+postNumber+" complete." ;
    }
    
    @PutMapping("/editPostWithImage/{postNumber}")
    public String editPost(@PathVariable int postNumber,@RequestParam("post") String newPost, @RequestParam("tags") String newTags, @RequestParam("file") MultipartFile file) throws IOException {
    	
    	//edit Posts
    	Posts editDataPost = new Gson().fromJson(newPost, Posts.class);
    	//edit Tags
    	Type tagsType = new TypeToken<ArrayList<Tags>>(){}.getType();
    	ArrayList<Tags> newTagsData = new Gson().fromJson(newTags, tagsType );
    	//find Old Post By Number
    	Posts post = postsJpaRepository.findById(postNumber).orElse(null);
    	//find Old PostTags
    		//Set <Posts_has_Tags> postTags = post.getPostTags();
    	//Delete Old Image
    	storageService.delete(post.getImageName());
    	//Set Post Data
    	post.setAll(editDataPost);
    	postsJpaRepository.save(post);
    	//Delete Old Tags
    	deleteTagsByPostNumber(postNumber);
    		//posts_has_TagsJpaRepository.deleteAll(postTags);
    	//Add New Tags
    	for (Tags tag : newTagsData) { 
    		Posts_has_Tags postTag = new Posts_has_Tags(); 
    		postTag.setPostsNumber(post.getPostNumber());
    		postTag.setTagId(tag.getTagId());
    		posts_has_TagsJpaRepository.save(postTag);
    	}
    	//Add New Image
    	storageService.store(file);
    	
        return "Update Post Number "+postNumber+" complete.";
    }
    
    private boolean deleteTagsByPostNumber(int postNumber) {
		Posts post = postsJpaRepository.findById(postNumber).orElse(null);
		if(post != null) {
			List<Posts_has_Tags> postTags = posts_has_TagsJpaRepository.findAllBypostsNumber(postNumber);
			posts_has_TagsJpaRepository.deleteAll(postTags);
			return true;
		} else {
    	return false;
		}
    }
    
    
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
