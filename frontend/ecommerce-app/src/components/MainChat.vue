<template>
    <div class="main-chat">
        <h1 class="title">{{ otherUsersName }}</h1>
        <div class="messages-container" id="chatbox">
            <div class="messages">
            <div v-for="message in messages" :key="message.id" :class="['message', message.sender === 'me' ? 'sent' : 'received']">
                <div class="message-text">{{ message.text }}</div>
                <div class="message-time">{{ formatDate(message.createdAt) }}</div>
            </div>
            </div>
        </div>
        <form class="send-message" @submit.prevent="sendMessage">
            <input class="message-input" type="text" v-model="newMessageText" placeholder="Type your message...">
            <button type="submit" >Send</button>
        </form>
    </div>
</template>
  
<script>
  import moment from 'moment';
  import axios from 'axios';
  import '@/assets/style/MainChat.css'
import UniqueID from '@/features/UniqueID';
  
  export default {
    data() {
      return {
        messages: [
        { id: 1, text: 'Hi there!', createdAt: '2022-03-21T12:30:00Z', user: 'me' },
        { id: 2, text: 'How are you?', createdAt: '2022-03-21T12:35:00Z', user: 'otherUser' },
        { id: 3, text: 'I am doing well, thanks for asking!', createdAt: '2022-03-21T12:40:00Z', user: 'me' }
        ],
        newMessageText: '',
        otherUsersName: 'otherUser',
        currentUser: 'me' 
      }
    },
    mounted() {
      axios.get('/api/messages').then(response => {
        this.messages = response.data;
      });
    },
    methods: {
        // move axios to seperate file and import component
        sendMessage() {
            let messagetoSend = {
                id: UniqueID,
                sender: this.currentUser,
                text: this.newMessageText.trim(),
                createdAt: new Date().toISOString()
            }
            if (messagetoSend.text.trim() !== '') {
                this.messages.push(messagetoSend)
            }
            this.scrollToBottom()
            

            /*
            axios.post('/api/messages', {
                sender: 'User 1', // replace with the actual sender's information
                text: this.newMessageText.trim()
            }).then(response => {
                this.newMessageText = '';
                this.messages.push(response.data);
            });}
            */
        },
        scrollToBottom() {
            var chatbox = document.getElementById("chatbox");
            chatbox.scrollTop = chatbox.scrollHeight;
        },
        formatDate(date) {
            return moment(date).format('HH:mm');
        },
    },
  }
</script>
  