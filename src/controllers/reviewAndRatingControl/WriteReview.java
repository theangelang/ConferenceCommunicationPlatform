package controllers.reviewAndRatingControl;

import entities.Person;
import useCases.*;
import controllers.UserControl;
import outer.MenuPresenter;

public class WriteReview implements UserControl {
    private MenuPresenter menuPresenter = new MenuPresenter();
    private ReviewManager reviewManager;
    private UserManager userManager;

    /**
     * Class constructor
     *
     * @param reviewManager the current reviewManager that handles the reviews in the program
     * @param userManager   the current userManager that handles the users in the program
     */
    public WriteReview(ReviewManager reviewManager, UserManager userManager) {
        this.reviewManager = reviewManager;
        this.userManager = userManager;
    }

    /**
     * Allows an User to write a review of an event
     *
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        if (chunks.length >= 5 && userManager.getUser(userManager.getCurrent()).getType() == Person.ATTENDEE) {
            if (reviewManager.isValidRating(chunks[2]) && userManager.getSpeakers().contains(chunks[1])) {
                int rating = Integer.parseInt(chunks[2]);
                String content = "";
                for (int i = 4; i < chunks.length; i++) {
                    content += chunks[i] + " ";
                }
                if (reviewManager.addReview(chunks[1], rating,
                        userManager.getCurrent(), chunks[3], content, userManager)) {
                    menuPresenter.successfullyCreatedReview();
                } else {
                    menuPresenter.failedAction();
                }
            } else if (!(userManager.getSpeakers().contains(chunks[1]))) {
                menuPresenter.failedAction();
            } else if (!(reviewManager.isValidRating(chunks[2]))) {
                menuPresenter.invalidRating();
            } else {
                menuPresenter.failedAction();
            }
        }else {
            menuPresenter.invalidCommand();
        }
    }
}
