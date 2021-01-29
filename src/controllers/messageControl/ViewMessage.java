package controllers.messageControl;

import controllers.UserControl;
import entities.Person;
import outer.MenuPresenter;
import useCases.MessageManager;
import useCases.UserManager;

import java.util.Arrays;

class ViewMessage implements UserControl {
    MessageManager messageManager;
    UserManager userManager;
    MenuPresenter menuPresenter = new MenuPresenter();

    ViewMessage(MessageManager messageManager, UserManager userManager) {
        this.userManager = userManager;
        this.messageManager = messageManager;
    }

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 2) {
            String[] participants = {userManager.getCurrent(), chunks[1]};

            Person[] pairing = {userManager.getUser(userManager.getCurrent()).getType(),
                    userManager.getUser(chunks[1]).getType()};
            Person[] bad = {Person.ATTENDEE, Person.SPEAKER};

            if (!Arrays.deepEquals(pairing, bad)) {
                if (!messageManager.setCurrentViewed(participants)) {
                    messageManager.createMessageChain(participants);
                    messageManager.setCurrentViewed(participants);
                }
                menuPresenter.displayMessages(messageManager.getMessage(participants));
            } else {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
