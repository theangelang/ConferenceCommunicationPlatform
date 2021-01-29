package controllers.messageControl;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.MessageManager;

class ArchiveMessage implements UserControl {
    MessageManager messageManager;
    MenuPresenter menuPresenter = new MenuPresenter();

    ArchiveMessage(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        try {
            if (chunks.length == 2) {
                messageManager.archive(Integer.parseInt(chunks[1]));
            } else {
                menuPresenter.invalidCommand();
            }
        } catch (NumberFormatException n) {
            menuPresenter.invalidCommand();
        }
    }
}
