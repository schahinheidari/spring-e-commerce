package fr.tln.univ.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;

    @Column(name= "NAME")
    @NotNull(message="Please enter the first name")
    @Pattern(regexp="[A-Za-z\\s]+", message="First Name should contains alphabets only")
    private String name;

    @Column(name= "FAMILY")
    @NotNull(message="Please enter the last name")
    @Pattern(regexp="[A-Za-z\\s]+", message="First Name should contains alphabets only")
    private String family;

    @Pattern(regexp="[A-Za-z0-9!@#$%^&*_]{8,15}", message="Please Enter a valid Password")
    private String password;

    @Email
    @Column(name = "EMAIL" ,unique = true)
    private String email;


    @OneToMany
    @JsonIgnore
    private List<Produit> produitList;


}
