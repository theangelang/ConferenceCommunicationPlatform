package controllers.reviewAndRatingControl;

import controllers.UserControl;
import entities.SubmenuSwitch;
import outer.MenuPresenter;
import useCases.ReviewManager;
import useCases.UserManager;

public class ViewRROptions implements UserControl {
    private ReviewManager reviewManager;
    private UserManager userManager;
    private MenuPresenter menuPresenter = new MenuPresenter();

    public ViewRROptions(ReviewManager reviewManager, UserManager userManager) {
        this.reviewManager = reviewManager;
        this.userManager = userManager;
    }

    @Override
    public void parseCommand(String command) {
        if(reviewManager.getaSwitch() == SubmenuSwitch.ON){
            menuPresenter.displayReviewOptions(userManager.getCurrent(), userManager);
        }
    }
}
