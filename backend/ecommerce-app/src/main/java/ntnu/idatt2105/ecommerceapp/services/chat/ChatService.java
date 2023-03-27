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

    /**
     * The method gets all the chats related to a specific profile
     * @param profileId the id of the profile
     * @return a list containing all the chats
     */
    public List<Chat> getChats(int profileId) {
        logger.info("Gets chats for profileId {} from database", profileId);
        List<Chat> chats = chatRepo.getChats(profileId);
        if (chats.isEmpty()) {
            logger.info("The received list empty");
        }
        logger.info("Received list with chats");
        return chats;
    }

    /**
     * The method adds a net chat
     * @param chat the chat to add
     * @return Boolean value representing if the chat was added or not
     */
    public boolean addChat(Chat chat) {
        logger.info("Adding new chat for profileId {} and profileId {}", chat.getProfile1(), chat.getProfile2());
        boolean status = chatRepo.addChat(chat);
        logger.info("Status for adding chat is {}", status);
        return status;
    }

    /**
     * The method gets all the messages related to a specific chat
     * @param chatId the id of the chat
     * @return a list containing all the messages
     */
    public List<Message> getMessages(int chatId) {
        logger.info("Retrieving messages for chatId {}", chatId);
        List<Message> messages = chatRepo.getMessages(chatId);
        if (messages.isEmpty()) {
            logger.info("The received list empty");
        }
        logger.info("Received list with messages");
        return messages;
    }

    /**
     * The method adds a new message
     * @param message the message to add
     * @return The chatId where there is a new chat
     */
    public int addMessage(MessageRequest message) {
        logger.info("Adding message with text {} to chatId {}, the message is from profileId {}", message.getText(), message.getChatId(), message.getSenderId());
        boolean status = chatRepo.addMessage(message);
        logger.info("Retrieving chat with chatId {}", message.getChatId());
        Chat chat = chatRepo.getChat(message.getChatId());
        logger.info("Get status for the chat with chatId {} is {}", message.getChatId(), chat != null);
        return message.getChatId();
    }

    /**
     * The method sets the isUnread status to false
     * @param chatId the id of the chat
     * @return the new isUnread status
     */
    public boolean readChat(int chatId) {
        logger.info("Reading chat with chatId {}", chatId);
        boolean statusReading = chatRepo.readChat(chatId);
        logger.info("The readingStatus is: {}", statusReading);
        return !statusReading;
    }

    /**
     * The method gets the email of the other participant in the chat
     * @param chatId the id of the chat
     * @param myEmail your email
     * @return the other participants´ email
     */
    public String getParticipant(int chatId, String myEmail) {
        int myProfileId = profileDao.getProfile(myEmail).getProfileId();
        logger.info("Retrieving profileId for the participant");
        int participantId = chatRepo.getParticipantId(chatId, myProfileId);
        logger.info("Retrieved participantId {}", participantId);
        logger.info("Retrieving eMail for the participant with id {}", participantId);
        String participantEmail = profileDao.getProfileEmail(participantId);
        logger.info("Got following e-mail {} for profileId {}", participantEmail, participantId);
        return participantEmail;
    }
}

