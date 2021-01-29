package controllers.request;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.RequestManager;

/**
 * A class for viewing all the resolved requests.
 */

public class ViewResolvedRequests implements UserControl {
    private RequestManager requestManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Constructor for the viewResolvedRequests class.
     * @param requestManager the use case class that holds and manages Requests
     */

    public ViewResolvedRequests(RequestManager requestManager){
        this.requestManager = requestManager;
    }

    /**
     * The Organizer can view all the resolved requests along with their status and id.
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        menuPresenter.displayResolvedRequests(requestManager.getResolvedRequests());

    }
}
