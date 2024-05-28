public class Location {
    private String locationName;
    private int capacity;

    public Location() {}

    public boolean setLocationName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Location name cannot be null or empty. Try again.");
            return false;
        }
        if (!name.matches("[A-Za-z0-9 ]*")) {
            System.out.println("Location name can only contain letters, digits and spaces. Try again.");
            return false;
        }
        this.locationName = name;
        return true;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public boolean setCapacity(int capacity) {
        if (capacity <= 0) {
            System.out.println("Capacity must be a positive number. Try again.");
            return false;
        }
        this.capacity = capacity;
        return true;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
