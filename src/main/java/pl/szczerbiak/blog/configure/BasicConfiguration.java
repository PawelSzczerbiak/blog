package pl.szczerbiak.blog.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.szczerbiak.blog.model.dtos.PostCommentDto;
import pl.szczerbiak.blog.model.dtos.PostDto;
import pl.szczerbiak.blog.model.dtos.TagDto;
import pl.szczerbiak.blog.model.entities.Post;
import pl.szczerbiak.blog.model.entities.PostComment;
import pl.szczerbiak.blog.model.entities.Tag;

@Configuration
public class BasicConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Post.class, PostDto.class)
                .addMapping(post -> post.getUser().getId(), PostDto::setIdOfUser)
                .addMapping(post -> post.getAudit().getCreated(), PostDto::setCreated);

        modelMapper.createTypeMap(PostComment.class, PostCommentDto.class)
                .addMapping(pc -> pc.getUser().getId(), PostCommentDto::setIdOfUser)
                .addMapping(pc -> pc.getPost().getId(), PostCommentDto::setIdOfPost)
                .addMapping(pc -> pc.getAudit().getCreated(), PostCommentDto::setCreated);

        modelMapper.createTypeMap(Tag.class, TagDto.class)
                .addMapping(tag -> tag.getAudit().getCreated(), TagDto::setCreated);

        return modelMapper;
    }

}
