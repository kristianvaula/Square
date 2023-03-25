package ntnu.idatt2105.ecommerceapp.repositiories.chat;

import ntnu.idatt2105.ecommerceapp.model.chat.Chat;
import ntnu.idatt2105.ecommerceapp.model.chat.Message;

import java.util.List;

public interface ChatRepoInterface {

    List<Chat> getChats(int profileId);
    void addChat(Chat chat);

    List<Message> getMessages(int chatId);
    void addMessage(Message message);

}
