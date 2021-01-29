package controllers.attendee;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for viewing friends list for attendees
 */

class ViewFriends implements UserControl {

    private MenuPresenter menuPresenter =  new MenuPresenter();
    private UserManager userManager;

    /**
     * Class constructor
     * @param userManager the current userManager that handles the users in the program
     */

    ViewFriends(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Attendee can view their friends list
     * @param command the string input by the current User
     */
    @Override   // not sure if this is correct
    public void parseCommand(String command) {
        List<String> friendsList = userManager.getFriendsList(userManager.getCurrent());
            menuPresenter.displayFriendsList(friendsList);
    }
}
