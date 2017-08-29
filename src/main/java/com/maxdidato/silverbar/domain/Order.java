package com.maxdidato.silverbar.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Order {
    private UUID id;
    private String userId;
    private float kilos;
    private BigDecimal pricePerKilos;
    private OrderType orderType;
}
