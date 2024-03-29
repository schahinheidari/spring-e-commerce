package fr.tln.univ.service;

import fr.tln.univ.dao.AdminRepository;
import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.dao.SessionRepository;
import fr.tln.univ.exception.AdminNotFoundException;
import fr.tln.univ.exception.ClientNotFoundException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.LoginDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.UserSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginLogoutServiceImp implements LoginLogoutService {

    private SessionRepository sessionRepository;
    private ClientRepository clientRepository;
    private AdminRepository adminRepository;

    // Method to login a customer
    @Override
    public Client loginClient(LoginDto loginDto) {
        Optional<Client> res = clientRepository.findByEmail(loginDto.getEmail());
        if(res.isEmpty())
            throw new ClientNotFoundException("Client record does not exist with given email");
        Client existingClient = res.get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(loginDto.getPassword());
        if(!password.equals(existingClient.getPassword()))
            throw new LoginException("Invalid password");
        return existingClient;
    }

    // Method to logout a client session
    @Override
    public SessionDto logoutClient(SessionDto sessionToken) {
        return getSessionDto(sessionToken);
    }

    private SessionDto getSessionDto(SessionDto sessionToken) {
        String token = sessionToken.getToken();
        checkTokenStatus(token);
        Optional<UserSession> opt = sessionRepository.findByToken(token);
        if(opt.isEmpty())
            throw new LoginException("User not logged in. Invalid session token. Login Again.");
        UserSession session = opt.get();
        sessionRepository.delete(session);
        sessionToken.setMessage("Logged out sucessfully.");
        return sessionToken;
    }

    // Method to check status of session token
    @Override
    public void checkTokenStatus(String token) {
        Optional<UserSession> opt = sessionRepository.findByToken(token);
        if(opt.isPresent()) {
            UserSession session = opt.get();
            LocalDateTime endTime = session.getSessionEndTime();
            boolean flag = false;
            if(endTime.isBefore(LocalDateTime.now())) {
                sessionRepository.delete(session);
                flag = true;
            }
            deleteExpiredTokens();
            if(flag)
                throw new LoginException("Session expired. Login Again");
        }
        else {
            throw new LoginException("User not logged in. Invalid session token. Please login first.");
        }
    }


    // Method to login a valid seller and generate a seller token
    @Override
    public Admin loginAdmin(AdminDto adminDto) {
        Optional<Admin> res = adminRepository.findByEmail(adminDto.getEmail());
        if(res.isEmpty())
            throw new AdminNotFoundException("Admin record does not exist with given email address");
        Admin existingAdmin = res.get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(adminDto.getPassword());
        if(!password.equals(existingAdmin.getPassword()))
            throw new LoginException("Invalid password");
        return existingAdmin;
    }

    // Method to logout a admin and delete his session token
    @Override
    public SessionDto logoutAdmin(SessionDto session) {
        return getSessionDto(session);
    }

    // Method to delete expired tokens
    @Override
    public void deleteExpiredTokens() {
        System.out.println("Inside delete tokens");
        List<UserSession> users = sessionRepository.findAll();
        System.out.println(users);
        if(users.size() > 0) {
            for(UserSession user:users) {
                System.out.println(user.getUserId());
                LocalDateTime endTime = user.getSessionEndTime();
                if(endTime.isBefore(LocalDateTime.now())) {
                    System.out.println(user.getUserId());
                    sessionRepository.delete(user);
                }
            }
        }
    }
}
