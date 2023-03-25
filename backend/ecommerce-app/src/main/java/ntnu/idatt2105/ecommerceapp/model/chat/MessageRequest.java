package ntnu.idatt2105.ecommerceapp.model.chat;

public class MessageRequest {
    private int chatId;
    private String text;
    private int senderId;

    public MessageRequest() {
    }

    public MessageRequest(int chatId, String text, int senderId) {
        this.chatId = chatId;
        this.text = text;
        this.senderId = senderId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}
