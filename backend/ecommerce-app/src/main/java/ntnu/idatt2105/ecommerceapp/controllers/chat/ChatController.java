package ntnu.idatt2105.ecommerceapp.controllers.chat;

import ntnu.idatt2105.ecommerceapp.model.chat.Chat;
import ntnu.idatt2105.ecommerceapp.model.chat.Message;
import ntnu.idatt2105.ecommerceapp.model.chat.MessageRequest;
import ntnu.idatt2105.ecommerceapp.model.chat.ParticipantRequest;
import ntnu.idatt2105.ecommerceapp.services.chat.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/unauthorized/chat") //todo: add user to url - only users should have the ability to send chats
public class ChatController {

    @Autowired
    private ChatService chatService;
    Logger logger = LoggerFactory.getLogger(ntnu.idatt2105.ecommerceapp.controllers.RegisterProfileController.class);

    /**
     * Returns an empty list if chats is null...
     * @return chats. A list holding all active chats
     */
    @GetMapping("/my-chats/{profileId}")
    public ResponseEntity<List<Chat>> getChats(@PathVariable int profileId) {
        logger.info("Received a request to get chats for profileId {}", profileId);
        List<Chat> chats = chatService.getChats(profileId);
        if (chats == null) {
            logger.info("Could not find any chats for profileId {}", profileId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Returned list with chats for profileId {}", profileId);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    /**
     * Create a new chat and add it to the database
     * @param chat
     */
    @PostMapping("/new-chat")
    public void newChat(@RequestBody Chat chat){
        logger.info("Received a request to create a new chat");
        boolean status = chatService.addChat(chat);
        logger.info("Status adding new chat {}", status);
    }

    /**
     * Returns an empty list if messages is null...
     * @return messages. A list holding all sent messages
     */
    @GetMapping("/messages/{chatId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable int chatId) {
        logger.info("Received a request to get messages in chat with chatId {}", chatId);
        List<Message> messages = chatService.getMessages(chatId);
        if (messages == null) {
            logger.info("Could not find any messages in chat with chatId {}", chatId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Returned list with messages for chat with chatId {}", chatId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    /**
     * Create a new message and add it to the database
     * @param message
     */
    @PostMapping("/new-message")
    public void newMessage(@RequestBody MessageRequest message){
        logger.info("Received a request to create a new message for chatId {}, the messageTxt is {}", message.getChatId(), message.getText());
        int messageId = chatService.addMessage(message);
        logger.info("Message with messageId {} is added to chatId {}", messageId, message.getChatId());
    }

    @PostMapping("/participant")
    public ResponseEntity<String> getParticipant(@RequestBody ParticipantRequest participantRequest){
        logger.info("Received a request from {} to get the other profile in chat with chatId {}", participantRequest.getMyEmail(), participantRequest.getChatId());
        String participantEmail = chatService.getParticipant(participantRequest.getChatId(), participantRequest.getMyEmail());
        logger.info("The other participant in chat with chatId {} is {}", participantRequest.getChatId(), participantEmail);
        if (participantEmail != null) {
            return new ResponseEntity<>(participantEmail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
