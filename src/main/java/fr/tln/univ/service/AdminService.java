package fr.tln.univ.service;

import fr.tln.univ.exception.AdminException;
import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;

import java.util.List;

public interface AdminService {
    public Admin addAdmin(Admin admin);
    public AdminDto addAdmin(AdminDto adminDto);

    public List<Admin> getAllAdmins() throws AdminException;

    public Admin getAdminById(Integer adminId)throws AdminException;

    public Admin getCurrentlyLoggedInAdmin(String token) throws AdminException;

    public SessionDto updateAdminPassword(AdminDto adminDto, String token) throws AdminException;

    public Admin updateAdmin(Admin admin, String token)throws AdminException;

    public void deleteAdminById(Integer adminId)throws AdminException;

}
