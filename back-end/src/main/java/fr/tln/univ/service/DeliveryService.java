package fr.tln.univ.service;

import fr.tln.univ.dao.DeliveryDao;
import fr.tln.univ.enums.StatusLivraison;
import fr.tln.univ.exception.NotFoundException;
import fr.tln.univ.model.entities.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryDao deliveryDao;

    public List<Delivery> getAllDeliveries() {
        return deliveryDao.findAll();
    }

    public Optional<Delivery> getDeliveryById(Integer id) {
        return deliveryDao.findById(id);
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryDao.save(delivery);
    }

    public Delivery updateDelivery(Integer id, Delivery updatedDelivery) {
        if (deliveryDao.existsById(id)) {
            updatedDelivery.setId(id);
            return deliveryDao.save(updatedDelivery);
        }
        return null;
    }

    public boolean deleteDelivery(Integer id) {
        if (deliveryDao.existsById(id)) {
            deliveryDao.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Delivery> getByStatus(StatusLivraison status) {
        List<Delivery> deliveryList = deliveryDao.getByStatus(status);
        if (!deliveryList.isEmpty())
            throw new NotFoundException("No deliveries found with given status:" + status);
        return deliveryList;
    }
}

