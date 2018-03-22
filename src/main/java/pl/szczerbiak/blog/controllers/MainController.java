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

import java.util.ArrayList;
import java.util.List;

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

        if (userSessionService.getUserDto() == null) {
            model.addAttribute("name", "logout");
        } else {
            model.addAttribute("name", userSessionService.getUserDto().getUsername());
        }
        return "index";
    }

    @GetMapping("/post/add")
    public String addPostPage() {
        return "addPost";
    }

    @PostMapping("/post/add")
    public String addPost(@RequestParam(value = "title") String title,
                          @RequestParam String content, Model model) {

        Post post = new Post(title, content);

        // List initialization
        // TODO: improve it
        PostComment postComment = new PostComment();
        postComment.setComment(title);
        post.addComment(postComment);

        postRepository.save(post);

        return "index";
    }

    // Show posts

    // --- all

    @GetMapping("/posts")
    public String postsPage(Model model) {

        model.addAttribute("loggedUser", userSessionService.getUserDto());

        if (userSessionService.getUserDto() == null) {
            model.addAttribute("name", "logout");
        } else {
            model.addAttribute("name", userSessionService.getUserDto().getUsername());
        }

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

        List<Post> postList = postRepository.findPostsByTitleContains(title);
        model.addAttribute("posts", postList);

        return "posts";
    }

    // --- contains title or content

    @GetMapping("/posts/{phrase}")
    public String postsByTitleContent(@PathVariable String phrase,
                                      Model model) {

        List<Post> postList = postRepository
                .findPostsByTitleContainsOrContentContains(phrase, phrase);
        model.addAttribute("posts", postList);

        return "posts";
    }

    // --- sort by title

    @GetMapping("/posts/{title}/{sortField}/{sortDirection")
    public String postsByTitleSort(@PathVariable String title,
                                   @PathVariable String sortField,
                                   @PathVariable String sortDirection,
                                   Model model) {

        Sort.Direction direction = Sort.Direction.ASC;

        if ("desc".equals(sortDirection)) {
            direction = Sort.Direction.DESC;
        }

        List<Post> postList = postRepository.findPostsByTitleContains(title, Sort.by(direction, sortField));
        // This may not always work:
        // List<Post> postList = postRepository.findPostsByTitleContains(title, Sort.Direction.fromString(sortDirection), sortField));

        model.addAttribute("posts", postList);

        return "posts";
    }
}
