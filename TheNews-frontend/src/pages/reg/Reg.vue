<template>
    <div class="sub_header"></div>
    <div class="reg_page">
        <form class="reg_block" @submit.prevent="submitForm">
            <h1 class="reg_header">Регистрация </h1>
            <div class="reg_container">
                <div class="name_input">
                    <input type="text" v-model="reg.first_name" placeholder=" Имя" id="firstname" required="" />
                    <input type="text" v-model="reg.last_name" placeholder=" Фамилия" id="lastname" required="" />
                </div>
                <div class="info_input">
                    <input type="text" v-model="reg.email" placeholder=" Email" id="email" required="" />
                    <input type="text" v-model="reg.password" placeholder=" Пароль" id="password" required="" />
                </div>
                <div class="reg_btn">
                    <button type="submit"> Создать аккаунт </button>
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
import axios from 'axios'
import { useCatalog } from '../../store/catalog.js';
import { mapState, mapActions } from 'pinia';
import RegDataService from '../../services/RegDataService'

export default {
    name: 'Reg',
    data() {
        return {
            reg: {
                first_name: null,
                last_name: null,
                email: null,
                password: null
            },
            submitted: false,
        };
    },
    methods: {
        async submitForm() {
            try {

                RegDataService.create(this.reg)
                    .then(response => {
                        console.log(response.this.reg);
                        this.submitted = true;
                    })

                this.$router.push('/login');
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

<style scoped src="./reg.css"></style>