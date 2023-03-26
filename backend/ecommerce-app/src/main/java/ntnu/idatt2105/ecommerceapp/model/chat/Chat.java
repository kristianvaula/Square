package ntnu.idatt2105.ecommerceapp.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chat {

    private int chatId;
    private int isUnread;
    private int profile1;
    private int profile2;

    public Chat() {
    }

    public Chat(int profile1, int profile2) {
        this.profile1 = profile1;
        this.profile2 = profile2;
    }

    public Chat(@JsonProperty("chatId") int chatId, @JsonProperty("profile1") int profile1,
                @JsonProperty("profile2") int profile2, @JsonProperty("isUnread") int isUnread) {
        this.chatId = chatId;
        this.profile1 = profile1;
        this.profile2 = profile2;
        this.isUnread = isUnread;
    }

    @JsonProperty("chatId")
    public int getChatId() {
        return chatId;
    }

    @JsonProperty("profile1")
    public int getProfile1() {
        return profile1;
    }

    @JsonProperty("profile2")
    public int getProfile2() {
        return profile2;
    }

    @JsonProperty("isUnread")
    public boolean isUnread() {
        return getBooleanFromTinyInt(isUnread);
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setIsUnread(int isUnread) {
        this.isUnread = isUnread;
    }

    public void setProfile1(int profile1) {
        this.profile1 = profile1;
    }

    public void setProfile2(int profile2) {
        this.profile2 = profile2;
    }

    public int getTinyintFromBoolean(boolean isUnread) {
        return isUnread ? 1 : 0;
    }

    public boolean getBooleanFromTinyInt(int isUnread) {
        return isUnread == 1;
    }
}
