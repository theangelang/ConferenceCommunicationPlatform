package entities;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * A class that holds all app traffic information for all users.
 *
 * loginQueue tracks the last users to login
 * cancelQueue tracks the last users to cancel their enrolment in an event
 * enrolQueue tracks the last users to enrol in an event
 * downloadQueue tracks the last users to download a schedule
 * messageQueue tracks the last users to send a message
 */

public class UserQueue implements Serializable {
    private int capacity;
    private LinkedList<String> loginQueue;
    private LinkedList<String> cancelQueue;
    private LinkedList<String> enrolQueue;
    private LinkedList<String> downloadQueue;
    private LinkedList<String> messageQueue;

    /**
     * Creates an instance of UserQueue
     * @param size defines how long each queue is allowed to be
     */

    public UserQueue(int size){
        this.capacity = size;
        this.loginQueue = new LinkedList<String>();
        this.cancelQueue = new LinkedList<String>();
        this.enrolQueue = new LinkedList<String>();
        this.downloadQueue = new LinkedList<String>();
        this.messageQueue = new LinkedList<String>();
    }

    /**
     * removes the user of the first index of the list
     * @param typeQueue the queue you are removing the first element from
     */
    public void remove(LinkedList<String> typeQueue){
        typeQueue.poll();
    }

    /**
     * add a new user to the end of the queue
     * @param userID the id of the user
     * @param type which one of five queues you are adding them
     */
    public void addTo(String userID, String type){
        if(type.equals("loginQueue")) {
            if(loginQueue.size() == 0){
                loginQueue.add(userID);
            }
            if(!checkCapacity(loginQueue) && !loginQueue.getLast().equals(userID)){
                loginQueue.add(userID);
            }
            if(checkCapacity(loginQueue) && !loginQueue.getLast().equals(userID)){
                remove(loginQueue);
                loginQueue.add(userID);
            }
        }
        else if(type.equals("cancelQueue")) {
            if(cancelQueue.size() == 0){
                cancelQueue.add(userID);
            }
            if(!checkCapacity(cancelQueue) && !cancelQueue.getLast().equals(userID)){
                cancelQueue.add(userID);
            }
            if(checkCapacity(cancelQueue) && !cancelQueue.getLast().equals(userID)){
                remove(cancelQueue);
                cancelQueue.add(userID);
            }
        }
        else if(type.equals("enrolQueue")){
            if(enrolQueue.size() == 0){
                enrolQueue.add(userID);
            }
            if(!checkCapacity(enrolQueue) && !enrolQueue.getLast().equals(userID)){
                enrolQueue.add(userID);
            }
            if(checkCapacity(enrolQueue) && !enrolQueue.getLast().equals(userID)){
                remove(enrolQueue);
                enrolQueue.add(userID);
            }
        }
        else if(type.equals("downloadQueue")){
            if(downloadQueue.size() == 0){
                downloadQueue.add(userID);
            }
            if(!checkCapacity(downloadQueue) && !downloadQueue.getLast().equals(userID)){
                downloadQueue.add(userID);
            }
            if(checkCapacity(downloadQueue) && !downloadQueue.getLast().equals(userID)){
                remove(downloadQueue);
                downloadQueue.add(userID);
            }
        }
        else if(type.equals("messageQueue")){
            if(messageQueue.size() == 0){
                messageQueue.add(userID);
            }
            if(!checkCapacity(messageQueue) && !messageQueue.getLast().equals(userID)){
                messageQueue.add(userID);
            }
            if(checkCapacity(messageQueue) && !messageQueue.getLast().equals(userID)){
                remove(messageQueue);
                messageQueue.add(userID);
            }
        }
    }

    /**
     * checks if a queue is at capacity
     * @param currentQueue the queue you are checking its capacity
     * @return true if it is at capacity and false otherwise
     */

    public boolean checkCapacity(LinkedList<String> currentQueue){
        return currentQueue.size() == this.capacity;
    }

    /**
     *  gets the loginQueue which tracks the users that login
     * @return loginQueue
     */

    public LinkedList<String> getLoginQueue(){
        return loginQueue;
    }

    /**
     *  gets the cancelQueue which tracks the users that cancel enrolment
     * @return cancelQueue
     */

    public LinkedList<String> getCancelQueue(){
        return cancelQueue;
    }

    /**
     *  gets the enrolQueue which tracks the users that enrol in events
     * @return enrolQueue
     */

    public LinkedList<String> getEnrolQueue(){
        return enrolQueue;
    }

    /**
     *  gets the downloadQueue which tracks the users that download their schedules
     * @return downloadQueue
     */

    public LinkedList<String> getDownloadQueue(){
        return downloadQueue;
    }

    /**
     *  gets the messageQueue which tracks the users that message
     * @return messageQueue
     */

    public LinkedList<String> getMessageQueue(){
        return messageQueue;
    }
}
