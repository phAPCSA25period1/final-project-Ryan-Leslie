import java.util.Scanner;

/**
 * Main entry point for the Volunteer Hour Tracker application.
 * Provides a menu-driven interface for managing volunteer logs.
 *
 * @author Ryan Leslie
 * @version 1.0
 */
public class App {

    /**
     * Runs the main menu loop for the Volunteer Tracker.
     * Allows the user to add logs, view stats, filter by org,
     * sort by date, and track progress toward a goal.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        VolunteerManager manager = new VolunteerManager(name);

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
                System.out.print("Enter date (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                System.out.print("Enter hours: ");
                double hours = Double.parseDouble(scanner.nextLine());

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
                // view stats
                System.out.println("\n=== Your Stats ===");
                System.out.println("Total logs:        " + manager.getLogCount());
                System.out.println("Total hours:       " + manager.getTotalHours());
                System.out.println("Avg hours/month:   " + manager.getAvgHoursPerMonth());
                System.out.println("Top organization:  " + manager.getTopOrg());
                System.out.println("Percent to 100hrs: " + manager.getPercentToGoal(100) + "%");
                if (manager.getMostRecentLog() != null) {
                    System.out.println("Most recent log:   " + manager.getMostRecentLog());
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
            else {
                System.out.println("Invalid option, try again.");
            }
        }
        scanner.close();
    }

}
