package com.example.demo1;

import java.util.Scanner;

public class FoodieFave {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        // Using a do-while loop with switch statements as the main structure of the program
        do {
            FoodQueue.displayMenu();
            userInput = scanner.next();
            scanner.nextLine();

            switch (userInput) {
                case "100":
                case "VFQ":
                    FoodQueue.viewAllQueues();
                    break;
                case "101":
                case "VEQ":
                    FoodQueue.viewAllEmptyQueues();
                    break;
                case "102":
                case "ACQ":
                    FoodQueue.addCustomerToQueue(scanner);
                    break;
                case "103":
                case "RCQ":
                    FoodQueue.removeCustomerFromQueue(scanner);
                    break;
                case "104":
                case "PCQ":
                    FoodQueue.removeServedCustomer(scanner);
                    break;
                case "105":
                case "VCS":
                    FoodQueue.viewCustomersSorted();
                    break;
                case "106":
                case "SPD":
                    FoodQueue.storeProgramData();
                    break;
                case "107":
                case "LPD":
                    FoodQueue.loadProgramData();
                    break;
                case "108":
                case "STK":
                    FoodQueue.viewRemainingStock();
                    break;
                case "109":
                case "AFS":
                    FoodQueue.addBurgersToStock(scanner);
                    break;
                case "110":
                case "IFQ":
                    FoodQueue.incomeOfEachQueue();
                    break;
                case "112":
                case "GUI":
                    System.out.println("Close the GUI page to continue with the command line.");
                    HelloApplication.launch(HelloApplication.class,args);
                    break;
                case "999":
                case "EXT":
                    System.out.println("Exiting from the program......");
                    break;
                default:
                    System.out.println("Invalid input please try again.");
                    break;
            }
            System.out.println();
        } while (!userInput.equals("EXT") && !userInput.equals("999"));
    }

}
