package pl.szczerbiak.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szczerbiak.blog.model.entities.Post;
import pl.szczerbiak.blog.model.entities.PostComment;
import pl.szczerbiak.blog.repositories.PostRepository;
import pl.szczerbiak.blog.services.UserSessionService;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

@Controller
public class MainController {

    private PostRepository postRepository;
    private UserSessionService userSessionService;

    @Autowired
    public MainController(PostRepository postRepository, UserSessionService userSessionService) {
        this.postRepository = postRepository;
        this.userSessionService = userSessionService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model) {
        model.addAttribute("loggedUser", userSessionService.getUserDto());
        model.addAttribute("name", "Pawel");
        return "index";
    }

    @GetMapping("/add/post")
    public String addPostPage() {
        return "addPost";
    }

    @PostMapping("/add/post")
    public String addPost(@RequestParam(value = "title") String titleParam,
                          @RequestParam String content, Model model) {

        System.out.println("Param = " + titleParam + ", " + content);
        Post post = new Post(titleParam, content);

        // List initialization
        PostComment postComment = new PostComment();
        postComment.setComment(titleParam);
        post.addComment(postComment);

        postRepository.save(post);

        return "index";
    }

    // Show posts

    // --- all
/*

    @GetMapping("/posts")
    public String postsPage(Model model) {
        List<Post> postList = new ArrayList<>();
        // Very useful !!!!!!!
        Iterable<Post> postIterable = postRepository.findAll();

        for (Post post : postIterable) {
            postList.add(post);
        }

        model.addAttribute("posts", postList);

        return "posts";
    }

    // --- contains title

    @GetMapping("/posts/{title}")
    public String postsByTitle(@PathVariable String title,
                               Model model) {

        List<Post> postList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAllByTitleContains(title);

        for (Post post : postIterable) {
            postList.add(post);
        }

        model.addAttribute("posts",postList);

        return "posts";
    }
*/

    // --- sort by title
/*
    @GetMapping("/posts/{title}/{sortField}/{sortDirection")
    public String postsByTitleSort(@PathVariable String title,
                                   @PathVariable String sortField,
                                   @PathVariable String sortDirection,
                                   Model model){

        Sort.Direction direction = Sort.Direction.ASC;

        if("desc".equals(sortDirection)){
            direction = Sort.Direction.DESC;
        }

        List<Post> postList = postRepository.findAllByTitleContains(title, direction, sortField);

        // This may not work:

//        List<Post> postList = postRepository.findAllByTitleContains(title, Sort.Direction.fromString(sortDirection), sortField);

        model.addAttribute("posts", postList);

        return "posts";

    }*/
}
