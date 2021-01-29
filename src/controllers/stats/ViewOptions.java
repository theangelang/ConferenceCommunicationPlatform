package controllers.stats;

import controllers.UserControl;
import entities.Person;
import outer.MenuPresenter;
import useCases.UserManager;
import useCases.UserQueueManager;

/**
 * Class for viewing the statistic menu options
 */

public class ViewOptions implements UserControl {
    private UserQueueManager userQueueManager;
    private UserManager userManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class Constructor
     * @param userQueueManager the Use Case that handles UserQueue functions and submenu functions
     * @param userManager  the Use Case that handles and manages Users
     */

    public ViewOptions(UserManager userManager, UserQueueManager userQueueManager) {
        this.userManager = userManager;
        this.userQueueManager = userQueueManager;
    }

    /**
     * Organizer calls for statistics menu options
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command) {
        if (userManager.getUser(userManager.getCurrent()).getType() == Person.ORGANIZER) {
            menuPresenter.displayStatisticOptions(userManager.getCurrent());
        }
        else{
            menuPresenter.failedAction();
        }
    }
}
