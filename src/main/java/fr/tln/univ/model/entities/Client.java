package fr.tln.univ.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    @Pattern(regexp = "[A-Za-z\\s]+", message = "First Name should contains alphabets only")
    private String name;

    @Column(name = "FAMILY")
    @Pattern(regexp = "[A-Za-z\\s]+", message = "Last Name should contains alphabets only")
    private String family;

    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Please Enter a valid Password")
    private String password;

    @OneToMany(mappedBy = "client")
    private List<Command> commandList;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart;



    public Client(Integer id, String name, String family, String email, String password) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.email = email;
        this.password = password;
    }
}

