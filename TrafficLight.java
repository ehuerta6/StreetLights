public class TrafficLight {
    String name; // "NorthSouth" or "EastWest"
    String color;

    public TrafficLight(String name, String color) {
        this.name = name;
        this.color = color.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color.toLowerCase();
    }

    public void setColor(String color) {
        this.color = color.toLowerCase();
        System.out.println("Traffic light " + name + " set to " + this.color);
    }

}
