package lw03.unguided;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> registeredStudents = new HashSet<>();
        Scanner regScanner = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (regScanner.hasNext()) {
            String id = regScanner.next();
            registeredStudents.add(id);
        }
        regScanner.close();

        Set<String> checkedInStudents = new HashSet<>();
        List<String> checkInResults = new ArrayList<>();
        int rejectedAttempts = 0;

        Scanner checkinScanner = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (checkinScanner.hasNext()) {
            String id = checkinScanner.next();
            if (!registeredStudents.contains(id)) {
                checkInResults.add(id + ": rejected (not registered)");
                rejectedAttempts++;
            } else if (checkedInStudents.contains(id)) {
                checkInResults.add(id + ": rejected (already checked in)");
                rejectedAttempts++;
            } else {
                checkInResults.add(id + ": checked in");
                checkedInStudents.add(id);
            }
        }
        checkinScanner.close();

        System.out.println("== eventt check in results ==");
        for (int i = 0; i < checkInResults.size(); i++) {
            System.out.println(checkInResults.get(i));
        }

        System.out.println();
        System.out.println("== final event summary ==");
        System.out.println("registered students: " + registeredStudents.size());
        System.out.println("successful check in: " + checkedInStudents.size());
        System.out.println("absent students: " + (registeredStudents.size() - checkedInStudents.size()));
        System.out.println("rejected attempts: " + rejectedAttempts);
    }
}
