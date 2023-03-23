<template>
      <div class="my-messages">
        <ChatPool/>
        <MainChat/>
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
    }
  
  }
  </script>