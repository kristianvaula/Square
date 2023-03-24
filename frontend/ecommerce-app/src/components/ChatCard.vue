<template>
    <div class="chat-card" @click="openChat">
      <div>
          <h3 class="chat-title">{{ this.ChatInfo.title }}</h3>
          <span class="last-message">{{ this.ChatInfo.lastMessage }}</span>
          <img v-if="this.isUnread" class="read-status" src="@/assets/icons/unread.png"> 
          <span class="last-message-time">{{ this.ChatInfo.lastMessageTime }}</span>
      </div>
    </div>
  </template>
  
  <script>
  import '@/assets/style/ChatCard.css'
  import { store } from '@/store/index.js'
  
  export default {
    name: "ChatCard",

    props: {
        ChatInfo: {
            type: Object,
            required: true
        }
    },

    methods: {
        openChat() {
            this.$emit("selected", this.ChatInfo);
            this.isUnread = false;
        },
    },
  
    data() {
      return {
        store,
        isUnread: true,
      };
    },
  };
  </script>
  
  
  