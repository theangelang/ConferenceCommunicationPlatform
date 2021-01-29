package controllers.attendee;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.EventManager;
import useCases.UserManager;
import outer.HtmlSaver;
import useCases.UserQueueManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages downloading html schedules for attendees
 */
public class DownloadSchedule implements UserControl {
    private UserQueueManager userQueueManager;
    private UserManager userManager;
    private EventManager eventManager;
    private MenuPresenter menuPresenter = new MenuPresenter();
    private HtmlSaver htmlSaver = new HtmlSaver();

    /**
     * Class constructor
     * @param eventManager the current eventManager that handles the events in the program
     * @param userManager the current userManager that handles the users in the program
     * @param userQueueManager the current userQueue that handles the user which is logged in
     */
    public DownloadSchedule(UserManager userManager, EventManager eventManager, UserQueueManager userQueueManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.userQueueManager = userQueueManager;
    }


    /**
     * Downloads the list of all events in html format
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        List<String> events = userManager.getUser(userManager.getCurrent()).getEvents();
        if (events.size() == 0) {
            menuPresenter.failedAction();
        } else {
            userQueueManager.getMainQueue().addTo(userManager.getCurrent(), "downloadQueue");
            htmlSaver.saveHtml(events, eventManager);
            menuPresenter.downloadSuccess();

        }
    }
}
