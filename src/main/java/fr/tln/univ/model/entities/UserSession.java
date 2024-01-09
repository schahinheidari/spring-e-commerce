package fr.tln.univ.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sessionId;

    @Column(unique = true)
    private String token;

    @Column(unique = true)
    private Integer userId;

    private String userType;
    private LocalDateTime sessionStartTime;

    private LocalDateTime sessionEndTime;
}
