package controllers.attendee;

import controllers.UserControl;
import controllers.request.CreateRequest;
import useCases.*;
import outer.MenuPresenter;

import java.util.TreeMap;
/**
 * Class for Attendee Commands
 */

public class AttendeeControl implements UserControl {

    private MenuPresenter menuPresenter = new MenuPresenter();

    private TreeMap<String, UserControl> commandList =  new TreeMap<>();

    /**
     * Class Constructor
     * @param userManager the Use Case that holds and manages User accounts
     * @param messageManager the Use Case that holds and manages MessageChains
     * @param eventManager the Use Case that holds and manages Events
     * @param roomManager the Use Case that holds and manages Rooms
     * @param bookingManager the Use Case that deals with the booking of Attendee accounts for Events
     */

    public AttendeeControl(MessageManager messageManager, UserManager userManager, BookingManager bookingManager,
                           RoomManager roomManager, EventManager eventManager, UserQueueManager userQueueManager, RequestManager requestManager, ReviewManager reviewManager) {
        commandList.put("signUp", new EventRegistration(eventManager, userManager, bookingManager, roomManager, userQueueManager));
        commandList.put("cancel", new CancelRegistration(eventManager, userManager, userQueueManager));
        commandList.put("seeMySchedule", new ViewSchedule(userManager, eventManager));
        commandList.put("downloadMySchedule", new DownloadSchedule(userManager, eventManager, userQueueManager));
        commandList.put("seeListOfEvents", new ViewEvents(eventManager));
        commandList.put("seeListOfFriends", new ViewFriends(userManager));
        commandList.put("seeRecommendedFriends", new ViewRecommendedFriends(userManager));
        commandList.put("addFriend", new AddFriend(userManager));
        commandList.put("removeFriend", new RemoveFriend(userManager));
        commandList.put("createRequest", new CreateRequest(requestManager));
        commandList.put("recommendEvent", new RecommendEvent(userManager, eventManager));
        commandList.put("viewRecommendedEvents", new ViewRecommendedEvents(userManager, eventManager));
        commandList.put("viewSpeakerRecommendedEvents", new EventRecommendation(eventManager, reviewManager, userManager));
        commandList.put("viewSpeakerEvents", new ViewSpeakerEvents(eventManager, userManager));

    }

    /**
     * Takes a command from the Attendee and returns invalid command if command is not one of the options allowed
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
