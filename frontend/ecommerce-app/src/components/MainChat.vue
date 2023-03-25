<template>
  <div class="main-chat">
    <h1 class="title"> {{ selectedChat ? selectedChat.title : "Select a Chat" }}</h1>
    <div class="messages-container" id="chatbox">
      <div class="messages">
        <div v-for="message in messages" :key="message.id" :class="['message', message.sender === loggedInUser ? 'sent' : 'received']">
          <div class="message-text">{{ message.text }}</div>
          <div class="message-time">{{ formatDate(message.timeStamp) }}</div>
        </div>
      </div>
    </div>
    <form class="send-message" @submit.prevent="sendMessage">
      <input class="message-input" type="text" v-model="messageText" placeholder="Type your message...">
      <button type="submit">Send</button>
    </form>
  </div>
</template>

<script>
import '@/assets/style/MainChat.css'
import ChatUtils from '@/utils/ChatUtils.js'
//import httputils from '@/utils/httputils';

export default {
  name: 'MainChat',
  props: {
    chatId: {
      type: String,
      required: true,
    },
    loggedInUser: {
      type: Object,
      required: true,
    },
    otherUser: {
      type: Object,
      required: true,
    },
    isUnread: {
      type: Boolean,
      required: true,
    },
    selectedChat: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      messageText: '',
      messages: [],
    };
  },
  watch: { //er hentet for chatGPT - ikke sikker på om det fungerer
    chatId() {
      this.loadMessages();
    },
  },
  computed: {
    otherUsersName() {
      const otherUser = this.messages.find((message) => message.sender !== this.loggedInUser);
      return otherUser ? otherUser.sender : '';
    },
  },
  methods: {
    async sendMessage() {
      /*
      let responesePromise = await ChatUtils.getChats(33); //gets array with chats
      let chatArray = responesePromise.data;
      console.log(chatArray)
      let chat = chatArray[0];
      
      let message = {
        "chatId": 1,
        "text": 'Message from frontend',
        "senderId": 34
      }

      await ChatUtils.newMessage(message);

      let promise = await ChatUtils.getMessages(chat.chatId);
      console.log(promise.data)

      let newChat = {
        "profile1": 34,
        "profile2": 33
      }

      await ChatUtils.newChat(newChat)

      let responesePromise1 = await ChatUtils.getChats(33); //gets array with chats
      let chatArray1 = responesePromise1.data;
      console.log(chatArray1)

      //let promise2 = await ChatUtils.getMessages(chatArray1.length - 1);
      //console.log(promise2.data)
      */
 
      const newMessage = {
        chatId: this.chatId,
        sender: this.loggedInUser,
        text: this.messageText.trim(),
        timeStamp: new Date().toISOString()
      };
      if (newMessage.text.trim() !== '') {
        await ChatUtils.newMessage(newMessage);
        this.loadMessages();
      }
    },
    scrollToBottom() {
      var chatbox = document.getElementById("chatbox");
      chatbox.scrollTop = chatbox.scrollHeight;
    },
    formatDate(timestamp) {
      const date = new Date(timestamp);
      const hours = date.getHours();
      const minutes = "0" + date.getMinutes();
      return `${hours}:${minutes.substr(-2)}`;
    },
    /*
    async loadMessages() {
      try {
        const response = await ChatUtils.getMessages(this.chatId);
        this.messages = response.data;
        this.scrollToBottom();
      } catch (e) {
        console.log(e)
      }
    },
    async loadLoggedInUser() {
      try {
        const response = await httputils.getProfileByEmail("email");
        this.$emit('update:loggedInUser', response.data);
      } catch (e) {
        console.log(e)
      }
    },
    */
  },
  mounted() {
    //this.loadMessages();
    //this.loadLoggedInUser();
  },
}
</script>
