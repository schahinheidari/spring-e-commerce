package fr.tln.univ.service;

import fr.tln.univ.dao.AdminRepository;
import fr.tln.univ.dao.SessionRepository;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.exception.NotFoundException;
import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {

    private final AdminRepository adminRepository;
    private final LoginLogoutServiceImp loginService;
    private final SessionRepository sessionRepository;

    @Override
    public Admin add(Admin admin) {
        log.info("Adding admin: {}", admin);
        return adminRepository.save(admin);
    }

    @Override
    @Cacheable(value = "allAdmins")
    public List<Admin> getAll() {
        log.info("Getting all admins");
        return adminRepository.findAll();
    }

    @Override
    @Cacheable(value = "admins", key = "#admin.name")
    public Admin getById(Integer adminId) {
        log.info("Getting admin by ID: {}", adminId);
        Optional<Admin> admin = adminRepository.findById(adminId);
        if (admin.isEmpty()) {
            log.warn("Admin not found for ID: {}", adminId);
            throw new NotFoundException("Admin not found for this ID: " + adminId);
        }
        return admin.get();
    }

    @Override
    public Admin update(Admin admin, String token) {
        if (!token.contains("admin")) {
            log.error("Invalid session token for admin");
            throw new LoginException("Invalid session token for admin");
        }
        loginService.checkTokenStatus(token);
        try {
            getById(admin.getId());
            Admin updatedAdmin = adminRepository.save(admin);
            log.info("Admin updated: {}", updatedAdmin.getId());
            return updatedAdmin;
        } catch (NotFoundException ex) {
            log.error("Admin not found for ID: {}", admin.getId());
            throw ex;
        } catch (Exception ex) {
            log.error("Error updating admin: {}", admin.getId(), ex);
            throw ex;
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "allAdmins", allEntries = true),
            @CacheEvict(value = "admin", key = "#id")
    })
    public void deleteById(Integer id) {
        log.info("Deleting admin by ID: {}", id);
        try {
            adminRepository.deleteById(id);
            log.info("Admin deleted: {}", id);
        } catch (Exception ex) {
            log.error("Error deleting admin: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public Admin getCurrentlyLoggedInAdmin(String token) {
        if (!token.contains("admin")) {
            throw new LoginException("Invalid session token for admin");
        }
        loginService.checkTokenStatus(token);
        UserSession user = sessionRepository.findByToken(token).get();
        return adminRepository.findById(user.getUserId()).orElseThrow(()
                -> new NotFoundException("Admin not found for this ID"));
    }

    @Override
    public SessionDto updateAdminPassword(AdminDto adminDto, String token) {
        if (!token.contains("admin")) {
            throw new LoginException("Invalid session token for admin password");
        }
        loginService.checkTokenStatus(token);
        UserSession user = sessionRepository.findByToken(token).get();
        Optional<Admin> opt = adminRepository.findById(user.getUserId());
        if (opt.isEmpty())
            throw new NotFoundException("Admin does not exist");
        Admin existingAdmin = opt.get();
        existingAdmin.setPassword(adminDto.getPassword());
        adminRepository.save(existingAdmin);
        SessionDto session = new SessionDto();
        session.setToken(token);
        loginService.logout(session);
        session.setMessage("Updated password and logged out. Login again with new password");
        return session;
    }

    @Override
    public Page<Admin> paging(Pageable pageable) {
        log.info("Performing admin paging with parameters: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        try {
            Page<Admin> adminPage = adminRepository.findAll(pageable);
            log.info("Admin paging completed. Total elements: {}", adminPage.getTotalElements());
            return adminPage;
        } catch (Exception ex) {
            log.error("Error performing admin paging", ex);
            throw ex;
        }
    }

}
