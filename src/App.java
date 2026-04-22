public class App {
    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   VOLUNTEER TRACKER - FULL TEST SUITE");
        System.out.println("========================================");

        VolunteerManager manager = new VolunteerManager("Alex");

        // add logs across three months and multiple orgs
        manager.addLog(new VolunteerLog("2024-11-12", 1.5, "Hospital", "Dr. Reed"));
        manager.addLog(new VolunteerLog("2024-09-08", 2.0, "Animal Shelter", "Mr. Lee"));
        manager.addLog(new VolunteerLog("2024-10-05", 2.0, "Library", "Mr. Jones"));
        manager.addLog(new VolunteerLog("2024-09-01", 3.5, "Food Bank", "Ms. Smith"));
        manager.addLog(new VolunteerLog("2024-09-15", 4.0, "Food Bank", "Ms. Smith"));
        manager.addLog(new VolunteerLog("2025-09-10", 2.5, "Food Bank", "Ms. Smith"));

        // ── displayAllLogs() ──────────────────────────────
        System.out.println("\n=== All Logs (unsorted) ===");
        manager.displayAllLogs();

        // ── sortByDate() ──────────────────────────────────
        System.out.println("\n=== All Logs (sorted by date) ===");
        manager.sortByDate();
        manager.displayAllLogs();
        // should print: Sep 1, Sep 8, Sep 15, Oct 5, Nov 12, Sep 2025

        // ── getLogCount() ─────────────────────────────────
        System.out.println("\n=== Log Count ===");
        System.out.println("Number of logs: " + manager.getLogCount());
        // should print 6

        // ── getTotalHours() ───────────────────────────────
        System.out.println("\n=== Total Hours ===");
        System.out.println("Total hours: " + manager.getTotalHours());
        // should print 15.5

        // ── getAvgHoursPerMonth() ─────────────────────────
        System.out.println("\n=== Average Hours Per Month ===");
        System.out.println("Avg per month: " + manager.getAvgHoursPerMonth());
        // 4 unique year-months → 15.5 / 4 = 3.875
        // confirms multi-year fix works (2024-09 and 2025-09 are separate)

        // ── filterByOrg() normal ──────────────────────────
        System.out.println("\n=== Filter: Food Bank ===");
        manager.filterByOrg("Food Bank");
        // should print 3 logs

        // ── filterByOrg() case insensitive ────────────────
        System.out.println("\n=== Filter: food bank (lowercase) ===");
        manager.filterByOrg("food bank");
        // should still print 3 logs

        // ── filterByOrg() no match ────────────────────────
        System.out.println("\n=== Filter: No Match ===");
        manager.filterByOrg("NASA");
        // should print nothing, no crash

        // ── getTopOrg() ───────────────────────────────────
        System.out.println("\n=== Top Organization ===");
        System.out.println("Top org: " + manager.getTopOrg());
        // Food Bank has 10.0 hours total, should win

        // ── getPercentToGoal() ────────────────────────────
        System.out.println("\n=== Percent to Goal ===");
        System.out.println("Percent to 100hr goal: " + manager.getPercentToGoal(100) + "%");
        // should print 15.5%
        System.out.println("Percent to 50hr goal:  " + manager.getPercentToGoal(50) + "%");
        // should print 31.0%

        // ── edge cases ────────────────────────────────────
        System.out.println("\n=== Edge Cases ===");
        VolunteerManager emptyManager = new VolunteerManager("Empty");
        System.out.println("Avg hours (empty):  " + emptyManager.getAvgHoursPerMonth());
        // should print 0.0, no crash
        System.out.println("Log count (empty):  " + emptyManager.getLogCount());
        // should print 0
        System.out.println("Total hours (empty):" + emptyManager.getTotalHours());
        // should print 0.0
        System.out.println("Top org (empty):    " + emptyManager.getTopOrg());
        // should print empty string, no crash

        // ── bad date string ───────────────────────────────
        System.out.println("\n=== Bad Date Input ===");
        VolunteerManager badManager = new VolunteerManager("Bad");
        badManager.addLog(new VolunteerLog("BAD", 2.0, "Food Bank", "Ms. Smith"));
        System.out.println("Avg with bad date: " + badManager.getAvgHoursPerMonth());
        // should print 0.0, not crash

        System.out.println("\n========================================");
        System.out.println("           ALL TESTS COMPLETE");
        System.out.println("========================================");
    }
}
