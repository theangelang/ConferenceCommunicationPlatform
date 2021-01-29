package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An instance of this Class represents an event taking place at the conference
 */

public class Event implements Serializable {
    private String roomID;
    private List< String > attendees = new ArrayList<>();
    private String date;
    private String start;
    private String end;
    private String eventID;
    private int capacity;
    private String[] speakers;
    /** Class constructor
     * Creates an Arraylist to hold the attendees of the Event.
     * @param eventID  A string ID of the Event to pass to other classes.
     * @param date  The date of this event.
     * @param start When the event starts
     * @param end When the event ends
     * @param roomID  A string representation of the room where the event takes place.
     * @param capacity How many people are allowed at the event
     * @param speakers  A string ID of the speaker of the event
     */


    public Event(String eventID, String date, String start, String end, String roomID, int capacity, String[] speakers) {
        this.eventID = eventID;
        this.date = date;
        this.start = start;
        this.end = end;
        this.roomID = roomID;
        this.capacity = capacity;
        this.speakers = speakers;

    }
    /**
     * @return List of the attendees of the event
     */
    public List<String> getAttendees() {
        return attendees;
    }

    /**
     * Allows an attendee to be added to the list of attendees
     * @param attendee is a string representation of a user of attendee type who will be added to the list
     */

    public void addAttendee(String attendee) {
        attendees.add(attendee);
    }

    /**
     * @return the date and time of the event
     */
    public String[] getTime() {
        return new String[] {start, end};
    }

    /**
     * @return the date of the event
     */
    public String getDate() { return date;}

    /**
     * @return the speaker ID of the event
     */
    public String[] getSpeakers() {
        return speakers;
    }

    /**
     * @return the room ID of the event
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * @return the event ID of the event
     */
    public String getEventID() {return eventID;}

    /**
     * @return the current capacity of the event
     */
    public int getCapacity() {return capacity;}

    /**
     * Sets the current capacity of the event
     * @param capacity What the capacity will be set to
     */
    public void setCapacity(int capacity) {this.capacity = capacity;}

    /**
     * @return True if the attendee was removed from the event list
     * @param attendee is a user of type attendee who will be removed from the list
     */
    public boolean removeAttendee(String attendee) {
        return attendees.remove(attendee);
    }

    /**
     * @return a string representation of the eventID, date, time, speakerID, and roomID in that order.
     */
    public String toString() {
        StringBuilder speak = new StringBuilder();
        for (String s : speakers) {
            speak.append(", ").append(s);
        }

        return eventID + ": " + date + " " + start + " - " + end + speak + ", in " + roomID + ", MAX: " + capacity;
    }
}
