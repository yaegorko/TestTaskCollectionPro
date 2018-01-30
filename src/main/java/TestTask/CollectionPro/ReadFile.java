package testtask.collectionpro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class ReadFile {

    Set<OrderBook> orderBookSet = new HashSet();

    String lineForParse = "Start";

    public void readLines(String filePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
               parser(line);
            }
        } catch (IOException e) {

        }
    }

    private Order parser(String line) {
        Order order;
        if (line.contains("AddOrder")) {
           order = addOrderParser(line);
        } else if (line.contains("DeleteOrder")) {
           order = deleteOrderParser(line);
        } else if (line.contains("version") || line.contains("Orders")) {
            return new DeleteOrder("0", 0); //костыль
        } else {
            throw new RuntimeException();
        }
        return order;
    }

    private Order addOrderParser(String line) {
        Order order;
        this.lineForParse = line;

        String book = readParameterFromLine("book=", lineForParse);
        String operation = readParameterFromLine("operation=", lineForParse);
        Double price = Double.parseDouble(readParameterFromLine("price=", lineForParse));
        int volume = Integer.parseInt(readParameterFromLine("volume=", lineForParse));
        int orderId = Integer.parseInt(readParameterFromLine("orderId=", lineForParse));

        isItNewBook(book);

        if (operation.contains("BUY")) {
            order = createBuyOrder(book, price, volume, orderId);
        } else if ((operation.contains("SELL"))) {
            order = createSellOrder(book, price, volume, orderId);
        } else {
            throw new RuntimeException();
        }
        return order;
    }

    private Order deleteOrderParser(String line) {
        this.lineForParse = line;
        String book = readParameterFromLine("book=", lineForParse);
        int orderId = Integer.parseInt(readParameterFromLine("orderId=", lineForParse));
        return new DeleteOrder(book, orderId);
    }
    
    private String readParameterFromLine(String parameter, String line) {
        if (line.contains(parameter)) {
           lineForParse = line.substring(line.indexOf(parameter));
           int firstQuotes = lineForParse.indexOf("\"");
           int secondQuotes = lineForParse.indexOf("\"", firstQuotes + 1);
           line = lineForParse.substring(firstQuotes + 1, secondQuotes);
        }
        return line;
    }

    private void isItNewBook(String book) {
        OrderBook orderBook = new OrderBook(book);
        if (!orderBookSet.contains(orderBook)) {
            orderBookSet.add(orderBook);
        }
    }

    private Order createBuyOrder(String book, double price, int volume, int orderId) {
        return new BuyOrder(book, price, volume, orderId);
    }

    private Order createSellOrder(String book, double price, int volume, int orderId) {
        return new SellOrder(book, price, volume, orderId);
    }
}
