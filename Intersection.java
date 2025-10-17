public class Intersection {
    private TrafficLight northSouthLight;
    private TrafficLight eastWestLight;
    private boolean isEmergencyActive;

    public Intersection(TrafficLight nSLight, TrafficLight eWLight, boolean emergency) {
        northSouthLight = nSLight;
        eastWestLight = eWLight;
        this.isEmergencyActive = emergency;
        initializeStartingState();
    }

    private void initializeStartingState() {
        if (!validateLights()) {
            return;
        }
        if (isEmergencyActive) {
            northSouthLight.setColor("red");
            eastWestLight.setColor("red");
        } else {
            northSouthLight.setColor("green");
            eastWestLight.setColor("red");
        }
    }

    public TrafficLight getNorthSouthLight() {
        return northSouthLight;
    }

    public void setNorthSouthLight(TrafficLight nSLight) {
        northSouthLight = nSLight;
    }

    public TrafficLight getEastWestLight() {
        return eastWestLight;
    }

    public void setEastWestLight(TrafficLight eWLight) {
        eastWestLight = eWLight;
    }

    public boolean isEmergencyActive() {
        return isEmergencyActive;
    }

    private void setEmergencyActive(boolean emergency) {
        this.isEmergencyActive = emergency;
    }

    public void cycle() {
        if (isEmergencyActive) {
            System.out.println("Cannot cycle - emergency mode is active.");
            return;
        }

        if (!validateLights()) {
            return;
        }

        String nsColor = northSouthLight.getColor();
        String ewColor = eastWestLight.getColor();

        if (nsColor.equals("green") && ewColor.equals("red")) {
            northSouthLight.setColor("yellow");
            eastWestLight.setColor("red");
        } else if (nsColor.equals("yellow") && ewColor.equals("red")) {
            northSouthLight.setColor("red");
            eastWestLight.setColor("green");
        } else if (nsColor.equals("red") && ewColor.equals("green")) {
            northSouthLight.setColor("red");
            eastWestLight.setColor("yellow");
        } else if (nsColor.equals("red") && ewColor.equals("yellow")) {
            northSouthLight.setColor("green");
            eastWestLight.setColor("red");
        } else {
            northSouthLight.setColor("green");
            eastWestLight.setColor("red");
            System.out.println("Invalid state detected. Reset to initial state.");
        }
    }

    public void setEmergencyMode(boolean on) {
        setEmergencyActive(on);

        TrafficLight ns = getNorthSouthLight();
        TrafficLight ew = getEastWestLight();

        if (ns == null || ew == null) {
            System.out.println("Cannot change emergency mode: one or more lights are not initialized.");
            return;
        }

        if (on) {
            ns.setColor("red");
            ew.setColor("red");
            System.out.println("Emergency mode activated.");
            System.out.println("All lights RED.");
        } else {
            ns.setColor("green");
            ew.setColor("red");
            System.out.println("Emergency mode deactivated.");
            System.out.println("North/South GREEN.");
            System.out.println("East/West RED.");
        }
    }

    public void showStatus() {
        if (!validateLights()) {
            return;
        }
        System.out.println("North/South Light: " + northSouthLight.getColor() + ".");
        System.out.println("East/West Light:   " + eastWestLight.getColor() + ".");
        if (isEmergencyActive) {
            System.out.println("Mode: Emergency.");
        } else {
            System.out.println("Mode: Normal.");
        }
    }

    private boolean validateLights() {
        if (northSouthLight == null || eastWestLight == null) {
            System.out.println("Error: Lights are not properly initialized.");
            return false;
        }
        return true;
    }
}
