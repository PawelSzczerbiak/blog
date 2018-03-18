package pl.szczerbiak.blog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szczerbiak.blog.model.dtos.PostDto;
import pl.szczerbiak.blog.model.entities.Post;
import pl.szczerbiak.blog.model.entities.User;
import pl.szczerbiak.blog.repositories.PostRepository;
import pl.szczerbiak.blog.repositories.UserRepository;

import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    // Frontend data
    public PostDto createPost(String title, String content, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Post post = new Post(title, content);
        userOptional.ifPresent(
                user -> {
                    post.setUser(user);
                    postRepository.save(post);
                });

        return modelMapper.map(post, PostDto.class);
    }
}
