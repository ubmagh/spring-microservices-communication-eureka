package me.ubmagh.billingservice.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {

    @Id
    private String id;
    private Date date;
    private BigDecimal amount;
    private String cusomerId;

    @Transient
    private Customer customer;
}
