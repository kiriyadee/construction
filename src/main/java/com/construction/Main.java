package com.construction;

import java.util.ArrayList;
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

    private static ArrayList<Project> projects = new ArrayList<>();  // Store project objects
    private static ArrayList<String> statuses = new ArrayList<>();  // Store project statuses 
    private static int projectCount = 0;  // Keep track of number of projects

    private static void displayMenu() {
        System.out.println("\n\u001B[44m======= Construction Project Management System =======\u001B[0m");
        System.out.println("\u001B[44m1.\u001B[0m Create New Project");
        System.out.println("\u001B[44m2.\u001B[0m Check Project Status");
        System.out.println("\u001B[44m3.\u001B[0m Mark Project as Finished");
        System.out.println("\u001B[41m4.\u001B[0m Exit");
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
                        createNewProject(scanner);
                    }
                    case 2 -> {
                        checkProjectStatus(scanner);
                    }
                    case 3 -> {
                        markProjectAsFinished(scanner);
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

    private static void createNewProject(Scanner scanner) {
        System.out.println("\n=== 1. Create New Project ===");

        System.out.print("Enter project name: ");
        String name = scanner.nextLine();

        System.out.print("Enter project description: ");
        String description = scanner.nextLine();

        System.out.print("Enter estimated duration (in days): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter project budget: ");
        double budget = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Project newProject = new Project(name, description, duration, budget);
        projects.add(newProject);

        System.out.println("\nProject created successfully!");
        System.out.println("Project ID: " + newProject.getId());
    }

    private static class Project {

        private static int nextId = 1;
        private final int id;
        private final String name;
        private final String description;
        private final int duration;
        private final double budget;
        private String status;
        private int completionPercentage;

        public Project(String name, String description, int duration, double budget) {
            this.id = nextId++;
            this.name = name;
            this.description = description;
            this.duration = duration;
            this.budget = budget;
            this.status = "Not Started";
            this.completionPercentage = 0;
        }

        public int getId() {
            return id;
        }
    }

    @SuppressWarnings("unused")
    private static void checkProjectStatus(Scanner scanner) {
        System.out.println("\n=== 2. Check Project Status ===");
        System.out.println("1. View Current Status");
        System.out.println("2. Update Project Status");
        System.out.println("3. List All Projects");
        System.out.print("Enter your choice (1-3): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                System.out.println("\nCurrent Project Status:");
                // Add code to display project status
                System.out.println("Status: In Progress");
            }
            case 2 -> {
                System.out.println("\nUpdate Project Status");
                System.out.print("Enter project ID: ");
                int projectId = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Project project = findProjectById(projectId);
                if (project != null) {
                    System.out.println("\nSelect new status:");
                    System.out.println("1. Not Started");
                    System.out.println("2. In Progress");
                    System.out.println("3. On Hold");
                    System.out.println("4. Completed");
                    System.out.print("Enter choice (1-4): ");

                    int statusChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (statusChoice) {
                        case 1 ->
                            project.status = "Not Started";
                        case 2 ->
                            project.status = "In Progress";
                        case 3 ->
                            project.status = "On Hold";
                        case 4 -> {
                            project.status = "Completed";
                            project.completionPercentage = 100;
                        }
                        default -> {
                            System.out.println("Invalid status choice!");
                            return;
                        }
                    }

                    System.out.print("Enter completion percentage (0-100): ");
                    int percentage = scanner.nextInt();
                    if (percentage >= 0 && percentage <= 100) {
                        project.completionPercentage = percentage;
                        System.out.println("Project status updated successfully!");
                    } else {
                        System.out.println("Invalid percentage! Must be between 0 and 100.");
                    }
                } else {
                    System.out.println("Project not found!");
                }
            }
            case 3 -> {
                System.out.println("\nAll Projects:");
                if (projects.isEmpty()) {
                    System.out.println("No projects found.");
                } else {
                    for (Project project : projects) {
                        System.out.println("ID: " + project.getId());
                        System.out.println("Name: " + project.name);
                        System.out.println("Status: " + project.status);
                        System.out.println("Completion: " + project.completionPercentage + "%");
                        System.out.println("-------------------");
                    }
                }
            }
            default ->
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private static void markProjectAsFinished(Scanner scanner) {
        System.out.println("\n=== Mark Project as Finished ===");

        System.out.print("Enter project ID: ");
        int projectId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Project project = findProjectById(projectId);

        if (project != null) {
            project.status = "Completed";
            project.completionPercentage = 100;
            System.out.println("Project marked as finished successfully!");
        } else {
            System.out.println("Project not found!");
        }
    }

    private static Project findProjectById(int id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }
}
