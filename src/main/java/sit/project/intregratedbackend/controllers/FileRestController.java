package sit.project.intregratedbackend.controllers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
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
public class FileRestController {
	@Autowired
    PostsRepository postsJpaRepository;
    @Autowired
    Posts_has_TagsRepository posts_has_TagsJpaRepository;
    
    final StorageService storageService;
    
    @Autowired
    public FileRestController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @GetMapping(value = "/files/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE )
    public Resource serveFile(@PathVariable String filename) {
        return storageService.loadAsResource(filename);
    }

    @DeleteMapping(value = "/deletefile/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE )
     public String deleteFile(@PathVariable String filename) throws IOException {
        storageService.delete(filename);
        return "Delete Image: "+filename+" complete." ;
    }

    @PostMapping("/uploadimage")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        	return file.getOriginalFilename()+" Uplode complete";
    }
    
    @PutMapping("/updateimage/{postNumber}")
    public String handleFileUpdate(@PathVariable int postNumber,@RequestParam("file") MultipartFile file) throws IOException {
    	String oldImage = postsJpaRepository.findById(postNumber).get().getImageName();
    	deleteFile(oldImage);
        handleFileUpload(file);
        return "Update complete: Change to "+file.getOriginalFilename();
    }
    
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
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
    	handleFileUpload(file);
    	return "Complete";
    }
}
