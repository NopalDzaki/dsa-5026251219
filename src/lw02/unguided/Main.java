package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> food = new LinkedList<>();
        LinkedList<String[]> drink = new LinkedList<>();

        food.add(new String[] { "Bakso", "2" });
        food.add(new String[] { "Sate", "1" });
        food.add(new String[] { "Soto", "2" });

        drink.add(new String[] { "EsTeh", "4" });
        drink.add(new String[] { "EsJeruk", "2" });

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(
                Main.class.getResourceAsStream("orders.txt"));

        while (scanner.hasNext()) {
            String[] order = new String[4];
            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            order[3] = scanner.next();
            orders.add(order);
        }
        scanner.close();

        queue.addAll(orders);

        LinkedList<String[]> successful = new LinkedList<>();

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String foodReq = order[1];
            String drinkReq = order[2];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;
            String[] foodRecord = null;
            String[] drinkRecord = null;

            if (!foodReq.equals("-")) {
                foodAvailable = false;
                for (String[] f : food) {
                    if (f[0].equals(foodReq)) {
                        if (Integer.parseInt(f[1]) > 0) {
                            foodAvailable = true;
                            foodRecord = f;
                        }
                        break;
                    }
                }
            }

            if (!drinkReq.equals("-")) {
                drinkAvailable = false;
                for (String[] d : drink) {
                    if (d[0].equals(drinkReq)) {
                        if (Integer.parseInt(d[1]) > 0) {
                            drinkAvailable = true;
                            drinkRecord = d;
                        }
                        break;
                    }
                }
            }

            if (foodAvailable && drinkAvailable) {
                if (foodRecord != null) {
                    foodRecord[1] = String.valueOf(Integer.parseInt(foodRecord[1]) - 1);
                }
                if (drinkRecord != null) {
                    drinkRecord[1] = String.valueOf(Integer.parseInt(drinkRecord[1]) - 1);
                }
                successful.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== successfully processed orders ===");
        for (String[] order : successful) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println("\n=== remaining food stock ===");
        for (String[] f : food) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println("\n=== remaining drink stock ===");
        for (String[] d : drink) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println("\n=== failed orders ===");
        while (!failed.isEmpty()) {
            String[] order = failed.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}
