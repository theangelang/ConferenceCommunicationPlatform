package useCases;

import entities.MessageChain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class that managers message functions.
 */
public class MessageManager implements Serializable {

    private List<MessageChain> allChats;
    private String[] currentViewed = null;

    /**
     * Creates an empty message manager.
     */
    public MessageManager() {
        allChats = new ArrayList<>();
    }

    /**
     * Send message to all participants.
     * Return true if is at least one participant.
     * @param sender the string ID of the sender
     * @param participants string array of participants in the MessageChain.
     * @param message the message to be sent.
     * @return true if there is at least one receiver of message, and message sent successfully.
     */
    public boolean sendMessage(String sender, String[] participants, String message, UserManager userManager) {

        if (userManager.getUser(participants[0]) == null || userManager.getUser(participants[1]) == null) {
            return false;
        }

        String[] altP = {participants[1], participants[0]};

        for (MessageChain m : allChats) {
            if (Arrays.equals(m.getParticipants(), participants) || Arrays.equals(m.getParticipants(), altP)) {
                m.addMessage(message, sender);
                return true;
            }
        }

        return false;
    }

    /**
     * create a messageChain of two participants.
     * Return true if message chain created successfully.
     * @param participants string array of participants in the MessageChain.
     * @return true if message chain created successfully.
     */
    public boolean createMessageChain(String[] participants) {
        if (getMessage(participants) == null) {
            allChats.add(new MessageChain(participants));
            return true;
        }

        return false;
    }

    /**Get a chat of the first user at index 0 with the second user at index 1 in participants.
     * @param participants the participants for the MessageChain.
     * @return MessageChain the message chain between two participants.
     * @return null if such message chain does not found
     */
    public MessageChain getMessage(String[] participants) {
        String[] altP = {participants[1], participants[0]};
        for (MessageChain m : allChats){
            if (Arrays.equals(m.getParticipants(), participants) || Arrays.equals(m.getParticipants(), altP)) {
                return m;
            }
        }
        return null;
    }


    public boolean setCurrentViewed(String[] participants) {
        if (getMessage(participants) != null) {
            currentViewed = participants;
            return true;
        }
        currentViewed = null;
        return false;
    }

    public void clearCurrentViewed(String user) {
        MessageChain m = getMessage(currentViewed);
        if (m != null) {
            getMessage(currentViewed).clearMessagesFromUser(user);
        }
        currentViewed = null;
    }

    public String[] getCurrentViewed() {
        return currentViewed;
    }

    public boolean archive(int i) {
        MessageChain m = getMessage(currentViewed);

        if (m != null) {
            return m.archiveMessage(i);
        }
        return false;
    }

    public boolean mark(int i) {
        MessageChain m = getMessage(currentViewed);

        if (m != null) {
            return m.markMessage(i);
        }
        return false;
    }
}
