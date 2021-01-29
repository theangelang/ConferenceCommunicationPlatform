package controllers.messageControl;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.MessageManager;

class GetArchive implements UserControl {
    MessageManager messageManager;
    MenuPresenter menuPresenter = new MenuPresenter();

    GetArchive(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @Override
    public void parseCommand(String command) {
        menuPresenter.displayArchivedMessages(messageManager.getMessage(messageManager.getCurrentViewed()));
    }
}
