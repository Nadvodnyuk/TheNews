import { createRouter } from "vue-router";
import Homepage from "./home/Home.vue";
import Reg from "./reg/Reg.vue";
import Login from "./login/Login.vue";

const routes = [
  {
    path: "/",
    name: "homePage",
    component: Homepage,
  },

  {
    path: "/reg/",
    name: "regPage",
    component: Reg,
  },

  {
    path: "/login/",
    name: "loginPage",
    component: Login,
  },
];

export default function (history) {
  return createRouter({
    history,
    routes,
  });
}
