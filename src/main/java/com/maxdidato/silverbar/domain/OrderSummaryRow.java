package com.maxdidato.silverbar.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.math.BigDecimal;

@Data
@Wither
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderSummaryRow {

    private BigDecimal price;
    private double kilos;

    public String toString(){
        return String.format("\n%s kg  £%s\n",kilos,price);
    }
}
