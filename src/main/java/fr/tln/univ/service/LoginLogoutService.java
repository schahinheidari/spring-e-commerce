package fr.tln.univ.service;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.UserSession;

public interface LoginLogoutService {
    public UserSession loginClient(ClientDto clientDto);

    public SessionDto logoutClient(SessionDto session);

    public void checkTokenStatus(String token);

    public void deleteExpiredTokens();


    public UserSession loginAdmin(AdminDto adminDto);

    public SessionDto logoutAdmin(SessionDto session);

}
