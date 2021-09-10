package sit.project.intregratedbackend.repositories;

import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tags, Integer>{

}
