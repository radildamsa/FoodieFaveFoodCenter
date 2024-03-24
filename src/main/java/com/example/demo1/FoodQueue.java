package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class FoodQueue {

    private static String[] cashier1Customers = {"X", "X", " ", " ", " "};
    private static String[] cashier2Customers = {"X", "X", "X", " ", " "};
    private static String[] cashier3Customers = {"X", "X", "X", "X", "X"};

    // Hash map to store Name of customer and Queue
    private static HashMap<String, Integer> queueCustomer = new HashMap<>();

    // To update the burger stock when removing customers
    static HashMap<Integer,String> queue1PositionName = new HashMap<>();
    private static HashMap<String,Integer> queue1BurgerCountName = new HashMap<>();
    private static HashMap<Integer,String> queue2PositionName = new HashMap<>();
    private static HashMap<String,Integer> queue2BurgerCountName = new HashMap<>();
    private static HashMap<Integer,String> queue3PositionName = new HashMap<>();
    private static HashMap<String,Integer> queue3BurgerCountName = new HashMap<>();

    // To get the income of queue for 110/Income of each queue
    private static int queue1CustomersBurgerCount = 0;
    private static int queue2CustomersBurgerCount = 0;
    private static int queue3CustomersBurgerCount = 0;


    // For Waiting List
    private static CircularHashMap<String, Integer> waitingList = new CircularHashMap<>();

    // Burger stock
    static int burgerStock = 50;

    // Hash maps to store position number of queue and the burgers required
    // This is used when removing customers to update the burger stock
    private static HashMap<Integer, Integer> queue1PositionBurgerCount = new HashMap<>();
    private static HashMap<Integer, Integer> queue2PositionBurgerCount = new HashMap<>();
    private static HashMap<Integer, Integer> queue3PositionBurgerCount = new HashMap<>();
    private static boolean customerAdded;

    // To add names to GUI
    public static ObservableList<String> queue1Names;
    public static ObservableList<String> queue2Names;
    public static ObservableList<String> queue3Names;

    static void displayMenu() {
        System.out.println("==== Foodies Fave Food Center ====");
        System.out.println("100/VFQ. View all Queues");
        System.out.println("101/VEQ. View all Empty Queues");
        System.out.println("102/ACQ. Add customer to a Queue");
        System.out.println("103/RCQ. Remove a customer from a Queue");
        System.out.println("104/PCQ. Remove a served customer");
        System.out.println("105/VCS. View Customers Sorted in alphabetical order");
        System.out.println("106/SPD. Store Program Data into file");
        System.out.println("107/LPD. Load Program Data from file");
        System.out.println("108/STK. View Remaining burgers Stock");
        System.out.println("109/AFS. Add burgers to Stock");
        System.out.println("110/IFQ. View income of each queue");
        System.out.println("112/GUI. View the status of queues using GUI");
        System.out.println("999/EXT. Exit the Program");
        System.out.print("Enter your requirement: ");
    }


    static void viewAllQueues() {


        System.out.println("*****************");
        System.out.println("*\tCashiers\t*");
        System.out.println("*****************");

        // The below loop is used to print each cashier queue line by line
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    cashier1Customers[i] + "\t\t" + cashier2Customers[i] + "\t\t" + cashier3Customers[i]);
        }
    }

    static void viewAllEmptyQueues(){
        System.out.println("*****************");
        System.out.println("*\tCashiers\t*");
        System.out.println("*****************");


        // New variables are created to store the availability of the cashier queues
        String[] cashier1CustomersEmpty = new String[5];
        String[] cashier2CustomersEmpty = new String[5];
        String[] cashier3CustomersEmpty = new String[5];

        // Using loops to iterate over and to find whether it is occupied or not and to enter it to the relevant variable
        for (int i = 0; i < 5; i++) {
            if (cashier1Customers[i].equals("X")){
                cashier1CustomersEmpty[i] = "X";
            }
            else if (cashier1Customers[i].equals(" ")){
                cashier1CustomersEmpty[i] = " ";
            }
            else {
                cashier1CustomersEmpty[i] = "-";
            }
        }
        for (int i = 0; i < 5; i++) {
            if (cashier2Customers[i].equals("X")){
                cashier2CustomersEmpty[i] = "X";
            }
            else if (cashier2Customers[i].equals(" ")){
                cashier2CustomersEmpty[i] = " ";
            }
            else {
                cashier2CustomersEmpty[i] = "-";
            }
        }
        for (int i = 0; i < 5; i++) {
            if (cashier3Customers[i].equals("X")){
                cashier3CustomersEmpty[i] = "X";
            }
            else {
                cashier3CustomersEmpty[i] = "-";
            }
        }
        // The below loop is used to print each cashier queue line by line
        for (int i = 0; i < 5; i++) {
            System.out.println(
                    cashier1CustomersEmpty[i] + "\t\t" + cashier2CustomersEmpty[i] + "\t\t" + cashier3CustomersEmpty[i]);
        }
        System.out.println("\nAll empty queues are represented by a X\n");
    }

    static void addCustomerToQueue(Scanner scanner) {

        boolean customerAdded = false;


        System.out.print("Enter customer's first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter customer's last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter number of burgers required: ");
        int burgersRequired = scanner.nextInt();
        Customer customer = new Customer(firstName, lastName, burgersRequired);

        while (!customerAdded) {
            for (int i = 0; i < 2; i++) {
                if (cashier1Customers[i].equals("X")) {
                    cashier1Customers[i] = "O";

                    System.out.println("\n" + customer.getFullName() + " " + " added to queue 1");


                    queueCustomer.put(customer.getFullName(), 1);

                    // To store position in queue and name
                    queue1PositionName.put(i+1, customer.getFullName());
                    // To store name and burgers required
                    // Together these two can be used to update burger stock when removing a customer
                    queue1BurgerCountName.put(customer.getFullName(), customer.getBurgersRequired());

                    // For the GUI
                    queue1Names = FXCollections.observableArrayList();
                    queue1Names.add(customer.getFullName());


                    queue1CustomersBurgerCount += customer.getBurgersRequired();
                    burgerStock -= burgersRequired;

                    if (burgerStock <= 10)
                        System.out.println("\nWARNING Burger count remaining in stock is " + burgerStock);
                    customerAdded = true;
                    break;
                }
            }
            if (customerAdded) {
                break;
            }

            for (int i = 0; i < 3; i++) {
                if (cashier2Customers[i].equals("X")) {
                    cashier2Customers[i] = "O";
                    System.out.println("\n" + customer.getFullName() + " " + " added to queue 2");

                    queueCustomer.put(customer.getFullName(), 2);

                    // To store position in queue and name
                    queue2PositionName.put(i+1, customer.getFullName());
                    // To store name and burgers required
                    // Together these two can be used to update burger stock when removing a customer
                    queue2BurgerCountName.put(customer.getFullName(), customer.getBurgersRequired());

                    // For the GUI
                    queue2Names = FXCollections.observableArrayList();
                    queue2Names.add(customer.getFullName());

                    queue2CustomersBurgerCount += customer.getBurgersRequired();

                    burgerStock -= burgersRequired;
                    if (burgerStock <= 10)
                        System.out.println("\nWARNING Burger count remaining in stock is " + burgerStock);
                    customerAdded = true;
                    break;
                }
            }
            if (customerAdded) {
                break;
            }

            for (int i = 0; i < 5; i++) {
                if (cashier3Customers[i].equals("X")) {
                    //allQueuesFull = false;
                    cashier3Customers[i] = "O";
                    System.out.println("\n" + customer.getFullName() + " " + " added to queue 3");

                    queueCustomer.put(customer.getFullName(), 3);

                    // To store position in queue and name
                    queue3PositionName.put(i+1, customer.getFullName());
                    // To store name and burgers required
                    // Together these two can be used to update burger stock when removing a customer
                    queue3BurgerCountName.put(customer.getFullName(), customer.getBurgersRequired());

                    // For the GUI
                    queue3Names = FXCollections.observableArrayList();
                    queue3Names.add(customer.getFullName());

                    queue3CustomersBurgerCount += customer.getBurgersRequired();

                    burgerStock -= burgersRequired;
                    if (burgerStock <= 10)
                        System.out.println("\nWARNING Burger count remaining in stock is " + burgerStock);
                    customerAdded = true;
                    break;
                }
            }
            if (allQueuesOccupied()){
                waitingList.put(customer.getFullName(),customer.getBurgersRequired());
                System.out.println("\n" + customer.getFullName() + " " + " added to the waiting list");
                customerAdded = true;
            }
            if (customerAdded) {
                break;
            }
        }
    }


    public static boolean allQueuesOccupied() {
        for (String item : cashier3Customers) {
            if (item.equals("X")) {
                return false;
            }
        }
        for (String item : cashier2Customers) {
            if (item.equals("X")) {
                return false;
            }
        }
        for (String item : cashier1Customers) {
            if (item.equals("X")) {
                return false;
            }
        }
        return true;
    }

    public static void namesForGUI(){
        Collection<String> valuesOfQueue1PositionName = queue1PositionName.values();
        Integer[] queue1Names = valuesOfQueue1PositionName.toArray(new Integer[0]);

        Collection<String> valuesOfQueue2PositionName = queue2PositionName.values();
        Integer[] queue2Names = valuesOfQueue2PositionName.toArray(new Integer[0]);

        Collection<String> valuesOfQueue3PositionName = queue3PositionName.values();
        Integer[] queue3Names = valuesOfQueue3PositionName.toArray(new Integer[0]);
    }


    static void removeCustomerFromQueue(Scanner scanner) {
        System.out.print("Enter the queue number of the customer that has to be removed: ");
        int queueRemoveNumber = scanner.nextInt();

        System.out.print("Enter the position of the queue of the customer that has to be removed: ");
        int queueRemovePosition = scanner.nextInt();


        if (queueRemoveNumber == 1) {
            if (cashier1Customers[queueRemovePosition - 1].equals("O")) {
                cashier1Customers[queueRemovePosition - 1] = "X";

                // Getting the name of removing customer
                String removingCustomer =  queue1PositionName.get(queueRemovePosition);

                // Getting the number of burgers required by removing customer to update the stock
                int burgersRequired = queue1BurgerCountName.get(removingCustomer);

                // Updating burger stock
                burgerStock += burgersRequired;

                // Removing the customer from the dictionaries
                queue1PositionName.remove(queueRemovePosition);
                queue1BurgerCountName.remove(removingCustomer);
                queueCustomer.remove(removingCustomer);


                System.out.println("\nCustomer " + removingCustomer +" in queue " + queueRemoveNumber + " position " + queueRemovePosition + " is removed.");

            if (!waitingList.isEmpty()){ // If there are items in the waiting list, it will be entered to the removing position
                cashier1Customers[queueRemovePosition - 1] = "O";

                Map.Entry<String, Integer> entry = waitingList.next();
                queueCustomer.put(entry.getKey(), 1);

                // To store position in queue and name
                queue1PositionName.put(queueRemovePosition, entry.getKey());
                // To store name and burgers required
                // Together these two can be used to update burger stock when removing a customer
                queue1BurgerCountName.put(entry.getKey(), entry.getValue());

                System.out.println("\n" + entry.getKey() + " " + " added to queue 1" + " position " + queueRemovePosition);

                queue1CustomersBurgerCount += entry.getValue();
                burgerStock -= entry.getValue();


                if (burgerStock <= 10)
                    System.out.println("\nWARNING Burger count remaining in stock is " + burgerStock);

                waitingList.removeFirst();
            }
            }
            else{
                System.out.println("Theres no position " + queueRemovePosition + " in queue " + queueRemoveNumber);
            }
        }
        else if (queueRemoveNumber == 2) {
            if (cashier2Customers[queueRemovePosition - 1].equals("O")) {
                cashier2Customers[queueRemovePosition - 1] = "X";

                // Getting the name of removing customer
                String removingCustomer =  queue2PositionName.get(queueRemovePosition);

                // Getting the number of burgers required by removing customer to update the stock
                int burgersRequired = queue2BurgerCountName.get(removingCustomer);

                // Updating burger stock
                burgerStock += burgersRequired;

                // Removing the customer from the dictionaries
                queue2PositionName.remove(queueRemovePosition);
                queue2BurgerCountName.remove(removingCustomer);
                queueCustomer.remove(removingCustomer);


                System.out.println("\nCustomer " + removingCustomer +" in queue " + queueRemoveNumber + " position " + queueRemovePosition + " is removed.");
            if (!waitingList.isEmpty()){
                cashier2Customers[queueRemovePosition - 1] = "O";

                Map.Entry<String, Integer> entry = waitingList.next();
                queueCustomer.put(entry.getKey(), 2);

                // To store position in queue and name
                queue1PositionName.put(queueRemovePosition, entry.getKey());
                // To store name and burgers required
                // Together these two can be used to update burger stock when removing a customer
                queue1BurgerCountName.put(entry.getKey(), entry.getValue());

                System.out.println("\n" + entry.getKey() + " " + " added to queue 2" + " position " + queueRemovePosition);

                queue1CustomersBurgerCount += entry.getValue();
                burgerStock -= entry.getValue();

                if (burgerStock <= 10)
                   System.out.println("\nWARNING Burger count remaining in stock is " + burgerStock);

                waitingList.removeFirst();
            }
            }
            else{
                System.out.println("Theres no position " + queueRemovePosition + " in queue " + queueRemoveNumber);
            }
        }
        else if (queueRemoveNumber == 3) {
            if (cashier3Customers[queueRemovePosition - 1].equals("O")) {
                cashier3Customers[queueRemovePosition - 1] = "X";

                // Getting the name of removing customer
                String removingCustomer =  queue3PositionName.get(queueRemovePosition);

                // Getting the number of burgers required by removing customer to update the stock
                int burgersRequired = queue3BurgerCountName.get(removingCustomer);

                // Updating burger stock
                burgerStock += burgersRequired;

                // Removing the customer from the dictionaries
                queue3PositionName.remove(queueRemovePosition);
                queue3BurgerCountName.remove(removingCustomer);
                queueCustomer.remove(removingCustomer);


                System.out.println("\nCustomer " + removingCustomer +" in queue " + queueRemoveNumber + " position " + queueRemovePosition + " is removed.");
            if (!waitingList.isEmpty()){
                cashier3Customers[queueRemovePosition - 1] = "O";

                Map.Entry<String, Integer> entry = waitingList.next();
                queueCustomer.put(entry.getKey(), 3);

                // To store position in queue and name
                queue1PositionName.put(queueRemovePosition, entry.getKey());
                // To store name and burgers required
                // Together these two can be used to update burger stock when removing a customer
                queue1BurgerCountName.put(entry.getKey(), entry.getValue());

                System.out.println("\n" + entry.getKey() + " " + " added to queue 3" + " position " + queueRemovePosition);

                queue1CustomersBurgerCount += entry.getValue();
                burgerStock -= entry.getValue();

                if (burgerStock <= 10)
                    System.out.println("\nWARNING Burger count remaining in stock is " + burgerStock);

                waitingList.removeFirst();
            }
            }
            else{
                System.out.println("Theres no customer " + queueRemovePosition + " in queue " + queueRemoveNumber);
            }
        }
        else{
            System.out.println("Invalid input");
        }
    }

    static void removeServedCustomer(Scanner scanner) {
        System.out.print("Enter the queue number of the customer that has to be removed: ");
        int queueRemoveNumber = scanner.nextInt();

        System.out.print("Enter the position of the queue of the customer that has to be removed: ");
        int queueRemovePosition = scanner.nextInt();


        if (queueRemoveNumber == 1) {
            if (cashier1Customers[queueRemovePosition - 1].equals("O")) {
                cashier1Customers[queueRemovePosition - 1] = "X";

                // Getting the name of removing customer
                String removingCustomer =  queue1PositionName.get(queueRemovePosition);

                // Removing the customer from the dictionaries
                queue1PositionName.remove(queueRemovePosition);
                queue1BurgerCountName.remove(removingCustomer);
                queueCustomer.remove(removingCustomer);


                System.out.println("\nCustomer " + removingCustomer +" in queue " + queueRemoveNumber + " position " + queueRemovePosition + " is removed.");
            if (!waitingList.isEmpty()){
                cashier3Customers[queueRemovePosition - 1] = "O";

                Map.Entry<String, Integer> entry = waitingList.next();
                queueCustomer.put(entry.getKey(), 3);

                // To store position in queue and name
                queue1PositionName.put(queueRemovePosition, entry.getKey());
                // To store name and burgers required
                // Together these two can be used to update burger stock when removing a customer
                queue1BurgerCountName.put(entry.getKey(), entry.getValue());

                System.out.println("\n" + entry.getKey() + " " + " added to queue 3" + " position " + queueRemovePosition);

                queue1CustomersBurgerCount += entry.getValue();
                burgerStock -= entry.getValue();

                if (burgerStock <= 10)
                    System.out.println("\nWARNING Burger count remaining in stock is " + burgerStock);

                waitingList.removeFirst();
            }
            }
            else{
                System.out.println("Theres no customer " + queueRemovePosition + " in queue " + queueRemoveNumber);
            }
        }
        else if (queueRemoveNumber == 2) {
            if (cashier2Customers[queueRemovePosition - 1].equals("O")) {
                cashier2Customers[queueRemovePosition - 1] = "X";

                // Getting the name of removing customer
                String removingCustomer =  queue2PositionName.get(queueRemovePosition);

                // Removing the customer from the dictionaries
                queue2PositionName.remove(queueRemovePosition);
                queue2BurgerCountName.remove(removingCustomer);
                queueCustomer.remove(removingCustomer);


                System.out.println("\nCustomer " + removingCustomer +" in queue " + queueRemoveNumber + " position " + queueRemovePosition + " is removed.");

            if (!waitingList.isEmpty()){
                cashier3Customers[queueRemovePosition - 1] = "O";

                Map.Entry<String, Integer> entry = waitingList.next();
                queueCustomer.put(entry.getKey(), 3);

                // To store position in queue and name
                queue1PositionName.put(queueRemovePosition, entry.getKey());
                // To store name and burgers required
                // Together these two can be used to update burger stock when removing a customer
                queue1BurgerCountName.put(entry.getKey(), entry.getValue());

                System.out.println("\n" + entry.getKey() + " " + " added to queue 3" + " position " + queueRemovePosition);

                queue1CustomersBurgerCount += entry.getValue();
                burgerStock -= entry.getValue();

                if (burgerStock <= 10)
                    System.out.println("\nWARNING Burger count remaining in stock is " + burgerStock);

                waitingList.removeFirst();
            }
            }
            else{
                System.out.println("Theres no position " + queueRemovePosition + " in queue " + queueRemoveNumber);
            }
        }
        else if (queueRemoveNumber == 3) {
            if (cashier3Customers[queueRemovePosition - 1].equals("O")) {
                cashier3Customers[queueRemovePosition - 1] = "X";

                // Getting the name of removing customer
                String removingCustomer =  queue3PositionName.get(queueRemovePosition);

                // Removing the customer from the dictionaries
                queue3PositionName.remove(queueRemovePosition);
                queue3BurgerCountName.remove(removingCustomer);
                queueCustomer.remove(removingCustomer);


                System.out.println("\nCustomer " + removingCustomer +" in queue " + queueRemoveNumber + " position " + queueRemovePosition + " is removed.");

            if (!waitingList.isEmpty()){
                cashier3Customers[queueRemovePosition - 1] = "O";

                Map.Entry<String, Integer> entry = waitingList.next();
                queueCustomer.put(entry.getKey(), 3);

                // To store position in queue and name
                queue1PositionName.put(queueRemovePosition, entry.getKey());
                // To store name and burgers required
                // Together these two can be used to update burger stock when removing a customer
                queue1BurgerCountName.put(entry.getKey(), entry.getValue());

                System.out.println("\n" + entry.getKey() + " " + " added to queue 3" + " position " + queueRemovePosition);

                queue1CustomersBurgerCount += entry.getValue();
                burgerStock -= entry.getValue();

                if (burgerStock <= 10)
                    System.out.println("\nWARNING Burger count remaining in stock is " + burgerStock);

                waitingList.removeFirst();
            }
            }
            else{
                System.out.println("Theres no customer " + queueRemovePosition + " in queue " + queueRemoveNumber);
            }
        }
        else{
            System.out.println("Invalid input");
        }
    }

    static void viewRemainingStock() {
        System.out.println( "\n" + burgerStock + " burgers remains from the stock.");
    }
    static void addBurgersToStock(Scanner scanner) {
        System.out.println("\nNumber of burgers in stock is " + burgerStock);
        System.out.print("\nHow many burgers are to be restocked? ");
        int numberOfRestockingBurgers = scanner.nextInt();
        burgerStock += numberOfRestockingBurgers;
        System.out.println("\nThere are " + burgerStock + " burgers in stock.");
    }

    static void viewCustomersSorted() {
        // Using bubble sort method to arrange strings in the alphabetical order
        String[] keys = queueCustomer.keySet().toArray(new String[0]);
        int n = keys.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                // Compare strings and swap if necessary
                if (keys[j].compareTo(keys[j + 1]) > 0) {
                    String temp = keys[j];
                    keys[j] = keys[j + 1];
                    keys[j + 1] = temp;
                    swapped = true;
                }
            }
            // If swaps did not happen in the inner loop that means its already in alphabetical order
            if (!swapped) {
                break;
            }
        }
        printArray(keys);
    }
    public static void printArray(String[] arr) {
        // this is used to print out the sorted customer names accordingly

        System.out.println("Customers sorted in alphabetical order");

        for (String str : arr) {
            System.out.println("\n" + str);
        }
        System.out.println();
    }

    public static void storeProgramData() {

        // I used this store data into a file option to store names and queue numbers of customers and the burger stock
        try {
            FileWriter writer = new FileWriter("storeProgramData.txt");
            writer.write("\nName and queue number of customers");
            for (Map.Entry<String, Integer> entry : queueCustomer.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                writer.write("\nName: " + key + ", Queue: " + value);
            }
            writer.write("\n" + "Burgers remaining in stock is: " + burgerStock);
            writer.close();
            System.out.println("\nData saved successfully.");
        }
        catch (IOException e) {
            System.out.println("\nAn error occurred while saving the data to a file.");
            e.printStackTrace();
        }
    }
    public static void loadProgramData() {

        // This code is used to load program data stored in text file into the console
        try {
            String filename = "storeProgramData.txt";

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name")) {
                    System.out.println("\n" + line);
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        System.out.println(line);
                    }
                }
                else if (line.startsWith("Burgers")) {
                    System.out.println("\n" + line);
                }
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println("\nError occurred while loading data from file: " + e.getMessage());
        }
    }
    public static void incomeOfEachQueue() {
        System.out.println("\nIncome of queue one: " + queue1CustomersBurgerCount*650);
        System.out.println("Income of queue two: " + queue2CustomersBurgerCount*650);
        System.out.println("Income of queue three: " + queue3CustomersBurgerCount*650);
    }



}

