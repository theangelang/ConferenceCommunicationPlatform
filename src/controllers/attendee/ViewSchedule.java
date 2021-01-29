package controllers.attendee;

import controllers.UserControl;

import useCases.*;

import outer.MenuPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages viewing schedules for attendees
 */

class ViewSchedule implements UserControl {
    private UserManager userManager;
    private EventManager eventManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class constructor
     * @param eventManager the current eventManager that handles the events in the program
     * @param userManager the current userManager that handles the users in the program
     */

    ViewSchedule(UserManager userManager, EventManager eventManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
    }

    /**
     * Displays the list of all events
     * @param command the string input by the current User
     */

    public void parseCommand(String command) {
        List<String> events = userManager.getUser(userManager.getCurrent()).getEvents();
        if (events.size() == 0) {
            menuPresenter.failedAction();
        } else {
            menuPresenter.displayEvents(events, eventManager);
        }
    }
}
