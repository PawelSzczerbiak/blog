package pl.szczerbiak.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szczerbiak.blog.model.dtos.UserDto;
import pl.szczerbiak.blog.model.entities.Post;
import pl.szczerbiak.blog.model.entities.User;
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

        if (userSessionService.getUserDto() != null) {
            model.addAttribute("loggedUser", userSessionService.getUserDto().getUsername());
        }

        return "index";
    }

    // Show posts

    // --- all

    @GetMapping("/posts")
    public String postsPage(Model model) {

        if (userSessionService.getUserDto() != null) {
            model.addAttribute("loggedUser", userSessionService.getUserDto().getUsername());
        }

        List<Post> postList = new ArrayList<>();
        // Very useful construction !!!!!!!
//        Iterable<Post> posts = postRepository.findAll();
//        Iterable<Post> posts = postRepository.findAllByOrderByIdDesc();
        Iterable<Post> posts = postRepository.findAllBy(Sort.by(Sort.Direction.DESC, "audit.created"));
        posts.forEach(postList::add);

        model.addAttribute("posts", postList);

        return "posts";
    }

    // --- contains title

    @GetMapping("/postsTitleSearch")
    public String postsByTitle(@RequestParam String title, Model model) {

        if (userSessionService.getUserDto() != null) {
            model.addAttribute("loggedUser", userSessionService.getUserDto().getUsername());
        }

        List<Post> postList = new ArrayList<>();
        Iterable<Post> posts = postRepository.findPostsByTitleContains(title);
        posts.forEach(postList::add);

        model.addAttribute("posts", postList);

        return "posts";
    }

    // --- contains title or content

    @GetMapping("/postsContentSearch")
    public String postsByTitleContent(@RequestParam String phrase, Model model) {

        if (userSessionService.getUserDto() != null) {
            model.addAttribute("loggedUser", userSessionService.getUserDto().getUsername());
        }

        List<Post> postList = new ArrayList<>();
        Iterable<Post> posts = postRepository
                .findPostsByTitleContainsOrContentContains(phrase, phrase);
        posts.forEach(postList::add);

        model.addAttribute("posts", postList);

        return "posts";
    }

    // --- sort by title

    @GetMapping("/posts/{title}/{sortField}/{sortDirection")
    public String postsByTitleSort(@PathVariable String title,
                                   @PathVariable String sortField,
                                   @PathVariable String sortDirection,
                                   Model model) {

        if (userSessionService.getUserDto() != null) {
            model.addAttribute("loggedUser", userSessionService.getUserDto().getUsername());
        }

        Sort.Direction direction = Sort.Direction.ASC;

        if ("desc".equals(sortDirection)) {
            direction = Sort.Direction.DESC;
        }

        List<Post> postList = new ArrayList<>();
        Iterable<Post> posts = postRepository.
                findPostsByTitleContains(title, Sort.by(direction, sortField));
        posts.forEach(postList::add);
        // This may not always work:
        // List<Post> postList = postRepository.findPostsByTitleContains(title, Sort.Direction.fromString(sortDirection), sortField));

        model.addAttribute("posts", postList);

        return "posts";
    }
}
