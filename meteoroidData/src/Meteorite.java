//Aasmit Thapa
// 13 Dec 2024
import java.io.Serializable;

public class Meteorite implements Serializable {
    private String name;
    private String id;
    private String nametype;
    private String recclass;
    private double mass;
    private String fall;
    private String year;
    private double reclat;
    private double reclong;
    private Geolocation geolocation;

    // Constructor
    public Meteorite(String name, String id, String nametype, String recclass, 
                    double mass, String fall, String year, double reclat, 
                    double reclong, Geolocation geolocation) {
        this.name = name;
        this.id = id;
        this.nametype = nametype;
        this.recclass = recclass;
        this.mass = mass;
        this.fall = fall;
        this.year = year;
        this.reclat = reclat;
        this.reclong = reclong;
        this.geolocation = geolocation;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getNametype() {
        return nametype;
    }

    public String getRecclass() {
        return recclass;
    }

    public double getMass() {
        return mass;
    }

    public String getFall() {
        return fall;
    }

    public String getYear() {
        return year;
    }

    public double getReclat() {
        return reclat;
    }

    public double getReclong() {
        return reclong;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    // Method to display the meteorite data in a string format
    public String display() {
        return "Name: " + name + ", ID: " + id + ", Class: " + recclass + 
            ", Mass: " + mass + ", Year: " + year;
    }

    // Override the toString() method for display purposes
    @Override
    public String toString() {
        return "Meteorite{" +
            "name='" + name + '\'' +
            ", id='" + id + '\'' +
            ", nametype='" + nametype + '\'' +
            ", recclass='" + recclass + '\'' +
            ", mass=" + mass +
            ", fall='" + fall + '\'' +
            ", year='" + year + '\'' +
            ", reclat=" + reclat +
            ", reclong=" + reclong +
            ", geolocation=" + geolocation +
            '}';
    }

    // Setter for year to adjust format
    public void setYear(String year) {
        this.year = year;
    }
}
