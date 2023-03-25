<template>
  <div class="my-messages">
    <ChatPool @chatSelected="onChatSelected"/>
    <MainChat :selectedChat="selectedChat"/>
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
      selectedChatId: null,
    };
  },
  methods: {
    onChatSelected(chatId) {
      this.selectedChatId = chatId;
    },
  },
};
</script>
