import java.util.*;

// Stock Class
class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

// User Class
class User {
    private double balance;
    private HashMap<String, Integer> portfolio;

    public User(double balance) {
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }

    public double getBalance() {
        return balance;
    }

    public void buyStock(Stock stock, int quantity) {
        double totalCost = stock.getPrice() * quantity;

        if (totalCost > balance) {
            System.out.println("Insufficient balance!");
            return;
        }

        balance -= totalCost;
        portfolio.put(stock.getSymbol(),
                portfolio.getOrDefault(stock.getSymbol(), 0) + quantity);

        System.out.println("Stock purchased successfully!");
    }

    public void sellStock(Stock stock, int quantity) {
        String symbol = stock.getSymbol();

        if (!portfolio.containsKey(symbol) || portfolio.get(symbol) < quantity) {
            System.out.println("Not enough stocks to sell!");
            return;
        }

        balance += stock.getPrice() * quantity;
        portfolio.put(symbol, portfolio.get(symbol) - quantity);

        System.out.println("Stock sold successfully!");
    }

    public void viewPortfolio() {
        System.out.println("\n--- Portfolio ---");
        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
            return;
        }

        for (String symbol : portfolio.keySet()) {
            System.out.println(symbol + " - Quantity: " + portfolio.get(symbol));
        }

        System.out.println("Available Balance: ₹" + balance);
    }
}

// Main Class
public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Market Stocks
        ArrayList<Stock> market = new ArrayList<>();
        market.add(new Stock("TCS", 3500));
        market.add(new Stock("INFY", 1500));
        market.add(new Stock("RELIANCE", 2800));
        market.add(new Stock("HDFC", 1700));

        User user = new User(10000); // Starting balance

        int choice;

        do {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Market Data ---");
                    for (Stock s : market) {
                        System.out.println(s.getSymbol() + " - ₹" + s.getPrice());
                    }
                    break;

                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = sc.next().toUpperCase();

                    Stock buyStock = findStock(market, buySymbol);
                    if (buyStock == null) {
                        System.out.println("Stock not found!");
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();
                    user.buyStock(buyStock, buyQty);
                    break;

                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = sc.next().toUpperCase();

                    Stock sellStock = findStock(market, sellSymbol);
                    if (sellStock == null) {
                        System.out.println("Stock not found!");
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();
                    user.sellStock(sellStock, sellQty);
                    break;

                case 4:
                    user.viewPortfolio();
                    break;

                case 5:
                    System.out.println("Thank you for trading!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }

    public static Stock findStock(ArrayList<Stock> market, String symbol) {
        for (Stock s : market) {
            if (s.getSymbol().equals(symbol)) {
                return s;
            }
        }
        return null;
    }
}