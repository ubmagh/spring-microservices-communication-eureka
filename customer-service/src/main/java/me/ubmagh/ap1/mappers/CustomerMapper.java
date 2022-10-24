package me.ubmagh.ap1.mappers;

import me.ubmagh.ap1.dtos.requests.CustomerRequestDTO;
import me.ubmagh.ap1.dtos.responses.CustomerResponseDTO;
import me.ubmagh.ap1.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer customerRequestDtoToCustomer(CustomerRequestDTO customerReqDto);
}
