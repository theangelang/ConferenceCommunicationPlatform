package controllers.organizer;

import controllers.UserControl;
import controllers.request.*;
import controllers.stats.*;
import useCases.*;
import outer.MenuPresenter;

import java.util.TreeMap;

/**
 * A controller that handles the commands available to Organizers.
 */

public class OrganizerControl implements UserControl {

    private MenuPresenter menuPresenter = new MenuPresenter();

    private TreeMap<String, UserControl> commandList = new TreeMap<>();

    /**
     * The constructor for the OrganizerControl
     * @param messageManager the Use Case than holds and manages messageChains
     * @param userManager the Use Case that holds and manages Users
     * @param bookingManager the Use Case that manages bookings for Attendees
     * @param roomManager the Use Case that holds and manages Rooms
     * @param eventManager the Use Case that holds and manages Events
     */
    public OrganizerControl(MessageManager messageManager, UserManager userManager, BookingManager bookingManager,
                     RoomManager roomManager, EventManager eventManager, RequestManager requestManager, ReviewManager reviewManager, UserQueueManager userQueueManager) {

        commandList.put("messageAll", new MassMessageO(messageManager, userManager));
        commandList.put("addRoom", new RoomAdditionO(roomManager));
        commandList.put("addEvent", new EventAdditionO(eventManager, roomManager, userManager));
        commandList.put("cancelEvent", new CancelEvent(eventManager, userManager, roomManager));
        commandList.put("changeCapacity", new ChangeCapacityO(eventManager));
        commandList.put("addSpeaker", new SpeakerAdditionO(userManager));
        commandList.put("addAttendee", new AttendeeAdditionO(userManager));
        commandList.put("addVIP", new VIPAdditionO(userManager));

        commandList.put("deleteRequest", new DeleteRequest(requestManager));
        commandList.put("updateRequestStatus", new UpdateRequestStatus(requestManager));
        commandList.put("viewAllRequests", new ViewAllRequests(requestManager));
        commandList.put("viewPendingRequests", new ViewPendingRequests(requestManager));
        commandList.put("viewResolvedRequests", new ViewResolvedRequests(requestManager));
        commandList.put("viewRequest", new ViewRequest(requestManager));

    }


    /**
     * Takes a command from an Organizer and manipulates the data accordingly
     * @param command the string input by the current User
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
