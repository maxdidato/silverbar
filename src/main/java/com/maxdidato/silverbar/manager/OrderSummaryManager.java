package com.maxdidato.silverbar.manager;

import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.domain.OrderSummaryRow;
import com.maxdidato.silverbar.domain.OrderType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.maxdidato.silverbar.domain.OrderType.BUY;

public class OrderSummaryManager {

    private TreeMap summary;

    public List<OrderSummaryRow> generate(OrderType orderType, List<Order> orders) {
        //using a treemap using the order price as key to apply the natural order
        summary = new TreeMap<BigDecimal, OrderSummaryRow>();
        aggregateOrderByPrice(orderType, orders);
        LinkedList orderList = new LinkedList<>(summary.values());
        if (orderType.equals(BUY)) {
            Collections.reverse(orderList);
        }
        return orderList;

    }

    private void aggregateOrderByPrice(OrderType orderType, List<Order> orders) {
        filterOrdersByType(orderType, orders).forEach(o -> {
            OrderSummaryRow newEntry = new OrderSummaryRow(o.getPricePerKilos(), o.getKilos());
            OrderSummaryRow previousEntry = (OrderSummaryRow) summary.put(newEntry.getPrice(), newEntry);
            if (previousEntry != null) {
                aggregateWeight(newEntry, previousEntry);
            }
        });
    }

    private void aggregateWeight(OrderSummaryRow newEntry, OrderSummaryRow previousEntry) {
        double aggregatedWeight = (previousEntry).getKilos() + newEntry.getKilos();
        newEntry.setKilos(aggregatedWeight);
        summary.put(newEntry.getPrice(), newEntry);
    }

    private List<Order> filterOrdersByType(OrderType orderType, List<Order> orders) {
        return orders.stream().filter(o -> o.getOrderType().equals(orderType)).collect(Collectors.toList());
    }
}
