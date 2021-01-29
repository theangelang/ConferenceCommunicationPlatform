package controllers.stats;

import entities.Person;
import entities.SubmenuSwitch;
import outer.MenuPresenter;
import useCases.UserManager;
import useCases.UserQueueManager;

/**
 * Class organizers use to open the statistics submenu
 */

public class ViewStats implements controllers.UserControl {

    private UserManager userManager;
    private UserQueueManager userQueueManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class Constructor
     * @param userManager the Use Case that handles and manages Users
     * @param userQueueManager the Use Case that handles UserQueue functions and submenu functions
     */

    public ViewStats(UserManager userManager, UserQueueManager userQueueManager) {
        this.userManager = userManager;
        this.userQueueManager = userQueueManager;
    }

    /**
     * Organizer uses command to open the stats menu
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        if(chunks.length == 1 && userManager.getUser(userManager.getCurrent()).getType() == Person.ORGANIZER){
            if(userQueueManager.setStatsMenuStatusOn() == SubmenuSwitch.ON){
                menuPresenter.statsMenuGreeting(userManager.getCurrent());
            }
        }
        else{
            menuPresenter.invalidCommand();
        }
    }
}
