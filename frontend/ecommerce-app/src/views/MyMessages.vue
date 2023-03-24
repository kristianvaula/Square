<template>
      <div class="my-messages">
        <ChatPool/>
        <MainChat :chatId="chatId" :loggedInUser="loggedInUser" @update:loggedInUser="loggedInUser = $event" />      
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
        loggedInUser: '',
        chatId: '123',
      };
    },
  
  }
  </script>