<template>
    <div class="main-chat">
        <h1 class="title">{{ otherUsersName }}</h1>
        <div class="messages-container" id="chatbox">
            <div class="messages">
              <div v-for="message in messages" :key="message.id" :class="['message', message.sender === 'me' ? 'received' : 'sent' ]">
                  <div class="message-text">{{ message.text }}</div>
                  <div class="message-time">{{ formatDate(message.timeStamp) }}</div>
              </div>
            </div>
        </div>
        <form class="send-message" @submit.prevent="sendMessage">
            <input class="message-input" type="text" v-model="message.messageText" placeholder="Type your message...">
            <button type="submit" >Send</button>
        </form>
    </div>
</template>
  
<script>
  import moment from 'moment';
  import '@/assets/style/MainChat.css'
  import ChatUtils from '@/utils/ChatUtils.js'
  import httputils from '@/utils/httputils';
  
  export default {
    name: 'MainChat',
    props: {
      loggedInUser: {
        type: String,
        required: true,
      },
      chatId: {
        type: String,
        required: true,
      },
    },
    data() {
    return {
      message: {
        messageText: '',
      },
      messages: [
        { id: 1, text: 'Hi there!', timeStamp: '2022-03-21T12:30:00Z', sender: 'otherUser' },
        { id: 2, text: 'How are you?', timeStamp: '2022-03-21T12:35:00Z', sender: 'me' },
        { id: 3, text: 'I am doing well, thanks for asking!', timeStamp: '2022-03-21T12:40:00Z', sender: 'otherUser' },
      ],
    };
    },
    computed: {
      otherUsersName() {
        const otherUser = this.messages.find((message) => message.sender !== this.loggedInUser);
        return otherUser ? otherUser.sender : '';
      },
    },
    methods: {
        sendMessage() {
            let messagetoSend = {
                chatId: this.chatId,
                sender: this.loggedInUser,
                text: this.message.messageText.trim(),
                timeStamp: new Date().toISOString()
            }
            if (messagetoSend.text.trim() !== '') {
                this.messages.push(messagetoSend)
            }

            ChatUtils.newMessage(messagetoSend)

            this.scrollToBottom()
        },
        scrollToBottom() {
            var chatbox = document.getElementById("chatbox");
            chatbox.scrollTop = chatbox.scrollHeight;
        },
        formatDate(date) {
            return moment(date).format('HH:mm');
        },
        async loadMessages() {
          try {
            const response = await ChatUtils.getMessages(this.chatId);
            this.messages = response.data;
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
    },
    mounted() {
      this.loadMessages();
      this.loadLoggedInUser();
    },
  }
</script>
  