<template>
    <div class="product-card">
      <div class="image-wrapper">
        <img
          src="https://images.finncdn.no/dynamic/1600w/2023/3/vertical-0/20/4/295/577/254_831193565.jpg"
          alt="Product Image"
        />
      </div>
      <div class="product-info">
        <div>
          <h3>{{ this.title }}</h3>
        </div>
        <div class="price-location">
          <div class="product-price">
            <span>Price:</span>
            <span>{{ this.price + "NOK" }}</span>
          </div>
          <div class="location">
            <span> {{ this.location }} </span>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import '@/assets/style/ProductCardComponent.css'
  export default {
    name: "ProductCard",
  
    data() {
      return {
        price: 2999,
        title: "Completely new Iphone 11",
        location: "Bergen",
      };
    },
  };
  </script>
  
  
  