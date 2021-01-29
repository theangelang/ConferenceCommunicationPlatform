package controllers.attendee;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.EventManager;
import useCases.UserManager;

public class ViewRecommendedEvents implements UserControl {
    UserManager userManager;
    EventManager eventManager;
    MenuPresenter menuPresenter = new MenuPresenter();

    ViewRecommendedEvents(UserManager userManager, EventManager eventManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
    }

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 1) {
            menuPresenter.displayRecommendedEvents(userManager.getRecommendedEvents(userManager.getCurrent()), eventManager);
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
