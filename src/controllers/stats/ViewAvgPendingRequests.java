package controllers.stats;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.RequestManager;

/**
 * class that calculates the percentage of pending requests for an organizer
 */

public class ViewAvgPendingRequests implements UserControl {

    private RequestManager requestManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class Constructor
     * @param requestManager the Use Case that handles requests
     */

    public ViewAvgPendingRequests(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    /**
     * Organizer calculates the value of this statistic
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command) {
        double pending = requestManager.getPendingRequests().size();
        double total = requestManager.getTotalRequests().size();

        if(total != 0){
            double stat = pending/total;
            menuPresenter.displayStatistic(Math.round(stat*100), "viewAvgPendingRequests");
        }
        else{
            menuPresenter.failedAction();
        }

    }
}
