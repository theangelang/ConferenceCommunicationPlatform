package controllers.messageControl;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.MessageManager;

class MarkMessage implements UserControl {
    MessageManager messageManager;
    MenuPresenter menuPresenter = new MenuPresenter();

    MarkMessage(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        try {
            if (chunks.length == 2) {
                messageManager.mark(Integer.parseInt(chunks[1]));
            }
        } catch (NumberFormatException n) {
            menuPresenter.invalidCommand();
        }

    }
}
