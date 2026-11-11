package lw04.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/lw04/unguided/parcels.txt");
        if (!file.exists()) {
            file = new File("lw04/unguided/parcels.txt");
        }
        if (!file.exists()) {
            file = new File("parcels.txt");
        }

        int[] parcels;
        int[] targets;

        try (Scanner sc = new Scanner(file)) {
            int n = sc.nextInt();
            parcels = new int[n];
            for (int i = 0; i < n; i++) {
                parcels[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = sc.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        int[] afterTwoPasses = bubbleSort(parcels, 2);

        System.out.println("== After 2 Passes ==");
        printArray(afterTwoPasses);
        System.out.println();

        System.out.println("== Fully Sorted ==");
        printArray(parcels);
        System.out.println();

        System.out.println("== Search Results ==");
        for (int i = 0; i < targets.length; i++) {
            int target = targets[i];
            int[] result = binarySearch(parcels, target);
            int index = result[0];
            int steps = result[1];

            if (index != -1) {
                System.out.println(target + ": Found at index " + index + " (steps: " + steps + ")");
            } else {
                System.out.println(target + ": Not Found (steps: " + steps + ")");
            }
        }
    }

    public static int[] bubbleSort(int[] arr, int snapshotPass) {
        int[] snapshot = new int[arr.length];

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (i + 1 == snapshotPass) {
                for (int k = 0; k < arr.length; k++) {
                    snapshot[k] = arr[k];
                }
            }
        }

        return snapshot;
    }

    public static int[] binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (arr[mid] == target) {
                return new int[] { mid, steps };
            } else if (arr[mid] < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[] { -1, steps };
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i == arr.length - 1 ? "" : " "));
        }
        System.out.println();
    }
}

