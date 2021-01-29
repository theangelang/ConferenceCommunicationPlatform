package controllers.reviewAndRatingControl;

import controllers.UserControl;
import outer.MenuPresenter;
import useCases.ReviewManager;
import useCases.UserManager;

/**
 * A class for viewing the reviews of an speaker of a rating
 */
public class ViewReviewsOfSpeakerByRating implements UserControl {
    private MenuPresenter menuPresenter = new MenuPresenter();
    private ReviewManager reviewManager;
    private UserManager userManager;

    /**
     * Class constructor
     * @param reviewManager the current reviewManager that handles the reviews in the program
     * @param userManager the current userManager that handles the users in the program
     */
    public ViewReviewsOfSpeakerByRating(ReviewManager reviewManager, UserManager userManager){
        this.reviewManager = reviewManager;
        this.userManager = userManager;
    }

    /**
     * Allows an User to view the reviews of a speaker of a rating
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        if (chunks.length == 3) {
            if (userManager.getSpeakers().contains(chunks[1]) && reviewManager.isValidRating(chunks[2])){
                int i=Integer.parseInt(chunks[2]);
                menuPresenter.displayReviews(reviewManager.getReviewsOfSpeakerByRating(chunks[1],i));
            } else if (!(userManager.getSpeakers().contains(chunks[1]))) {
                menuPresenter.failedAction();
            } else if (!(reviewManager.isValidRating(chunks[2]))){
                menuPresenter.invalidRating();
            } else {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
