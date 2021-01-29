package entities;

import java.io.Serializable;


/**
 * A class that represents a request a user at a conference might have.
 */

public class Request implements Serializable {

    private String note;
    private Status status;
    private Integer id;

    /**
     * Creates an instance of Request that a user might have.
     * A note detailing the request is required to create an instance of Request.
     * @param note The note detailing the request.
     * @param requestID The id assigned to this request.
     */

    public Request(String note, Integer requestID){
        this.note = note;
        this.status = Status.PENDING;
        this.id = requestID;

    }

    /**
     * Returns the note for this Request.
     * @return the note detailing this Request
     */

    public String getNote(){

        return note;
    }

    /**
     * Returns the Status associated with this Request.
     * @return the Status of this Request
     */
    public Status getStatus(){

        return status;
    }

    /**
     * Changes the status of this Request.
     * @param newStatus the new status to associate with this Request
     */

    public void changeStatus(Status newStatus){

        this.status = newStatus;
    }

    /**
     * Returns the id of this Request.
     * @return the id for this Request
     */

    public String getId(){
        return this.id.toString();
    }
}
