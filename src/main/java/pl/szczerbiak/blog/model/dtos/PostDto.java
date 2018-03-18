package pl.szczerbiak.blog.model.dtos;

import java.util.Date;
import java.util.Set;

public class PostDto {

    private Long id;
    private String title;
    private String content;
    private Long idOfUser;
    private Date created;
    private Set<TagDto> tags;

    public PostDto(){
    }

    public PostDto(Long id, String title, String content, Long idOfUser, Date created, Set<TagDto> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.idOfUser = idOfUser;
        this.created = created;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(Long idOfUser) {
        this.idOfUser = idOfUser;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }
}
