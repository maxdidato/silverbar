package com.maxdidato.silverbar;

import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.domain.OrderSummaryRow;
import com.maxdidato.silverbar.manager.OrderManager;
import com.maxdidato.silverbar.manager.OrderSummaryManager;

import java.util.List;
import java.util.UUID;

import static com.maxdidato.silverbar.domain.OrderType.BUY;
import static com.maxdidato.silverbar.domain.OrderType.SELL;
import static java.util.Arrays.stream;

public class LiveOrderBoard {
    private final OrderSummaryManager orderSummaryManager;
    private final OrderManager orderManager;

    public LiveOrderBoard() {
        // Dependency injection would be much better here. Creating the object in the constructor for simplicity
        orderManager = new OrderManager();
        orderSummaryManager = new OrderSummaryManager();
    }

    public void register(Order... order) {
        stream(order).forEach( o -> orderManager.addOrder(o));
    }

    public List<Order> getOrders() {
        return orderManager.getAllOrders();
    }

    public void delete(UUID id) {
        orderManager.removeOrder(id);
    }

    public List<OrderSummaryRow> buyOrdersSummary() {
        return orderSummaryManager.generate(BUY,getOrders());
    }
    public List<OrderSummaryRow> sellOrdersSummary() {
        return orderSummaryManager.generate(SELL,getOrders());
    }
}
