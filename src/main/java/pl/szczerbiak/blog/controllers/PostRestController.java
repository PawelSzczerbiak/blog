package pl.szczerbiak.blog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.szczerbiak.blog.model.dtos.PostDto;
import pl.szczerbiak.blog.repositories.PostRepository;
import pl.szczerbiak.blog.services.PostService;

@RestController
public class PostRestController {

    private PostRepository postRepository;
    private PostService postService;
    private ModelMapper modelMapper;

    // Why constructor? See: UserController
    @Autowired
    public PostRestController(PostRepository postRepository, PostService postService, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/api/post/add")
    public ResponseEntity<PostDto> addPost(@RequestParam String title,
                                           @RequestParam String content,
                                           @RequestParam Long userId){

        PostDto postDto = postService.createPost(title, content, userId);
        return ResponseEntity.ok().body(postDto);
    }
}
