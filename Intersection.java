/**
 * Represents a traffic intersection with two perpendicular traffic lights.
 * Manages the cycling of lights and emergency mode where all lights are red.
 */
public class Intersection {
    // Traffic light controlling North/South traffic flow
    private TrafficLight northSouthLight;
    // Traffic light controlling East/West traffic flow
    private TrafficLight eastWestLight;
    // Flag indicating if emergency mode is currently active
    private boolean isEmergencyActive;

    /**
     * Creates a new intersection with two traffic lights.
     */
    public Intersection(TrafficLight nSLight, TrafficLight eWLight, boolean emergency) {
        this.northSouthLight = nSLight;
        this.eastWestLight = eWLight;
        this.isEmergencyActive = emergency;
        // Set initial light configuration based on mode
        initializeStartingState();
    }

    /**
     * Initializes lights to their starting configuration.
     * Emergency mode: both red. Normal mode: NS green, EW red.
     */
    private void initializeStartingState() {
        if (!validateLights()) {
            return;
        }
        if (isEmergencyActive) {
            // Emergency: both directions red
            northSouthLight.setColor("red");
            eastWestLight.setColor("red");
        } else {
            // Normal: NS gets green, EW stays red
            northSouthLight.setColor("green");
            eastWestLight.setColor("red");
        }
    }

    /**
     * Returns the North/South traffic light.
     */
    public TrafficLight getNorthSouthLight() {
        return northSouthLight;
    }

    /**
     * Sets the North/South traffic light.
     */
    public void setNorthSouthLight(TrafficLight nSLight) {
        this.northSouthLight = nSLight;
    }

    /**
     * Returns the East/West traffic light.
     */
    public TrafficLight getEastWestLight() {
        return eastWestLight;
    }

    /**
     * Sets the East/West traffic light.
     */
    public void setEastWestLight(TrafficLight eWLight) {
        this.eastWestLight = eWLight;
    }

    /**
     * Checks if emergency mode is currently active.
     */
    public boolean isEmergencyActive() {
        return isEmergencyActive;
    }

    /**
     * Internal method to set emergency mode state.
     * Use setEmergencyMode() for public API.
     */
    private void setEmergencyActive(boolean emergency) {
        this.isEmergencyActive = emergency;
    }

    /**
     * Advances the traffic lights to the next state in the cycle.
     * Cycle sequence: NS Green → NS Yellow → EW Green → EW Yellow → repeat.
     * Blocked during emergency mode.
     */
    public void cycle() {
        // Block cycling during emergency
        if (isEmergencyActive) {
            System.out.println("[WARN] Cannot cycle - emergency mode is active.");
            return;
        }

        if (!validateLights()) {
            return;
        }

        // Read current state to determine next transition
        String nsColor = northSouthLight.getColor();
        String ewColor = eastWestLight.getColor();

        // State machine: determine next state based on current colors
        if (nsColor.equals("green") && ewColor.equals("red")) {
            // Phase 1 → 2: NS turns yellow (preparing to stop)
            northSouthLight.setColor("yellow");
            eastWestLight.setColor("red");
        } else if (nsColor.equals("yellow") && ewColor.equals("red")) {
            // Phase 2 → 3: NS stops, EW gets green
            northSouthLight.setColor("red");
            eastWestLight.setColor("green");
        } else if (nsColor.equals("red") && ewColor.equals("green")) {
            // Phase 3 → 4: EW turns yellow (preparing to stop)
            northSouthLight.setColor("red");
            eastWestLight.setColor("yellow");
        } else if (nsColor.equals("red") && ewColor.equals("yellow")) {
            // Phase 4 → 1: EW stops, NS gets green (cycle restarts)
            northSouthLight.setColor("green");
            eastWestLight.setColor("red");
        } else {
            // Invalid state: reset to known starting configuration
            northSouthLight.setColor("green");
            eastWestLight.setColor("red");
            System.out.println("[WARN] Invalid state detected. Reset to initial state.");
        }
    }

    /**
     * Activates or deactivates emergency mode.
     * Emergency on: all lights turn red.
     * Emergency off: returns to normal cycle (NS green, EW red).
     */
    public void setEmergencyMode(boolean on) {
        // Update internal emergency flag
        setEmergencyActive(on);

        TrafficLight ns = getNorthSouthLight();
        TrafficLight ew = getEastWestLight();

        // Safety check: ensure lights exist
        if (ns == null || ew == null) {
            System.out.println("[ERROR] Cannot change emergency mode: one or more lights are not initialized.");
            return;
        }

        System.out.println("----------------------------------------");
        if (on) {
            // Emergency mode: set all lights to red for safety
            ns.setColor("red");
            ew.setColor("red");
            System.out.println("[MODE] Emergency mode activated.");
            System.out.println("[INFO] All lights RED.");
        } else {
            // Exit emergency: resume normal operation
            ns.setColor("green");
            ew.setColor("red");
            System.out.println("[MODE] Emergency mode deactivated.");
            System.out.println("[INFO] North/South GREEN.");
            System.out.println("[INFO] East/West RED.");
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Displays the current state of both traffic lights and the operation mode.
     */
    public void showStatus() {
        if (!validateLights()) {
            return;
        }
        System.out.println("----------------------------------------");
        // Show current color of each direction
        System.out.println("[STATUS] North/South Light: " + northSouthLight.getColor() + ".");
        System.out.println("[STATUS] East/West Light: " + eastWestLight.getColor() + ".");
        // Indicate current operation mode
        if (isEmergencyActive) {
            System.out.println("[MODE] Emergency.");
        } else {
            System.out.println("[MODE] Normal.");
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Validates that both traffic lights are properly initialized.
     */
    private boolean validateLights() {
        if (northSouthLight == null || eastWestLight == null) {
            System.out.println("[ERROR] Lights are not properly initialized.");
            return false;
        }
        return true;
    }
}
