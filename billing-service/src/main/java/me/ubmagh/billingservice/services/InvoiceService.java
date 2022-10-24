package me.ubmagh.billingservice.services;

import me.ubmagh.billingservice.dtos.request.InvoiceRequestDTO;
import me.ubmagh.billingservice.dtos.responses.InvoiceResponseDTO;
import me.ubmagh.billingservice.exceptions.CustomerNotFoundException;
import me.ubmagh.billingservice.exceptions.InvoiceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {

    InvoiceResponseDTO saveInvoice(InvoiceRequestDTO invoiceRequestDTO) throws CustomerNotFoundException;
    InvoiceResponseDTO getInvoice( String Id) throws InvoiceNotFoundException;
    List<InvoiceResponseDTO> invoicesByCustomer(String customerId) throws CustomerNotFoundException;
    List<InvoiceResponseDTO> getAllInvoices();
}
