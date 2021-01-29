package controllers.attendee;

import controllers.UserControl;

import useCases.BookingManager;
import useCases.EventManager;
import useCases.UserManager;

import outer.MenuPresenter;
import useCases.UserQueueManager;

/**
 * Class for canceling event registrations for attendees
 */

class CancelRegistration implements UserControl{
    private UserQueueManager userQueueManager;
    private EventManager eventManager;
    private UserManager userManager;
    private BookingManager bookingManager = new BookingManager();
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class constructor
     * @param userManager the current userManager that handles users in the program
     * @param eventManager the current eventManager that handles events in the program
     */

    CancelRegistration(EventManager eventManager, UserManager userManager, UserQueueManager userQueueManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
        this.userQueueManager = userQueueManager;
    }

    /**
     * Attendee can cancel their registration for an event
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 2){
            if (!bookingManager.remove(userManager.getCurrent(), chunks[1], eventManager, userManager)) {
                menuPresenter.failedAction();
            }
        }  else{
            menuPresenter.invalidCommand();
        }
        userQueueManager.getMainQueue().addTo(userManager.getCurrent(), "enrolQueue");
    }

}


