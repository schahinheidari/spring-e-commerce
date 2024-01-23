package fr.tln.univ.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name= "NAME")
    @NotNull(message="Please enter the first name")
    @Pattern(regexp="[A-Za-z\\s]+", message="First Name should contains alphabets only")
    private String name;

    @Column(name= "FAMILY")
    @NotNull(message="Please enter the last name")
    @Pattern(regexp="[A-Za-z\\s]+", message="First Name should contains alphabets only")
    private String family;

    @Email
    @Column(name = "EMAIL" ,unique = true)
    private String email;

    @Pattern(regexp="[A-Za-z0-9!@#$%^&*_]{8,15}", message="Please Enter a valid Password")
    private String password;

}

