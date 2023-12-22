package fr.tln.univ.dao;

import fr.tln.univ.model.entities.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository underTest;

    @Test
    void findAllByName() {
    }

/*    @Test
    void findByEmail() {
        // given
        String email = "shah@gmail.com";
        Client client = new Client(1, "shahin",
                "Heidari",
                email,
                "1234");
        underTest.save(client);
        // when
        boolean expected = underTest.findByEmail(email);
        // then
        assertThat(expected).isTrue();
    }*/
}