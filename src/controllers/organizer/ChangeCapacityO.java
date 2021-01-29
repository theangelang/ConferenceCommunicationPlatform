package controllers.organizer;

import controllers.UserControl;
import useCases.EventManager;
import useCases.RoomManager;
import useCases.UserManager;
import outer.MenuPresenter;

/**
 * The controller class that deals specifically with the addition of events by an Organizer
 */

class ChangeCapacityO implements UserControl {
    private EventManager eventManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * The constructor for an ChangeCapacityO
     * @param eventManager the Use Case that holds and manages Events
     */
    ChangeCapacityO(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * Takes an ChangeCapacity command, and creates an event based on the name, date, time, speaker, and room
     *  specified by the command.
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        try {
            if (chunks.length == 3) {
                if (!eventManager.changeCapacityOf(chunks[1], Integer.parseInt(chunks[2]))){
                    menuPresenter.failedAction();
                }
            } else {
                menuPresenter.invalidCommand();
            }
        } catch (NumberFormatException n) {
            menuPresenter.invalidCommand();
        }
    }
}
