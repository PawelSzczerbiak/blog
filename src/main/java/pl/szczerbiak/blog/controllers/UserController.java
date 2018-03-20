package pl.szczerbiak.blog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szczerbiak.blog.model.entities.User;
import pl.szczerbiak.blog.model.forms.LoginForm;
import pl.szczerbiak.blog.model.forms.RegisterForm;
import pl.szczerbiak.blog.repositories.UserRepository;
import pl.szczerbiak.blog.services.UserSessionService;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserRepository userRepository;
    private UserSessionService userSessionService;
    private ModelMapper modelMapper;

    // One @Autowired for all fields above
    // This is a good practice and is better for testing
    @Autowired
    public UserController(UserRepository userRepository, UserSessionService userSessionService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userSessionService = userSessionService;
        this.modelMapper = modelMapper;
    }

    // Registration

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";

    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid RegisterForm registerForm,
                               BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        User user = modelMapper.map(registerForm, User.class);
        userRepository.save(user);

        return "index";
    }

    // Login

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute @Valid LoginForm loginForm,
                            BindingResult bindingResult, Model model) {

        boolean logged = userSessionService.loginUser(
                loginForm.getUsername(), loginForm.getPassword());

        if (!logged) { // TODO: distinguish case when password is incorrect
            bindingResult.reject("username", null, "Username does not exist");
        }

        if (bindingResult.hasErrors()) {
            return "login";
        }

        model.addAttribute("loggedUser", logged);

        return "redirect:/";
    }

    // Logout

    @GetMapping("/logout")
    public String logoutUser(Model model) {

        boolean logged =  userSessionService.logoutUser();
        model.addAttribute("loggedUser", logged);

        return "redirect:/";
    }

}
