package sit.project.intregratedbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sit.project.intregratedbackend.models.Posts_has_Tags;

public interface Posts_has_TagsRepository extends JpaRepository<Posts_has_Tags, Integer>{

	//@Query("SELECT pt FROM Posts_has_Tags pt WHERE pt.PostNumber = 000001")
	//List<Posts_has_Tags> findByPostNumber(int PostNumber);

}
