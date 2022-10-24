package me.ubmagh.ap1.services;

import lombok.AllArgsConstructor;
import me.ubmagh.ap1.dtos.requests.CustomerRequestDTO;
import me.ubmagh.ap1.dtos.responses.CustomerResponseDTO;
import me.ubmagh.ap1.entities.Customer;
import me.ubmagh.ap1.exceptions.CustomerNotFoundException;
import me.ubmagh.ap1.mappers.CustomerMapper;
import me.ubmagh.ap1.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = this.customerMapper.customerRequestDtoToCustomer(customerRequestDTO);
        customer.setId(UUID.randomUUID().toString());
        Customer savedCustomer = customerRepository.save(customer);
        return this.customerMapper.customerToCustomerResponseDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO getCustomer(String customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException(customerId));
        return this.customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO updateCustomer( String customerId, CustomerRequestDTO customerRequestDTO) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException(customerId));
        customer.setName( customerRequestDTO.getName() );
        customer.setEmail( customerRequestDTO.getEmail() );
        // saved automatically by adding transactional annotation above the class !
        return customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException(customerId));
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDTO> list = customers.stream().map(c->customerMapper.customerToCustomerResponseDTO(c)).collect(Collectors.toList());
        return list;
    }
}
