package controllers.stats;
import controllers.UserControl;
import outer.MenuPresenter;
import useCases.EventManager;

/**
 * Class for calculating the number of enrolled attendees per event
 */

public class ViewAvgAttendeesPerEvent implements UserControl{
    private EventManager eventManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class Constructor
     * @param eventManager the Use Cases that handles and manages Events
     */

    public ViewAvgAttendeesPerEvent(EventManager eventManager){
        this.eventManager = eventManager;
    }

    /**
     * Organizer calculates the value of this statistic
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command){

        double numEvents = eventManager.getEvents().size();
        int numAttendees = 0;
        for(String event:eventManager.getEvents()){
            numAttendees += eventManager.getEvent(event).getAttendees().size();
        }
        if(numEvents != 0) {
            double top = numAttendees;
            double stat = top / numEvents;
            menuPresenter.displayStatistic(Math.round(stat), "viewAvgAttendeesPerEvent");
        }
        else{
            menuPresenter.failedAction();
        }
    }
}
