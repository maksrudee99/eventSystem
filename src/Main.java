import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Please select an option:");
            System.out.println("1. Display all events");
            System.out.println("2. Create a new event");
            System.out.println("3. Exit");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Display existing events
                    Event.displayEvents();
                    break;
                case 2:
                    // Create a Location
                    Location location = new Location();
                    location.setLocationName();
                    location.setCapacity();

                    // Create Tickets
                    Tickets tickets = new Tickets();
                    tickets.setQuantity();
                    tickets.setPrice();

                    String continueAdding = "yes";

                    // declare the resource List
                    List<Resource> resources;
                    resources = new ArrayList<>();

                    while (continueAdding.equalsIgnoreCase("yes")) {
                        // Create a Resource
                        Resource resource = new Resource();
                        resource.setResourceName();

                        // Add the resource to the List
                        resources.add(resource);

                        System.out.println("Do you want to add another resource? (yes/no)");
                        continueAdding = scanner.next();
                    }

                    // Create an Event
                    Event event = new Event(location, tickets, resources);
                    event.setName();
                    event.setDate();

                    // Display Event details
                    System.out.println("Event Name: " + event.eventName);
                    System.out.println("Event Date: " + event.date);
                    System.out.println("Location: " + event.location.locationName);
                    System.out.println("Capacity: " + event.location.capacity);
                    System.out.println("Tickets Quantity: " + event.tickets.quantity);
                    System.out.println("Ticket Price: " + event.tickets.price);
                    for (Resource resource : event.resources) {
                        System.out.println("Resource: " + resource.resourceName);
                    }

                    event.saveEvent();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 3);
    }
}
