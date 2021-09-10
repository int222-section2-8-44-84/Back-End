package sit.project.intregratedbackend.repositories;
import sit.project.intregratedbackend.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Integer>{
	
}
