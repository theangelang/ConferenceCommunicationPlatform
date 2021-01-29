package controllers.request;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.RequestManager;

/**
 * A class for viewing all the pending requests.
 */

public class ViewPendingRequests implements UserControl {

    private RequestManager requestManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Constructor for the viewPendingRequests class
     * @param requestManager the use case class that holds and manages Requests
     */

    public ViewPendingRequests(RequestManager requestManager){
        this.requestManager = requestManager;
    }

    /**
     * The Organizer can view all the pending requests along with their status and id.
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        menuPresenter.displayPendingRequests(requestManager.getPendingRequests());

    }
}
