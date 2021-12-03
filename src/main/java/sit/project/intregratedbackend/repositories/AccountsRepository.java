package sit.project.intregratedbackend.repositories;
import sit.project.intregratedbackend.models.AuthenticationUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<AuthenticationUser, Integer>{
	Optional<AuthenticationUser> findByuserID(String name);
	Optional<AuthenticationUser> findByemail(String email);
}
