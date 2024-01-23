package fr.tln.univ.controller;

import fr.tln.univ.model.entities.Invoice;
import fr.tln.univ.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invoice/v1")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/save")
    public ResponseEntity<Invoice> add(@Valid @RequestBody Invoice invoiceDto) {
        return ResponseEntity.ok(invoiceService.createInvoice(invoiceDto));
    }
    @PostMapping("/list")
    public ResponseEntity<List<Invoice>> getAll() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }
    @PostMapping("/get/{id}")
    public ResponseEntity<Optional<Invoice>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.deleteInvoice(id));
    }
    @PutMapping
    public ResponseEntity<Invoice> update(@Valid @RequestBody Invoice invoiceDto) {
        return ResponseEntity.ok(invoiceService.updateInvoice(invoiceDto.getId(), invoiceDto));
    }
}
