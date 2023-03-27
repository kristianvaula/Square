package ntnu.idatt2105.ecommerceapp.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The class represents a participant-request
 * It contains getters and setters for attributes
 */
public class ParticipantRequest {

    private int chatId;
    private String myEmail;

    /**
     * Default constructor
     */
    public ParticipantRequest() {
    }

    /**
     * Constructor for creating a participant-request
     * @param chatId the id of the chat
     * @param myEmail the email of the user
     */
    public ParticipantRequest(@JsonProperty("chatId") int chatId, @JsonProperty("eMail") String myEmail) {
        this.chatId = chatId;
        this.myEmail = myEmail;
    }

    /**
     * Getter for chatId
     * @return chatId
     */
    @JsonProperty("chatId")
    public int getChatId() {
        return chatId;
    }

    /**
     * Setter fir chatId
     * @param chatId newChatId
     */
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    /**
     * Getter for myEmail
     * @return myEmail
     */
    @JsonProperty("eMail")
    public String getMyEmail() {
        return myEmail;
    }

    /**
     * Setter for myEmail
     * @param myEmail new email
     */
    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }
}
