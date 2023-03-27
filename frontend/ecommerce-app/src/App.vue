<template>
  <nav>
    <NavComponent/>
  </nav>
  <router-view/>
  <FooterComponent/>
</template>

<script>
import NavComponent from './components/NavComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';

export default {
  components: {NavComponent, FooterComponent}
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
