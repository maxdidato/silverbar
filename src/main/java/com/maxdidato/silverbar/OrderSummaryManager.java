package com.maxdidato.silverbar;

import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.domain.OrderSummaryRow;
import com.maxdidato.silverbar.domain.OrderType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.maxdidato.silverbar.domain.OrderType.SELL;

public class OrderSummaryManager {

    public List<OrderSummaryRow> generate(List<Order> orders) {
        //using a treemap using the order price as key to apply the natural order
        TreeMap summary = new TreeMap<BigDecimal,OrderSummaryRow>();
        List<Order> filteredOrders = orders.stream().filter(o -> o.getOrderType().equals(SELL)).collect(Collectors.toList());
        filteredOrders.forEach(o -> {
            OrderSummaryRow orderSummaryRow = new OrderSummaryRow(o.getPricePerKilos(),o.getKilos());
            Object previousValue = summary.put(orderSummaryRow.getPrice(), orderSummaryRow);
            if (previousValue != null) {
                double aggregatedWeight = ((OrderSummaryRow)previousValue).getKilos() + o.getKilos();
                orderSummaryRow.setKilos(aggregatedWeight);
                summary.put(orderSummaryRow.getPrice(),orderSummaryRow);
            }
        });

        return new LinkedList<>(summary.values());

    }
}
