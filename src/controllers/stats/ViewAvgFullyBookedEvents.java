package controllers.stats;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.EventManager;
import useCases.RoomManager;
import java.util.List;

/**
 * Class that calculates the percentage of fully booked rooms
 */

public class ViewAvgFullyBookedEvents implements UserControl {
    private RoomManager roomManager;
    private EventManager eventManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class constructor
     * @param eventManager the Use Case that handles events
     * @param roomManager the Use Case that handles rooms
     */

    public ViewAvgFullyBookedEvents(RoomManager roomManager, EventManager eventManager) {
        this.roomManager = roomManager;
        this.eventManager = eventManager;

    }

    /**
     * Organizer calculates the value of this statistic
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command){
        List<String> events = eventManager.getEvents();
        double totalEvents = events.size();
        int fullyBookedEvents = 0;
        for(String s: events){
            if(eventManager.getEvent(s).getCapacity() == eventManager.getEvent(s).getAttendees().size()){
                fullyBookedEvents += 1;
            }
        }
        if(totalEvents != 0){
            double top = fullyBookedEvents;
            double stat = top/totalEvents;
            menuPresenter.displayStatistic(Math.round(stat*100), "viewAvgFullyBookedEvents");
        }
    }

}
