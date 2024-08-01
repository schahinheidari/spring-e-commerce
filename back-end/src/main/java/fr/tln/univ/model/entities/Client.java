package fr.tln.univ.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.tln.univ.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends UserDetail {

    @OneToMany(mappedBy = "client")
    private List<Command> commandList;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart;

    @OneToMany(mappedBy = "client")
    private Set<UserPhone> userPhones = new HashSet<>();



}

