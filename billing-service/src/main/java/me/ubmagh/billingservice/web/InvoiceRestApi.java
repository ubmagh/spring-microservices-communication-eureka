package me.ubmagh.billingservice.web;

import lombok.AllArgsConstructor;
import me.ubmagh.billingservice.dtos.request.InvoiceRequestDTO;
import me.ubmagh.billingservice.dtos.responses.InvoiceResponseDTO;
import me.ubmagh.billingservice.exceptions.CustomerNotFoundException;
import me.ubmagh.billingservice.exceptions.InvoiceNotFoundException;
import me.ubmagh.billingservice.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class InvoiceRestApi {

    private InvoiceService invoiceService;

    @GetMapping("/health-beat")
    public String checkHealth(){
        return "Healthy Beat @BillingSvc";
    }

    @GetMapping("/invoices/{id}")
    public InvoiceResponseDTO getInvoice( @PathVariable String invoiceId){
        InvoiceResponseDTO invoice;
        try{
            invoice = invoiceService.getInvoice(invoiceId);
        }catch (InvoiceNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Invoice with id: "+exc.getInvoiceId()+" was not found !" );
        }
        return invoice;
    }

    @GetMapping("/invoicesByCustomer/{id}")
    public List<InvoiceResponseDTO> getInvoiceByCustomer(@PathVariable String customerId){
        List<InvoiceResponseDTO> list;
        try{
            list = invoiceService.invoicesByCustomer(customerId);
        }catch ( CustomerNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Customer with id: "+exc.getCustomerId()+" was not found !" );
        }
        return list;
    }

    @PostMapping("/invoices")
    public InvoiceResponseDTO createInvoice(@RequestBody InvoiceRequestDTO invoice){
        InvoiceResponseDTO invoiceResp;
        try{
            invoiceResp = invoiceService.saveInvoice(invoice);
        }catch ( CustomerNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Customer with id: "+exc.getCustomerId()+" was not found !" );
        }
        return invoiceResp;
    }

    @GetMapping("/invoices")
    public List<InvoiceResponseDTO> getInvoices(){
        return invoiceService.getAllInvoices();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> exceptionHandler( ResponseStatusException exc){
        return new ResponseEntity<>(exc.getReason(), exc.getStatus() );
    }

}
