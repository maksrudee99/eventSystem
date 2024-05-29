import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create a Location
        Location location = new Location();
        location.setLocationName();
        location.setCapacity();

        // Create Tickets
        Tickets tickets = new Tickets();
        tickets.setQuantity();
        tickets.setPrice();

        // Create an Event
        Event event = new Event();
        event.setName();
        event.setDate();
        event.setLocation(location);
        event.addTickets(tickets);

        Scanner scanner = new Scanner(System.in);
        String continueAdding = "yes";
        while (continueAdding.equalsIgnoreCase("yes")) {
            // Create a Resource
            Resource resource = new Resource();
            resource.setResourceName();

            // Add the resource to the event
            event.addResource(resource);

            System.out.println("Do you want to add another resource? (yes/no)");
            continueAdding = scanner.nextLine();
        }

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
