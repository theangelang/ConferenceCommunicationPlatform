package useCases;

import entities.Person;
import entities.User;

import java.io.Serializable;
import java.util.*;

/**
 * A class that holds all Users for this program
 */

public class UserManager implements Serializable {
    private List<User> users = new ArrayList<>();
    private User current;

    /**
     * The constructor for a blank UserManager
     */
    public UserManager() {
        users.add(new User("0000", Person.ORGANIZER));
    }

    /**
     * Returns the username of the User who is currently logged in
     * @return the ID of the user who is currently logged in, returns null if nobody is logged in
     */
    public String getCurrent() {
        if (current != null) {
            return current.getID();
        } else {
            return null;
        }
    }

    /**
     * Sets a User as the User who is currently logged in. If the empty string is passed,
     * the current user is set to null
     * @param id the username of the User who is logged in
     * @return true if a User was set as current, or current was set to null, false otherwise
     */
    public boolean setCurrent(String id) {
        if (id.equals("LOGOUT")) {
            current = null;
            return true;
        }

        for (User u : users) {
            if (u.getID().equals(id)) {
                current = u;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an ArrayList of the usernames of every Speaker account
     * @return an ArrayList of the usernames of every Speaker account
     */
    public List<String> getSpeakers() {
        ArrayList<String> speakers = new ArrayList<>();

        for (User u : users) {
            if (u.getType()==Person.SPEAKER) {
                speakers.add(u.getID());
            }
        }
        return speakers;
    }

    /**
     * Returns an ArrayList of the usernames of every Attendee account
     * @return an ArrayList of the usernames of every Attendee account
     */
    public List<String> getAttendees() {
        ArrayList<String> attendees = new ArrayList<>();

        for (User u : users) {
            if (u.getType() == Person.ATTENDEE) {
                attendees.add(u.getID());
            }
        }

        return attendees;
    }

    /**
     * Adds an Attendee account if the username chosen was not already taken
     * @param id the username of the Attendee account that will be created
     * @return true if the Attendee account was added, false otherwise
     */
    public boolean addAttendee(String id) {
        for (User u : users) {
            if (u.getID().equals(id)) {
                return false;
            }
        }

        users.add(new User(id, Person.ATTENDEE));
        return true;
    }

    /**
     * Adds a Speaker account is the username chosen was not already taken
     * @param id the username of the Speaker account that will be created
     * @return true if the Speaker account was added, false otherwise
     */
    public boolean addSpeaker(String id) {
        for (User u : users) {
            if (u.getID().equals(id)) {
                return false;
            }
        }

        users.add(new User(id, Person.SPEAKER));
        return true;
    }

    public boolean addVIP(String id) {
        for (User u : users) {
            if (u.getID().equals(id)) {
                return false;
            }
        }

        users.add(new User(id, Person.VIP));
        return true;
    }

    /**
     * Returns the instance of the User class with the username provided
     * @param id the username of the User account
     * @return the User with the username id, null if such an account doesn't exist
     */
    public User getUser(String id) {
        for (User u : users) {
            if (u.getID().equals(id)) {
                return u;
            }
        }

        return null;
    }

    /**
     * Adds an event to a user if that user doesn't already have that event. NOTE: only used for adding events to speakers,
     * for registering attendees in event, use BookingManager
     * @param user the username of the User
     * @param event the name of the Event
     */
    public void addEventTo(String user, String event) {
        for (User u : users) {
            if (u.getID().equals(user) && !u.getEvents().contains(event)) {
                u.addEvent(event);
                u.removeRecommendedEvent(event);
            }
        }
    }

    /**
     * Removes the given event from the given user. NOTE: only used for removing events from speakers, to remove an event
     * from an attendee, use BookingManager
     * @param user the username of the user
     * @param event the name event being removed
     * @return whether or not the username was valid and the event could be removed
     */
    public boolean removeEventFrom(String user, String event) {
        User u = getUser(user);

        if (u != null){
            u.removeEvent(event);
            return true;
        }
        return false;
    }

    /**
     * Returns a list of usernames for users that are friends with user
     * @param user the user who's friends list is being returned
     * @return user's friends list
     */
    public List<String> getFriendsList(String user){
        for (User u : users){
            if (u.getID().equals(user)){
                return u.getFriendsList();
            }
        }
        return null;
    }

    /** Returns true if friend is successfully added to the user's friends list. Returns false otherwise.
     * @param user the username of the User account
     * @param friend the username of the account of the friend to be added
     * @return true if friend is added to user's friend list, false otherwise
     */
    public boolean addFriend(String user, String friend){
        User u = getUser(user);

        User f = getUser(friend);

        if (u != null && f != null) {
            u.addToFriendsList(friend);
            return true;
        }

        return false;
    }

    /** Returns true if friend is successfully removed from the user's friends list. Returns false otherwise.
     * @param user the username of the User account
     * @param friend the username of the account of the friend to be removed
     * @return true if friend is removed from user's friend list, false otherwise
     */
    public boolean removeFriend(String user, String friend){
        User u = getUser(user);

        User f = getUser(friend);

        if (u != null && f != null) {
            u.removeFromFriendsList(friend);
            return true;
        }

        return false;
    }

    /**
     * Recommends and event to receiver from user iff they are on each other's friend list
     * @param user the username of the user sending the recommendation
     * @param receiver the username of the user receiving the recommendation
     * @param event the name of the event being recommended
     * @return whether or not the event was recommended successfully
     */

    public boolean recommendEvent(String user, String receiver, String event) {
        User u = getUser(user);
        User r = getUser(receiver);

        if (u != null && r != null && u.getFriendsList().contains(receiver) && r.getFriendsList().contains(user)) {
            if (r.getRecommendedEvents().get(user) == null) {
                r.addRecommendedEvent(user, event);
                return true;
            } else if (!r.getRecommendedEvents().get(user).contains(event)) {
                r.addRecommendedEvent(user, event);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the recommended events for user
     * @param user the username of the user who's recommended events are being returned
     * @return the recommended events of user
     */
    public Map<String, List<String>> getRecommendedEvents(String user) {
        User u = getUser(user);
        if (u != null) {
            return u.getRecommendedEvents();
        }
        return null;
    }

    /**
     * Updates user's recommended friends based on common events
     * @param user the username of the user who's recommended friends is being updated
     */
    public void updateRecommendedFriends(String user) {
        if (getUser(user) != null && getUser(user).getType() == Person.ATTENDEE) {
            User curr = getUser(user);

            ArrayList<String> bestFriends = new ArrayList<>();
            HashMap<Integer, ArrayList<String>> candidates = new HashMap<>();

            for (User u : users) {
                if (!u.getID().equals(user) && u.getType()==Person.ATTENDEE) {
                    int count = 0;
                    for (String e : u.getEvents()) {
                        if (curr.getEvents().contains(e)) count ++;
                    }
                    if (candidates.containsKey(count)) {
                        candidates.get(count).add(u.getID());
                    } else {
                        ArrayList<String> ids = new ArrayList<>();
                        ids.add(u.getID());
                        candidates.put(count, ids);
                    }
                }
            }

            Integer[] scores = candidates.keySet().toArray(new Integer[0]);
            Arrays.sort(scores);
            int i = scores.length - 1;

            int attendees = -1;

            for (User u : users) {
                if (u.getType() == Person.ATTENDEE) attendees ++;
            }

            while (bestFriends.size() < 10 && bestFriends.size() < attendees) {
                bestFriends.addAll(candidates.get(scores[i]));
                i --;
            }

            curr.setRecommendedFriends(bestFriends);
        }
    }


}
