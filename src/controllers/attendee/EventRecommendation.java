package controllers.attendee;

import controllers.UserControl;
import entities.Person;
import useCases.UserManager;
import useCases.EventManager;
import outer.MenuPresenter;
import useCases.ReviewManager;

/**
 * Class for viewing recommended events list for attendees based on their speaker ratings
 */
class EventRecommendation implements UserControl {
    private EventManager eventManager;
    private ReviewManager reviewManager;
    private UserManager userManager;
    private MenuPresenter menuPresenter =  new MenuPresenter();

    /**
     * Class constructor
     * @param userManager the current userManager that handles the users in the program
     * @param reviewManager the current reviewManager for this user
     * @param eventManager the current eventManager that handles the events for the user
     */
    public EventRecommendation(EventManager eventManager, ReviewManager reviewManager, UserManager userManager) {
        this.eventManager = eventManager;
        this.reviewManager = reviewManager;
        this.userManager = userManager;
    }
    /**
     * Attendee can view their recommended event list based on speaker reviews
     * @param command the string input by the current User
     */
    @Override

    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        if (chunks.length == 1 && userManager.getUser(userManager.getCurrent()).getType() == Person.ATTENDEE) {
            menuPresenter.displaySpeakerRecommendedEvents(reviewManager.getTopSpeakerEvents(userManager.getCurrent(), eventManager.getSpeakerEvents()));
        } else if (chunks.length == 1 && !(userManager.getUser(userManager.getCurrent()).getType() == Person.ATTENDEE)){
            menuPresenter.failedAction();
        } else {
            menuPresenter.invalidCommand();
        }

    }
}
