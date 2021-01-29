package controllers.speaker;

import controllers.UserControl;
import entities.Event;
import useCases.EventManager;
import useCases.MessageManager;
import useCases.UserManager;
import outer.MenuPresenter;
/**
 * Class for Speakers mass Messaging
 */

class MassMessage implements UserControl{
    private MessageManager messageManager;
    private EventManager eventManager;
    private UserManager userManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class Constructor
     * @param eventManager the current eventManager that handles the events in the program
     * @param userManager the current userManager that handles the users in the program
     * @param messageManager the current messageManager that handles the messages in the program
     */
    MassMessage(MessageManager messageManager, EventManager eventManager, UserManager userManager) {
        this.messageManager = messageManager;
        this.eventManager = eventManager;
        this.userManager = userManager;
    }

    /**
     * Allows Speakers to mass message all the attendee for an event only if it is their event
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        Event event = eventManager.getEvent(chunks[1]);

        if (chunks.length > 2) {
            String message = "";

            for (int i = 2; i < chunks.length; i++) {
                message += " " + chunks[i];
            }

            if (userManager.getUser(userManager.getCurrent()).getEvents().contains(chunks[1])) {
                for (String s : event.getAttendees()){
                    String[] participants = {userManager.getCurrent(), s};
                    messageManager.sendMessage(userManager.getCurrent(), participants, message, userManager);
                    menuPresenter.displayMessages(messageManager.getMessage(participants));
                }
            } else {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
