/**
 * Represents a single traffic light with a name and color.
 * Used to model individual lights in a traffic intersection.
 */
public class TrafficLight {
    // The direction or name of this traffic light (e.g., "North/South")
    String name;
    // The current color state: "red", "yellow", or "green"
    String color;

    /**
     * Creates a new traffic light with the specified name and initial color.
     */
    public TrafficLight(String name, String color) {
        this.name = name;
        // Store color in lowercase for consistent comparisons
        this.color = color.toLowerCase();
    }

    /**
     * Returns the name of this traffic light.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current color of this traffic light.
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the traffic light to a new color and logs the change.
     */
    public void setColor(String color) {
        // Convert to lowercase for consistency
        this.color = color.toLowerCase();
        // Log the color change for visibility
        System.out.println("[INFO] Traffic light " + name + " set to " + this.color + ".");
    }
}
