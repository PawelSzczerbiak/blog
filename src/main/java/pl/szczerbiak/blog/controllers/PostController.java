package pl.szczerbiak.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.szczerbiak.blog.model.dtos.PostCommentDto;
import pl.szczerbiak.blog.model.dtos.PostDto;
import pl.szczerbiak.blog.model.entities.Post;
import pl.szczerbiak.blog.model.forms.PostCommentForm;
import pl.szczerbiak.blog.services.PostService;
import pl.szczerbiak.blog.services.UserSessionService;

import javax.validation.Valid;

@Controller
public class PostController {

    private PostService postService;
    private UserSessionService userSessionService;

    @Autowired
    public PostController(PostService postService, UserSessionService userSessionService) {
        this.postService = postService;
        this.userSessionService = userSessionService;
    }

    @GetMapping("/postAdd")
    public String addPostPage(Model model) {

        model.addAttribute("post", new Post());
        return "addPost";
    }

    @PostMapping("/postAdd")
    public String addPost(@ModelAttribute @Valid Post post,
                          BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "addPost";
        }

        Long userId = userSessionService.getUserDto().getId();

        PostDto postDto = postService.createPost(post.getTitle(), post.getContent(), userId);
        ResponseEntity.ok().body(postDto);

        return "redirect:/posts";
    }

    @GetMapping("/postCommentAdd{postId}")
    public String addPostCommentPage(@PathVariable Long postId, Model model) {

        if (userSessionService.getUserDto() != null) {
            Long userId = userSessionService.getUserDto().getId();
            model.addAttribute("postCommentForm", new PostCommentForm(userId, postId));
        }

        return "addPostComment";
    }

    @PostMapping("/postCommentAdd")
    public String addPostComment(@ModelAttribute PostCommentForm postCommentForm) {

        Long userId = postCommentForm.getUserId();
        Long postId = postCommentForm.getPostId();
        String comment = postCommentForm.getComment();

        PostCommentDto postCommentDto = postService.createPostComment(comment, userId, postId);
        ResponseEntity.ok().body(postCommentDto);

        return "redirect:/posts";
    }

}
