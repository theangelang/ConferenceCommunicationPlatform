package controllers.reviewAndRatingControl;

import useCases.*;
import controllers.UserControl;
import outer.MenuPresenter;

import java.util.TreeMap;

/**
 * A class for the commands regarding Reviews
 */
public class ReviewAndRatingControl implements UserControl {
    private MenuPresenter menuPresenter = new MenuPresenter();
    private TreeMap<String, UserControl> commandList =  new TreeMap<>();

    /**
     * Constructor for the ReviewControl class
     * @param reviewManager the use case class that holds and manages Review
     * @param userManager the use case class that holds and manages current user
     * @param eventManager the use case class that holds and manages event
     **/
    public ReviewAndRatingControl(ReviewManager reviewManager, UserManager userManager, EventManager eventManager){
        commandList.put("viewReviewsAndRatings", new ViewReviewsAndRatings(reviewManager, userManager));
        commandList.put("closeReviewMenu", new CloseReviewSubmenu(reviewManager, userManager));

        //for a attendee's operation only: write a review.
        commandList.put("writeReview", new WriteReview(reviewManager, userManager));
        //for a attendee's operation only: view review(s) that the attendee wrote.
        commandList.put("viewWrittenReviews", new ViewWrittenReviews(reviewManager, userManager));

        //view reviews with the selection of the speaker and/or the event and/or the rating
        commandList.put("viewReviewsOfSpeaker", new ViewReviewsOfSpeaker(reviewManager, userManager));
        commandList.put("viewReviewsOfSpeakerByRating", new ViewReviewsOfSpeakerByRating(reviewManager, userManager));
        //view the average rating of all reviews of a speaker
        commandList.put("viewAvgRatingOfSpeaker", new ViewAvgRatingOfSpeaker(reviewManager, userManager));

        commandList.put("viewRROptions", new ViewRROptions(reviewManager, userManager));
        commandList.put("viewMyReviewedSpeakers", new ViewSpeakerReviews(reviewManager, userManager));
    }

    /**
     * Takes a command from the user and returns invalid if the command is not one of the options allowed.
     * @param command the string input by the current User
     */
    @Override
    public void parseCommand(String command){
        String[] commandChunks = command.split(" ");
        if (commandList.get(commandChunks[0]) == null) {
            menuPresenter.invalidCommand();
        } else {
            commandList.get(commandChunks[0]).parseCommand(command);
        }
    }
}
