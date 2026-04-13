public class VolunteerLog {

    private String date;
    private double hours;
    private String organization;
    private String supervisor;

    public VolunteerLog(String date, double hours, String organization, String supervisor){
        this.date = date;
        this.hours = hours;
        this.organization = organization;
        this.supervisor = supervisor;
    }

    public String getDate(){
        return date;
    }

    public double getHours(){
        return hours;
    }

    public String getOrganization(){
        return organization;
    }

    public String getSupervisor(){
        return supervisor;
    }

    // toString — prints a readable summary of this log
    public String toString() {
        return "Date: " + date + " | Org: " + organization +
               " | Hours: " + hours + " | Supervisor: " + supervisor;
    }

}
