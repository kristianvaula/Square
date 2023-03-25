package ntnu.idatt2105.ecommerceapp.model.chat;

import ntnu.idatt2105.ecommerceapp.model.profiles.Profile;

public class Chat {

    private int chatId;
    private boolean isUnread;
    private Profile profile1;
    private Profile profile2;

    public Chat(int chatId, Profile profile1, Profile profile2, boolean isUnread) {
        this.chatId = chatId;
        this.profile1 = profile1;
        this.profile2 = profile2;
        this.isUnread = isUnread;
    }

    public int getChatId() {
        return chatId;
    }

    public Profile getProfile1() {
        return profile1;
    }

    public Profile getProfile2() {
        return profile2;
    }

    public boolean isUnread() {
        return isUnread;
    }

}
