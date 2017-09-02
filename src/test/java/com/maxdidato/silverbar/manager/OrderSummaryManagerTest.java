package com.maxdidato.silverbar.manager;

import com.maxdidato.silverbar.LiveOrderBoard;
import com.maxdidato.silverbar.OrderSummaryManager;
import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.domain.OrderSummaryRow;
import com.maxdidato.silverbar.domain.OrderType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.maxdidato.silverbar.domain.OrderType.BUY;
import static com.maxdidato.silverbar.domain.OrderType.SELL;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrderSummaryManagerTest {

    private OrderSummaryManager orderSummaryManager;

    @Before
    public void setUp() {
        orderSummaryManager = new OrderSummaryManager();
    }

    @Test
    public void it_aggregates_orders_with_sell_type_by_price() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order().withKilos(2)
                .withPricePerKilos(new BigDecimal(200)).withOrderType(SELL));
        orders.add(new Order().withKilos(3).withUserId("user1")
                .withPricePerKilos(new BigDecimal(200)).withOrderType(SELL));
        orders.add(new Order().withKilos(2).withUserId("user2")
                .withPricePerKilos(new BigDecimal(300)).withOrderType(SELL));
        orders.add(new Order().withKilos(5).withUserId("user3")
                .withPricePerKilos(new BigDecimal(300)).withOrderType(SELL));

        List<OrderSummaryRow> orderSummaryRows = new LinkedList<>();
        orderSummaryRows.add(new OrderSummaryRow().withKilos(5).withPrice(new BigDecimal(200)));
        orderSummaryRows.add(new OrderSummaryRow().withKilos(7).withPrice(new BigDecimal(300)));
        assertThat(orderSummaryManager.generate(orders),is(orderSummaryRows));
    }

    @Test
    public void it_shows_sell_orders_orderd_by_price_ascending_order() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order().withKilos(2)
                .withPricePerKilos(new BigDecimal(200)).withOrderType(SELL));
        orders.add(new Order().withKilos(3)
                .withPricePerKilos(new BigDecimal(300)).withOrderType(SELL));
        orders.add(new Order().withKilos(2)
                .withPricePerKilos(new BigDecimal(400)).withOrderType(SELL));
        orders.add(new Order().withKilos(5)
                .withPricePerKilos(new BigDecimal(500)).withOrderType(SELL));

        List<OrderSummaryRow> orderSummaryRows = new LinkedList<>();
        orderSummaryRows.add(new OrderSummaryRow().withKilos(2).withPrice(new BigDecimal(200)));
        orderSummaryRows.add(new OrderSummaryRow().withKilos(3).withPrice(new BigDecimal(300)));
        orderSummaryRows.add(new OrderSummaryRow().withKilos(2).withPrice(new BigDecimal(400)));
        orderSummaryRows.add(new OrderSummaryRow().withKilos(5).withPrice(new BigDecimal(500)));
        assertThat(orderSummaryManager.generate(orders),is(orderSummaryRows));
    }


}