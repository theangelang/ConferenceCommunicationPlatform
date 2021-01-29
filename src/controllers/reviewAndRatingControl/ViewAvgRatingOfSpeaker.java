package controllers.reviewAndRatingControl;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.ReviewManager;
import useCases.UserManager;

/**
 * A class for viewing the average rating of a speaker.
 */
public class ViewAvgRatingOfSpeaker implements UserControl {
    private MenuPresenter menuPresenter = new MenuPresenter();
    private ReviewManager reviewManager;
    private UserManager userManager;

    /**
     * Class constructor
     * @param reviewManager the current reviewManager that handles the reviews in the program
     * @param userManager the current userManager that handles the users in the program
     */
    public ViewAvgRatingOfSpeaker(ReviewManager reviewManager, UserManager userManager){
        this.reviewManager = reviewManager;
        this.userManager = userManager;
    }

    /**
     * Allows a User to view the average rating of a speaker.
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        if (chunks.length == 2) {
            if (userManager.getSpeakers().contains(chunks[1])){
                menuPresenter.displayAvgRating(reviewManager.getSpeakerRating(chunks[1]),chunks[1]);
            }
            else {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
