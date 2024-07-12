<template>
  <div class="sub_header"></div>
  <div class="reg_page">
    <form class="reg_block" @submit.prevent="submitForm">
      <h1 class="reg_header">Регистрация</h1>
      <div class="reg_container">
        <div class="name_input">
          <input
            type="text"
            v-model="reg.first_name"
            placeholder=" Имя"
            id="reg.first_name"
            required=""
          />
          <input
            type="text"
            v-model="reg.last_name"
            placeholder=" Фамилия"
            id="reg.last_name"
            required=""
          />
        </div>
        <div class="info_input">
          <input
            type="text"
            v-model="reg.username"
            placeholder=" Логин"
            id="reg.username"
            required=""
          />
          <input
            type="text"
            v-model="reg.password"
            placeholder=" Пароль"
            id="reg.password"
            required=""
          />
        </div>
        <div class="reg_btn">
          <button type="submit">Создать аккаунт</button>
        </div>
        <div class="login_ref">
          <router-link :to="{ name: 'loginPage' }">
            <a href="url">Есть аккаунт? Войти</a>
          </router-link>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import RegDataService from "../../services/RegDataService";

export default {
  name: "Reg",
  data() {
    return {
      reg: {
        first_name: null,
        last_name: null,
        username: null,
        password: null,
      },
      submitted: false,
    };
  },
  methods: {
    async submitForm() {
      try {
        RegDataService.create(this.reg).then((response) => {
          console.log(response.data);
          this.submitted = true;
        });

        this.$router.push("/login");
      } catch (e) {
        this.error = "Проверьте все поля!";
      }
    },
    newTutorial() {
      this.submitted = false;
      this.tutorial = {};
    },
  },
  mounted() {},
};
</script>

<style scoped src="./reg.css"></style>
