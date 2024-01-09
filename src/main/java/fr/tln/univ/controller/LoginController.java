package fr.tln.univ.controller;

import fr.tln.univ.dao.AdminRepository;
import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.UserSession;
import fr.tln.univ.service.AdminService;
import fr.tln.univ.service.ClientService;
import fr.tln.univ.service.LoginLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AdminService adminService;
    @Autowired
    private LoginLogoutService loginService;

    // Handler to register a new customer

    @PostMapping(value = "/register/client", consumes = "application/json")
    public ResponseEntity<Client> registerAccountHandler(@Valid @RequestBody Client client) {
        return new ResponseEntity<>(clientService.addClient(client), HttpStatus.CREATED);
    }

    // Handler to login a user
    @PostMapping(value = "/login/client", consumes = "application/json")
    public ResponseEntity<UserSession> loginClientHandler(@Valid @RequestBody ClientDto clientDto){
        return new ResponseEntity<>(loginService.loginClient(clientDto), HttpStatus.ACCEPTED);
    }

    // Handler to logout a user
    @PostMapping(value = "/logout/client", consumes = "application/json")
    public ResponseEntity<SessionDto> logoutClientHandler(@RequestBody SessionDto sessionToken){
        return new ResponseEntity<>(loginService.logoutClient(sessionToken), HttpStatus.ACCEPTED);
    }

    /*********** SELLER REGISTER LOGIN LOGOUT HANDLER ************/

    @PostMapping(value = "/register/admin", consumes = "application/json")
    public ResponseEntity<Admin> registerAdminAccountHandler(@Valid @RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.addAdmin(admin), HttpStatus.CREATED);
    }


    // Handler to login a user

    @PostMapping(value = "/login/admin", consumes = "application/json")
    public ResponseEntity<UserSession> loginAdminHandler(@Valid @RequestBody AdminDto adminDto){
        return new ResponseEntity<>(loginService.loginAdmin(adminDto), HttpStatus.ACCEPTED);
    }


    // Handler to logout a user

    @PostMapping(value = "/logout/admin", consumes = "application/json")
    public ResponseEntity<SessionDto> logoutAdminHandler(@RequestBody SessionDto sessionToken){
        return new ResponseEntity<>(loginService.logoutAdmin(sessionToken), HttpStatus.ACCEPTED);
    }

}

