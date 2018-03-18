package pl.szczerbiak.blog.model.forms;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// TODO: does it generate errors?  Yes, in UserController!
//@Getter
//@Setter
@ToString(exclude = {"password"})
public class RegisterForm { // validation by @Valid

    @Size(min = 5, message = "Username must be longer than {min} characters." +
            "Given phrase ${validatedValue} does not match")
    private String username;

    @Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters")
    private String password;

    @Pattern(regexp = "[A-z0-9.]+@[a-z0-9\\-]+\\.[a-z]{2,5}", message = "E-mail not correct")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
