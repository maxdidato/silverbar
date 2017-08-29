package com.maxdidato.silverbar;


import com.maxdidato.silverbar.domain.Order;

import java.util.*;

public class LiveOrderBoard {
    LinkedHashMap<UUID, Order> orderRegistry;

    public LiveOrderBoard() {
        //Using a LinkedHashMap to preserve the insertion order
        orderRegistry = new LinkedHashMap<>();
    }

    public void register(Order order) {
        orderRegistry.put(UUID.randomUUID(),order);
    }


    public LinkedHashMap getOrders() {
        return orderRegistry;
    }
}
