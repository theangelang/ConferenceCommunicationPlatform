package controllers.attendee;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.UserManager;

/**
 * Class for adding friends for attendees
 */

public class AddFriend implements UserControl {
    private MenuPresenter menuPresenter = new MenuPresenter();
    private UserManager userManager;

    /**
     * Class constructor
     * @param userManager the current userManager that handles the users in the program
     */

    AddFriend(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Attendee can add a friend to their friends list
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        if (chunks.length == 2) {
            userManager.addFriend(userManager.getCurrent(), chunks[1]); // how to do this?
        }

    }
}
