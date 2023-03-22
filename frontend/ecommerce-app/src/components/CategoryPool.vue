<template>
    <div class="category-pool">
        <h3 class="title"> {{ title }}</h3> 
        <div class="pool">
            <CategoryCard v-for="instance in this.store.CategoryList" 
            :key="instance.categoryId" 
            :CategoryInfo="instance"
            @selected-card-event="handleSelection"
            @deselected-card-event="removeSelection"
            ></CategoryCard>
        </div>
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
                store,
                selectedCategoryID: null 
            }
        },

        methods: {
            handleSelection(value) {
                this.chosenCategoryID = value.categoryId
                this.$emit("selectedCategoryEvent", this.chosenCategoryID)
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