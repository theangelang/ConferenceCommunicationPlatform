package controllers.organizer;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.UserManager;

public class VIPAdditionO implements UserControl {
    private UserManager userManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * The constructor for a VIPAdditionO
     * @param userManager the Use Case that holds and manages User accounts
     */
    VIPAdditionO(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Takes an addAttendee command and creates an Attendee account with the given username
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 2) {
            if (!userManager.addVIP(chunks[1])) {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
