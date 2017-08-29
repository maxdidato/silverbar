package com.maxdidato.silverbar.manager;

import com.maxdidato.silverbar.datalayer.KeyValueStorage;
import com.maxdidato.silverbar.domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderManager {

    KeyValueStorage keyValueStorage;

    public OrderManager() {
        keyValueStorage = new KeyValueStorage();
    }

    public UUID addOrder(Order order) {
        UUID id = UUID.randomUUID();
        keyValueStorage.addValue(id, order);
        return id;
    }

    public Order getOrder(UUID id){
        return (Order) keyValueStorage.retrieveValue(id);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(keyValueStorage.getAllValues().values());
    }


    public void removeOrder(UUID id) {

    }
}
