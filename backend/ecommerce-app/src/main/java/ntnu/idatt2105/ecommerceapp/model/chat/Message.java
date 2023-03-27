package ntnu.idatt2105.ecommerceapp.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Date;

/**
 * The class represents a message
 * It contains getters and setters for attributes
 */
public class Message {

    private int messageId;
    private String text;
    private Date timeStamp;
    private int chatId;
    private int senderId;

    /**
     * Default constructor
     */
    public Message() {
    }

    /**
     * Constructor for creating a chat object using java
     * @param messageId the id of the message
     * @param text the message text
     * @param timeStamp for when the message is sent
     * @param chatId the id of the chat the message is sent in
     * @param senderId the id of the profile sending the message
     */
    public Message(@JsonProperty("messageId") int messageId, @JsonProperty("text") String text,
                   @JsonProperty("timeStamp") Date timeStamp, @JsonProperty("chatId") int chatId,
                   @JsonProperty("senderId") int senderId) {
        this.messageId = messageId;
        this.text = text;
        this.timeStamp = timeStamp;
        this.chatId = chatId;
        this.senderId = senderId;
    }

    /**
     * Constructor for creating a chat object using the database
     * @param messageId the id of the message
     * @param text the message text
     * @param timeStamp for when the message is sent
     * @param chatId the id of the chat the message is sent in
     * @param senderId the id of the profile sending the message
     */
    public Message(@JsonProperty("messageId") int messageId, @JsonProperty("text") String text,
                   @JsonProperty("timeStamp") Timestamp timeStamp, @JsonProperty("chatId") int chatId,
                   @JsonProperty("senderId") int senderId) {
        this.messageId = messageId;
        this.text = text;
        this.timeStamp = new Date(timeStamp.getTime());
        this.chatId = chatId;
        this.senderId = senderId;
    }

    /**
     * Getter for messageId
     * @return messageId
     */
    @JsonProperty("messageId")
    public int getMessageId() {
        return messageId;
    }

    /**
     * Getter for text
     * @return text
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     * Getter for timeStamp
     * @return timeStamp
     */
    @JsonProperty("timeStamp")
    public Date getTimeStamp() {
        return timeStamp;
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
     * Getter for senderId
     * @return senderId
     */
    @JsonProperty("senderId")
    public int getSenderId() {
        return senderId;
    }

    /**
     * Setter for messageId
     * @param messageId new messageId
     */
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    /**
     * Setter for text
     * @param text new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Setter for timeStamp in Date format
     * @param date new date
     */
    public void setTimeStamp(Date date) {
        this.timeStamp = timeStamp;
    }

    /**
     * Setter for timeStamp in Timestamp format
     * @param timeStamp new timeStamp
     */
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = new Date(timeStamp.getTime());
    }

    /**
     * Setter for chatId
     * @param chatId new chatId
     */
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    /**
     * Setter for senderId
     * @param senderId new senderId
     */
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    /**
     * toString method for message
     * @return formatted sting containing chatId, text, timestamp and senderId
     */
    @Override
    public String toString() {
        return "chatId: " + chatId + "message: " + text + ", " + timeStamp + ", " + ", senderId " + senderId;
    }
}
