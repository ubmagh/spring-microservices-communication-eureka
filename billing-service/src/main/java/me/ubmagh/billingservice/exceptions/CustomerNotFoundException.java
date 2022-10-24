package me.ubmagh.billingservice.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    private String customerId;
    public CustomerNotFoundException(String customerId ){
        super("Customer not found !");
        this.customerId = customerId;
    }
    public String getCustomerId() {
        return customerId;
    }
}
