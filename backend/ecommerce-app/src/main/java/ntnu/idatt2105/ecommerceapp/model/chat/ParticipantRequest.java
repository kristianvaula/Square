package ntnu.idatt2105.ecommerceapp.model.chat;

public class ParticipantRequest {
    private int chatId;
    private String myEmail;

    public ParticipantRequest() {
    }

    public ParticipantRequest(int chatId, String myEmail) {
        this.chatId = chatId;
        this.myEmail = myEmail;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getMyEmail() {
        return myEmail;
    }

    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }
}
