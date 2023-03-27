<template>
    <div class="pool-vertical">
      <h2>Chats</h2>      
        <ChatCard v-for="chat in chats"
        :key="chat.chatId"
        :ChatInfo="chat"
        @selected-chat-card="selectChat" 
        class="item"      
        ></ChatCard>
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
      ChatCard
    },
    data () {
      return {
        chats: []
      }
    },
    async mounted() {      
      const tokenStore = useTokenStore();
      console.log(tokenStore.loggedInUser)
      let profileId = await ProfileUtils.getProfileId(tokenStore.loggedInUser);
      console.log("Chats for profileId " + profileId.data);
      let chatsPromise = await ChatUtils.getChats(profileId.data);
    
      let chatArray = chatsPromise.data;

      this.chats = chatArray;
      return {
        tokenStore
      }
    },
    methods: {            
      selectChat(chat) {        
        this.$emit('selectedChatEvent', chat);
      },
    },
  };
  </script>
  