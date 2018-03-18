package pl.szczerbiak.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczerbiak.blog.model.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    // TODO: searching by tag
}
