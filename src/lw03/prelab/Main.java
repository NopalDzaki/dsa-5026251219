package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }

    private static void problem1() {
        System.out.println("===== problem 1 =====");
        List<String> playlist = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/lw03/prelab/playlist.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(" ", 2);
                String command = parts[0];
                if (command.equals("ADD")) {
                    playlist.add(parts[1]);
                } else if (command.equals("INSERT")) {
                    String[] insertParts = parts[1].split(" ", 2);
                    int index = Integer.parseInt(insertParts[0]);
                    String song = insertParts[1];
                    playlist.add(index, song);
                } else if (command.equals("REMOVE")) {
                    playlist.remove(parts[1]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File playlist.txt not found.");
        }

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    private static void problem2() {
        System.out.println("===== problem 2 =====");
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;
        try {
            Scanner scanner = new Scanner(new File("src/lw03/prelab/participants.txt"));
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) continue;
                if (!participants.add(name)) {
                    duplicateCount++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File participants.txt not found.");
        }

        System.out.println("Unique participants: " + participants.size());
        int i = 1;
        for (String participant : participants) {
            System.out.println(i + ". " + participant);
            i++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);
    }

    private static void problem3() {
        System.out.println("===== Problem 3 =====");
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;
        try {
            Scanner scanner = new Scanner(new File("src/lw03/prelab/inventory.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(" ");
                String type = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                if (type.equals("ADD")) {
                    inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
                } else if (type.equals("SELL")) {
                    if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                        inventory.put(product, inventory.get(product) - quantity);
                    } else {
                        failedSales++;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File inventory.txt not found.");
        }

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}
