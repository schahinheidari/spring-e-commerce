package fr.tln.univ.model.entities;

import fr.tln.univ.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "NAME")
    @Pattern(regexp="[A-Za-z\\s]+", message="First Name should contains alphabets only")
    private String name;

    @Column(name = "FAMILY")
    @Pattern(regexp="[A-Za-z\\s]+", message="Last Name should contains alphabets only")
    private String family;

    @Column(name = "BIRTHDAY")
    @Pattern(regexp="\\d{4}-\\d{2}-\\d{2}", message="Please Enter a valid birthday")
    private long birthday;

    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @Pattern(regexp="[A-Za-z0-9!@#$%^&*_]{8,15}", message="Please Enter a valid Password")
    private String password;

    @Column(name = "NATIONAL_CODE")
    @Pattern(regexp="[0-9]{10}", message="Please Enter a valid national Code")
    private String nationalCode;

    @ElementCollection
    private List<Role> roles;
}
