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
  
  export default {
    name: 'ChatPool',
    components: {
      ChatCard,
    },
    props: {
      chats: {
        type: Array,
        required: true,
      },
      chatSelected: {
        type: Function,
        required: true,
      },
    },
    
    methods: {
      selectChat(chat) {
        this.$emit('selected', chat);
      },
      onChatSelected(chat) {
        this.selectedChat = chat;
      },
    },
  };
  </script>
  