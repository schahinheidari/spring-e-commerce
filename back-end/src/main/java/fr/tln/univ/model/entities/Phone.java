package fr.tln.univ.model.entities;

import fr.tln.univ.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    private long id;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @OneToMany(mappedBy = "phone")
    private Set<UserPhone> userPhones = new HashSet<>();

}
