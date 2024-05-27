import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create a Location
        Location location = new Location();
        location.setLocationName("Stadium");
        location.setCapacity(5000);

        // Create Tickets
        Tickets tickets = new Tickets();
        tickets.setQuantity(1000);
        tickets.setPrice(50.0);

        // Create a Resource
        Resource resource = new Resource();
        resource.setResourceName("Sound System");

        // Create an Event
        Event event = new Event();
        event.setName("Concert");
        event.setDate(new Date()); // sets the date to current date and time
        event.setLocation(location);
        event.addTickets(tickets);
        event.addResource(resource);

        // Display Event details
        System.out.println("Event Name: " + event.getName());
        System.out.println("Event Date: " + event.getDate());
        System.out.println("Location: " + event.getLocation().getLocationName());
        System.out.println("Capacity: " + event.getLocation().getCapacity());
        System.out.println("Tickets Quantity: " + event.getTickets().getQuantity());
        System.out.println("Ticket Price: " + event.getTickets().getPrice());
        System.out.println("Resource: " + event.getResource().getResourceName());
    }
}
