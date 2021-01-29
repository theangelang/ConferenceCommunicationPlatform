package controllers.messageControl;

import controllers.UserControl;
import useCases.MessageManager;
import useCases.UserManager;

class CloseMessage implements UserControl {
    MessageManager messageManager;
    UserManager userManager;

    CloseMessage(MessageManager messageManager, UserManager userManager) {
        this.messageManager = messageManager;
        this.userManager = userManager;
    }

    @Override
    public void parseCommand(String command) {
        String other = "";

        for (String s : messageManager.getCurrentViewed()) {
            if (!s.equals(userManager.getCurrent())){
                other = s;
            }
        }
        messageManager.clearCurrentViewed(other);
    }
}
