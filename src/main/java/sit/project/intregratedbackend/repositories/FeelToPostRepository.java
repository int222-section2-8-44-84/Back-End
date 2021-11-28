package sit.project.intregratedbackend.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sit.project.intregratedbackend.models.FeelToPost;

@Repository
public interface FeelToPostRepository  extends JpaRepository<FeelToPost, Integer>{
	List<FeelToPost> findAllByaccountNumber(int accountNumber);
}
