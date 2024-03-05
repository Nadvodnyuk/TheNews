<script setup>
import Header from './components/header/TheHeader.vue'
import Footer from './components/footer/TheFooter.vue'
</script>

<template>
  <div class="wrapper">
    <div class="header">
      <Header />
    </div>

    <div class="body">
      <router-view>
      </router-view>
    </div>

    <div class="footer">
      <Footer />
    </div>
  </div>
</template>
