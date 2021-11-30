package sit.project.intregratedbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sit.project.intregratedbackend.models.Posts;

public interface PostsRepository extends JpaRepository<Posts, Integer>{

	List<Posts> findAllByaccountNumber(int accountNumber);

}
