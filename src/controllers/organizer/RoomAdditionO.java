package controllers.organizer;

import controllers.UserControl;
import useCases.RoomManager;
import outer.MenuPresenter;

/**
 * The class that deals specifically with the addition of Rooms by an Organizer
 */
class RoomAdditionO implements UserControl {
    private RoomManager roomManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * The constructor for a RoomAdditionO
     * @param roomManager the Use Case that holds and manages Rooms
     */
    RoomAdditionO(RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    /**
     * Takes a addRoom command and creates a room with the given name and capacity
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");

        if (chunks.length == 3) {
            char[] cap = chunks[2].toCharArray();
            int capacity = 0;
            boolean allInts = true;

            for (char c : cap) {
                capacity = capacity * 10 + (c - 48);
                if (c-48 < 0 || c-48 > 9) allInts = false;
            }

            if (allInts && roomManager.addRoom(chunks[1], capacity)) {
            } else {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
