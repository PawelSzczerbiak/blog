package pl.szczerbiak.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczerbiak.blog.model.entities.Post;
import pl.szczerbiak.blog.model.entities.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
