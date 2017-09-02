package com.maxdidato.acceptancetest;


import com.maxdidato.silverbar.LiveOrderBoard;
import com.maxdidato.silverbar.domain.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrderDeletionTest {

    private LiveOrderBoard liveOrderBorard;

    @Before
    public void setUp() {
        liveOrderBorard = new LiveOrderBoard();
    }

    @Test
    public void it_removes_a_given_order() {
        //What is important here is to test the deletion of a given order
        //No additional info are added to the orders,as the id will be enough
        liveOrderBorard.register(new Order(),new Order(),new Order());
        List<Order> expectedOrders = liveOrderBorard.getOrders();
        Order orderToBeDeleted = expectedOrders.get(0);
        expectedOrders.remove(orderToBeDeleted);
        liveOrderBorard.delete(orderToBeDeleted.getId());
        assertThat(liveOrderBorard.getOrders(), is(expectedOrders));
    }


}


