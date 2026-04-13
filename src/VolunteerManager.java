import java.util.ArrayList;

public class VolunteerManager {

    private ArrayList<VolunteerLog> logs;
    private String userName;

    // Constructor
    public VolunteerManager(String userName) {
        this.userName = userName;
        this.logs = new ArrayList<VolunteerLog>();
    }

}
