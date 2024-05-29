import java.util.Scanner;

public class Resource {
    private String resourceName;

    public Resource() {}

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

    public String getResourceName() {
        return this.resourceName;
    }
}
