<template>
    <div class="chat-card" @click="openChat">
      <div>
          <h3 class="chat-title">{{ this.participant }}</h3>
          <span class="last-message">{{ this.ChatInfo.lastMessage }}</span>
          <img v-if="this.isUnread" class="read-status" src="@/assets/icons/unread.png"> 
          <span class="last-message-time">{{ this.ChatInfo.lastMessageTime }}</span>
      </div>
    </div>
  </template>
  
  <script>
  import { useTokenStore } from "@/store/token";
  import '@/assets/style/ChatCard.css'
  import { store } from '@/store/index.js'
  import ChatUtils from '@/utils/ChatUtils.js'

  
  export default {
    name: "ChatCard",

    props: {
        ChatInfo: {//chatId, isUnread, profile1, profile2
            type: Object,
            required: true
        }
    },
    setup() { 
      const tokenStore = useTokenStore();

      let participant = ChatUtils.getParticipant(this.ChatInfo.chatId, tokenStore.loggedInUser)  
      return {
        tokenStore,
        participant
      }
    },
    methods: {
        openChat() {
          //todo: call method from backend which get messages and emit them
            this.$emit("selected", this.ChatInfo);            
            //this.isUnread = false;
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
  
  
  