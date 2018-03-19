//package pl.szczerbiak.blog.repositories;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import pl.szczerbiak.blog.model.entities.User;
//
//import java.util.List;
//import java.util.Optional;
//
//public class UserRepositoryImpl implements UserRepositorySearch {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public Optional<User> findByUsername(String username) {
//        List<User> users = userRepository.findAll();
//        Optional<User> result = users.stream()
//                .filter(user -> user.getUsername().equals(username))
//                .findAny();
//
//        return result;
//    }
//}
