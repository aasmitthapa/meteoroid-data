//Aasmit Thapa
// 13 Dec 2024
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class UserInterface {

    private List<Meteorite> meteorites;
    private Scanner scanner;

    public UserInterface() {
        meteorites = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void go() {
        try {
            while (true) {
                // Display the menu
                System.out.println("Welcome to the NASA Meteorite tracking database.");
                System.out.println("Menu options:");
                System.out.println("0) Quit");
                System.out.println("1) Import meteorite data from a JSON file");
                System.out.println("2) Display the meteorite data");
                System.out.println("3) Export the meteorite data to a binary file");
                System.out.println("4) Find a meteorite by name");
                System.out.println("5) Find a meteorite by ID");
                System.out.println("6) List the largest meteorites");
                System.out.println("7) List the most recent meteorites by year");
                System.out.println("8) List the meteorite classes");
                System.out.print("Enter your choice: ");

                // Check if there's input before proceeding
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.nextLine(); // Clear invalid input
                    continue; // Skip the rest of the loop and ask for input again
                }

                // Get user choice
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 0:
                        System.out.println("Goodbye!");
                        return; // Exit the program
                    case 1:
                        importData();
                        break;
                    case 2:
                        displayData();
                        break;
                    case 3:
                        exportData();
                        break;
                    case 4:
                        findByName();
                        break;
                    case 5:
                        findById();
                        break;
                    case 6:
                        listLargestMeteorites();
                        break;
                    case 7:
                        listMostRecentMeteorites();
                        break;
                    case 8:
                        listMeteoriteClasses();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the scanner when done
            scanner.close();
        }
    }

    private void importData() {
        try {
            System.out.print("Enter the filename to import (default: meteorites.json): ");
            String filename = scanner.nextLine();
            if (filename.isEmpty()) {
                filename = "NASA_Meteorite.json"; // default file name
            }

            // Read JSON file
            String jsonData = new String(Files.readAllBytes(Paths.get(filename)));

            // Parse JSON into meteorites array
            Gson gson = new Gson();
            Meteorite[] meteoriteArray = gson.fromJson(jsonData, Meteorite[].class);

            for (Meteorite meteorite : meteoriteArray) {
                String year = meteorite.getYear();
                if (year != null && year.contains("-")) {
                    meteorite.setYear(year.split("-")[0]); 
                }
                // Handle case when year is null or empty
                if (year == null || year.isEmpty()) {
                    meteorite.setYear("Unknown"); // Set to "Unknown" if year is missing
                }
            }
            
            meteorites = Arrays.asList(meteoriteArray);
            System.out.println("Data imported successfully! Total meteorites: " + meteorites.size());
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
    }

    private void displayData() {
        if (meteorites.isEmpty()) {
            System.out.println("No data available to display.");
        } else {
            for (Meteorite meteorite : meteorites) {
                System.out.println(meteorite);
            }
        }
    }

    private void exportData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("meteorites.dat"))) {
            out.writeObject(meteorites);
            System.out.println("Data exported successfully to meteorites.dat");
        } catch (IOException e) {
            System.out.println("Error exporting data: " + e.getMessage());
        }
    }

    private void findByName() {
        System.out.print("Enter the meteorite name: ");
        String name = scanner.nextLine().toLowerCase();

        Optional<Meteorite> meteorite = meteorites.stream()
                .filter(m -> m.getName().toLowerCase().equals(name))
                .findFirst();

        if (meteorite.isPresent()) {
            System.out.println(meteorite.get().display());
        } else {
            System.out.println("No meteorite found with the name: " + name);
        }
    }

    private void findById() {
        System.out.print("Enter the meteorite ID: ");
        String id = scanner.nextLine();

        Optional<Meteorite> meteorite = meteorites.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();

        if (meteorite.isPresent()) {
            System.out.println(meteorite.get().display());
        } else {
            System.out.println("No meteorite found with ID: " + id);
        }
    }

    private void listLargestMeteorites() {
        System.out.print("How many largest meteorites to display? ");
        int count = scanner.nextInt();
        scanner.nextLine(); // consume newline

        meteorites.stream()
                .sorted(Comparator.comparingDouble(Meteorite::getMass).reversed())
                .limit(count)
                .forEach(m -> System.out.println(m.display()));
    }

    private void listMostRecentMeteorites() {
        System.out.print("How many most recent meteorites to display? ");
        int count = scanner.nextInt();
        scanner.nextLine(); // consume newline

        meteorites.stream()
                .filter(m -> m.getYear() != null && !m.getYear().equals("Unknown")) // Exclude "Unknown" years
                .sorted(Comparator.comparing(Meteorite::getYear).reversed())
                .limit(count)
                .forEach(m -> System.out.println(m.display()));
    }

    private void listMeteoriteClasses() {
        meteorites.stream()
                .collect(Collectors.groupingBy(Meteorite::getRecclass, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
