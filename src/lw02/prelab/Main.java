package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        try {
            Scanner fileScanner = new Scanner(new File("C:\\Users\\rifqy\\OneDrive\\Dokumen\\GitHub\\dsa-5026251088\\src\\lw02\\prelab\\transactions.txt"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                transactions.add(parts);

                String name = parts[0];
                boolean found = false;
                for (String[] customer : customers) {
                    if (customer[0].equals(name)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    customers.add(new String[] { name, "0" });
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("transactions.txt not found.");
            return;
        }

        Queue<String[]> transactionQueue = new LinkedList<>();
        transactionQueue.addAll(transactions);

        Stack<String[]> failedWithdrawals = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] transaction = transactionQueue.poll();
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = null;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance = balance + amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    // Insufficient balance, record as failed
                    failedWithdrawals.push(transaction);
                } else {
                    balance = balance - amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedWithdrawals.isEmpty()) {
            String[] failed = failedWithdrawals.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}