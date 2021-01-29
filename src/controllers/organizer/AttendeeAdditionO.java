package controllers.organizer;

import controllers.UserControl;
import useCases.UserManager;
import outer.MenuPresenter;

/**
 * The class that deals specifically with the addition of an Attendee account by an Organizer
 */
class AttendeeAdditionO implements UserControl {
    private UserManager userManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * The constructor for an AttendeeAdditionO
     * @param userManager the Use Case that holds and manages User accounts
     */
    AttendeeAdditionO(UserManager userManager) {
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
            if (!userManager.addAttendee(chunks[1])) {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
