package com.maxdidato.silverbar;

import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.domain.OrderSummaryRow;
import com.maxdidato.silverbar.domain.OrderType;
import com.maxdidato.silverbar.manager.OrderManager;

import java.util.*;

public class LiveOrderBoard {
    private final OrderSummaryManager orderSummaryManager;
    private final OrderManager orderManager;

    public LiveOrderBoard() {
        // Dependency injection would be much better here. Creating the object in the constructor for simplicity
        orderManager = new OrderManager();
        orderSummaryManager = new OrderSummaryManager();
    }

    public void register(Order order) {
        //In order to track every single order we use a uuid as id
        orderManager.addOrder(order);
    }

    public List<Order> getOrders() {
        return orderManager.getAllOrders();
    }

    public void delete(UUID id) {
        orderManager.removeOrder(id);
    }

    public List<OrderSummaryRow> summary(OrderType orderType) {
        return orderSummaryManager.generate(orderType,getOrders());
    }
}
