import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Event {
    public String eventName;
    public Date date;
    public final Location location;
    public final Tickets tickets;
    public final List<Resource> resources;

    public Event(Location location, Tickets tickets, List<Resource> resources) {
        this.location = location;
        this.tickets = tickets;
        this.resources = resources;
    }

    public void setName() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the event name:");
            String eventName = scanner.nextLine();
            if (eventName.isEmpty()) {
                System.out.println("Event name cannot be empty. Please, try again.");
            } else {
                this.eventName = eventName;
                break;
            }
        }
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setDate() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        while (true) {
            System.out.println("Enter the event date (dd/mm/yyyy):");
            String dateInput = scanner.nextLine();
            if (dateInput.isEmpty()) {
                System.out.println("Date cannot be empty. Please, try again.");
            } else {
                try {
                    Date inputDate = dateFormat.parse(dateInput);
                    if (inputDate.before(currentDate)) {
                        System.out.println("The event date must be in the future. Please, try again.");
                    } else {
                        this.date = inputDate;
                        break;
                    }
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please, try again.");
                }
            }
        }
    }

    public void saveEvent() {
        String jdbcUrl = "jdbc:sqlite:C:\\Java\\Sqlite\\eventSystem.db";

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Convert resources to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String resourcesJson = objectMapper.writeValueAsString(this.resources);

            // Verbindung zur Datenbank herstellen
            try (Connection connection = DriverManager.getConnection(jdbcUrl);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO Event (name, date, location_name, capacity, tickets_quantity, ticket_price, resource_name) VALUES (?, ?, ?, ?, ?, ?, ?)")
            ) {
                // Parameter setzen
                preparedStatement.setString(1, this.eventName);
                java.sql.Date sqlDate = new java.sql.Date(this.date.getTime());
                preparedStatement.setDate(2, sqlDate);
                preparedStatement.setString(3, this.location.locationName);
                preparedStatement.setInt(4, this.location.capacity);
                preparedStatement.setInt(5, this.tickets.quantity);
                preparedStatement.setDouble(6, this.tickets.price);
                preparedStatement.setString(7, resourcesJson);

                // SQL-Befehl ausführen
                preparedStatement.executeUpdate();

                System.out.println("Event saved successfully");
            }
        } catch (SQLException e) {
            System.out.println("Event not saved. Retry");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (JsonProcessingException e) {
            System.out.println("Error converting resources to JSON");
        }
    }
}
