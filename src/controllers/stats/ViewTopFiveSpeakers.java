package controllers.stats;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.ReviewManager;
import useCases.UserManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that gets the top five reviewed speakers by their rating or the reviewed speakers ordered by ratings if there
 * are less than five reviewed speakers
 */

public class ViewTopFiveSpeakers implements UserControl {

    private UserManager userManager;
    private ReviewManager reviewManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    /**
     * Class Constructor
     * @param userManager the Use Case that handles Users
     * @param ratingManager the Use Case that handles ratings and reviews
     */

    public ViewTopFiveSpeakers(UserManager userManager, ReviewManager ratingManager) {
        this.userManager = userManager;
        this.reviewManager = ratingManager;
    }

    /**
     * Organizer uses command to get the top five reviewed speakers or ordered reviewed speakers
     * @param command the string input by current User
     */

    @Override
    public void parseCommand(String command) {
        List<String> speakers = new ArrayList<>();
        ArrayList<Double> speakerRatings = new ArrayList<>();
        ArrayList<String> topFive = new ArrayList<>();


        for(String speaker: userManager.getSpeakers()){
            if(reviewManager.getReviewsBySpeakerID(speaker).size() != 0){
                speakers.add(speaker);
            }
        }

        for(int i = 0; i < speakers.size(); i++){
            speakerRatings.add(reviewManager.getSpeakerRating(speakers.get(i)));
        }

        int stopPoint = speakerRatings.size();


        if(speakerRatings.size() != 0) {
            if (speakerRatings.size() >= 5) {
                do {
                    if (speakerRatings.size() != 0 || speakers.size() != 0) {
                        double max = Collections.max(speakerRatings);
                        topFive.add(speakers.get(speakerRatings.indexOf(max)));
                        speakers.remove(speakers.get(speakerRatings.indexOf(max)));
                        speakerRatings.remove(max);
                    }
                } while (topFive.size() < 5);
            } else {
                do {
                    if (speakerRatings.size() != 0 || speakers.size() != 0) {
                        double max = Collections.max(speakerRatings);
                        topFive.add(speakers.get(speakerRatings.indexOf(max)));
                        speakers.remove(speakers.get(speakerRatings.indexOf(max)));
                        speakerRatings.remove(max);
                    }
                } while (topFive.size() < stopPoint);
            }
            menuPresenter.displayStatisticList(topFive, "viewTopFiveSpeakers");

        }
        else{
            menuPresenter.failedAction();
        }
    }
}
