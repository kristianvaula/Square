package ntnu.idatt2105.ecommerceapp.model.chat;

/**
 * The class represents a message-request
 * It contains getters and setters for attributes
 */
public class MessageRequest {

    private int chatId;
    private String text;
    private int senderId;

    /**
     * Default constructor
     */
    public MessageRequest() {
    }

    /**
     * Constructor for creating a message-request
     * @param chatId the id of the chat the message is sent in
     * @param text the message text
     * @param senderId the id of the profile sending the message
     */
    public MessageRequest(int chatId, String text, int senderId) {
        this.chatId = chatId;
        this.text = text;
        this.senderId = senderId;
    }

    /**
     * Getter for chatId
     * @return chatId
     */
    public int getChatId() {
        return chatId;
    }

    /**
     * Setter for chatId
     * @param chatId new chatId
     */
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    /**
     * Getter for text
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for text
     * @param text new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter for senderId
     * @return senderId
     */
    public int getSenderId() {
        return senderId;
    }

    /**
     * Setter for senderId
     * @param senderId new senderId
     */
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}
