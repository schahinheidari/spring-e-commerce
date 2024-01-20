package fr.tln.univ.controller;

import fr.tln.univ.config.RestCaller;
import fr.tln.univ.model.LoginDto;
import org.springframework.http.HttpMethod;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;


@ViewScoped
@Named("loginBean")
public class LoginBean implements Serializable {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void login() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(this.email);
        loginDto.setPassword(this.password);
        RestCaller restCaller = new RestCaller();
        restCaller.call("api/login/client/v1", HttpMethod.POST, loginDto, LoginDto.class);
    }

}
