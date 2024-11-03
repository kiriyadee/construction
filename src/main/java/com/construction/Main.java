package com.construction;

import java.util.Scanner;

public class Main {

    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "admin123";

    private static boolean authenticate(Scanner scanner) {
        int attempts = 3;  // Give user 3 attempts to login

        while (attempts > 0) {
            System.out.println("\nLogin Authentication");
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
                System.out.println("Login successful!");
                return true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Invalid credentials. You have " + attempts + " attempts remaining.");
                } else {
                    System.out.println("Login failed. No more attempts remaining.");
                }
            }
        }
        return false;
    }

    private static String[] projects = new String[100];  // Store project names
    private static String[] statuses = new String[100];  // Store project statuses
    private static String[] workRequests = new String[100];  // Store work requests
    private static int projectCount = 0;  // Keep track of number of projects

    private static void displayMenu() {
        System.out.println("\nConstruction Project Management System");
        System.out.println("1. Create New Project");
        System.out.println("2. Check Project Status");
        System.out.println("3. Mark Project as Finished");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            // Authenticate user first
            if (!authenticate(scanner)) {
                System.out.println("Authentication failed. Program terminating.");
                return;
            }

            int choice;
            do {
                displayMenu();
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> {
                    }
                    case 2 -> {
                        checkProjectStatus(scanner);
                    }
                    case 3 -> {
                    }
                    case 4 ->
                        System.out.println("Exiting program...");
                    default ->
                        System.out.println("Invalid choice! Please try again.");
                }
                // Create new project
                // Check project status
                // Mark project as finished
            } while (choice != 4);
        }
        displayMenu();
    }

    @SuppressWarnings("unused")
    private static void checkProjectStatus(Scanner scanner) {
        System.out.println("\n=== 2. Check Project Status ===");
        System.out.println("1. View Current Status");
        System.out.println("2. Create Work Request");
        System.out.print("Enter your choice (1-2): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                System.out.println("\nCurrent Project Status:");
                // Add code to display project status
                System.out.println("Status: In Progress");
                System.out.println("Completion: 65%");
                System.out.println("Days Remaining: 45");
            }
            case 2 -> {
                System.out.println("\nCreate Work Request");
                System.out.print("Enter work request description: ");
                String description = scanner.nextLine();
                System.out.print("Enter priority (High/Medium/Low): ");
                String priority = scanner.nextLine();

                // Add code to save work request
                System.out.println("Work request created successfully!");
            }
            default ->
                System.out.println("Invalid choice! Please try again.");
        }
    }
}
