package controllers.reviewAndRatingControl;

import controllers.UserControl;
import entities.Person;
import outer.MenuPresenter;
import useCases.ReviewManager;
import useCases.UserManager;


public class ViewSpeakerReviews implements UserControl {
    private MenuPresenter menuPresenter = new MenuPresenter();
    private ReviewManager reviewManager;
    private UserManager userManager;

    /**
     * Class constructor
     * @param reviewManager the current reviewManager that handles the reviews in the program
     * @param userManager the current userManager that handles the users in the program
     */
    public ViewSpeakerReviews(ReviewManager reviewManager, UserManager userManager){
        this.reviewManager = reviewManager;
        this.userManager = userManager;
    }

    /**
     * Allows an User to view all reviews of written
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        if (chunks.length == 1 && userManager.getUser(userManager.getCurrent()).getType() == Person.ATTENDEE) {
            menuPresenter.displayMySpeakerReviews(reviewManager.getMyReviewedSpeakers(userManager.getCurrent()));
        } else if (chunks.length == 1 && !(userManager.getUser(userManager.getCurrent()).getType() == Person.ATTENDEE)){
            menuPresenter.failedAction();
        } else {
            menuPresenter.invalidCommand();
        }

    }
}
