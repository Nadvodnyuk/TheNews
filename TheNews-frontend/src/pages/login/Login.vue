<template>
  <div class="sub_header"></div>
  <div class="login_page">
    <form class="login_block" @submit.prevent="submitForm">
      <h1 class="login_header">Вход в личный аккаунт</h1>
      <div class="login_container">
        <div class="info_input">
          <input
            type="text"
            v-model="login.username"
            placeholder=" Имя пользователя"
            id="login.username"
            required=""
            autocomplete="username"
          />
          <input
            type="password"
            v-model="login.password"
            placeholder=" Пароль"
            id="login.password"
            required=""
            autocomplete="current-password"
          />
        </div>
        <div class="login_btn">
          <button type="submit">Войти</button>
        </div>
        <div class="create_acc_ref">
          <router-link :to="{ name: 'regPage' }">
            <a href="url">Зарегистрироваться</a>
          </router-link>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import { useCatalog } from "../../store/catalog.js";
import { mapActions } from "pinia";
import LoginDataService from "../../services/LoginDataService";

export default {
  name: "Login",
  data() {
    return {
      login: {
        username: null,
        password: null,
      },
      submitted: false,
    };
  },
  methods: {
    ...mapActions(useCatalog, [
      "setName",
      "setToken",
      "setRole",
      "setId",
      "setFavorite",
      "setBlocked",
    ]),
    async submitForm() {
      try {
        await LoginDataService.login(this.login).then((response) => {
          console.log(response.data);
          localStorage.setItem("token", response.data.token);
          localStorage.setItem("name", response.data.name);
          this.setId(response.data.user_id);
          this.setName(response.data.name);
          this.setToken(response.data.token);
          this.setRole(response.data.role);
          this.setFavorite(response.data.favoriteTopics);
          this.setBlocked(response.data.blockedTopics);
          localStorage.setItem("role", response.data.role);
          this.submitted = true;
        });
        console.log(localStorage);
        this.$router.push("/");
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },
    newTutorial() {
      this.submitted = false;
      this.tutorial = {};
    },
  },
};
</script>

<style scoped src="./login.css"></style>
