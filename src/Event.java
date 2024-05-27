import java.util.Date;

public class Event {
    private String eventName;
    private Date date;
    private Location location;
    private Tickets tickets;
    private Resource resource;

    public Event() {}

    public void setName(String name) {
        this.eventName = name;
    }

    public String getName() {
        return this.eventName;
    }

    public void setDate(Date date) {
        this.date = date;
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
        this.resource = resource;
    }

    public Resource getResource() {
        return this.resource;
    }

    public void removeResource() {
        this.resource = null;
    }
}
