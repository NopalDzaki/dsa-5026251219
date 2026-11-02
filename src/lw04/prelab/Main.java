package lw04.prelab;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/lw04/prelab/branches.txt");
        if (!file.exists()) {
            file = new File("branches.txt");
        }

        try {
            Scanner sc = new Scanner(file);
            int n = sc.nextInt();
            int[] branches = new int[n];
            for (int i = 0; i < n; i++) {
                branches[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = sc.nextInt();
            }
            sc.close();

            insertionSort(branches, 3);

            for (int i = 0; i < branches.length; i++) {
                System.out.print(branches[i] + (i == branches.length - 1 ? "" : " "));
            }
            System.out.println();

            for (int i = 0; i < targets.length; i++) {
                int target = targets[i];
                int index = linearSearch(branches, target);
                if (index != -1) {
                    System.out.println(target + ": FOUND at index " + index);
                } else {
                    System.out.println(target + ": NOT FOUND");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertionSort(int[] arr, int passes) {
        for (int i = 1; i <= passes && i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}

