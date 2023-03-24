package ntnu.idatt2105.ecommerceapp.model.chat;

import java.util.Date;

public class Message {

    private int messageId;
    private String text;
    private Date timeStamp;
    private int chatId;
    private int senderId;


    public Message(int messageId, String text, Date timeStamp, int chatId, int senderId) {
        this.messageId = messageId;
        this.text = text;
        this.timeStamp = timeStamp;
        this.chatId = chatId;
        this.senderId = senderId;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getText() {
        return text;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getChatId() {
        return chatId;
    }

    public int getSenderId() {
        return senderId;
    }
}
