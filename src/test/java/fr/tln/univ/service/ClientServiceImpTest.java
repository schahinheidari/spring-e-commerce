/*
package fr.tln.univ.service;


import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.dao.SessionRepository;
import fr.tln.univ.exception.ClientException;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.mapper.ClientMapper;
import fr.tln.univ.service.ClientServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImpTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private LoginLogoutServiceImp loginService;
    @Mock
    private SessionRepository sessionRepository;
    @InjectMocks
    private ClientServiceImp clientServiceImp;

    Client client;
    ClientDto clientDto;

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setId(1);
        client.setEmail("email");

        clientDto = new ClientDto();
        clientDto.setId(1);
        clientDto.setPassword("new_password");
    }

    @Test
    void getAllClients() {
        int size = 0;
        int size2 = clientServiceImp.getAllClients().size();
        assertEquals(size, size2);
    }

    @Test
    void shouldClientByIdReturnAClient() {
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(clientMapper.mapClientToClientDto(client)).thenReturn(clientDto);
        ClientDto clientDto1 = clientServiceImp.getClientById(1);
        Assertions.assertEquals(clientDto1.getId(), 1);
    }
    @Test
    void removeAClientById() {
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(clientServiceImp.findByEmail("email")).thenReturn(client);
        clientServiceImp.deleteClient(1);
        Assertions.assertSame(0, clientRepository.findById(1).get().getId());
    }

    @Test
    void update() {
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);
        Client updatedClient = clientServiceImp.updateClient(client, "client_token");
        Assertions.assertEquals(1, updatedClient.getId());
    }


    @Test
    void shouldAddClientThrowExceptionWhenEmailExist() {
        when(clientRepository.findByEmail("email")).thenReturn(Optional.of(client));
        Assertions.assertThrows(ClientException.class
                , () -> clientServiceImp.addClient(client));
    }
    @Test
    void shouldAddClientSuccessfully() {
        when(clientRepository.findByEmail("email")).thenReturn(Optional.empty());
        when(clientRepository.save(client)).thenReturn(client);
        Assertions.assertEquals(1,clientServiceImp.addClient(client).getId());
    }
*/
/*    @Test
    void updateClientPassword() {
        when(clientRepository.findByEmail("email")).thenReturn(Optional.of(client)); // Return Optional.of(client)
        when(clientRepository.save(client)).thenReturn(client);
        when(clientServiceImp.updateClient(client, "client_token")).thenReturn(client);
        SessionDto sessionDto = clientServiceImp.updateClientPassword(clientDto, "client_token");
        Assertions.assertEquals("client_token", sessionDto.getToken());
        Assertions.assertEquals("Updated password and logged out. Login again with new password", sessionDto.getMessage());
    }*//*

}
*/
