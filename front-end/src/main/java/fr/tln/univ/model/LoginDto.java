package fr.tln.univ.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginDto() {
    }

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

}

