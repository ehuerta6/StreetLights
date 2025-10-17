/**
 * Entry point for the Traffic Light System application.
 * Creates and starts the system.
 */
public class Main {
    /**
     * Main method that launches the traffic light simulation.
     */
    public static void main(String[] args) {
        // Create a new traffic light system
        TrafficLightSystem system = new TrafficLightSystem();
        // Start the interactive menu loop
        system.start();
    }
}
