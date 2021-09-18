package sit.project.intregratedbackend.controllers;

import java.io.IOException;
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

import sit.project.intregratedbackend.repositories.PostsRepository;
import sit.project.intregratedbackend.repositories.Posts_has_TagsRepository;
import sit.project.intregratedbackend.uploadfiles.StorageFileNotFoundException;
import sit.project.intregratedbackend.uploadfiles.StorageService;

@CrossOrigin
@RestController
public class FileRestController {
	@Autowired
    PostsRepository postsJpaRepository;
    //@Autowired
    //Posts_has_TagsRepository posts_has_TagsJpaRepository;
    
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
//    	storageService.delete(oldImage);
    	deleteFile(oldImage);
//    	for (int i = 0; i < productsJpaRepository.count(); i++) {
//			if(file.getOriginalFilename().equals(productsJpaRepository.findAll().get(i).getProductimage())) {
//				
//			}
//		}
        handleFileUpload(file);
        return "Update complete: Change to "+file.getOriginalFilename();
    }
    
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
    
//    public String randomString() {
//    	String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        StringBuilder salt = new StringBuilder();
//        Random rnd = new Random();
//        int size = 17;
//        while (salt.length() < size) { 
//            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//            salt.append(SALTCHARS.charAt(index));
//        }
//        String saltStr = salt.toString();
//        return saltStr;
//    }
}
