package controllers.stats;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.EventManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that gets the top five events based on their enrolment size or the events ordered by enrolment size if there
 * are less than five events in total
 */

public class ViewTopFiveEvents implements UserControl {
    private EventManager eventManager;
    private MenuPresenter menuPresenter = new MenuPresenter();


    /**
     * Class Constructor
     * @param eventManager the Use Case that handles Events
     */

    public ViewTopFiveEvents(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * Organizer uses command to get the top five events or ordered events
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command) {
        List<String> eventIDS = eventManager.getEvents();
        ArrayList<Integer> numAttendees = new ArrayList<>();
        ArrayList<String> topFive = new ArrayList<>();

        for (String event : eventManager.getEvents()) {
            numAttendees.add(eventManager.getEvent(event).getAttendees().size());
        }

        int stopPoint = numAttendees.size();
        if (numAttendees.size() != 0) {
            if (numAttendees.size() >= 5) {
                do {
                    if (numAttendees.size() != 0 || eventIDS.size() != 0) {
                        Integer max = Collections.max(numAttendees);
                        topFive.add(eventIDS.get(numAttendees.indexOf(max)));
                        eventIDS.remove(eventIDS.get(numAttendees.indexOf(max)));
                        numAttendees.remove(max);
                    }
                } while (topFive.size() < 5);

            } else {
                do {
                    if (numAttendees.size() != 0 || eventIDS.size() != 0) {
                        Integer max = Collections.max(numAttendees);
                        topFive.add(eventIDS.get(numAttendees.indexOf(max)));
                        eventIDS.remove(eventIDS.get(numAttendees.indexOf(max)));
                        numAttendees.remove(max);
                    }
                } while (topFive.size() < stopPoint);

            }
            menuPresenter.displayStatisticList(topFive, "viewTopFiveEvents");
        }
        else{
            menuPresenter.failedAction();
        }
    }
}





