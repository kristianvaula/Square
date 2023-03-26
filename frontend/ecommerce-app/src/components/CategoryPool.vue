<template>
    <div class="category-pool">
        <div class="pool">
            <CategoryCard v-for="instance in categories" 
            :key="instance.id" 
            :CategoryInfo="instance"
            @selected-card-event="handleSelection"
            @deselected-card-event="removeSelection"
            ></CategoryCard>
        </div>
    </div>

</template>

<script>
import CategoryUtils from '@/utils/CategoryUtils.js'
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
                store,
                selectedCategoryID: null, 
                categories: null
            }
        },
        mounted () {
            CategoryUtils.getCategories()
                .then((response) => {
                if(response.data) {
                    console.log(response.data)
                    this.categories = response.data
                }
                })
                .catch((err) => {
                console.log(err)
                })
        },  

        methods: {
            handleSelection(value) {
                this.chosenCategoryID = value.categoryId
                this.$emit("selectedCategoryEvent", this.chosenCategoryID)
            },

            removeSelection(value) {
                this.chosenCategoryID = null
                this.$emit("deselectCategoryEvent", value.categoryId)
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