package controllers.stats;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.EventManager;

/**
 * Class that calculates the percentage of events that occur on a given date
 */

public class ViewAvgEventsOnDate implements UserControl {
    private EventManager eventManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class constructor
     * @param eventManager the Use Case that handles Events
     */

    public ViewAvgEventsOnDate(EventManager eventManager){
        this.eventManager = eventManager;
    }

    /**
     * Organizer calculates the value of this statistic
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command){
        String[] chunks = command.split(" ");
        int numEvents = eventManager.getEvents().size();
        int neededEvents = 0;
        for(String event:eventManager.getEvents()){
            if(eventManager.getEvent(event).getDate().equals(chunks[1])){
                neededEvents += 1;
            }
        }
        if(numEvents > 0.0){
            double top = neededEvents;
            double stat = top / numEvents;
            menuPresenter.displayStatistic(Math.round(stat*100), "viewAvgEventsOnDate");
        }
        else{
            menuPresenter.failedAction();
        }
    }
}
