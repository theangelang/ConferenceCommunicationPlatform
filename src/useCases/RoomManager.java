package useCases;


import entities.Event;
import entities.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class that holds all Rooms for this program
 */
public class RoomManager implements Serializable {

    private List<Room> rooms = new ArrayList<>();

    /**
     * Returns a list of the names of rooms available.
     * @return the list of room names
     */
    public List<String> getRooms() {

        List<String> roomNames = new ArrayList<>();
        for (Room room : rooms) {
            roomNames.add(room.getName());
        }
        return roomNames;
    }

    /**
     * Returns a map of the names of rooms available and their respective Room objects.
     * @return a map of room names and their Room objects
     */
    public HashMap<String, Room> getRoomMap(){   // OPTIONAL, if needed by another class

        HashMap<String, Room> roomsMap = new HashMap<>();
        for (Room room : rooms){
            roomsMap.put(room.getName(), room);  // room name as key and room object as value
        }
        return roomsMap;
    }

    /**
     * Returns the Room object of the given room name if it exists.
     * @param id the name of the room
     * @return Room object of the given room name if room exists, and null otherwise
     */
    public Room getRoom(String id){

        for (Room room : rooms){
            if (room.getName().equals(id)){
                return room;
            }
        }
        return null;
    }

    /**
     * Adds the given room to the list of rooms and returns a boolean.
     * @param room the name of the room
     * @param capacity the maximum capacity of the given room
     * @return true if the room is added to the list of rooms and false otherwise
     */
    public boolean addRoom(String room, int capacity){

        if (!getRooms().contains(room)){
            rooms.add(new Room(capacity, room));   // class Room needs constructor
            return true;
        }
        return false;
    }

    /**
     * Adds the given event to the given room if no other event is taking place in the room at the same
     * time and date, and the room exists. Returns a boolean.
     * @param room the name of the room
     * @param event the name of the event to be added to the room
     * @param eventManager the use case class for managing events
     * @return true if the event was added to the room and false otherwise
     */
    public boolean addEventTo(String event, String room, EventManager eventManager){
        // assuming room already exists
        for (Room r : rooms){
            if (r.getName().equals(room)){
                for (String id : r.getEvents()) {
                    Event e = eventManager.getEvent(event);
                    Event ec = eventManager.getEvent(id);

                    if (e.getDate().equals(ec.getDate()) && compareTimes(e.getTime(), ec.getTime())) {
                        return false;
                    }
                }
                r.addEvent(event);     // Need Event entity class to initialize event
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the given event from the given room.
     * @param room the name of the room
     * @param event the event using the room
     */
    public void removeEventFrom(String event, String room) {
        for (Room r : rooms) {
            if (r.getName().equals(room)) {
                r.removeEvent(event);
            }
        }
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
}
