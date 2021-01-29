package useCases;

import entities.Request;
import entities.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that holds all the requests for this program.
 */
public class RequestManager implements Serializable {

    private List<Request> totalRequests = new ArrayList<>();
    private List<Request> pendingRequests = new ArrayList<>();
    private List<Request> resolvedRequests = new ArrayList<>();
    private List<String> requestLocation = new ArrayList<>(); //stores the location of each request in totalRequests by id

    /**
     * Returns the contents of this Request including the id and status.
     * @param id the id for this Request
     * @return the Request id, Request content, and the Request's status as a string
     */
    public String getRequest(String id){
        int location = requestLocation.indexOf(id);
        if (location != -1){
            Request request = totalRequests.get(location);
            return request.getId() + ": " + request.getNote() + " (" + request.getStatus().name() + ")";
        }
        else{
            return null;
        }
    }

    /**
     * Returns the total number of requests that attendees and speakers have made
     * @return the total number of Requests
     */

    public Integer getNumRequests(){
        return totalRequests.size();
    }

    /**
     * Returns a boolean for whether or not the Request was created successfully.  If the request was created
     * successfully it returns true otherwise it returns false.
     * @param note the note detailing the Request
     * @return whether or not the creation of a new Request was successful
     */
    public boolean addRequest(String note){
        if (note != null){
            Integer requestID = getNumRequests() + 1;
            Request newRequest = new Request(note, requestID);
            totalRequests.add(newRequest);
            requestLocation.add(newRequest.getId());
            pendingRequests.add(newRequest); //by default all requests are pending when created
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * Returns true if the Request was deleted successfully and false otherwise.
     * @param id the id for the request to be deleted
     * @return whether or not the request was deleted
     */
    public boolean removeRequest(String id){
        int location = requestLocation.indexOf(id);
        if (location != -1){
            Request deleteRequest = totalRequests.get(location);
            totalRequests.remove(deleteRequest);
            pendingRequests.remove(deleteRequest);
            resolvedRequests.remove(deleteRequest);
            requestLocation.remove(location);
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Returns a List of all the requests made no matter the status specifying each Request's id, the note detailing
     * the request, and the status.
     * @return a List of all the requests made with each one as a String
     */

    public List<String> getTotalRequests(){

        ArrayList<String> allRequests = new ArrayList<>();

        for (Request request : totalRequests){
            allRequests.add(request.getId() + ": " + request.getNote() + " " + "(" + request.getStatus().name() + ")");
        }

        return allRequests;
    }

    /**
     * Returns a List of all requests that are pending.  Each Request is represented as a single string in the list
     * with its id and note specifying the request.
     * @return a List of all the pending requests each as a String with its id and note specifying the request
     */

    public List<String> getPendingRequests() {
        ArrayList<String> pending = new ArrayList<>();
        for (Request request : pendingRequests) {
            pending.add(request.getId() + ": " + request.getNote());
        }

        return pending;
    }

    /**
     * Returns a List of all requests that are resolved.  Each Request is represented as a single string in the list
     * with its id and note specifying the request.
     * @return a List of all the resolved requests each as a String with its id and note specifying the request
     */

    public List<String> getResolvedRequests(){
        ArrayList<String> resolved = new ArrayList<>();
        for (Request request : resolvedRequests) {
            resolved.add(request.getId() + ": " + request.getNote());
        }
        return resolved;

    }

    /**
     * Returns true if the status of the Request was changed to a new status, false otherwise.
     * @param id the id of the request
     * @param newStatus the new status to change the Request's status to
     * @return whether the status of the Request was changed to a new status
     */

    public boolean changeStatus(String id, Status newStatus){
        //1. find the request
        //2. see if the status changed
        //3. move it and change status

        //found the request
        if (requestLocation.contains(id)){
            Request current = totalRequests.get(requestLocation.indexOf(id));
           //see if the status is changed
            if (current.getStatus() != newStatus){
                //move the request
                if (current.getStatus() == Status.PENDING){
                    //find in pending
                    //add to resolved
                    int pendingLocation = pendingRequests.indexOf(current);
                    Request wasPending = pendingRequests.get(pendingLocation);
                    pendingRequests.remove(wasPending);
                    wasPending.changeStatus(newStatus);
                    resolvedRequests.add(wasPending);
                    return true;

                }
                else {
                    //find in resolved
                    //add to pending
                    int resolvedLocation = resolvedRequests.indexOf(current);
                    Request wasResolved = resolvedRequests.get(resolvedLocation);
                    resolvedRequests.remove(wasResolved);
                    wasResolved.changeStatus(newStatus);
                    pendingRequests.add(wasResolved);
                    return true;
                }

            }
            //same status as before
            else{
                return false;
            }
        }

        //not in the requests
        else {
            return false;
        }

    }

    /**
     * Returns a list of all the possible statuses that a request can be marked as.
     * @return a list of all the statuses that a request can be marked as where each is a string in the list
     */

    public List<String> getAllStatus(){
        ArrayList<String> availableStatus = new ArrayList<>();
        for (Status s : Status.values()){
            availableStatus.add(s.name());
        }
        return availableStatus;
    }

    /**
     * Returns the Status object that is associated with the string representation of that status.
     * @param status the status represented as a String
     * @return the Status object associated with the argument status (the string representation of it)
     */

    public Status giveStatus(String status){
        for (Status s : Status.values()){
            if (s.name().equals(status)){
                return s;
            }
        }
        return null;
    }

    /**
     * Returns the String representation of this Request's Status
     * @param id the id of this Request
     * @return the Status of this Request as a String
     */

    public String getStatus(String id) {

        return totalRequests.get(requestLocation.indexOf(id)).getStatus().name();
    }
}
