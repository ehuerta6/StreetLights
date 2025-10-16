public class Intersection {
    TrafficLight NSLight;
    TrafficLight EWLight;
    boolean emergency;

    public void cycle() {
        if (emergency) {
            return; // Do not change lights during an emergency
        }

    }
}
