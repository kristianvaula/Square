package ntnu.idatt2105.ecommerceapp.repositiories.chat;

import ntnu.idatt2105.ecommerceapp.model.chat.Chat;
import ntnu.idatt2105.ecommerceapp.model.chat.Message;
import ntnu.idatt2105.ecommerceapp.model.chat.MessageRequest;

import java.util.List;

public interface IChatRepo {

    List<Chat> getChats(int profileId);

    /**
     *
     * @param chat
     * @return Boolean value as represent if the chat is added or not
     */
    boolean addChat(Chat chat);

    Chat getChat(int chatId);

    /**
     *
     * @param chatId
     * @return Status for reading
     */
    boolean readChat(int chatId);
    List<Message> getMessages(int chatId);

    /**
     *
     * @param message
     * @return Boolean value as represent if the message is added or not
     */
    boolean addMessage(MessageRequest message);

    int getParticipantId(int chatId, int myProfileId);

}
