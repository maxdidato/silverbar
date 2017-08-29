package com.maxdidato.silverbar;
import com.maxdidato.silverbar.datalayer.KeyValueStorage;
import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.manager.OrderManager;

import java.util.*;

public class LiveOrderBoard {
   OrderManager orderManager;

    public LiveOrderBoard() {
        // Dependency injection would be much better here. Creating the object in the constructor for simplicity
        orderManager = new OrderManager();
    }

    public void register(Order order) {
        //In order to track every single order we use a uuid as id
        orderManager.addOrder(order);
    }

    public List getOrders() {
        return orderManager.getAllOrders();
    }
}
