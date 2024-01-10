package fr.tln.univ.service;

import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.model.mapper.ClientMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceImpTest {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientServiceImp clientServiceImp;

    @Test
    void getAllClients() {
        int size = 0;
        int size2 = clientServiceImp.getAllClients().size();
        assertEquals(size, size2);
    }

    @Test
    void getClientById() {
    }

    @Test
    void createClient() {
    }

    @Test
    void deleteClient() {
    }

    @Test
    void addClient() {
    }
}