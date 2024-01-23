package fr.tln.univ.service;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    Admin add(Admin admin);

    List<Admin> getAll();

    Admin getById(Integer adminId);

    SessionDto updateAdminPassword(AdminDto adminDto, String token);

    Admin update(Admin admin, String token);

    void deleteById(Integer adminId);

    Page<Admin> paging(Pageable pageable);
}
