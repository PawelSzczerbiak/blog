package pl.szczerbiak.blog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczerbiak.blog.model.dtos.PostDto;
import pl.szczerbiak.blog.model.dtos.TagDto;
import pl.szczerbiak.blog.model.entities.Post;
import pl.szczerbiak.blog.model.entities.Tag;
import pl.szczerbiak.blog.repositories.PostRepository;
import pl.szczerbiak.blog.repositories.TagRepository;

import java.util.List;

// Tags to posts are added from API
// Just for practise...
@RestController
public class TagRestController {

    private TagRepository tagRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    // Why constructor? See: UserController
    @Autowired
    public TagRestController(TagRepository tagRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/api/tag/add") // equivalent to:
    // @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public ResponseEntity<TagDto> createTag(@RequestParam String tagName){

        Tag tag = new Tag();
        tag.setTagname(tagName);
        tagRepository.save(tag);

        TagDto tagDto = modelMapper.map(tag, TagDto.class);

        return ResponseEntity.ok().body(tagDto);
    }

    @PutMapping("/api/tag/addToPpost")
    public ResponseEntity<PostDto> addTagToPost(@RequestParam Long tagId,
                                                @RequestParam Long postId){

        Tag tag = tagRepository.getOne(tagId);
        Post post =  postRepository.getOne(postId);

        post.getTags().add(tag);
        postRepository.save(post);

        return ResponseEntity.ok().body(modelMapper.map(post, PostDto.class));
    }

    @GetMapping("/api/tags")
    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }
}
