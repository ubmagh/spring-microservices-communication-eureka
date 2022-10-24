package me.ubmagh.billingservice.exceptions;

public class InvoiceNotFoundException extends RuntimeException{
    private String invoiceId;
    public InvoiceNotFoundException(String invoiceId ){
        super("Invoice not found !");
        this.invoiceId = invoiceId;
    }
    public String getInvoiceId() {
        return invoiceId;
    }
}
