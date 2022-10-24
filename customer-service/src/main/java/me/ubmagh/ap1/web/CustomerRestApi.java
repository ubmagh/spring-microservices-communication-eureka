package me.ubmagh.ap1.web;

import lombok.AllArgsConstructor;
import me.ubmagh.ap1.dtos.requests.CustomerRequestDTO;
import me.ubmagh.ap1.dtos.responses.CustomerResponseDTO;
import me.ubmagh.ap1.exceptions.CustomerNotFoundException;
import me.ubmagh.ap1.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CustomerRestApi {

    private CustomerService customerService;

    @GetMapping("/health-beat")
    public String checkHealth(){
        return "Healthy Beat @CustomerSvc";
    }

    @GetMapping("/customers")
    public List<CustomerResponseDTO> customersList(){
        return this.customerService.listCustomers();
    }

    @PostMapping("/customers")
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {
        return this.customerService.saveCustomer(customerRequestDTO);
    }

    @GetMapping("/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id){
        CustomerResponseDTO customer;
        try{
            customer = this.customerService.getCustomer(id);
        }catch ( CustomerNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Customer with id: "+exc.getCustomerId()+" was not found !" );
        }
        return customer;
    }

    @PutMapping("/customers/{id}")
    public CustomerResponseDTO updateCustomer(@PathVariable String id, @RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO customer;
        try{
            customer = this.customerService.updateCustomer( id, customerRequestDTO);
        }catch ( CustomerNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Customer with id: "+exc.getCustomerId()+" was not found !" );
        }
        return customer;
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable String id){
        try{
            this.customerService.deleteCustomer( id);
        }catch ( CustomerNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Customer with id: "+exc.getCustomerId()+" was not found !" );
        }
        return id;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> exceptionHandler(ResponseStatusException exc){
        return new ResponseEntity<>(exc.getReason(), exc.getStatus() );
    }

}
