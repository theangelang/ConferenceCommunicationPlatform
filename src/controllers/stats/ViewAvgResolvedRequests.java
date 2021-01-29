package controllers.stats;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.RequestManager;

/**
 * Class that calculates the percentage of resolved requests for an organizer
 */

public class ViewAvgResolvedRequests implements UserControl {

    private RequestManager requestManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class Constructor
     * @param requestManager Use Case that handles requests
     */

    public ViewAvgResolvedRequests(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    /**
     * Organizer calculates the value of this statistic
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command) {
        double resolved = requestManager.getResolvedRequests().size();
        double total = requestManager.getTotalRequests().size();

        if (total != 0.0) {
            double stat = resolved / total;
            menuPresenter.displayStatistic(Math.round(stat*100), "viewAvgResolvedRequests");
        } else {
            menuPresenter.failedAction();
        }

    }
}

