/**
 * Tracks a volunteer's progress toward a personal hour goal.
 * Compares current hours against a target and deadline.
 *
 * @author Ryan Leslie
 * @version 1.0
 */
public class GoalTracker {

    private double targetHours;
    private int weeksToDeadline;

    /**
     * Constructs a new GoalTracker with a target and deadline.
     *
     * @param targetHours      the total hours the volunteer wants to reach
     * @param weeksToDeadline  the number of weeks until the deadline
     */
    public GoalTracker(double targetHours, int weeksToDeadline) {
        this.targetHours = targetHours;
        this.weeksToDeadline = weeksToDeadline;
    }

    /**
     * Calculates the number of hours remaining to reach the target.
     *
     * @param currentHours the number of hours volunteered so far
     * @return the hours remaining as a double, or 0 if goal is already reached
     */
    public double getHoursRemaining(double currentHours) {
        if (currentHours >= targetHours) {
            return 0;
        }
        return targetHours - currentHours;
    }

    /**
     * Returns the target hour goal.
     *
     * @return the target hours as a double
     */
    public double getTargetHours() {
        return targetHours;
    }

    /**
     * Returns the number of weeks until the deadline.
     *
     * @return weeks to deadline as an int
     */
    public int getWeeksToDeadline() {
        return weeksToDeadline;
    }

    /**
     * Determines whether the volunteer has reached their target hour goal.
     *
     * @param currentHours the number of hours volunteered so far
     * @return true if currentHours is greater than or equal to targetHours
     */
    public boolean isGoalReached(double currentHours){
        if(currentHours >= targetHours){
            return true;
        }
        return false;

    }
    /**
     * Calculates the required volunteer hours per week to reach the target goal
     * based on the remaining hours and weeks until the deadline.
     *
     * @param currentHours the number of hours volunteered so far
     * @return the required hours per week as a double, or 0 if goal is already reached
     */
    public double calcRequiredRate(double currentHours){
        if(isGoalReached(currentHours) == true){
            return 0.0;
        }
        if(weeksToDeadline == 0){
            return 0.0;
        }
        return (getHoursRemaining(currentHours)/weeksToDeadline);

    }




}
