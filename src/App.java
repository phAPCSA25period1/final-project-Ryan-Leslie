public class App {
    public static void main(String[] args) throws Exception {

        VolunteerManager manager = new VolunteerManager("Alex");

        // add some logs across two different months
        manager.addLog(new VolunteerLog("2024-09-01", 3.5, "Food Bank", "Ms. Smith"));
        manager.addLog(new VolunteerLog("2024-09-08", 2.0, "Animal Shelter", "Mr. Lee"));
        manager.addLog(new VolunteerLog("2024-09-15", 4.0, "Food Bank", "Ms. Smith"));
        manager.addLog(new VolunteerLog("2024-10-05", 2.0, "Library", "Mr. Jones"));

        // test displayAllLogs()
        System.out.println("=== All Logs ===");
        manager.displayAllLogs();

        // test getLogCount()
        System.out.println("\n=== Log Count ===");
        System.out.println("Number of logs: " + manager.getLogCount());
        // should print 4

        // test getTotalHours()
        System.out.println("\n=== Total Hours ===");
        System.out.println("Total hours: " + manager.getTotalHours());
        // should print 11.5

        // test getAvgHoursPerMonth()
        System.out.println("\n=== Average Hours Per Month ===");
        System.out.println("Avg per month: " + manager.getAvgHoursPerMonth());
        // 2 unique months → 11.5 / 2 = 5.75

        // test filterByOrg() normal case
        System.out.println("\n=== Filter: Food Bank ===");
        manager.filterByOrg("Food Bank");
        // should print log1 and log3 only

        // test filterByOrg() case insensitive
        System.out.println("\n=== Filter: food bank ===");
        manager.filterByOrg("food bank");
        // should still print log1 and log3 if using equalsIgnoreCase

        // test filterByOrg() no match
        System.out.println("\n=== Filter: Hospital ===");
        manager.filterByOrg("Hospital");
        // should print nothing

        // test getAvgHoursPerMonth() with empty manager
        System.out.println("\n=== Edge Case: Empty Manager ===");
        VolunteerManager emptyManager = new VolunteerManager("Empty");
        System.out.println("Avg hours (empty): " + emptyManager.getAvgHoursPerMonth());
        // should print 0.0 not crash

        // test getLogCount() on empty manager
        System.out.println("Log count (empty): " + emptyManager.getLogCount());
        // should print 0


    }
}
