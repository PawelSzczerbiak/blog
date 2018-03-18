package pl.szczerbiak.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szczerbiak.blog.model.entities.Post;
import pl.szczerbiak.blog.repositories.PostRepository;

import java.util.List;

public class SearchController {

    @Autowired
    PostRepository postRepository;

/*    @GetMapping("/search/posts")
    public String searchPosts(@RequestParam String searchPhrase,
                         Model model) {

        List<Post> postList = postRepository.findAllByTitleContainsOrContentContains(
                searchPhrase, searchPhrase);

        model.addAttribute("posts",postList);
        model.addAttribute("searchPhrase",searchPhrase);

        return "posts";
    }*/

}
