package controllers.attendee;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for viewing recommended friends list for attendees
 */

class ViewRecommendedFriends implements UserControl {

    private MenuPresenter menuPresenter =  new MenuPresenter();
    private UserManager userManager;

    /**
     * Class constructor
     * @param userManager the current userManager that handles the users in the program
     */

    ViewRecommendedFriends(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Attendee can view their recommended friends list
     * @param command the string input by the current User
     */
    @Override  // not sure if this is correct
    public void parseCommand(String command) {
        userManager.updateRecommendedFriends(userManager.getCurrent());
        menuPresenter.displayRecommendedFriends(userManager.getUser(userManager.getCurrent()).getRecommendedFriends());
    }
}
