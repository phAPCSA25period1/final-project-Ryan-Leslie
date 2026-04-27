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
        System.out.println(userName + "'s Volunteer Logs (" + getLogCount() + " entries):");
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

    public ArrayList<String> getOrgList(){
        ArrayList<String> orgs = new ArrayList<String>();
        for(int i = 0; i < logs.size(); i++){
            String org = logs.get(i).getOrganization();
            if(!orgs.contains(org)){
                orgs.add(org);
            }
        }
        return orgs;
    }

    public double getTotalHoursByOrg(String org){
        double total = 0;
        for(int i = 0; i < logs.size(); i++){
            if(logs.get(i).getOrganization().equalsIgnoreCase(org)){
                total += logs.get(i).getHours();
            }
        }
        return total;
    }

    public VolunteerLog getMostRecentLog(){

        if(logs.size() == 0){
            return null;
        }

        VolunteerLog mostRecent = logs.get(0);
        for(int i = 0; i < logs.size(); i++){
            if(logs.get(i).getDate().compareTo(mostRecent.getDate()) > 0){
                mostRecent = logs.get(i);
            }
        }
        return mostRecent;
    }

    public String[][] getMonthlySummary() {
        if (logs.size() == 0){
            return new String[0][0];
        }

        ArrayList<String> months = new ArrayList<String>();
        for (int i = 0; i < logs.size(); i++){
            if (logs.get(i).getDate().length() >= 7){
                String month = logs.get(i).getDate().substring(0, 7);
                if (!months.contains(month)){
                    months.add(month);
                }
            }
        }

        String[][] summary = new String[months.size()][2];

        for (int i = 0; i < months.size(); i++){
            String month = months.get(i);
            double total = 0;

            for (int j = 0; j < logs.size(); j++){
                if (logs.get(j).getDate().length() >= 7) {
                    if(logs.get(j).getDate().substring(0, 7).equals(month)){
                        total += logs.get(j).getHours();
                    }
                }
            }

            summary[i][0] = month;
            summary[i][1] = String.valueOf(total);
        }

        return summary;

    }




}
