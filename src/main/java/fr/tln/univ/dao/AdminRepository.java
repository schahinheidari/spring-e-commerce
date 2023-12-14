package fr.tln.univ.dao;

import fr.tln.univ.entities.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminRepository {

    private List<Admin> admins = new ArrayList<>();

    public List<Admin> getAllAdmin(){
        List<Admin> list = new ArrayList<>();

        return list;
    }
    public void save(Admin admin){
        admins.add(admin);
    }

    public void update(Admin admin, String[] params){

    }

    void delete(Admin admin){
        admins.remove(admin);
    }

}
