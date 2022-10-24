package me.ubmagh.billingservice.services;

import lombok.AllArgsConstructor;
import me.ubmagh.billingservice.dtos.request.InvoiceRequestDTO;
import me.ubmagh.billingservice.dtos.responses.InvoiceResponseDTO;
import me.ubmagh.billingservice.entities.Customer;
import me.ubmagh.billingservice.entities.Invoice;
import me.ubmagh.billingservice.exceptions.CustomerNotFoundException;
import me.ubmagh.billingservice.exceptions.InvoiceNotFoundException;
import me.ubmagh.billingservice.mappers.InvoiceMapper;
import me.ubmagh.billingservice.openfeign.CustomerRestClient;
import me.ubmagh.billingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;


    @Override
    public InvoiceResponseDTO saveInvoice(InvoiceRequestDTO invoiceRequestDTO) throws CustomerNotFoundException {
        Invoice invoice = invoiceMapper.fromInvoiceRequestDTO( invoiceRequestDTO );
        Customer customer = customerRestClient.getCustomer(invoiceRequestDTO.getCusomerId());
        if( customer==null ){
            throw new CustomerNotFoundException( invoiceRequestDTO.getCusomerId() );
        }
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice savedInvoice = invoiceRepository.save( invoice );
        return this.getInvoice(savedInvoice.getId());
    }

    @Override
    public InvoiceResponseDTO getInvoice(String Id) throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById( Id).orElseThrow( ()-> new InvoiceNotFoundException(Id));
        Customer customer = customerRestClient.getCustomer( invoice.getCusomerId() );
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomer(String customerId) throws CustomerNotFoundException {
        Customer customer = customerRestClient.getCustomer( customerId );
        if( customer==null ){
            throw new CustomerNotFoundException( customerId );
        }
        List<Invoice> invoices = invoiceRepository.findByCusomerId(customerId);
        return invoices.stream().map(i->{
                    InvoiceResponseDTO dtoed = invoiceMapper.fromInvoice(i);
                    dtoed.setCustomer( customerRestClient.getCustomer(i.getCusomerId()) );
                    return dtoed;
                }).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> getAllInvoices(){
        return this.invoiceRepository.findAll().stream().map(i->{
            InvoiceResponseDTO dtoed = invoiceMapper.fromInvoice(i);
            dtoed.setCustomer( customerRestClient.getCustomer(i.getCusomerId()) );
            return dtoed;
        }).collect(Collectors.toList());
    }
}
