import { createApp } from "vue";
import "./style.css";
import App from "./App.vue";
import { createPinia } from "pinia";
import { createWebHistory } from "vue-router";
import createRouter from "./pages/routes.js";
import Antd from "ant-design-vue";
import "ant-design-vue/dist/reset.css";
import axios from "axios";

const router = createRouter(createWebHistory());
const app = createApp(App);
const store = createPinia();

app.config.globalProperties.axios = axios;
app.use(router).use(Antd).use(store).mount("#app");

if (sessionStorage.getItem("token")) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${sessionStorage.getItem("token")}`;
}