package pl.szczerbiak.blog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szczerbiak.blog.model.dtos.PostCommentDto;
import pl.szczerbiak.blog.model.dtos.PostDto;
import pl.szczerbiak.blog.model.entities.Post;
import pl.szczerbiak.blog.model.entities.PostComment;
import pl.szczerbiak.blog.model.entities.User;
import pl.szczerbiak.blog.repositories.PostCommentRepository;
import pl.szczerbiak.blog.repositories.PostRepository;
import pl.szczerbiak.blog.repositories.UserRepository;
import pl.szczerbiak.blog.services.PostService;

import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostRestController {

    private PostService postService;
    private PostRepository postRepository;
    private PostCommentRepository postCommentRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PostRestController(PostService postService, PostRepository postRepository, PostCommentRepository postCommentRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<PostDto> createPost(@RequestParam String title,
                                           @RequestParam String content,
                                           @RequestParam Long userId) {

        PostDto postDto = postService.createPost(title, content, userId);
        return ResponseEntity.ok().body(postDto);
    }

    @PostMapping("/addcomment/{userId}/{postId}/")
    public ResponseEntity<PostCommentDto> createPostComment(@PathVariable Long userId,
                                                         @PathVariable Long postId,
                                                         @RequestParam String comment) {

        PostCommentDto postCommentDto = postService.createPostComment(comment, userId, postId);
        return ResponseEntity.ok().body(postCommentDto);
    }
}
