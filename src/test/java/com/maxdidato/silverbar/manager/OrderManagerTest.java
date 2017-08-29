package com.maxdidato.silverbar.manager;

import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.domain.OrderType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderManagerTest {


    private OrderManager orderManager;

    @Before
    public void setUp() {
        orderManager = new OrderManager();
    }

    @Test
    public void it_stores_a_new_order_generating_a_uuid() {
        UUID id = UUID.randomUUID();
        Order order = new Order().withOrderType(OrderType.SELL)
                .withKilos(1.2)
                .withPricePerKilos(new BigDecimal(10))
                .withUserId("user id");
        UUID uuid = orderManager.addOrder(order);
        assertThat(orderManager.getOrder(uuid), is(order));
    }


    @Test
    public void retrieve_all_order_in_list_format(){
        Order order1 = new Order().withUserId("user1").withKilos(1.2).withPricePerKilos(new BigDecimal(10));
        Order order2 = new Order().withUserId("user2").withKilos(1.3).withPricePerKilos(new BigDecimal(11));
        Order order3 = new Order().withUserId("user3").withKilos(1.4).withPricePerKilos(new BigDecimal(12));
        orderManager.addOrder(order1);
        orderManager.addOrder(order2);
        orderManager.addOrder(order3);
        assertThat(orderManager.getAllOrders(),is(Arrays.asList(order1,order2,order3)));
    }
}