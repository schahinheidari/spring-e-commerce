package fr.tln.univ.service;

import fr.tln.univ.dao.AdminRepository;
import fr.tln.univ.dao.SessionDao;
import fr.tln.univ.exception.AdminException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Admin;
import fr.tln.univ.model.entities.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {



    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LoginLogoutService loginService;

    @Autowired
    private SessionDao sessionDao;


    
    public Admin addAdmin(Admin admin) {

        Admin add= adminRepository.save(admin);

        return add;
    }


    public List<Admin> getAllAdmins() throws AdminException {

        List<Admin> sellers= adminRepository.findAll();

        if(sellers.size()>0) {
            return sellers;
        }
        else throw new AdminException("No Admin Found !");

    }
    
    public Admin getAdminById(Integer adminId) {

        Optional<Admin> seller=adminRepository.findById(adminId);

        if(seller.isPresent()) {
            return seller.get();
        }
        else throw new AdminException("Admin not found for this ID: "+adminId);
    }
    
    public Admin updateAdmin(Admin admin, String token) {

        if(token.contains("seller") == false) {
            throw new LoginException("Invalid session token for admin");
        }

        loginService.checkTokenStatus(token);

        Admin existingAdmin=adminRepository.findById(admin.getAdminId()).orElseThrow(()-> new AdminException("Admin not found for this Id: "+admin.getAdminId()));
        Admin newSeller= adminRepository.save(admin);
        return newSeller;
    }
    
    public Admin deleteAdminById(Integer adminId, String token) {

        if(token.contains("seller") == false) {
            throw new LoginException("Invalid session token for admin");
        }

        loginService.checkTokenStatus(token);

        Optional<Admin> opt=adminRepository.findById(adminId);

        if(opt.isPresent()) {

            UserSession user = sessionDao.findByToken(token).get();

            Admin existingAdmin=opt.get();

            if(user.getUserId() == existingAdmin.getAdminId()) {
                adminRepository.delete(existingAdmin);

                // logic to log out a seller after he deletes his account
                SessionDto session = new SessionDto();
                session.setToken(token);
                loginService.logoutAdmin(session);

                return existingAdmin;
            }
            else {
                throw new AdminException("Verification Error in deleting admin account");
            }
        }
        else throw new AdminException("Seller not found for this ID: "+adminId);

    }

    public Admin getCurrentlyLoggedInAdmin(String token) throws AdminException{

        if(token.contains("admin") == false) {
            throw new LoginException("Invalid session token for admin");
        }

        loginService.checkTokenStatus(token);

        UserSession user = sessionDao.findByToken(token).get();

        Admin existingAdmin=adminRepository.findById(user.getUserId()).orElseThrow(()->new AdminException("Admin not found for this ID"));

        return existingAdmin;

    }


    // Method to update password - based on current token

    public SessionDto updateAdminPassword(AdminDto adminDto, String token) {

        if(token.contains("admin") == false) {
            throw new LoginException("Invalid session token for admin password");
        }


        loginService.checkTokenStatus(token);

        UserSession user = sessionDao.findByToken(token).get();

        Optional<Admin> opt = adminRepository.findById(user.getUserId());

        if(opt.isEmpty())
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
