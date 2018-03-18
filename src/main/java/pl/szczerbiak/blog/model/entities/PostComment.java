package pl.szczerbiak.blog.model.entities;

import javax.persistence.*;

@Entity
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;

    @Embedded
    private AuditEntity audit = new AuditEntity();

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AuditEntity getAudit() {
        return audit;
    }

    public void setAudit(AuditEntity audit) {
        this.audit = audit;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
