package controllers.reviewAndRatingControl;

import controllers.UserControl;
import entities.Person;
import entities.SubmenuSwitch;
import outer.MenuPresenter;
import useCases.ReviewManager;
import useCases.UserManager;


public class ViewReviewsAndRatings implements UserControl {
    ReviewManager reviewManager;
    UserManager userManager;
    MenuPresenter menuPresenter = new MenuPresenter();

    ViewReviewsAndRatings(ReviewManager reviewManager, UserManager userManager) {
        this.userManager = userManager;
        this.reviewManager = reviewManager;
    }

    @Override
    public void parseCommand(String command) {
        String[] chunks = command.split(" ");
        if (chunks.length == 1 ) {
            if (reviewManager.switchON() == SubmenuSwitch.ON) {
                menuPresenter.reviewMenuGreeting(userManager.getCurrent());
            } else {
                menuPresenter.failedAction();
            }
        } else {
            menuPresenter.invalidCommand();
        }
    }
}
