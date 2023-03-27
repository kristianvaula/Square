package ntnu.idatt2105.ecommerceapp.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The class represents a chat
 * It contains getters and setters for attributes
 */
public class Chat {

    private int chatId;
    private int isUnread;
    private int profile1;
    private int profile2;

    /**
     * Default constructor
     */
    public Chat() {
    }

    /**
     * Constructor for creating a chat between two profiles
     * @param profile1 profile1 to participate in the chat
     * @param profile2 profile2 to participate in the chat
     */
    public Chat(int profile1, int profile2) {
        this.profile1 = profile1;
        this.profile2 = profile2;
    }

    /**
     * Constructor for creating a chat object
     * @param chatId the id of the chat
     * @param profile1 profile1 to participate in the chat
     * @param profile2 profile2 to participate in the chat
     * @param isUnread an int value representing if the chat is read or not
     */
    public Chat(@JsonProperty("chatId") int chatId, @JsonProperty("profile1") int profile1,
                @JsonProperty("profile2") int profile2, @JsonProperty("isUnread") int isUnread) {
        this.chatId = chatId;
        this.profile1 = profile1;
        this.profile2 = profile2;
        this.isUnread = isUnread;
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
     * Getter for profile1
     * @return profile1
     */
    @JsonProperty("profile1")
    public int getProfile1() {
        return profile1;
    }

    /**
     * Getter for profile2
     * @return profile2
     */
    @JsonProperty("profile2")
    public int getProfile2() {
        return profile2;
    }

    /**
     * Getter for isUnread
     * @return isUnread
     */
    @JsonProperty("isUnread")
    public boolean isUnread() {
        return getBooleanFromTinyInt(isUnread);
    }

    /**
     * Setter for chatId
     * @param chatId new chatId
     */
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    /**
     * Setter for isUnread
     * @param isUnread new isUnread value
     */
    public void setIsUnread(int isUnread) {
        this.isUnread = isUnread;
    }

    /**
     * Setter for profile1
     * @param profile1 new profile1
     */
    public void setProfile1(int profile1) {
        this.profile1 = profile1;
    }

    /**
     * Setter for profile2
     * @param profile2 new profile2
     */
    public void setProfile2(int profile2) {
        this.profile2 = profile2;
    }

    /**
     * Getter for int value of isUnread
     * @param isUnread as boolean
     * @return isUnread as int value
     */
    public int getTinyintFromBoolean(boolean isUnread) {
        return isUnread ? 1 : 0;
    }

    /**
     * Getter for boolean value of isUnread
     * @param isUnread as int value
     * @return isUnread as boolean
     */
    public boolean getBooleanFromTinyInt(int isUnread) {
        return isUnread == 1;
    }

}
