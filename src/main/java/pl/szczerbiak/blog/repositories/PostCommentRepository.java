package pl.szczerbiak.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szczerbiak.blog.model.entities.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
