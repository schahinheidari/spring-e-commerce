package fr.tln.univ.service;

import fr.tln.univ.dao.DeliveryDao;
import fr.tln.univ.enums.StatusLivraison;
import fr.tln.univ.model.dto.DeliveryDto;
import fr.tln.univ.model.entities.Delivery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {
    @Mock
    private DeliveryDao deliveryDao;
    @InjectMocks
    private DeliveryService deliveryService;

    Delivery delivery;
    DeliveryDto deliveryDto;
    List<Delivery> deliveries;
    List<DeliveryDto> deliveryDtos;
    int deliveryId;
    StatusLivraison statusLivraison;

    @BeforeEach
    void setUp() {
        deliveries = new ArrayList<>();
        delivery = new Delivery();
        deliveryDto = new DeliveryDto();
        deliveryId = 1;

        statusLivraison = StatusLivraison.EN_ATTENTE;
    }

    @Test
    void getAllDeliveries() {
        deliveries.add(delivery);
        when(deliveryDao.findAll()).thenReturn(deliveries);
        assertEquals(deliveries, deliveryService.getAllDeliveries());
    }

    @Test
    void getDeliveryById() {
        deliveryId = 1;
        when(deliveryDao.findById(deliveryId)).thenReturn(Optional.of(delivery));
        Optional<Delivery> deliveryRes = deliveryDao.findById(deliveryId);
        assertEquals(delivery, deliveryRes);
    }

    @Test
    void createDelivery() {
        when(deliveryDao.save(delivery)).thenReturn(delivery);
        assertEquals(delivery, deliveryService.createDelivery(delivery));
    }

    @Test
    void updateDelivery() {
        deliveryId = 1;
        when(deliveryDao.findById(deliveryId)).thenReturn(Optional.of(delivery));
        when(deliveryDao.save(delivery)).thenReturn(delivery);
        assertEquals(delivery, deliveryService.updateDelivery(deliveryId, delivery));
    }

    @Test
    void deleteDelivery() {
        deliveryId = 1;
        when(deliveryDao.findById(deliveryId)).thenReturn(Optional.of(delivery));
        assertTrue(deliveryService.deleteDelivery(deliveryId));
    }

    @Test
    void getDeliveryOfStatus() {
        when(deliveryDao.getByStatus(statusLivraison)).thenReturn(deliveries);
        List<Delivery> result = deliveryService.getByStatus(statusLivraison);
        assertEquals(2, result.size());
    }
}