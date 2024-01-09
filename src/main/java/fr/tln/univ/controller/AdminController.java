package fr.tln.univ.controller;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController {
    
    @Autowired
    private AdminService adminService;


    //Add Admin-------------------------------------

    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> addAdminHandler(@Valid @RequestBody Admin admin){

        Admin addAdmin=adminService.addAdmin(admin);

        System.out.println("Admin"+ admin);

        return new ResponseEntity<Admin>(addAdmin, HttpStatus.CREATED);
    }



    //Get the list of Admin-----------------------


    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdminHandler(){

        List<Admin> admins=adminService.getAllAdmins();

        return new ResponseEntity<List<Admin>>(admins, HttpStatus.OK);
    }


    //Get the Admin by Id............................


    @GetMapping("/admin/{adminId}")
    public ResponseEntity<Admin> getAdminByIdHandler(@PathVariable("adminId") Integer Id){

        Admin getAdmin=adminService.getAdminById(Id);

        return new ResponseEntity<Admin>(getAdmin, HttpStatus.OK);
    }


    // Get currently logged in Admin

    @GetMapping("/admin/current")
    public ResponseEntity<Admin> getLoggedInAdminHandler(@RequestHeader("token") String token){

        Admin getAdmin = adminService.getCurrentlyLoggedInAdmin(token);

        return new ResponseEntity<Admin>(getAdmin, HttpStatus.OK);
    }

    //Update the Admin..............................


    @PutMapping("/admin")
    public ResponseEntity<Admin> updateAdminHandler(@RequestBody Admin admin, @RequestHeader("token") String token){
        Admin updatedAdmin=adminService.updateAdmin(admin, token);

        return new ResponseEntity<Admin>(updatedAdmin,HttpStatus.ACCEPTED);

    }



    @PutMapping("/admin/update/password")
    public ResponseEntity<SessionDto> updateAdminPasswordHandler(@Valid @RequestBody AdminDto AdminDto, @RequestHeader("token") String token){
        return new ResponseEntity<>(adminService.updateAdminPassword(AdminDto, token), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/admin/{adminId}")
    public ResponseEntity<Admin> deleteAdminByIdHandler(@PathVariable("adminId") Integer Id, @RequestHeader("token") String token){

        Admin deletedAdmin=adminService.deleteAdminById(Id, token);

        return new ResponseEntity<Admin>(deletedAdmin,HttpStatus.OK);

    }


}
