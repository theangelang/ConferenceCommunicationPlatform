package outer;

import entities.Event;
import entities.MessageChain;
import entities.Person;
import entities.Review;
import useCases.EventManager;
import useCases.RequestManager;
import useCases.ReviewManager;
import useCases.UserManager;

import java.io.Serializable;
import java.util.*;

/**
 * Class that presents the menus to the user.
 */
public class MenuPresenter implements Serializable {

    /** Displays the events to the user.
     *
     * @param events ArrayList of strings containing events
     * @param eventManager the current instance of EventManager
     */
    public void displayEvents(List<String> events, EventManager eventManager){
        System.out.println("---------");
        System.out.println("EVENTS:");
        System.out.println("---------");

        for (String event : events) {
            Event e = eventManager.getEvent(event);
            System.out.println(e.toString());
        }

    }

    /** Displays menu of options for the user to choose from.
     *
     * @param type The type of user
     */
    public void displayOptions(Person type) {
        System.out.println("---------");
        System.out.println("OPTIONS:");
        System.out.println("---------");

        if (type==Person.SPEAKER) {
            System.out.println("messageAttendees <Event Name> <Message>");
            System.out.println("seeMyTalks");
            System.out.println("createRequest <note>");
            System.out.println("~~~Note: All requests will be anonymous unless a username is included in the note of the " +
                    "request ~~~");
        } else if (type==Person.ORGANIZER) {
            System.out.println("addEvent <Name> <Date> <Start time> <End time> <Room> <Capacity> " +
                    "<Speaker1,Speaker2,Speaker3,...>");
            System.out.println("cancelEvent <Event>");
            System.out.println("changeCapacity <Event>");
            System.out.println("addRoom <Name> <Capacity>");
            System.out.println("messageAll <User Type> <Message>");
            System.out.println("addSpeaker <ID>");
            System.out.println("addAttendee <ID>");
            System.out.println("addVIP <ID>");
            System.out.println("viewAllRequests");
            System.out.println("viewPendingRequests");
            System.out.println("viewResolvedRequests");
            System.out.println("viewRequest <id>");
            System.out.println("updateRequestStatus <id> <status>");
            System.out.println("deleteRequest <id>");
            System.out.println("viewStats");
            System.out.println("~~~Note: All requests will be anonymous unless a username is included in the note of the " +
                    "request ~~~");
        } else if (type==Person.ATTENDEE || type==Person.VIP) {
            System.out.println("cancel <Event Name>");
            System.out.println("signUp <Event Name>");
            System.out.println("seeMySchedule");
            System.out.println("downloadMySchedule");
            System.out.println("seeListOfEvents");
            System.out.println("createRequest <note>");
            System.out.println("viewRecommendedFriends");
            System.out.println("addFriend <User>");
            System.out.println("viewFriends");
            System.out.println("recommendEvent <User> <Event>");
            System.out.println("viewRecommendedEvents");
            System.out.println("~~~Note: All requests will be anonymous unless a username is included in the note of the " +
                    "request ~~~");
            System.out.println("viewSpeakerRecommendedEvents <User>");
            System.out.println("viewSpeakerEvents");

        }
        System.out.println("viewMessage <User>");
        System.out.println("---------");
        System.out.println("When viewing a message, you have the following commands:");
        System.out.println("sendMessage <Message>");
        System.out.println("mark <Index>");
        System.out.println("archive <Index>");
        System.out.println("viewArchive");
        System.out.println("close");
        System.out.println("~~~Note: Commands done outside of viewing a message cannot be performed when viewing a " +
                "message~~~");
        System.out.println("---------");
        System.out.println("viewReviewsAndRatings");
    }

    /** Displays the MessageChain for user.
     *
     * @param messages MessageChain containing messages.
     */
    public void displayMessages(MessageChain messages){
        System.out.println("---------");
        System.out.println("MESSAGE CHAIN:");
        System.out.println("---------");
        int index = 0;
        for (String message : messages.toArray()){
            System.out.println(index + " ~ " + message);
            index++;
        }
    }

    public void displayArchivedMessages(MessageChain messages) {
        System.out.println("---------");
        System.out.println("ARCHIVED MESSAGES:");
        System.out.println("---------");
        for (String message : messages.getArchive()) {
            System.out.println(message);
        }
    }

    public void displayStatistic (long num, String typeOfStat){

        if(typeOfStat.equals("viewAvgAttendeesPerEvent")){
            System.out.println("There is approximately " + num + " Attendees per event");

        }
        else if(typeOfStat.equals("viewAvgEventsOnDate")){
            System.out.println("Approximately " + num + "% percent of events occur on this date");
        }
        else if(typeOfStat.equals("viewAvgEventsInRoom")) {
            System.out.println("Approximately " + num + "% percent of events occur in this room");
        }
        else if(typeOfStat.equals("viewAvgFullyBookedEvents")) {
            System.out.println("Approximately " + num + "% percent of events are fully booked");
        }
        else if(typeOfStat.equals("viewAvgPendingRequests")){
            System.out.println(num + "% of requests are pending");
        }

        else if(typeOfStat.equals("viewAvgResolvedRequests")){
            System.out.println(num + "% of requests are resolved");
        }
    }

    public void displayStatisticList(List<String> lst, String typeOfStat){
        if(typeOfStat.equals("viewTopFiveEvents")){
            System.out.println("---------");
            System.out.println("Top Five Enrolled Events:");
            System.out.println("---------");
            for (String event : lst) {
                System.out.println(event);
            }
        }
        if(typeOfStat.equals("viewTopFiveSpeakers")){
            System.out.println("---------");
            System.out.println("Top Five Rated Speakers:");
            System.out.println("---------");
            for (String event : lst) {
                System.out.println(event);
            }
        }
        if(typeOfStat.equals("viewLastEventRegistration")){
            System.out.println("---------");
            System.out.println("Last Three Users to Enrol In An Event");
            System.out.println("---------");
            for (String event : lst) {
                System.out.println(event);
            }
        }
        if(typeOfStat.equals("viewLastLogins")){
            System.out.println("---------");
            System.out.println("Last Three Users to Login");
            System.out.println("---------");
            for (String event : lst) {
                System.out.println(event);
            }
        }
        if(typeOfStat.equals("viewLastRegistrationCanceled")){
            System.out.println("---------");
            System.out.println("Last Three Users to Cancel Event Registration:");
            System.out.println("---------");
            for (String event : lst) {
                System.out.println(event);
            }
        }
        if(typeOfStat.equals("viewLastUsersToDownload")){
            System.out.println("---------");
            System.out.println("Last Three Users to Download A Schedule:");
            System.out.println("---------");
            for (String event : lst) {
                System.out.println(event);
            }
        }
        if(typeOfStat.equals("viewLastUsersToMessage")){
            System.out.println("---------");
            System.out.println("Last Three Users to Message:");
            System.out.println("---------");
            for (String event : lst) {
                System.out.println(event);
            }
        }

    }

    public void displayAllRequests(List<String> totalRequests) {
        System.out.println("---------");
        System.out.println("ALL REQUESTS:");
        System.out.println("---------");

        for (String request : totalRequests) {
            System.out.println(request);
        }

    }

    public void displayPendingRequests(List<String> pendingRequests) {
        System.out.println("---------");
        System.out.println("PENDING REQUESTS:");
        System.out.println("---------");

        for (String pendingRequest : pendingRequests){
            System.out.println(pendingRequest);
        }
    }

    public void displayResolvedRequests(List<String> resolvedRequests) {
        System.out.println("---------");
        System.out.println("RESOLVED REQUESTS:");
        System.out.println("---------");

        for (String resolvedRequest : resolvedRequests){
            System.out.println(resolvedRequest);
        }
    }

    /** Displays Reviews for user.
     *
     * @param reviews List of Reviews.
     */
    public void displayReviews(List<Review> reviews){
        System.out.println("---------");
        System.out.println("REVIEWS:");
        System.out.println("---------");

        for (Review r : reviews) {
            System.out.println(r.toString());
            System.out.println("\n");
        }
    }

    public void displayMyReviews(List<Review> reviews) {
        System.out.println("---------");
        System.out.println("MY REVIEWS:");
        System.out.println("---------");

        for (Review r : reviews) {
            System.out.println(r.toString());
            System.out.println("\n");
        }
    }

    public void displayAvgRating (Double rating, String speaker) {
        System.out.println("---------");
        System.out.println("The Average Rating Of " + speaker + " : ");
        System.out.println("---------");
        System.out.println(rating);
    }

    public void invalidRating() {
        System.out.println("Invalid Rating: Rating should be an integer of: 5 | 4 | 3 | 2 | 1 ");
    }

    public void successfullyCreatedReview() {
        System.out.println("The review is write successfully.");
        System.out.println("Enter viewWrittenReviews to see reviews you wrote");
    }

    public void reviewMenuGreeting(String userID){
        System.out.println("Welcome to the Review and Rating Viewer " + userID);
        System.out.println("Enter viewRROptions to see your commands");
        System.out.println("Enter closeReviewMenu to go back to main menu");
    }



    public void displayReviewOptions(String userId, UserManager userManager){
        System.out.println("---------");
        System.out.println("In this review panel, you have the following commands:");
        if (userManager.getUser(userManager.getCurrent()).getType() == Person.ATTENDEE){
            System.out.println("---------");
            System.out.println("For your Review, You have following commands for your reviews:");
            System.out.println("~~~Note: You can write a review with a rating of integer from: " +
                    "5-Amazing | 4-Good | 3-Normal | 2-Bad | 1-Very Bad ~~~");
            System.out.println("~~~Note: You can write a review with a whatever reviewer name you want, not " +
                    "necessarily your username ~~~");
            System.out.println("writeReview <Speaker Name> <Rating> <Reviewer Name> <Content>");
            System.out.println("viewWrittenReviews");
            System.out.println("---------");
        }
        System.out.println("viewReviewsOfSpeaker <SpeakerID>");
        System.out.println("viewReviewsOfSpeakerByRating <SpeakerID> <rating>");
        System.out.println("viewAvgRatingOfSpeaker <SpeakerID>");
        System.out.println("viewMyReviewedSpeakers");
        System.out.println("~~~Note: Commands done outside of review panel cannot be performed.~~~");
        System.out.println("Enter closeReviewMenu to go back to main menu to perform external commands");
    }

    public void statsMenuGreeting(String userID){
        System.out.println("Welcome to the Statistics Viewer " + userID);
        System.out.println("Enter viewOptions for your commands");
        System.out.println("Enter closeStats to log out");
    }

    public void displayStatisticOptions(String userId){
        System.out.println("--- Options for " + userId + " ---");
        System.out.println("viewAvgAttendeesPerEvent");
        System.out.println("viewAvgEventsInRoom <Room>");
        System.out.println("viewAvgEventsOnDate <Date>");
        System.out.println("viewAvgFullyBookedEvents");
        System.out.println("viewAvgPendingRequests");
        System.out.println("viewAvgResolvedRequests");
        System.out.println("viewLastEventRegistration");
        System.out.println("viewLastLogins");
        System.out.println("viewLastRegistrationCanceled");
        System.out.println("viewLastUsersToDownload");
        System.out.println("viewLastUsersToMessage");
        System.out.println("viewTopFiveEvents");
        System.out.println("viewTopFiveSpeakers");
    }

    public void displayRequest(String request) {
        System.out.println(request);
    }

    public void invalidCommand() { System.out.println("Invalid command"); }

    public void failedAction() {
        System.out.println("Action could not be performed");
    }

    public void loginPrompt() {
        System.out.println("Please enter your username");
    }

    public void invalidUser() {
        System.out.println("Invalid username");
    }

    public void downloadSuccess() {System.out.println("Successfully downloaded");}

    public void loginGreeting(String user) {
        System.out.println("---------");
        System.out.println("ZALDAMOS EVENT SCHEDULING SYSTEM");
        System.out.println("---------");
        System.out.println("Logged in as " + user);
        System.out.println("Enter help for your commands");
        System.out.println("Enter logout to log out");
    }

    public void subMenuCloseMenu(String userID){
        System.out.println("menu closed " + userID);
    }


    public void displayRecommendedFriends(List<String> recommendedFriends) {
        System.out.println("---------");
        System.out.println("RECOMMENDED FRIENDS");
        System.out.println("---------");

        for (String friend : recommendedFriends) {
            System.out.println(friend);
        }
    }

    public void displayFriendsList(List<String> friendsList) {
        System.out.println("---------");
        System.out.println("FRIENDS LIST");
        System.out.println("---------");

        for (String friend : friendsList) {
            System.out.println(friend);
        }
    }

    public void displayRecommendedEvents(Map<String, List<String>> recs, EventManager eventManager) {
        System.out.println("---------");
        System.out.println("RECOMMENDED EVENTS");
        System.out.println("---------");

        for (String u : recs.keySet()) {
            for (String e : recs.get(u)) {
                System.out.println(u + ": " + eventManager.getEvent(e).toString());
            }
        }
    }

    public void displaySpeakerEvents(HashMap<String, ArrayList<Event>> speakerEvents){
        System.out.println("---------");
        System.out.println("SpeakerEvents:");
        System.out.println("---------");
        for (String u : speakerEvents.keySet()) {
            for (Event e : speakerEvents.get(u)) {
                System.out.println(u + ": " + e);
            }
        }
    }
    public void displaySpeakerRecommendedEvents(List<Event> speakerEvents){
        System.out.println("---------");
        System.out.println("Recommended Events:");
        System.out.println("---------");
        for (Event e : speakerEvents){
            System.out.println(e);
        }
    }

    public void displayMySpeakerReviews(List<String> myReviewedSpeakers) {
        System.out.println("---------");
        System.out.println("My Reviewed Speakers:");
        System.out.println("---------");
        System.out.println(myReviewedSpeakers);
    }

}

