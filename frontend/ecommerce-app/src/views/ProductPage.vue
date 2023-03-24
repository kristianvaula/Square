<template>
    <div class="product-page">
        <div class="product-pictures">
            <div>
                <ImgCarouselComponent :images="this.images"/>
            </div>
            <div>
                <div>
                    <h3> Favourite Product</h3>
                    <img v-if="this.isInFavourites" class="favourite-icon" src="@/assets/icons/heartfilled.png" @click="unfavouriteProduct">
                    <img v-else class="favourite-icon" src="@/assets/icons/heart.png" @click="favouriteProduct">
                
                </div>
                <div>
                    
                </div>
                
            </div>
        </div>

    </div>



</template>

<script>
import { store } from '@/store';
import ImgCarouselComponent from '@/components/ImgCarouselComponent.vue';
export default {
    name: `ProductPage`,

    data() {
        return {
            store,
            displayImage: false, 
            images: [],
            isInFavourites: false
        }
    },


    components: {
        ImgCarouselComponent
    },

    methods: {
        unfavouriteProduct() {
            this.isInFavourites = false
        },

        favouriteProduct() {
            this.isInFavourites = true
        }


    }
}


</script>

<style>
.product-pictures {
    display: flex;
    flex-direction: row;
}

.favourite-icon {
    cursor: pointer;
    max-width: 70px;
    max-height: 70px;
}

</style>