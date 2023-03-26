<template>
    <div class="pool-vertical">
      <h2>Chats</h2>      
        <ChatCard v-for="chat in chats"
        :key="chat.chatId"
        :ChatInfo="chat"
        @selected-chat-card="selectChat"       
        ></ChatCard>
    </div>
</template>
  <!--
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
  -->
  
  <script>
  import ChatCard from '@/components/ChatCard.vue';
  import '@/assets/style/Pool.css';
  import { useTokenStore } from "@/store/token.js";
  import ChatUtils from '@/utils/ChatUtils.js'
  import ProfileUtils from "@/utils/ProfileUtils";
  
  export default {
    name: 'ChatPool',
    components: {
      ChatCard
    },
    data () {
      return {
        chats: []
      }
    },
    async mounted() {
      //chatId, eMail to participant, lastMessage, isUnread, lastMessageTime

      const tokenStore = useTokenStore();
      let profileId = await ProfileUtils.getProfileId(tokenStore.loggedInUser);
      console.log("Chats for profileId " + profileId.data);
      let chatsPromise = await ChatUtils.getChats(profileId.data);
    
      let chatArray = chatsPromise.data;//chatId, profile1, profile2, isUnread
      console.log(chatArray)

      this.chats = chatArray;
      return {
        tokenStore
      }
    },
    methods: {
      //should emit to mainChat
      
      selectChat(chat) {
        console.log("Emiting from chatPool:")
        console.log(chat)
        this.$emit('selectedChatEvent', chat);
      },
    
     /*
      onChatSelected(chat) {
        this.selectedChat = chat;
      },
      */
    },
  };
  </script>
  