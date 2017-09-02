package com.maxdidato.acceptancetest;


import com.maxdidato.silverbar.LiveOrderBoard;
import com.maxdidato.silverbar.OrderSummaryGenerator;
import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.domain.OrderSummaryRow;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.maxdidato.silverbar.domain.OrderType.SELL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


public class OrderSummaryGeneratorTest {


    @Test
    public void it_aggregates_same_orders_by_price() {
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
        OrderSummaryRow orderSummaryRow1 = new OrderSummaryRow().withKilos(5.5).withPrice(new BigDecimal(306));
        OrderSummaryRow orderSummaryRow2 = new OrderSummaryRow().withKilos(1.5).withPrice(new BigDecimal(307));
        OrderSummaryRow orderSummaryRow3 = new OrderSummaryRow().withKilos(1.2).withPrice(new BigDecimal(310));
        List<OrderSummaryRow> expectedOrderSummary = Arrays.asList(orderSummaryRow1,orderSummaryRow2,orderSummaryRow3);
        assertThat(orderSummary.size(), is(3));
        orderSummary.forEach(s -> assertThat(expectedOrderSummary.contains(s),is(true)));
    }


}


