package pl.szczerbiak.blog.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczerbiak.blog.model.entities.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

/*
    Iterable<Post> findAllByTitleContains(String title);
    List<Post> findAllByTitleContains(String title, Sort.Direction direction, String sortField);
    List<Post> findAllByTitleContainsOrContentContains(String searchPhrase, String searchPhrase1);
*/

}
