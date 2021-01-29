package controllers.request;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.RequestManager;

import java.util.TreeMap;

/**
 * A class for the commands regarding a Request
 */

public class RequestControl implements UserControl {

    private MenuPresenter menuPresenter = new MenuPresenter();

    private TreeMap<String, UserControl> commandList = new TreeMap<>();

    /**
     * Constructor for the RequestControl class
     * @param requestManager the use case class that holds and manages Requests
     */

    public RequestControl(RequestManager requestManager){
        //add request
        commandList.put("createRequest", new CreateRequest(requestManager));
        //change the status of a request
        commandList.put("updateRequestStatus", new UpdateRequestStatus(requestManager));
        //view a request
        commandList.put("viewRequest", new ViewRequest(requestManager));
        //delete a request
        commandList.put("deleteRequest", new DeleteRequest(requestManager));
        //view all request
        commandList.put("viewAllRequests", new ViewAllRequests(requestManager));
        //view pending requests
        commandList.put("viewPendingRequests", new ViewPendingRequests(requestManager));
        //view resolved requests
        commandList.put("viewResolvedRequests", new ViewResolvedRequests(requestManager));
    }

    /**
     * Takes a command from the user and returns invalid if the command is not one of the options allowed.
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] commandChunks = command.split(" ");

        if (commandList.get(commandChunks[0])==null) {
            menuPresenter.invalidCommand();
        } else {
            commandList.get(commandChunks[0]).parseCommand(command);
        }

    }
}
