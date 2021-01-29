package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * An instance of this Class represents a review of a speaker.
 */

public class Review implements Serializable, Comparable<Review> {
    private String speakerID;
    private int rating;
    private String reviewerID;
    private List<String> review;

    /** Constructs a new Review.
     *
     * @param speakerID A string of speaker who the review is about.
     * @param rating A integer of the rating by the reviewer of the review from 1 to 5.
     * @param reviewerID A string of the reviewer username.
     * @param shownName A string of the name that is for presented in review.
     * @param content A string of the review content.
     */
    public Review(String speakerID,  int rating, String reviewerID, String shownName,
                  String content) {
        this.speakerID = speakerID;
        this.rating = rating;
        this.reviewerID = reviewerID;
        this.review = new ArrayList<>();
        review.add(shownName);
        review.add(content);
    }

    /**
     * @return an String of the speaker of the event that the review is about
     */
    public String getSpeakerID(){return speakerID;}

    /**
     * @return an Integer of rating of the review
     */
    public Integer getRating(){return rating;}

    /**
     * @return an String of the reviewer who wrote the review
     */
    public String getReviewerID() {return reviewerID;}

    /**
     * @return an String representation of the review 
     */
    public String toString(){
        double d = rating;
        return "Review of "+speakerID+"\n ["+d+"] by "+review.get(0)+":"+"\n"+review.get(1);
    }
    /**
     * @return rating of the review and compare it to the next one to sort
     */
    @Override
    public int compareTo(Review o) {
        if (o == null)
            return -1;
        return o.getRating().compareTo(getRating());
    }

}
