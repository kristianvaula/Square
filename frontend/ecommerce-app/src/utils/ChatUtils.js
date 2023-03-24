import axios from 'axios';

const baseurl = "http://localhost:8081/chat";
const config = {
    headers: {
        "Content-type": "application/json"
    },
};

export default {
    getChats(profileId) {
        return axios.get(`${baseurl}/my-chats`, profileId, config)
    },
    newChat(chat) {
        return axios.post(`${baseurl}/new-chat`, chat)
    },
    getMessages(chatId) {
        return axios.get(`${baseurl}/${chatId}/messages`, chatId, config)
    }, 
    newMessage(chatId, message) {
        return axios.post(`${baseurl}/${chatId}/new-message`, message)
    },    
}
