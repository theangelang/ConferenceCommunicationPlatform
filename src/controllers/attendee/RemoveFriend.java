package controllers.attendee;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.UserManager;

/**
 * Class for adding friends for attendees
 */

public class RemoveFriend implements UserControl {
    private MenuPresenter menuPresenter = new MenuPresenter();
    private UserManager userManager;

    /**
     * Class constructor
     * @param userManager the current userManager that handles the users in the program
     */

    RemoveFriend(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Attendee can remove a friend from their friends list
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        if (chunks.length == 2) {
            userManager.removeFriend(userManager.getCurrent(), chunks[1]); // how to do this?
        }

    }
}
