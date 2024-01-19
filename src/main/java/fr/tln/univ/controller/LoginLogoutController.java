package fr.tln.univ.controller;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.LoginDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.Client;
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

    private final ClientServiceImp clientServiceImp;
    private final AdminServiceImp adminServiceImp;
    private final LoginLogoutServiceImp loginService;

    @PostMapping(value = "/register/client", consumes = "application/json")
    public ResponseEntity<Client> registerAccount(@Valid @RequestBody Client client) {
        return new ResponseEntity<>(clientServiceImp.addClient(client), HttpStatus.CREATED);
    }

    @PostMapping(value = "/login/client", consumes = "application/json")
    public ResponseEntity<Client> loginClient(@Valid @RequestBody LoginDto loginDto){
        return new ResponseEntity<>(loginService.loginClient(loginDto), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/logout/client", consumes = "application/json")
    public ResponseEntity<SessionDto> logoutClient(@RequestBody SessionDto sessionToken){
        return new ResponseEntity<>(loginService.logoutClient(sessionToken), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/register/admin", consumes = "application/json")
    public ResponseEntity<Admin> registerAdminAccount(@Valid @RequestBody Admin admin) {
        return new ResponseEntity<>(adminServiceImp.addAdmin(admin), HttpStatus.CREATED);
    }

    @PostMapping(value = "/login/admin", consumes = "application/json")
    public ResponseEntity<Admin> loginAdmin(@Valid @RequestBody AdminDto adminDto){
        return new ResponseEntity<>(loginService.loginAdmin(adminDto), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/logout/admin", consumes = "application/json")
    public ResponseEntity<SessionDto> logoutAdmin(@RequestBody SessionDto sessionToken){
        return new ResponseEntity<>(loginService.logoutAdmin(sessionToken), HttpStatus.ACCEPTED);
    }
}

