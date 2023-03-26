<template>
  <div class="chat-card" @click="openChat">
    <div>
      <h3 class="chat-title">{{ this.participantEmail }}</h3>
        <span class="last-message">{{ this.lastMessage }}</span>
        <img v-if="this.readStatus" class="read-status" src="@/assets/icons/unread.png"> 
        <span v-if="lastMessage" class="last-message-time">{{ this.lastMessageTime }}</span>
    </div>
  </div>
</template>
<script>
  import '@/assets/style/ChatCard.css'
  import ChatUtils from '@/utils/ChatUtils.js'
  import { useTokenStore } from "@/store/token.js";

  export default {
    name: "ChatCard",
    props: {
        ChatInfo: {
            type: Object,
            required: true
        }
    },
    data() {
      return {
        participantEmail: 'failed to load', 
        lastMessage: 'No messages',
        readStatus: 'false',        
        lastMessageTime: ''           
      }
    },
    setup() {
      const tokenStore = useTokenStore();
      return {
        tokenStore
      }
    },
    async mounted () {  
      console.log(this.ChatInfo)
      this.readStatus = this.ChatInfo.isUnread;
      
      if (this.ChatInfo.chatId == 1) { //todo: remove, is just for error searching....
        console.log("Sending an request to get particiapnt e-mail for chatId " + this.ChatInfo.chatId + " and where my e-mail is " + this.tokenStore.loggedInUser)
        const participantEmailPromise = await ChatUtils.getParticipant(this.ChatInfo.chatId, this.tokenStore.loggedInUser)
        console.log(participantEmailPromise)
        console.log(participantEmailPromise.data)
        this.participantEmail = participantEmailPromise.data;
      }
      
      const messagePromise = await ChatUtils.getMessages(this.ChatInfo.chatId);
      const messages = messagePromise?.data;
      if (messages) {
        this.lastMessage = messages[messages.length - 1].text
        this.lastMessageTime = new Date(messages[messages.length - 1].timeStamp).toDateString();
      }
    },
    methods: {
        openChat() {
          //todo: call method from backend which get messages and emit them
          const selectedChat = {
            chatId: this.ChatInfo.chatId,
            profile1: this.ChatInfo.profile1,
            profile2: this.ChatInfo.profile2,
            isUnread: this.ChatInfo.inUnread,
            participantEmail: this.participantEmail
          };
          console.log("Emiting from chatCard: ")
          console.log(selectedChat)
          this.$emit("selectedChatCard", selectedChat);//chat object som man kan hente alt fra...            
        }
    }
  };
</script>
  
  
  