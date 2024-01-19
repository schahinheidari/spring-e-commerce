package fr.tln.univ.service;

import fr.tln.univ.dao.AdminRepository;
import fr.tln.univ.dao.SessionRepository;
import fr.tln.univ.exception.AdminException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService{

    private final AdminRepository adminRepository;
    private final LoginLogoutServiceImp loginService;
    private final SessionRepository sessionRepository;

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    @Cacheable(value = "allAdmins")
    public List<Admin> getAllAdmins() {
        List<Admin> adminList = adminRepository.findAll();
        if(adminList.size()>0) {
            return adminList;
        }
        else throw new AdminException("No Admin Found !");
    }

    @Override
    @Cacheable(value= "admins", key="#admin.name")
    public Admin getAdminById(Integer adminId) throws AdminException{
        Optional<Admin> admin = adminRepository.findById(adminId);
        if (admin.isEmpty())
            throw new AdminException("Admin not found for this ID: " + adminId);
        return admin.get();
    }

    @Override
    public Admin updateAdmin(Admin admin, String token) {
        if (!token.contains("admin")) {
            throw new LoginException("Invalid session token for admin");
        }
        loginService.checkTokenStatus(token);
        Admin existingAdmin = adminRepository.findById(admin.getId()).orElseThrow(() -> new AdminException("Admin not found for this Id: " + admin.getId()));
        return adminRepository.save(admin);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "allAdmins", allEntries = true),
            @CacheEvict(value = "admin", key = "#id")
    })
    public void deleteAdminById(Integer id){
        adminRepository.deleteById(id);
    }

    @Override
    public Admin getCurrentlyLoggedInAdmin(String token) throws AdminException {
        if (!token.contains("admin")) {
            throw new LoginException("Invalid session token for admin");
        }
        loginService.checkTokenStatus(token);
        UserSession user = sessionRepository.findByToken(token).get();
        Admin existingAdmin = adminRepository.findById(user.getUserId()).orElseThrow(() -> new AdminException("Admin not found for this ID"));
        return existingAdmin;
    }


    // Method to update password - based on current token
    @Override
    public SessionDto updateAdminPassword(AdminDto adminDto, String token) {
        if (!token.contains("admin")) {
            throw new LoginException("Invalid session token for admin password");
        }
        loginService.checkTokenStatus(token);
        UserSession user = sessionRepository.findByToken(token).get();
        Optional<Admin> opt = adminRepository.findById(user.getUserId());
        if (opt.isEmpty())
            throw new AdminException("Admin does not exist");
        Admin existingAdmin = opt.get();
        existingAdmin.setPassword(adminDto.getPassword());
        adminRepository.save(existingAdmin);
        SessionDto session = new SessionDto();
        session.setToken(token);
        loginService.logoutAdmin(session);
        session.setMessage("Updated password and logged out. Login again with new password");
        return session;
    }
}
