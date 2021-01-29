package controllers.stats;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.*;

import java.util.TreeMap;

/**
 * Class for Stats Commands
 */


public class StatsControl implements UserControl {

    private MenuPresenter menuPresenter = new MenuPresenter();
    private TreeMap<String, UserControl> commandList = new TreeMap<>();

    /**
     * Class Constructor
     * @param userManager the Use Case that holds and manages User accounts
     * @param eventManager the Use Case that holds and manages Events
     * @param roomManager the Use Case that holds and manages Rooms
     * @param requestManager the Use Case that holds and manages Requests
     * @param userQueueManager the Use Case that handles userQueue functions and submenu functions
     * @param ratingManager the Use Case that handles and manages rating and reviews
     */

    public StatsControl(UserManager userManager, EventManager eventManager, RoomManager roomManager, RequestManager requestManager, UserQueueManager userQueueManager, ReviewManager ratingManager){
        commandList.put("viewStats", new ViewStats(userManager, userQueueManager));
        commandList.put("closeStats", new CloseStats(userManager, userQueueManager));

        commandList.put("viewOptions", new ViewOptions(userManager, userQueueManager));

        commandList.put("viewAvgAttendeesPerEvent", new ViewAvgAttendeesPerEvent(eventManager));
        commandList.put("viewAvgEventsInRoom", new ViewAvgEventsInRoom(eventManager));
        commandList.put("viewAvgEventsOnDate", new ViewAvgEventsOnDate(eventManager));
        commandList.put("viewAvgFullyBookedEvents", new ViewAvgFullyBookedEvents(roomManager, eventManager));
        commandList.put("viewAvgPendingRequests", new ViewAvgPendingRequests(requestManager));
        commandList.put("viewAvgResolvedRequests", new ViewAvgResolvedRequests(requestManager));
        commandList.put("viewLastEventRegistration", new ViewLastEventRegistration(userQueueManager));
        commandList.put("viewLastLogins", new ViewLastLogins(userQueueManager));
        commandList.put("viewLastRegistrationCanceled", new ViewLastRegistrationCanceled(userQueueManager));
        commandList.put("viewLastUsersToDownload", new ViewLastUsersToDownload(userQueueManager));
        commandList.put("viewLastUsersToMessage", new ViewLastUsersToMessage(userQueueManager));
        commandList.put("viewTopFiveEvents", new ViewTopFiveEvents(eventManager));
        commandList.put("viewTopFiveSpeakers", new ViewTopFiveSpeakers(userManager, ratingManager));

    }

    /**
     * Takes a command from the Organizer and returns invalid command if command is not one of the options allowed
     */

    @Override
    public void parseCommand(String command) {
        String[] commandChunks = command.split(" ");

        if (commandList.get(commandChunks[0]) == null) {
            menuPresenter.invalidCommand();
        }
        else{
            commandList.get(commandChunks[0]).parseCommand(command);
        }
    }
}
