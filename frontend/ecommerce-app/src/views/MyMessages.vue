<template>
    <div class="home">
      <div>
        <MainChat/>
      </div>
    </div>
  </template>
  
  <script>
  import MainChat from '@/components/MainChat.vue';
  
  export default {
    name: 'HomePage',
    components: {
      MainChat,
    }
  
  }
  </script>