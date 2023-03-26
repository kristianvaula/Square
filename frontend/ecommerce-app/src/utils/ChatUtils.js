import axios from 'axios';

const baseurl = "http://localhost:8081/unauthorized/chat";
const config = {
    headers: {
        "Content-type": "application/json"
        //"Authorization": "Bearer " + jwtToken // todo: add jwtToken!!!
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
    newMessage(message) {
        return axios.post(`${baseurl}/new-message`, message, config)
    }, 
    getParticipant(chatId, eMail) {
        return axios.post(`${baseurl}/participant`, JSON.stringify({chatId, eMail}), config)
    },
    readChat(chatId) {
        return axios.post(`${baseurl}/read-chat/${chatId}`, config)
    }
}
