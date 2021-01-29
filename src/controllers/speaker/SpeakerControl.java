package controllers.speaker;

import controllers.UserControl;
import controllers.request.CreateRequest;
import useCases.*;
import outer.MenuPresenter;
import java.util.TreeMap;

/**
 * Class for Speaker Commands
 */

public class SpeakerControl implements UserControl {
    private MenuPresenter menuPresenter = new MenuPresenter();

    private TreeMap<String, UserControl> commandList =  new TreeMap<>();

    /**
     * Class Constructor
     * @param messageManager the Use Case that holds and manages MessageChains
     * @param bookingManager the Use Case that deals with Attendee account bookings
     * @param roomManager the Use Case that holds and manages Rooms
     * @param eventManager the Use Case that holds and manages Events
     * @param userManager the Use Case that holds and manages User accounts
     */
    public SpeakerControl(MessageManager messageManager, UserManager userManager, BookingManager bookingManager,
                           RoomManager roomManager, EventManager eventManager, RequestManager requestManager) {
        commandList.put("seeMyTalks", new ViewTalks(userManager, eventManager));
        commandList.put("messageAttendees", new MassMessage(messageManager, eventManager, userManager));
        commandList.put("createRequest", new CreateRequest(requestManager));
    }

    /**
     * Takes a command from the Attendee and returns invalid command if command is not one of the options allowed
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        String[] commandChunks = command.split(" ");

        if (commandList.get(commandChunks[0])==null){
            menuPresenter.invalidCommand();
        } else {
            commandList.get(commandChunks[0]).parseCommand(command);
        }
    }
}
