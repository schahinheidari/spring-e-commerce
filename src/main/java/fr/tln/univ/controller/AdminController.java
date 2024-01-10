package fr.tln.univ.controller;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.service.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImp adminServiceImp;

    //Add Admin-------------------------------------
    @PostMapping("/save")
    public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin) {
        Admin addAdmin = adminServiceImp.addAdmin(admin);
        System.out.println("Admin" + admin);
        return new ResponseEntity<Admin>(addAdmin, HttpStatus.CREATED);
    }

    //Get the list of Admin-----------------------
    @GetMapping("/list")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminServiceImp.getAllAdmins();
        return new ResponseEntity<List<Admin>>(admins, HttpStatus.OK);
    }

    //Get the Admin by Id............................
    @GetMapping("/find/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") Integer Id) {
        Admin getAdmin = adminServiceImp.getAdminById(Id);
        return new ResponseEntity<Admin>(getAdmin, HttpStatus.OK);
    }

    // Get currently logged in Admin
    @GetMapping("/current")
    public ResponseEntity<Admin> getLoggedInAdmin(@RequestHeader("token") String token) {
        Admin getAdmin = adminServiceImp.getCurrentlyLoggedInAdmin(token);
        return new ResponseEntity<Admin>(getAdmin, HttpStatus.OK);
    }

    //Update the Admin..............................
    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @RequestHeader("token") String token) {
        Admin updatedAdmin = adminServiceImp.updateAdmin(admin, token);
        return new ResponseEntity<Admin>(updatedAdmin, HttpStatus.ACCEPTED);
    }

    //Update the Admin password..............................
    @PutMapping("/update/password")
    public ResponseEntity<SessionDto> updateAdminPassword(@Valid @RequestBody AdminDto AdminDto, @RequestHeader("token") String token) {
        return new ResponseEntity<>(adminServiceImp.updateAdminPassword(AdminDto, token), HttpStatus.ACCEPTED);
    }

    //Delete the Admin..............................
    @DeleteMapping("/delete/{id}")
    public void deleteAdminById(Integer id){
        adminServiceImp.deleteAdminById(id);
    }

}
