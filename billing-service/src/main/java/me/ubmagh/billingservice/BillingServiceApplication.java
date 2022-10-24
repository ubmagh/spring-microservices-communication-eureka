package me.ubmagh.billingservice;

import me.ubmagh.billingservice.dtos.request.InvoiceRequestDTO;
import me.ubmagh.billingservice.entities.Customer;
import me.ubmagh.billingservice.openfeign.CustomerRestClient;
import me.ubmagh.billingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceService invoiceService, CustomerRestClient customerRestClient){
        List<Customer> customers = customerRestClient.getCustomersList();
        return args -> {
            for ( Customer customer:customers) {
                for( int i=0; i<(Math.random()*33)%5; i++)
                    invoiceService.saveInvoice( new InvoiceRequestDTO( BigDecimal.valueOf(Math.random()*777), customer.getId() ));
            }
        };
    }
}
