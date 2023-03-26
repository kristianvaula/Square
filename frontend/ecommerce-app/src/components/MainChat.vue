<template>
  <div class="main-chat">
    <h1 class="title" v-if="participantEmail"> {{ participantEmail }}</h1>
    <h1 class="title" v-else> {{ "Select a Chat" }}</h1>

    <div class="messages-container" id="chatbox">
      <div class="messages">
        <div v-for="message in messages" :key="message.messageId" :class="['message', message.senderId === loggedInUserId ? 'sent' : 'received']">
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
import { useTokenStore } from "@/store/token.js";
import ProfileUtils from '@/utils/ProfileUtils';  

export default {
  name: 'MainChat',
  props: {
    ChatInfo: {
      type: Object,
      required: true,
    }
  },
  watch: {
    async ChatInfo() {
      if(this.ChatInfo) {
        console.log("Retrieved chatInfo:")
        console.log(this.ChatInfo)

        console.log("participantEmail: " + this.participantEmail)
        this.participantEmail = this.ChatInfo.participantEmail;
        console.log("participantEmail: " + this.participantEmail)

        this.loggedInUser = this.tokenStore.loggedInUser
        
        let loggedInUserPromise = await ProfileUtils.getProfileId(this.loggedInUser);
        
        console.log(loggedInUserPromise)
        this.loggedInUserId = loggedInUserPromise.data;
        console.log("userId is now set to: " + this.loggedInUserId);
          
        this.loadMessages();
      }
    }
  },
  data() {
    return {
      participantEmail: '',
      messageText: '',
      messages: [],
      loggedInUser: '',
      loggedInUserId: -1
    };
  },
  setup() {
    const tokenStore = useTokenStore();

    return {
      tokenStore
    }
  },
  computed: {
    otherUsersName() {
      const otherUser = this.messages.find((message) => message.sender !== this.loggedInUser);
      return otherUser ? otherUser.sender : '';
    },
  },
  methods: {
    async sendMessage() {
      const loggedInProfileId = this.loggedInUserId;
      console.log("The logged in profileId is " + loggedInProfileId);
      
      const newMessage = {
        chatId: this.ChatInfo.chatId,
        senderId: loggedInProfileId,
        text: this.messageText.trim(),
      };
      if (newMessage.text.trim() !== '') {
        await ChatUtils.newMessage(newMessage);
        this.loadMessages();
        this.messageText = '';
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
    async loadMessages() {
      try {
        console.log("Retrieving getMessages")
        const response = await ChatUtils.getMessages(this.ChatInfo.chatId);
        console.log("Result from retrieving getMessages")
        console.log(response)
        this.messages = response.data;
        this.scrollToBottom();
      } catch (e) {
        console.log(e)
      }
    }
  }
}
</script>
