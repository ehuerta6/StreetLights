import java.util.Scanner;

/**
 * Main system controller for the traffic light simulation.
 * Provides a menu-driven interface for users to interact with the intersection.
 */
public class TrafficLightSystem {
    // The intersection being controlled by this system
    private final Intersection intersection;
    // Flag to control the main program loop
    private boolean running;

    /**
     * Creates a new traffic light system with a fresh intersection.
     * Initializes two traffic lights and sets up the intersection in normal mode.
     */
    public TrafficLightSystem() {
        // Create two traffic lights for the intersection
        TrafficLight ns = new TrafficLight("North/South", "red");
        TrafficLight ew = new TrafficLight("East/West", "red");
        // Create intersection in normal mode (not emergency)
        this.intersection = new Intersection(ns, ew, false);
        this.running = false;
    }

    /**
     * Starts the main program loop and handles user interaction.
     * Runs continuously until the user chooses to exit.
     */
    public void start() {
        running = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("Traffic Light System started.");
        System.out.println("========================================");

        // Main interaction loop
        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            // Read and process user input
            String choice = scanner.nextLine().trim();
            handleInput(choice, scanner);
        }

        // Clean up resources before exit
        scanner.close();
        System.out.println("\n========================================");
        System.out.println("Traffic Light System stopped.");
        System.out.println("========================================");
    }

    /**
     * Displays the menu options to the user.
     */
    private void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("  1. Cycle");
        System.out.println("  2. Set emergency");
        System.out.println("  3. Show status");
        System.out.println("  4. Exit");
        System.out.println();
    }

    /**
     * Processes user input and executes the corresponding action.
     */
    private void handleInput(String choice, Scanner scanner) {
        try {
            // Parse menu choice and execute appropriate action
            switch (Integer.parseInt(choice)) {
                case 1:
                    // Advance to next traffic light state
                    intersection.cycle();
                    break;
                case 2:
                    // Toggle emergency mode on/off
                    handleEmergency(scanner);
                    break;
                case 3:
                    // Display current intersection status
                    intersection.showStatus();
                    break;
                case 4:
                    // Exit the program
                    running = false;
                    break;
                default:
                    System.out.println("[ERROR] Invalid option. Please enter 1, 2, 3, or 4.");
            }
        } catch (NumberFormatException e) {
            // Handle non-numeric input gracefully
            System.out.println("[ERROR] Invalid input. Please enter a number between 1 and 4.");
        }
    }

    /**
     * Handles emergency mode activation/deactivation.
     * Prompts user for 'on' or 'off' command.
     */
    private void handleEmergency(Scanner scanner) {
        System.out.print("Enter 'on' to activate or 'off' to deactivate emergency: ");
        // Read and normalize user input
        String command = scanner.nextLine().trim().toLowerCase();
        switch (command) {
            case "on":
                // Activate emergency mode (all lights red)
                intersection.setEmergencyMode(true);
                break;
            case "off":
                // Deactivate emergency mode (resume normal cycle)
                intersection.setEmergencyMode(false);
                break;
            default:
                System.out.println("[ERROR] Invalid input. Type 'on' or 'off'.");
        }
    }
}
