package ntnu.idatt2105.ecommerceapp.controllers.chat;

import ntnu.idatt2105.ecommerceapp.model.chat.Chat;
import ntnu.idatt2105.ecommerceapp.model.chat.Message;
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
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;
    Logger logger = LoggerFactory.getLogger(ntnu.idatt2105.ecommerceapp.controllers.RegisterProfileController.class);

    /**
     * Returns an empty list if chats is null...
     * @return chats. A list holding all active chats
     */
    @GetMapping("/my-chats")
    public ResponseEntity<List<Chat>> getChats(int profileId) {
        logger.info("Received a request to get active chats");
        List<Chat> chats = chatService.getChats(profileId);
        if (chats == null) {
            logger.info("Could not find any chats");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Returned list with chats");
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    /**
     * Create a new chat and add it to the database
     * @param chat
     */
    @PostMapping("/new-chat")
    public void newChat(@RequestBody Chat chat){
        logger.info("Received a request to create a new chat");
        chatService.addChat(chat);
    }

    /**
     * Returns an empty list if messages is null...
     * @return messages. A list holding all sent messages
     */
    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<Message>> getMessages(int chatId) {
        logger.info("Received a request to get messages in chat");
        List<Message> messages = chatService.getMessages(chatId);
        if (messages == null) {
            logger.info("Could not find any messages");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Returned list with messages");
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    /**
     * Create a new message and add it to the database
     * @param message
     */
    @PostMapping("/{chatId}/new-message")
    public void newMessage(@RequestBody Message message){
        logger.info("Received a request to create a new message");
        chatService.addMessage(message);
    }

}
