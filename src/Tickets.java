import java.util.Scanner;

public class Tickets {
    private int quantity;
    private double price;

    public Tickets() {}

    public void setQuantity() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the quantity of tickets:");
            if (!scanner.hasNextInt()) {
                System.out.println("Quantity must be a number. Please, try again.");
                scanner.next(); // discard the non-integer input
            } else {
                this.quantity = scanner.nextInt();
                break;
            }
        }
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setPrice() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the price of tickets:");
            if (!scanner.hasNextDouble()) {
                System.out.println("Price must be a number. Please, try again.");
                scanner.next(); // discard the non-numeric input
            } else {
                this.price = scanner.nextDouble();
                break;
            }
        }
    }

    public double getPrice() {
        return this.price;
    }
}