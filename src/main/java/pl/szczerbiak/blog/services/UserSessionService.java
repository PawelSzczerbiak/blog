package pl.szczerbiak.blog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.szczerbiak.blog.model.dtos.UserDto;
import pl.szczerbiak.blog.model.entities.User;
import pl.szczerbiak.blog.repositories.UserRepository;

import java.util.Optional;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionService {

    private boolean logged;
    private UserDto userDto;

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserSessionService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    // Frontend data
    public boolean loginUser(String username, String password) {

        Optional<User> userOptional = userRepository.findUserByUsername(username);

        // User does not exist
        if (!userOptional.isPresent()) {
            return false;
        }

        User user = userOptional.get();

        // Wrong password
        if (!user.getPassword().equals(password)){
            return false;
        }

        userDto = modelMapper.map(user, UserDto.class);
        logged = true;
        return logged;
    }

    public boolean getLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
