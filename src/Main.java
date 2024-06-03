import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;

public class Main {
    private static boolean exit = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String answer;

        while (!exit) {
            System.out.println("Hello! Please select what you want to do:");
            System.out.println("Select all Events(SELECT), Create new Event(CREATE)");
            answer = scanner.nextLine();

            switch (answer) {
                case "SELECT":
                    exit = selectAllEvents();
                    break;
                case "CREATE":
                    exit = createEvent();
                    break;
                default:
                    System.out.println("SELECT)");
            }
        }
    }

    public static boolean createEvent(){
        // Create a Location
        Location location = new Location();
        location.setLocationName();
        location.setCapacity();

        // Create Tickets
        Tickets tickets = new Tickets();
        tickets.setQuantity();
        tickets.setPrice();

        Scanner scanner = new Scanner(System.in);
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
            continueAdding = scanner.nextLine();
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

        return false;
    }

    public static void editEvent(){
        //tbd
    }

    public static void deleteEvent(){
        //tbd
    }

    public static void selectEvent(){
        //tbd
    }

    public static boolean selectAllEvents() {
        String jdbcUrl = "jdbc:sqlite:C:\\Java\\Sqlite\\eventSystem.db";
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            ObjectMapper objectMapper = new ObjectMapper();

            // Verbindung zur Datenbank herstellen
            try (Connection connection = DriverManager.getConnection(jdbcUrl);
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Event")) {

                // SQL-Befehl ausführen
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    Date date = resultSet.getDate("date");
                    String locationName = resultSet.getString("location_name");
                    int capacity = resultSet.getInt("capacity");
                    int ticketsQuantity = resultSet.getInt("tickets_quantity");
                    String ticketPrice = resultSet.getString("ticket_price");
                    String jsonString = resultSet.getString("resource_name");
                    List<String> resourceNames;
                    resourceNames = objectMapper.readValue(jsonString, new TypeReference<List<Resource>>() {});

                    // Liste ausgeben
                    System.out.printf("Name: %s, Date: %s, Location: %s, Capacity: %d, Tickets: %d, Price: %s, Resources: %s%n",
                            name, date, locationName, capacity, ticketsQuantity, ticketPrice, resourceNames);
                }

                System.out.println("Events retrieved successfully.");
            } catch (IOException e) {
                System.out.println("Failed to decode JSON. Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve events. Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found. Make sure it is added to the classpath.");
        }

        return false;
    }
}
