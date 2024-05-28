import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
    private String eventName;
    private Date date;
    private Location location;
    private Tickets tickets;
    private List<Resource> resources;

    public Event() {
        this.resources = new ArrayList<>();
    }

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
        this.resources.add(resource);
    }

    public List<Resource> getResources() {
        return this.resources;
    }

    public void removeResource(Resource resource) {
        this.resources.remove(resource);
    }
}
