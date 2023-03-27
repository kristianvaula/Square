import axios from 'axios';
import { useTokenStore } from "@/store/token";

const baseurl = "http://localhost:8081/user/chat";
function getAxiosConfig() {
    let tokenStore = useTokenStore(); 

    return {
        headers: {
            "Content-type": "application/json",
            "Authorization": "Bearer " + tokenStore.jwtToken
        },
    }; 
}

export default {
    getChats(profileId) {
        return axios.get(`${baseurl}/my-chats/${profileId}`, getAxiosConfig())
    },
    newChat(chat) {
        return axios.post(`${baseurl}/new-chat`, chat, getAxiosConfig())
    },
    getMessages(chatId) {
        return axios.get(`${baseurl}/messages/${chatId}`, getAxiosConfig())
    }, 
    newMessage(message) {
        return axios.post(`${baseurl}/new-message`, message, getAxiosConfig())
    }, 
    getParticipant(chatId, eMail) {
        return axios.post(`${baseurl}/participant`, JSON.stringify({chatId, eMail}), getAxiosConfig())
    },
    readChat(chatId) {
        return axios.post(`${baseurl}/read-chat/${chatId}`, getAxiosConfig())
    }
}
