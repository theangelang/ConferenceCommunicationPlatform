package controllers.organizer;

import controllers.UserControl;
import useCases.*;
import outer.MenuPresenter;

/**
 * The class that deals specifically with the mass messaging command for Organizers
 */
class MassMessageO implements UserControl {
    private MessageManager messageManager;
    private UserManager userManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * The constructor for a MassMessageO
     * @param messageManager the Use Case that holds and manages MessageChains
     * @param userManager the Use Case that holds and manages User accounts
     */
    MassMessageO(MessageManager messageManager, UserManager userManager) {
        this.messageManager = messageManager;
        this.userManager = userManager;
    }

    /**
     * Takes a massMessage command and messages the given message to the given type of User account
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length > 2) {
            String message = "";

            for (int i = 2; i < chunks.length; i++) {
                message += " " + chunks[i];
            }

            if (chunks[1].equals("speaker")) {
                for (String s : userManager.getSpeakers()) {
                    String[] participants = {userManager.getCurrent(), s};
                    messageManager.sendMessage(userManager.getCurrent(), participants, message, userManager);
                }
            } else if (chunks[1].equals("attendee")) {
                for (String a : userManager.getAttendees()) {
                    String[] participants = {userManager.getCurrent(), a};
                    messageManager.sendMessage(userManager.getCurrent(), participants, message, userManager);
                }
            } else {
                menuPresenter.invalidCommand();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
