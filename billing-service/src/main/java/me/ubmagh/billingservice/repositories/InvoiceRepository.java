package me.ubmagh.billingservice.repositories;

import me.ubmagh.billingservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository< Invoice, String> {
    List<Invoice> findByCusomerId( String customerId);
}
