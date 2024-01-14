package fr.tln.univ.service;

import fr.tln.univ.dao.AdminRepository;
import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.dao.SessionRepository;
import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.UserSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginLogoutServiceImpTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void loginClient() {
    }

    @Test
    void logoutClient() {
    }

    @Test
    void checkTokenStatus() {
    }

    @Test
    void loginAdmin() {
    }

    @Test
    void logoutAdmin() {
    }

    @Test
    void deleteExpiredTokens() {
    }
}