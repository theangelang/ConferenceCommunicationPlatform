package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An instance of this Class represents a Room where an event can take place at the conference
 */
public class Room implements Serializable {
    private int capacity;
    private String roomID;
    private List<String> events = new ArrayList<>();
    /** Class constructor
     * Creates an Arraylist to hold the attendees of the Event.
     * @param capacity  The number of attendees that the room can hold.
     * @param roomID The id of the room
     */

    public Room(int capacity, String roomID) {
        this.capacity = capacity;
        this.roomID = roomID;
    }
    /**
     * @return List of the events of the room
     */

    public List<String> getEvents() {
        return events;

    }
    /**
     * Allows an event to be added to the list of events taking place in this room
     * @param event is the string representation of the event id that will be added to the list of events
     */
    public void addEvent(String event) {
        events.add(event);
    }

    public void removeEvent(String event) {
        events.remove(event);
    }

    /**
     * @return the capacity of the room
     */

    public int getCapacity() {
        return capacity;
    }

    /**
     * @return the roomID of the room
     */

    public String getName() {
        return roomID;
    }
}
