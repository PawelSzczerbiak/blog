package pl.szczerbiak.blog.model.dtos;

import java.util.Date;

public class PostCommentDto {

    private Long id;
    private String comment;
    private Date created;

    public PostCommentDto(){
    }

    public PostCommentDto(Long id, String comment, Date created) {
        this.id = id;
        this.comment = comment;
        this.created = created;
    }

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}