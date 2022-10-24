package me.ubmagh.billingservice.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceRequestDTO {
    private BigDecimal amount;
    private String cusomerId;
}
