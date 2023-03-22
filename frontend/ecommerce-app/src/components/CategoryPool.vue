<template>
    <div class="category-pool">
        <CategoryCard v-for="instance in this.store.CategoryList" 
        :key="instance.id" 
        :CategoryInfo="instance" 
        @selected-card-event="handleSelection"
        @deselected-card-event="removeSelection"
        ></CategoryCard>
    </div>

</template>

<script>
import { store } from '@/store';
import CategoryCard from './CategoryCard.vue';
    export default {
        name: "ProductPool",
        components: {
            CategoryCard,
        },

        data() {
            return {
                store,
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