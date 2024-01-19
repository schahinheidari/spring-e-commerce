package fr.tln.univ.service;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.LoginDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.Client;

public interface LoginLogoutService {
    Client loginClient(LoginDto loginDto);

    SessionDto logout(SessionDto session);

    void checkTokenStatus(String token);

    void deleteExpiredTokens();

    Admin loginAdmin(AdminDto adminDto);
}
