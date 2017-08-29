package com.maxdidato.acceptancetest;


import com.maxdidato.silverbar.LiveOrderBoard;
import com.maxdidato.silverbar.domain.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class OrderRegistrationTest {

    @Test
    public void it_registers_an_order() {
        LiveOrderBoard liveOrderBorard = new LiveOrderBoard();
        Order order1 = new Order().withUserId("usersId").withKilos(1).withPricePerKilos(new BigDecimal(1));
        liveOrderBorard.register(order1);
        assertThat(liveOrderBorard.getOrders(), is(Arrays.asList(order1)));
    }

    @Test
    public void it_registers_multiple_orders(){
        LiveOrderBoard liveOrderBorard = new LiveOrderBoard();
        Order order1 = new Order().withUserId("usersId").withKilos(1).withPricePerKilos(new BigDecimal(1));
        Order order2 = new Order().withUserId("usersId2").withKilos(2).withPricePerKilos(new BigDecimal(2));
        Order order3 = new Order().withUserId("usersId3").withKilos(3).withPricePerKilos(new BigDecimal(3));
        liveOrderBorard.register(order1);
        liveOrderBorard.register(order2);
        liveOrderBorard.register(order3);
        assertThat(liveOrderBorard.getOrders(), is(Arrays.asList(order1,order2,order3)));
    }



}


