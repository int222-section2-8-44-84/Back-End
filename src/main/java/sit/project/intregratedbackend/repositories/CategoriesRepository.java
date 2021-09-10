package sit.project.intregratedbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.project.intregratedbackend.models.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Integer>{

}
