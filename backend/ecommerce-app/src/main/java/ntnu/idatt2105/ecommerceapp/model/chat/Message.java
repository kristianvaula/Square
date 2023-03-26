package ntnu.idatt2105.ecommerceapp.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Message {

    private int messageId;
    private String text;
    private Date timeStamp;
    private int chatId;
    private int senderId;

    public Message() {
    }

    public Message(@JsonProperty("messageId") int messageId, @JsonProperty("text") String text,
                   @JsonProperty("timeStamp") Date timeStamp, @JsonProperty("chatId") int chatId,
                   @JsonProperty("senderId") int senderId) {
        this.messageId = messageId;
        this.text = text;
        this.timeStamp = timeStamp;
        this.chatId = chatId;
        this.senderId = senderId;
    }

    @JsonProperty("messageId")
    public int getMessageId() {
        return messageId;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("timeStamp")
    public Date getTimeStamp() {
        return timeStamp;
    }

    @JsonProperty("chatId")
    public int getChatId() {
        return chatId;
    }

    @JsonProperty("senderId")
    public int getSenderId() {
        return senderId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}
