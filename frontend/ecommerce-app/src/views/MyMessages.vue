<template>
  <div class="my-messages">
    <ChatPool @chatSelected="onChatSelected" />
    <MainChat :selectedChat="selectedChat" :loggedInUser="loggedInUser" />
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
      loggedInUser: 'user1',
      selectedChat: null,
      chats: [
      {
        id: 1,
        title: 'Chat 1',
        lastMessage: 'Hi there!',
        lastMessageTime: '10:00 AM',
      },
      {
        id: 2,
        title: 'Chat 2',
        lastMessage: 'How are you?',
        lastMessageTime: '11:30 AM',
      },
      {
        id: 3,
        title: 'Chat 3',
        lastMessage: 'See you later!',
        lastMessageTime: '1:45 PM',
      },
      ],
    };
  },
  methods: {
    onChatSelected(chat) {
      this.selectedChat = chat;
    },
  },
};
</script>
