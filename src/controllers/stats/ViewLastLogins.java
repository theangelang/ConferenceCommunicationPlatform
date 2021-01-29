package controllers.stats;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.UserQueueManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that gets the last users to log in
 */

public class ViewLastLogins implements UserControl {
    private UserQueueManager userQueueManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class Constructor
     * @param userQueueManager the Use Case that handles UserQueue functions and submenu functions
     */

    public ViewLastLogins(UserQueueManager userQueueManager) {
        this.userQueueManager = userQueueManager;
    }

    /**
     * Organizer calculates the value of this statistic
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command){
        List<String> statsList = userQueueManager.convertQueue("loginQueue");
        if(statsList.size() != 0) {
            menuPresenter.displayStatisticList(statsList, "viewLastLogins");
        }
        else{
            menuPresenter.failedAction();
        }
    }


}
