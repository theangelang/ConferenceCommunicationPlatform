package controllers.messageControl;

import controllers.UserControl;
import entities.UserQueue;
import outer.MenuPresenter;
import useCases.MessageManager;
import useCases.UserManager;
import useCases.UserQueueManager;

import java.util.TreeMap;

public class MessageControl implements UserControl {
    private UserQueueManager userQueueManager;
    UserManager userManager;
    MessageManager messageManager;
    MenuPresenter menuPresenter = new MenuPresenter();
    private TreeMap<String, UserControl> commandList = new TreeMap<>();


    public MessageControl(UserManager userManager, MessageManager messageManager, UserQueueManager userQueueManager) {
        this.messageManager = messageManager;
        this.userManager = userManager;
        this.userQueueManager = userQueueManager;

        commandList.put("viewMessage", new ViewMessage(messageManager, userManager));
        commandList.put("sendMessage", new SendMessage(userManager, messageManager, userQueueManager));
        commandList.put("markMessage", new MarkMessage(messageManager));
        commandList.put("archiveMessage", new ArchiveMessage(messageManager));
        commandList.put("close", new CloseMessage(messageManager, userManager));
        commandList.put("viewArchive", new GetArchive(messageManager));

    }

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (commandList.get(chunks[0])==null) {
            menuPresenter.invalidCommand();
        } else {
            commandList.get(chunks[0]).parseCommand(command);
        }
    }
}
