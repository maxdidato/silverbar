package com.maxdidato.acceptancetest;


import com.maxdidato.silverbar.LiveOrderBoard;
import com.maxdidato.silverbar.domain.Order;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class OrderRegistrationTest {

    private LiveOrderBoard liveOrderBorard;

    @Before
    public void setUp() {
        liveOrderBorard = new LiveOrderBoard();
    }

    @Test
    public void it_registers_an_order() {
        Order order1 = new Order().withUserId("usersId").withKilos(1).withPricePerKilos(new BigDecimal(1));
        liveOrderBorard.register(order1);
        assertThat(liveOrderBorard.getOrders(), is(Arrays.asList(order1)));
    }

    @Test
    public void it_registers_multiple_orders() {
        liveOrderBorard = new LiveOrderBoard();
        Order order1 = new Order().withUserId("usersId").withKilos(1).withPricePerKilos(new BigDecimal(1));
        Order order2 = new Order().withUserId("usersId2").withKilos(2).withPricePerKilos(new BigDecimal(2));
        Order order3 = new Order().withUserId("usersId3").withKilos(3).withPricePerKilos(new BigDecimal(3));
        liveOrderBorard.register(order1,order2,order3);
        assertThat(liveOrderBorard.getOrders(), is(Arrays.asList(order1, order2, order3)));
    }

}


