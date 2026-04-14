import java.util.ArrayList;

public class VolunteerManager {

    private ArrayList<VolunteerLog> logs;
    private String userName;

    // Constructor
    public VolunteerManager(String userName) {
        this.userName = userName;
        this.logs = new ArrayList<VolunteerLog>();
    }

    public void addLog(VolunteerLog log) {
    logs.add(log);
    }

    public void displayAllLogs(){
        for(int i = 0; i < logs.size(); i++){
            System.out.println(logs.get(i));
        }
    }

    public double getTotalHours(){
        double total = 0;
        for(int i = 0; i < logs.size(); i++){
            total += logs.get(i).getHours();
        }
        return total;
    }

    public int getLogCount(){
        return logs.size();
    }

    public double getAvgHoursPerMonth() {
        if (logs.size() == 0) {
            return 0;
        }

        double total = getTotalHours();

        // collect unique months
        ArrayList<String> months = new ArrayList<String>();
        for (int i = 0; i < logs.size(); i++) {
            String month = logs.get(i).getDate().substring(5, 7);
            if (!months.contains(month)) {
                months.add(month);
            }
        }

        return total / months.size();
    }

    public void filterByOrg(String org){
        for(int i = 0; i < logs.size(); i++){
            if(logs.get(i).getOrganization().equals(org)){
                System.out.println(logs.get(i));
            }
        }
    }



}
