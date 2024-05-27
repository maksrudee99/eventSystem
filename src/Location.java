public class Location {
    private String locationName;
    private int capacity;

    public Location() {}

    public void setLocationName(String name) {
        this.locationName = name;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }
}