package controllers;

/**
 * The interface for controllers
 */
public interface UserControl {
    /**
     * A method that will interpret a command from a User
     * @param command the string input by the current User
     */

    void parseCommand(String command);
}
