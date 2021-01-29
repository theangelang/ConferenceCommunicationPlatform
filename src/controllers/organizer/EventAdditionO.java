package controllers.organizer;

import controllers.UserControl;
import entities.Person;
import useCases.EventManager;
import useCases.RoomManager;
import useCases.UserManager;
import outer.MenuPresenter;

/**
 * The controller class that deals specifically with the addition of events by an Organizer
 */

class EventAdditionO implements UserControl {
    private EventManager eventManager;
    private RoomManager roomManager;
    private UserManager userManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * The constructor for an EventAdditionO
     * @param eventManager the Use Case that holds and manages Events
     * @param roomManager the Use Case that holds and manages Rooms
     * @param userManager the Use Case that holds and manages User accounts
     */
    EventAdditionO(EventManager eventManager, RoomManager roomManager, UserManager userManager) {
        this.roomManager = roomManager;
        this.eventManager = eventManager;
        this.userManager = userManager;
    }

    /**
     * Takes an addEvent command, and creates an event based on the name, date, time, speaker, and room
     *  specified by the command.
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 7) {
            try {
                if (eventManager.addEvent(chunks[1], chunks[2], chunks[3], chunks[4], chunks[5],
                        Integer.parseInt(chunks[6]), new String[] {}) && roomManager.addEventTo(chunks[1], chunks[5], eventManager)) {
                } else {
                    eventManager.removeEvent(chunks[1]);
                    roomManager.removeEventFrom(chunks[1], chunks[5]);
                    menuPresenter.failedAction();
                }
            } catch(NumberFormatException n) {
                menuPresenter.invalidCommand();
            }
        } else if (chunks.length == 8) {
            String[] speakers = chunks[7].split(",");
            try {
                if (eventManager.addEvent(chunks[1], chunks[2], chunks[3], chunks[4], chunks[5],
                        Integer.parseInt(chunks[6]), speakers) && roomManager.addEventTo(chunks[1], chunks[5], eventManager)) {
                    boolean notSpeaker = false;
                    for (String s : speakers) {
                        if (userManager.getUser(s) == null || userManager.getUser(s).getType() != Person.SPEAKER) {
                            notSpeaker = true;
                        }
                    }

                    if (!notSpeaker) {
                        for (String s : speakers) {
                            userManager.addEventTo(s, chunks[1]);
                        }
                    } else {
                        eventManager.removeEvent(chunks[1]);
                        roomManager.removeEventFrom(chunks[1], chunks[5]);
                        menuPresenter.failedAction();
                    }

                } else {
                    eventManager.removeEvent(chunks[1]);
                    roomManager.removeEventFrom(chunks[1], chunks[5]);
                    menuPresenter.failedAction();
                }
            } catch (NumberFormatException n) {
                menuPresenter.invalidCommand();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
