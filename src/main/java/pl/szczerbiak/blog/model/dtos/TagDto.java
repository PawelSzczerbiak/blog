package pl.szczerbiak.blog.model.dtos;

import java.util.Date;
import java.util.Set;

public class TagDto {

    private Long id;
    private String tagname;
    private Date created;
    private Set<PostDto> posts;

    public TagDto(){
    }

    public TagDto(Long id, String tagname, Date created, Set<PostDto> posts) {
        this.id = id;
        this.tagname = tagname;
        this.created = created;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostDto> posts) {
        this.posts = posts;
    }
}
