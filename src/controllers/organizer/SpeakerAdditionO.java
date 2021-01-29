package controllers.organizer;

import controllers.UserControl;
import useCases.UserManager;
import outer.MenuPresenter;

/**
 * The class that deals specifically with the addition of Speaker accounts by an Organizer
 */
class SpeakerAdditionO implements UserControl {
    private UserManager userManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * The constructor for a SpeakerAdditionO
     * @param userManager the Use Case that holds and manages User accounts
     */
    SpeakerAdditionO(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Takes an addSpeaker command and creates a Speaker account with the given username
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 2) {
            if (!userManager.addSpeaker(chunks[1])) {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.failedAction();
        }
    }
}
