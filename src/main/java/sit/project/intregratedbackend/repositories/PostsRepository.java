package sit.project.intregratedbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sit.project.intregratedbackend.models.Posts;

public interface PostsRepository extends JpaRepository<Posts, Integer>{

}
