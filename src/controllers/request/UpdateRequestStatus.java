package controllers.request;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.RequestManager;

/**
 * A class to update the status of a request.
 */

public class UpdateRequestStatus implements UserControl {

    private RequestManager requestManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Constructor for the updateRequestStatus class.
     * @param requestManager the use case class that holds and manages Requests
     */

    public UpdateRequestStatus(RequestManager requestManager){
        this.requestManager = requestManager;
    }

    /**
     * The Organizer can update the status of a Request.
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");


        if (chunks.length == 3) {
            String id = chunks[1];
            String newStatus = chunks[2];
            if (requestManager.getAllStatus().contains(newStatus) && !requestManager.getStatus(id).equals(newStatus)){
                requestManager.changeStatus(id, requestManager.giveStatus(chunks[2]));
            }
            else {
                menuPresenter.failedAction();
            }
    }
        else {
            menuPresenter.invalidCommand();
        }

    }
}
