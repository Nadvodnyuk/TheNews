<template>
    <div class="sub_header"></div>
    <div class="login_page">
        <form class="login_block" @submit.prevent="submitForm">
            <h1 class="login_header">Вход в личный аккаунт </h1>
            <div class="login_container">
                <div class="info_input">
                    <input type="text" v-model="login.username" placeholder=" Имя пользователя" id="login.username" required="" 
                    />
                    <input type="text" v-model="login.password" placeholder=" Пароль" id="login.password" required="" />
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
        </form>
    </div>
</template>

<script>
import { useCatalog } from '../../store/catalog.js';
import { mapState, mapActions } from 'pinia';
import LoginDataService from '../../services/LoginDataService';

export default {
    name: 'Login',
    data() {
        return {
            login: {
                username: null,
                password: null
            },
            submitted: false,
        };
    },
    methods: {
        async submitForm() {
            try {

                LoginDataService.login(this.login)
                    .then(response => {
                        console.log(response.data);
                        this.submitted = true;
                    })

                this.$router.push('/');
            } catch (e) {
                this.error = 'Проверьте все поля!';
            }
        },
        newTutorial() {
            this.submitted = false;
            this.tutorial = {};
        }
        // scrollToTop() {
        //     // Прокручиваем страницу наверх (координаты 0, 0)
        //     window.scrollTo(0, 0);
        // },

    },
    mounted() {
    },
}
</script>

<style scoped src="./login.css"></style>