import axios from 'axios';

const baseurl = "http://localhost:8081/chat";
const config = {
    headers: {
        "Content-type": "application/json"
    },
};

export default {
    getChats(profileId) {
        return axios.get(`${baseurl}/my-chats/${profileId}`, config)
    },
    newChat(chat) {
        return axios.post(`${baseurl}/new-chat`, chat, config)
    },
    getMessages(chatId) {
        return axios.get(`${baseurl}/messages/${chatId}`, config)
    }, 
    newMessage(chatId, message) {
        return axios.post(`${baseurl}/new-message/${chatId}`, message, config)
    },    
}
