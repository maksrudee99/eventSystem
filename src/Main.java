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
        int option;

        while (!exit) {
            System.out.println("Please select an option:");
            System.out.println("1. Display all events");
            System.out.println("2. Create a new event");
            System.out.println("3. Select an event");
            System.out.println("4. Delete an event");
            System.out.println("5. Exit");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Display existing events
                    exit = selectAllEvents();
                    break;
                case 2:
                    // Create a new event
                    exit = createEvent();
                    break;
                case 3:
                    exit = selectEvent();
                    break;
                case 4:
                    exit = deleteEvent();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void editEvent(){
        // tbd
    }

    public static boolean deleteEvent(){
        Scanner scanner = new Scanner(System.in);
        String jdbcUrl = "jdbc:sqlite:C:\\Java\\Sqlite\\eventSystem.db";
        String eventName;

        System.out.println("Which event do yu want delete(Write the Event Name): ");
        eventName = scanner.nextLine();

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Verbindung zur Datenbank herstellen
            try (Connection connection = DriverManager.getConnection(jdbcUrl);
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Event WHERE name = ?")) {
                preparedStatement.setString(1, eventName);

                // SQL-Befehl ausführen
                preparedStatement.executeUpdate();

                System.out.println("Event " + eventName + "deleted");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete event. Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found. Make sure it is added to the classpath.");
        }

        return false;
    }

    public static boolean selectEvent() {
        Scanner scanner = new Scanner(System.in);
        String jdbcUrl = "jdbc:sqlite:C:\\Java\\Sqlite\\eventSystem.db";
        String eventName;

        System.out.println("Which event do yu want select(Write the Event Name): ");
        eventName = scanner.nextLine();

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            ObjectMapper objectMapper = new ObjectMapper();

            // Verbindung zur Datenbank herstellen
            try (Connection connection = DriverManager.getConnection(jdbcUrl);
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Event WHERE Name = ?")) {

                preparedStatement.setString(1, eventName);

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
                    List<Resource> resourceNames = objectMapper.readValue(jsonString, new TypeReference<List<Resource>>() {
                    });

                    // Liste ausgeben
                    System.out.printf("Name: %s, Date: %s, Location: %s, Capacity: %d, Tickets: %d, Price: %s, Resources: %s%n",
                            name, date, locationName, capacity, ticketsQuantity, ticketPrice, resourceNames);
                }
            } catch (IOException e) {
                System.out.println("Failed to decode JSON. Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve event. Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found. Make sure it is added to the classpath.");
        }

        return false;
    }

    public static boolean createEvent() {
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
                    List<Resource> resourceNames = objectMapper.readValue(jsonString, new TypeReference<List<Resource>>() {});

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
