package pl.szczerbiak.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczerbiak.blog.model.entities.User;

import java.util.Optional;

// https://stackoverflow.com/questions/11880924/how-to-add-custom-method-to-spring-data-jpa
//public interface UserRepository extends JpaRepository<User, Long>, UserRepositorySearch {

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

}
