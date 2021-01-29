package controllers.attendee;

import controllers.UserControl;

import entities.Event;
import useCases.EventManager;
import useCases.UserManager;
import outer.MenuPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Class for viewing events associated with a speaker
 */
public class ViewSpeakerEvents implements UserControl {
    private EventManager eventManager;
    private UserManager userManager;
    private MenuPresenter menuPresenter =  new MenuPresenter();
    /**
     * Class constructor
     * @param userManager the current userManager that handles the users in the program
     * @param eventManager the current eventManager that handles the events in the program
     */
    public ViewSpeakerEvents(EventManager eventManager, UserManager userManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
    }
    /**
     * Attendee can view the events associated with speakers
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        HashMap<String, ArrayList<Event>> speakerEvents = eventManager.getSpeakerEvents();
        menuPresenter.displaySpeakerEvents(speakerEvents);
    }

}

