package controllers;

import controllers.attendee.AttendeeControl;
import controllers.organizer.OrganizerControl;
import controllers.speaker.SpeakerControl;
import entities.Person;
import useCases.*;

class UserControlFactory {
    private MessageManager messageManager;
    private UserManager userManager;
    private BookingManager bookingManager;
    private RoomManager roomManager;
    private EventManager eventManager;
    private RequestManager requestManager;
    private UserQueueManager userQueueManager;
    private ReviewManager reviewManager;

    UserControlFactory(MessageManager messageManager, UserManager userManager, BookingManager bookingManager,
                       RoomManager roomManager, EventManager eventManager, RequestManager requestManager, ReviewManager reviewManager, UserQueueManager userQueueManager) {
        this.messageManager = messageManager;
        this.userManager = userManager;
        this.bookingManager = bookingManager;
        this.roomManager = roomManager;
        this.eventManager = eventManager;
        this.requestManager = requestManager;
        this.userQueueManager = userQueueManager;
        this.reviewManager = reviewManager;
    }


    public UserControl createUserControl(Person p) {
        if (p==Person.ATTENDEE || p==Person.VIP) {
            return new AttendeeControl(messageManager, userManager, bookingManager, roomManager, eventManager, userQueueManager, requestManager, reviewManager);
        } else if (p==Person.SPEAKER) {
            return new SpeakerControl(messageManager, userManager, bookingManager, roomManager, eventManager, requestManager);
        } else if (p==Person.ORGANIZER) {
            return new OrganizerControl(messageManager, userManager, bookingManager, roomManager, eventManager, requestManager, reviewManager, userQueueManager);
        }

        return null;
    }
}
