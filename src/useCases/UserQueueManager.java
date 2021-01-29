package useCases;


import entities.SubmenuSwitch;
import entities.User;
import entities.UserQueue;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that manage userQueue functions and submenu functions
 */

public class UserQueueManager implements Serializable {

    private UserQueue mainQueue;
    private SubmenuSwitch statsMenuStatus;

    /**
     * The constructor for a UserQueueManager
     */

    public UserQueueManager(){
        this.mainQueue = new UserQueue(3);
    }

    /**
     * getter method for the main queue of the userQueueManager
     * @return mainQueue the mainQueue for userQueueManager
     */

    public UserQueue getMainQueue(){
        return mainQueue;
    }

    /**
     * Changes the LinkedListed mainQueue into an Arraylist containing the elements of the queue based on its type
     * @param type the type of queue being accessed
     * @return convertedQueue the queue now in Arraylist form
     */

    public ArrayList<String> convertQueue(String type){
        ArrayList<String> convertedQueue = new ArrayList<>();
        if(type.equals("loginQueue")){
            convertedQueue.addAll(mainQueue.getLoginQueue());
        }else if(type.equals("cancelQueue")){
            convertedQueue.addAll(mainQueue.getCancelQueue());
        }else if(type.equals("enrolQueue")){
            convertedQueue.addAll(mainQueue.getEnrolQueue());
        }else if(type.equals("downloadQueue")){
            convertedQueue.addAll(mainQueue.getDownloadQueue());
        }else if(type.equals("messageQueue")){
            convertedQueue.addAll(mainQueue.getMessageQueue());
        }
        return convertedQueue;
    }

    /**
     * Sets the stat's menu status to on
     * @return the now turned on statsMenuStatus
     *
     */

    public SubmenuSwitch setStatsMenuStatusOn(){
        statsMenuStatus = SubmenuSwitch.ON;
        return statsMenuStatus;
    }

    /**
     * Sets the stat's menu status to off
     * @return the now turned off statsMenuStatus
     */

    public SubmenuSwitch setStatsMenuStatusOff(){
        statsMenuStatus = SubmenuSwitch.OFF;
        return statsMenuStatus;
    }

    /**
     * Returns the value of statsMenuStatus
     * @return statsMenuStatus which tells us whether the stats menu is open or not
     */

    public SubmenuSwitch getStatsMenuStatus(){
        return statsMenuStatus;
    }

}
