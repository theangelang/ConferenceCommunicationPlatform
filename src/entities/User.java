package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *  A class that represents a real-life person at this conference.
 */

public class User implements Serializable {

    private String username;
    private Person usertype;
    private List<String> events = new ArrayList<>();
    private List<String> friendsList = new ArrayList<>();
    private List<String> recommendedFriends = new ArrayList<>();
    private Map<String, List<String>> recommendedEvents = new TreeMap<>();

    /**
     * Creates an instance of User.
     * A username user type is required to create an instance of User.
     * @param username  The username associated with this user.
     * @param usertype  The type of person at this event associated with this user.
     */
    public User(String username, Person usertype){
        this.username = username;
        this.usertype = usertype;
    }

    /**
     * Returns the username of the User.
     * @return username The username associated with this user.
     */
    public String getID(){
        return this.username;
    }

    /**
     * Returns the events this user is associated with.
     * @return events The events this user is involved in.
     */
    public List<String> getEvents(){ return events; }

    /**
     * Adds an event to the events this user is involved with.
     * @param event The event to be added to the events the user is involved with.
     */
    public void addEvent(String event){ events.add(event); }

    /**
     * Removes an event from the events this user is involved with.
     * @param event The event to be removed from the events the user is involved with.
     */
    public void removeEvent(String event){ events.remove(event); }


    public Person getType() {
        return usertype;
    }


    /** Returns a list of the usernames of this user's friends.
     * @return List of friends this user has
     */
    public List<String> getFriendsList(){
        return friendsList;
    }


    /** Returns a list of the usernames of this user's recommended friends.
     * @return List of recommended friends for this user
     */
    public List<String> getRecommendedFriends() {
        return recommendedFriends;
    }


    /** Removes the given user from this user's friends list.
     * @param user the user to be removed
     */
    public void removeFromFriendsList(String user){ friendsList.remove(user); }


    /** Adds the given user to this user's friends list.
     * @param user the user to be added
     */
    public void addToFriendsList(String user){ friendsList.add(user); }


    /** Updates the list of this user's recommended friends.
     * @param recommendedFriends the new list of this user's recommended friends
     */
    public void setRecommendedFriends(List<String> recommendedFriends){ this.recommendedFriends = recommendedFriends; }

    /**
     * Adds a recommended event from a user
     * @param user the username of the user who recommended the event
     * @param event the name of the event that was recommended
     */
    public void addRecommendedEvent(String user, String event) {
        if (recommendedEvents.containsKey(user)) {
            recommendedEvents.get(user).add(event);
        } else {
            List<String> events = new ArrayList<>();
            events.add(event);

            recommendedEvents.put(user, events);
        }
    }

    /**
     * Removes an event from the recommended events
     * @param event the event that will be removed
     */
    public void removeRecommendedEvent(String event) {
        for (List<String> l : recommendedEvents.values()) {
            l.remove(event);
        }
    }

    public Map<String, List<String>> getRecommendedEvents() {
        return recommendedEvents;
    }
}

