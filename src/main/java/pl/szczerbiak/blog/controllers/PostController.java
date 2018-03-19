package pl.szczerbiak.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.szczerbiak.blog.repositories.PostRepository;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;


}
