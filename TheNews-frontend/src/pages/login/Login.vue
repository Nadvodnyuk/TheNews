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
import HomeDataService from "../../services/HomeDataService";

export default {
  name: "Login",
  data() {
    return {
      login: {
        username: null,
        password: null,
      },
      submitted: false,
      intervalId: null,
      timeoutId: null,
      interval: 3600000,
      remainingTime: 3600000,
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
      console.log("Submitting form...");
      try {
        const response = await LoginDataService.login(this.login);
        console.log("Login successful:", response.data);
        sessionStorage.setItem("token", response.data.token);
        sessionStorage.setItem("name", response.data.name);
        this.setId(response.data.user_id);
        this.setName(response.data.name);
        this.setToken(response.data.token);
        this.setRole(response.data.role);
        this.setFavorite(response.data.favoriteTopics);
        this.setBlocked(response.data.blockedTopics);
        sessionStorage.setItem("role", response.data.role);
        this.submitted = true;
        this.startTimer();
        this.$router.push("/");
      } catch (e) {
        console.error("Error:", e);
        this.error = "Проверьте все поля!";
      }
    },
    startTimer() {
      console.log(`Токен действует ${this.interval / 60000} минут`);

      if (!sessionStorage.getItem("token")) {
        console.log("Токен отсутствует в sessionStorage.");
        return;
      }

      this.timeoutId = setTimeout(async () => {
        console.log("Logging out...");
        try {
          await HomeDataService.logout();
          this.clearSessionStorage();
          this.$router.push("/login");
        } catch (error) {
          console.error("Ошибка при выходе из аккаунта:", error);
        }
      }, this.interval);

      this.intervalId = setInterval(() => {
        this.remainingTime -= 600000;
        if (this.remainingTime <= 0) {
          clearInterval(this.intervalId);
        }
      }, 600000);

      setInterval(() => {
        if (!sessionStorage.getItem("token")) {
          console.log(
            "Токен отсутствует в sessionStorage."
          );
          this.stopTimer();
        }
      }, 60000);
    },
    stopTimer() {
      if (this.timeoutId) {
        clearTimeout(this.timeoutId);
      }
      if (this.intervalId) {
        clearInterval(this.intervalId);
      }
    },
    clearSessionStorage() {
      sessionStorage.removeItem("token");
      sessionStorage.removeItem("name");
      sessionStorage.removeItem("role");
      sessionStorage.removeItem("id");
      this.setName("");
      this.setToken("");
      this.setRole("");
      this.setId("");
      sessionStorage.clear();
    },
  },
  watch: {
    remainingTime(time) {
      if (time <= 0) {
        this.stopTimer();
      }
    },
  },
};
</script>

<style scoped src="./login.css"></style>
