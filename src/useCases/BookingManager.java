package useCases;

import entities.Person;
import entities.Room;
import entities.Event;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Class for booking an attendee for an event.
 */

public class BookingManager implements Serializable {

    /**Book an attendee for an event if event is not full.
     * @param attendee the string ID for the attendee
     * @param event the string ID for an event
     * @param eventManager current instance of EventManager
     * @param roomManager current instance of RoomManager
     * @param userManager current instance of UserManager
     * @return true if attendee is booked for event or false otherwise.
     */
    public boolean book(String attendee, String event, EventManager eventManager,
                        RoomManager roomManager, UserManager userManager) {
        if (canBook(event, eventManager, roomManager)) {
            userManager.addEventTo(attendee, event);
            return eventManager.addAttendeeTo(attendee, event);
        } else if(userManager.getUser(attendee).getType() == Person.VIP) {
            for (int i = eventManager.getEvent(event).getAttendees().size() - 1; i >= 0;i--) {
                if (userManager.getUser(eventManager.getEvent(event).getAttendees().get(i)).getType() != Person.VIP) {
                    String goodbye = eventManager.getEvent(event).getAttendees().get(i);
                    eventManager.removeAttendeeFrom(goodbye, event);
                    userManager.removeEventFrom(goodbye, event);
                    userManager.addEventTo(attendee, event);
                    return eventManager.addAttendeeTo(attendee, event);
                }
            }
        }
        return false;
    }

    /**Remove an attendee for an event they are signed up for.
     * @param attendee the string ID for the attendee
     * @param event the string ID for an event
     * @param eventManager current instance of EventManager
     * @param userManager current instance of UserManager
     * @return return true if attendee is removed from event and false otherwise.
     */
    public boolean remove(String attendee, String event, EventManager eventManager, UserManager userManager) {
        userManager.getUser(attendee).removeEvent(event);
        return eventManager.removeAttendeeFrom(attendee, event);
    }

    //Checks if event is full.
    private boolean canBook(String event, EventManager eventManager, RoomManager roomManager) {
        int registered = getAttendeesRegistered(event, eventManager);
        return getRoomCapacity(event, eventManager, roomManager) > registered &&
                eventManager.getEvent(event).getCapacity() > registered;
    }

    //Returns Room capacity for an event
    private int getRoomCapacity(String event, EventManager eventManager, RoomManager roomManager) {
        Event eventObj = eventManager.getEvent(event);
        String roomStr = eventObj.getRoomID();
        HashMap<String, Room> roomMap = roomManager.getRoomMap();
        Room roomObject = roomMap.get(roomStr);
        return roomObject.getCapacity();
    }

    // returns count of attendees currently registered for an event
    private int getAttendeesRegistered(String event, EventManager eventManager) {
        Event eventObj = eventManager.getEvent(event);
        List<String> attendees = eventObj.getAttendees();
        return attendees.size();
    }
}


