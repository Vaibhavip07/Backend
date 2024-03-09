package com.invoiceprocessing.server.services;

import com.invoiceprocessing.server.dao.InvoiceDao;
import com.invoiceprocessing.server.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    InvoiceDao invoiceDao;

    @Override
    public Invoice addInvoice(Invoice invoice) {
        invoiceDao.save(invoice);
        return invoice;
    }

    @Override
    public List<Invoice> getInvoices() {
        return invoiceDao.findAll();
    }

    @Override
    public Invoice deleteInvoice(long id) {
        Invoice invoice = invoiceDao.findById(id).get();
        invoiceDao.delete(invoice);
        return invoice;
    }


    @Override
    public Invoice updateInvoice(Long id, Invoice updateInvoice) {
        Optional<Invoice> optionalInvoice = invoiceDao.findById(id);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            // Update invoice details with the updatedInvoice object
            invoice.setVendor(updateInvoice.getVendor());
            invoice.setProduct(updateInvoice.getProduct());
            invoice.setAmount(updateInvoice.getAmount());
            invoice.setDate(updateInvoice.getDate());
            invoice.setAction(updateInvoice.getAction());
            // Save the updated invoice to the repository
            return invoiceDao.save(updateInvoice);
        } else {
            throw new IllegalArgumentException("Invoice with ID " + id + " not found");
        }



    }
}
