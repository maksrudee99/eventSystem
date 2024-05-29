import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private String eventName;
    private Date date;
    private Location location;
    private Tickets tickets;
    private final List<Resource> resources;

    public Event() {

        this.resources = new ArrayList<>();

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

    public String getName() {
        return this.eventName;
    }

    public void setDate() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        while (true) {
            System.out.println("Enter the event date (dd/mm/yyyy):");
            String dateInput = scanner.nextLine();
            if (dateInput.isEmpty()) {
                System.out.println("Date cannot be empty. Please, try again.");
            } else {
                try {
                    this.date = dateFormat.parse(dateInput);
                    break;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please, try again.");
                }
            }
        }
    }

    public Date getDate() {
        return this.date;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public void addTickets(Tickets tickets) {
        this.tickets = tickets;
    }

    public Tickets getTickets() {
        return this.tickets;
    }

    public void removeTickets() {
        this.tickets = null;
    }

    public void addResource(Resource resource) {
        this.resources.add(resource);
    }

    public List<Resource> getResources() {
        return this.resources;
    }

    public Resource getResource(int index) {
        return this.resources.get(index);
    }

    public void removeResource(Resource resource) {
        this.resources.remove(resource);
    }
}
