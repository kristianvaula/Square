package ntnu.idatt2105.ecommerceapp.services.chat;

        import ntnu.idatt2105.ecommerceapp.model.chat.Chat;
        import ntnu.idatt2105.ecommerceapp.model.chat.Message;
        import ntnu.idatt2105.ecommerceapp.repositiories.chat.ChatRepo;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

/**
 * Service class for chat
 * Provides mechanism to control, add and get chat messages.
 */
@Service
public class ChatService {

    @Autowired
    private ChatRepo chatRepo;

    public List<Chat> getChats(int profileId) {
        return chatRepo.getChats(profileId);
    }

    public int addChat(Chat chat) {
        chatRepo.addChat(chat);
        return chat.getChatId();
    }

    public List<Message> getMessages(int chatId) {
        return chatRepo.getMessages(chatId);
    }

    public int addMessage(Message message) {
        chatRepo.addMessage(message);
        return message.getMessageId();
    }


}

