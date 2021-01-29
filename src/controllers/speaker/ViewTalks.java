package controllers.speaker;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for viewing talks
 */

class ViewTalks implements UserControl {
    private UserManager userManager;
    private EventManager eventManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class constructor
     * @param userManager the Use Case that holds and manages User accounts
     * @param eventManager the Use Case that holds and manages Events
     */

    ViewTalks(UserManager userManager, EventManager eventManager){
        this.userManager = userManager;
        this.eventManager = eventManager;
    }

    /**
     * Displays all the talks of a Speaker
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        List<String> talks = userManager.getUser(userManager.getCurrent()).getEvents();
        menuPresenter.displayEvents(talks, eventManager);
    }


}
