package controllers.attendee;

import controllers.UserControl;

import useCases.*;
import outer.MenuPresenter;

/**
 * Class for event registrations for attendees
 */

class EventRegistration implements UserControl{
    private EventManager eventManager;
    private UserManager userManager;
    private BookingManager bookingManager;
    private RoomManager roomManager;
    private UserQueueManager userQueueManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class constructor
     * @param eventManager the current eventManager that handles the events in the program
     * @param userManager the current userManager that handles the users in the program
     * @param bookingManager the current bookingManager that handles bookings in the program
     * @param roomManager the current roomManager that handles rooms in the program
     */

    EventRegistration(EventManager eventManager, UserManager userManager,
                      BookingManager bookingManager, RoomManager roomManager, UserQueueManager userQueueManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
        this.bookingManager = bookingManager;
        this.roomManager = roomManager;
        this.userQueueManager = userQueueManager;
    }

    /**
     * Allows an attendee to register for an event
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        if (chunks.length == 2){
            if (eventManager.getEvent(chunks[1]) != null) {
                if (!bookingManager.book(userManager.getCurrent(), chunks[1], eventManager, roomManager, userManager)) {
                    menuPresenter.failedAction();
                }
            } else {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
        userQueueManager.getMainQueue().addTo(userManager.getCurrent(), "cancelQueue");
    }

}


