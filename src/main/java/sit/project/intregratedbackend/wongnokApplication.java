package sit.project.intregratedbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sit.project.intregratedbackend.uploadfiles.StorageProperties;

@SpringBootApplication
public class wongnokApplication {

	public static void main(String[] args) {
		
//		StorageProperties test = new StorageProperties();
//		System.out.print(test.getLocation());
		SpringApplication.run(wongnokApplication.class, args);
	}

}
