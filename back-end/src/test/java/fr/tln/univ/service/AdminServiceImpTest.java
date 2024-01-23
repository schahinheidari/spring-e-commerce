package fr.tln.univ.service;

import fr.tln.univ.dao.AdminRepository;
import fr.tln.univ.dao.SessionRepository;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.exception.NotFoundException;
import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceImpTest {
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private LoginLogoutServiceImp loginService;
    @Mock
    private SessionRepository sessionRepository;
    @InjectMocks
    private AdminServiceImp adminServiceImp;

    Admin admin;
    AdminDto adminDto;
    List<Admin> adminList;
    UserSession userSession;
    String token = "adminToken";
    Integer adminId = 1;

    @BeforeEach
    void setUp() {
        adminList = new ArrayList<>();
        admin = new Admin();
        admin.setId(1);
        admin.setEmail("email");

        adminDto = new AdminDto();
        adminDto.setEmail("<EMAIL>");
        adminDto.setPassword("<PASSWORD>");

        userSession = new UserSession();
    }

    @Test
    void addAdmin() {
        when(adminRepository.save(admin)).thenReturn(admin);
        Admin result = adminServiceImp.add(admin);
        assertEquals(admin, result);
        adminRepository.save(admin);
    }

    @Test
    void getAllAdmins_adminListNotEmpty_shouldReturnAdminList(){
        adminList.add(new Admin());
        when(adminRepository.findAll()).thenReturn(adminList);
        List<Admin> result = adminServiceImp.getAll();
        assertEquals(adminList, result);
    }

    @Test
    void getAllAdmins_adminListEmpty_shouldThrowAdminException() {
        when(adminRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class, () -> adminServiceImp.getAll());
    }

    @Test
    void getAdminById_existingId_shouldReturnAdmin(){
        when(adminRepository.findById(adminId)).thenReturn(Optional.of(admin));
        Admin result = adminServiceImp.getById(adminId);
        assertEquals(admin, result);
    }

    @Test
    void getAdminById_nonExistingId_shouldThrowAdminException() {
        when(adminRepository.findById(adminId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> adminServiceImp.getById(adminId));
    }

    @Test
    void updateAdmin_validAdminAndToken_shouldReturnUpdatedAdmin() {
        when(adminRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);
        Admin result = adminServiceImp.update(admin, token);
        assertEquals(admin, result);
    }

    @Test
    void updateAdmin_nonExistingAdminId_shouldThrowAdminException() {
        when(adminRepository.findById(admin.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> adminServiceImp.update(admin, token));
    }

    @Test
    void deleteAdminById() {
        adminServiceImp.deleteById(adminId);
        adminRepository.deleteById(adminId);
    }

/*    @Test
    void getCurrentlyLoggedInAdmin_validToken_shouldReturnAdmin(){
        userSession.setUserId(1);
        Optional<UserSession> userSessionOpt = Optional.of(userSession);
        when(sessionRepository.findByToken(token)).thenReturn(userSessionOpt);
        when(adminRepository.findById(userSession.getUserId())).thenReturn(Optional.of(admin));
        Admin result = adminServiceImp.getCurrentlyLoggedInAdmin(token);
        assertEquals(admin, result);
    }*/


    @Test
    void updateAdminPassword_validAdminToken_shouldReturnSessionDto() {
        userSession.setUserId(1);
        Optional<UserSession> userSessionOpt = Optional.of(userSession);
        when(sessionRepository.findByToken(token)).thenReturn(userSessionOpt);
        Admin existingAdmin = new Admin();
        when(adminRepository.findById(userSession.getUserId())).thenReturn(Optional.of(existingAdmin));
        adminDto.setPassword("newPassword");
        SessionDto result =adminServiceImp.updateAdminPassword(adminDto, token);
        assertNotNull(result);
        assertEquals(token, result.getToken());
        adminRepository.save(existingAdmin);
        loginService.logout(result);
    }

    @Test
    void updateAdminPassword_invalidAdminToken_shouldThrowLoginException() {
        String token = "invalidToken";
        assertThrows(LoginException.class, () -> adminServiceImp.updateAdminPassword(adminDto, token));
    }
}