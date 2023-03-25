package ntnu.idatt2105.ecommerceapp.services.chat;

        import ntnu.idatt2105.ecommerceapp.model.chat.Chat;
        import ntnu.idatt2105.ecommerceapp.model.chat.Message;
        import ntnu.idatt2105.ecommerceapp.model.chat.MessageRequest;
        import ntnu.idatt2105.ecommerceapp.repositiories.chat.ChatRepo;
        import ntnu.idatt2105.ecommerceapp.repositiories.profile.IProfileDao;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
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
    @Autowired
    private IProfileDao profileDao;
    private Logger logger = LoggerFactory.getLogger(ChatService.class);

    //todo: remove errorSearching
    public List<Chat> getChats(int profileId) {
        logger.info("Gets chats for profileId {} from database", profileId);
        List<Chat> chats = chatRepo.getChats(profileId);
        Chat chat = chats.get(0);
        logger.info("Logging chat info {}, {}, {}, {}", chat.getChatId(), chat.isUnread(), chat.getProfile1(), chat.getProfile2() );
        return chats;
    }

    public boolean addChat(Chat chat) {
        logger.info("Adding new chat for profileId {} and profileId {}", chat.getProfile1(), chat.getProfile2());
        boolean status = chatRepo.addChat(chat);
        logger.info("Status for adding chat is {}", status);
        //return chat.getChatId(); todo: should return new chatId?
        return status;
    }

    //todo: remove errorSearching
    public List<Message> getMessages(int chatId) {
        logger.info("Retrieving messages for chatId {}", chatId);
        List<Message> messages = chatRepo.getMessages(chatId);
        logger.info("Messages is retrieved from chatId {}", chatId);
        boolean statusReading = chatRepo.readChat(chatId);
        logger.info("Status for reading messages in chat {} is {}", chatId, statusReading);

        logger.info("Logging message info {}, {}, {}, {}", messages.get(0).getChatId(), messages.get(0).getText(), messages.get(0).getTimeStamp(), messages.get(0).getSenderId() );
        return messages;
    }

    /**
     *
     * @param message
     * @return The chatId where it is a new chat
     */
    public int addMessage(MessageRequest message) {
        logger.info("Adding message with text {} to chatId {}, the message is from profileId {}", message.getText(), message.getChatId(), message.getSenderId());
        boolean status = chatRepo.addMessage(message);
        logger.info("Status for adding message with message with text {} to chatId {} is {}", message.getText(), message.getChatId(), status);
        return message.getChatId();
    }

    public String getParticipant(int chatId, String myEmail) {
        return null;
    }
}

