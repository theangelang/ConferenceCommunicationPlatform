package controllers.request;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.RequestManager;

/**
 * A class for viewing a single request.
 */
public class ViewRequest implements UserControl {
    private RequestManager requestManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Constructor for the viewRequest class.
     * @param requestManager the use case class that holds and manages Requests
     */

    public ViewRequest(RequestManager requestManager) {

        this.requestManager = requestManager;
    }

    /**
     * The Organizer can view a single request with its status and id.
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 2) {
            String id = chunks[1];
            if (requestManager.getRequest(id) == null) {
                menuPresenter.failedAction();
            }
            else {
                menuPresenter.displayRequest(requestManager.getRequest(id));
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
