package useCases;

import entities.Event;
import entities.Person;
import entities.Review;
import entities.SubmenuSwitch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * A class that manages ratings&reviews functions.
 */
public class ReviewManager implements Serializable {
    private List<Review> allReviews;
    private List<Integer> validRatings;
    private SubmenuSwitch aSwitch;

    /**
     * Creates an empty review manager.
     */
    public ReviewManager(){
        allReviews = new ArrayList<>();
        validRatings = new ArrayList<>();
        validRatings.add(1);
        validRatings.add(2);
        validRatings.add(3);
        validRatings.add(4);
        validRatings.add(5);
        this.aSwitch = SubmenuSwitch.OFF;
    }

    /**
     * Returns SubmenuSwitch.ON for turning on the switch succesfully.
     * @return SubmenuSwitch.ON if the aSwich is turned on.
     */
    public SubmenuSwitch switchON (){
        aSwitch = SubmenuSwitch.ON;
        return aSwitch;
    }

    /**
     * Returns SubmenuSwitch.OFF for turning off the switch succesfully.
     * @return SubmenuSwitch.OFF if the aSwich is turned off.
     */
    public SubmenuSwitch switchOFF (){
        aSwitch = SubmenuSwitch.OFF;
        return aSwitch;
    }

    public SubmenuSwitch getaSwitch() {
        return aSwitch;
    }

    /**Add a new review.
     *
     * @param speakerID A string of speaker who made the event of the review.
     * @param rating A integer of the rating by the reviewer of the review from 1 to 5.
     * @param reviewerID A string of the reviewer username.
     * @param shownName A string of the name that is for presenting in review.
     * @param content A string of the review content.
     * @return true if review added successfully.
     */
    public boolean addReview(String speakerID, int rating, String reviewerID,
                             String shownName, String content, UserManager userManager){
        if (userManager.getUser(speakerID).getType() == Person.SPEAKER) {
            Review newReview = new Review(speakerID, rating, reviewerID, shownName, content);
            allReviews.add(newReview);
            return true;
        }
        return false;
    }

    /**
     * Returns a list of reviews which have the reviewer id.
     * @param reviewerID the reviewer id
     * @return list of reviews
     */
    public List<Review> getReviewsByReviewerID(String reviewerID){
        List<Review> reviews = new ArrayList<>();
        for (Review r : allReviews){
            if(r.getReviewerID().equals(reviewerID)){
                reviews.add(r);
            }
        }
        return reviews;
    }

    /**
     * Returns a list of reviews which have the speaker id.
     * @param speakerID the speaker id
     * @return list of reviews
     */
    public List<Review> getReviewsBySpeakerID(String speakerID){
        List<Review> reviews = new ArrayList<>();
        for (Review r : allReviews){
            if(r.getSpeakerID().equals(speakerID)){
                reviews.add(r);
            }
        }
        return reviews;
    }

    /**
     * Returns a list of reviews which have the speaker id and review rating.
     * @param speakerID the speaker id
     * @param rating the rating of the review
     * @return list of reviews
     */
    public List<Review> getReviewsOfSpeakerByRating(String speakerID, int rating){
        List<Review> reviews = new ArrayList<>();
        for (Review r : getReviewsBySpeakerID(speakerID)){
            if(r.getRating() == rating){
                reviews.add(r);
            }
        }
        return reviews;
    }

    /**
     * Returns the average rating of all review of a speaker.
     * @param speakerID the speaker id
     * @return a double of the average rating of all review of a speaker
     */
    public double getSpeakerRating(String speakerID){
        double sum = 0;
        int count = 0;
        for (Review r : getReviewsBySpeakerID(speakerID)){
            sum += r.getRating();
            count += 1;
        }
        if (count == 0){
            return 0;
        }
        return sum/count;
    }

    /**
     * Returns true if the given string of the rating is an integer in the list of validRatings [1,2,3,4,5]
     * @param rating the string of rating
     * @return true if the given string of the rating is valid.
     */
    public boolean isValidRating(String rating){
        if (isIntegerRating(rating)) {
            return validRatings.contains(Integer.parseInt(rating));
        }
        return false;
    }

    /**
     * Returns true if the given string of the rating is an integer. Check if the rating string represents
     * an integer.
     * @param rating the string of rating
     * @return true if the given string of the rating is an integer
     */
    private boolean isIntegerRating(String rating){
        try {
            Integer.parseInt(rating);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    /**
     * Returns an ArrayList of speakerIDs sorted by the ratings of the speakers
     * @param reviewerID the string the current user who is reviewing
     * @return ArrayList of the speaker ids arragned by the rating in descending order
     */


    public List<String> getMyReviewedSpeakers(String reviewerID){
        ArrayList<String> myReviewedSpeakers = new ArrayList<>();
        List<Review> myReviews = getReviewsByReviewerID(reviewerID);

        Collections.sort(myReviews);

        for (Review r : myReviews){
            if (!myReviewedSpeakers.contains(r.getSpeakerID())) {
                myReviewedSpeakers.add(r.getSpeakerID());
            }
        }

        /*
        for (Review r : myReviews){
            if (r.getRating() == 5) {
                myReviewedSpeakers.add(r.getSpeakerID());
            }
        }
        for (Review r : myReviews){
            if (r.getRating() == 4 && !myReviewedSpeakers.contains(r.getSpeakerID())) {
                myReviewedSpeakers.add(r.getSpeakerID());
            }
        }
        for (Review r : myReviews){
            if (r.getRating() == 3 && !myReviewedSpeakers.contains(r.getSpeakerID())) {
                myReviewedSpeakers.add(r.getSpeakerID());
            }
        }
        for (Review r : myReviews){
            if (r.getRating() == 2 && !myReviewedSpeakers.contains(r.getSpeakerID())) {
                myReviewedSpeakers.add(r.getSpeakerID());
            }
        }
        for (Review r : myReviews){
            if (r.getRating() == 1 && !myReviewedSpeakers.contains(r.getSpeakerID())) {
                myReviewedSpeakers.add(r.getSpeakerID());
            }
        }
         */

        return myReviewedSpeakers;
    }
    /**
     * Returns List of Events based on the reviews of speakers
     * @param reviewerID the string of reviewer id
     * @param speakerEvents hashmap of the events associated with a speaker
     * @return List of Events by using the reviews of the speakers arragned by topmost rating
     */
    public List<Event> getTopSpeakerEvents(String reviewerID, HashMap<String, ArrayList<Event>> speakerEvents){
        List<Event> topSpeakerEvents = new ArrayList<>();
        List<String> myReviewedSpeakers = getMyReviewedSpeakers(reviewerID);
        int i = 0;
        for (String id : myReviewedSpeakers){ //gives the speaker that has been highest rated
            if (i < 5){ //checks if the recommended events list is full
                for (Event e : speakerEvents.get(id)){
                    if (!e.getAttendees().contains(reviewerID) && !topSpeakerEvents.contains(e)){ //checks that the attendee is not already going
                        topSpeakerEvents.add(e);
                        i++;
                    }
                }
            }
            else{
                return topSpeakerEvents;
            }
        }
        return topSpeakerEvents;
    }
}
