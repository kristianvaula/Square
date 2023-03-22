<template>
    <h3 class="title"> {{ title }}</h3> 
    <div class="pool">
        <CategoryCard v-for="instance in this.store.CategoryList" :key="instance.id" :CategoryInfo="instance" ></CategoryCard>
    </div>

</template>

<script>
import { store } from '@/store';
import CategoryCard from './CategoryCard.vue';
import '@/assets/style/Pool.css'
    export default {
        name: "ProductPool",
        components: {
            CategoryCard,
        },

        data() {
            return {
                title: "Categories",
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