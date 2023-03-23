<template>
    <div class="pool-vertical">
        <ChatCard class="item" v-for="instance in this.store.ChatList" :key="instance.id" :ChatInfo="instance" ></ChatCard>
    </div>
</template>

<script>
import { store } from '@/store';
import ChatCard from './ChatCard.vue';
import '@/assets/style/Pool.css'
    export default {
        name: "ProductPool",
        components: {
            ChatCard,
        },

        data() {
            return {
                title: "Chats",
                store
            }
        }

    }


</script>

<style>
    .category-pool {
        display:flex;
        flex-direction:row;
        flex-wrap:wrap;
        padding: 5px;
    }


</style>