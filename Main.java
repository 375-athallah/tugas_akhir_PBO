import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Food {
    private String name;
    private double price;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%-15s $%.2f", name, price);
    }
}

class Order {
    private Food food;
    private int quantity;

    public Order(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public double calculateTotal() {
        return food.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-15s x %d - Total: $%.2f", food.getName(), quantity, calculateTotal());
    }
}

class Restaurant {
    private List<Food> menu;
    private List<Order> orders;

    public Restaurant() {
        menu = new ArrayList<>();
        orders = new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        menu.add(new Food("Nasi Goreng", 12.99));
        menu.add(new Food("Mie Goreng", 10.99));
        menu.add(new Food("Ayam Bakar", 15.99));
        menu.add(new Food("Soto Ayam", 8.99));
        // Tambahkan menu makanan lainnya sesuai kebutuhan
    }

    public void displayWelcomeMessage() {
        System.out.println("=============================================");
        System.out.println("           Welcome to Our Restaurant          ");
        System.out.println("=============================================");
    }

    public void displayMenu() {
        System.out.println("\nMenu:");
        System.out.printf("%-5s %-15s %-10s\n", "No.", "Food Item", "Price");
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf("%-5d %s\n", (i + 1), menu.get(i));
        }
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter the number of the food item to order (0 to finish): ");
        int choice = scanner.nextInt();

        while (choice != 0) {
            if (choice >= 1 && choice <= menu.size()) {
                Food selectedFood = menu.get(choice - 1);

                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();

                Order order = new Order(selectedFood, quantity);
                orders.add(order);

                System.out.println("Added to order: " + order);
            } else {
                System.out.println("Invalid choice. Please enter a valid number.");
            }

            System.out.print("\nEnter the number of the food item to order (0 to finish): ");
            choice = scanner.nextInt();
        }
    }

    public void displayOrder() {
        System.out.println("\nOrder Details:");
        for (Order order : orders) {
            System.out.println(order);
        }

        double totalOrder = calculateTotalOrder();
        System.out.println("\nTotal Order Amount: $" + totalOrder);
    }

    private double calculateTotalOrder() {
        double totalOrder = 0.0;
        for (Order order : orders) {
            totalOrder += order.calculateTotal();
        }
        return totalOrder;
    }
}

public class RestaurantApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();

        // Menampilkan pesan sambutan
        restaurant.displayWelcomeMessage();

        // Menampilkan menu
        restaurant.displayMenu();

        // Mengambil pesanan
        restaurant.takeOrder();

        // Menampilkan rincian pesanan dan total pembayaran
        restaurant.displayOrder();

        System.out.println("\nThank you for dining at our restaurant!");
    }
}
