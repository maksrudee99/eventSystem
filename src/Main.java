import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create a Location
        Location location = new Location();
        while (true) {
            System.out.println("Enter the location name:");
            String locationName = input.nextLine();

            if (locationName == null || locationName.isEmpty()) {
                System.out.println("Location name cannot be null or empty. Try again.");
                continue;
            }
            if (!locationName.matches("[A-Za-z0-9 ]*")) {
                System.out.println("Location name can only contain letters, digits and spaces. Try again.");
                continue;
            }
            location.setLocationName(locationName);
            break;
        }

//        while (true) {
//            System.out.println("Enter the location name:");
//            String locationName = input.nextLine();
//
//            if (locationName == null || locationName.isEmpty()) {
//                System.out.println("Location name cannot be null or empty. Try again.");
//                continue;
//            }
//            if (!locationName.matches("[A-Za-z0-9 ]*")) {
//                System.out.println("Location name can only contain letters, digits and spaces. Try again.");
//                continue;
//            }
//            location.setLocationName(locationName);
//            break;
//        }


        while (true) {
            System.out.println("Enter the location capacity:");
            try {
                if (!input.hasNextInt()) {
                    System.out.println("Capacity must be a number. Please, try again.");
                    input.nextLine(); // discard the non-integer input
                    continue;
                }
                int capacity = input.nextInt();
                location.setCapacity(capacity);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.nextLine(); // discard the invalid input
            }
        }

        // Create Tickets
        Tickets tickets = new Tickets();

        while (true) {
            System.out.println("Enter the quantity of tickets:");
            try {
                if (!input.hasNextInt()) {
                    System.out.println("Quantity must be a number. Please, try again.");
                    input.nextLine(); // discard the non-integer input
                    continue;
                }
                int quantity = input.nextInt();
                tickets.setQuantity(quantity);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.nextLine(); // discard the invalid input
            }
        }

        while (true) {
            System.out.println("Enter the price of tickets:");
            try {
                if (!input.hasNextDouble()) {
                    System.out.println("Price must be a number. Please, try again.");
                    input.nextLine(); // discard the non-numeric input
                    continue;
                }
                double price = input.nextDouble();
                tickets.setPrice(price);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine(); // discard the invalid input
            }
        }

        // Create a Resource
        Resource resource = new Resource();
        input.nextLine();

        while (true) {
            System.out.println("Enter the resource name:");
            String resourceName = input.nextLine();

            if (resourceName.isEmpty()) {
                System.out.println("Resource name cannot be empty. Please, try again.");
                continue;
            }
            if (!resourceName.matches("[A-Za-z0-9 ]+")) {
                System.out.println("Resource name can only contain letters, digits, and spaces. Please, try again.");
                continue;
            }

            resource.setResourceName(resourceName);

            System.out.println("Do you want to add another resource? Enter any key to continue or just press ENTER to stop.");
            String continueInput = input.nextLine();
            if (continueInput.isEmpty()) {
                break;
            }
        }


        // Create an Event
        Event event = new Event();

        while (true) {
            System.out.println("Enter the event name:");
            String eventName = input.nextLine();

            if (eventName.isEmpty()) {
                System.out.println("Event name cannot be empty. Please, try again.");
                continue;
            }
            if (!eventName.matches("[A-Za-z0-9 ]+")) {
                System.out.println("Event name can only contain letters, digits, and spaces. Please, try again.");
                continue;
            }

            event.setName(eventName);
            break;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            System.out.println("Enter the event date (dd/mm/yyyy):");
            String dateInput = input.nextLine();
            if (dateInput.isEmpty()) {
                System.out.println("Date cannot be empty. Please, try again.");
                continue;
            }

            Date eventDate;
            try {
                eventDate = dateFormat.parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please, try again.");
                continue;
            }
            event.setDate(eventDate);
            break;
        }
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
