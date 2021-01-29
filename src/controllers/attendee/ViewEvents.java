package controllers.attendee;

import controllers.UserControl;

import useCases.EventManager;
import outer.MenuPresenter;

/**
 * Class that lets attendees view events
 */

class ViewEvents implements UserControl{
    private EventManager eventManager;
    private MenuPresenter menuPresenter =  new MenuPresenter();

    /**
     * Class constructor
     * @param eventManager the current eventManager that manages events in the program
     */
    ViewEvents(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * Displays all events
     * @param command the string input by the current User
     */
    public void parseCommand(String command) {
        menuPresenter.displayEvents(eventManager.getEvents(), eventManager);
    }
}

