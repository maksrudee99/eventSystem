import java.util.Scanner;

/**
 * This class represents a location where an event can be held.
 */
public class Location {
    public String locationName;
    public int capacity;

    public Location() {}

    /**
     * Sets the name of the location.
     * The user is prompted to enter the location name until a non-empty string is provided.
     */
    public void setLocationName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the location name:");
            String name = scanner.next();
            if (name.isEmpty()) {
                System.out.println("Location name cannot be empty. Please, try again.");
            } else {
                this.locationName = name;
                break;
            }
        }
    }

    /**
     * Sets the capacity of the location.
     * The user is prompted to enter the location capacity until a valid integer is provided.
     */
    public void setCapacity() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the location capacity:");
            if (!scanner.hasNextInt()) {
                System.out.println("Capacity must be a number. Please, try again.");
                scanner.next(); // discard the non-integer input
            } else {
                this.capacity = scanner.nextInt();
                break;
            }
        }
    }
}