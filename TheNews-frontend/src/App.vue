<script setup>
import TheHeader from "./components/header/TheHeader.vue";
import Footer from "./components/footer/TheFooter.vue";
</script>

<template>
  <div class="wrapper">
    <div class="header">
      <TheHeader />
    </div>

    <div class="body">
      <router-view> </router-view>
    </div>

    <div class="footer">
      <Footer />
    </div>
  </div>
</template>

<script>
export default {
  created() {
    localStorage.clear();
  },
};
</script>
