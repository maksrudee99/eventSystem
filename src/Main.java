import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create a Location
        Location location = new Location();

        System.out.println("Enter the location name:");
        String locationName = input.next();
        location.setLocationName(locationName);

        System.out.println("Enter the location capacity:");
        int capacity = input.nextInt();
        location.setCapacity(capacity);

        // Create Tickets
        Tickets tickets = new Tickets();

        System.out.println("Enter the quantity of tickets:");
        int quantity = input.nextInt();
        tickets.setQuantity(quantity);

        System.out.println("Enter the price of tickets:");
        double price = input.nextDouble();
        tickets.setPrice(price);

//        // Create a Resource
//        Resource resource = new Resource();
//
//        System.out.println("Enter the resource name:");
//        String resourceName = input.next();
//        resource.setResourceName(resourceName);

        // Create an Event
        Event event = new Event();

        System.out.println("Enter the event name:");
        String eventName = input.next();
        event.setName(eventName);

        event.setDate(new Date()); // sets the date to current date and time
        event.setLocation(location);
        event.addTickets(tickets);

        // Create Resources
        String addMoreResources;
        do {
            Resource resource = new Resource();

            System.out.println("Enter the resource name:");
            String resourceName = input.next();
            resource.setResourceName(resourceName);

            event.addResource(resource);

            System.out.println("Do you want to add more resources? (yes/no)");
            addMoreResources = input.next();
        } while (addMoreResources.equalsIgnoreCase("yes"));

        // Display Event details
        System.out.println("Event Name: " + event.getName());
        System.out.println("Event Date: " + event.getDate());
        System.out.println("Location: " + event.getLocation().getLocationName());
        System.out.println("Capacity: " + event.getLocation().getCapacity());
        System.out.println("Tickets Quantity: " + event.getTickets().getQuantity());
        System.out.println("Ticket Price: " + event.getTickets().getPrice());
        for (Resource resource : event.getResources()) {
            System.out.println("Resource: " + resource.getResourceName());
        }
    }
}
