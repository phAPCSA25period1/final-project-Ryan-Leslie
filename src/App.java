public class App {
    public static void main(String[] args) throws Exception {

        VolunteerLog log1 = new VolunteerLog("2024-09-01", 3.5, "Food Bank", "Ms. Smith");
        VolunteerLog log2 = new VolunteerLog("2024-09-08", 2.0, "Animal Shelter", "Mr. Lee");
        VolunteerLog log3 = new VolunteerLog("2024-09-15", 4.0, "Food Bank", "Ms. Smith");

        // Zero hours — is this valid?
        VolunteerLog log4 = new VolunteerLog("2024-09-20", 0.0, "Library", "Mr. Jones");

        // Very large hours — what if someone enters 100?
        VolunteerLog log5 = new VolunteerLog("2024-09-21", 100.0, "Hospital", "Dr. Reed");

        // Empty string organization — what prints?
        VolunteerLog log6 = new VolunteerLog("2024-09-22", 2.0, "", "Ms. Smith");

        System.out.println(log1);
        System.out.println(log2);
        System.out.println(log3);
        System.out.println(log4);
        System.out.println(log5);
        System.out.println(log6);

        System.out.println("Hours from log1: " + log1.getHours());
        System.out.println("Org from log1: " + log1.getOrganization());

        double manualTotal = log1.getHours() + log2.getHours() + log3.getHours();
        System.out.println("Total hours (manual): " + manualTotal);
        // Should print 9.5


    }
}
