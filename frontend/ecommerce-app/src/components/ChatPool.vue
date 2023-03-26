<template>
    <div class="pool-vertical">
      <h2>Chats</h2>
      <ul>
        <li v-for="chat in chats" :key="chat.id">
          <ChatCard :chat="chat" @click="selectChat(chat)" />
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import ChatCard from '@/components/ChatCard.vue';
  import '@/assets/style/Pool.css';
  import { useTokenStore } from "@/store/token.js";
  import ChatUtils from '@/utils/ChatUtils.js'
  import ProfileUtils from "@/utils/ProfileUtils";
  
  export default {
    name: 'ChatPool',
    components: {
      ChatCard,
    },
    async setup() {  
      const tokenStore = useTokenStore();
      let profileId = await ProfileUtils.getProfileId(tokenStore.loggedInUser);
      console.log("Chats for profileId " + profileId.data);
      let chatsPromise = await ChatUtils.getChats(profileId.data);
    
      console.log(chatsPromise.data);
      let chats = chatsPromise.data;
      
      return {
        tokenStore,
        chats
      }
    },
    methods: {
      //should emit to mainChat
      selectChat(chat) {
        this.$emit('selected', chat);
      },
      onChatSelected(chat) {
        this.selectedChat = chat;
      },
    },
  };
  </script>
  