package sit.project.intregratedbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sit.project.intregratedbackend.models.Roles;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{
	
}
