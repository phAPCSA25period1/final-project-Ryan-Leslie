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
            if(logs.get(i).getDate().length() >= 7){
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

    public void filterByOrg(String org){
        for(int i = 0; i < logs.size(); i++){
            if(logs.get(i).getOrganization().equalsIgnoreCase(org)){
                System.out.println(logs.get(i));
            }
        }
    }

    public String getTopOrg(){
        String topOrg = "";
        double topHours = 0;

        for(int i = 0; i < logs.size(); i++){
            String orgName = logs.get(i).getOrganization();  // grab org from outer loop
            double orgTotal = 0;

            for(int j = i + 1; j < logs.size(); j++){
                if(logs.get(i).getOrganization().equalsIgnoreCase(orgName)){
                    orgTotal += logs.get(i).getHours();
                }
            }

            if(orgTotal > topHours){
                topHours = orgTotal;
                topOrg = orgName;
            }
        }
        return topOrg;
    }

    public void sortByDate() {
        for (int i = 0; i < logs.size() - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < logs.size(); j++) {
                if (logs.get(j).getDate().compareTo(logs.get(minIndex).getDate()) < 0) {
                    minIndex = j;
                }
        }

        // swap
        VolunteerLog temp = logs.get(minIndex);
        logs.set(minIndex, logs.get(i));
        logs.set(i, temp);
        }
    }

    public double getPercentToGoal(double targetHours) {
        return (getTotalHours() / targetHours) * 100;
    }



}
