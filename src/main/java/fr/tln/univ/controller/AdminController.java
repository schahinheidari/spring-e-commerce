package fr.tln.univ.controller;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.service.AdminServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/v1")
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImp adminServiceImp;

    @PostMapping("/save")
    public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin) {
        Admin addAdmin = adminServiceImp.addAdmin(admin);
        System.out.println("Admin" + admin);
        return new ResponseEntity<>(addAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer id) {
        Admin getAdmin = adminServiceImp.getAdminById(id);
        return new ResponseEntity<>(getAdmin, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminServiceImp.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    // Get currently logged in Admin
    @GetMapping("/current")
    public ResponseEntity<Admin> getLoggedInAdmin(@RequestHeader("token") String token) {
        Admin getAdmin = adminServiceImp.getCurrentlyLoggedInAdmin(token);
        return new ResponseEntity<>(getAdmin, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @RequestHeader("token") String token) {
        Admin updatedAdmin = adminServiceImp.updateAdmin(admin, token);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/password")
    public ResponseEntity<SessionDto> updateAdminPassword(@Valid @RequestBody AdminDto AdminDto, @RequestHeader("token") String token) {
        return new ResponseEntity<>(adminServiceImp.updateAdminPassword(AdminDto, token), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAdminById(@PathVariable Integer id) {
        adminServiceImp.deleteAdminById(id);
    }

}