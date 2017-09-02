package com.maxdidato.acceptancetest;


import com.maxdidato.silverbar.LiveOrderBoard;
import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.domain.OrderSummaryRow;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.maxdidato.silverbar.domain.OrderType.SELL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


public class OrderSummaryGenerationTest {


    @Test
    public void it_aggregates_sell_order_by_price_and_sort_by_price_descending() {
        LiveOrderBoard liveOrderBorard = new LiveOrderBoard();
        Order order1 = new Order().withUserId("usersId").withKilos(3.5)
                .withPricePerKilos(new BigDecimal(306))
                .withOrderType(SELL);
        Order order2 = new Order().withUserId("usersId").withKilos(1.2)
                .withPricePerKilos(new BigDecimal(310))
                .withOrderType(SELL);
        Order order3 = new Order().withUserId("usersId").withKilos(1.5)
                .withPricePerKilos(new BigDecimal(307))
                .withOrderType(SELL);
        Order order4 = new Order().withUserId("usersId").withKilos(2.0)
                .withPricePerKilos(new BigDecimal(306))
                .withOrderType(SELL);
        liveOrderBorard.register(order1);
        liveOrderBorard.register(order2);
        liveOrderBorard.register(order3);
        liveOrderBorard.register(order4);
        List<OrderSummaryRow> orderSummary = liveOrderBorard.summary();
        //We need to preserve the insertion order so a linked list is used
        List<OrderSummaryRow> expectedOrderSummary = new LinkedList<>();
        expectedOrderSummary.add(new OrderSummaryRow().withKilos(5.5).withPrice(new BigDecimal(306)));
        expectedOrderSummary.add(new OrderSummaryRow().withKilos(1.5).withPrice(new BigDecimal(307)));
        expectedOrderSummary.add(new OrderSummaryRow().withKilos(1.2).withPrice(new BigDecimal(310)));
        assertThat(orderSummary.size(),is(expectedOrderSummary));
    }


}

