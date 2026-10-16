import java.io.*;
import java.nio.file.*;
import java.util.*;

class Donor {
    // Module 4: Object-Oriented Modelling
    private String name;
    private String bloodGroup;
    private int age;

    // Constructor
    public Donor(String name, String bloodGroup, int age) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.age = age;
    }

    // Getters
    public String getName() { return name; }
    public String getBloodGroup() { return bloodGroup; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return "Donor [Name=" + name + ", BloodGroup=" + bloodGroup + ", Age=" + age + "]";
    }
}

class Inventory {
    private Map<String, Integer> bloodUnits = new HashMap<>();

    // Add blood units
    public void addBlood(String bloodGroup, int units) {
        bloodUnits.put(bloodGroup, bloodUnits.getOrDefault(bloodGroup, 0) + units);
    }

    // Request blood units
    public boolean requestBlood(String bloodGroup, int units) {
        int available = bloodUnits.getOrDefault(bloodGroup, 0);
        if (available >= units) {
            bloodUnits.put(bloodGroup, available - units);
            return true;
        }
        return false;
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n--- Current Blood Inventory ---");
        for (Map.Entry<String, Integer> entry : bloodUnits.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " units");
        }
    }
}

public class BloodBankSystem {
    // Module 3: Recursion Example
    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Module 1: Console I/O
        System.out.print("Enter number of donors: ");
        int n = sc.nextInt();
        sc.nextLine();

        Donor[] donors = new Donor[n];

        // Module 2: Control Flow & Loops
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for donor " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Blood Group: ");
            String bloodGroup = sc.nextLine();
            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine();
            donors[i] = new Donor(name, bloodGroup, age);
        }

        // Display donors
        System.out.println("\n--- Registered Donors ---");
        for (Donor d : donors) {
            System.out.println(d);
        }

        // Module 5: Sorting donors by name
        Arrays.sort(donors, Comparator.comparing(Donor::getName));
        System.out.println("\n--- Donors Sorted by Name ---");
        for (Donor d : donors) {
            System.out.println(d);
        }

        // Inventory management
        Inventory inventory = new Inventory();
        inventory.addBlood("A+", 5);
        inventory.addBlood("O-", 3);
        inventory.displayInventory();

        System.out.println("\nHospital requests 2 units of O-:");
        if (inventory.requestBlood("O-", 2)) {
            System.out.println("Request successful!");
        } else {
            System.out.println("Request failed. Not enough units.");
        }
        inventory.displayInventory();

        // File I/O: Save donor data
        try {
            Path file = Paths.get("donors.txt");
            BufferedWriter writer = Files.newBufferedWriter(file);
            for (Donor d : donors) {
                writer.write(d.toString());
                writer.newLine();
            }
            writer.close();
            System.out.println("\nDonor data saved to donors.txt");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
// Recursion demo
        System.out.print("\nEnter a number to calculate factorial: ");
        int num = sc.nextInt();
        System.out.println("Factorial of " + num + " = " + factorial(num));

        sc.close();
    }
}