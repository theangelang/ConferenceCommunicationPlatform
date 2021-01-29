package controllers.attendee;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.EventManager;
import useCases.UserManager;

public class RecommendEvent implements UserControl {
    UserManager userManager;
    EventManager eventManager;
    MenuPresenter menuPresenter = new MenuPresenter();

    RecommendEvent(UserManager userManager, EventManager eventManager) {
        this.userManager = userManager;
        this.eventManager = eventManager;
    }

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 3) {
            if (eventManager.getEvent(chunks[2]) != null) {
                if (!userManager.recommendEvent(userManager.getCurrent(), chunks[1], chunks[2])) {
                    menuPresenter.failedAction();
                }
            } else {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
