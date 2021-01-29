package controllers.request;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.RequestManager;

/**
 * A class to delete requests
 */
public class DeleteRequest implements UserControl {

    private RequestManager requestManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Constructor for the deleteRequest class
     * @param requestManager the use case class that holds and manages Requests
     */

    public DeleteRequest(RequestManager requestManager){
        this.requestManager = requestManager;
    }

    /**
     * The Organizer can delete a Request.
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 2){
            if (!requestManager.removeRequest(chunks[1])){
                menuPresenter.failedAction();
            }
        }
        else {
            menuPresenter.invalidCommand();
        }
    }
}
