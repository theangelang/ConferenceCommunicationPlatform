package controllers.reviewAndRatingControl;

import controllers.UserControl;
import entities.SubmenuSwitch;
import outer.MenuPresenter;
import useCases.ReviewManager;
import useCases.UserManager;

public class CloseReviewSubmenu implements UserControl {
    ReviewManager reviewManager;
    UserManager userManager;
    MenuPresenter menuPresenter = new MenuPresenter();

    CloseReviewSubmenu(ReviewManager reviewManager, UserManager userManager) {
        this.reviewManager = reviewManager;
        this.userManager = userManager;
    }

    @Override
    public void parseCommand(String command) {
        if (reviewManager.switchOFF() == SubmenuSwitch.OFF){
            menuPresenter.displayOptions(userManager.getUser(userManager.getCurrent()).getType());
        } else {
            menuPresenter.failedAction();
        }
    }
}
