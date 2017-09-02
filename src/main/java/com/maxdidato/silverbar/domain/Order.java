package com.maxdidato.silverbar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
public class Order {
    //Id is used only for DBO object. For simplicity I will keep it in the same object
    private UUID id;
    private String userId;
    private double kilos;
    //Assuming price in sterling so no Currency object is required.
    //BigDecimal is the proper data structure for currencies, for floating point precision
    private BigDecimal pricePerKilos;
    private OrderType orderType;

    public String toString(){
        return String.format("\nUUID:%s USER:%s KG:%s £%s TYPE:%s\n",id,userId,kilos,pricePerKilos,orderType);
    }
}
