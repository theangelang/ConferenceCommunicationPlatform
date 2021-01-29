package controllers;

import java.io.Serializable;
import java.util.Scanner;

import controllers.messageControl.MessageControl;
import controllers.stats.StatsControl;
import entities.SubmenuSwitch;
import useCases.*;
import outer.MenuPresenter;
import useCases.BookingManager;
import controllers.reviewAndRatingControl.ReviewAndRatingControl;


/**
 *A controller that handles logging a user in.
 */
public class LoginSystem implements Serializable {
    private UserQueueManager userQueueManager = new UserQueueManager();
    private MessageManager messageManager = new MessageManager();
    private UserManager userManager = new UserManager();
    private BookingManager bookingManager = new BookingManager();
    private RoomManager roomManager = new RoomManager();
    private EventManager eventManager = new EventManager();
    private MenuPresenter menuPresenter = new MenuPresenter();
    private RequestManager requestManager = new RequestManager();
    private ReviewManager reviewManager = new ReviewManager();
    private UserControl userControl;

    /**
     * The method called by the main method. It prompts a user to log in, then passes their commands
     * to the specific controller for their user type.
     */
    public void run() {
        UserControl messageControl = new MessageControl(userManager, messageManager, userQueueManager);
        UserControlFactory userControlFactory = new UserControlFactory(messageManager, userManager, bookingManager,
                roomManager, eventManager, requestManager, reviewManager, userQueueManager);
        menuPresenter.loginPrompt();
        Scanner sc = new Scanner(System.in);
        UserControl reviewControl = new ReviewAndRatingControl(reviewManager, userManager, eventManager);
        UserControl statsControl = new StatsControl(userManager, eventManager, roomManager, requestManager, userQueueManager, reviewManager);

        while (!userManager.setCurrent(sc.nextLine())) {
            menuPresenter.invalidUser();
        }

        menuPresenter.loginGreeting(userManager.getCurrent());

        userControl = userControlFactory.createUserControl(userManager.getUser(userManager.getCurrent()).getType());

        while (!(userControl == null)) {
            String input = sc.nextLine();
            if (input.split(" ")[0].equals("help")) {
                menuPresenter.displayOptions(userManager.getUser(userManager.getCurrent()).getType());
            } else if (input.split(" ")[0].equals("viewMessage") || messageManager.getCurrentViewed() != null) {
                messageControl.parseCommand(input);
            } else if (input.split(" ")[0].equals("viewReviewsAndRatings")
                    || reviewManager.getaSwitch() == SubmenuSwitch.ON) {
                reviewControl.parseCommand(input);
            } else if(input.split(" ")[0].equals("viewStats") || userQueueManager.getStatsMenuStatus() == SubmenuSwitch.ON){
                statsControl.parseCommand(input);
            } else if (!input.equals("logout")){
                userControl.parseCommand(input);
            }

            else {
                userQueueManager.getMainQueue().addTo(userManager.getCurrent(), "loginQueue");
                userControl = null;
                userManager.setCurrent("LOGOUT");
            }
        }
    }
}
