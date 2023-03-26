package ntnu.idatt2105.ecommerceapp.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParticipantRequest {
    private int chatId;
    private String myEmail;

    public ParticipantRequest() {
    }

    public ParticipantRequest(@JsonProperty("chatId") int chatId, @JsonProperty("eMail") String myEmail) {
        this.chatId = chatId;
        this.myEmail = myEmail;
    }

    @JsonProperty("chatId")
    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    @JsonProperty("eMail")
    public String getMyEmail() {
        return myEmail;
    }

    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }
}
