package fr.tln.univ.controller;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.UserSession;
import fr.tln.univ.service.AdminServiceImp;
import fr.tln.univ.service.ClientServiceImp;
import fr.tln.univ.service.LoginLogoutServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api")
public class LoginLogoutController {

    private ClientServiceImp clientServiceImp;
    private AdminServiceImp adminServiceImp;
    private LoginLogoutServiceImp loginService;

    // Handler to register a new client account
    @PostMapping(value = "/register/client", consumes = "application/json")
    public ResponseEntity<Client> registerAccount(@Valid @RequestBody Client client) {
        return new ResponseEntity<>(clientServiceImp.addClient(client), HttpStatus.CREATED);
    }

    // Handler to login a user
    @PostMapping(value = "/login/client", consumes = "application/json")
    public ResponseEntity<UserSession> loginClient(@Valid @RequestBody ClientDto clientDto){
        return new ResponseEntity<>(loginService.loginClient(clientDto), HttpStatus.ACCEPTED);
    }

    // Handler to logout a user
    @PostMapping(value = "/logout/client", consumes = "application/json")
    public ResponseEntity<SessionDto> logoutClient(@RequestBody SessionDto sessionToken){
        return new ResponseEntity<>(loginService.logoutClient(sessionToken), HttpStatus.ACCEPTED);
    }

    /*********** SELLER REGISTER LOGIN LOGOUT HANDLER ************/
    @PostMapping(value = "/register/admin", consumes = "application/json")
    public ResponseEntity<Admin> registerAdminAccount(@Valid @RequestBody Admin admin) {
        return new ResponseEntity<>(adminServiceImp.addAdmin(admin), HttpStatus.CREATED);
    }

    // Handler to login a user
    @PostMapping(value = "/login/admin", consumes = "application/json")
    public ResponseEntity<UserSession> loginAdmin(@Valid @RequestBody AdminDto adminDto){
        return new ResponseEntity<>(loginService.loginAdmin(adminDto), HttpStatus.ACCEPTED);
    }

    // Handler to logout a user
    @PostMapping(value = "/logout/admin", consumes = "application/json")
    public ResponseEntity<SessionDto> logoutAdmin(@RequestBody SessionDto sessionToken){
        return new ResponseEntity<>(loginService.logoutAdmin(sessionToken), HttpStatus.ACCEPTED);
    }

}

