package controllers.messageControl;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.MessageManager;
import useCases.UserManager;
import useCases.UserQueueManager;

public class SendMessage implements UserControl {
    private UserQueueManager userQueueManager;
    MessageManager messageManager;
    UserManager userManager;
    MenuPresenter menuPresenter = new MenuPresenter();

    SendMessage(UserManager userManager, MessageManager messageManager, UserQueueManager userQueueManager) {
        this.messageManager = messageManager;
        this.userManager = userManager;
        this.userQueueManager = userQueueManager;
    }

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length > 1) {
            String message = "";

            for (int i = 1; i < chunks.length; i++) {
                message += " " + chunks[i];
            }

            String[] participants = messageManager.getCurrentViewed();

            messageManager.sendMessage(userManager.getCurrent(), participants, message, userManager);
            menuPresenter.displayMessages(messageManager.getMessage(participants));

            userQueueManager.getMainQueue().addTo(userManager.getCurrent(), "messageQueue");
        }
    }
}
