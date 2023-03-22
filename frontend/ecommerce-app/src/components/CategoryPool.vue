<template>
<<<<<<< HEAD
    <div class="category-pool">
        <CategoryCard v-for="instance in this.store.CategoryList" 
        :key="instance.id" 
        :CategoryInfo="instance" 
        @selected-card-event="handleSelection"
        @deselected-card-event="removeSelection"
        ></CategoryCard>
=======
    <h3 class="title"> {{ title }}</h3> 
    <div class="pool">
        <CategoryCard v-for="instance in this.store.CategoryList" :key="instance.id" :CategoryInfo="instance" ></CategoryCard>
>>>>>>> d6d7440f259fbbb0530e444b8587139b1a2a4c1b
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
<<<<<<< HEAD
                store,
=======
                title: "Categories",
                store
>>>>>>> d6d7440f259fbbb0530e444b8587139b1a2a4c1b
            }
        },

        methods: {
            handleSelection(value) {
                console.log(value)
            },

            removeSelection() {
                console.log("code to remove the category from list")

            }
        },

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