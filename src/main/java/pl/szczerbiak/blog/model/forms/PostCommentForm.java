package pl.szczerbiak.blog.model.forms;

public class PostCommentForm {

    private String comment;
    private Long userId;
    private Long postId;

    public PostCommentForm() {
    }

    public PostCommentForm(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}

