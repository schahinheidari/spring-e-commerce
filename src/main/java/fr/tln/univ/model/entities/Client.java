package fr.tln.univ.model.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "NAME")
    private String name;

    @Column(name = "FAMILY")
    private String family;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandeList;


    public Client(Integer id, String name, String family, String email, String password) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.email = email;
        this.password = password;
    }
}

