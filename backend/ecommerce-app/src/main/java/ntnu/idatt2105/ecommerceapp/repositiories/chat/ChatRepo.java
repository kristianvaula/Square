package ntnu.idatt2105.ecommerceapp.repositiories.chat;

import ntnu.idatt2105.ecommerceapp.model.chat.Chat;
import ntnu.idatt2105.ecommerceapp.model.chat.Message;
import ntnu.idatt2105.ecommerceapp.model.chat.MessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ChatRepo implements IChatRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(ChatRepo.class);

    @Override
    public List<Chat> getChats(int profileId) {
        return jdbcTemplate.query("SELECT * FROM chat WHERE profile1=? OR profile2=?",
                BeanPropertyRowMapper.newInstance(Chat.class), profileId, profileId);
    }

    @Override
    public Chat getChat(int chatId) {
        return jdbcTemplate.queryForObject("SELECT * FROM chat WHERE chatId=?",
                BeanPropertyRowMapper.newInstance(Chat.class), chatId);
    }

    @Override
    public boolean addChat(Chat chat) {
        chat.setIsUnread(1);//setting the new chat to be unread
        logger.info("The new chat has isUnread set to {}", chat.isUnread());
        int rowsAffected = jdbcTemplate.update("INSERT INTO chat (isUnread, profile1, profile2) VALUES(?,?,?)",
                chat.isUnread(), chat.getProfile1(), chat.getProfile2());
        if (rowsAffected == 1) {
            rowsAffected = jdbcTemplate.update("UPDATE chat SET isUnread = 1 WHERE chatId = ?", chat.getChatId());
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean readChat(int chatId) {
        logger.info("Reading from chatId {}", chatId);
        int rowsAffected = jdbcTemplate.update("UPDATE chat SET isUnread = 0 WHERE chatId = ?", chatId);
        logger.info("Reading status for chat with chatId {} is {}", chatId, rowsAffected == 1);
        return rowsAffected == 1;
    }

    @Override
    public List<Message> getMessages(int chatId) {
        return jdbcTemplate.query("SELECT * FROM message WHERE chatId=?",
                BeanPropertyRowMapper.newInstance(Message.class), chatId);
    }

    @Override
    public boolean addMessage(MessageRequest message) {
        int rowsAffected = jdbcTemplate.update("INSERT INTO message (text, timeStamp, chatId, senderId) VALUES(?,?,?,?)",
                message.getText(), new Timestamp(new Date().getTime()), message.getChatId(), message.getSenderId());
        if (rowsAffected == 1) {
            rowsAffected = jdbcTemplate.update("UPDATE chat SET isUnread = 1 WHERE chatId = ?", message.getChatId());
        }
        return rowsAffected == 1;
    }

    @Override
    public int getParticipantId(int chatId, int myProfileId) {
        List<int[]> profileIdsList = jdbcTemplate.query("SELECT profile1, profile2 FROM chat WHERE chatId=?", rs -> {
            List<int[]> idsList = new ArrayList<>();
            while (rs.next()) {
                int[] ids = new int[2];
                ids[0] = rs.getInt("profile1");
                ids[1] = rs.getInt("profile2");
                idsList.add(ids);
            }
            return idsList;
        }, chatId);

        if (profileIdsList == null ||profileIdsList.isEmpty()) {
            logger.info("The array profileIds is empty... returning profileId -1");
            return -1;
        }

        int[] profileIds = profileIdsList.get(0);
        logger.info("profile1 has profileId {}", profileIds[0]);
        logger.info("profile2 has profileId {}", profileIds[1]);
        return profileIds[0] == myProfileId ? profileIds[1] : profileIds[0];
    }
}
