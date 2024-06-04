import java.util.Scanner;

/**
 * This class represents a resource that can be used in an event.
 */
public class Resource {
    // class attributes
    public String resourceName;

    public Resource() {}

    /**
     * Sets the name of the resource.
     * The user is prompted to enter the resource name until a non-empty string is provided.
     */
    public void setResourceName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the resource name:");
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Resource name cannot be empty. Please, try again.");
            } else {
                this.resourceName = name;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Resource: " + resourceName;
    }
}
