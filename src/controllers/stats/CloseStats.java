package controllers.stats;

import controllers.UserControl;
import entities.SubmenuSwitch;
import outer.MenuPresenter;
import useCases.UserManager;
import useCases.UserQueueManager;

/**
 * Class for closing the statistics submenu
 */

public class CloseStats implements UserControl {
    private UserQueueManager userQueueManager;
    private UserManager userManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class constructor
     * @param userManager the current userManager that handles users in the program
     * @param userQueueManager the current userQueueManager that handles userQueue functions and submenu functions
     */

    public CloseStats(UserManager userManager, UserQueueManager userQueueManager) {
        this.userManager = userManager;
        this.userQueueManager = userQueueManager;
    }

    /**
     * Organizer can close the statistics submenu to go back to the main menu
     * @param command the string input by the current User
     */

    @Override
    public void parseCommand(String command) {
        if(userQueueManager.setStatsMenuStatusOff() != SubmenuSwitch.OFF){
            menuPresenter.invalidCommand();
        }
        else{
            menuPresenter.subMenuCloseMenu(userManager.getCurrent());
        }

    }
}
