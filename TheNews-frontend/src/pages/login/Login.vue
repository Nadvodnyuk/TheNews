<template>
    <div class="sub_header"></div>
    <div class="login_page">
        <div class="login_block">
            <h1 class="login_header">Вход в личный аккаунт </h1>
            <div class="login_container">
                <div class="info_input">
                    <input
                        type="text"
                        placeholder=" Email"
                        id="email"
                        required=""
                    />
                    <input
                        type="text"
                        placeholder=" Пароль"
                        id="password"
                        required=""
                    />
                </div>
                <div class="login_btn">
                    <button type="submit"> Войти </button>
                </div>
                <div class="create_acc_ref">
                    <router-link :to="{ name: 'regPage' }">
                        <a href="url">Зарегистрироваться</a>
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useCatalog } from '../../store/catalog.js';
import { mapState, mapActions } from 'pinia';
import LoginDataService from '../../services/LoginDataService'

export default {
    name: 'App',
    data() {
        return {
        };
    },
    methods: {
        scrollToTop() {
            // Прокручиваем страницу наверх (координаты 0, 0)
            window.scrollTo(0, 0);
        },
    },
    mounted() {
    },
}
</script>

<style scoped src="./login.css"></style>