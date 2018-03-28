package pl.szczerbiak.blog.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczerbiak.blog.model.entities.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByIdDesc();
    List<Post> findAllBy(Sort sort);
    List<Post> findPostsByTitleContains(String titlePhrase);
    List<Post> findPostsByTitleContains(String titlePhrase, Sort sort);
    List<Post> findPostsByTitleContainsOrContentContains(String titlePhrase, String contentPhrase);

}
