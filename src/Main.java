import java.util.Scanner;

/**
 * Main entry point for the Volunteer Hour Tracker application.
 * Provides a menu-driven interface for managing volunteer logs.
 *
 * @author Ryan Leslie
 * @version 1.0
 */
public class Main {

    /**
     * Runs the main menu loop for the Volunteer Tracker.
     * Allows the user to add logs, view stats, filter by org,
     * sort by date, and track progress toward a goal.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n========================================");
        System.out.println("    Welcome to the Volunteer Tracker");
        System.out.println("========================================");

        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();
        VolunteerManager manager = new VolunteerManager(name);

        double targetHours = 0;
        boolean validTarget = false;
        while (!validTarget) {
            System.out.print("Enter your hour goal: ");
            try {
                targetHours = Double.parseDouble(scanner.nextLine());
                if (targetHours <= 0) {
                    System.out.println("Goal must be greater than 0, try again.");
                } else {
                    validTarget = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }

        int weeksToDeadline = 0;
        boolean validWeeks = false;
        while (!validWeeks) {
            System.out.print("Enter weeks until deadline: ");
            try {
                weeksToDeadline = Integer.parseInt(scanner.nextLine());
                if (weeksToDeadline < 0) {
                    System.out.println("Weeks cannot be negative, try again.");
                } else {
                    validWeeks = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a whole number.");
            }
        }

        GoalTracker goalTracker = new GoalTracker(targetHours, weeksToDeadline);

        boolean running = true;
        while (running) {
            System.out.println("\n========================================");
            System.out.println("         VOLUNTEER TRACKER MENU");
            System.out.println("========================================");
            System.out.println("1. Add a log");
            System.out.println("2. View all logs");
            System.out.println("3. View stats");
            System.out.println("4. Filter by org");
            System.out.println("5. Sort logs by date");
            System.out.println("6. View monthly summary");
            System.out.println("7. Quit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                boolean validDate = false;
                String date = "";
                while (!validDate) {
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    if (date.length() == 10 && date.charAt(4) == '-' && date.charAt(7) == '-') {
                        validDate = true;
                    } else {
                        System.out.println("Invalid date format, please use YYYY-MM-DD.");
                    }
                }

                double hours = 0;
                boolean validHours = false;
                while (!validHours) {
                    System.out.print("Enter hours: ");
                    try {
                        hours = Double.parseDouble(scanner.nextLine());
                        if (hours < 0) {
                            System.out.println("Hours cannot be negative, try again.");
                        } else {
                            validHours = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input, please enter a number.");
                    }
                }

                System.out.print("Enter organization: ");
                String org = scanner.nextLine();

                System.out.print("Enter supervisor: ");
                String supervisor = scanner.nextLine();

                manager.addLog(new VolunteerLog(date, hours, org, supervisor));
                System.out.println("Log added successfully!");
            }
            else if (choice.equals("2")) {
                // view all logs
                manager.displayAllLogs();
            }

            else if (choice.equals("3")) {
                System.out.println("\n=== Your Stats ===");
                System.out.println("Total logs:        " + manager.getLogCount());
                System.out.println("Total hours:       " + manager.getTotalHours());
                System.out.println("Avg hours/month:   " + manager.getAvgHoursPerMonth());
                System.out.println("Top organization:  " + manager.getTopOrg());
                System.out.println("Percent to goal:   " + manager.getPercentToGoal(goalTracker.getTargetHours()) + "%");
                if (manager.getMostRecentLog() != null) {
                    System.out.println("Most recent log:   " + manager.getMostRecentLog());
                }

                System.out.println("\n=== Goal Progress ===");
                System.out.println("Target hours:      " + goalTracker.getTargetHours());
                System.out.println("Weeks remaining:   " + goalTracker.getWeeksToDeadline());
                System.out.println("Hours remaining:   " + goalTracker.getHoursRemaining(manager.getTotalHours()));
                System.out.println("Required rate:     " + goalTracker.calcRequiredRate(manager.getTotalHours()) + " hrs/week");
                if (goalTracker.isGoalReached(manager.getTotalHours())) {
                    System.out.println("Goal reached! Great work!");
                } else {
                    System.out.println("Keep going, you got this!");
                }
            }

            else if (choice.equals("4")) {
                // filter by org
                System.out.print("Enter organization name: ");
                String org = scanner.nextLine();
                manager.filterByOrg(org);
            }

            else if (choice.equals("5")) {
                // sort by date
                manager.sortByDate();
                System.out.println("Logs sorted by date.");
                manager.displayAllLogs();

            }

            else if (choice.equals("6")) {
                // monthly summary
                String[][] summary = manager.getMonthlySummary();
                if (summary.length == 0) {
                    System.out.println("No logs to summarize.");
                } else {
                    System.out.println("\n=== Monthly Summary ===");
                    for (int i = 0; i < summary.length; i++) {
                        System.out.println("Month: " + summary[i][0] + " | Hours: " + summary[i][1]);
                    }
                }
            }

            else if (choice.equals("7")) {
                running = false;
                System.out.println("Goodbye!");
            }
            else if (choice.equals("7")) {
            manager.saveToFile("logs.txt");
            running = false;
            System.out.println("Goodbye!");
            }
            else {
                System.out.println("Invalid option, try again.");
            }
        }
        scanner.close();
    }

}
