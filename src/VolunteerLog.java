/**
 * Represents a single volunteer log entry.
 * Stores information about one volunteering event including
 * the date, hours served, organization, and supervisor.
 *
 * @author Ryan Leslie
 * @version 1.0
 */
public class VolunteerLog {

    private String date;
    private double hours;
    private String organization;
    private String supervisor;

    /**
     * Constructs a new VolunteerLog with the given details.
     *
     * @param date         the date of the volunteer event (format: YYYY-MM-DD)
     * @param hours        the number of hours served
     * @param organization the name of the organization volunteered at
     * @param supervisor   the name of the supervising staff member
     */
    public VolunteerLog(String date, double hours, String organization, String supervisor) {
        this.date = date;
        this.hours = hours;
        this.organization = organization;
        this.supervisor = supervisor;
    }

    /**
     * Returns the date of this volunteer log entry.
     *
     * @return the date as a String in YYYY-MM-DD format
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the number of hours served in this log entry.
     *
     * @return the hours as a double
     */
    public double getHours() {
        return hours;
    }

    /**
     * Returns the name of the organization for this log entry.
     *
     * @return the organization name as a String
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Returns the name of the supervisor for this log entry.
     *
     * @return the supervisor name as a String
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * Returns a formatted String representation of this log entry.
     *
     * @return a readable summary showing date, org, hours, and supervisor
     */
    public String toString() {
        return "Date: " + date + " | Org: " + organization +
               " | Hours: " + hours + " | Supervisor: " + supervisor;
    }
}
