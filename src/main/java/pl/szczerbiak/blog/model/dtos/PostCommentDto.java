package pl.szczerbiak.blog.model.dtos;

import java.util.Date;

public class PostCommentDto {

    private Long id;
    private String comment;
    private Date created;
    private Long idOfPost;
    private Long idOfUser;

    public PostCommentDto(){
    }

    public PostCommentDto(Long id, String comment, Date created, Long idOfPost, Long idOfUser) {
        this.id = id;
        this.comment = comment;
        this.created = created;
        this.idOfPost = idOfPost;
        this.idOfUser = idOfUser;
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

    public Long getIdOfPost() {
        return idOfPost;
    }

    public void setIdOfPost(Long idOfPost) {
        this.idOfPost = idOfPost;
    }

    public Long getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(Long idOfUser) {
        this.idOfUser = idOfUser;
    }
}