package fr.tln.univ.service;

import fr.tln.univ.dao.AdminRepository;
import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.dao.SessionDao;
import fr.tln.univ.exception.AdminNotFoundException;
import fr.tln.univ.exception.ClientNotFoundException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginLogoutService {


    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;



    // Method to login a customer


    public UserSession loginClient(ClientDto loginClient) {

        Optional<Client> res = clientRepository.findByEmail(loginClient.getEmail());

        if(res.isEmpty())
            throw new ClientNotFoundException("Client record does not exist with given email");

        Client existingClient = res.get();
        Optional<UserSession> opt = sessionDao.findByUserId(existingClient.getId());
        if(opt.isPresent()) {

            UserSession user = opt.get();

            if(user.getSessionEndTime().isBefore(LocalDateTime.now())) {
                sessionDao.delete(user);
            }
            else
                throw new LoginException("User already logged in");

        }

        if(existingClient.getPassword().equals(loginClient.getPassword())) {

            UserSession newSession = new UserSession();

            newSession.setUserId(existingClient.getId());
            newSession.setUserType("client");
            newSession.setSessionStartTime(LocalDateTime.now());
            newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));

            UUID uuid = UUID.randomUUID();
            String token = "client_" + uuid.toString().split("-")[0];

            newSession.setToken(token);

            return sessionDao.save(newSession);
        }
        else {
            throw new LoginException("Password Incorrect. Try again.");
        }
    }


    // Method to logout a client session


    public SessionDto logoutClient(SessionDto sessionToken) {

        String token = sessionToken.getToken();

        checkTokenStatus(token);

        Optional<UserSession> opt = sessionDao.findByToken(token);

        if(!opt.isPresent())
            throw new LoginException("User not logged in. Invalid session token. Login Again.");

        UserSession session = opt.get();

        sessionDao.delete(session);

        sessionToken.setMessage("Logged out sucessfully.");

        return sessionToken;
    }



    // Method to check status of session token



    public void checkTokenStatus(String token) {

        Optional<UserSession> opt = sessionDao.findByToken(token);

        if(opt.isPresent()) {
            UserSession session = opt.get();
            LocalDateTime endTime = session.getSessionEndTime();
            boolean flag = false;
            if(endTime.isBefore(LocalDateTime.now())) {
                sessionDao.delete(session);
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

    public UserSession loginAdmin(AdminDto adminDto) {

        Optional<Admin> res = adminRepository.findByEmail(adminDto.getEmail());

        if(res.isEmpty())
            throw new AdminNotFoundException("Admin record does not exist with given email address");

        Admin existingAdmin = res.get();

        Optional<UserSession> opt = sessionDao.findByUserId(existingAdmin.getAdminId());

        if(opt.isPresent()) {

            UserSession user = opt.get();

            if(user.getSessionEndTime().isBefore(LocalDateTime.now())) {
                sessionDao.delete(user);
            }
            else
                throw new LoginException("User already logged in");

        }


        if(existingAdmin.getPassword().equals(adminDto.getPassword())) {

            UserSession newSession = new UserSession();

            newSession.setUserId(existingAdmin.getAdminId());
            newSession.setUserType("admin");
            newSession.setSessionStartTime(LocalDateTime.now());
            newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));

            UUID uuid = UUID.randomUUID();
            String token = "admin_" + uuid.toString().split("-")[0];

            newSession.setToken(token);

            return sessionDao.save(newSession);
        }
        else {
            throw new LoginException("Password Incorrect. Try again.");
        }
    }


    // Method to logout a admin and delete his session token


    public SessionDto logoutAdmin(SessionDto session) {

        String token = session.getToken();

        checkTokenStatus(token);

        Optional<UserSession> opt = sessionDao.findByToken(token);

        if(!opt.isPresent())
            throw new LoginException("User not logged in. Invalid session token. Login Again.");

        UserSession user = opt.get();

        sessionDao.delete(user);

        session.setMessage("Logged out sucessfully.");

        return session;
    }


    // Method to delete expired tokens

    public void deleteExpiredTokens() {

        System.out.println("Inside delete tokens");

        List<UserSession> users = sessionDao.findAll();

        System.out.println(users);

        if(users.size() > 0) {
            for(UserSession user:users) {
                System.out.println(user.getUserId());
                LocalDateTime endTime = user.getSessionEndTime();
                if(endTime.isBefore(LocalDateTime.now())) {
                    System.out.println(user.getUserId());
                    sessionDao.delete(user);
                }
            }
        }
    }
}
