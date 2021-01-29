package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An instance of this class represents a chat between 2 users.
 */
public class MessageChain implements Serializable {

    private String[] participants;
    private ArrayList<String> messages;
    private ArrayList<String> archived = new ArrayList<>();
    private ArrayList<Integer> marked = new ArrayList<>();

    /** Constructs a new MessageChain.
     *
     * @param participants a string array of participants in the MessageChain.
     */
    public MessageChain(String[] participants) {
        this.participants = participants;
        messages = new ArrayList<>();
    }

    /** Adds a message to the MessageChain
     *
     * @param message string message to send.
     * @param sender the string ID of the sender.
     */
    public void addMessage(String message, String sender) {
        messages.add(sender +": " + message);
    }

    /** Returns a string array of participants in the MessageChain.
     */
    public String[] getParticipants() {
        return this.participants;
    }

    /** Returns a list of strings which contain the messages.
     */
    public List<String> toArray() {
        return messages;
    }

    /**
     * Removes all non-archived messages sent by user
     */
    public void clearMessagesFromUser(String user) {
        ArrayList<String> after = new ArrayList<>();
        for (String s: messages) {
            if (!s.split(" ")[0].equals(user+":") || marked.contains(messages.indexOf(s))) {
                after.add(s);
            }
        }
        messages = after;
        marked.clear();
    }

    /**
     * Archives a message
     * @param i the index of the message
     * @return whether or not the message could be archived
     */
    public boolean archiveMessage(int i) {
        if (! (0 <= i && i < messages.size())) return false;
        String m = messages.get(i);
        if (archived.contains(m)) return false;

        if (archived.size() > 0 && messages.contains(archived.get(archived.size()-1))) {
            ArrayList<Integer> indices = new ArrayList<>();
            for (int j = archived.size() - 1;j >= 0;j--) {
                if (!messages.contains(archived.get(j))) {
                    break;
                }
                indices.add(0, messages.indexOf(archived.get(j)));
            }

            for (int j=0;j < indices.size();j++) {
                if (i < indices.get(j)) {
                    indices.add(j, i);
                    break;
                }
            }
            if (!indices.contains(i)) indices.add(i);

            archived.add(archived.size() - indices.size() + 1 + indices.indexOf(i), m);

        } else {
            archived.add(m);
        }

        return true;
    }

    public boolean markMessage(int i) {
        if (! (0 <= i && i < messages.size()) || marked.contains(i)) return false;

        marked.add(i);
        return true;
    }

    public List<String> getArchive() {
        return archived;
    }
}
