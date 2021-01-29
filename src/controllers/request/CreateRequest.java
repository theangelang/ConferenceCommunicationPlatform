package controllers.request;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.RequestManager;

/**
 * A class for creating a request.
 */

public class CreateRequest implements UserControl {

    private RequestManager requestManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Constructor for the createRequest class
     * @param requestManager the use case class that holds and manages Requests
     */

    public CreateRequest(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    /**
     * The user can create a new Request.
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length >= 2) {
            StringBuilder note = new StringBuilder();
            for (int i = 1; i < chunks.length; i++) {
                note.append(" ").append(chunks[i]);
            }
            requestManager.addRequest(note.toString());
        } else {
            menuPresenter.invalidCommand();

        }
    }
}
