package controllers.organizer;

import controllers.UserControl;
import useCases.EventManager;
import outer.MenuPresenter;
import useCases.RoomManager;
import useCases.UserManager;

/**
 * The class that deals with removal of Rooms by an Organizer
 */
class CancelEvent implements UserControl {
    private EventManager eventManager;
    private UserManager userManager;
    private RoomManager roomManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * The constructor for an EventAdditionO
     * @param eventManager the Use Case that holds and manages Events
     * @param userManager the Use Case that holds and manages User accounts
     * @param roomManager the Use Case that holds and manages Rooms
     */
    CancelEvent(EventManager eventManager, UserManager userManager, RoomManager roomManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
        this.roomManager = roomManager;
    }

    /**
     * Takes an CancelEvent command, and removes an event based on the name, date, time, speaker, and room
     *  specified by the command.
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 2) {
            if (eventManager.getEvent(chunks[1]) != null) {
                roomManager.removeEventFrom(chunks[1], eventManager.getEvent(chunks[1]).getRoomID());
                for (String s : eventManager.getEvent(chunks[1]).getSpeakers()) {
                    userManager.removeEventFrom(s, chunks[1]);
                }
                for (String a : eventManager.getEvent(chunks[1]).getAttendees()) {
                    userManager.removeEventFrom(a, chunks[1]);
                }
                eventManager.removeEvent(chunks[1]);
            } else {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
