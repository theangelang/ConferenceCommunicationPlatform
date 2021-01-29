package useCases;

import entities.Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A class that holds all Events for this program
 */
public class EventManager implements Serializable {

    private List<Event> events = new ArrayList<>();  // Assuming no two events have same name

    /**
     * Returns a list of ids of the events.
     * @return list of event ids
     */
    public List<String> getEvents(){
        ArrayList<String> eventIDs = new ArrayList<>();

        for (Event event : events){
            eventIDs.add(event.getEventID());
        }
        return eventIDs;
    }

    // add this event given the speaker is not speaking somewhere else at the time, and the time does not have another event
    /**
     * Initialises a new Event with the given date, time, speaker and roomID and adds it to the list of events, and
     * adds the event to the speaker's addTalk() method. Returns false if there already exists an event with
     * the given time and speaker. Returns true if event added.
     * @param name  A string ID of the Event to pass to other classes.
     * @param date  The date of this event.
     * @param start When the event starts
     * @param end When the event ends
     * @param roomID  A string representation of the room where the event takes place.
     * @param capacity How many people are allowed at the event
     * @param speakers  A string ID of the speaker of the event
     * @return true if event is added to the list of events and event is added to speaker's addTalk() method and false otherwise
     */
    public boolean addEvent(String name, String date, String start, String end, String roomID, int capacity, String[] speakers){
        for (Event event : events) {
            if (event.getEventID().equals(name)) return false;

            if (event.getDate().equals(date) && compareTimes(new String[] {start, end}, event.getTime())) {
                for (String s : speakers) {
                    if (Arrays.asList(event.getSpeakers()).contains(s)) return false;
                }
            }
        }

        events.add(new Event(name, date, start, end, roomID, capacity, speakers));
        return true;
    }

    /**
     * removes an event from the program
     * @param name the name of the event being removed
     */
    public void removeEvent(String name) {
        events.remove(getEvent(name));
    }

    /**
     * Returns a map of the ids of events and their respective Event objects.
     * @param attendee the attendee to be added
     * @param event the event the attendee is to be added to
     * @return map of event ids and their Event objects
     */
    public boolean addAttendeeTo(String attendee, String event){
        for (Event e : events){
            if (e.getEventID().equals(event) && (!e.getAttendees().contains(attendee))){
                e.addAttendee(attendee);  // waiting for addAttendee in Event class to be changed


                // a.addEvent(event); << not needed right?
                // does UserManager's addAttendee method need to be called too?
                return true;
            }

        }
        return false;
    }

    /**
     * Removes the given attendee from the given event if the event exists, and returns true. Returns false otherwise.
     * @param attendee the attendee to be removed
     * @param event the event the attendee is to be removed from
     * @return true if attendee is removed from event, false otherwise
     */
    public boolean removeAttendeeFrom(String attendee, String event){

        for (Event e : events){
            if (e.getEventID().equals(event) && (e.getAttendees().contains(attendee))){
                return e.removeAttendee(attendee);
//                UserManager userManager = new UserManager();
//                User a = (Attendee) userManager.getUser(attendee); // why isnt this working? trying to find Attendee object that has the same name as param so I can call the removeEvent method.
            }
        }
        return false;
    }

    /**
     * Returns the Event object of the given event id if the event exists (is in the list of events).
     * @param eventID the id of the event
     * @return Event object of the event id if the event exists, and null otherwise
     */
    public Event getEvent(String eventID){
        for (Event event : events){
            if (event.getEventID().equals(eventID)){
                return event;
            }
        }
        return null;
    }

    /**
     * Changes the capacity of an event. If the capacity is set to be lower than the current amount of registered
     * attendees, attendees are removed from the event until the total enrollment is under capacity
     * @param name the name of the event
     * @param capacity the new capacity
     * @return whether or not the event name and capacity were valid, and whether or not the change could be made
     */
    public boolean changeCapacityOf(String name, int capacity) {
        for (Event e: events) {
            if (capacity > 0 && e.getEventID().equals(name)) {
                e.setCapacity(capacity);
                while (e.getAttendees().size() > capacity) {
                    removeAttendeeFrom(name, e.getAttendees().get(e.getAttendees().size() - 1));
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if the Event of given String eventID exists (is in the list of events).
     * @param eventID the id of the event
     */
    public boolean isEventID(String eventID){
        for (Event event : events){
            if (event.getEventID().equals(eventID)){
                return true;
            }
        }
        return false;
    }

    //returns whether or not two times intersect, assuming they last 1 hour
    private boolean compareTimes(String[] time1, String[] time2){
        String[] esTime = time1[0].split(":");
        String[] ecsTime = time2[0].split(":");
        String[] eeTime = time1[1].split(":");
        String[] eceTime = time2[1].split(":");

        int esHour = Integer.parseInt(esTime[0]);
        int esMin = Integer.parseInt(esTime[1]);
        int ecsHour = Integer.parseInt(ecsTime[0]);
        int ecsMin = Integer.parseInt(ecsTime[1]);
        int eeHour = Integer.parseInt(eeTime[0]);
        int eeMin = Integer.parseInt(eeTime[1]);
        int eceHour = Integer.parseInt(eceTime[0]);
        int eceMin = Integer.parseInt(eceTime[1]);

        if (ecsHour < eeHour && eeHour <= eceHour) {
            return true;
        } else if (ecsHour == eeHour) {
            return ecsMin < eeMin;
        }

        if (esHour < eceHour && eceHour <= eeHour) {
            return true;
        } else if (esHour == eceHour) {
            return esMin < eceMin;
        }

        return false;
    }

    /**
     * Returns a map of speaker and the events they are speaking at.
     * @return Map of speakers and their events
     */
    public HashMap<String, ArrayList<Event>> getSpeakerEvents (){
        HashMap<String, ArrayList<Event>> speaker_events = new HashMap<>();
        for (Event event: events){
            for (String s : event.getSpeakers()) {
                List<Event> eventList = speaker_events.computeIfAbsent(s, k -> new ArrayList<>());
                eventList.add(event);
            }
        }
        return speaker_events;
    }
}
