import java.util.Scanner;

/**
 * This class represents a resource that can be used in an event.
 */
public class Tickets {
    // class attributes
    public int quantity;
    public double price;

    public Tickets() {}

    /**
     * Sets the quantity of tickets.
     * The user is prompted to enter the quantity until a valid integer is provided.
     */
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

    /**
     * Sets the price of tickets.
     * The user is prompted to enter the price until a valid number is provided.
     */
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
}