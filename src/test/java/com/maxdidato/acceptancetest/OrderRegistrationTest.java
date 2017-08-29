package com.maxdidato.acceptancetest;


import com.maxdidato.silverbar.LiveOrderBoard;
import com.maxdidato.silverbar.domain.Order;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;


public class OrderRegistrationTest {

    @Test
    public void it_registers_an_order() {
        LiveOrderBoard liveOrderBorard = new LiveOrderBoard();
        Order order1 = new Order();
        liveOrderBorard.register(order1);
        assertThat(liveOrderBorard.getOrders(), is(Arrays.asList(order1)));
    }


}


