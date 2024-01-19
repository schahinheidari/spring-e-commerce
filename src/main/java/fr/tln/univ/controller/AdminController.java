package fr.tln.univ.controller;

import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.service.AdminServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Admin> add(@Valid @RequestBody Admin admin) {
        Admin addAdmin = adminServiceImp.add(admin);
        System.out.println("Admin" + admin);
        return new ResponseEntity<>(addAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Admin> getById(@PathVariable Integer id) {
        Admin getAdmin = adminServiceImp.getById(id);
        return new ResponseEntity<>(getAdmin, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Admin>> getAll() {
        List<Admin> admins = adminServiceImp.getAll();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    // Get currently logged in Admin
    @GetMapping("/current")
    public ResponseEntity<Admin> getLoggedInAdmin(@RequestHeader("token") String token) {
        Admin getAdmin = adminServiceImp.getCurrentlyLoggedInAdmin(token);
        return new ResponseEntity<>(getAdmin, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> update(@RequestBody Admin admin, @RequestHeader("token") String token) {
        Admin updatedAdmin = adminServiceImp.update(admin, token);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.ACCEPTED);
    }

/*    @PutMapping("/update/password")
    public ResponseEntity<SessionDto> updatePassword(@Valid @RequestBody AdminDto AdminDto, @RequestHeader("token") String token) {
        return new ResponseEntity<>(adminServiceImp.updateAdminPassword(AdminDto, token), HttpStatus.ACCEPTED);
    }*/

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        adminServiceImp.deleteById(id);
    }

    @GetMapping("/paging/{page}/{size}")
    public Page<Admin> paging(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return adminServiceImp.paging(pageable);
    }

}
