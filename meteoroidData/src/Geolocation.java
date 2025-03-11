//Aasmit Thapa
// 13 Dec 2024
import java.io.Serializable;

public class Geolocation implements Serializable {
    private String type;
    private double[] coordinates;

    // Constructor
    public Geolocation(String type, double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    // Getter methods
    public String getType() { return type; }
    public double[] getCoordinates() { return coordinates; }

    @Override
    public String toString() {
        return "Geolocation{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates[0] + ", " + coordinates[1] +
                '}';
    }
}
