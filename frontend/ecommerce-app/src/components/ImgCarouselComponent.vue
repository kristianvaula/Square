<template>
  <div class="carousel-frame">
    <div class="carousel">
      <div class="carousel-image-container">
        <p v-if="this.images.length===0">Your images will be displayed here</p>
        <img v-if="loaded" :src="images[currentIndex].src">
      </div>
    </div>
    <div class="carousel-controls">
      <button class="button-small" @click="previousImage" :disabled="this.images === undefined || currentIndex === 0">Previous</button>
      <button class="button-small" @click="nextImage" :disabled="this.images === undefined || currentIndex === images.length - 1">Next</button>
    </div>
  </div>
  
</template>

<script>
import '@/assets/style/ImgCarouselComponent.css'

export default {
  props: {
    images: {
      type: Array,
      required: true,
      default:undefined
    },
    loaded: {
      type: Boolean, 
      required: true, 
      default: false
    }
  },
  data() {
    return {
      currentIndex: 0
    };
  },
  computed: {
    currentImage() {
      return this.images[this.currentIndex];
    }
  },
  methods: {
    previousImage() {
      this.currentIndex--;
    },
    nextImage() {
      this.currentIndex++;
    }
  }
};
</script>
