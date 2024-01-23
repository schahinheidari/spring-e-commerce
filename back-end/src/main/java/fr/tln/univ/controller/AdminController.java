package fr.tln.univ.controller;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.Command;
import fr.tln.univ.model.entities.Product;
import fr.tln.univ.service.AdminServiceImp;
import fr.tln.univ.service.CommandServiceImp;
import fr.tln.univ.service.ProductServiceImp;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Api(tags = "Information Api")
@Tag(name = "Information")
public class AdminController {

    private final AdminServiceImp adminServiceImp;
    private final ProductServiceImp pService;
    private final CommandServiceImp cService;

    @Operation(summary = "Save Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json"
                            , schema = @Schema(implementation = AdminDto.class))})})
    @PostMapping("/save")
    //@PostMapping("/v3/api-docs/swagger-config")
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

/*
    @GetMapping("/current")
    public ResponseEntity<Admin> getLoggedInAdmin(@RequestHeader("token") String token) {
        Admin getAdmin = adminServiceImp.getCurrentlyLoggedInAdmin(token);
        return new ResponseEntity<>(getAdmin, HttpStatus.OK);
    }*/

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

    @DeleteMapping("/removeProduct/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable Integer productId){
        pService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getProductById/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId){
        Product product1 = pService.getById(productId);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Command>> getAllCommands(){
        List<Command> commandList = cService.getAll();
        return new ResponseEntity<>(commandList, HttpStatus.OK);
    }

    @GetMapping("/paging/{page}/{size}")
    public Page<Admin> paging(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return adminServiceImp.paging(pageable);
    }

}
