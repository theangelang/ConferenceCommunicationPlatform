package controllers.stats;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.EventManager;

/**
 * Class for calculating the percentage of events that occur in a room
 */

public class ViewAvgEventsInRoom implements UserControl {
    private EventManager eventManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class constructor
     * @param eventManager Use Case that handles Events
     */

    public ViewAvgEventsInRoom(EventManager eventManager){
        this.eventManager = eventManager;
    }

    /**
     * Organizer calculates the value of this statistic
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command){
        String[] chunks = command.split(" ");
        double numEvents = eventManager.getEvents().size();
        int neededEvents = 0;
        for(String event:eventManager.getEvents()){
            if(eventManager.getEvent(event).getRoomID().equals(chunks[1])){
                neededEvents += 1;
            }
        }
        if(numEvents > 0.0) {
            double top = neededEvents;
            double stat = top / numEvents;
            menuPresenter.displayStatistic(Math.round(stat*100), "viewAvgEventsInRoom");
        }
        else{
            menuPresenter.failedAction();
        }
    }
}
