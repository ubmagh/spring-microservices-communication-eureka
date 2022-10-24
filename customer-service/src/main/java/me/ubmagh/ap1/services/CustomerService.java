package me.ubmagh.ap1.services;

import me.ubmagh.ap1.dtos.requests.CustomerRequestDTO;
import me.ubmagh.ap1.dtos.responses.CustomerResponseDTO;
import me.ubmagh.ap1.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);
    public CustomerResponseDTO getCustomer( String customerId) throws CustomerNotFoundException;
    public CustomerResponseDTO updateCustomer( String customerId, CustomerRequestDTO customerRequestDTO) throws CustomerNotFoundException;
    public void deleteCustomer( String customerId);
    public List<CustomerResponseDTO> listCustomers();
}
