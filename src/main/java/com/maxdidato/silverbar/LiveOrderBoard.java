package com.maxdidato.silverbar;

import com.maxdidato.silverbar.domain.Order;
import com.maxdidato.silverbar.domain.OrderSummaryRow;
import com.maxdidato.silverbar.domain.OrderType;
import com.maxdidato.silverbar.manager.OrderManager;
import com.maxdidato.silverbar.manager.OrderSummaryManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static com.maxdidato.silverbar.domain.OrderType.BUY;
import static com.maxdidato.silverbar.domain.OrderType.SELL;
import static java.util.Arrays.sort;
import static java.util.Arrays.stream;

public class LiveOrderBoard {
    private final OrderSummaryManager orderSummaryManager;
    private final OrderManager orderManager;

    public LiveOrderBoard() {
        // Dependency injection would be much better here. Creating the object in the constructor for simplicity
        orderManager = new OrderManager();
        orderSummaryManager = new OrderSummaryManager();
    }

    public void register(Order... order) {
        stream(order).forEach(o -> orderManager.addOrder(o));
    }

    public List<Order> getOrders() {
        return orderManager.getAllOrders();
    }

    public void delete(UUID id) {
        orderManager.removeOrder(id);
    }

    public List<OrderSummaryRow> buyOrdersSummary() {
        return orderSummaryManager.generate(BUY, getOrders());
    }

    public List<OrderSummaryRow> sellOrdersSummary() {
        return orderSummaryManager.generate(SELL, getOrders());
    }


    //ROUGH IMPLEMENTATION OF A COMMAND LINE UI
    public static void main(String[] args) {
        LiveOrderBoard liveOrderBoard = new LiveOrderBoard();
        System.out.println("--------------LIVE ORDER----------------");
        String command = "";
        while (true) {
            System.out.println("OPTIONS \nO TO INSERT A NEW ORDER \nD TO DELETE ONE \nA TO SHOW ALL ORDERS \nS TO GENERATE A SUMMARY\nQ TO QUIT");
            Scanner scanner = new Scanner(System.in);
            command = scanner.nextLine();
            if (command.toLowerCase().equals("o")) {
                System.out.println("INSERT YOUR ORDER. FORMAT: TYPE USERNAME ORDER_QUANTITY PRICE\n");
                System.out.println("EXAMPLE: SELL username1 3.5 100\n");
                String order = scanner.nextLine();
                String[] orderValues = order.split(" ");
                OrderType orderType = OrderType.valueOf(orderValues[0]);
                String username = orderValues[1];
                double kilos = Double.parseDouble(orderValues[2]);
                BigDecimal price = new BigDecimal(orderValues[3]);
                liveOrderBoard.register(new Order().withUserId(username).withKilos(kilos).withOrderType(orderType).withPricePerKilos(price));
            } else if (command.toLowerCase().equals("d")) {
                System.out.println("INSERT ID OF ORDER TO DELETE");
                String uuid = scanner.nextLine();
                liveOrderBoard.delete(UUID.fromString(uuid));
            } else if (command.toLowerCase().equals("s")) {
                System.out.println("SELECT TYPE OF SUMMARY (SELL OR BUY)");
                String type = scanner.nextLine();
                if (type.equals("SELL")) {
                    System.out.println("SELL ORDER SUMMARY\n");
                    System.out.println(liveOrderBoard.sellOrdersSummary());
                } else if (type.equals("BUY")) {
                    System.out.println("BUY ORDER SUMMARY\n");
                    System.out.println(liveOrderBoard.buyOrdersSummary());
                }
            } else if (command.toLowerCase().equals("a")) {
                System.out.println("ORDER LIST");
                System.out.println(liveOrderBoard.getOrders());

            } else if (command.toLowerCase().equals("q")) {
                break;
            } else {
                System.out.println("Command not recognised");
            }
        }
    }
}
