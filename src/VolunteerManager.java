import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Manages a collection of VolunteerLog entries for a single user.
 * Handles adding logs, calculating statistics, sorting, and filtering.
 *
 * @author Ryan Leslie
 * @version 1.0
 */
public class VolunteerManager {

    private ArrayList<VolunteerLog> logs;
    private String userName;

    /**
     * Constructs a new VolunteerManager for the given user.
     *
     * @param userName the name of the volunteer
     */
    public VolunteerManager(String userName) {
        this.userName = userName;
        this.logs = new ArrayList<VolunteerLog>();
    }

    /**
     * Adds a new VolunteerLog entry to the collection.
     *
     * @param log the VolunteerLog object to add
     */
    public void addLog(VolunteerLog log) {
        logs.add(log);
    }

    /**
     * Prints all volunteer logs to the console with a header
     * showing the user's name and total number of entries.
     */
    public void displayAllLogs() {
        System.out.println(userName + "'s Volunteer Logs (" + getLogCount() + " entries):");
        for (int i = 0; i < logs.size(); i++) {
            System.out.println(logs.get(i));
        }
    }

    /**
     * Calculates and returns the total hours across all log entries.
     *
     * @return the total volunteer hours as a double
     */
    public double getTotalHours() {
        double total = 0;
        for (int i = 0; i < logs.size(); i++) {
            total += logs.get(i).getHours();
        }
        return total;
    }

    /**
     * Returns the total number of log entries in the collection.
     *
     * @return the number of logs as an int
     */
    public int getLogCount() {
        return logs.size();
    }

    /**
     * Calculates the average volunteer hours per unique calendar month.
     * Ignores log entries with malformed date strings shorter than 7 characters.
     * Returns 0 if the log list is empty or all dates are invalid.
     *
     * @return the average hours per month as a double
     */
    public double getAvgHoursPerMonth() {
        if (logs.size() == 0) {
            return 0;
        }

        double total = getTotalHours();

        ArrayList<String> months = new ArrayList<String>();
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getDate().length() >= 7) {
                String month = logs.get(i).getDate().substring(0, 7);
                if (!months.contains(month)) {
                    months.add(month);
                }
            }
        }

        if (months.size() == 0) {
            return 0;
        }

        return total / months.size();
    }

    /**
     * Prints all log entries matching the given organization name.
     * Comparison is case insensitive.
     *
     * @param org the organization name to filter by
     */
    public void filterByOrg(String org) {
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getOrganization().equalsIgnoreCase(org)) {
                System.out.println(logs.get(i));
            }
        }
    }

    public String getTopOrg() {
        if (logs.isEmpty()) return "None";

        String topOrg = "";
        double maxHours = -1.0;
        ArrayList<String> allOrgs = getOrgList(); // Uses your existing helper!

        for (String org : allOrgs) {
            double hoursForThisOrg = getTotalHoursByOrg(org); // Uses your other helper!
            if (hoursForThisOrg > maxHours) {
                maxHours = hoursForThisOrg;
                topOrg = org;
            }
        }
        return topOrg;
    }

    /**
     * Sorts all log entries chronologically by date using Selection Sort.
     * Dates must be in YYYY-MM-DD format for correct ordering.
     */
    public void sortByDate() {
        for (int i = 0; i < logs.size() - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < logs.size(); j++) {
                if (logs.get(j).getDate().compareTo(logs.get(minIndex).getDate()) < 0) {
                    minIndex = j;
                }
            }

            VolunteerLog temp = logs.get(minIndex);
            logs.set(minIndex, logs.get(i));
            logs.set(i, temp);
        }
    }

    /**
     * Calculates the percentage of a target hour goal that has been completed.
     *
     * @param targetHours the goal number of volunteer hours
     * @return the percentage complete as a double
     */
    public double getPercentToGoal(double targetHours) {
        return (getTotalHours() / targetHours) * 100;
    }

    /**
     * Returns a list of unique organization names across all log entries.
     *
     * @return an ArrayList of unique org name Strings
     */
    public ArrayList<String> getOrgList() {
        ArrayList<String> orgs = new ArrayList<String>();
        for (int i = 0; i < logs.size(); i++) {
            String org = logs.get(i).getOrganization();
            if (!orgs.contains(org)) {
                orgs.add(org);
            }
        }
        return orgs;
    }

    /**
     * Calculates the total volunteer hours for a specific organization.
     * Comparison is case insensitive.
     *
     * @param org the organization name to total hours for
     * @return the total hours for that organization as a double
     */
    public double getTotalHoursByOrg(String org) {
        double total = 0;
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getOrganization().equalsIgnoreCase(org)) {
                total += logs.get(i).getHours();
            }
        }
        return total;
    }

    /**
     * Finds and returns the most recently dated log entry.
     * Returns null if the log list is empty.
     *
     * @return the most recent VolunteerLog object, or null if empty
     */
    public VolunteerLog getMostRecentLog() {
        if (logs.size() == 0) {
            return null;
        }

        VolunteerLog mostRecent = logs.get(0);
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getDate().compareTo(mostRecent.getDate()) > 0) {
                mostRecent = logs.get(i);
            }
        }
        return mostRecent;
    }

    /**
     * Builds and returns a 2D array summarizing total hours per unique month.
     * Column 0 contains the year-month string (YYYY-MM).
     * Column 1 contains the total hours for that month as a String.
     * Returns an empty 2D array if the log list is empty.
     *
     * @return a String[][] where each row is a unique month and its total hours
     */
    public String[][] getMonthlySummary() {
        if (logs.size() == 0) {
            return new String[0][0];
        }

        ArrayList<String> months = new ArrayList<String>();
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getDate().length() >= 7) {
                String month = logs.get(i).getDate().substring(0, 7);
                if (!months.contains(month)) {
                    months.add(month);
                }
            }
        }

        String[][] summary = new String[months.size()][2];

        for (int i = 0; i < months.size(); i++) {
            String month = months.get(i);
            double total = 0;

            for (int j = 0; j < logs.size(); j++) {
                if (logs.get(j).getDate().length() >= 7) {
                    if (logs.get(j).getDate().substring(0, 7).equals(month)) {
                        total += logs.get(j).getHours();
                    }
                }
            }

            summary[i][0] = month;
            summary[i][1] = String.valueOf(total);
        }

        return summary;

    }

    /**
     * Saves all volunteer logs to a text file.
     * Each log is written as a comma separated line in the format:
     * date,hours,organization,supervisor
     *
     * @param filename the name of the file to save to
     */
    public void saveToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < logs.size(); i++) {
                writer.write(logs.get(i).getDate() + "," +
                            logs.get(i).getHours() + "," +
                            logs.get(i).getOrganization() + "," +
                            logs.get(i).getSupervisor() + "\n");
            }
            writer.close();
            System.out.println("Logs saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving logs: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("Welcome! No previous logs found, starting fresh.");
                return;
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty()) {  // skip empty lines
                    continue;
                }
                String[] data = line.split(",");
                if (data.length == 4) {       // make sure line has all 4 fields
                    String date = data[0];
                    double hours = Double.parseDouble(data[1]);
                    String org = data[2];
                    String superv = data[3];
                    addLog(new VolunteerLog(date, hours, org, superv));
                }
            }
            fileScanner.close();
            System.out.println("Previous logs loaded successfully!");
        } catch (Exception e) {
            System.out.println("Error loading logs: " + e.getMessage());
        }
    }




}
