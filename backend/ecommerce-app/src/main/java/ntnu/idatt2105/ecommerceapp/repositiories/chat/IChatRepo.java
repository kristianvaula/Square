package ntnu.idatt2105.ecommerceapp.repositiories.chat;

import ntnu.idatt2105.ecommerceapp.model.chat.Chat;
import ntnu.idatt2105.ecommerceapp.model.chat.Message;
import ntnu.idatt2105.ecommerceapp.model.chat.MessageRequest;

import java.util.List;

/**
 * Interface for a chat repository
 */
public interface IChatRepo {

    /**
     * Method to get the chats
     * @param profileId the profileId
     * @return a list containing the chats
     */
    List<Chat> getChats(int profileId);

    /**
     * Method to add a new chat
     * @param chat the chat to add
     * @return Boolean value representing if the chat was added or not
     */
    boolean addChat(Chat chat);

    /**
     * Method tho get a specific chat
     * @param chatId the id of the chat you want to get
     * @return the chat with the specified chatId
     */
    Chat getChat(int chatId);

    /**
     * Method to set the isUnread status to false
     * @param chatId the id of the chat
     * @return the isUnread status
     */
    boolean readChat(int chatId);

    /**
     * Method to get the messages in a chat
     * @param chatId the id of the chat
     * @return a list containing the messages
     */
    List<Message> getMessages(int chatId);

    /**
     * Method to add a new message
     * @param message the message to add
     * @return Boolean value representing whether the message was added or not
     */
    boolean addMessage(MessageRequest message);

    /**
     * Method to get the participantId
     * @param chatId the id of the chat
     * @param myProfileId the id of the profile
     * @return the participantId
     */
    int getParticipantId(int chatId, int myProfileId);

}
