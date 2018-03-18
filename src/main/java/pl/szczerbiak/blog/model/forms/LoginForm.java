package pl.szczerbiak.blog.model.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// TODO: does it generate errors? Yes, in UserController!
//@Getter
//@Setter
//@ToString(exclude = {"password"})
public class LoginForm {

    private String username;
    private String password;

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
}
