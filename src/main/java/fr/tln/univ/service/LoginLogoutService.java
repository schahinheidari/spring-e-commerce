package fr.tln.univ.service;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.LoginDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.Client;

public interface LoginLogoutService {
    public Client loginClient(LoginDto loginDto);

    public SessionDto logoutClient(SessionDto session);

    public void checkTokenStatus(String token);

    public void deleteExpiredTokens();


    public Admin loginAdmin(AdminDto adminDto);

    public SessionDto logoutAdmin(SessionDto session);

}
