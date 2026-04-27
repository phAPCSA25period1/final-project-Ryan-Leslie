import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   VOLUNTEER TRACKER - FULL TEST SUITE");
        System.out.println("========================================");

        VolunteerManager manager = new VolunteerManager("Ryan");

        // add logs across three months and multiple orgs
        manager.addLog(new VolunteerLog("2024-11-12", 1.5, "Hospital", "Dr. Reed"));
        manager.addLog(new VolunteerLog("2024-09-08", 2.0, "Animal Shelter", "Mr. Lee"));
        manager.addLog(new VolunteerLog("2024-10-05", 2.0, "Library", "Mr. Jones"));
        manager.addLog(new VolunteerLog("2024-09-01", 3.5, "Food Bank", "Ms. Smith"));
        manager.addLog(new VolunteerLog("2024-09-15", 4.0, "Food Bank", "Ms. Smith"));
        manager.addLog(new VolunteerLog("2025-09-10", 2.5, "Food Bank", "Ms. Smith"));

        // ── displayAllLogs() ─────────────────────────────
        System.out.println("\n=== All Logs (unsorted) ===");
        manager.displayAllLogs();

        // ── sortByDate() ─────────────────────────────────
        System.out.println("\n=== All Logs (sorted by date) ===");
        manager.sortByDate();
        manager.displayAllLogs();
        // should print: Sep 1, Sep 8, Sep 15, Oct 5, Nov 12, Sep 2025

        // ── getLogCount() ────────────────────────────────
        System.out.println("\n=== Log Count ===");
        System.out.println("Number of logs: " + manager.getLogCount());
        // should print 6

        // ── getTotalHours() ──────────────────────────────
        System.out.println("\n=== Total Hours ===");
        System.out.println("Total hours: " + manager.getTotalHours());
        // should print 15.5

        // ── getAvgHoursPerMonth() ────────────────────────
        System.out.println("\n=== Average Hours Per Month ===");
        System.out.println("Avg per month: " + manager.getAvgHoursPerMonth());
        // 4 unique year-months → 15.5 / 4 = 3.875
        // confirms multi-year fix (2024-09 and 2025-09 are separate)

        // ── filterByOrg() ────────────────────────────────
        System.out.println("\n=== Filter: Food Bank ===");
        manager.filterByOrg("Food Bank");
        // should print 3 logs

        System.out.println("\n=== Filter: food bank (lowercase) ===");
        manager.filterByOrg("food bank");
        // should still print 3 logs — equalsIgnoreCase check

        System.out.println("\n=== Filter: No Match ===");
        manager.filterByOrg("NASA");
        // should print nothing, no crash

        // ── getTopOrg() ──────────────────────────────────
        System.out.println("\n=== Top Organization ===");
        System.out.println("Top org: " + manager.getTopOrg());
        // Food Bank has 10.0 hours total, should win

        // ── getTotalHoursByOrg() ─────────────────────────
        System.out.println("\n=== Total Hours By Org ===");
        System.out.println("Food Bank: " + manager.getTotalHoursByOrg("Food Bank"));
        // should print 10.0
        System.out.println("Animal Shelter: " + manager.getTotalHoursByOrg("Animal Shelter"));
        // should print 2.0
        System.out.println("NASA (none): " + manager.getTotalHoursByOrg("NASA"));
        // should print 0.0

        // ── getOrgList() ─────────────────────────────────
        System.out.println("\n=== Org List ===");
        ArrayList<String> orgs = manager.getOrgList();
        for (int i = 0; i < orgs.size(); i++) {
            System.out.println(orgs.get(i));
        }
        // should print 4 unique orgs, no duplicates

        // ── getMostRecentLog() ───────────────────────────
        System.out.println("\n=== Most Recent Log ===");
        System.out.println(manager.getMostRecentLog());
        // should print the 2025-09-10 Food Bank log

        // ── getPercentToGoal() ───────────────────────────
        System.out.println("\n=== Percent to Goal ===");
        System.out.println("Percent to 100hr goal: " + manager.getPercentToGoal(100) + "%");
        // should print 15.5%
        System.out.println("Percent to 50hr goal:  " + manager.getPercentToGoal(50) + "%");
        // should print 31.0%

        // ── edge cases ───────────────────────────────────
        System.out.println("\n=== Edge Cases: Empty Manager ===");
        VolunteerManager emptyManager = new VolunteerManager("Empty");
        System.out.println("Avg hours: " + emptyManager.getAvgHoursPerMonth());
        // should print 0.0
        System.out.println("Log count: " + emptyManager.getLogCount());
        // should print 0
        System.out.println("Total hours: " + emptyManager.getTotalHours());
        // should print 0.0
        System.out.println("Top org: " + emptyManager.getTopOrg());
        // should print empty string
        System.out.println("Most recent: " + emptyManager.getMostRecentLog());
        // should print null, no crash
        System.out.println("Org list size: " + emptyManager.getOrgList().size());
        // should print 0

        // ── bad date input ───────────────────────────────
        System.out.println("\n=== Edge Cases: Bad Date ===");
        VolunteerManager badManager = new VolunteerManager("Bad");
        badManager.addLog(new VolunteerLog("BAD", 2.0, "Food Bank", "Ms. Smith"));
        System.out.println("Avg with bad date: " + badManager.getAvgHoursPerMonth());
        // should print 0.0, no crash
        System.out.println("Most recent bad date: " + badManager.getMostRecentLog());
        // should still return the log object, just with a bad date string

        // ── getMonthlySummary() ──────────────────────────────
        System.out.println("\n=== Monthly Summary (2D Array) ===");
        String[][] summary = manager.getMonthlySummary();
        for (int i = 0; i < summary.length; i++) {
            System.out.println("Month: " + summary[i][0] + " | Hours: " + summary[i][1]);
        }
        // should print 4 rows:
        // Month: 2024-09 | Hours: 9.5
        // Month: 2024-10 | Hours: 2.0
        // Month: 2024-11 | Hours: 1.5
        // Month: 2025-09 | Hours: 2.5

        // confirm 2D array dimensions
        System.out.println("Rows: " + summary.length);
        // should print 4
        System.out.println("Columns: " + summary[0].length);
        // should print 2

        // test getMonthlySummary() on empty manager
        System.out.println("\n=== Monthly Summary: Empty Manager ===");
        String[][] emptySum = emptyManager.getMonthlySummary();
        System.out.println("Rows (empty): " + emptySum.length);
        // should print 0, no crash

        // test getMonthlySummary() with bad date
        System.out.println("\n=== Monthly Summary: Bad Date ===");
        String[][] badSum = badManager.getMonthlySummary();
        System.out.println("Rows (bad date): " + badSum.length);
        // should print 0 since no valid dates exist, no crash

        System.out.println("\n========================================");
        System.out.println("           ALL TESTS COMPLETE");
        System.out.println("========================================");
    }
}
