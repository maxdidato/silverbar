package com.maxdidato.silverbar.manager;

import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.domain.OrderSummaryRow;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.maxdidato.silverbar.domain.OrderType.BUY;
import static com.maxdidato.silverbar.domain.OrderType.SELL;
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

        List<OrderSummaryRow> orderSummaryRows = new ArrayList<>();
        orderSummaryRows.add(new OrderSummaryRow().withKilos(5).withPrice(new BigDecimal(200)));
        orderSummaryRows.add(new OrderSummaryRow().withKilos(7).withPrice(new BigDecimal(300)));
        // In this case we are not interested in order. We just want to check that the prices have been aggregated
        //correctly
        assertThat(orderSummaryManager.generate(SELL,orders).size(),is(2));
        assertThat(orderSummaryManager.generate(SELL,orders).containsAll(orderSummaryRows),is(true));

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
        //In this case the order matters so we check agains a linkedlist
        assertThat(orderSummaryManager.generate(SELL,orders),is(orderSummaryRows));
    }


    @Test
    public void it_aggregates_orders_with_buy_type_by_price() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order().withKilos(2)
                .withPricePerKilos(new BigDecimal(400)).withOrderType(BUY));
        orders.add(new Order().withKilos(3).withUserId("user1")
                .withPricePerKilos(new BigDecimal(100)).withOrderType(BUY));
        orders.add(new Order().withKilos(2).withUserId("user2")
                .withPricePerKilos(new BigDecimal(100)).withOrderType(BUY));
        orders.add(new Order().withKilos(5).withUserId("user3")
                .withPricePerKilos(new BigDecimal(400)).withOrderType(BUY));

        List<OrderSummaryRow> orderSummaryRows = new ArrayList<>();
        orderSummaryRows.add(new OrderSummaryRow().withKilos(7).withPrice(new BigDecimal(400)));
        orderSummaryRows.add(new OrderSummaryRow().withKilos(5).withPrice(new BigDecimal(100)));
        assertThat(orderSummaryManager.generate(BUY,orders).size(),is(2));
        assertThat(orderSummaryManager.generate(BUY,orders).containsAll(orderSummaryRows),is(true));
    }

    @Test
    public void it_shows_buy_orders_orderd_by_price_ascending_order() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order().withKilos(5)
                .withPricePerKilos(new BigDecimal(600)).withOrderType(BUY));
        orders.add(new Order().withKilos(6)
                .withPricePerKilos(new BigDecimal(500)).withOrderType(BUY));
        orders.add(new Order().withKilos(7)
                .withPricePerKilos(new BigDecimal(400)).withOrderType(BUY));
        orders.add(new Order().withKilos(8)
                .withPricePerKilos(new BigDecimal(300)).withOrderType(BUY));

        List<OrderSummaryRow> orderSummaryRows = new LinkedList<>();
        orderSummaryRows.add(new OrderSummaryRow().withKilos(5).withPrice(new BigDecimal(600)));
        orderSummaryRows.add(new OrderSummaryRow().withKilos(6).withPrice(new BigDecimal(500)));
        orderSummaryRows.add(new OrderSummaryRow().withKilos(7).withPrice(new BigDecimal(400)));
        orderSummaryRows.add(new OrderSummaryRow().withKilos(8).withPrice(new BigDecimal(300)));
        //In this case the order matters so we check agains a linkedlist
        assertThat(orderSummaryManager.generate(BUY,orders),is(orderSummaryRows));
    }

}