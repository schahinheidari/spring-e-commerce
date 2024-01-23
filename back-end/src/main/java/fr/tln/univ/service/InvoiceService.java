package fr.tln.univ.service;

import fr.tln.univ.dao.InvoiceDao;
import fr.tln.univ.model.entities.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceDao invoiceDao;

    public Invoice createInvoice(Invoice invoice) {
        return invoiceDao.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceDao.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceDao.findById(id);
    }


    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        if (invoiceDao.existsById(id)) {
            updatedInvoice.setId(id);
            return invoiceDao.save(updatedInvoice);
        }
        return null;
    }

    public boolean deleteInvoice(Long id) {
        if (invoiceDao.existsById(id)) {
            invoiceDao.deleteById(id);
            return true;
        }
        return false;
    }

    public String totalAmount(Long id) {
        return null;
    }
}

