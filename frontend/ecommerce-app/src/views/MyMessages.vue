<template>
  <div class="my-messages">
    <ChatPool
      @selected-chat-event="selectedChatToShow"
    ></ChatPool>
    <MainChat
     :ChatInfo="chatInfo"
     ></MainChat>
  </div>
</template>

<script>
import MainChat from '@/components/MainChat.vue';
import ChatPool from '@/components/ChatPool.vue';
import '@/assets/style/MyMessages.css'

export default {
  name: 'MyMessages',
  components: {
    ChatPool,
    MainChat,
  },
  data() {
    return {
      chatInfo: null,
    };
  },
  methods: {
    selectedChatToShow (chat) {      
      this.chatInfo = chat;
    },
  },
};
</script>
