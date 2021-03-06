package com.maxdidato.silverbar.manager;

import com.maxdidato.silverbar.datalayer.OrderDao;
import com.maxdidato.silverbar.domain.Order;

import java.util.List;
import java.util.UUID;

public class OrderManager {
    //This abstract the data layer. At the moment is implemented by a in memory key value storage.
    //If in future we want to add a database for the orderManager will be transparent
    OrderDao orderDao;

    public OrderManager() {
        orderDao = new OrderDao();
    }

    public UUID addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    public Order getOrder(UUID id) {
        return orderDao.getOrder(id);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }


    public void removeOrder(UUID id) {
        orderDao.removeOrder(id);
    }
}
