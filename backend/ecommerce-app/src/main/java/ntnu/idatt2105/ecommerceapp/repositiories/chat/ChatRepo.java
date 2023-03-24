package ntnu.idatt2105.ecommerceapp.repositiories.chat;

import ntnu.idatt2105.ecommerceapp.model.chat.Chat;
import ntnu.idatt2105.ecommerceapp.model.chat.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepo implements ChatRepoInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Chat> getChats(int profileId) {
        return jdbcTemplate.query("SELECT * FROM chat WHERE profileId=?",
                BeanPropertyRowMapper.newInstance(Chat.class), profileId);
    }

    @Override
    public void addChat(Chat chat) {
        jdbcTemplate.update("INSERT INTO chat (isUnread, profile1, profile2) VALUES(?,?,?)",
                chat.isUnread(), chat.getProfile1(), chat.getProfile2());
    }

    @Override
    public List<Message> getMessages(int chatId) {
        return jdbcTemplate.query("SELECT * FROM message WHERE chatId=?",
                BeanPropertyRowMapper.newInstance(Message.class), chatId);
    }

    @Override
    public void addMessage(Message message) {
        jdbcTemplate.update("INSERT INTO message (text, timeStamp, chatId, senderId) VALUES(?,?,?,?)",
                message.getText(), message.getTimeStamp(), message.getChatId(), message.getSenderId());
    }

}
